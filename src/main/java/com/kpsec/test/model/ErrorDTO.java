package com.kpsec.test.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@ApiModel(description = "에러 상태")
public class ErrorDTO {
	
	@ApiModelProperty(example = "404")
	private String code;
	
	@ApiModelProperty(example = "br code not found error")
	private String message;
	
}
