package com.daniweb.selenium;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GreetingController {

	@GetMapping("/start")
	public String start() {
		return "start";
	}

}