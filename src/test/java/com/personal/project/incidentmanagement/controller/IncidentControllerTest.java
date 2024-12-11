package com.personal.project.incidentmanagement.controller;

import com.personal.project.incidentmanagement.model.Incident;
import com.personal.project.incidentmanagement.service.IncidentService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IncidentControllerTest {
    @Mock
    IncidentService incidentService;

    @InjectMocks
    private IncidentController incidentController;

    @Test
    void allIncident() {
        Incident incident1 = new Incident();
        incident1.setIncidentId("uid1");
        incident1.setIncidentTitle("title 1");
        incident1.setDescription("description 1");
        Incident incident2 = new Incident();
        incident2.setIncidentId("uid2");
        incident2.setIncidentTitle("title 2");
        incident2.setDescription("description 2");
        when(incidentService.getAllIncident()).thenReturn(List.of(incident1.toString(), incident2.toString()));
        List<String> res = incidentController.allIncident();
        assertEquals(2, res.size());
        assertTrue(res.get(0).contains("uid1"));
        assertTrue(res.get(1).contains("uid2"));
    }

    @Test
    void createIncident() {
        Incident incident1 = new Incident();
        incident1.setIncidentId("uid1");
        incident1.setIncidentTitle("title 1");
        incident1.setDescription("description 1");
        assertDoesNotThrow(() -> incidentController.createIncident(incident1));
    }

    @Test
    void deleteIncident() {
        assertDoesNotThrow(() -> incidentController.deleteIncident("uid1"));
    }

    @Test
    void updateIncident() {
        Incident incident1 = new Incident();
        incident1.setIncidentId("uid1");
        incident1.setIncidentTitle("title 1");
        incident1.setDescription("description 1");
        assertDoesNotThrow(() -> incidentController.updateIncident(incident1));

    }

}