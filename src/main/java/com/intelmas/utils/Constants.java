package com.intelmas.utils;

import java.io.IOException;
import java.time.ZoneId;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

/** Class to define static and final variables.
 * @author Intelma
 *
 */
public class Constants {
	
	static Properties props;
	static {
		Resource resource = new ClassPathResource("/application.properties");
		try {
			props = PropertiesLoaderUtils.loadProperties(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static final ZoneId TIMEZONE = ZoneId.systemDefault();
	public static final String HUAWEI = "Huawei";
	public static final String ERICSSON = "Ericsson";
	
	//IP and PORT to access to the backend kpis services
	private static final String KPIS_BACKEND_IP = props.getProperty("kpis.backend.ip");
	private static final String KPIS_BACKEND_PORT = props.getProperty("kpis.backend.port");
	private static final String KPIS_BACKEND_ACCESS = "http://" + KPIS_BACKEND_IP
			+ (KPIS_BACKEND_PORT != null && !KPIS_BACKEND_PORT.isEmpty() ? ":" : "") + KPIS_BACKEND_PORT;

	//paths
	public static final String SOFTALARMS_FILE_PATH = props.getProperty("softalarms.path");
	public static final String NOTIFICATIONS_FILE_PATH = props.getProperty("notifications.path");
	
	//parameters for services
	public static final String PARAM_NODE = "node=";
	public static final String PARAM_CELL = "cell=";
	public static final String PARAM_OSS = "oss=";
	public static final String PARAM_KPI_NAME = "name=";
	public static final String PARAM_RESOLUTION = "resolution=";
	
	//Backend kpis services
	public static final String GETALLKPIFORMULA_SERVICE = KPIS_BACKEND_ACCESS+"/kpiFormulaApi/getAllKpiFormulas";
	public static final String GETKPIFORMULA_SERVICE = KPIS_BACKEND_ACCESS+"/kpiFormulaApi/getKpiFormulasById?id=";
	public static final String GETSOFTDATA_SERVICE = KPIS_BACKEND_ACCESS+"/kpiApi/getSoftAlarmData?name=";
	public static final String GETNOTIFDATA_SERVICE = KPIS_BACKEND_ACCESS+"/kpiApi/getNotificationsData?";
	public static final boolean STATIC = Boolean.parseBoolean(props.getProperty("var.static"));
	
}
