package com.intelmas.service;

import java.util.List;

import com.intelmas.controller.dto.NodesCountResponse;
import com.intelmas.controller.dto.NodesModel;
import com.intelmas.dto.model.OssEntity;


/** Interface to define methods for implementation of GralServicesServiceImpl.
 * @author Intelma
 *
 */
public interface GralServicesService {

	/**
	 * @param vendor name to get the list of OSS from DB.
	 * @return List of oss found for vendor provided in request.
	 */
	List<OssEntity> getOssList(String vendor);

	/**
	 * @param tech name to get the list of node types from DB.
	 * @param vendor name to get the list of node types from DB.
	 * @return List of nodes type for vendor and technology provided in request.
	 */
	List<String> getNodesTypeList(String tech, String vendor);

	/**
	 * @param region name to get the count of nodes from DB.
	 * @param vendor name to get the count of nodes from DB.
	 * @return List with count of nodes per region and vendor.
	 */
	List<NodesCountResponse> getNodesCount(String region, String vendor);

	/**
	 * @param node name to get the list of cells from DB.
	 * @param vendor name to get the list of cells from DB.
	 * @return List with all cell related to the node provided in request.
	 */
	List<String> getNodeCell(String node, String vendor);

	/**
	 * @param node_type name to get the list of node versions from DB.
	 * @param vendor name to get the list of node versions from DB.
	 * @return List with all versions of node related to the node type and vendor provided in request.
	 */
	List<String> getNodeVersions(String node_type, String vendor);

	/**
	 * @param vendor name to get the list of regions from DB.
	 * @return List of regions related to specific vendor.
	 */
	List<String> getRegions(String vendor);

	/**
	 * @param type name to get the list of nodes from DB.
	 * @param vendor name to get the list of nodes from DB.
	 * @return List of nodes related to node type and vendor provided in request.
	 */
	List<NodesModel> getNodesList(String type, String vendor);
}
