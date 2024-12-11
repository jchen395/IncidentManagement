package com.personal.project.incidentmanagement.model;

import java.io.Serializable;

public class Incident implements Serializable {
    String incidentId;
    String incidentTitle;
    String description;

    public String getDescription() {
        return description;
    }

    public String getIncidentTitle() {
        return incidentTitle;
    }

    public String getIncidentId() {
        return incidentId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIncidentTitle(String incidentTitle) {
        this.incidentTitle = incidentTitle;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    @Override
    public String toString() {
        return "Incident{" +
                "incidentId='" + incidentId + '\'' +
                ", incidentTitle='" + incidentTitle + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
