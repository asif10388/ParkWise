package csc305.parkwise.Users.Asif.CampgroundManager.Common.Models;

import java.io.Serializable;
import java.util.List;

public class Campsite implements Serializable {
	private int campsiteId;
	private String campsiteName;
	private String campsiteStatus;
	private String campsiteLocation;
	private double campsitePerDayFee;
	private String maintenanceReason;
	private String currentReservedUserId;

	public Campsite(int campsiteId, String campsiteName, String campsiteStatus, String campsiteLocation,
			double campsitePerDayFee, String maintenanceReason, String currentReservedUserId) {
		this.campsiteId = campsiteId;
		this.campsiteName = campsiteName;
		this.campsiteStatus = campsiteStatus;
		this.campsiteLocation = campsiteLocation;
		this.campsitePerDayFee = campsitePerDayFee;
		this.maintenanceReason = maintenanceReason;
		this.currentReservedUserId = currentReservedUserId;
	}

	public String getMaintenanceReason() {
		return maintenanceReason;
	}

	public void setMaintenanceReason(String maintenanceReason) {
		this.maintenanceReason = maintenanceReason;
	}

	public double getCampsitePerDayFee() {
		return campsitePerDayFee;
	}

	public void setCampsitePerDayFee(double campsitePerDayFee) {
		this.campsitePerDayFee = campsitePerDayFee;
	}

	public int getCampsiteId() {
		return campsiteId;
	}

	public void setCampsiteId(int campsiteId) {
		this.campsiteId = campsiteId;
	}

	public String getCampsiteName() {
		return campsiteName;
	}

	public void setCampsiteName(String campsiteName) {
		this.campsiteName = campsiteName;
	}

	public String getCampsiteStatus() {
		return campsiteStatus;
	}

	public void setCampsiteStatus(String campsiteStatus) {
		this.campsiteStatus = campsiteStatus;
	}

	public String getCampsiteLocation() {
		return campsiteLocation;
	}

	public void setCampsiteLocation(String campsiteLocation) {
		this.campsiteLocation = campsiteLocation;
	}

	public String getCurrentReservedUserId() {
		return currentReservedUserId;
	}

	public void setCurrentReservedUserId(String currentReservedUserId) {
		this.currentReservedUserId = currentReservedUserId;
	}

	public boolean isCampsiteAvailable() {
		return "Available".equals(campsiteStatus);
	}

	public boolean isCampsiteUnderMaintenance() {
		return "Under Maintenance".equals(campsiteStatus);
	}

	public double calculateTotalFee(int numberOfDays) {
		return campsitePerDayFee * numberOfDays;
	}

	public void resetCampsite() {
		this.maintenanceReason = null;
		this.campsiteStatus = "Available";
		this.currentReservedUserId = null;
	}

	public double calculateTotalRevenue(List<CampsiteBooking> bookings) {
		double totalRevenue = 0.0;

		for (CampsiteBooking booking : bookings) {
			if (booking.getCampsiteId() == this.campsiteId) {
				int numberOfDays = booking.getCheckOutDate().compareTo(booking.getCheckInDate());
				totalRevenue += calculateTotalFee(numberOfDays);
			}
		}
		return totalRevenue;
	}

	@Override
	public String toString() {
		return "Campsite{" +
				"campsiteId=" + campsiteId +
				", campsiteName='" + campsiteName + '\'' +
				", campsiteStatus='" + campsiteStatus + '\'' +
				", campsiteLocation='" + campsiteLocation + '\'' +
				", campsitePerDayFee=" + campsitePerDayFee +
				", maintenanceReason='" + maintenanceReason + '\'' +
				", currentReservedUserId='" + currentReservedUserId + '\'' +
				'}';
	}

}
