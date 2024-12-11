package com.personal.project.incidentmanagement.service;

import com.personal.project.incidentmanagement.model.Incident;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class IncidentServiceTest {

    @Mock
    private RedisService redisService;

    @InjectMocks
    IncidentService incidentService;

    @Test
    void getAllIncident() {
        Incident incident1 = new Incident();
        incident1.setIncidentId("uid1");
        incident1.setIncidentTitle("title 1");
        incident1.setDescription("description 1");
        Incident incident2 = new Incident();
        incident2.setIncidentId("uid2");
        incident2.setIncidentTitle("title 2");
        incident2.setDescription("description 2");

        when(redisService.getAll()).thenReturn(List.of(incident1.toString(), incident2.toString()));
        List<String> res = incidentService.getAllIncident();
        assertEquals(2, res.size());
        assertTrue(res.get(0).contains("uid1"));
        assertTrue(res.get(1).contains("uid2"));

    }

    @Test
    void saveIncident_givenNewIncident() {
        Incident incident1 = new Incident();
        incident1.setIncidentId("uid1");
        incident1.setIncidentTitle("title 1");
        incident1.setDescription("description 1");

        when(redisService.contains("uid1")).thenReturn(false);
        incidentService.saveIncident(incident1);
        verify(redisService, times(1)).save(anyString(), any(Incident.class));
    }

    @Test
    void saveIncident_givenExistingIncident() {
        Incident incident1 = new Incident();
        incident1.setIncidentId("uid1");
        incident1.setIncidentTitle("title 1");
        incident1.setDescription("description 1");

        when(redisService.contains("uid1")).thenReturn(true);
        assertThrows(ResponseStatusException.class, () -> incidentService.saveIncident(incident1));
        verify(redisService, times(0)).save(anyString(), any(Incident.class));
    }

    @Test
    void updateIncident_givenExistingIncident() {
        Incident incident1 = new Incident();
        incident1.setIncidentId("uid1");
        incident1.setIncidentTitle("title 1");
        incident1.setDescription("description 1");

        when(redisService.contains("uid1")).thenReturn(true);
        incidentService.updateIncident(incident1);
        verify(redisService, times(1)).save(anyString(), any(Incident.class));
    }

    @Test
    void updateIncident_givenNonExistingIncident() {
        Incident incident1 = new Incident();
        incident1.setIncidentId("uid1");
        incident1.setIncidentTitle("title 1");
        incident1.setDescription("description 1");

        when(redisService.contains("uid1")).thenReturn(false);
        assertThrows(ResponseStatusException.class, () -> incidentService.updateIncident(incident1));
        verify(redisService, times(0)).save(anyString(), any(Incident.class));
    }

    @Test
    void deleteIncident_givenExistingIncident() {

        when(redisService.contains("uid1")).thenReturn(true);
        incidentService.deleteIncident("uid1");
        verify(redisService, times(1)).delete(anyString());

    }

    @Test
    void deleteIncident_givenNonExistingIncident() {

        when(redisService.contains("uid1")).thenReturn(false);
        assertThrows(ResponseStatusException.class, () -> incidentService.deleteIncident("uid1"));
        verify(redisService, times(0)).delete(anyString());
    }
}