package com.kpsec.test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kpsec.test.model.YearlyMaxResult;
import com.kpsec.test.model.entity.TranHistory;

public interface TranHistoryRepository extends JpaRepository<TranHistory, String> {

	@Query(value = "SELECT :year as year, a.account_no as acctNo, b.account_name as name, a.total as sumAmt FROM (SELECT account_no, SUM(amount)-SUM(fee) as total FROM tran_history "
			+ "WHERE transaction_date >= :year||'0000' and transaction_date <= :year||'1231' and cancel_yn = 'N'  GROUP BY account_no ORDER BY total desc limit 1 ) a INNER JOIN account b ON a.account_no = b.account_no"
    		, nativeQuery = true)
    YearlyMaxResult getYearlyMaxData(@Param("year") String year);

	@Query(value = "SELECT :year as year, a.account_name as name, a.account_no as acctNo FROM account a LEFT JOIN "
					+ "( SELECT s2.account_no FROM tran_history s1 INNER JOIN account s2 ON s1.account_no =s2.account_no "
					+ "WHERE s1.cancel_yn = 'N' AND s1.transaction_date >= :year||'0000' AND s1.transaction_date <= :year||'1231' "
					+ "GROUP BY s2.account_no ORDER BY s2.account_no ) b "
					+ "ON a.account_no = b.account_no "
					+ "WHERE b.account_no is null "
    		, nativeQuery = true)
	List<YearlyMaxResult> getNoTransactionClient(@Param("year") String year);
	
	
}
