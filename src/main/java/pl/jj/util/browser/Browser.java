package pl.jj.util.browser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author JNartowicz
 */
public class Browser {

    public List<Map<String, String>> getElementsValues(String url, String elementSelector, String... valueSelectors) throws IOException {
        List<Map<String, String>> maps = new ArrayList<>();
        Element body = body(url);
        List<Element> elements = body.select(elementSelector);
        for (Element el: elements) {
            Map<String, String> valueMap = new HashMap<>();
            for (String sel: valueSelectors) {
                valueMap.put(sel, el.select(sel).html());
            }
            maps.add(valueMap);
        }
        return maps;
    }

    public String getElementValue(String url, String selector) throws IOException, BrowserException {
        Element body = body(url);
        List<Element> el = body.select(selector);
        if (el != null && el.size() == 1)
            return el.iterator().next().html();
        else throw new BrowserException("Not found unique element");
    }

    public Element body(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        return doc.body();
    }

}
