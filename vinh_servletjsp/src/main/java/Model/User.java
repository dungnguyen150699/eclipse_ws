package Model;

import java.io.Serializable;
import java.sql.Date;

public class User implements Serializable{
	public int ID;
	public String NAME,BIRTHPLACE;
	public Date BIRTHDAY;
	
	public User() {}
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String nAME) {
		NAME = nAME;
	}
	public String getBIRTHPLACE() {
		return BIRTHPLACE;
	}
	public void setBIRTHPLACE(String bIRTHPLACE) {
		BIRTHPLACE = bIRTHPLACE;
	}
	public Date getBIRTHDAY() {
		return BIRTHDAY;
	}
	public void setBIRTHDAY(Date bIRTHDAY) {
		BIRTHDAY = bIRTHDAY;
	}
	
	
}
