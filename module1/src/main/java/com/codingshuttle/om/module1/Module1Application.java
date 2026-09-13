package com.codingshuttle.om.module1;

import com.codingshuttle.om.module1.hw.CakeBaker;
import com.codingshuttle.om.module1.impl.EmailNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class Module1Application implements CommandLineRunner {

//	@Autowired
//	PaymentService paymentServiceObj;


//	@Autowired  //FIELD dependency injection (using @Autowired)
//	NotificationService notificationServiceObj;

	//RECOMMENDED WAY OF DI: CONSTRUCTOR dependency injection
//	NotificationService notificationServiceObj;
//	public Module1Application(NotificationService notificationServiceObj){
//		this.notificationServiceObj = notificationServiceObj;
//	}

	//IF WE WANT TO INJECT ALL THE BEANS (eg a cas where both sms and email needs to be sent on the device)
//	@Autowired
//	Map<String, NotificationService> notifMap = new HashMap<>();

	CakeBaker cakeBakerObj;
	public Module1Application(CakeBaker cakeBakerObj){
		this.cakeBakerObj = cakeBakerObj;
	}


	public static void main(String[] args) {
		SpringApplication.run(Module1Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception{
//		paymentServiceObj.pay();

		//TRADTITIONAL WAY: using the "new" keyword
//		NotificationService notificationServiceObj = new EmailNotificationService(); //instead of email, we can have smsNotificationService as well but we don't want it to be done manually
//		notificationServiceObj.send("Test message"); //this is tight coupling which we don't want
		//SOLUTION: we create a bean of all notification services i.e email and sms

		//SPRINGBOOT WAY: using dependency injection (@Autowired)
//		notificationServiceObj.send("Test message");

		//This is for running all the beans (i.e both email and sms in this case)
//		for(var notificationService: notifMap.entrySet()){
//			System.out.println(notificationService.getKey());
//			notificationService.getValue().send("Test message");
//		}


		cakeBakerObj.bakeCake();
	}

}
