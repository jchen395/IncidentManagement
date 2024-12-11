package com.personal.project.incidentmanagement.service;

import com.personal.project.incidentmanagement.model.Incident;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class IncidentService {
    @Autowired
    private RedisService redisService;

    public List<String> getAllIncident() {
        List<String> res = redisService.getAll();
        System.out.println("found incident number " + res.size());
        return res;
    }

    public void saveIncident(Incident incident) {
        redisService.save(incident.getIncidentId(), incident);
        System.out.println("Successfully saved incident " + incident.getIncidentId());
    }

    public void updateIncident(Incident incident) {
        if (redisService.contains(incident.getIncidentId())) {
            redisService.save(incident.getIncidentId(), incident);
            System.out.println("Successfully updated incident " + incident.getIncidentId());

        } else {
            System.out.println("Incident " + incident.getIncidentId() + " not found");

        }
    }

    public void deleteIncident(String incidentId) {
        if (redisService.contains(incidentId)) {
            redisService.delete(incidentId);
        } else {
            System.out.println("Incident " + incidentId + " not found");
        }
    }
}
