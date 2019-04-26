package pl.jj.util.generator;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author JNartowicz
 */
public class RandomString {

    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyyMMddhhmmssSSS");

    private RandomString() {
    }

    public static final String getRandomString(){
        Date date = new Date();
        char[] timeStump = SIMPLE_DATE_FORMAT.format(date).toCharArray();
        StringBuilder sb = new StringBuilder();
        for(char c: timeStump){
            Integer integer = Integer.valueOf(c + "");
            String[] strings = RandomConst.getStringTab(integer);
            sb.append(strings[Helper.getRandomInt(strings.length - 1)]);
        }
        return sb.toString();
    }






}
