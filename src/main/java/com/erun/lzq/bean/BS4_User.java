package com.erun.lzq.bean;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class BS4_User {
    private String usercode;

    private String username;

    private String password;

    private String empcode;

    private String deptcode;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date lastonlinetime;

    private String replacer;

    private String mobilephone;

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode){
        this.usercode = usercode == null ? null : usercode.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getEmpcode() {
        return empcode;
    }

    public void setEmpcode(String empcode) {
        this.empcode = empcode == null ? null : empcode.trim();
    }

    public String getDeptcode() {
        return deptcode;
    }

    public void setDeptcode(String deptcode) {
        this.deptcode = deptcode == null ? null : deptcode.trim();
    }

    public Date getLastonlinetime() {
        return lastonlinetime;
    }

    public void setLastonlinetime(Date lastonlinetime) {
        this.lastonlinetime = lastonlinetime;
    }

    public String getReplacer() {
        return replacer;
    }

    public void setReplacer(String replacer) {
        this.replacer = replacer == null ? null : replacer.trim();
    }

    public String getMobilephone() {
        return mobilephone;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone == null ? null : mobilephone.trim();
    }

	@Override
	public String toString() {
		return "BS4_User [usercode=" + usercode + ", username=" + username + ", password=" + password + ", empcode="
				+ empcode + ", deptcode=" + deptcode + ", lastonlinetime=" + lastonlinetime + ", replacer=" + replacer
				+ ", mobilephone=" + mobilephone + "]";
	}
    
    
    
}