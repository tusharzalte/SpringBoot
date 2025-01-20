package com.training.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("CalculateEmi")
public class IndexController {
	
	@GetMapping("{duration}/{interest}/{principle}")
	public String calculateEmi(@PathVariable("duration")int d,@PathVariable("interest")int i,@PathVariable("principle")int p)
	{
		return Integer.toString(d*i*p);
	}
}
