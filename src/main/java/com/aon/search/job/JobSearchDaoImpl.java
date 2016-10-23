package com.aon.search.job;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.aon.services.utils.LoadDataFromPersistantSourceUtils;

public class JobSearchDaoImpl implements JobSearchDao {

	@Override
	public List<Map<String, String>> searchJobByCriteria(Map<String, String> criteria, String jobType)
			throws IOException {
		Map<String, Object> sourceData = LoadDataFromPersistantSourceUtils.readFile(new File(
				"C:\\Users\\lalit goyal\\git\\spring-restful-api\\src\\main\\resources\\company_wise_job_info.txt"));
		return fetchCompanyListByCriteria(sourceData, jobType, criteria);
	}

	@SuppressWarnings({ "unused", "unchecked" })
	private List<Map<String, String>> fetchCompanyListByCriteria(Map<String, Object> sourceData, String jobType,
			Map<String, String> criteria) {
		List<Map<String, String>> companyDetailsList = new ArrayList<>();
		Iterator<Map<String, Object>> servicesIterator = ((List<Map<String, Object>>) sourceData.get("services"))
				.iterator();
		while (servicesIterator.hasNext()) {
			Map<String, Object> service = servicesIterator.next();
			if ((jobType).equals((String) service.get("type"))) {
				List<Map<String, Object>> companyList = (List<Map<String, Object>>) service.get("companyList");
				Iterator<Map<String, Object>> companyIterator = companyList.iterator();
				while (companyIterator.hasNext()) {
					Map<String, Object> companyInfo = companyIterator.next();
					if (companyInfo.get("rules") == null) {
						Map<String, String> companyDetails = new HashMap<String, String>();
						companyDetails.put("name", (String) companyInfo.get("name"));
						companyDetails.put("phoneNumber", (String) companyInfo.get("phoneNumber"));
						companyDetailsList.add(companyDetails);
					} else {
						Map<String, Object> rules = (Map<String, Object>) companyInfo.get("rules");
						// TODO Compare and find common rules between two maps
						// rules
						// and criteria
						for (Map.Entry<String, Object> entry : rules.entrySet()) {
						}
					}
				}
			}
		}
		return companyDetailsList;
	}
}