package com.anm.spring.restapi.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.anm.spring.restapi.modal.BloodStock;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class BloodStockService {
	
    private static final String BLOODSTOCK_JSON_FILE = "BloodStock.json";  // Path to the JSON file
    private final ObjectMapper objectMapper = new ObjectMapper();  // Jackson ObjectMapper for JSON handling

    // Retrieve all 
    public List<BloodStock> getAllBloodStocks() {
        List<BloodStock> bloodstocks = new ArrayList<>();  // Start with a new, empty list
       
        try {
            File file = new File(BLOODSTOCK_JSON_FILE);
            if (!file.exists()) {
                return new ArrayList<>();  // Return empty list if file doesn't exist
            }           
            return objectMapper.readValue(file, new TypeReference<List<BloodStock>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
        	return bloodstocks;  // Return the fresh data or empty list if no valid data
    }
    
	public BloodStock saveBloodStock(BloodStock bloodstock) {
		List<BloodStock> bloodstocks = getAllBloodStocks();  // Get current list of BloodStocks
	    bloodstocks.add(bloodstock);  // Add the new BloodStock to the list
	    writeBloodStocksToFile(bloodstocks);  // Write updated list to file
	    return bloodstock;
	}
   
    // Get a specific BloodStock by ID
	public BloodStock getBloodStockById(Long id) {
        return getAllBloodStocks().stream()
                .filter(bloodstock -> bloodstock.getId().equals(id))
                .findFirst()
                .orElse(null);  // Return null if not found
    }
 
    // Update an existing BloodStock
    public BloodStock updateBloodStock(BloodStock updatedBloodStock) {
        List<BloodStock> bloodstocks = getAllBloodStocks().stream()
                .map(bloodstock -> bloodstock.getId().equals(updatedBloodStock.getId()) ? updatedBloodStock : bloodstock)
                .collect(Collectors.toList());
        writeBloodStocksToFile(bloodstocks);  // Write updated list to file
        return updatedBloodStock;
    }
   
    // Delete a BloodStock by ID
    public void deleteBloodStock(Long id) {
        List<BloodStock> bloodstocks = getAllBloodStocks().stream()
                .filter(bloodstock -> !bloodstock.getId().equals(id))
                .collect(Collectors.toList());
        writeBloodStocksToFile(bloodstocks);  // Write updated list to file
    }
    
    // Helper method to write BloodStocks list to JSON file
    private void writeBloodStocksToFile(List<BloodStock> bloodstocks) {
        try {
            objectMapper.writeValue(new File(BLOODSTOCK_JSON_FILE), bloodstocks);
        } catch (IOException e) {
            e.printStackTrace();
        }
	
    }
    
    // Compatibility methods
    // Retrieve all BloodStocks for Thymeleaf
	  public List<BloodStock> findAll() { 
		  return getAllBloodStocks(); 
	  }
	  
    // Save method for compatibility
    public BloodStock save(BloodStock bloodstock) {
        return saveBloodStock(bloodstock);
    }

    // Delete method for compatibility
    public void deleteById(Long id) {
        deleteBloodStock(id);
    }

	public BloodStock findById(Long id) {
        //return getBloodStockById(id);  // Uses getBloodStockById method for finding a bloodstock by ID
	    return getAllBloodStocks().stream()
	            .filter(bloodstock -> bloodstock.getId() != null && bloodstock.getId().equals(id))
	            .findFirst()
	            .orElse(null);  // Return null if not found
	}
	
	public void reloadData() {
	    // No caching logic here, so no need to clear. Just a placeholder if you had any cache
	}
 	
}
