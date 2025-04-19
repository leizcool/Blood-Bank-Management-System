package com.anm.spring.restapi.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.anm.spring.restapi.modal.Seeker;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class SeekerService {

    private static final String SEEKER_JSON_FILE = "Seekers.json";  // Path to the JSON file
    private final ObjectMapper objectMapper = new ObjectMapper();  // Jackson ObjectMapper for JSON handling
     
    // Save or create a new Seeker
    public Seeker saveSeeker(Seeker seeker) {
        List<Seeker> seekers = getAllSeekers();  // Get current list of Seekers
        seekers.add(seeker);  // Add the new Seeker to the list
        writeSeekersToFile(seekers);  // Write updated list to file
        return seeker;
    }

    // Retrieve all Seekers
    public List<Seeker> getAllSeekers() {
        List<Seeker> seekers = new ArrayList<>();  // Start with a new, empty list
       
        try {
            File file = new File(SEEKER_JSON_FILE);
            if (!file.exists()) {
                return new ArrayList<>();  // Return empty list if file doesn't exist
            }           
            return objectMapper.readValue(file, new TypeReference<List<Seeker>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
        	return seekers;  // Return the fresh data or empty list if no valid data
    }

    // Get a specific Seeker by ID
    public Seeker getSeekerById(Long id) {
        return getAllSeekers().stream()
                .filter(seeker -> seeker.getId().equals(id))
                .findFirst()
                .orElse(null);  // Return null if not found
    }

    // Update an existing Seeker
    public Seeker updateSeeker(Seeker updatedSeeker) {
        List<Seeker> seekers = getAllSeekers().stream()
                .map(seeker -> seeker.getId().equals(updatedSeeker.getId()) ? updatedSeeker : seeker)
                .collect(Collectors.toList());
        writeSeekersToFile(seekers);  // Write updated list to file
        return updatedSeeker;
    }

    // Delete a Seeker by ID
    public void deleteSeeker(Long id) {
        List<Seeker> seekers = getAllSeekers().stream()
                .filter(seeker -> !seeker.getId().equals(id))
                .collect(Collectors.toList());
        writeSeekersToFile(seekers);  // Write updated list to file
    }

    // Helper method to write Seekers list to JSON file
    private void writeSeekersToFile(List<Seeker> seekers) {
        try {
            objectMapper.writeValue(new File(SEEKER_JSON_FILE), seekers);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Compatibility methods
    // Retrieve all Seekers for Thymeleaf
    public List<Seeker> findAll() {
        return getAllSeekers();
    }

    // Save method for compatibility
    public Seeker save(Seeker seeker) {
        return saveSeeker(seeker);
    }

    // Delete method for compatibility
    public void deleteById(Long id) {
        deleteSeeker(id);
    }

	public Seeker findById(Long id) {
        //return getSeekerById(id);  // Uses getSeekerById method for finding a seeker by ID
	    return getAllSeekers().stream()
	            .filter(seeker -> seeker.getId() != null && seeker.getId().equals(id))
	            .findFirst()
	            .orElse(null);  // Return null if not found
	}
	
	public void reloadData() {
	    // No caching logic here, so no need to clear. Just a placeholder if you had any cache
	}

}
