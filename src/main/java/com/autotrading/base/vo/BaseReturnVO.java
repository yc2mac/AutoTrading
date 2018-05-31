package com.autotrading.base.vo;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zrj on 2017/03/16
 * .
 */
public class BaseReturnVO {

    private static final Logger LOG = LoggerFactory.getLogger(BaseReturnVO.class);

    private int retCode=0;   //0 失败  1成功
    private String retMsg;
    /*存管接口调用流水号*/
    private String businessSeqNo;
    /*token*/
    private String token;
    private String tranCode;
    private String tranName;


    public int getRetCode() {
        return retCode;
    }

    public void setRetCode(int retCode) {
        LOG.info("retCode:"+retCode);
        this.retCode = retCode;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        LOG.info("retMsg:"+retMsg);
        this.retMsg = retMsg;
    }

    public String getBusinessSeqNo() {
        return businessSeqNo;
    }

    public void setBusinessSeqNo(String businessSeqNo) {
        this.businessSeqNo = businessSeqNo;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTranCode() {
        return tranCode;
    }

    public void setTranCode(String tranCode) {
        this.tranCode = tranCode;
    }

    public String getTranName() {
        return tranName;
    }

    public void setTranName(String tranName) {
        this.tranName = tranName;
    }
}
