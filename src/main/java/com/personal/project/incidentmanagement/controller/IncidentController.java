package com.personal.project.incidentmanagement.controller;


import com.personal.project.incidentmanagement.model.Incident;
import com.personal.project.incidentmanagement.service.IncidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class IncidentController {

    @Autowired
    IncidentService incidentService;

    @GetMapping("/all")
    public List<String> allIncident(){
        return incidentService.getAllIncident();
    }

    @PostMapping("/create")
    public void createIncident(@RequestBody Incident incident) {
        incidentService.saveIncident(incident);
    }

    @DeleteMapping("/delete/{incidentId}")
    public void deleteIncident(@PathVariable String incidentId) {
        incidentService.deleteIncident(incidentId);
    }

    @PutMapping("/update")
    public void updateIncident(@RequestBody Incident incident) {
        incidentService.updateIncident(incident);
    }
}
