package com.aon.search.core;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface JobSearchDao {

	public List<Map<String, Object>> findCompanyListByCriteria(Map<String, String> criteria, String jobType)
			throws IOException;
}
