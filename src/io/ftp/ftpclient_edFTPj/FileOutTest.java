package io.ftp.ftpclient_edFTPj;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by Kevin on 2016/10/14.
 */
public class FileOutTest {
    public static void main(String[] args) {
        //获取当前项目路径
        File directory = new File("");//设定为当前文件夹
        try{
            System.out.println(directory.getCanonicalPath());//获取标准的路径
            System.out.println(directory.getAbsolutePath());//获取绝对路径
        }catch(Exception e){
            e.printStackTrace();
        }

        String localPath = "./BDC_LOCAL/0085/incoming/20161014/5010_Req_201609270078644_012.xml";
        boolean resume = false;
        try {
            FileOutputStream out =
                    new FileOutputStream(localPath, resume);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
