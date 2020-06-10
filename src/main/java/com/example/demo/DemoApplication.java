package com.example.demo;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class,SecurityAutoConfiguration.class})
public class DemoApplication {
	  private static final Logger logger = LoggerFactory.getLogger(DemoApplication.class);

	public static void main(String[] args) {
	      System.out.println("Current Directory = " + System.getProperty("user.dir"));
		SpringApplication.run(DemoApplication.class, args);
	      logger.info("just a test info log");

	}
	public static void appendStrToFile(String fileName, 
            String str) 
{ 
try { 

// Open given file in append mode. 
BufferedWriter out = new BufferedWriter( 
new FileWriter(fileName, true)); 
out.write(str+"\n"); 
out.close(); 
} 
catch (IOException e) { 
System.out.println("exception occoured" + e); 
} 
} 

}
