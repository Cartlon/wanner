package com.ananjt.zero.test;

import com.ananjt.zero.entity.Student;
import com.ananjt.zero.util.rabbitMQ.Producer;
import com.ananjt.zero.util.rabbitMQ.QueueConsumer;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.UUID;

/**
 * Created by Admin on 2017/5/27.
 */
public class RabbitMQTest {

    public RabbitMQTest() throws Exception{
        Gson gson = new Gson();
        Student student = new Student();
        student.setIdNo(UUID.randomUUID().toString());
        student.setClazz("12班");
        student.setDescrible("中华任命共和国小学生");
        student.setGrade("二年级");
        student.setSex("男");
        student.setName("西XX_");

        QueueConsumer consumer = new QueueConsumer("queue");
        Thread consumerThread = new Thread(consumer);
        consumerThread.start();

        Producer producer = new Producer("queue");
        for (int i = 0; i < 10; i++) {
            HashMap message = new HashMap();
            student.setIdNo(String.valueOf(i));
            student.setName("西XX_" +i);
            message.put("message number", gson.toJson(student));
            producer.sendMessage(message);
            System.out.println("Message Number "+ i +" sent.");
        }
        /*while (true){
            HashMap message = new HashMap();
            message.put("message number", i);
            producer.sendMessage(message);
            System.out.println("Message Number "+ i +" sent.");
        }*/

    }

    public static void main(String[] args ) throws Exception{
         new RabbitMQTest();
     }
}
