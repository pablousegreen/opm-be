package com.intelmas.service;

import java.util.List;

import com.intelmas.exception.ProcessingException;


public interface KpiService {

	List<String> getLatestKpisByName(String organisation, String node) throws ProcessingException; 
}
