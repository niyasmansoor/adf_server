package model;

import com.ibm.icu.text.DecimalFormat;

public class LeadScore {

	private double firstNameScore;
	private double lastNameScore;
	private double emailScore;
	private double zipCodeScore;
	private double cityScore;
	private double stateScore;
	private double phoneNoScore;
	private double zipCityStateScore;
	private double timeFrameScore;
	private double purchaseTypeScore;
	private double contactTypeScore;
	private double contactTimeScore;
	private double score;

	private boolean isContactFlagSet;

	public double getFirstNameScore() {
		return firstNameScore;
	}

	public void setFirstNameScore(double firstNameScore) {
		this.firstNameScore = firstNameScore;
	}

	public double getLastNameScore() {
		return lastNameScore;
	}

	public void setLastNameScore(double lastNameScore) {
		this.lastNameScore = lastNameScore;
	}

	public double getEmailScore() {
		return emailScore;
	}

	public void setEmailScore(double emailScore) {
		this.emailScore = emailScore;
	}

	public double getZipCodeScore() {
		return zipCodeScore;
	}

	public void setZipCodeScore(double zipCodeScore) {
		this.zipCodeScore = zipCodeScore;
	}

	public double getCityScore() {
		return cityScore;
	}

	public void setCityScore(double cityScore) {
		this.cityScore = cityScore;
	}

	public double getStateScore() {
		return stateScore;
	}

	public void setStateScore(double stateScore) {
		this.stateScore = stateScore;
	}

	public double getPhoneNoScore() {
		return phoneNoScore;
	}

	public void setPhoneNoScore(double phoneNoScore) {
		this.phoneNoScore = phoneNoScore;
	}

	public double getZipCityStateScore() {
		return zipCityStateScore;
	}

	public void setZipCityStateScore(double zipCityStateScore) {
		this.zipCityStateScore = zipCityStateScore;
	}

	public double getTimeFrameScore() {
		return timeFrameScore;
	}

	public void setTimeFrameScore(double timeFrameScore) {
		this.timeFrameScore = timeFrameScore;
	}

	public double getPurchaseTypeScore() {
		return purchaseTypeScore;
	}

	public void setPurchaseTypeScore(double purchaseTypeScore) {
		this.purchaseTypeScore = purchaseTypeScore;
	}

	public double getContactTypeScore() {
		return contactTypeScore;
	}

	public void setContactTypeScore(double contactTypeScore) {
		this.contactTypeScore = contactTypeScore;
	}

	public double getContactTimeScore() {
		return contactTimeScore;
	}

	public void setContactTimeScore(double contactTimeScore) {
		this.contactTimeScore = contactTimeScore;
	}

	public double getScore() {
		return this.addScores();
	}

	public void setScore(double score) {
		this.score = score;
	}

	private double addScores() {

		// System.out.println("FN >"+this.getFirstNameScore());
		// System.out.println("LN >"+this.getLastNameScore());
		// System.out.println("EM >"+this.getEmailScore());
		// System.out.println("ZC >"+this.getZipCodeScore());
		// System.out.println("CTY >"+this.getCityScore());
		// System.out.println("ST >"+this.getStateScore());
		// System.out.println("CST >"+this.getZipCityStateScore());
		// System.out.println("PNO >"+this.getPhoneNoScore());
		// System.out.println("TF >"+this.getTimeFrameScore());
		// System.out.println("PT >"+this.getPurchaseTypeScore());
		// System.out.println("CT >"+this.getContactTypeScore());
		// System.out.println("TIM >"+this.getContactTimeScore());

		return (this.firstNameScore + this.lastNameScore + this.emailScore
				+ this.zipCodeScore + this.cityScore + this.stateScore
				+ this.phoneNoScore + this.zipCityStateScore
				+ this.timeFrameScore + this.purchaseTypeScore
				+ this.contactTypeScore + this.contactTimeScore);
	}

	public boolean isContactFlagSet() {
		return isContactFlagSet;
	}

	public void setContactFlagSet(boolean isContactFlagSet) {
		this.isContactFlagSet = isContactFlagSet;
	}

	

}
