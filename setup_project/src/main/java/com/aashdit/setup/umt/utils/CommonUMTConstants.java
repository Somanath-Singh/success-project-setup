package com.aashdit.setup.umt.utils;

import java.util.HashMap;
import java.util.Map;

public class CommonUMTConstants {
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

    public static final String USER_TYPE_ADMIN = "ADMIN";
    public static final String ROLE_ADMIN = "ROLE_ADMIN";


    public static final String ONE_TIME = "ONE_TIME";



    public static String GENERATE_RANDOM_NUMBER(int i) {
        return String.valueOf((int) (Math.random() * i));
    }


    //LEVEL CODES OF t_umt_mst_role_right_level
    public static final String LVL_CODE_GOVERNING ="GOVERNING";
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
}
