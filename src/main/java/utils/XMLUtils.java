package utils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.LeadScore;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import com.ibm.icu.text.DecimalFormat;

//import pstsassignment.ADFConstants;

public class XMLUtils {
	@SuppressWarnings("unused")
	private static SAXReader reader = new SAXReader();
	@SuppressWarnings("unused")
	private static Document document;
	private CSVUtils csvUtils;
	private LeadScore score;
	private ClassLoader classLoader;
	
	public XMLUtils(){
			
	}

	public LeadScore calculateLeadScore(File adfFile) throws IOException, DocumentException{
		document = reader.read( adfFile );
		this.calculateADFScore();
		return this.score;
	}

	private void calculateADFScore() throws IOException {
		csvUtils = new CSVUtils();		
		classLoader = XMLUtils.class.getClassLoader();	
		score = new LeadScore();
		Element classElement = document.getRootElement();
		List<Node> nodes = document
				.selectNodes("/adf/prospect/customer/contact");

		for (Node node : nodes) {

			List<Node> names = node.selectNodes("name");
			String firstName = names.get(0).getText(), lastName = names.get(1)
					.getText();
			
			score.setFirstNameScore(this.roundTo2Decimals(this.calculateScore(ADFConstants.CASE_FIRST_NAME, csvUtils.validateFirstName(firstName))));
			score.setLastNameScore(this.roundTo2Decimals(this.calculateScore(ADFConstants.CASE_LAST_NAME, csvUtils.validateLastName(lastName))));
			Node emailNode = node.selectSingleNode(ADFConstants.CASE_EMAIL);
			
			if(!(null == emailNode)){
			String prefEmail = emailNode.valueOf("@preferredcontact").trim();
			//System.out.println("Pref email>"+prefEmail);
			
			if((!(null == prefEmail)) && (("1".equalsIgnoreCase(prefEmail)) || ("yes".equalsIgnoreCase(prefEmail)))){
				if(!score.isContactFlagSet()){
				score.setContactFlagSet(true);
				score.setContactTypeScore(this.roundTo2Decimals(this.calculateScore(ADFConstants.CASE_CONT_TYPE_E, false)));
				}				
			}
			score.setEmailScore(this.roundTo2Decimals(this.calculateScore(ADFConstants.CASE_EMAIL, this.validateEmail(emailNode.getText().trim()))));
			
			String pref = emailNode.valueOf("time").trim();
			if(!(null==pref)){				
				if(ADFConstants.CASE_TIME_TYPE_D.equalsIgnoreCase(pref))
					score.setContactTimeScore(this.roundTo2Decimals(this.calculateScore(ADFConstants.CASE_TIME_TYPE_D, false)));
				else if(ADFConstants.CASE_TIME_TYPE_E.equalsIgnoreCase(pref))
					score.setContactTimeScore(this.roundTo2Decimals(this.calculateScore(ADFConstants.CASE_TIME_TYPE_E, false))); 
				else
					score.setContactTimeScore(this.roundTo2Decimals(this.calculateScore(ADFConstants.CASE_TIME_TYPE_N, false)));
			}
			
			} // end of email node ...
			
			
			Node phoneNode = node.selectSingleNode(ADFConstants.CASE_PH);
			if(!(null == phoneNode)){
			
			score.setPhoneNoScore(this.roundTo2Decimals(this.calculateScore(ADFConstants.CASE_PH, this.validatePhone(phoneNode.getText().trim()))));
				
			String prefPhone = phoneNode.valueOf("@preferredcontact").trim();
			if((!(null == prefPhone)) && (("1".equalsIgnoreCase(prefPhone)) || ("yes".equalsIgnoreCase(prefPhone)))){
				if(!score.isContactFlagSet()){
				score.setContactFlagSet(true);
				score.setContactTypeScore(this.roundTo2Decimals(this.calculateScore(ADFConstants.CASE_CONT_TYPE_P, false)));
				}
			} else {
				if(!score.isContactFlagSet()){
					score.setContactTypeScore(this.roundTo2Decimals(this.calculateScore(ADFConstants.CASE_CONT_TYPE_N, false)));
				}
			}
			
			String pref = phoneNode.valueOf("@time").trim();
			//System.out.println("pref >>>>"+pref);
			if(!(null==pref)){				
				if(ADFConstants.CASE_TIME_TYPE_D.equalsIgnoreCase(pref))
					score.setContactTimeScore(this.roundTo2Decimals(this.calculateScore(ADFConstants.CASE_TIME_TYPE_D, false)));
				else if(ADFConstants.CASE_TIME_TYPE_E.equalsIgnoreCase(pref))
					score.setContactTimeScore(this.roundTo2Decimals(this.calculateScore(ADFConstants.CASE_TIME_TYPE_E, false))); 
				else
					score.setContactTimeScore(this.roundTo2Decimals(this.calculateScore(ADFConstants.CASE_TIME_TYPE_N, false)));
			}
		
			} // end of phone node ...	Z
			
		}		
		
		List<Node> addrs = document
				.selectNodes("/adf/prospect/customer/contact/address");
		
		if(!(null == addrs))
		for (Node node : addrs) {
			score.setZipCodeScore(this.roundTo2Decimals(this.calculateScore(ADFConstants.CASE_ZIPCODE, csvUtils.validateZipCode(node.selectSingleNode(ADFConstants.CASE_ZIPCODE).getText()))));
			score.setCityScore(this.roundTo2Decimals(this.calculateScore(ADFConstants.CASE_CITY, csvUtils.validateCity(node.selectSingleNode(ADFConstants.CASE_CITY).getText()))));
			score.setStateScore(this.roundTo2Decimals(this.calculateScore(ADFConstants.CASE_STATE, csvUtils.validateState(node.selectSingleNode(ADFConstants.CASE_STATE).getText()))));
			score.setZipCityStateScore(this.roundTo2Decimals(this.calculateScore(ADFConstants.CASE_CITY_STATE, csvUtils.validateCityState(node.selectSingleNode(ADFConstants.CASE_CITY).getText(),node.selectSingleNode(ADFConstants.CASE_STATE).getText()))));
		}
	
		List<Node> tf = document
				.selectNodes("/adf/prospect/customer/timeframe/description");
		
		if(!(null == tf))
		for (Node node : tf) {
			String value = node.getStringValue();
			if(!(null==value)){
				if((ADFConstants.CASE_TIMEFRAME_2W.equalsIgnoreCase(value)) || (ADFConstants.CASE_TIMEFRAME_1W.equalsIgnoreCase(value))){
					score.setTimeFrameScore(this.roundTo2Decimals(this.calculateScore(ADFConstants.CASE_TIMEFRAME_2W, false)));
				} else if(ADFConstants.CASE_TIMEFRAME_48.equalsIgnoreCase(value)){
					score.setTimeFrameScore(this.roundTo2Decimals(this.calculateScore(ADFConstants.CASE_TIMEFRAME_48, false)));
				} else if(ADFConstants.CASE_TIMEFRAME_1M.equalsIgnoreCase(value)){
					score.setTimeFrameScore(this.roundTo2Decimals(this.calculateScore(ADFConstants.CASE_TIMEFRAME_1M, false)));
				} else {
					score.setTimeFrameScore(this.roundTo2Decimals(this.calculateScore(ADFConstants.CASE_TIMEFRAME_N, false)));
				}
			}
		}
		
		List<Node> purNodes = document
				.selectNodes("/adf/prospect/vehicle/finance");

		if(!(null == purNodes))
		for (Node node : purNodes) {			
			String purType = node.selectSingleNode(ADFConstants.CASE_PUR_TYPE).getText();
			if(ADFConstants.CASE_PUR_TYPE_F.equalsIgnoreCase(purType))
				score.setPurchaseTypeScore(this.roundTo2Decimals(this.calculateScore(ADFConstants.CASE_PUR_TYPE_F, false)));
			else if(ADFConstants.CASE_PUR_TYPE_C.equalsIgnoreCase(purType))
				score.setPurchaseTypeScore(this.roundTo2Decimals(this.calculateScore(ADFConstants.CASE_PUR_TYPE_C, false)));
			else
				score.setPurchaseTypeScore(this.roundTo2Decimals(this.calculateScore(ADFConstants.CASE_PUR_TYPE_N, false)));
		}
		
		//System.out.println("TOT >"+score.getScore());
		
	}	

	private boolean validateEmail(String email) {
		Pattern pattern = Pattern.compile(ADFConstants.EMAIL_REG_EXP_STR);
		Matcher matcher = pattern.matcher(email);
		// //System.out.println(email +" : "+ matcher.matches());
		if (matcher.matches()) {
			return true;
		}
		return false;
	}

	private boolean validatePhone(String phone) {
		//System.out.println("ph no >"+phone);
		try {
			Long.parseLong(phone);
		} catch (Exception e) {
			return false;
		}
		if (phone.length() != ADFConstants.PHONE_NO_LENGTH)
			return false;

		return true;
	}

	private double calculateScore(String field,boolean value){
		
		switch(field){
		
		case ADFConstants.CASE_FIRST_NAME	:
			
			return value ? (ADFConstants.P_FIRST_NAME_WT * ADFConstants.WEIGHT_MULTIPLIER) : - (ADFConstants.N_FIRST_NAME_WT * ADFConstants.WEIGHT_MULTIPLIER);			
			
		case ADFConstants.CASE_LAST_NAME	:
			
			return value ? (ADFConstants.P_LAST_NAME_WT * ADFConstants.WEIGHT_MULTIPLIER)  : - (ADFConstants.N_LAST_NAME_WT * ADFConstants.WEIGHT_MULTIPLIER);
		
		case ADFConstants.CASE_EMAIL		:
			
			return value ? (ADFConstants.P_EMAIL_WT * ADFConstants.WEIGHT_MULTIPLIER) : - (ADFConstants.N_EMAIL_WT * ADFConstants.WEIGHT_MULTIPLIER);
			
		case ADFConstants.CASE_ZIPCODE		:
			
			return value ? (ADFConstants.P_ZIP_CODE_WT * ADFConstants.WEIGHT_MULTIPLIER) : - (ADFConstants.N_ZIP_CODE_WT * ADFConstants.WEIGHT_MULTIPLIER);
			
		case ADFConstants.CASE_CITY			:
			
			return value ? (ADFConstants.P_CITY_WT * ADFConstants.WEIGHT_MULTIPLIER) : - (ADFConstants.N_CITY_WT * ADFConstants.WEIGHT_MULTIPLIER);
			
		case ADFConstants.CASE_STATE	 	:
			
			return value ? (ADFConstants.P_STATE_WT * ADFConstants.WEIGHT_MULTIPLIER) : - (ADFConstants.N_STATE_WT * ADFConstants.WEIGHT_MULTIPLIER);
		
		case ADFConstants.CASE_CITY_STATE	:
			
			return value ? (ADFConstants.P_ZIP_CITY_STATE_WT * ADFConstants.WEIGHT_MULTIPLIER) : - (ADFConstants.N_ZIP_CITY_STATE_WT * ADFConstants.WEIGHT_MULTIPLIER);
			
		case ADFConstants.CASE_PH		:
			
			return value ? (ADFConstants.P_PHONE_NO_WT * ADFConstants.WEIGHT_MULTIPLIER) : - (ADFConstants.N_PHONE_NO_WT * ADFConstants.WEIGHT_MULTIPLIER);
			
		case ADFConstants.CASE_TIMEFRAME_48	:
			
			return ADFConstants.P_TIMEFRAME_48 * ADFConstants.WEIGHT_MULTIPLIER;
			
		case ADFConstants.CASE_TIMEFRAME_2W	:
			
			return ADFConstants.P_TIMEFRAME_2_WEEKS * ADFConstants.WEIGHT_MULTIPLIER;
			
		case ADFConstants.CASE_TIMEFRAME_1M	:
			
			return ADFConstants.P_TIMEFRAME_1_MONTH * ADFConstants.WEIGHT_MULTIPLIER;
			
		case ADFConstants.CASE_TIMEFRAME_N	:
			
			return  - (ADFConstants.N_TIMEFRAME * ADFConstants.WEIGHT_MULTIPLIER);
			
		case ADFConstants.CASE_PUR_TYPE_F	:
			
			return ADFConstants.P_PURCHASE_FINANCE * ADFConstants.WEIGHT_MULTIPLIER;
			
		case ADFConstants.CASE_PUR_TYPE_C	:
			
			return ADFConstants.P_PURCHASE_CASH * ADFConstants.WEIGHT_MULTIPLIER;
			
		case ADFConstants.CASE_PUR_TYPE_N	:
			
			return - (ADFConstants.N_PURCHASE * ADFConstants.WEIGHT_MULTIPLIER);
			
		case ADFConstants.CASE_CONT_TYPE_P	:
			
			return ADFConstants.P_CONTACT_PHONE * ADFConstants.WEIGHT_MULTIPLIER;
			
		case ADFConstants.CASE_CONT_TYPE_E	:
			
			return - (ADFConstants.N_CONTACT_EMAIL * ADFConstants.WEIGHT_MULTIPLIER);
			
		case ADFConstants.CASE_CONT_TYPE_N	:
			
			return - (ADFConstants.N_CONTACT_NONE * ADFConstants.WEIGHT_MULTIPLIER);
			
		case ADFConstants.CASE_TIME_TYPE_D	:
			
			return ADFConstants.P_TIME_DAY * ADFConstants.WEIGHT_MULTIPLIER;
			
		case ADFConstants.CASE_TIME_TYPE_E	:
			
			return ADFConstants.P_TIME_PM * ADFConstants.WEIGHT_MULTIPLIER;
			
		case ADFConstants.CASE_TIME_TYPE_N	:
			
			return - (ADFConstants.N_TIME_NONE * ADFConstants.WEIGHT_MULTIPLIER);
			
		default								:
			return 0;
		}
		
	}
	
	private double roundTo2Decimals(double val) {
		//System.out.println("Rounded Value b4> " + val);
		DecimalFormat df2 = new DecimalFormat("###.###");
		//System.out.println("Rounded Value after > "+ Double.valueOf(df2.format(val)));
		return Double.valueOf(df2.format(val));
	}
	
	public static void main(String[] args) throws IOException {
		XMLUtils util = new XMLUtils();
		// util.validateEmail("ni@abc.ae");
		model.LeadScore score = new model.LeadScore();
		score.setFirstNameScore(1);
		score.setLastNameScore(1);
		score.setEmailScore(-0.5);
		// //System.out.println(score.getScore());
		util.calculateADFScore();
	}
}
