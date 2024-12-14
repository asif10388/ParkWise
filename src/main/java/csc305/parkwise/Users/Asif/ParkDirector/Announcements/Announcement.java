package csc305.parkwise.Users.Asif.ParkDirector.Announcements;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import csc305.parkwise.Common.Utils.Stream.ObjectStreamOperation;
import csc305.parkwise.Common.Utils.Stream.StreamMapper;
import csc305.parkwise.Common.Utils.Stream.StreamsEnum.ObjectStreams;
import csc305.parkwise.Users.Asif.ParkDirector.StaffAccounts.StaffAccount;

public class Announcement implements Serializable {
	private String body;
	private String title;
	private LocalDate expirationDate;
	private ArrayList<String> receivers;

	private String status;
	private String announcer;
	private LocalDate createdAt;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAnnouncer() {
		return announcer;
	}

	public void setAnnouncer(String announcer) {
		this.announcer = announcer;
	}

	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}

	public Announcement(String body, String title, LocalDate expirationDate, ArrayList<String> receivers, String status,
			String announcer, LocalDate createdAt) {
		this.body = body;
		this.title = title;
		this.expirationDate = expirationDate;
		this.receivers = receivers;
		this.status = status;
		this.announcer = announcer;
		this.createdAt = createdAt;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public LocalDate getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(LocalDate expirationDate) {
		this.expirationDate = expirationDate;
	}

	public ArrayList<String> getReceivers() {
		return receivers;
	}

	public void setReceivers(ArrayList<String> receivers) {
		this.receivers = receivers;
	}

	public boolean isAnnouncementActive() {
		return "Active".equalsIgnoreCase(status);
	}

	public boolean isAnnouncementExpired() {
		return LocalDate.now().isAfter(expirationDate);
	}

	public static boolean isValidExpirationDate(LocalDate expirationDate) {
		return expirationDate != null && expirationDate.isAfter(LocalDate.now());
	}

	public String getSummary() {
		return String.format("Title: %s, Status: %s, Expires on: %s", title, status, expirationDate);
	}

	public int getNumberOfReceivers() {
		return receivers.size();
	}

	public static String getAnnouncerName() throws IOException {
		StreamMapper stream = new StreamMapper();

		List<Object> userObjects = ObjectStreamOperation.getObjectsFromFile(
				stream.getObjectStream(ObjectStreams.StaffObjects));

		Optional<StaffAccount> findParkDirector = userObjects.stream()
				.filter(obj -> obj instanceof StaffAccount)
				.map(obj -> (StaffAccount) obj)
				.filter(obj -> Objects.equals(obj.getUserType(), "Park Director"))
				.findFirst();

		if (findParkDirector.isPresent())
			return findParkDirector.get().getFullName();
		return null;
	};

	@Override
	public String toString() {
		return "Announcement{" +
				"body='" + body + '\'' +
				", title='" + title + '\'' +
				", expirationDate=" + expirationDate +
				", receivers=" + receivers +
				", status='" + status + '\'' +
				", announcer='" + announcer + '\'' +
				", createdAt=" + createdAt +
				'}';
	}

}
