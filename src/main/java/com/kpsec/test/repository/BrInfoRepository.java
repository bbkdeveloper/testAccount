package com.kpsec.test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kpsec.test.model.BrInfoResult;
import com.kpsec.test.model.entity.BrInfo;

public interface BrInfoRepository extends JpaRepository<BrInfo, String> {

    @Query(value = "SELECT b.branch_code, c.br_name, sum(a.total) as sumAmt FROM "
    		+ "( SELECT account_no, sum(amount) - sum(fee) as total FROM tran_history WHERE  cancel_yn='N' AND transaction_date >= :year||'0000' AND transaction_date <= :year||'1231' GROUP BY account_no) a "
    		+ "INNER JOIN account b ON a.account_no = b.account_no "
    		+ "INNER JOIN br_info c ON b.branch_code = c.br_code "
    		+ "GROUP BY b.branch_code ORDER BY sumAmt desc"
    		, nativeQuery = true)
    List<BrInfoResult> yearlySumList(@Param("year") String year);
    
    
    @Query(value = "SELECT b.branch_code as brCode, c.br_name as brName, sum(a.total) as sumAmt FROM "
    		+ "(SELECT account_no, sum(amount) - sum(fee) as total FROM tran_history WHERE cancel_yn='N' GROUP BY account_no) a "
    		+ "INNER JOIN account b ON a.account_no = b.account_no "
    		+ "INNER JOIN br_info c ON b.branch_code = c.br_code "
    		+ "WHERE c.br_name = :brName GROUP BY b.branch_code "
    		, nativeQuery = true)
    BrInfoResult brSumInfo(@Param("brName") String brName);

}
