package com.autotrading.base;

/**
 * @author : YJJ
 * @class :cn.lesaas.base.extention.conf.constants.AutoCons
 * @createDate    :2017/10/24
 * @modifyDate :2017/10/24
 * @annocation :常用标志性常量
 */
public  class FlagCons {
    /**
     * 账号状态
     0：销户
     1：正常(默认)
     3：挂失
     4：司法冻结
     */
    public static final String ACCTSTAT_CLOSE="0";
    public static final String ACCTSTAT_NORMAL ="1";
    public static final String ACCTSTAT_LOSS="3";
    public static final String ACCTSTAT_FROZEN="4";

    /**
     * 删除标志
     */

    //删除标志：正常
    public static final String DELFLAGNORMAL="0";
    //删除标志：作废
    public static final String DELFLAGCANCEL="1";

    /**
     * 交易渠道 默认00
     * NORAMAL 自身
     * LDYS  联动优势
     * YL   银联
     * FINA 理财平台
     * TL  通联
     * HF  华付
     */
    public static final String CHANNEL_NORAMAL="00";
    public static final String CHANNEL_LDYS="01";
    public static final String CHANNEL_YL="02";
    public static final String CHANNEL_FINA="03";
    public static final String CHANNEL_TL="04";
    public static final String CHANNEL_HF="05";

    /**冻结信息*/
    //冻结方式 1自动冻结 2 司法冻结
    public static final String FRZMETHOD_AUTO="1";
    public static final String FRZMETHOD_MANU="2";

    //冻结标志 1自动冻结,账户状态视为正常
    public static final String  FRZFLAG_AUTO="1";
    //冻结标志 0：正常（默认值）
    public static final String  FRZFLAG_NORMAL="0";

    //冻结状态 1.冻结 2.解冻 0.撤销
    public static final String FRZSTAS_FRZ="1";
    public static final String FRZSTAS_THAW="2";
    public static final String FRZSTAS_CANCEL="0";

    /**冲销标志**/
    //正常未冲销
    public static final String CANCEL_NORMAL="0";
    
    // 帐户状态 0：销户 1：正常(默认)3：挂失4：支付冻结5：司法冻结
    public static final String ACCT_STAT_0 = "0";
    public static final String ACCT_STAT_NORMAL = "1";
    public static final String ACCT_STAT_3 = "3";
    public static final String ACCT_STAT_4 = "4";
    public static final String ACCT_STAT_5 = "5";
    // 17 定活两便 76表外（理财）
    public static final String DPS_KIND_CHANGE = "17";
    public static final String DPS_KIND_OUT = "76";
    // 账户类别 定期
    public static final String ACCT_TYPE_TERM="4";
    // 账户类型自营性质
    public static final String ACCT_SORT_SELF = "0";

    // 计息标志 0：不计息 1：计息入账 2：计息不入账
    public static final String INT_FLAG_NO = "0";
    public static final String INT_FLAG_IN_LED = "1";
    public static final String INT_FLAG_OUT_LED = "2";

    // 利率期限类型 y.年m.月d.日
    public static final String RATE_UNIT_Y = "y";
    public static final String RATE_UNIT_M = "m";
    public static final String RATE_UNIT_D = "d";

    //帐类 0-表外
    public static final String SUB_KIND_TABLE_OUT = "0";

    //标志 第一位：标准户标志: 1-标准户
    public static final String FLAG_NORM = "1";
    // 余额控制标志 0.不控制  1.控制
    public static final String BAL_CTRL_FLAG_NON = "1";
    // 手工记账控制标志 1-允许  0-不允许
    public static final String ACCCTRL_FLAG_NON = "0";
    //  B-双向 C-贷方 D-借
    public static final String B = "B";
    public static final String C = "C";
    public static final String D = "D";

    // 帐户种类 '3'-内部  '5'-销帐
    public static final String ACCT_KIND_INNER = "3";

    // 参加平衡标志 0不参加平衡1参加平衡
    public static final String BALANCE_FLAG_TURE = "1";
    public static final String BALANCE_FLAG_FALSE = "0";

    // 产品状态 1-发布，2-成立，3-赎回
    public static final String FINA_STATUS_RELEASE = "1";
    public static final String FINA_STATUS_CRATE = "2";
    public static final String FINA_STATUS_CASH = "3";

    // finaType 理财产品类别 1-保本 0-非保本
    public static final String FINA_TYPE_BB="1";
    public static final String FINA_TYPE_FBB="0";

    //交易渠道
    public static final String CHANNEL_SELF="00";

    //平衡标志 0:不平 1平
    public static final String FLAG_ZERO="0";
    public static final String FLAG_ONE="1";
    public static final Integer BALFLAG_ZERO=0;
    public static final Integer BALFLAG_ONE=1;

}
