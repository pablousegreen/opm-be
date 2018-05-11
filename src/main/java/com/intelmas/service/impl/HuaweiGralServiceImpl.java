package com.intelmas.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intelmas.controller.dto.Kpi;
import com.intelmas.controller.dto.KpisDTO;
import com.intelmas.dto.model.DataSourceEntity;
import com.intelmas.repository.cassandra.DataSourceRepository;
import com.intelmas.service.HuaweiGralService;
import com.intelmas.utils.MapUtil;
import com.intelmas.utils.TimeManager;

@Service
public class HuaweiGralServiceImpl implements HuaweiGralService {

	@Autowired
	DataSourceRepository dataRepository;

	@Override
	public KpisDTO getLatest(String vendor, String link, String mo) {

		// List<DataSourceEntity> nodes =
		// dataRepository.findCells(request.getOss(), request.getDatetime(),
		// request.getNode());
		List<DataSourceEntity> nodes = null;
		Set<Kpi> listResponse = new HashSet<>();
		KpisDTO response = new KpisDTO();
		String moid = mo != null && !mo.isEmpty() ? mo : link;
		int minus = 0;

		while (minus < 14400 && (nodes == null || nodes.size() == 0)) {

			nodes = dataRepository.findByNodeMoDatetime(vendor, TimeManager.getLatest(minus),
					link, moid);
			// System.out.println(nodes);
			minus = minus + 15;

		}

		nodes.forEach(node -> {
			Kpi kpi = new Kpi();
			
			node.getProperties().remove("Granularity ");
			
			kpi.setNode(node.getPk().getNode());
			kpi.setCell(node.getPk().getMoid());
			kpi.setRegion("R9");
			kpi.setTech("4G");
			kpi.setKpiType("SGSN");
			kpi.setDatetime(TimeManager.dateFormatDb.format(node.getPk().getDatetime()));
			kpi.setParameters(node.getProperties());
			
			node.getProperties().forEach((p1,p2)->{
				Double kilobits = 0.0;
				Double megabits = 0.0;
				
				if(p2.contains("K")){
					if(p1.equalsIgnoreCase("Inbound Packet Rate(pkt/s)") || p1.equalsIgnoreCase("Outbound Packet Rate(pkt/s)")){
						kilobits = Double.valueOf(p2.substring(0, p2.length()-1));
						kilobits = kilobits * 1000;
						
						node.getProperties().put(p1, kilobits.toString());
					}else{
						
						kilobits = Double.valueOf(p2.substring(0, p2.length()-1));
						megabits = (kilobits * 1000) / 1000000;
						
						node.getProperties().put(p1, megabits.toString());
					}
				}else if(p2.contains("M")){
					
					node.getProperties().put(p1, p2.substring(0, p2.length()-1));
					
				}
			});

			listResponse.add(kpi);
		});

		response.setKpis(listResponse);

		return response;
	}

	@Override
	public KpisDTO getLatestByResolution(String vendor, String link, String mo, int resolution) {
		List<DataSourceEntity> nodes = null;
		Set<Kpi> listResponse = new HashSet<>();
		KpisDTO response = new KpisDTO();
		Set<String> times = TimeManager.getTimesByResolution(resolution);
		String moid = mo != null && !mo.isEmpty() ? mo : link;

		nodes = dataRepository.findByResolution(vendor,
				TimeManager.getTimesByResolution(resolution), link, moid);

		nodes.forEach(node -> {
			Kpi kpi = new Kpi();
			
			node.getProperties().remove("Granularity ");
			
			kpi.setNode(link);
			kpi.setCell(node.getPk().getMoid());
			kpi.setRegion("R9");
			kpi.setTech("4G");
			kpi.setKpiType("SGSN");
			kpi.setDatetime(TimeManager.dateFormatDb.format(node.getPk().getDatetime()));
			kpi.setParameters(node.getProperties());
			
			node.getProperties().forEach((p1,p2)->{
				Double kilobits = 0.0;
				Double megabits = 0.0;
				
				if(p2.contains("K")){
					if(p1.equalsIgnoreCase("Inbound Packet Rate(pkt/s)") || p1.equalsIgnoreCase("Outbound Packet Rate(pkt/s)")){
						kilobits = Double.valueOf(p2.substring(0, p2.length()-1));
						kilobits = kilobits * 1000;
						
						node.getProperties().put(p1, kilobits.toString());
					}else{
						
						kilobits = Double.valueOf(p2.substring(0, p2.length()-1));
						megabits = (kilobits * 1000) / 1000000;
						
						node.getProperties().put(p1, megabits.toString());
					}
				}else if(p2.contains("M")){
					
					node.getProperties().put(p1, p2.substring(0, p2.length()-1));
					
				}
			});

			listResponse.add(kpi);
		});

		response.setKpis(listResponse);

		return response;
	}
	
	@Override
	public KpisDTO getAverageKpiPerTech(String vendor, String kpi) {
		List<DataSourceEntity> nodes = new ArrayList<>();
		List<DataSourceEntity> nodesFiltered = new ArrayList<>();
		Set<Kpi> listResponse = new HashSet<>();
		KpisDTO response = new KpisDTO();
		String dateFE = "";

//		String string = "2018-01-07 05:00:00";
		try {
			dataRepository.findAll().forEach(nodes::add);
			
			
			int minus = 0;
			while(nodesFiltered.isEmpty()){
				
				Date date = TimeManager.dateFormatDb.parse(TimeManager.getLatest(minus));
				minus = minus + 15;
				System.out.println(date);
				
				nodesFiltered = nodes.stream()
						.filter(p -> p.getPk().getOrganisation().equalsIgnoreCase(vendor))
						.filter(p -> p.getPk().getDatetime().equals(date))
						.filter(p -> p.getProperties().containsKey(kpi))
						.filter(p -> p.getProperties().get(kpi) != null)
						.filter(p -> !p.getProperties().get(kpi).isEmpty())
						.filter(p -> !p.getProperties().get(kpi).equalsIgnoreCase("--"))
						.collect(Collectors.toList());
				
				dateFE = TimeManager.dateFormatDb.format(date);
			}
			
			
			
			nodesFiltered.forEach(node -> {
				
				node.getProperties().forEach((p1,p2)->{
					Double kilobits = 0.0;
					Double megabits = 0.0;
					
					if(p2.contains("K")){
						if(p1.equalsIgnoreCase("Inbound Packet Rate(pkt/s)") || p1.equalsIgnoreCase("Outbound Packet Rate(pkt/s)")){
							kilobits = Double.valueOf(p2.substring(0, p2.length()-1));
							kilobits = kilobits * 1000;
							
							node.getProperties().put(p1, kilobits.toString());
						}else{
							
							kilobits = Double.valueOf(p2.substring(0, p2.length()-1));
							megabits = (kilobits * 1000) / 1000000;
							
							node.getProperties().put(p1, megabits.toString());
						}
					}else if(p2.contains("M")){
						
						node.getProperties().put(p1, p2.substring(0, p2.length()-1));
						
					}
					
				});
				
				System.out.println(node.getPk().getDatetime());
				
			});

			double average = nodesFiltered.stream()
//					.filter(p -> p.getPk().getOrganisation().equalsIgnoreCase(vendor))
//					.filter(p -> p.getPk().getDatetime().equals(date))
//					.filter(p -> p.getProperties().containsKey(kpi))
//					.filter(p -> p.getProperties().get(kpi) != null)
//					.filter(p -> !p.getProperties().get(kpi).isEmpty())
//					.filter(p -> !p.getProperties().get(kpi).equalsIgnoreCase("--"))
					// .collect(Collectors.toList())
					.mapToDouble(p -> Double.parseDouble(p.getProperties().get(kpi)))
					.average()
					.getAsDouble();
			
			Kpi kpiObj = new Kpi();
			
			kpiObj.setKpiType("SGSN");
			kpiObj.setTech("4G");
			kpiObj.setValue(average);
			kpiObj.setDatetime(dateFE);
			
			listResponse.add(kpiObj);
			response.setKpis(listResponse);
			

			System.out.println("average: " + average);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		System.out.println("nodes: " + nodes.size());

		return response;
	}

	@Override
	public KpisDTO getBottomKpisByTech(String vendor, String kpi) {
		Set<Kpi> listResponse = new HashSet<>();
		KpisDTO response = new KpisDTO();
		List<DataSourceEntity> nodes = new ArrayList<>();

		String string = "2017-12-18 19:00:00";
		try {
			Date date = TimeManager.dateFormatDb.parse(string);
			dataRepository.findAll().forEach(nodes::add);

			System.out.println("allnodesBottom: " + nodes.size());
			
			nodes.forEach(node -> {
				
				node.getProperties().forEach((p1,p2)->{
					Double kilobits = 0.0;
					Double megabits = 0.0;
					
					if(p2.contains("K")){
						if(p1.equalsIgnoreCase("Inbound Packet Rate(pkt/s)") || p1.equalsIgnoreCase("Outbound Packet Rate(pkt/s)")){
							kilobits = Double.valueOf(p2.substring(0, p2.length()-1));
							kilobits = kilobits * 1000;
							
							node.getProperties().put(p1, kilobits.toString());
						}else{
							
							kilobits = Double.valueOf(p2.substring(0, p2.length()-1));
							megabits = (kilobits * 1000) / 1000000;
							
							node.getProperties().put(p1, megabits.toString());
						}
					}else if(p2.contains("M")){
						
						node.getProperties().put(p1, p2.substring(0, p2.length()-1));
						
					}
					
				});
			});

			nodes = nodes.stream().filter(p -> p.getPk().getOrganisation().equalsIgnoreCase(vendor))
					// .filter(p -> p.getPk().getDatetime().equals(date))
					.filter(p -> p.getProperties().containsKey(kpi))
					.filter(p -> p.getProperties().get(kpi) != null)
					.filter(p -> !p.getProperties().get(kpi).isEmpty())
					.sorted((p1, p2) -> Double.compare(Double.parseDouble(p1.getProperties().get(kpi)),
							Double.parseDouble(p2.getProperties().get(kpi))))
					.collect(Collectors.toList());

			nodes = nodes.subList(0, 10);

			nodes.forEach(node -> {
				Kpi kpiObj = new Kpi();
				kpiObj.setNode(node.getPk().getNode());
				kpiObj.setCell(node.getPk().getMoid());
				kpiObj.setRegion("R9");
				kpiObj.setTech("4G");
				kpiObj.setKpiType("SGSN");
				kpiObj.setDatetime(TimeManager.dateFormatDb.format(node.getPk().getDatetime()));
				kpiObj.setValue(Double.valueOf(node.getProperties().get(kpi)));
//				kpi.setParameters(node.getProperties());

				listResponse.add(kpiObj);
			});

			response.setKpis(listResponse);
			response.setTotalElements(listResponse.size());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}

	@Override
	public KpisDTO getTopKpisByTech(String vendor, String kpi) {
		Set<Kpi> listResponse = new HashSet<>();
		KpisDTO response = new KpisDTO();
		List<DataSourceEntity> nodes = new ArrayList<>();

		String string = "2017-12-18 19:00:00";
		try {
			Date date = TimeManager.dateFormatDb.parse(string);
			dataRepository.findAll().forEach(nodes::add);

			System.out.println("allnodesBottom: " + nodes.size());
			
			nodes.forEach(node -> {
				
				node.getProperties().forEach((p1,p2)->{
					
					Double kilobits = 0.0;
					Double megabits = 0.0;
					
					if(p2.contains("K")){
						
						if(p1.equalsIgnoreCase("Inbound Packet Rate(pkt/s)") || p1.equalsIgnoreCase("Outbound Packet Rate(pkt/s)")){
							kilobits = Double.valueOf(p2.substring(0, p2.length()-1));
							kilobits = kilobits * 1000;
							
							node.getProperties().put(p1, kilobits.toString());
						}else{
							
							kilobits = Double.valueOf(p2.substring(0, p2.length()-1));
							megabits = (kilobits * 1000) / 1000000;
							
							node.getProperties().put(p1, megabits.toString());
						}
					}else if(p2.contains("M")){
						
						node.getProperties().put(p1, p2.substring(0, p2.length()-1));
						
					}
					
				});
			});

			nodes = nodes.stream()
					.filter(p -> p.getPk().getOrganisation().equalsIgnoreCase(vendor))
					// .filter(p -> p.getPk().getDatetime().equals(date))
					.filter(p -> p.getProperties().containsKey(kpi))
					.filter(p -> p.getProperties().get(kpi) != null)
					.filter(p -> !p.getProperties().get(kpi).isEmpty())
					.sorted((p1, p2) -> Double.compare(Double.parseDouble(p1.getProperties().get(kpi)),
							Double.parseDouble(p2.getProperties().get(kpi))))
					.collect(Collectors.toList());

			nodes = nodes.subList(nodes.size() - 11, nodes.size() - 1);

			nodes.forEach(node -> {
				Kpi kpiObj = new Kpi();
				kpiObj.setNode(node.getPk().getNode());
				kpiObj.setCell(node.getPk().getMoid());
				kpiObj.setRegion("R9");
				kpiObj.setTech("4G");
				kpiObj.setKpiType("SGSN");
				kpiObj.setDatetime(TimeManager.dateFormatDb.format(node.getPk().getDatetime()));
				kpiObj.setValue(Double.valueOf(node.getProperties().get(kpi)));
//				kpi.setParameters(node.getProperties());

				listResponse.add(kpiObj);
			});

			response.setKpis(listResponse);
			response.setTotalElements(listResponse.size());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}

	@Override
	public KpisDTO getLinks(String type, String vendor) {
		Set<Kpi> listResponse = new HashSet<>();
		KpisDTO response = new KpisDTO();
		List<DataSourceEntity> nodes = new ArrayList<>();
		
		try {
			dataRepository.findAll().forEach(nodes::add);
			
			nodes = nodes.stream()
					.filter(p -> p.getPk().getOrganisation().equalsIgnoreCase(vendor))
					.filter(MapUtil.distinctByKey(p -> p.getPk().getNode()))
					.collect(Collectors.toList());
			
			nodes.forEach(node -> {
				Kpi kpi = new Kpi();
				
				kpi.setNode(node.getPk().getNode());
				kpi.setRegion("R9");
				kpi.setTech("4G");
				kpi.setKpiType("SGSN");

				listResponse.add(kpi);
			});

			response.setKpis(listResponse);
			response.setTotalElements(listResponse.size());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public KpisDTO getCells(String vendor, String node) {
		Set<Kpi> listResponse = new HashSet<>();
		KpisDTO response = new KpisDTO();
		List<DataSourceEntity> cells = new ArrayList<>();
		int minus = 0;
									
		while (minus < 14400 && (cells == null || cells.size() == 0)) {

			System.out.println(minus);
			cells = dataRepository.findCells(vendor, TimeManager.getLatest(minus), node);
			 System.out.println(cells);
			minus = minus + 15;
			
			System.out.println(TimeManager.getLatest(minus));
		}
		
		cells.forEach(c->{
			if(!node.equalsIgnoreCase(c.getPk().getMoid())){
				Kpi kpi = new Kpi();
				kpi.setCell(c.getPk().getMoid());

				listResponse.add(kpi);
			}
		});
		
		response.setKpis(listResponse);
		response.setTotalElements(listResponse.size());
		
		return response;
	}
}
