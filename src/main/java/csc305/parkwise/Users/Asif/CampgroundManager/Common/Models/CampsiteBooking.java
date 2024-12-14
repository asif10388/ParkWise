package csc305.parkwise.Users.Asif.CampgroundManager.Common.Models;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class CampsiteBooking implements Serializable {
	private int userId;
	private boolean paid;
	private int bookingId;
	private int campsiteId;
	private int parkRangerId;
	private double paidAmount;
	private String bookingStatus;
	private LocalDate checkInDate;
	private LocalDate bookingDate;
	private LocalDate checkOutDate;

	public CampsiteBooking(int userId, boolean paid, int bookingId, int campsiteId, int parkRangerId, double paidAmount,
			String bookingStatus, LocalDate checkInDate, LocalDate bookingDate, LocalDate checkOutDate) {
		this.userId = userId;
		this.paid = paid;
		this.bookingId = bookingId;
		this.campsiteId = campsiteId;
		this.parkRangerId = parkRangerId;
		this.paidAmount = paidAmount;
		this.bookingStatus = bookingStatus;
		this.checkInDate = checkInDate;
		this.bookingDate = bookingDate;
		this.checkOutDate = checkOutDate;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public int getCampsiteId() {
		return campsiteId;
	}

	public void setCampsiteId(int campsiteId) {
		this.campsiteId = campsiteId;
	}

	public int getParkRangerId() {
		return parkRangerId;
	}

	public void setParkRangerId(int parkRangerId) {
		this.parkRangerId = parkRangerId;
	}

	public double getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(double paidAmount) {
		this.paidAmount = paidAmount;
	}

	public String getBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

	public LocalDate getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(LocalDate checkInDate) {
		this.checkInDate = checkInDate;
	}

	public LocalDate getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(LocalDate bookingDate) {
		this.bookingDate = bookingDate;
	}

	public LocalDate getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(LocalDate checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	public int getDurationInDays() {
		return getCheckOutDate().compareTo(getCheckInDate());
	}

	public boolean isEligibleForDiscount(double discountThreshold) {
		return paidAmount >= discountThreshold;
	}

	public void extendBooking(int additionalDays) {
		if (additionalDays > 0) {
			this.checkOutDate = this.checkOutDate.plusDays(additionalDays);
		} else {
			throw new IllegalArgumentException("Additional days must be greater than zero.");
		}
	}

	public boolean isEligibleForRefund(double cancellationThreshold) {
		long daysUntilCheckIn = ChronoUnit.DAYS.between(LocalDate.now(), checkInDate);
		return daysUntilCheckIn > cancellationThreshold;
	}

	@Override
	public String toString() {
		return "CampsiteBooking{" +
				"userId=" + userId +
				", paid=" + paid +
				", bookingId=" + bookingId +
				", campsiteId=" + campsiteId +
				", parkRangerId=" + parkRangerId +
				", paidAmount=" + paidAmount +
				", bookingStatus='" + bookingStatus + '\'' +
				", checkInDate=" + checkInDate +
				", bookingDate=" + bookingDate +
				", checkOutDate=" + checkOutDate +
				'}';
	}
}
