package pl.jj.util;

import org.junit.Test;
import pl.jj.util.generator.Helper;
import pl.jj.util.generator.RandomString;
import pl.jj.util.html.HtmlModel;
import pl.jj.util.html.HtmlReader;
import pl.jj.util.html.HtmlReaderException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;

public class Testing {

    @Test
    public void generateRandomString(){
        System.out.println(RandomString.getRandomString());
    }

    @Test
    public void testScanPage() {
//        WebDataGetter.scanPage(TestAutoData.urls);
    }

    @Test
    public void testStrings(){
        Set<String> strings = Helper.prepareRandomStrings(1000, 3);
        Map<Integer, String[]> integerMap = new HashMap<>();

        Iterator<String> stringIterator = strings.iterator();
        int i=0;
        int k = 0;
        while(true){

            String[] strings1 = new String[100];
            for(int j=0; j < 100; j++){
                strings1[j] = stringIterator.next();
                k++;
            }
            integerMap.put(i, strings1);

            if(i == 9){
                break;
            }
            i++;

        }

        Iterator<Integer> integerIterator = integerMap.keySet().iterator();
        while (integerIterator.hasNext()){
            Integer itNext = integerIterator.next();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("private static final char[] s" + itNext);
            stringBuilder.append(" = {");

            for(String s: integerMap.get(itNext)){
                stringBuilder.append("\"" + s + "\"" + ", ");
            }

            stringBuilder.append("};");

            System.out.println(stringBuilder.toString());

        }

    }

    @Test
    public void html() throws FileNotFoundException, HtmlReaderException {

        String filePath = "C:\\Users\\jnartowicz\\Desktop\\navigation.html";
        InputStream inputStream = new FileInputStream(new File(filePath));
        HtmlReader htmlReader = new HtmlReader(inputStream);

        HtmlModel htmlModel = htmlReader.readFile();
        showModel(htmlModel);
        if(htmlModel.getChildren() != null && !htmlModel.getChildren().isEmpty()){
            showModels(htmlModel.getChildren());
        }



    }

    private static void showModels(List<HtmlModel> htmlModels){
        for(HtmlModel htmlModel: htmlModels){
            showModel(htmlModel);
            if(htmlModel.getChildren() != null && !htmlModel.getChildren().isEmpty()){
                showModels(htmlModel.getChildren());
            }
        }
    }

    private static void showModel(HtmlModel htmlModel){

        System.out.println("Tag: " + htmlModel.getTag());

        //Add component text
        String text = htmlModel.getText();
        if(text != null){
            System.out.println("Text: " + text);
        }

        //Set id
        String id = htmlModel.getId();
        if(id != null){
            System.out.println("Id: " + id);
        }

        //Add CSS class
        List<String> classes = htmlModel.getClasses();
        if(classes != null && !classes.isEmpty()){
            for(String clazz: classes){
                System.out.println("Class: " + clazz);
            }
        }

        //Add styles to component
        Map<String, String> styles = htmlModel.getStyles();
        if(styles != null && !styles.isEmpty()){
            Iterator<String> styleIterator = styles.keySet().iterator();
            while (styleIterator.hasNext()){
                String key = styleIterator.next();
                System.out.println("Style: " + key + ", style: " + styles.get(key));
            }
        }

        //Add attributes to component
        Map<String, String> attributes = htmlModel.getAttributes();
        if(attributes != null && !attributes.isEmpty()){
            Iterator<String> attributeIterator = attributes.keySet().iterator();
            while (attributeIterator .hasNext()){
                String key = attributeIterator .next();
                System.out.println("Attribute: " + key + ", value: " + attributes.get(key));
            }
        }

        System.out.println();
    }


}
