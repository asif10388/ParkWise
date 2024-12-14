package csc305.parkwise.Users.Asif.ParkDirector;

import csc305.parkwise.Users.Asif.CampgroundManager.CampsiteMaintenanceRequests.CampsiteMaintenanceRequest;
import csc305.parkwise.Users.Asif.ParkDirector.Announcements.Announcement;
import csc305.parkwise.Users.Asif.ParkDirector.Budgets.Budget;
import csc305.parkwise.Users.Asif.ParkDirector.PerformanceReviews.PerformanceReview;
import csc305.parkwise.Users.Asif.ParkDirector.StaffAccounts.StaffAccount;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ParkDirector extends StaffAccount implements Serializable {
	private List<Budget> budgets;
	private List<Announcement> announcements;
	private List<StaffAccount> staffAccounts;
	private List<PerformanceReview> performanceReviews;
	private List<CampsiteMaintenanceRequest> maintenanceRequests;

	public List<Budget> getBudgets() {
		return budgets;
	}

	public void setBudgets(List<Budget> budgets) {
		this.budgets = budgets;
	}

	public List<Announcement> getAnnouncements() {
		return announcements;
	}

	public void setAnnouncements(List<Announcement> announcements) {
		this.announcements = announcements;
	}

	public void setstaffAccounts(List<StaffAccount> staffAccounts) {
		this.staffAccounts = staffAccounts;
	}

	public List<PerformanceReview> getPerformanceReviews() {
		return performanceReviews;
	}

	public void setPerformanceReviews(List<PerformanceReview> performanceReviews) {
		this.performanceReviews = performanceReviews;
	}

	public List<CampsiteMaintenanceRequest> getMaintenanceRequests() {
		return maintenanceRequests;
	}

	public void setMaintenanceRequests(List<CampsiteMaintenanceRequest> maintenanceRequests) {
		this.maintenanceRequests = maintenanceRequests;
	}

	public ParkDirector() {
		super();
		this.budgets = new ArrayList<>();
		this.announcements = new ArrayList<>();
		this.performanceReviews = new ArrayList<>();
		this.maintenanceRequests = new ArrayList<>();
		this.staffAccounts = new ArrayList<>();
	}

	public ParkDirector(int userId, String email, String password, String fullName, String userType) {
		super(userId, email, password, fullName, userType);
		this.budgets = new ArrayList<>();
		this.announcements = new ArrayList<>();
		this.performanceReviews = new ArrayList<>();
		this.maintenanceRequests = new ArrayList<>();
		this.staffAccounts = new ArrayList<>();
	}

	public void addStaffAccount(StaffAccount staff) {
		staffAccounts.add(staff);
	}

	public void removeStaffAccount(StaffAccount staff) {
		staffAccounts.remove(staff);
	}

	public List<StaffAccount> getstaffAccounts() {
		return staffAccounts;
	}

	public List<CampsiteMaintenanceRequest> getPendingMaintenanceRequests() {
		return maintenanceRequests;
	}

}