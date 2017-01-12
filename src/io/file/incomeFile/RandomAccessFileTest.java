package io.file.incomeFile;

import io.file.incomeFile.pojo.MonthFileLinePojo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kevin on 2016/12/27.
 */
public class RandomAccessFileTest {
    private static String originPath = "D:/IdeaProject/CoolPractice/src/io/file/incomeFile/D_Sub_A_20161227_01_0001.dat";
    private static int lineLength = 106;
    private static String partFileName = "D:/IdeaProject/CoolPractice/src/io/file/incomeFile/part1.dat";

    public static void main(String[] args) {
        int startPos = 0;
        File originFile = new File(originPath);
        File partFile = new File(partFileName);
        int perReadLength = lineLength * 2;
        byte[] perRead;
        RandomAccessFile rFile = null;
        RandomAccessFile wFile = null;
        int writeStart = 0;
        try {
            rFile = new RandomAccessFile(originFile, "r");
            wFile = new RandomAccessFile(partFile, "rw");
            for (int j = 0; j < (originFile.length() / perReadLength) + 1; j++) {
                startPos = perReadLength * j;
                System.out.println(startPos);
                perRead = new byte[perReadLength];
                // 移动指针到每“段”开头
                rFile.seek(startPos);
                //读取固定长度
                int s = rFile.read(perRead);
                if (s > 0) {
                    String read = new String(perRead, "gbk");
                    //如果有中文，gbk编码，一个中文占用2个byte，如果用utf-8，一个中文占用3-4个byte
                    System.out.println("read length: " + s + " ,origin file content length: " + originFile.length() + " ,string length: " + read.length() + " ,part file length now: " + partFile.length());
                    System.out.println(read);
                    String[] lineArr = read.split("0x0A");
                    List<MonthFileLinePojo> pojoList = new ArrayList<MonthFileLinePojo>();
                    for (int i = 0; i < lineArr.length; i++) {
                        MonthFileLinePojo pojo = parseLineToPojo(lineArr[i]);
                        if (pojo != null) {
                            pojoList.add(pojo);
                        }
                    }
                    System.out.println(pojoList.toString());
                    String out = pojoList.toString();
                    wFile.seek(writeStart);
                    wFile.write(out.getBytes());
                    writeStart = writeStart + out.length();
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (rFile != null) {
                try {
                    rFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (wFile != null) {
                try {
                    wFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    public static MonthFileLinePojo parseLineToPojo(String line) {
        MonthFileLinePojo pojo = new MonthFileLinePojo();
        if (line != null && line.length() == (lineLength - 4)) {
            pojo.setOperType(line.substring(0, 1).trim());
            pojo.setOperTime(line.substring(1, 15).trim());
            pojo.setSymbolType(line.substring(15, 17).trim());
            pojo.setSymbolValue(line.substring(17, 49).trim());
            pojo.setBusinessType(line.substring(49, 51).trim());
            pojo.setCorpCode(line.substring(51, 69).trim());
            pojo.setContractId(line.substring(69, 93).trim());
            pojo.setSubscribeStatus(line.substring(93, 94).trim());
            pojo.setEffectDate(line.substring(94, 102).trim());
            return pojo;
        } else {
            return null;
        }
    }

}
