package com.aon.search.job;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search/job")
public class JobSearchController {

	@Autowired
	private JobSearchServiceImpl jobSearchServiceImpl;

	/**
	 * 
	 * http://localhost:8080/search/job/cleaning?criterias={postcode=2000;
	 * duration=1}
	 * 
	 * TODO 1 Tomcat not working . see why . once done find the way to add path
	 * variable and requestparams
	 * 
	 * @param criterias
	 * @return
	 */
	@RequestMapping(value = "/cleaning", produces = "application/json")
	public List<Map<String, String>> cleaningJobs(@RequestParam(value = "criterias") String criterias) {
		return jobSearchServiceImpl.getSearchResult(criterias, "Cleaning");
	}

	@RequestMapping("/plumbing")
	public String plumbingJobs(@RequestParam(value = "criteria") String criteria) {
		return null;
	}
}