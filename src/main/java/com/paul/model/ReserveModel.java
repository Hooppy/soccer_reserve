package com.paul.model;

import java.util.Date;

import org.springframework.stereotype.Repository;

@Repository
public class ReserveModel{
	int idx;
	String code;
	String time;
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
}
