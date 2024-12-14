package csc305.parkwise.Users.Asif.CampgroundManager;

import csc305.parkwise.Users.Asif.ParkDirector.StaffAccounts.StaffAccount;
import csc305.parkwise.Users.Asif.CampgroundManager.Common.Models.Campsite;
import csc305.parkwise.Users.Asif.CampgroundManager.Common.Models.CampsiteBooking;
import csc305.parkwise.Users.Asif.CampgroundManager.OccupancyReports.OccupancyReport;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CampgroundManager extends StaffAccount implements Serializable {
	private List<Campsite> campsites;
	private List<CampsiteBooking> bookings;
	private List<OccupancyReport> occupancyReports;

	public CampgroundManager() {
		super();
		this.bookings = new ArrayList<>();
		this.campsites = new ArrayList<>();
		this.occupancyReports = new ArrayList<>();
	}

	public CampgroundManager(int userId, String email, String password, String fullName, String userType) {
		super(userId, email, password, fullName, userType);
		this.bookings = new ArrayList<>();
		this.campsites = new ArrayList<>();
		this.occupancyReports = new ArrayList<>();
	}

	public void addCampsite(Campsite campsite) {
		campsites.add(campsite);
	}

	public void removeCampsite(Campsite campsite) {
		campsites.remove(campsite);
	}

	public List<Campsite> getCampsites() {
		return campsites;
	}

	public List<CampsiteBooking> getBookings() {
		return bookings;
	}

	public List<OccupancyReport> generateOccupancyReports() {
		return occupancyReports;
	}
}