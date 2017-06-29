package com.ananjt.zero.util.rabbitMQ;

import org.apache.commons.lang.SerializationUtils;

import java.io.IOException;
import java.io.Serializable;

/**
 * Created by Admin on 2017/5/27.
 * The producer endpoint that writes to the queue.
 * 生产者类的任务是向队列里写一条消息
 */
public class Producer extends EndPoint{

    public Producer(String endpointName) throws IOException {
        super(endpointName);
    }

    public void sendMessage(Serializable object) throws IOException {
//        channel.basicQos(1);
        channel.basicPublish("",endPointName, null, SerializationUtils.serialize(object));
    }
}
