package com.cg.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
	import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.bind.annotation.RestController;
    @RestController
	@RequestMapping(value = "/hello")
	public class HelloController {
    	
    	
	@GetMapping(value = "/show")
	void showMsg() {
	System.out.println("welcome");
	//http://localhost:8086//studentApp//hello/show
	}}




