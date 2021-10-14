package com.kpsec.test.infra;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.kpsec.test.model.BrInfoResult;
import com.kpsec.test.model.YearlyBrHistResult;
import com.kpsec.test.model.YearlyMaxResult;
import com.kpsec.test.model.entity.Account;
import com.kpsec.test.model.entity.BrInfo;
import com.kpsec.test.repository.BrInfoRepository;
import com.kpsec.test.repository.TranHistoryRepository;
import com.kpsec.test.service.AccountService;

@DataJpaTest
class InitDataTest {
	
	@Autowired
	private TranHistoryRepository tranHistoryRepository;
	
	@Autowired
	private BrInfoRepository brInfoRepository;
	
	@Test
	void testAccount() throws Exception {
		
		Resource resource = new ClassPathResource("계좌정보.csv");
		
		Files.readAllLines(resource.getFile().toPath(), StandardCharsets.UTF_8).stream().skip(1).forEach(System.out::println);
		System.out.println("**************************************************");
		List<Account> accountList = Files.readAllLines(resource.getFile().toPath(), StandardCharsets.UTF_8).stream().skip(1).map(line -> {
			String[] split = line.split(",");
			return Account.builder().accountNo(split[0]).accountName(split[1]).branchCode(split[2]).build();
		}).collect(Collectors.toList());
			
		
		if ( accountList.size() > 0 )
			accountList.forEach(System.out::println);
		
	}
	
	@Test
	void accountTransaction() throws Exception {
		
		String[] strArr = {"2018","2019"};
		
		for ( String str : strArr ) {
			tranHistoryRepository.getYearlyMaxData(str);
		}
		
	}
	
	@Test
	void accountNoTransaction() throws Exception {
		
		String[] strArr = {"2018","2019"};
		
		for ( String str : strArr ) {
			tranHistoryRepository.getNoTransactionClient(str);
		}
		
	}
	
	@Test
	void brTransactionList() throws Exception {
		
		String[] strArr = {"2018","2019"};
		
		for ( String str : strArr ) {
			brInfoRepository.yearlySumList(str);
		}
		
	}
	
	@Test
	void brTransaction() throws Exception {
		
		BrInfo brInfo = new BrInfo("판교점");
		brInfoRepository.brSumInfo(brInfo.getBrName());
		
	}
	
	
}
