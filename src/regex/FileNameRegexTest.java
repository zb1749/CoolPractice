package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Kevin on 2016/10/13.
 */
public class FileNameRegexTest {
    public static void main(String[] args) {
        //a little test
        String regex1 = "(\\d{3}|\\d{4})_Req_\\d{15}_(\\d{3}|\\d{4}).xml";
        String[] fileName1 = {"501123_Req_201609270078644_012.xml"};
        Pattern pattern = Pattern.compile(regex1);
        Matcher matcher = pattern.matcher(fileName1[0]);
        System.out.println(matcher.find());
        //function
        FileNameRegexTest rt = new FileNameRegexTest();
        String regex = "(\\d{3}|\\d{4})_Req_\\d{15}_(\\d{3}|\\d{4}).xml";
        String[] fileName = {"501123_Req_201609270078644_012.xml"};
        System.out.println(rt.getMatcher(fileName,regex)[0]);
    }

    /**
     * 正则表达式文件过滤
     */
    private String[] getMatcher(String[] data, String reg) {
        String[] strArrayRet = null;
        if (reg!=null) {
            if (null != data && data.length > 0) {
                strArrayRet = new String[data.length];
                int i = 0;
                for (String str : data) {
                    if (str!=null) {
                        Pattern pattern = Pattern.compile(reg);
                        Matcher matcher = pattern.matcher(str);
                        if (matcher.find()) {
                            strArrayRet[i++] = str;
                        }
                    }
                }
            }
        } else {
            strArrayRet = data;
        }

        return strArrayRet;
    }
}
