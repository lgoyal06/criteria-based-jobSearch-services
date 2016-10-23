package com.aon.search.job;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface JobSearchDao {

	public List<Map<String, String>> searchJobByCriteria(Map<String, String> criteria, String jobType) throws IOException;
}
