package com.kpsec.test.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.kpsec.test.model.AccountResult;
import com.kpsec.test.model.BrInfoResult;
import com.kpsec.test.model.ErrorDTO;
import com.kpsec.test.model.YearlyBrHistResult;
import com.kpsec.test.model.YearlyMaxResult;
import com.kpsec.test.model.entity.BrInfo;
import com.kpsec.test.service.AccountService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Sample")
@RestController
@RequestMapping("/test/")
public class SampleController {
	
	@Autowired
	private AccountService accountService;

	@ApiOperation(value = "sample")
	@GetMapping(value = "/acount")
	public List<AccountResult> getAccountInfo(String branchCode) {
		
		return accountService.getAccountByBranchCode(branchCode);
	}
	
	
	@ApiOperation(value = "거래 고객 조회", notes = "2018년, 2019년 각 연도별 합계 금액이 가장 많은 고객 조회")
	@GetMapping(value = "/account/transaction")
	public List<YearlyMaxResult> accountTransaction() {
		
		return accountService.accountTransaction();
	}
	
	
	@ApiOperation(value = "미거래 고객 조회", notes = "2018년 또는 2019년에 거래가 없는 고객 조회")
	@GetMapping(value = "/account/notransaction")
	public List<YearlyMaxResult> accountNoTransaction() {
		return accountService.accountNoTransaction();
	}
	
	
	@ApiOperation(value = "관리점 목록 조회", notes = "연도별 관리점별 거래금액 합계를 구하고 합계금액이 큰 순서로 조회")
	@GetMapping(value = "/br/transaction/list")
	public List<YearlyBrHistResult> brTransactionList() {
		return accountService.brTransactionList();
	}
	
	@ApiOperation(value = "관리점 정보 조회", notes = "지점명을 입력하면 해당지점의 거래금액 합계 조회")
	@PostMapping(value = "/br/transaction")
//	@ApiImplicitParams({
//		@ApiImplicitParam(name = "brName", value = "지점명", allowableValues = "판교점, 분당점, 강남점, 잠실점, 을지로점",
//							dataType = "string", paramType = "query")	//paramType : 쿼리 선택가능 드롭다운 박스 표출
//	})
	@ApiResponses({
		@ApiResponse(code = 200, message = "OK", response = BrInfoResult.class)
//		, @ApiResponse(code = 404, message = "br code not found error", response = ErrorDTO.class)
		, @ApiResponse(code = 404, message = "br code not found error", response = ErrorDTO.class)
	})
	@ResponseBody
	public BrInfoResult brTransaction(@RequestBody BrInfo brInfo) {
		
		if ( brInfo.getBrName().equals("분당점"))
			throw new NotFoundDataException();
		return accountService.brTransaction(brInfo);
	}
	
	@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="br code not found error") // 404 
	public class NotFoundDataException extends IllegalArgumentException{}

}
