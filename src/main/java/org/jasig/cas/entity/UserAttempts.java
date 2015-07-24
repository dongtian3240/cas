package org.jasig.cas.entity;

import java.util.Date;

/**
 * 用户登录失败次数
 * @author dongtian
 * @date   2015年5月26日 上午10:27:01
 */
public class UserAttempts {

	private Integer id;
	private String userName;
	private int attempts;
	private Date lastUpdateTime;
	
	//最大失败尝试次数
	public  static final int MAX_ATTEMPTS = 5;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getAttempts() {
		return attempts;
	}
	public void setAttempts(int attempts) {
		this.attempts = attempts;
	}
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	@Override
	public String toString() {
		return "UserAttempts [id=" + id + ", userName=" + userName
				+ ", attempts=" + attempts + ", lastUpdateTime="
				+ lastUpdateTime + "]";
	}
	
	
	
}
