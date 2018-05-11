package com.intelmas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.intelmas.controller.dto.KpisDTO;
import com.intelmas.service.HuaweiGralService;

@CrossOrigin
@RestController
public class HuaweiGralController {

	@Autowired
	HuaweiGralService huaweiService;
	
	@RequestMapping(value = "/kpiApi/getLatestKpis", method = RequestMethod.GET)
	public KpisDTO latest(
			@RequestParam(value = "vendor", required = true) String vendor,
			@RequestParam(value = "link", required = true) String link,
			@RequestParam(value = "mo", required = false) String mo){
		
		return huaweiService.getLatest(vendor, link, mo);
	}
	
	@RequestMapping(value = "/kpiApi/getLatestKpisByResolution", method = RequestMethod.GET)
	public KpisDTO latestByResolustion(
			@RequestParam(value = "vendor", required = true) String vendor,
			@RequestParam(value = "link", required = true) String link,
			@RequestParam(value = "mo", required = false) String mo,
			@RequestParam(value = "resolution", required = true) int resolution){
		
		return huaweiService.getLatestByResolution(vendor, link, mo, resolution);
	}
	
	@RequestMapping(value = "/kpiApi/getAverageKpiPerTech", method = RequestMethod.GET)
	public KpisDTO getAverageKpiPerTech(
			@RequestParam(value = "vendor", required = true) String vendor,
			@RequestParam(value = "kpi", required = true) String kpi){
		
		return huaweiService.getAverageKpiPerTech(vendor, kpi);
	}
	
	@RequestMapping(value = "/kpiApi/getBottomKpisByTech", method = RequestMethod.GET)
	public KpisDTO getBottomKpisByTech(
			@RequestParam(value = "vendor", required = true) String vendor,
			@RequestParam(value = "kpi", required = true) String kpi){
		
		return huaweiService.getBottomKpisByTech(vendor, kpi);
	}
	
	@RequestMapping(value = "/kpiApi/getTopKpisByTech", method = RequestMethod.GET)
	public KpisDTO getTopKpisByTech(
			@RequestParam(value = "vendor", required = true) String vendor,
			@RequestParam(value = "kpi", required = true) String kpi){
		
		return huaweiService.getTopKpisByTech(vendor, kpi);
	}
	
	@RequestMapping(value = "/kpiApi/getLinks/{type}/{vendor}", method = RequestMethod.GET)
	public KpisDTO getLinks(@PathVariable(value = "type") String type, @PathVariable(value = "vendor") String vendor){
		
		return huaweiService.getLinks(type, vendor);
	}
	
	@RequestMapping(value = "/kpiApi/getCells/{vendor}/{node}", method = RequestMethod.GET)
	public KpisDTO getCells(
			 @PathVariable(value = "vendor") String vendor,
			 @PathVariable(value = "node") String node){
		
		return huaweiService.getCells(vendor, node);
	}
}
