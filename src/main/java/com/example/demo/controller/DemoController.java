package com.example.demo.controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.minidev.json.JSONObject;

@RestController
public class DemoController {

	@RequestMapping("/")
	public String working()
	{
		return "Server Working Fine";
	}
	
	@RequestMapping("/demohook")
    public ResponseEntity<Object> getData(@RequestBody Map<String, Object> payload) 
    { 	  
	  System.out.println(payload);
	  appendStrToFile(System.getProperty("user.dir")+"/my-logs/app.log",payload.toString());
	  JSONObject jobj  = new JSONObject();
	  jobj.put("status", 200);
      return new ResponseEntity<Object>(jobj, HttpStatus.OK);
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
