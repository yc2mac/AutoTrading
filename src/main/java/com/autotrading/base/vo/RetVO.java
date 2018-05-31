package com.autotrading.base.vo;

import lombok.Data;

/*
 * @author : YC
 * @class  :
 * @email  : 37933599@qq.com
 * @createDate :2018/5/2 下午8:26
 * @modifyDate :2018/5/2
 * @annocation : 返回类 标准定义
 */
public @Data class RetVO<T> {

    /**返回错误代码  0交易成功 1交易失败 2 部分失败*/
    private String status;

    /**错误信息 当 status 为0时 message为空*/
    private String message;

    /**自己定义的返回内容 此处必须是data*/
    private T data;

    @Override
    public String toString() {
        return "RetVO{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
