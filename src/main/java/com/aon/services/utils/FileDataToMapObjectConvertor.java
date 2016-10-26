package com.aon.services.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class FileDataToMapObjectConvertor {

	public static Map<String, Object> readFile(File fin) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(fin));
		String line = null;
		Map<String, Object> root = new HashMap<>();
		List<Map<String, Object>> services = new ArrayList<>();
		List<String> jobTypes = new ArrayList<>();
		while ((line = br.readLine()) != null) {
			String[] columnsArray = line.split(",");
			if (jobTypes.size() > 0 && jobTypes.contains(columnsArray[1])) {
				addNewCompanyToExistingServiceType(columnsArray, services, columnsArray[1]);
			} else {
				services.add(buildNewService(columnsArray, jobTypes));
			}
		}
		root.put("services", services);
		br.close();
		return root;
	}

	@SuppressWarnings("unchecked")
	private static void addNewCompanyToExistingServiceType(String[] columnsArray, List<Map<String, Object>> services,
			String jobName) {
		Iterator<Map<String, Object>> serviceList = services.iterator();
		while (serviceList.hasNext()) {
			Map<String, Object> service = serviceList.next();
			if (jobName.equals(service.get("type"))) {
				List<Map<String, Object>> companyList = (List<Map<String, Object>>) service.get("companyList");
				companyList.add(buildCompanySection(columnsArray));
				break;
			}
		}
	}

	private static Map<String, Object> buildNewService(String[] columnsArray, List<String> jobTypes) {
		Map<String, Object> service = new HashMap<>();
		List<Map<String, Object>> companyList = new ArrayList<>();
		companyList.add(buildCompanySection(columnsArray));
		service.put("companyList", companyList);
		service.put("type", columnsArray[1]);
		jobTypes.add(columnsArray[1]);
		return service;
	}

	private static Map<String, Object> buildCompanySection(String[] columnsArray) {
		Map<String, Object> companyInfo = new HashMap<String, Object>();
		companyInfo.put("name", columnsArray[0]);
		companyInfo.put("phoneNumber", columnsArray[2]);

		if (columnsArray.length >= 4) {
			Map<String, Object> rules = new HashMap<>();
			List<Object> supportedPostalAddress = new ArrayList<>();
			String[] splitPostalSupported = columnsArray[3].split(":");
			supportedPostalAddress.addAll(Arrays.asList(splitPostalSupported[1].trim().replace("[", "").replace("]", "")
					.replace("{", "").replace("}", "").split(";")));

			rules.put("postalsSupported", supportedPostalAddress);
			if (columnsArray.length >= 5) {
				String[] splitCustomRules = columnsArray[4].split(";");
				for (int i = 0; i < splitCustomRules.length; ++i) {
					rules.put(splitCustomRules[i].trim().replace("{", "").replace("\"", "").split(":")[0],
							splitCustomRules[i].trim().replace("}", "").replace("\"", "").split(":")[1]);
				}
			}
			companyInfo.put("rules", rules);
		}
		return companyInfo;
	}
}