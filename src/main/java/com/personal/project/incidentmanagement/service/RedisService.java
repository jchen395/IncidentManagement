package com.personal.project.incidentmanagement.service;

import com.personal.project.incidentmanagement.model.Incident;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RedisService {
    private final RedisTemplate<String, Incident> redisTemplate;

    @Autowired
    public RedisService(RedisTemplate<String, Incident> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void save(String key, Incident value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public Incident get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void delete(String key) {
        redisTemplate.delete(key);
    }

    public boolean contains(String key) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(key));
    }

    public List<String> getAll() {
        List<String> values = new ArrayList<>();
        RedisConnection redisConnection = null;
        try {
            redisConnection = redisTemplate.getConnectionFactory().getConnection();
            ScanOptions options = ScanOptions.scanOptions().match("*").build();

            Cursor<byte[]> c = redisConnection.scan(options);
            while (c.hasNext()) {
                String key = new String(c.next());
                Incident incident = redisTemplate.opsForValue().get(key);
                if (incident == null) {
                    System.out.println("incident is null for incident " + key);
                } else {
                    values.add(incident.toString());
                    System.out.println("added incident " + key);

                }
            }
        } finally {
            redisConnection.close(); //Ensure closing this connection.
        }
        return values;
    }
}
