package com.hehanpeng.framework.cloudhhp.module.batch.domain.entity.batch;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@Table(name = "BATCH_STEP_EXECUTION_SEQ")
public class BatchStepExecutionSeq {
    @Column(name = "ID")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    @Column(name = "UNIQUE_KEY")
    private String uniqueKey;
}