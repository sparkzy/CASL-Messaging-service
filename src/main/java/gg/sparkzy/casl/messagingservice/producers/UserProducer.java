package gg.sparkzy.casl.messagingservice.producers;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gg.sparkzy.casl.messagingservice.configuration.MessagingConfig;
import gg.sparkzy.casl.messagingservice.dto.Message;
import gg.sparkzy.casl.messagingservice.dto.User;

@RestController
@RequestMapping("/user")
public class UserProducer {

	@Autowired
	private RabbitTemplate template;

	@Autowired
	private MessagingConfig config;

	@PostMapping
	public String userRequest(@RequestBody User user) {
		Message<User> userMessage = new Message<User>(user, "Processing", "Requesting a user");
		template.convertAndSend(config.USER_EXCHANGE, config.USER_ROUTINGKEY, userMessage);
		System.out.println(" [x] Producing message: " + userMessage);
		return " [x] Producing user request for: " + user;
	}

}
