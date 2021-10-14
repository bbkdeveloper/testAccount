package com.kpsec.test.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface YearlyMaxResult {
    String getYear();
    String getName();
    String getAcctNo();
    @JsonIgnore
    String getSumAmt();
}
