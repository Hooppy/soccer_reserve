package com.paul.model;

import org.springframework.stereotype.Repository;

@Repository
public class ReserveListModel{
	int rsvr_idx;
	String rsvr_class;
	String rsvr_date;
	String reg_date;
	String time;
	String reg_id;
	
	public int getRsvr_idx() {
		return rsvr_idx;
	}
	public void setRsvr_idx(int rsvr_idx) {
		this.rsvr_idx = rsvr_idx;
	}
	public String getRsvr_class() {
		return rsvr_class;
	}
	public void setRsvr_class(String rsvr_class) {
		this.rsvr_class = rsvr_class;
	}
	public String getRsvr_date() {
		return rsvr_date;
	}
	public void setRsvr_date(String rsvr_date) {
		this.rsvr_date = rsvr_date;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getReg_id() {
		return reg_id;
	}
	public void setReg_id(String reg_id) {
		this.reg_id = reg_id;
	}
}
