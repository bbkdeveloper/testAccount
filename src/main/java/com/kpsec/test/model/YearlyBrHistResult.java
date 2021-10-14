package com.kpsec.test.model;

import java.util.List;

import lombok.Data;

@Data
public class YearlyBrHistResult {
    
	private String year;
    private List<BrInfoResult> dataList;

    public void setData (String year, List<BrInfoResult> dataList) {
    	this.year = year;
    	this.dataList = dataList;
    }
}
