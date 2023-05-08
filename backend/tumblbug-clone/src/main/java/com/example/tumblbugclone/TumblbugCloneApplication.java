package com.example.tumblbugclone;

import com.example.tumblbugclone.dbexample.DBConnectClass;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TumblbugCloneApplication {

	public static void main(String[] args) {
		SpringApplication.run(TumblbugCloneApplication.class, args);
		//DBConnectClass.logic();
	}

}
