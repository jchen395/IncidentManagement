package com.personal.project.incidentmanagement.service;

import com.personal.project.incidentmanagement.model.Incident;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class IncidentService {
    Map<String, Incident> cache = new HashMap<>();

    public List<String> getAllIncident() {
        return cache.values().stream().map(Incident::toString).toList();
    }

    public void saveIncident(Incident incident) {
        cache.put(incident.getIncidentId(), incident);
        System.out.println("Successfully saved incident " + incident.getIncidentId());
    }

    public void updateIncident(Incident incident) {
        if (cache.containsKey(incident.getIncidentId())) {
            cache.put(incident.getIncidentId(), incident);
            System.out.println("Successfully updated incident " + incident.getIncidentId());
        } else {
            System.out.println("Incident " + incident.getIncidentId() + " not found");
        }
    }

    public void deleteIncident(String incidentId) {
        cache.remove(incidentId);
        System.out.println("Successfully deleted incident " + incidentId);
    }
}
