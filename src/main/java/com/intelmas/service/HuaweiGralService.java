package com.intelmas.service;

import com.intelmas.controller.dto.KpisDTO;

public interface HuaweiGralService {

	/**
	 * @param type
	 * @param vendor
	 * @return List of links by type and vendor
	 */
	KpisDTO getLinks(String type, String vendor);

	/**
	 * @param vendor
	 * @param link
	 * @param mo
	 * @return 
	 */
	KpisDTO getLatest(String vendor, String link, String mo);

	/**
	 * @param vendor
	 * @param link
	 * @param mo
	 * @param resolution
	 * @return
	 */
	KpisDTO getLatestByResolution(String vendor, String link, String mo, int resolution);

	/**
	 * @param vendor
	 * @param kpi
	 * @return
	 */
	KpisDTO getAverageKpiPerTech(String vendor, String kpi);

	/**
	 * @param vendor
	 * @param kpi
	 * @return
	 */
	KpisDTO getBottomKpisByTech(String vendor, String kpi);

	/**
	 * @param vendor
	 * @param kpi
	 * @return
	 */
	KpisDTO getTopKpisByTech(String vendor, String kpi);

	/**
	 * @param vendor
	 * @param node
	 * @return
	 */
	KpisDTO getCells(String vendor, String node);


}
