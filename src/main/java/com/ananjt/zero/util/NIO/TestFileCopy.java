package com.ananjt.zero.util.NIO;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;


/**
 * Created by Admin on 2017/6/30.
 * 321
 */
public class TestFileCopy {


    public static void main(String[] args) throws Exception {

        String sourcePath = "D:\\mysql-5.7.11-winx64.zip";

        String destPath1 = "D:\\uploadImg\\movie1.zip";

        String destPath2 = "D:\\uploadImg\\movie2.zip";

        String destPath3 = "D:\\uploadImg\\movie3.zip";

        long t1 = System.currentTimeMillis();

        traditionalCopy(sourcePath, destPath1);

        long t2 = System.currentTimeMillis();

        System.out.println("传统IO方法实现文件拷贝耗时:" + (t2 - t1) + "ms");


        nioCopy(sourcePath, destPath2);

        long t3 = System.currentTimeMillis();

        System.out.println("利用NIO文件通道方法实现文件拷贝耗时:" + (t3 - t2) + "ms");


        nioCopy2(sourcePath, destPath3);

        long t4 = System.currentTimeMillis();

        System.out.println("利用NIO文件内存映射及文件通道实现文件拷贝耗时:" + (t4 - t3) + "ms");


    }


    private static void nioCopy2(String sourcePath, String destPath) throws Exception {

        File source = new File(sourcePath);

        File dest = new File(destPath);

        if (!dest.exists()) {

            dest.createNewFile();

        }

        FileInputStream fis = new FileInputStream(source);

        FileOutputStream fos = new FileOutputStream(dest);

        FileChannel sourceCh = fis.getChannel();

        FileChannel destCh = fos.getChannel();

        MappedByteBuffer mbb = sourceCh.map(FileChannel.MapMode.READ_ONLY, 0, sourceCh.size());

        destCh.write(mbb);

        sourceCh.close();

        destCh.close();

    }


    private static void traditionalCopy(String sourcePath, String destPath) throws Exception {

        File source = new File(sourcePath);

        File dest = new File(destPath);

        if (!dest.exists()) {

            dest.createNewFile();

        }

        FileInputStream fis = new FileInputStream(source);

        FileOutputStream fos = new FileOutputStream(dest);

        byte[] buf = new byte[512];

        int len = 0;

        while ((len = fis.read(buf)) != -1) {

            fos.write(buf, 0, len);

        }

        fis.close();

        fos.close();

    }


    private static void nioCopy(String sourcePath, String destPath) throws Exception {

        File source = new File(sourcePath);

        File dest = new File(destPath);

        if (!dest.exists()) {

            dest.createNewFile();

        }

        FileInputStream fis = new FileInputStream(source);

        FileOutputStream fos = new FileOutputStream(dest);

        FileChannel sourceCh = fis.getChannel();

        FileChannel destCh = fos.getChannel();

        destCh.transferFrom(sourceCh, 0, sourceCh.size());

        sourceCh.close();

        destCh.close();

    }

}
