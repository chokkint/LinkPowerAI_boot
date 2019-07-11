function getContextPath() {
    var pathName = document.location.pathname;
    var index = pathName.substr(1).indexOf("/");
    var result = pathName.substr(0,index+1);
    return result;
}


window.ConstantNew = {
	MENU_ROOT:"MENU_ROOT",
	MENU_VATG:"MENU_VATG",
	SPLIT_STR:"@@@@",
	ERROR_CODE_OBJECT:{
		"ERROR_CODE_ERROR" : "ERROR",
		"ERROR_CODE_EXISTED" : "EXISTED",
		"ERROR_CODE_SUCCESS" : "SUCCESS",
		"ERROR_CODE_HAS_CONFIRMED" : "CONFIRMED",
		"ERROR_CODE_OVER_LENGTH" : "OVER_LENGTH",
		"ERROR_CODE_BLEND" : "BLEND",
		"ERROR_CODE_SYMBOL" : "SYMBOL",
		"ERROR_CODE_NO_ACCESS" : "NO_ACCESS",
		"ERROR_CODE_SESSION_TIMEOUT" : "SESSION_TIMEOUT",
		"ERROR_CODE_UNKNOWN_EXCEPTIONS" : "UNKNOWN_EXCEPTIONS"
	},
	PAGE_KEY:"PAGE",
	DATASET_KEY:"DATASET",
	ROWS_KEY:"rows",
	ERROR_CODE_KEY:"ERROR_CODE",
	ERROR_MESSAGE_KEY:"ERROR_MESSAGE",
	BIZ_CODE_OBJECT:{
		BUSINESS_CODE_V001:"V001",
		BUSINESS_CODE_V002:"V002",
		BUSINESS_CODE_V003:"V003",
		BUSINESS_CODE_V004:"V004",
		BUSINESS_CODE_V005:"V005",
		BUSINESS_CODE_V006:"V006",
		BUSINESS_CODE_V007:"V007"
	},
	BIZ_NAME_OBJECT:{
		"V001":"客户信息补录",
		"V002":"交易流水客户号补录",
		"V003":"负值交易流水补录",
		"V004":"PDTF交易流水补录",
		"V005":"手工开票补录",
		"V006":"自取客户信息补录",
		"V007":"普票客户信息补录"
	},
	REC_STATUS_OBJECT:{
		"1":"未补录",
		"2":"待审核",
		"3":"已审核",
		"4":"审核退回"
	}
}