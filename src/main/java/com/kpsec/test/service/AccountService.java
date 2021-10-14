package com.kpsec.test.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kpsec.test.model.AccountResult;
import com.kpsec.test.model.BrInfoResult;
import com.kpsec.test.model.YearlyBrHistResult;
import com.kpsec.test.model.YearlyMaxResult;
import com.kpsec.test.model.entity.BrInfo;
import com.kpsec.test.repository.AccountRepository;
import com.kpsec.test.repository.BrInfoRepository;
import com.kpsec.test.repository.TranHistoryRepository;

@Service
@Transactional
public class AccountService {

	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private BrInfoRepository brInfoRepository;
	
	@Autowired
	private TranHistoryRepository tranHistoryRepository;
	
	public List<AccountResult> getAccountByBranchCode(String branchCode){
		List<AccountResult> aa = accountRepository.getAccountByBranchCode(branchCode);
		return aa;
	}
	
	/**
	 * 2018년, 2019년 각 연도별 합계 금액이 가장 많은 고객을 추출하는 API 개발
	 * return [ 	{“year”:2018, name”:”계좌명”,“acctNo”:”계좌번호”,“sumAmt”:0000},{“year”:2019, “name”:”계좌명”, “acctNo”:”계좌번호”, “sumAmt”:0000}]
	 */
	public List<YearlyMaxResult> accountTransaction () {
		
		List<YearlyMaxResult> result = new ArrayList<YearlyMaxResult>();
		
		String[] strArr = {"2018","2019"};
		
		for ( String str : strArr ) {
			result.add(tranHistoryRepository.getYearlyMaxData(str));
		}
		
		return result;
	}
	
	/**
	 * 2018년 또는 2019년에 거래가 없는 고객을 추출하는 API 개발. (취소여부가 ‘Y’ 거래는 취소된 거래임)
	 * [	{“year”:2018,“name”:”계좌명”,“acctNo”:”계좌번호”}	…	{“year”:2019,“name”:”계좌명”,	“acctNo”:”계좌번호”}…	]
	 */
	public List<YearlyMaxResult> accountNoTransaction() {
		
		List<YearlyMaxResult> result = new ArrayList<YearlyMaxResult>();
		String[] strArr = {"2018","2019"};
		
		for ( String str : strArr ) {
			result.addAll(tranHistoryRepository.getNoTransactionClient(str));
		}
		
		return result;
		
	}
	
	/**
	 * 연도별 관리점별 거래금액 합계를 구하고 합계금액이 큰 순서로 출력하는 API 개발 (취소여부가 ‘Y’ 거래는 취소된 거래임)
	 * [ {“year”: 2018, “dataList”:[ {“brName”: “관리점명”,“brCode”: “관리점코드”,“sumAmt”: 0000 },… ]},{“year”: 2019, “dataList”:[{“brName”: “관리점명”,“brCode”: “관리점코드”,“sumAmt”: 0000 },…]}]
	 */
	public List<YearlyBrHistResult> brTransactionList() {
		
		List<YearlyBrHistResult> result = new ArrayList<YearlyBrHistResult>();
		YearlyBrHistResult brResult = null;
		String[] strArr = {"2018","2019"};
		
		for ( String str : strArr ) {
			
			brResult = new YearlyBrHistResult();
			brResult.setData(str, brInfoRepository.yearlySumList(str));
			result.add(brResult);
			
		}
		
		return result;
		
	}
	
	
	
	/**
	 * 분당점과 판교점을 통폐합하여 판교점으로 관리점 이관을 하였습니다. 지점명을 입력하면 해당지점의 거래금액 합계를 출력하는 API 개발( 취소여부가 ‘Y’ 거래는 취소된 거래임,)
	 */
	public BrInfoResult brTransaction (BrInfo brInfo) {
		
		return brInfoRepository.brSumInfo(brInfo.getBrName());
		
	}
	
	
}
