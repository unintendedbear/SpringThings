package com.example.springboot;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

/*
The class is flagged as a @RestController, meaning it is ready for use by Spring MVC to handle web requests.
@RequestMapping maps / to the index() method.
When invoked from a browser or by using curl on the command line, the method returns pure text.
That is because @RestController combines @Controller and @ResponseBody, two annotations that results in web requests returning data rather than a view. 
*/

@RestController
public class HelloController {

	@RequestMapping("/hello")
	public String index() {
		return "Hello World!";
	}

}