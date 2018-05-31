package com.autotrading.base.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

public @Data class CompFileOfAssetVO {
    private String seqBus;        //业务流水号
    private String seqNo;          //交易流水号
    private String brcNo;          //所属机构
    private String oppAccNo;        //对方账号
    private BigDecimal amt;     //交易金额
    private String ccy;         //币种
    private Date tranDate;             //记帐日期


}
