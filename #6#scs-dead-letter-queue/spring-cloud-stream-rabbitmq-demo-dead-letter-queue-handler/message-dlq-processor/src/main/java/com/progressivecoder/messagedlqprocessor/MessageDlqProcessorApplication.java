package com.progressivecoder.messagedlqprocessor;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MessageDlqProcessorApplication {

	private static final String ORIGINAL_SUBSCRIBER_QUEUE_NAME = "messages.messageInputSubscriber";
	private static final String ORIGINAL_SUBSCRIBER_QUEUE_NAME2 = "messages.messageInputSubscriber2";

	private static final String SUBSCRIBER_DLQ_NAME = ORIGINAL_SUBSCRIBER_QUEUE_NAME + ".dlq";
	private static final String SUBSCRIBER_DLQ_NAME2 = ORIGINAL_SUBSCRIBER_QUEUE_NAME2 + ".dlq2";

	@Autowired
	RabbitTemplate rabbitTemplate;

	public static void main(String[] args) {
		SpringApplication.run(MessageDlqProcessorApplication.class, args);
	}

	@RabbitListener(queues = {SUBSCRIBER_DLQ_NAME})
	public void processFailedMessage(Message failedMessage){

		System.out.println("Message cannot be processed. Write to database: " + failedMessage.toString());
	}

	@RabbitListener(queues = {SUBSCRIBER_DLQ_NAME2})
	public void processFailedMessage2(Message failedMessage){

		System.out.println("Message cannot be processed. Write to database: " + failedMessage.toString());
	}

}
