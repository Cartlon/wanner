package com.ananjt.zero.util.rabbitMQ;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.ShutdownSignalException;
import org.apache.commons.lang.SerializationUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Admin on 2017/5/27.
 * 读取队列的程序端，实现了Runnable接口。
 * 消费者可以以线程方式运行，对于不同的事件有不同的回调函数，其中最主要的是处理新消息到来的事件
 */
public class QueueConsumer extends EndPoint implements Runnable, Consumer {

    public QueueConsumer(String endpointName) throws IOException {
        super(endpointName);
    }


    @Override
    public void run() {
        try {
            //start consuming messages. Auto acknowledge messages.
            channel.basicConsume(endPointName, true, this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Called when consumer is registered.
     */
    @Override
    public void handleConsumeOk(String consumerTag) {
        System.out.println("Consumer "+consumerTag +" registered");
    }

    /**
     * Called when new message is available.
     */
    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties basicProperties, byte[] body) throws IOException {

      /*  try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        Map map = (HashMap) SerializationUtils.deserialize(body);
        System.out.println("Message Number "+ map.get("message number").toString() + " received.");
    }

    @Override
    public void handleCancelOk(String consumerTag) {

    }

    @Override
    public void handleCancel(String consumerTag) throws IOException {

    }

    @Override
    public void handleRecoverOk(String consumerTag) {

    }

    @Override
    public void handleShutdownSignal(String s, ShutdownSignalException e) {

    }
}
