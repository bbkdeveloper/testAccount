package com.kpsec.test.model.entity;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import com.kpsec.test.model.entity.TranHistory.TranHistoryKey;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@IdClass(TranHistoryKey.class)
public class TranHistory {

	@Id
    private String transactionDate;
    
	@Id
    private String accountNo;
	
    private String transactionNo;
    
    private int amount;
    
    private int fee;
    
    private String cancelYn;
    
	@EqualsAndHashCode
    @Embeddable
    static class TranHistoryKey implements Serializable {

        @Column(columnDefinition = "varchar(8)", name = "TRANSACTION_DATE", nullable = false)
        private String transactionDate;

        @Column(name = "ACCOUNT_NO", nullable = false)
        private String accountNo;
        
    }

}
