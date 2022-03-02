package workshop;

import org.junit.Test;

import workshop.PlainTextToHTML.PlaintextToHtmlConverter;

import static org.junit.Assert.assertEquals;

public class HtmlPagesConverterIntegrationTest {
    @Test
    public void convertFromActualFile() throws Exception {
        PlaintextToHtmlConverter converter = new PlaintextToHtmlConverter(null);
        assertEquals("abc<br />&lt;hello&gt;", converter.toHtml());
    }
}
