package com.intelmas.controller.elasticsearch.dto;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.elasticsearch.search.SearchHit;
import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.intelmas.controller.dto.Kpi;
import com.intelmas.controller.dto.Threshold;
import com.intelmas.dto.model.NodeTopologyEntity;
import com.intelmas.utils.Constants;


//@Document( indexName = "kpis" , type = "generic")
public class KpiDocument {

	@Id
    private String id;
	private String organisation;
	private String region;
	private String oss;
	private String node;
	private String tech;
	private String nodeType;
	private String nodeVersion;
	private String cell;
	
	private String kpiType;
	
	
	@JsonFormat (shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd'T'HH:mm:ss.SSSZZ")
	private Timestamp datetime;
	
	
	private String name;
	private Double value;
	
	private KpiParameter[] parameters;
	
	private Boolean isStandard;
	
	private String criticalType;
	private Double criticalValue;
	private String majorType;
	private Double majorValue;
	private String minorType;
	private Double minorValue;
	private String normalType;
	private Double normalValue;
	
	public KpiDocument(){
	}
	
	private DateTimeFormatter dbDateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssZ");
	private DateTimeFormatter localDateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZZ");
	
	public KpiDocument(SearchHit searchHit){
		if(searchHit == null) return;
		Map<String, Object> sourceMap = searchHit.getSourceAsMap();
		if(sourceMap == null) return;
		
		Double value = Double.NaN;
		try{  value = (Double)sourceMap.get("value"); 	}catch(Exception e){}
		
		String organisation = "";
		try{	organisation = (String)sourceMap.get("organisation"); 	}catch(Exception e){}
		
		String node = "";
		try{    node = (String)sourceMap.get("node"); 	}catch(Exception e){}
		
		String cell = "";
		try{	cell = (String)sourceMap.get("cell"); 	}catch(Exception e){}
		
		String name = "";
		try{	name = (String)sourceMap.get("name"); 	}catch(Exception e){}
		
		String region = "";
		try{	region = (String)sourceMap.get("region"); 	}catch(Exception e){}
		
		String tech = "";
		try{	tech = (String)sourceMap.get("tech"); 	}catch(Exception e){}
		
		
		List<KpiParameter> kpiParameters = new ArrayList<KpiParameter>();
		try{	
			
			@SuppressWarnings("unchecked")
			List<Map<String, Object>> listKpiParameters = (List<Map<String, Object>>)sourceMap.get("parameters");
			for(Map<String, Object> kpiParameter: listKpiParameters){
				kpiParameters.add(new KpiParameter(kpiParameter.get("name"), kpiParameter.get("value")));
			}
		}catch(Exception e){
		}
		
		Timestamp datetime = null;
		try{	
			
			String localtimestamp = (String)sourceMap.get("datetime");
			localtimestamp = StringUtils.replace(localtimestamp, "T", " ");
			localtimestamp = StringUtils.replace(localtimestamp, ".000", "");
			
			ZonedDateTime zonedDateTime = ZonedDateTime.parse(localtimestamp, dbDateFormatter);
			
			datetime = Timestamp.from(zonedDateTime.toInstant());
			
		}catch(Exception e){
		}
		
		
		this.setId(searchHit.getId());
		this.setOrganisation(organisation);
		this.setNode(node);
		this.setCell(cell);
		this.setRegion(region);
		this.setTech(tech);
		
		this.setValue(value);
		this.setDatetime(datetime);
		
		try{	
			String criticalType = (String)sourceMap.get("criticalType"); 
			Double criticalValue = (Double)sourceMap.get("criticalValue"); 
			this.setCriticalType(criticalType);
			this.setCriticalValue(criticalValue);
		}catch(Exception e){}
		
		try{	
			String majorType = (String)sourceMap.get("majorType"); 
			Double majorValue = (Double)sourceMap.get("majorValue"); 
			this.setMajorType(majorType);
			this.setMajorValue(majorValue);
		}catch(Exception e){}
		
		try{	
			String minorType = (String)sourceMap.get("minorType"); 
			Double minorValue = (Double)sourceMap.get("minorValue"); 
			this.setMinorType(minorType);
			this.setMinorValue(minorValue);
		}catch(Exception e){}
		
		try{	
			String normalType = (String)sourceMap.get("normalType"); 
			Double normalValue = (Double)sourceMap.get("normalValue");
			this.setNormalType(normalType);
			this.setNormalValue(normalValue);
		}catch(Exception e){}

		KpiParameter[] sourceKpiParameters = kpiParameters.toArray(new KpiParameter[kpiParameters.size()]);
		if(kpiParameters != null) this.setParameters(sourceKpiParameters);

		this.setName(name);
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public String getOrganisation() {
		return organisation;
	}

	public void setOrganisation(String organisation) {
		this.organisation = organisation;
	}

	public String getOss() {
		return oss;
	}

	public void setOss(String oss) {
		this.oss = oss;
	}

	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}

	public String getNodeType() {
		return nodeType;
	}

	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}

	public String getCell() {
		return cell;
	}

	public void setCell(String cell) {
		this.cell = cell;
	}

	public Timestamp getDatetime() {
		return datetime;
	}
	
	public String getDatetimeAsString(){
		Instant datetimeInstant = this.datetime.toInstant();
		ZoneId zoneId = Constants.TIMEZONE;
		ZonedDateTime zdt = ZonedDateTime.ofInstant( datetimeInstant , zoneId );
		
		return zdt.format(localDateFormatter);
	}

	public void setDatetime(Timestamp datetime) {
		this.datetime = datetime;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getTech() {
		return tech;
	}

	public void setTech(String tech) {
		this.tech = tech;
	}

	
	public String getNodeVersion() {
		return nodeVersion;
	}

	public void setNodeVersion(String nodeVersion) {
		this.nodeVersion = nodeVersion;
	}
	
	public Double getValue() {
		return value;
	}
	
	public void setValue(Double value) {
		this.value = value;
	}
	
	public String getKpiType() {
		return kpiType;
	}
	
	public void setKpiType(String kpiType) {
		this.kpiType = kpiType;
	}	
	
	public KpiParameter[] getParameters() {
		return parameters;
	}
	
	public void setParameters(KpiParameter[] parameters) {
		this.parameters = parameters;
	}
	
	public Boolean getIsStandard() {
		return isStandard;
	}
	
	public void setIsStandard(Boolean isStandard) {
		this.isStandard = isStandard;
	}
	
	public String getCriticalType() {
		return criticalType;
	}

	public void setCriticalType(String criticalType) {
		this.criticalType = criticalType;
	}

	public Double getCriticalValue() {
		return criticalValue;
	}

	public void setCriticalValue(Double criticalValue) {
		this.criticalValue = criticalValue;
	}

	public String getMajorType() {
		return majorType;
	}

	public void setMajorType(String majorType) {
		this.majorType = majorType;
	}

	public Double getMajorValue() {
		return majorValue;
	}

	public void setMajorValue(Double majorValue) {
		this.majorValue = majorValue;
	}

	public String getMinorType() {
		return minorType;
	}

	public void setMinorType(String minorType) {
		this.minorType = minorType;
	}

	public Double getMinorValue() {
		return minorValue;
	}

	public void setMinorValue(Double minorValue) {
		this.minorValue = minorValue;
	}

	public String getNormalType() {
		return normalType;
	}

	public void setNormalType(String normalType) {
		this.normalType = normalType;
	}

	public Double getNormalValue() {
		return normalValue;
	}

	public void setNormalValue(Double normalValue) {
		this.normalValue = normalValue;
	}

	public void updateFromNodeTopology(NodeTopologyEntity entity){
		if(entity == null) return;
		
		this.setOss(entity.getNode_parent());
		this.setRegion(entity.getNode_region());
		this.setTech(entity.getNode_tech());
		this.setNodeVersion(entity.getNode_version());
	}
	
	public Kpi generateKpi(){
		Kpi kpi = new Kpi();
		
		kpi.setId(this.getId());
		kpi.setOrganisation(this.getOrganisation());
		kpi.setRegion(this.getRegion());
		kpi.setTech(this.getTech());
		kpi.setOss(this.getOss());
		kpi.setNode(this.getNode());
		kpi.setNodeType(this.getNodeType());
		kpi.setCell(this.getCell());
		kpi.setDatetime(this.getDatetime());
		kpi.setName(this.getName());
		kpi.setValue(this.getValue());
		kpi.setKpiType(this.getKpiType());
		//kpi.setParameters(this.getKpi().decodeParameterValue());
		
		kpi.updateParams(this.getParameters());
		kpi.setCritical(new Threshold(this.getCriticalType(), this.getCriticalValue()));
		kpi.setMajor(new Threshold(this.getMajorType(), this.getMajorValue()));
		kpi.setMinor(new Threshold(this.getMinorType(), this.getMinorValue()));
		kpi.setNormal(new Threshold(this.getNormalType(), this.getNormalValue()));
		
		return kpi;
	}
	
}
