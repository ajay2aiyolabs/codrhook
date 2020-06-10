package com.example.demo.controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import net.minidev.json.JSONObject;

@RestController
public class DemoController {

	@RequestMapping("/")
	public String working()
	{
		return "Server Working Fine 1.0.2";
	}
	
	@RequestMapping("/demohook")
    public ResponseEntity<Object> getData(@RequestBody Map<String, Object> payload) 
    { 	  
	  System.out.println(payload);
	  appendStrToFile(System.getProperty("user.dir")+"/my-logs/app.log",payload.toString());
	  Unirest.setTimeouts(0, 0);
	  try {
		  String payloadstr="";
		 try {
			 payloadstr=URLEncoder.encode(payload.toString(),StandardCharsets.UTF_8.toString());
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpResponse<String> response = Unirest.get("https://script.google.com/macros/s/AKfycbx0f5Z220ajxxNc6t3NCOSLiKTxxSdX6WUXcEqZe5te_5THws0/exec?action=addlog&sheet=https://docs.google.com/spreadsheets/d/1SMQDPbZhHSeHH-KHtuQIvdmz3zc5nGw1mSLhUTpIzeI/edit?usp=sharing&data="+payloadstr)
		    .asString();
	} catch (UnirestException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

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
