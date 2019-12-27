package com.erun.lzq.bean;

public class BS4_Prog{
	
	private String ProgCode;
	private String ProgName;
	private String JSPFile;
	private String ProgType;
	
	public String getProgCode() {
		return ProgCode;
	}
	public void setProgCode(String progCode) {
		ProgCode = progCode;
	}
	public String getProgName() {
		return ProgName;
	}
	public void setProgName(String progName) {
		ProgName = progName;
	}
	public String getJSPFile() {
		return JSPFile;
	}
	public void setJSPFile(String jSPFile) {
		JSPFile = jSPFile;
	}
	public String getProgType() {
		return ProgType;
	}
	public void setProgType(String progType) {
		ProgType = progType;
	}
	
	@Override
	public String toString() {
		return "BS4_Prog [ProgCode=" + ProgCode + ", ProgName=" + ProgName + ", JSPFile=" + JSPFile + ", ProgType="
				+ ProgType + "]";
	}
	
}