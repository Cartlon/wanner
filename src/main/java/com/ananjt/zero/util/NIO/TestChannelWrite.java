package com.ananjt.zero.util.NIO;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by Admin on 2017/6/30.
 */
public class TestChannelWrite {

    private static byte message[] = {83, 111, 109, 101, 32, 98, 121, 116, 101, 115, 46};

    public static void main(String[] args) throws IOException {

        FileOutputStream fileOutputStream = new FileOutputStream("D:\\NIO测试写入数据.txt");
        // 获取通道
        FileChannel fileChannel = fileOutputStream.getChannel();

        // 创建缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        // 数据存入缓冲区
        for (int i = 0; i < message.length; ++i) {
            buffer.put(message[i]);
        }
        // 重设buffer，将limit设置为position，position设置为0
        buffer.flip();

        // 将buffer中的数据写入
        fileChannel.write(buffer);

        fileOutputStream.close();
    }
}
