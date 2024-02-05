package io.xlate.staedi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StaediMasterApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(StaediMasterApplication.class, args);
		ReadInterchangeAcknowledgementTest test = new ReadInterchangeAcknowledgementTest();
		test.isAcknowledgementSuccess();
		try {
			boolean result = test.isAcknowledgementSuccess();
			System.out.println("Acknowledgement Success: " + result);
			ReadFuncAcknowledgement read = new ReadFuncAcknowledgement();
			read.readFuncAcknowledgement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

}
