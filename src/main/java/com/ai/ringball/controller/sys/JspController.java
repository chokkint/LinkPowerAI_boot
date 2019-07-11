
package com.ai.ringball.controller.sys;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class JspController {
	@RequestMapping(value = "/jsp/{pageName}", method = RequestMethod.GET)
	public String backPage(@PathVariable String pageName) {
		return pageName;
	}

	@RequestMapping(value = "/jsp/{pageLocation}/{pageName}", method = RequestMethod.GET)
	public String backPage(@PathVariable String pageLocation, @PathVariable String pageName) {
		return pageLocation + "/" + pageName;
	}
	/*
	@RequestMapping(value = "/jsp/{pageLocation1}/{pageLocation2}/{pageName}",method = RequestMethod.GET)
	public String backPage(@PathVariable String pageLocation1,@PathVariable String pageLocation2,@PathVariable String pageName)
	{
		return pageLocation1+"/"+pageLocation2+"/"+pageName;
	}

	@RequestMapping(value = "/jsp/{pageLocation1}/{pageLocation2}/{pageLocation3}/{pageName}",method = RequestMethod.GET)
	public String backPage(@PathVariable String pageLocation1,@PathVariable String pageLocation2,@PathVariable String pageLocation3,@PathVariable String pageName)
	{
		return pageLocation1+"/"+pageLocation2+"/"+pageLocation3+"/"+pageName;
	}*/
	

}
