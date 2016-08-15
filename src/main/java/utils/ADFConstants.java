package utils;

public class ADFConstants {
	
	public static final double P_FIRST_NAME_WT = 0.025;
	public static final double N_FIRST_NAME_WT = 0.025;
	
	public static final double P_LAST_NAME_WT = 0.05;
	public static final double N_LAST_NAME_WT = 0.025;
	
	public static final double P_EMAIL_WT = 0.05;
	public static final double N_EMAIL_WT = 0.025;
	
	public static final double P_ZIP_CODE_WT = 0.1;
	public static final double N_ZIP_CODE_WT = 0.05;
	
	public static final double P_CITY_WT = 0.1;
	public static final double N_CITY_WT = 0.05;
	
	public static final double P_STATE_WT = 0.1;
	public static final double N_STATE_WT = 0.05;
	
	public static final double P_PHONE_NO_WT = 0.1;
	public static final double N_PHONE_NO_WT = 0.05;
	
	public static final double P_ZIP_CITY_STATE_WT = 0.13;	
	public static final double N_ZIP_CITY_STATE_WT = 0.13;	
	
	public static final double P_TIMEFRAME_48 = 0.1;
	public static final double P_TIMEFRAME_2_WEEKS = 0.05;
	public static final double P_TIMEFRAME_1_MONTH = 0.025;
	public static final double N_TIMEFRAME = 0.1;
	
	public static final double P_PURCHASE_FINANCE = 0.0625;
	public static final double P_PURCHASE_CASH = 0.1;
	public static final double N_PURCHASE = 0.05;
	
	public static final double P_CONTACT_PHONE = 0.07;
	public static final double N_CONTACT_EMAIL = 0.025;
	public static final double N_CONTACT_NONE = 0.025;
	
	public static final double P_TIME_DAY = 0.075;
	public static final double P_TIME_PM = 0.035;
	public static final double N_TIME_NONE = 0.025;
		
	public static final double WEIGHT_MULTIPLIER = 10.0;
	public static final int CSV_NAME_SEARCH_INDEX = 0;
	public static final int CSV_ZIPCODE_INDEX = 0;
	public static final int CSV_CITY_INDEX = 1;
	public static final int CSV_STATE_INDEX = 2;
	
	public static final String EMAIL_REG_EXP_STR = "\\w{2,}@\\w{3,}\\.\\w{2,}"; 
	
	public static final int PHONE_NO_LENGTH = 10;
	
	public static final String NAME_SEARCH_STR = "name";
	
	public static final String CASE_FIRST_NAME = "firstName";
	public static final String CASE_LAST_NAME = "lastName";
	public static final String CASE_EMAIL = "email";
	public static final String CASE_ZIPCODE = "postalcode";
	public static final String CASE_CITY = "city";
	public static final String CASE_STATE = "regioncode";
	public static final String CASE_CITY_STATE = "cityState";
	public static final String CASE_PH = "phone";
	public static final String CASE_TIMEFRAME = "timeframe";
	public static final String CASE_TIMEFRAME_48 = "within 48 hours";
	public static final String CASE_TIMEFRAME_2W= "within 2 weeks";
	public static final String CASE_TIMEFRAME_1W= "within 1 week";
	public static final String CASE_TIMEFRAME_1M= "with in 1 month";
	public static final String CASE_TIMEFRAME_N= "timeFrameN";
	public static final String CASE_PUR_TYPE = "method";
	public static final String CASE_PUR_TYPE_F = "Finance";
	public static final String CASE_PUR_TYPE_C = "Cash";
	public static final String CASE_PUR_TYPE_N = "None";
	public static final String CASE_CONT_TYPE_P = "contactTypeP";
	public static final String CASE_CONT_TYPE_E = "contactTypeE";
	public static final String CASE_CONT_TYPE_N = "contactTypeN";
	public static final String CASE_TIME = "time";
	public static final String CASE_TIME_TYPE_D = "Day";
	public static final String CASE_TIME_TYPE_E = "Afternoon";
	public static final String CASE_TIME_TYPE_N = "nopreference";
	
	public static final String FILE_UPLOAD_LOC = "adf";
	
	
}