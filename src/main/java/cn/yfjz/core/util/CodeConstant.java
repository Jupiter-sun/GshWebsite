package cn.yfjz.core.util;

/**
 * Created by liwj on 2015-7-12.
 */
public class CodeConstant {

    public static final String INTERNAL_SERVER_ERROR = "500";

    public static final String LEVEL_INFO = "info";
    public static final String LEVEL_WARN = "warn";
    public static final String LEVEL_ERROR = "error";

    public static final String USER_TYPE_STUDENT = "4";
    public static final String USER_TYPE_TEACHER = "2";
    public static final String USER_TYPE_OTHER = "9";
    
    public static final String DEPT_ROOT_DEFAULT_ID = "1";
    public static final String DEPT_ROOT_DEFAULT_NAME = "";

    public static final String KIND_ROOT_DEFAULT_ID = "1";
    public static final String KIND_ROOT_DEFAULT_NAME = "根节点";
    
    public static final String STUDENT_QUERY_ROOT_ID = "-1";
	public static final String STUDENT_QUERY_ROOT_NAME = "根节点";

    public static final String RESULT_TAB_HTML = "/common/jump_tab";
    public static final String RESULT_TOPIC_HTML = "/common/jump_grid";
    public static final String RESULT_HTML = "/common/jump";
    public static final String ROLE_ISSYSTEM_NO = "0";


    public static final String WC_KEY = "073184372828";
    public static final String WC_APPTOKEN = "WangcaiHjjk";

    public static final String WC_DESKEY = "84372828";
    
    public static final String CHANNEL_ROOT_DEFAULT_ID = "1";
    public static final String CONTENT_ROOT_DEFAULT_ID = "1";

    public static final String LOG_LOGIN = "登录";
    public static final String LOG_LOGOUT = "退出";
    public static final String LOG_ADD = "新增";
    public static final String LOG_EDIT = "修改";
    public static final String LOG_REMOVE = "删除";
    public static final String FUND_FPFS_MEFP = "mefp";
    public static final String FUND_FPFS_JEFP = "jefp";

    public static final String YES = "1";//是
    public static final String NO = "0";//否

    public static final String CODEKIND_ROOT_ID = "0";
    public static final String CODEKIND_ROOT_NAME = "根节点";

    public static final String CEPING_XY_STATUS="1";

    public static final String CEPING_TYPE_1="ceping_type_1";
    public static final String CEPING_TYPE_2="ceping_type_2";
    public static final String CEPING_TYPE_3="ceping_type_3";

    public static final String MODULE_CEPING="ceping";
    /**
     * 名单公示的栏目ID
     */
    public static final Long CHANNEL_MDGS_ID = 1L;
    
    /**
     * 流程状态：开始
     */
    public static final String PROCESS_STATUS_START = "start";
    /**
     * 流程状态：活动
     */
    public static final String PROCESS_STATUS_ACTIVE = "active";
    /**
     * 流程状态：结束
     */
    public static final String PROCESS_STATUS_END = "end";

    /**
     * 审批结果：已申请
     */
    public static final String PROCESS_SPJG_CREATE = "0";
    /**
     * 审批结果：通过
     */
    public static final String PROCESS_SPJG_APPROVE = "1";
    /**
     * 审批结果：不通过
     */
    public static final String PROCESS_SPJG_UNAPPROVE = "2";
    /**
     * 审批结果：退回
     */
    public static final String PROCESS_SPJG_REFUSE = "9";

//    public static final String PROCESS_DATA_STEPSTATUS="1";
    /*
    学生卡补办流程的id
    */
    public static final String STD_CARD_PROCESS_DEF_ID="151431826f7";

    public static final String DATABASE_BEAN_DM = "dataSourceDm";//dataSourceDm
    public static final String DATABASE_BEAN_ORACLE = "dataSource";

    public static final String HOME_FILT_XW = "HOME_ACTIONS";
    public static final String CONFIG_PUSH_QY = "CONFIG_PUSH_QY";
}
