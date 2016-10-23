package com.aon.search.job;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

public class JobSearchServiceImpl {

	@Autowired
	private JobSearchDao jobSearchDao;

	public List<Map<String, String>> getSearchResult(String criterias, String jobType) {
		try {
			Map<String, String> criteriaKeyValuePairs = getCriteriaParametersMap(criterias);
			return jobSearchDao.searchJobByCriteria(criteriaKeyValuePairs, jobType);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Map<String, String> getCriteriaParametersMap(String criterias) {
		String[] arrayOfCriteria = criterias.split(";");
		Map<String, String> criteriaKeyValuePairs = new HashMap<String, String>();
		for (int i = 0; i < arrayOfCriteria.length; ++i) {
			String[] split = arrayOfCriteria[i].split("=");
			criteriaKeyValuePairs.put(split[0], split[1]);
		}
		return criteriaKeyValuePairs;
	}

}