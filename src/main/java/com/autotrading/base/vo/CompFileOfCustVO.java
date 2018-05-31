package com.autotrading.base.vo;

import lombok.Data;

public @Data class CompFileOfCustVO {
    private String brcNo;          //所属机构
    private String custNo;	//客户号
    private String acctNo;	//账号
    private String beginDate;	//开户日期
    private String endDate;    //	销户日期


}
