package com.aon.search.core;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ComponentScan
@RequestMapping("/search/job")
public class JobSearchController {

	@Autowired
	private JobSearchServiceImpl jobSearchServiceImpl;

	/**
	 * 
	 * Sample URL -
	 * 
	 * http://localhost:8080/search/job/cleaning?criterias={postcode=2000;
	 * duration=1}
	 * 
	 * @param criterias
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/cleaning", produces = "application/json")
	public List<Map<String, Object>> cleaningJobs(@RequestParam(value = "criterias", required = false) String criterias)
			throws IOException {
		return jobSearchServiceImpl.getSearchResult(criterias, "Cleaning");
	}

	@RequestMapping(value = "/plumbing", produces = "application/json")
	public List<Map<String, Object>> plumbingJobs(@RequestParam(value = "criterias", required = false) String criterias)
			throws IOException {
		return jobSearchServiceImpl.getSearchResult(criterias, "Plumbing");
	}
}