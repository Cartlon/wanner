package com.ananjt.zero.util.NIO;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

/**
 * Created by Admin on 2017/6/29.
 */
public class TestChannelRead {

    public static void main(String[] args) throws Exception {

        String path = "F:\\维保修\\备份\\2016年7月28日\\qyh_qd_wbx_test\\logs\\catalina.out";

        FileInputStream fileInputStream = new FileInputStream(path);
        // 获取通道
        FileChannel fileChannel = fileInputStream.getChannel();

        // 创建缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);
//        CharBuffer charBuffer = CharBuffer.allocate(1024);

        // 读取数据到缓冲区
        fileChannel.read(buffer);

        // 重设buffer，将limit设置为position，position设置为0
        buffer.flip();

        // 设置编码
        Charset charSet = Charset.forName("utf-8");
        CharsetDecoder decoder = charSet.newDecoder();

        // 查看在position和limit之间是否有元素
        while (buffer.hasRemaining()) {
            // 读取buffer当前位置的整数
//            byte b = buffer.get();
//            System.out.print((char) b);
            System.out.println(decoder.decode(buffer));
        }

        fileInputStream.close();
    }
}
