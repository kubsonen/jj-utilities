package pl.jj.util.web;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * @author JNartowicz
 */
public class WebDataGetter {

    public static final void scanPage() throws IOException {

//        String url = "https://www.autocentrum.pl/dane-techniczne/bmw/seria-5/e60/sedan/silnik-benzynowy-525-i-192km-2003-2005/";
        String url = "https://www.autocentrum.pl/dane-techniczne/mazda/6/iii/sport-kombi-facelifting-2018/silnik-diesla-2.2-skyactiv-d-184km-od-2018/";
        Document document = Jsoup.connect(url).userAgent("Mozilla").timeout(28000).get();

        Integer power = getTheEnginePower(getDataLabel("Moc silnika", document));
        System.out.println(power);


    }

    private static String getDataLabel(String dataLabel, Document document){
        Elements pngs = document.select("div[data-label=\"" + dataLabel + "\"]");
        if(pngs.size() == 1){
            Element element = pngs.first();
            Element parent = element.parent();
            Elements es = parent.select("span.dt-param-value");
            if(es.size() == 1) return es.get(0).toString();
        }
        return null;
    }

    private static Integer getTheEnginePower(String element){
        String[] el = element.split(">");
        for(String s: el){
            try{
                Integer power = Integer.valueOf(s);
                return power;
            } catch (Throwable t){}
        }
        throw new RuntimeException("Cannot obtain power.");
    }

    private static Integer getTorque(String element){
        String[] el = element.split(" ");
        for(int i = el.length; i >= 0; i--){
            try{
                Integer torque = Integer.valueOf(el[i]);
                return torque;
            } catch (Throwable t){}
        }
        throw new RuntimeException("Cannot obtain torque.");
    }


}
