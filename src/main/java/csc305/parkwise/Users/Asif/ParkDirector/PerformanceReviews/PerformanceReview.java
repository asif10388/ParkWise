package csc305.parkwise.Users.Asif.ParkDirector.PerformanceReviews;

import java.io.Serializable;

public class PerformanceReview implements Serializable {
	private int staffId;
	private String staffName;
	private String staffRole;

	private int jobRating;
	private int teamworkRating;
	private int punctualityRating;
	private int workQualityRating;

	private int overallRating;

	public int getPunctualityRating() {
		return punctualityRating;
	}

	public void setPunctualityRating(int punctualityRating) {
		this.punctualityRating = punctualityRating;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	private String feedback;

	public PerformanceReview() {
	}

	public PerformanceReview(int staffId, String staffName, String staffRole, int jobRating, int teamworkRating,
			int punctualityRating, int workQualityRating, String feedback) {
		this.staffId = staffId;
		this.feedback = feedback;
		this.staffName = staffName;
		this.staffRole = staffRole;
		this.jobRating = jobRating;
		this.teamworkRating = teamworkRating;
		this.punctualityRating = punctualityRating;
		this.workQualityRating = workQualityRating;
	}

	public int getStaffId() {
		return staffId;
	}

	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getStaffRole() {
		return staffRole;
	}

	public void setStaffRole(String staffRole) {
		this.staffRole = staffRole;
	}

	public int getJobRating() {
		return jobRating;
	}

	public void setJobRating(int jobRating) {
		this.jobRating = jobRating;
	}

	public int getTeamworkRating() {
		return teamworkRating;
	}

	public void setTeamworkRating(int teamworkRating) {
		this.teamworkRating = teamworkRating;
	}

	public int getWorkQualityRating() {
		return workQualityRating;
	}

	public void setWorkQualityRating(int workQualityRating) {
		this.workQualityRating = workQualityRating;
	}

	public double getOverallRating() {
		return ((this.jobRating + this.teamworkRating + this.workQualityRating + this.punctualityRating) / 4.0);
	}

	public void setOverallRating(int overallRating) {
		this.overallRating = overallRating;
	}

	public boolean isPerformanceSatisfactory() {
		return getOverallRating() >= 7.0;
	}

	public boolean areAllRatingsPerfect() {
		return jobRating == 10 && teamworkRating == 10 && workQualityRating == 10 && punctualityRating == 10;
	}

	public String getOverallRatingAsLetter() {
		double overall = getOverallRating();
		if (overall >= 9) {
			return "A";
		} else if (overall >= 8) {
			return "B";
		} else if (overall >= 7) {
			return "C";
		} else if (overall >= 6) {
			return "D";
		} else {
			return "F";
		}
	}

	@Override
	public String toString() {
		return "PerformanceReview{" +
				"staffId=" + staffId +
				", staffName='" + staffName + '\'' +
				", staffRole='" + staffRole + '\'' +
				", jobRating=" + jobRating +
				", teamworkRating=" + teamworkRating +
				", punctualityRating=" + punctualityRating +
				", workQualityRating=" + workQualityRating +
				", overallRating=" + overallRating +
				", feedback='" + feedback + '\'' +
				'}';
	}
}
