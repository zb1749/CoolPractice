package url;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by Kevin on 2017/8/4.
 */
public class UrlDecode {
    public static void main(String[] args) {
        //注意这个encode在post传送的时候也是有问题的，特殊符号，在拼接成url后在decode会被忽略掉，所以，在拼接url之前要encode
        String url = "05页评写文字评论+01页面评论列表_V7";
        String notencodedodecode = "";
        String encode = "";
        String encodedodecode = "";
        try {
            notencodedodecode = URLDecoder.decode(url, "utf-8");
            encode = URLEncoder.encode(url, "utf-8");
            encodedodecode = URLDecoder.decode(encode, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(" url = " + url );
        System.out.println(" not encode , do decode = " + notencodedodecode );
        System.out.println(" after encode  = " + encode );
        System.out.println(" encode , then do decode = " + encodedodecode );

    }
}
