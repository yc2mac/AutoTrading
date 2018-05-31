package com.autotrading.base.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author : YJJ
 * @class :com.candy.base.vo.BaseInfoVO
 * @createDate    :2017/11/13
 * @modifyDate :2017/11/13
 * @annocation :
 */
public @Data class BaseInfoVO {
    private String acctNo;
    private String seqNo;
    private String ccy;
    private Date tranDate;
    private String custNo;
    private String brcNo;
    private String seriesNo;
    private String channel;
    private String tranBrief;

}
