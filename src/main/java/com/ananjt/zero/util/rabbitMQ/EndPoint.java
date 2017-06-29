package com.ananjt.zero.util.rabbitMQ;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

/**
 * 将产生产者和消费者统一为 EndPoint类型的队列
 * Created by Admin on 2017/5/27.
 *
 */
public abstract class EndPoint {

    protected Channel channel;
    protected Connection connection;
    protected String endPointName;

    public EndPoint(String endpointName) throws IOException {
        this.endPointName = endpointName;

        //Create a connection factory
        ConnectionFactory factory = new ConnectionFactory();

        //hostname of your rabbitmq server
        factory.setHost("172.21.223.8");  //localhost
        factory.setUsername("chenanhai");
        factory.setPassword("chenanhai");
        factory.setPort(5672);

        //getting a connection
        connection = factory.newConnection();

        //creating a channel
        channel = connection.createChannel();
        //declaring a queue for this channel. If queue does not exist,
        //it will be created on the server.
        boolean durable = true;
        channel.queueDeclare(endpointName, false, false, false, null);

    }


    /**
     * 关闭channel和connection。并非必须，因为隐含是自动调用的。
     * @throws IOException
     */
    public void close() throws IOException{
        this.channel.close();
        this.connection.close();
    }
}
