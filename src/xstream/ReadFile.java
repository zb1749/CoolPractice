package xstream;

import java.io.*;

/**
 * Created by kevin on 2016/9/25.
 */
public class ReadFile {
    /**
     * 读取文件，转成String
     * @param path
     * @return
     */
    public static String readXml(String path){
        String fileContent = null;
        try {
            BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(path),"UTF-8"));
            String line="";
            StringBuffer buffer = new StringBuffer();
            while((line=br.readLine())!=null){
                buffer.append(line);
            }
            fileContent = buffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileContent;
    }
}
