package com.intelmas.dto.model;

import org.springframework.data.cassandra.mapping.CassandraType;
import org.springframework.data.cassandra.mapping.UserDefinedType;

import com.datastax.driver.core.DataType.Name;

/** User Defined Type class of ftp type on DB.
 * @author Intelma
 *
 */
@UserDefinedType("ftp")
public class FTPaccess {

	@CassandraType(type = Name.TEXT)
	private String user;
	@CassandraType(type = Name.TEXT)
	private String pass;
	@CassandraType(type = Name.TEXT)
	private String ip;
	@CassandraType(type = Name.INT)
	private int port;
	@CassandraType(type = Name.TEXT)
	private String path;

	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
}
