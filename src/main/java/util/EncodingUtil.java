package util;

import java.io.UnsupportedEncodingException;

public class EncodingUtil {

    public static String changeEncoding(String oldStr) {
        String newStr = "";
        try {
            newStr = new String(oldStr.getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return newStr;
    }

}
