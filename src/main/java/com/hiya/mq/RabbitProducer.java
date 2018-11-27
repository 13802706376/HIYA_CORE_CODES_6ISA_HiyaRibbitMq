package com.hiya.mq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitProducer
{
    public static void main(String[] args) throws IOException, TimeoutException
    {
        // �������ӹ���
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("guest");
        factory.setPassword("guest");
        // ���� RabbitMQ ��ַ
        factory.setHost("10.10.51.74");
        // ���������������������
        Connection conn = factory.newConnection();
        // ����ŵ�
        Channel channel = conn.createChannel();
        // ����������
        String exchangeName = "hello-exchange";
        channel.exchangeDeclare(exchangeName, "direct", true);
        String routingKey = "hola";
        // ������Ϣ
        byte[] messageBodyBytes = "quit".getBytes();
        channel.basicPublish(exchangeName, routingKey, null, messageBodyBytes);
        channel.close();
        conn.close();
    }
}
