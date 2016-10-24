package com.aon.search.core;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;

import com.aon.services.utils.FileDataToMapObjectConvertor;

@Repository
@ComponentScan
public class JobSearchDaoImpl implements JobSearchDao {

	@Override
	public List<Map<String, Object>> findCompanyListByCriteria(Map<String, String> criteria, String jobType)
			throws IOException {
		Map<String, Object> sourceData = fetchSourceDataFromFileSystem();
		return fetchCompanyListMatchingCriteria(sourceData, jobType, criteria);
	}

	private Map<String, Object> fetchSourceDataFromFileSystem() throws IOException {
		return FileDataToMapObjectConvertor.readFile(new File(
				"C:\\Users\\lalit goyal\\git\\spring-restful-api\\src\\main\\resources\\company_wise_job_info.txt"));
	}

	@SuppressWarnings({ "unchecked" })
	// TODO make it more robust solution P2
	private List<Map<String, Object>> fetchCompanyListMatchingCriteria(Map<String, Object> sourceData, String jobType,
			Map<String, String> criteria) {
		List<Map<String, Object>> companyDetailsList = new ArrayList<>();
		Iterator<Map<String, Object>> serviceListIterator = ((List<Map<String, Object>>) sourceData.get("services"))
				.iterator();
		while (serviceListIterator.hasNext()) {
			Map<String, Object> currentService = serviceListIterator.next();
			if ((jobType).equals((String) currentService.get("type"))) {
				List<Map<String, Object>> companyList = (List<Map<String, Object>>) currentService.get("companyList");
				Iterator<Map<String, Object>> companyListIterator = companyList.iterator();
				while (companyListIterator.hasNext()) {
					Map<String, Object> companyInfo = companyListIterator.next();
					if (companyInfo.get("rules") == null) {
						Map<String, Object> companyDetails = new HashMap<String, Object>();
						companyDetails.put("name", (String) companyInfo.get("name"));
						companyDetails.put("phoneNumber", (String) companyInfo.get("phoneNumber"));
						companyDetailsList.add(companyDetails);
					} else if (criteria != null && criteria.size() > 0) {
						boolean isFirstMatchingRuleFoundForCompany = false;
						Map<String, Object> rules = (Map<String, Object>) companyInfo.get("rules");
						for (Map.Entry<String, Object> rule : rules.entrySet()) {
							for (Map.Entry<String, String> criteriaMap : criteria.entrySet()) {
								if (criteriaMap.getKey().equals(rule.getKey())) {
									if (rule.getValue() instanceof List<?>) {
										// TODO Not working P1
										if (((List<String>) rule.getValue())
												.contains(criteriaMap.getKey().toString())) {
											Map<String, Object> companyDetails = new HashMap<String, Object>();
											companyDetails.put("name", (String) companyInfo.get("name"));
											companyDetails.put("phoneNumber", (String) companyInfo.get("phoneNumber"));
											companyDetailsList.add(companyDetails);
											isFirstMatchingRuleFoundForCompany = true;
										}
									} else if (criteriaMap.getValue().equals((String) rule.getValue())) {
										Map<String, Object> companyDetails = new HashMap<String, Object>();
										companyDetails.put("name", (String) companyInfo.get("name"));
										companyDetails.put("phoneNumber", (String) companyInfo.get("phoneNumber"));
										companyDetailsList.add(companyDetails);
										isFirstMatchingRuleFoundForCompany = true;
									}
									break;
								}
							}
							if (isFirstMatchingRuleFoundForCompany) {
								break;
							}
						}
					}
				}
				break;
			}
		}
		return companyDetailsList;
	}
}