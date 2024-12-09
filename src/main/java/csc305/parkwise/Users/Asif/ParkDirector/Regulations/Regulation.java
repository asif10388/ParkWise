package csc305.parkwise.Users.Asif.ParkDirector.Regulations;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Regulation implements Serializable {
    private String ruleSummary;
    private String description;
    private String regulationType;
    private LocalDate effectiveDate;
    private ArrayList<String> appliesTo = new ArrayList<>();

    private String status;
    private LocalDate createdAt;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public Regulation(String ruleSummary, String description, String regulationType, LocalDate effectiveDate, ArrayList<String> appliesTo, String status, LocalDate createdAt) {
        this.ruleSummary = ruleSummary;
        this.description = description;
        this.regulationType = regulationType;
        this.effectiveDate = effectiveDate;
        this.appliesTo = appliesTo;
        this.status = status;
        this.createdAt = createdAt;
    }

    public String getRuleSummary() {
        return ruleSummary;
    }

    public void setRuleSummary(String ruleSummary) {
        this.ruleSummary = ruleSummary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRegulationType() {
        return regulationType;
    }

    public void setRegulationType(String regulationType) {
        this.regulationType = regulationType;
    }

    public LocalDate getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(LocalDate effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public ArrayList<String> getAppliesTo() {
        return appliesTo;
    }

    public void setAppliesTo(ArrayList<String> appliesTo) {
        this.appliesTo = appliesTo;
    }

    @Override
    public String toString() {
        return "Regulation{" +
                "ruleSummary='" + ruleSummary + '\'' +
                ", description='" + description + '\'' +
                ", regulationType='" + regulationType + '\'' +
                ", effectiveDate=" + effectiveDate +
                ", appliesTo=" + appliesTo +
                ", status='" + status + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
