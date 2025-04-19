package com.anm.spring.restapi.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.anm.spring.restapi.modal.BloodBank;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class BloodBankService {
	
    private static final String BLOODBANK_JSON_FILE = "BloodBank.json";  // Path to the JSON file
    private final ObjectMapper objectMapper = new ObjectMapper();  // Jackson ObjectMapper for JSON handling

    // Retrieve all 
    public List<BloodBank> getAllBloodBanks() {
        List<BloodBank> bloodbanks = new ArrayList<>();  // Start with a new, empty list
       
        try {
            File file = new File(BLOODBANK_JSON_FILE);
            if (!file.exists()) {
                return new ArrayList<>();  // Return empty list if file doesn't exist
            }           
            return objectMapper.readValue(file, new TypeReference<List<BloodBank>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
        	return bloodbanks;  // Return the fresh data or empty list if no valid data
    }
    
	public BloodBank saveBloodBank(BloodBank bloodbank) {
		List<BloodBank> bloodbanks = getAllBloodBanks();  // Get current list of BloodBanks
	    bloodbanks.add(bloodbank);  // Add the new BloodBank to the list
	    writeBloodBanksToFile(bloodbanks);  // Write updated list to file
	    return bloodbank;
	}
   
    // Get a specific BloodBank by ID
	public BloodBank getBloodBankById(Long id) {
        return getAllBloodBanks().stream()
                .filter(bloodbank -> bloodbank.getId().equals(id))
                .findFirst()
                .orElse(null);  // Return null if not found
    }
 
    // Update an existing BloodBank
    public BloodBank updateBloodBank(BloodBank updatedBloodBank) {
        List<BloodBank> bloodbanks = getAllBloodBanks().stream()
                .map(bloodbank -> bloodbank.getId().equals(updatedBloodBank.getId()) ? updatedBloodBank : bloodbank)
                .collect(Collectors.toList());
        writeBloodBanksToFile(bloodbanks);  // Write updated list to file
        return updatedBloodBank;
    }
   
    // Delete a BloodBank by ID
    public void deleteBloodBank(Long id) {
        List<BloodBank> bloodbanks = getAllBloodBanks().stream()
                .filter(bloodbank -> !bloodbank.getId().equals(id))
                .collect(Collectors.toList());
        writeBloodBanksToFile(bloodbanks);  // Write updated list to file
    }
    
    // Helper method to write BloodBanks list to JSON file
    private void writeBloodBanksToFile(List<BloodBank> bloodbanks) {
        try {
            objectMapper.writeValue(new File(BLOODBANK_JSON_FILE), bloodbanks);
        } catch (IOException e) {
            e.printStackTrace();
        }
	
    }
    
    // Compatibility methods
    // Retrieve all BloodBanks for Thymeleaf
    public List<BloodBank> findAll() {
        return getAllBloodBanks();
    }

    // Save method for compatibility
    public BloodBank save(BloodBank bloodbank) {
        return saveBloodBank(bloodbank);
    }

    // Delete method for compatibility
    public void deleteById(Long id) {
        deleteBloodBank(id);
    }

	public BloodBank findById(Long id) {
        //return getBloodBankById(id);  // Uses getBloodBankById method for finding a bloodbank by ID
	    return getAllBloodBanks().stream()
	            .filter(bloodbank -> bloodbank.getId() != null && bloodbank.getId().equals(id))
	            .findFirst()
	            .orElse(null);  // Return null if not found
	}
	
	public void reloadData() {
	    // No caching logic here, so no need to clear. Just a placeholder if you had any cache
	}
 
}
