package com.kpsec.test.infra;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.kpsec.test.model.entity.Account;
import com.kpsec.test.model.entity.BrInfo;
import com.kpsec.test.model.entity.TranHistory;
import com.kpsec.test.repository.AccountRepository;
import com.kpsec.test.repository.BrInfoRepository;
import com.kpsec.test.repository.TranHistoryRepository;

@Component
public class InitData {

    @Autowired
    AccountRepository accountRepository;
    
    @Autowired
    BrInfoRepository brInfoRepository;
    
    @Autowired
    TranHistoryRepository tranHistoryRepository;
    
    @PostConstruct
    private void initAccount() throws IOException {
    	
        if (accountRepository.count() == 0) {
            Resource resource = new ClassPathResource("계좌정보.csv");
        			
            List<Account> accountList = Files.readAllLines(resource.getFile().toPath(), StandardCharsets.UTF_8)
                    .stream().skip(1).map(line -> {
                        String[] split = line.split(",");
                        return Account.builder().accountNo(split[0]).accountName(split[1]).branchCode(split[2])
                                .build();
                    }).collect(Collectors.toList());
            accountRepository.saveAll(accountList);
        }
        
        if (brInfoRepository.count() == 0) {
        	
    		Resource resource = new ClassPathResource("데이터_관리점정보.csv");
    		
    		List<BrInfo> brList = Files.readAllLines(resource.getFile().toPath(), StandardCharsets.UTF_8).stream().skip(1).map(line -> {
    			String[] split = line.split(",");
    			return BrInfo.builder().brCode(split[0]).brName(split[1]).build();
    		}).collect(Collectors.toList());
    		brInfoRepository.saveAll(brList);
        }
        
        if (tranHistoryRepository.count() == 0) {
        	
    		Resource resource = new ClassPathResource("데이터_거래내역.csv");
    		
    		List<TranHistory> tranList = Files.readAllLines(resource.getFile().toPath(), StandardCharsets.UTF_8).stream().skip(1).map(line -> {
    			String[] split = line.split(",");
    			return TranHistory.builder().transactionDate(split[0]).accountNo(split[1]).transactionNo(split[2]).amount(Integer.parseInt(split[3]))
    					.fee(Integer.parseInt(split[4])).cancelYn(split[5]).build();
    		}).collect(Collectors.toList());
    		tranHistoryRepository.saveAll(tranList);
        }
    }
    
}
