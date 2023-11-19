package com.jarek.roomoccupancyopt.load;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.jarek.roomoccupancyopt.repository.jpa.JpaGuestRepository;
import com.jarek.roomoccupancyopt.repository.jpa.model.GuestEntity;
import jakarta.transaction.Transactional;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.util.List;
import java.util.UUID;

import static java.nio.charset.StandardCharsets.UTF_8;

@Component
public class TestDataLoader {

    private final JpaGuestRepository guestRepository;

    public TestDataLoader(JpaGuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    @Transactional
    public void load() throws JsonProcessingException {
        String jsonString = readFileToString("test_data.json");
        List<Integer> guestBudgets = new ObjectMapper().readValue(jsonString, TypeFactory.defaultInstance().constructCollectionType(List.class, Integer.class));

        List<GuestEntity> guests = guestBudgets.stream().map(budget -> new GuestEntity(UUID.randomUUID().toString(), budget)).toList();
        guestRepository.saveAll(guests);
    }

    private static String readFileToString(String path) {
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource(path);
        return asString(resource);
    }

    private static String asString(Resource resource) {
        try (Reader reader = new InputStreamReader(resource.getInputStream(), UTF_8)) {
            return FileCopyUtils.copyToString(reader);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
