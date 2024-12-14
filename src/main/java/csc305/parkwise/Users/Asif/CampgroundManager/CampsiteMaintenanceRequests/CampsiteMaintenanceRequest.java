package csc305.parkwise.Users.Asif.CampgroundManager.CampsiteMaintenanceRequests;

import csc305.parkwise.Common.Utils.Stream.StreamMapper;
import csc305.parkwise.Common.Utils.Stream.StreamsEnum.ObjectStreams;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;

import static csc305.parkwise.Common.Utils.Stream.ObjectStreamOperation.getObjectOutputStream;

public class CampsiteMaintenanceRequest implements Serializable {
	private int userId;
	private int requestId;
	private String status;
	private int campsiteId;
	private String priority;
	private String description;
	private String requestType;
	private LocalDate requestDate;
	private String decisionComments;

	public CampsiteMaintenanceRequest(int userId, int requestId, String status, int campsiteId, String priority,
			String description, String requestType, LocalDate requestDate, String decisionComments) {
		this.userId = userId;
		this.requestId = requestId;
		this.status = status;
		this.campsiteId = campsiteId;
		this.priority = priority;
		this.description = description;
		this.requestType = requestType;
		this.requestDate = requestDate;
		this.decisionComments = decisionComments;
	}

	public String getDecisionComments() {
		return decisionComments;
	}

	public void setDecisionComments(String decisionComments) {
		this.decisionComments = decisionComments;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public LocalDate getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(LocalDate requestDate) {
		this.requestDate = requestDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	public int getCampsiteId() {
		return campsiteId;
	}

	public void setCampsiteId(int campsiteId) {
		this.campsiteId = campsiteId;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isHighPriority() {
		return "High".equalsIgnoreCase(priority);
	}

	public boolean isOverdue(int daysThreshold) {
		return LocalDate.now().isAfter(requestDate.plusDays(daysThreshold));
	}

	@Override
	public String toString() {
		return "CampsiteMaintenanceRequest{" +
				"userId=" + userId +
				", requestId=" + requestId +
				", status='" + status + '\'' +
				", campsiteId=" + campsiteId +
				", priority='" + priority + '\'' +
				", description='" + description + '\'' +
				", requestType='" + requestType + '\'' +
				", requestDate=" + requestDate +
				", decisionComments='" + decisionComments + '\'' +
				'}';
	}
}
