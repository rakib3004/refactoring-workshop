package workshop;

import org.junit.Test;

import workshop.PlainTextToHTML.PlaintextToHtmlConverter;
import workshop.PlainTextToHTML.SignChecker;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class HtmlPagesConverterTest {
    PlaintextToHtmlConverterFake converter = new PlaintextToHtmlConverterFake(null);

    @Test
    public void charConversion() throws Exception {
        converter.setRead("<");
        assertEquals("&lt;", converter.toHtml());

        converter.setRead(">");
        assertEquals("&gt;", converter.toHtml());

        converter.setRead("&");
        assertEquals("&amp;", converter.toHtml());

        converter.setRead("\n");
        assertEquals("<br />", converter.toHtml());
    }

    @Test
    public void noConversion() throws Exception {
        converter.setRead("simple");
        assertEquals("simple", converter.toHtml());
    }

    @Test
    public void mixedCharConversion() throws Exception {
        converter.setRead("<small>\n&space");
        assertEquals("&lt;small&gt;<br />&amp;space", converter.toHtml());
    }

    class PlaintextToHtmlConverterFake extends PlaintextToHtmlConverter {
        public PlaintextToHtmlConverterFake(List<SignChecker> signCheckers) {
            super(signCheckers);
            //TODO Auto-generated constructor stub
        }
        String text;
        protected void setRead(String text) {
            this.text = text;
        }
        @Override
        protected String read() throws IOException {
            return text;
        }
    }
}
