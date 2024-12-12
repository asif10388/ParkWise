package csc305.parkwise.Users.Asif.CampgroundManager.OccupancyReports;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class OccupancyReport implements Serializable {
    private int reportId;
    private int totalReserved;
    private LocalDate endDate;
    private LocalDate startDate;
    private ArrayList<Integer> campsiteIds;

    public OccupancyReport(int reportId, int totalReserved, LocalDate endDate, LocalDate startDate, ArrayList<Integer> campsiteIds) {
        this.reportId = reportId;
        this.totalReserved = totalReserved;
        this.endDate = endDate;
        this.startDate = startDate;
        this.campsiteIds = campsiteIds;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    public int getTotalReserved() {
        return totalReserved;
    }

    public void setTotalReserved(int totalReserved) {
        this.totalReserved = totalReserved;
    }

    public ArrayList<Integer> getCampsiteIds() {
        return campsiteIds;
    }

    public void setCampsiteIds(ArrayList<Integer> campsiteIds) {
        this.campsiteIds = campsiteIds;
    }

    @Override
    public String toString() {
        return "OccupancyReport{" +
                "reportId=" + reportId +
                ", totalReserved=" + totalReserved +
                ", endDate=" + endDate +
                ", startDate=" + startDate +
                ", campsiteIds=" + campsiteIds +
                '}';
    }
}
