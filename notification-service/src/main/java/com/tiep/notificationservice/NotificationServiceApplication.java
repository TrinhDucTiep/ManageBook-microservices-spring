package com.tiep.notificationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableBinding(Sink.class)
/*
- @EnableBinding: Annotation này được sử dụng để kích hoạt Spring Cloud Stream
và định nghĩa các binding (kết nối) tới các message broker
-  Sink.class là một trong những interface được định nghĩa sẵn trong Spring Cloud Stream.
Nó đại diện cho một kênh (channel) mà ứng dụng của bạn sẽ sử dụng để nhận dữ liệu từ message broker
 */
public class NotificationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotificationServiceApplication.class, args);
    }

    @StreamListener(Sink.INPUT)
//    @KafkaListener(topics = "trinhtiep")
    public void consumeMessage(String message) {
        System.out.println("Message: " + message);
    }

}
