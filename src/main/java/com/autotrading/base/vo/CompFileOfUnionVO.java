package com.autotrading.base.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 银联对账文件
 */
@Data
public class CompFileOfUnionVO {
    private String cupsNo;      //受理侧交易流程号CHAR(16)
    private String srcNo;      //银联前置流水号	CHAR(24)
    private String sacquirer;         //渠道方机构码	CHAR(8)
    private String src;           //发起方代码	CHAR(8)
    private Date transDt;        //交易发生日期	CHAR(8)	YYYYMMDD
    private Timestamp transTm;        //交易发生时间	CHAR(6)	HHMMSS
    private String pan;        //交易卡号	CHAR(34)
    private BigDecimal purchAmt;     //交易金额	CHAR(12)	12n
    private String cdFlg;      //借贷记标志	CHAR(1)	D：借记  C：贷记
    private String currency;      //交易货币代码	CHAR(3)	156：人民币
    private Date settleDt;    //清算日期	YYYYMMDD


}
