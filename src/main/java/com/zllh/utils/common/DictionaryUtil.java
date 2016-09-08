package com.zllh.utils.common;

public class DictionaryUtil {
	
	
	//商品启用
	public static final Integer GOOD_STATUS_USING = 0;
	//商品禁用
	public static final Integer GOOD_STATUS_FORBID = 1;
	// 群组状态量---已开启
	public static final Integer GROUP_STATUS_OPEN = 1;
	// 群组状态量---已关闭
	public static final Integer GROUP_STATUS_CLOSE = 0;
	// 群成员和组之间关系状态量--申请中
	public static final Integer RELA_MMB_GROUP_SQZ = 1;
	// 群成员和组之间关系状态量--已加入
	public static final Integer RELA_MMB_GROUP_YJR = 2;
	// 群组与群组之间关系状态量--买
	public static final Integer RELA_GROUP_GROUP_BUY = 1;
	// 群组与群组之间关系状态量--卖
	public static final Integer RELA_GROUP_GROUP_SELL = 2;
	// 群组与群组之间关系状态量--借
	public static final Integer RELA_GROUP_GROUP_BORROW = 3;
	// 群组与群组之间关系状态量--贷
	public static final Integer RELA_GROUP_GROUP_LOAN = 4;
	// 群组与群组之间关系查询--正向
	public static final Integer RELA_GROUP_GROUP_QUERY_ZX = 0;
	// 群组与群组之间关系查询--反向
	public static final Integer RELA_GROUP_GROUP_QUERY_FX = 1;
	
	//用户群组关系
	public static final Integer GROUP_USER_OPEN  = 1;
	public static final Integer GROUP_USER_CLOSE  = 0;
	// 操作员状态量,已启用
	public static final Integer USER_YQY = 1;
	// 操作员状态量,已停用
	public static final Integer USER_YTY = 0;

	// 报价状态量---在使用
	public static final Integer QUOTE_STATUS_USING = 0;
	// 报价状态量---已禁止
	public static final Integer QUOTE_STATUS_FORBID = 1;
	// 报价状态量---已过期
	public static final Integer QUOTE_STATUS_YGQ = 2;
	// 报价类型状态量---销售
	public static final Integer GROUP_TYPE_SELL = 1;
	// 报价类型状态量---采购
	public static final Integer GROUP_TYPE_BUY = 0;
	// 报价关系类型--地域
	public static final Integer RELA_QUOTE_TYPE_AREA = 1;
	// 报价关系类型--群组
	public static final Integer RELA_QUOTE_TYPE_GROUP = 3;
	// 报价关系类型--合作会员
	public static final Integer RELA_QUOTE_TYPE_OMMB = 2;
	//报价范围关系--公开
	public static final Integer RELA_QUOTE_RANGTYPE_PUB = 0;
	//报价范围关系--指定
	public static final Integer RELA_QUOTE_RANGTYPE_PRI = 1;
	// 会员状态--启用
	public static final Integer MMB_STATUS_USING = 0;
	// 会员状态--禁用
	public static final Integer MMB_STATUS_FORBID = 1;

	// 会员关系传入字符串买卖关系
	public static final String MMB_RELATIONSHIP_BUY = "0";
	public static final String MMB_RELATIONSHIP_SELL = "1";

	// 会员关系审核状态--正常
	public static final Integer MMB_RELATIONSHIP_STATUS_NOMAL = 0;
	// 待审核
	public static final Integer MMB_RELATIONSHIP_STATUS_FAIL = 1;

	// 会员关系类型
	public static final Integer MMB_RELATIONSHIP_TYPE_ATTENTION = 0;
	public static final Integer MMB_RELATIONSHIP_TYPE_OPRATION = 1;

	// 会员关系审批状态
	public static final String MMB_RELATIONSHIP_STATUS_NOMAL_ = "0";
	public static final String MMB_RELATIONSHIP_STATUS_FAIL_ = "1";

	// 会员业务类型--买卖
	//public static final Integer MMB_BIZ_TYPE_BUY = 0;
	public static final Integer MMB_BIZ_TYPE_BUY = 1002;
	// 借贷
	public static final Integer MMB_BIZ_TYPE_BORROW = 1;
	
	//协议结款规则
	public static final Integer CONTRACT_CLEAR_MONEY_TYPE_MONTH = 0; //月结
	public static final Integer CONTRACT_CLEAR_MONEY_TYPE_QUARTER = 1; //季度结
	public static final Integer CONTRACT_CLEAR_MONEY_TYPE_SIXM = 2; //六个月
	public static final Integer CONTRACT_CLEAR_MONEY_TYPE_YEAR = 3; //年结
	public static final Integer CONTRACT_CLEAR_MONEY_TYPE_OTHER = 4; //其他
	//合作协议流程状态
	public static final Integer CONTRACT_BOTH_NO_CONFIRM = 0; //双方未定
	public static final Integer CONTRACT_BUYER_CONFIRM = 1; //买方确认
	public static final Integer CONTRACT_SELLER_CONFIRM = 2; //卖方确认
	public static final Integer CONTRACT_BOTH_CONFIRM = 3; //双方确认

	public static final Integer CONTRACT_CANCELLED = 4; //作废
	public static final Integer CONTRACT_BUYER_TERMINATE = 5; //买方终止确认
	public static final Integer CONTRACT_SELLER_TERMINATE = 6; //卖方终止确认
	public static final Integer CONTRACT_BOTH_TERMINATE = 7; //双方终止确认
	//平台超级管理员Id
	public static final String MMB_BASE_ID = "0";
	//商城搜索类型
	public static final String SHOP_QUOTE_AREA = "0";//
	public static final String SHOP_QUOTE_ACTIOM = "1";
	public static final String SHOP_QUOTE_MMB = "2";
	public static final String SHOP_QUOTE_GROUP= "3";
	//报表类型
	public static final String REPORT_BUYER = "3";
	public static final String REPORT_SELLER = "4";
	public static final String REPORT_GOODS = "5";
}
