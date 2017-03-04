package io.file;


import java.io.*;

/**
 * Created by Kevin on 2017/1/5.
 */
public class CreateBigFile {
    private static String path = "D:/incoming/201701/";

    //private static Logger logger = Logger.getLogger(CreateBigFile.class);
    public static void main(String[] args) {
        String fileName = "D_Sub_A_20170105_01_0001.dat";
        PrintWriter writer = FileUtil.newWriter(path, fileName);
        String line = "W|20160411174144|01|18850531234|81|698041|1675945|0|20160401";


        int max = 1*1024*1024*30;
        for(int i=0;i<max;i++){
            writer.println(line);

            if(i%1000==0){
                writer.flush();
            }
        }
        writer.flush();
        FileUtil.closeWriter(writer);
    }

}
