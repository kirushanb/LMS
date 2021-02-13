package com.lms.app.com.lms.app.Controller;

/*
 * this controller for handle page views 
 * Model And view Controller for thos pages 
 */
 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class viewController   {

//	@RequestMapping("/")
//	public String callNSBM() {
//
//		return "nsbm.html";
//	}

    @RequestMapping("/computing")
    public String callSchollofComputing() {
    	
    	return "school computing.html";
    }
    
    @RequestMapping("/adminbk")
    public String adminback() {
    	
    	return "admin";
    }
    
    
    @RequestMapping("/fac")
    public String callFacilities() {
    	
    	return "facilities.html";
    }
    
    @RequestMapping("/eng")
    public String callSchollofEng() {
    	
    	return "school engineering.html";
    }
    
    @RequestMapping("/bus")
    public String callSchollofBusiness() {
    	
    	return "school management.html";
    }
    @RequestMapping("/act")
    public String activities() {
    	
    	return "activities.html";
    }

    @RequestMapping("/abt")
    public String about() {
    	
    	return "about.html";
    }
    
    @RequestMapping("/cnt")
    public String contuct() {
    	
    	return "contacts.html";
    }

	
	
	
	
	@RequestMapping("/y1")
	public String year1View() {
		
		return "year1";
	}
	
	@RequestMapping("/y2")
	public String year2View() {
		
		return "year2";
	}
	
	@RequestMapping("/y3")
	public String year3View() {
		
		return "year3";
	}
	@RequestMapping("/y4")
	public String year4View() {
		
		return "year4";
	}
	@RequestMapping("/pgd")
	public String pgdView() {
		
		return "pgd";
	}
	@RequestMapping("/other")
	public String otherView() {
		
		return "other";
	}
}
