package com.trainticket;

import com.trainticket.utils.CPUDefect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PaymentApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentApplication.class, args);
		CPUDefect.injectCPUDefect();
	}
}
