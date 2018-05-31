package com.autotrading.base.vo;

import lombok.Data;

/**
 * Created by john on 2017/8/18.
 * 返回类标准写法 说明类
 */
public @Data class ReturnVO<T> {
    private String retCode; //返回错误代码  0交易失败 1交易成功 2 部分失败
    private String retType;  //在交易错误的情况下 1*：1candydog  2box  3bot
    private String retMsg;   //在交易错误情况下 具体错误信息
    private String seriesNo;  // 交易号

    private T retData;   //自己定义的返回内容 此处必须是retData

    @Override
    public String toString() {
        return "ReturnVO{" +
                "retCode='" + retCode + '\'' +
                ", retType='" + retType + '\'' +
                ", retMsg='" + retMsg + '\'' +
                ", seriesNo='" + seriesNo + '\'' +
                ", retData=" + retData +
                '}';
    }

}
