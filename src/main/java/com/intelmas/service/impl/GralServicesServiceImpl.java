package com.intelmas.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intelmas.controller.dto.NodeTypeCountResponse;
import com.intelmas.controller.dto.NodesCountResponse;
import com.intelmas.controller.dto.NodesModel;
import com.intelmas.dto.model.MosEntity;
import com.intelmas.dto.model.NodeTopologyEntity;
import com.intelmas.dto.model.NodesOssEntity;
import com.intelmas.dto.model.NodesTypeEntity;
import com.intelmas.dto.model.OssEntity;
import com.intelmas.dto.model.RegionsEntity;
import com.intelmas.repository.cassandra.MosRepository;
import com.intelmas.repository.cassandra.NodesNameRepository;
import com.intelmas.repository.cassandra.NodesOssRepository;
import com.intelmas.repository.cassandra.NodesTypeRepository;
import com.intelmas.repository.cassandra.OssRepository;
import com.intelmas.repository.cassandra.RegionsRepository;
import com.intelmas.service.GralServicesService;
import com.intelmas.utils.Constants;
import com.intelmas.utils.HelperMethods;
import com.intelmas.utils.MapUtil;


/**
 * Implementation class of interface
 * {@linkplain com.intelmas.service.GralServicesService}
 * 
 * @author Intelma
 *
 */
@Service
public class GralServicesServiceImpl implements GralServicesService {

	@Autowired
	private OssRepository ossRepository;
	@Autowired
	private NodesNameRepository nodesNameRepository;
	@Autowired
	private NodesTypeRepository typesRepository;
	@Autowired
	private NodesOssRepository nodesRepository;
	@Autowired
	private MosRepository mosRepository;
	@Autowired
	private RegionsRepository regionsRepository;
	
	private static final Logger LOG = LoggerFactory.getLogger(GralServicesServiceImpl.class);

	@Override
	public List<OssEntity> getOssList(String vendor) {
		List<OssEntity> ossList = new ArrayList<>();

		try {

			if (vendor != null) {
				ossList = ossRepository.findByOrganisation(HelperMethods.getVendor(vendor));
			} else {
				ossRepository.findAll().forEach(ossList::add);
			}

		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
		return ossList;
	}

	@Override
	public List<String> getNodesTypeList(String tech, String vendor) {
		List<NodesTypeEntity> typesList = new ArrayList<>();
		List<String> typesListResp = new ArrayList<String>();

		try {
			typesList = typesRepository.findByOrganisationAndTech(HelperMethods.getVendor(vendor), tech);

			for (NodesTypeEntity type : typesList) {
				if (type.getType_name().equals("ERBS") || type.getType_name().equals("RBS")
						|| type.getType_name().equals("RNC") || type.getType_name().equals("SGSN")) {
					typesListResp.add(type.getType_name());
				}
			}

			return typesListResp;

		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
		return typesListResp;
	}

	@Override
	public List<NodesCountResponse> getNodesCount(String region, String vendor) {
		List<String> nodes_type = new ArrayList<>();
		List<NodesCountResponse> techsCount = new ArrayList<NodesCountResponse>();

		try {

			// Get the list of technologies by vendor
			List<String> techs = getTechs(vendor);
			for (String tech : techs) {
				System.out.println("TECH: " + tech);
				int totalTech = 0;
				List<NodeTypeCountResponse> typesCount = new ArrayList<NodeTypeCountResponse>();
				NodesCountResponse nodesObj = new NodesCountResponse();
				nodesObj.setTech(tech);

				// Get nodes type by technology and vendor
				nodes_type = getNodesTypeList(tech, vendor);
				for (String type : nodes_type) {
					// System.out.println("TYPE: "+type.getType_name());
					NodeTypeCountResponse typeCount = new NodeTypeCountResponse();

					// Get nodes by node type and vendor
					List<NodesOssEntity> countNodes = nodesNameRepository
							.findByTypeAndOrganisation(HelperMethods.getVendor(vendor), type);

					if (region != null) {

						// Filter by region
						// Filter by node type
						// Get the count after filtering
						// And set the values to object NodeTypeCountResponse
						// countNodes.forEach(r -> {
						long numCounter = countNodes.stream().filter(p -> region.equals(p.getNode_region()))
								.filter(p -> p.getKey().getNode_type().equals(type)).count();

						typeCount.setType(type);
						typeCount.setTotal(Math.toIntExact(numCounter));
						// System.out.println("type.getType_name(): "+
						// Math.toIntExact(numCounter));
						// });
					} else {
						// countNodes.forEach(r -> {
						long numCounter = countNodes.stream().filter(p -> Objects.nonNull(p.getNode_region()))
								.filter(p -> !p.getNode_region().isEmpty())
								.filter(p -> p.getKey().getNode_type().equals(type)).count();

						typeCount.setType(type);
						typeCount.setTotal(Math.toIntExact(numCounter));
						// System.out.println("type.getType_name(): "+
						// Math.toIntExact(numCounter));
						// });
					}

					typesCount.add(typeCount);
					totalTech = totalTech + typeCount.getTotal();

				}
				nodesObj.setTypes(typesCount);
				nodesObj.setTotal(totalTech);

				techsCount.add(nodesObj);

			}
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
		return techsCount;
	}

	@Override
	public List<String> getNodeVersions(String node_type, String vendor) {
		List<String> versions = new ArrayList<String>();
		List<NodesOssEntity> nodes = null;

		try {
			// Get nodes list by vendor and node type
			nodes = nodesNameRepository.findByTypeAndOrganisation(HelperMethods.getVendor(vendor), node_type);

			// Filter null values
			// Filter empty values
			// Group by node version
			// Extract values to collect into List<String>
			versions = nodes.stream()
					.filter(p -> Objects.nonNull(p.getNode_version()))
					.filter(p -> !p.getNode_version().isEmpty())
					.filter(MapUtil.distinctByKey(p -> p.getNode_version()))
					.map(p -> Objects.toString(p.getNode_version(), null))
					.collect(Collectors.toList());
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
		return versions;
	}

	@Override
	public List<String> getRegions(String vendor) {
		List<String> regions = new ArrayList<>();
		List<RegionsEntity> regionsFound = new ArrayList<>();

		// Get whole regions list by organisation
		regionsFound = regionsRepository.findByOrganisation(HelperMethods.getVendor(vendor));

		// Extract values to collect into List<String>
		// Sort asc
		regions = regionsFound.stream().map(p -> Objects.toString(p.getRegion_name(), null))
				.sorted((p1, p2) -> p1.compareToIgnoreCase(p2)).collect(Collectors.toList());

		return regions;
	}

	@Override
	public List<NodesModel> getNodesList(String type, String vendor) {
		List<NodesModel> nodesListResp = new ArrayList<NodesModel>();
		List<OssEntity> ossList = new ArrayList<OssEntity>();
		List<NodesOssEntity> nodesList = null;
		try {
			// Get nodes by node type and vendor
			nodesList = nodesNameRepository.findByTypeAndOrganisation(HelperMethods.getVendor(vendor), type);
			ossList = getOssList(vendor);

			for (NodesOssEntity nodesOssEntity : nodesList) {
				NodesModel node = new NodesModel();

				node.setNode_name(nodesOssEntity.getKey().getNode_name());
				node.setRegion(nodesOssEntity.getNode_region());
				node.setTech(nodesOssEntity.getNode_tech());

				List<OssEntity> oss = ossList.stream()
						.filter(param -> nodesOssEntity.getNode_parent().equals(param.getOss_name()))
						.collect(Collectors.toList());

				node.setOss(oss.size() > 0 ? oss.get(0).getOss_name() : null);

				nodesListResp.add(node);
			}

			// Sort asc
			nodesListResp = nodesListResp.stream()
					.sorted((p1, p2) -> p1.getNode_name().compareToIgnoreCase(p2.getNode_name()))
					.collect(Collectors.toList());

		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
		return nodesListResp;
	}

	/**
	 * @param vendor
	 *            name to get the technologies from DB.
	 * @return List with String objects found in DB.
	 */
	public List<String> getTechs(String vendor) {
		List<String> techList = new ArrayList<>();
		List<OssEntity> ossFound = new ArrayList<>();

		// get all technologies records in DB
		ossRepository.findAll().forEach(ossFound::add);

		// Filter data by vendor
		// Group by technology
		// Extract values to collect into List<String>
		techList = ossFound.stream()
				.filter(p -> vendor.equalsIgnoreCase(p.getOss_organisation()))
				.filter(MapUtil.distinctByKey(p -> p.getOss_tech().toLowerCase()))
				.map(p -> Objects.toString(p.getOss_tech(), null))
				.collect(Collectors.toList());

		return techList;

	}

	@Override
	public List<String> getNodeCell(String node, String vendor) {

		NodeTopologyEntity nodeObj = new NodeTopologyEntity();
		List<MosEntity> mosList = new ArrayList<>();
		List<String> cells = new ArrayList<String>();

		try {

			// Get data for node provided in request
			nodeObj = nodesRepository.findByNodeName(node);

			if (nodeObj != null) {

				// In case nodes SGSN and Huawei returns node name as cell
				if ("SGSN".equalsIgnoreCase(nodeObj.getNode_type()) || Constants.HUAWEI.equalsIgnoreCase(vendor)
						|| nodeObj.getNode_type().contains("SGSN")) {
					cells.add(node);
				} else {

					// Get the MOs from DB by node name
					mosList = mosRepository.getCells(node);

					// Group by MO name
					mosList = mosList.stream().filter(MapUtil.distinctByKey(p -> p.getMoid()))
							.collect(Collectors.toList());

					for (MosEntity mo : mosList) {
						String cell = "";
						cell = getCell(mo.getMoid(), nodeObj.getNode_type(), vendor);
						if (cell != null && !cell.isEmpty() && !cells.contains(cell)) {
							cells.add(cell);
						}
					}
				}
			}

		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
		return cells;
	}

	/**
	 * @param moID
	 *            String found in DB to split and extract MO.
	 * @param nodeType
	 *            name of node type to extract MO by this parameter.
	 * @param vendor
	 *            name
	 * @return After evaluate conditions return string with cell
	 */
	public String getCell(String moID, String nodeType, String vendor) {
		String cell = "";
		String[] splitMoId = null;
		Map<String, String> mappedMoId = null;
		switch (nodeType) {
		case "ERBS":
			splitMoId = moID.split(",");
			mappedMoId = MapUtil.mappingLine(splitMoId, "=");
			cell = mappedMoId.get("EUtranCellFDD");
			break;
		case "SGSN":
			cell = moID;
			break;
		case "RBS":
			splitMoId = moID.split(",");
			mappedMoId = MapUtil.mappingLine(splitMoId, "=");
			if (mappedMoId.containsKey("HsDschResources")) {
				cell = moID;
			}
			break;
		case "RNC":
			splitMoId = moID.split(",");
			mappedMoId = MapUtil.mappingLine(splitMoId, "=");
			cell = mappedMoId.get("UtranCell");
			break;
		}

		return cell;
	}
}
