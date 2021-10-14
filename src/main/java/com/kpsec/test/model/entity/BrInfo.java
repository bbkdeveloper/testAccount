package com.kpsec.test.model.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BrInfo {
	
    @Id
    private String brName;
    
    @ApiModelProperty(hidden = true)
    private String brCode;
    
	public BrInfo(String string) {
	}

}
