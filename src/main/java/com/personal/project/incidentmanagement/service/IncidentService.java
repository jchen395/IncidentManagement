package com.personal.project.incidentmanagement.service;

import com.personal.project.incidentmanagement.model.Incident;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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
        if (redisService.contains(incident.getIncidentId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Incident " + incident.getIncidentId() + " not found");
        }

        redisService.save(incident.getIncidentId(), incident);
        System.out.println("Successfully saved incident " + incident.getIncidentId());
    }

    public void updateIncident(Incident incident) {
        if (!redisService.contains(incident.getIncidentId())) {
            System.out.println("Incident " + incident.getIncidentId() + " not found");
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Incident " + incident.getIncidentId() + " not found"
            );
        }

        redisService.save(incident.getIncidentId(), incident);
        System.out.println("Successfully updated incident " + incident.getIncidentId());
    }

    public void deleteIncident(String incidentId) {
        if (!redisService.contains(incidentId)) {
            System.out.println("Incident " + incidentId + " not found");
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Incident " + incidentId + " not found"
            );
        }

        redisService.delete(incidentId);
    }
}
