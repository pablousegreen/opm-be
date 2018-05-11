package com.intelmas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.intelmas.controller.dto.NodesCountResponse;
import com.intelmas.controller.dto.NodesModel;
import com.intelmas.dto.model.OssEntity;
import com.intelmas.exception.ProcessingException;
import com.intelmas.service.GralServicesService;

/** Class to catch requests from FE with general operations.
 * @author Brian Mm 
 *
 */
@CrossOrigin
@RestController
public class GralServicesController {
	
	@Autowired
	private GralServicesService gralService;
	
	/**
	 * @param vendor name to get list of OSS from BD.
	 * @return List with OssEntity objects found in DB with given id.
	 */
	@RequestMapping(value = "/list/oss/{vendor}", method = RequestMethod.GET)
	public List<OssEntity> listOss(@PathVariable(value = "vendor") String vendor){
		
		return gralService.getOssList(vendor);
		
	}
	
	/**
	 * @param tech name to get list of nodes type from BD.
	 * @param vendor name to get list of nodes type from BD.
	 * @return List with String objects found in DB with given parameters.
	 */
	@RequestMapping(value = {"/list/nodes_type/{tech}/{vendor}","/list/nodes_type/{tech}"}, method = RequestMethod.GET)
	public List<String> listNodeTypes(@PathVariable(value = "tech") String tech,
			@PathVariable(value = "vendor", required = false) String vendor) {
		
		return gralService.getNodesTypeList(tech, vendor);
	}
	
	/**
	 * @param type name to get list of nodes from BD.
	 * @param vendor name to get list of nodes from BD.
	 * @return List with String objects found in DB with given parameters.
	 */
	@RequestMapping(value = {"/list/nodes/{type}/{vendor}","/list/nodes/{type}"}, method = RequestMethod.GET)
	public List<NodesModel> listNodes(@PathVariable(value = "type") String type,
			@PathVariable(value = "vendor", required = false) String vendor){
				
		return gralService.getNodesList(type, vendor);
		
	}
	
	/**
	 * @param region name to get count of nodes from BD.
	 * @param vendor name to get count of nodes from BD.
	 * @return List with NodesCountResponse objects found in DB with given parameters.
	 */
	@RequestMapping(value = {"/count/nodes/{region}/{vendor}","/count/nodes/{vendor}"}, method = RequestMethod.GET)
	public List<NodesCountResponse> countNodes(@PathVariable(value = "region", required = false) String region,
				@PathVariable(value = "vendor", required = false) String vendor){
		
		return gralService.getNodesCount(region, vendor);
		
	}
	
	/**
	 * @param node name to get list MOs of node from BD.
	 * @param vendor name to get list MOs of node from BD.
	 * @return List with String objects found in DB with given parameters.
	 * @throws ProcessingException view  to describe exception error
	 */
	@RequestMapping(value = {"/mo/{node}/{vendor}","/mo/{node}"}, method = RequestMethod.GET)
	public List<String> cellNode(@PathVariable(value = "node") String node,
				@PathVariable(value = "vendor", required = false) String vendor) throws ProcessingException{
		
		return gralService.getNodeCell(node, vendor);

	}
	
	/**
	 * @param node_type name to get list of node versions from BD.
	 * @param vendor name to get list of node versions from BD.
	 * @return List with String objects found in DB with given parameters.
	 */
	@RequestMapping(value = {"/list/versions/{node_type}/{vendor}","/list/versions/{node_type}"}, method = RequestMethod.GET)
	public List<String> getNodeVersions(@PathVariable(value = "node_type") String node_type,
			@PathVariable(value = "vendor", required = true) String vendor){
		
		return gralService.getNodeVersions(node_type, vendor);
		
	}
	
	/**
	 * @param vendor name to get list of regions from BD.
	 * @return List with String objects found in DB with given parameters.
	 */
	@RequestMapping(value = "/regions/{vendor}", method = RequestMethod.GET)
	public List<String> ossRegions(@PathVariable(value = "vendor") String vendor){
		
		return gralService.getRegions(vendor);
		
	}
}
