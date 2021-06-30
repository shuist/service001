package com.bigFamily.service001.rabbitmq;

import com.rabbitmq.client.Channel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;

//@Component
public class MonitorConsumer {


//    @RabbitListener(queues = "helloworld")
//    @RabbitHandler
    public void onMessage(Channel channel, @Payload String message, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {

        System.out.println("======"+message);
    }


}
