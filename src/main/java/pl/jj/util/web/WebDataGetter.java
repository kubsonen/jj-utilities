package pl.jj.util.web;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author JNartowicz
 */
public class WebDataGetter {

    public static final void scanPage(String[] urls) throws IOException {

        Set<String> data = new HashSet<>();
        for(String url: urls){

            try {
                Document document = Jsoup.connect(url).userAgent("Mozilla").timeout(28000).get();
                Integer power = getTheEnginePower(getDataLabel("Moc silnika", document));
                Integer torque = getTorque(getDataLabel("Moc silnika", document));
                Integer nm = getNm(getDataLabel("Maksymalny moment obrotowy", document));
                Integer countCyl = countCyl(getDataLabel("Liczba cylindrów", document));
                Integer fuel = getFuel(getDataLabel("Typ silnika", document));
                Integer capacity = getCap(getDataLabel("Pojemność skokowa", document));
                Integer cylLayout = getCylLayout(getDataLabel("Układ cylindrów", document));

                //Collect data
                data.add(StringUtils.join(Arrays.asList(power, nm, countCyl, fuel, capacity, torque, cylLayout), ","));
            } catch (Throwable t){
                System.out.println(url);
            }

        }

        System.out.println(StringUtils.join(data, "\n"));






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
            for(String g: s.split(" ")){
                try{
                    Integer power = Integer.valueOf(g.trim());
                    return power;
                } catch (Throwable t){}
            }
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

    private static Integer getNm(String element){
        String[] el = element.split(">");
        for(String s: el){
            for(String g: s.split(" ")){
                try{
                    Integer i = Integer.valueOf(g.trim());
                    return i;
                } catch (Throwable t){}
            }
        }
        throw new RuntimeException("Cannot obtain nm.");
    }

    private static Integer countCyl(String element){
        for(String s: element.split(">")){
            for(String g: s.split("<")){
                try{
                    Integer i = Integer.valueOf(g.trim());
                    return i;
                } catch (Throwable t){}
            }
        }
        throw new RuntimeException("Cannot obtain cyl:" + element);
    }

    private static Integer getFuel(String element){
        for(String s: element.split(">")){
            for(String g: s.split("<")){
                if(g.equals("benzynowy")){
                    return 1;
                } else if(g.equals("diesel")){
                    return 0;
                }
            }
        }
        throw new RuntimeException("Cannot obtain fuel: " + element);
    }

    private static Integer getCap(String element){
        for(String s: element.split(">")){
            for (String g: s.split("<")){
                for(String f: g.split("&nbsp;")){
                    try{
                        Integer i = Integer.valueOf(f);
                        return i;
                    } catch (Throwable t){}
                }
            }
        }
        throw new RuntimeException("Cannot obtain capacity:" + element);
    }

    private static Integer getCylLayout(String element){
        for(String s: element.split(">")){
            for(String g: s.split("<")){
                if(g.equals("widlasty")){
                    return 1;
                } else if(g.equals("rzędowy")){
                    return 0;
                } else if(g.equals("bokser")){
                    return 2;
                }
            }
        }
        throw new RuntimeException("Cannot obtain cyl layout: " + element);
    }
}
