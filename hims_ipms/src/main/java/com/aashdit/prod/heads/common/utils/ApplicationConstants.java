package com.aashdit.prod.heads.common.utils;

import java.util.HashMap;
import java.util.Map;

public class ApplicationConstants {
	public static final String CONST_FILEUPLOAD_PREFIX = "Upload-Doc";

	public static final String ROLE_PUBLIC = "ROLE_PUBLIC";
	public static final String DEFAULT_PASSWORD = "123456";
    public static final String USER_ENTITY_ID_KEY = "entityId";
	public static final String USER_ENTITY_LEVEL_KEY = "entityLevel";
    public static final String OBJECT_ID_AND_TYPE = "_OBJ_ID_AND_TYPE";
    public static final String USER_ENTITY_ID_AND_LEVEL_KEY = "entityIdAndLevel";
	public static final String ROLE_ACCESS_TYPE_PUBLIC = "PUBLIC";
    public static final String MUNICIPALITY = "MUNICIPALITY";
	public static final String GOVERNING_BODY = "CORPORATE";
	private static final Map<String, String> staticMap = new HashMap<>();
	public static String selectedEntityObjectIdAndType = "selectedEntityObjectIdAndValue";
	public static String isReadOnly = "isReadOnly";
	public static String selectedEntityParentObjectIdAndValue = "selectedEntityParentObjectIdAndValue";
	public static String selectedUpperEntityObjectIdAndType = "selectedUpperEntityObjectIdAndType";
	public static String selectedOrgEntityObjectIdAndType = "selectedOrgEntityObjectIdAndType";

	static {
		staticMap.put("AADH", "AADHAR");
		staticMap.put("PROF", "PROFILE_PICTURE");
		staticMap.put("DISABLED", "DISABLED_CERTIFICATE");
		staticMap.put("DOC", "DOCUMENTS");
		staticMap.put("ENROLLDOC", "ENROLLMENTDOCUMENTS");
		staticMap.put("LEAVEDOC", "LEAVE_DOCUMENTS");
		
	}

	public static final String ONSERVICE = "ONSERVICE";

	public static final String USER_LEVEL_COLLEGE = "COLG";
	public static final String USER_LEVEL_HEAD_DEPARTMENT = "DEPT";
	public static final String USER_LEVEL_UNIVERSITY = "UNV";
	public static final String USER_LEVEL_OFFICE = "OFFICE";
	public static final String USER_TYPE_ADMIN = "ADMIN";
	public static final String ROLE_ADMIN = "ROLE_ADMIN";


	public static final String ONE_TIME = "ONE_TIME";
	


	public static String GENERATE_RANDOM_NUMBER(int i) {
		return String.valueOf((int) (Math.random() * i));
	}
	

	//LEVEL CODES OF t_umt_mst_role_right_level
	public static final String LVL_CODE_GOVERNING ="CORPORATE";
	public static final String LVL_CODE_MUNICIPALITY="MUNICIPALITY";

	public static final String STOCK_TYPE_ITEM = "ITEM";
	public static final String STOCK_TYPE_ASSET = "EQUIPMENT";

//Flow TAB
	public static final String DRAFT = "DRAFT";
	public static final String PENDING = "PENDING";
	public static final String IN_PROGRESS = "IN_PROGRESS";
	public static final String APPROVE = "APPROVE";
	public static final String REVERT = "REVERT";
	public static final String REJECT = "REJECT";
	
//	INVOICE
	public static final String INVOICE_STRING = "EM";


	public static final String VEHICLE_PAYMENT="VEHICLE_PAYMENT";
	public static final String CAMPAIGN_PAYMENT="CAMPAIGN_PAYMENT";

// LEAVE
	public static final String LEAVE_MODULE_CODE = "LEAVE-APPLY";
	public static final String APPLICABLE_TO_STAFF = "Staff";
	public static final String APPLICABLE_TO_ALL = "ALL";
	
	public static final String HALF_WDR_STATUS = "HALF_WDR";
	public static final String WFLOW_WDR_STATUS = "WITHDRAWN";
	public static final String FULL_WDR_STATUS = "FULL_WDR";
	
	public static final String WFLOW_INITIAL_STATUS = "PENDING";
	public static final String WFLOW_FINAL_STATUS = "APPROVED";
	public static final String WFLOW_FINAL_STATUS_REJECT = "REJECTED";
	public static final String WFLOW_REJECT_STATUS = "REJECTED";
	public static final String WFLOW_INITIAL_SENT_STATUS = "SENT_FOR_VERIFICATION";
	public static final String TRANSFER_MODULE_CODE = "TRANSFER";
	public static final String TOUR_MODULE_CODE = "TOUR-REQUEST";
	public static final String SUSPENSION_MODULE_CODE= "SUSPENSION";
	public static final String REVOKE_MODULE_CODE= "REVOKE";
	public static final String CONV_ALLOWANCE_MODULE_CODE = "CONV-ALLOWANCE";
}
