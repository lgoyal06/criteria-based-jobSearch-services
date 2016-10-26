package com.aon.search.core;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@Service
@ComponentScan
public class JobSearchServiceImpl {

	@Autowired
	private JobSearchDao jobSearchDao;

	public List<Map<String, Object>> getSearchResult(String criterias, String jobType) throws IOException {
		Map<String, String> criteriaKeyValuePairs = getCriteriaParametersMap(criterias);
		return jobSearchDao.findCompanyListByCriteria(criteriaKeyValuePairs, jobType);
	}

	private Map<String, String> getCriteriaParametersMap(String criterias) {
		if (criterias != null) {
			String[] arrayOfCriteria = criterias.split(";");
			Map<String, String> criteriaKeyValuePairs = new HashMap<String, String>();
			for (int i = 0; i < arrayOfCriteria.length; ++i) {
				String[] split = arrayOfCriteria[i].split("=");
				criteriaKeyValuePairs.put(split[0].replace("{", "").replace("}", ""),
						split[1].replace("{", "").replace("}", ""));
			}
			return criteriaKeyValuePairs;
		}
		return null;
	}
}