package com.anm.spring.frontend.controller;

import com.anm.spring.restapi.modal.BloodBank;
import com.anm.spring.restapi.modal.BloodStock;
import com.anm.spring.restapi.modal.Seeker;
import com.anm.spring.restapi.service.BloodBankService;
import com.anm.spring.restapi.service.BloodStockService;
import com.anm.spring.restapi.service.SeekerService;
import com.anm.spring.frontend.ConvertJavaObjtoJson;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/")
public class AppController {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(AppController.class);
    
    @Autowired
    private BloodBankService bloodBankService;

    @Autowired
    private BloodStockService bloodStockService;

    @Autowired
    private SeekerService seekerService;

    @Autowired
    private ConvertJavaObjtoJson convertJavaObjtoJson;

    // Serve index page
    @GetMapping("/")
    public String showIndex() {
        return "index";
    }

    // CRUD endpoints for Seeker  
    @GetMapping("/seekers")
    public String getAllSeekers(org.springframework.ui.Model model) {
        List<Seeker> seekers = seekerService.getAllSeekers();  // Always get the latest data
        model.addAttribute("seekers", seekerService.getAllSeekers());  // Add seekers list to model
        return "seekers";  // Return the name of the Thymeleaf template
    }

    @PostMapping("/seekers")
    public String createSeeker(@ModelAttribute Seeker seeker) {
        // Auto-generate the id if it's not provided
        if (seeker.getId() == null) {
            seeker.setId(System.currentTimeMillis());  // Use current time as unique id for simplicity
        }
        
        // Make sure gender and other fields are not null before saving
        if (seeker.getGender() == null || seeker.getGender().isEmpty()) {
            seeker.setGender("Unknown");  // Set default gender if not provided
        }
        
        Seeker saveSeeker = seekerService.save(seeker);
        convertJavaObjtoJson.writeToJson(seekerService.findAll(), "Seekers.json");  //*** dups Seeker.json changed last 11:12pm
        return "redirect:/seekers";  // Redirect to the list page after saving
    }
    // Load edit page for a seeker
    @GetMapping("/seekers/edit/{id}")
    public String editSeeker(@PathVariable Long id, org.springframework.ui.Model model) {
        try {
            Seeker seeker = seekerService.findById(id);  // findById should return Seeker
            if (seeker != null) {
                model.addAttribute("seeker", seeker);
                return "editseeker";  // Load the editseeker.html page
            } else {
                model.addAttribute("errorMessage", "Seeker with ID " + id + " not found.");
                return "error";  // Display an error page if Seeker not found
            }
        } catch (Exception e) {
            logger.error("Error fetching Seeker with ID: " + id, e);
            model.addAttribute("errorMessage", "An error occurred while fetching the seeker.");
            return "error";  // Error page if something went wrong
        }
    }

    // Update Seeker with POST instead of PUT for compatibility with HTML forms
    @PostMapping("/seekers/update/{id}")
    public String updateSeeker(@PathVariable Long id, @ModelAttribute Seeker seeker) {
        seeker.setId(id);  // Make sure the id is set when updating
        Seeker updatedSeeker = seekerService.updateSeeker(seeker);
        convertJavaObjtoJson.writeToJson(seekerService.findAll(), "Seekers.json");
        return "redirect:/seekers";
    }

    // Delete Seeker
    @GetMapping("/seekers/delete/{id}")
    public String deleteSeeker(@PathVariable Long id) {
        seekerService.deleteById(id);
        convertJavaObjtoJson.writeToJson(seekerService.findAll(), "Seekers.json");
        return "redirect:/seekers";
    }
    
    // CRUD endpoints for BloodBank
    @GetMapping("/bloodbanks")
    public String getAllBloodBanks(org.springframework.ui.Model model) {
        List<BloodBank> bloodbanks = bloodBankService.getAllBloodBanks();  // Always get the latest data
        model.addAttribute("bloodbanks", bloodbanks);  // Add list to model
        return "bloodbanks";  // Return the name of the Thymeleaf template
    }

    @PostMapping("/bloodbanks")
    public String createBloodBank(@ModelAttribute BloodBank bloodbank) {
        if (bloodbank.getId() == null) {
        	bloodbank.setId(System.currentTimeMillis());  // Use current time as unique id for simplicity
        }
        BloodBank saveBloodBank = bloodBankService.save(bloodbank);
        convertJavaObjtoJson.writeToJson(bloodBankService.findAll(), "BloodBank.json");
        return "redirect:/bloodbanks";  // Redirect to the list page after saving
    }
    // Load edit page for a bloodbank
    @GetMapping("/bloodbanks/edit/{id}")
    public String editBloodBank(@PathVariable Long id, org.springframework.ui.Model model) {
        try {
        	BloodBank bloodbank = bloodBankService.findById(id);  // findById should return BloodBank
            if (bloodbank != null) {
                model.addAttribute("bloodbank", bloodbank);
                return "editbloodbank";  // Load the editbloodbank.html page
            } else {
                model.addAttribute("errorMessage", "BloodBank with ID " + id + " not found.");
                return "error";  // Display an error page if BloodBank not found
            }
        } catch (Exception e) {
            logger.error("Error fetching BloodBank with ID: " + id, e);
            model.addAttribute("errorMessage", "An error occurred while fetching the bloodbank.");
            return "error";  // Error page if something went wrong
        }
    }

    // Update BloodBank with POST instead of PUT for compatibility with HTML forms
    @PostMapping("/bloodbanks/update/{id}")
    public String updateBloodBank(@PathVariable Long id, @ModelAttribute BloodBank bloodbank) {
    	bloodbank.setId(id);  // Make sure the id is set when updating
    	BloodBank updatedBloodBank = bloodBankService.updateBloodBank(bloodbank);
        convertJavaObjtoJson.writeToJson(bloodBankService.findAll(), "BloodBank.json");
        return "redirect:/bloodbanks";
    }
    
    // Delete Blood Bank
    @GetMapping("/bloodbanks/delete/{id}")
    public String deleteBloodBank(@PathVariable Long id) {
        bloodBankService.deleteById(id);
        convertJavaObjtoJson.writeToJson(bloodBankService.findAll(), "BloodBank.json");
        return "redirect:/bloodbanks";  // Redirect to the list page after deletion
    }

    // CRUD endpoints for BloodStock
    @GetMapping("/bloodstocks")
    public String getAllBloodStocks(org.springframework.ui.Model model) {
        List<BloodStock> bloodstocks = bloodStockService.getAllBloodStocks();  // Always get the latest data
        //logger.info("Fetching all bloodstocks, count: " + bloodstocks.size());     
        model.addAttribute("bloodstocks", bloodstocks);  // Add list to model
        return "bloodstocks";  // Return the name of the Thymeleaf template
    }

    @PostMapping("/bloodstocks")
    public String createBloodStock(@ModelAttribute BloodStock bloodstock) {
        if (bloodstock.getId() == null) {
        	bloodstock.setId(System.currentTimeMillis());  // Use current time as unique id for simplicity
        }
        BloodStock savedBloodStock = bloodStockService.save(bloodstock);
        convertJavaObjtoJson.writeToJson(bloodStockService.findAll(), "BloodStock.json");   	
        //logger.info("Saved new BloodStock with ID: " + savedBloodStock.getId());
        return "redirect:/bloodstocks";  // Redirect to the list page after saving
    }
    
    // Load edit page for a bloodstock
    @GetMapping("/bloodstocks/edit/{id}")
    public String editBloodStock(@PathVariable Long id, org.springframework.ui.Model model) {
        try {
        	BloodStock bloodstock = bloodStockService.findById(id);  // findById should return BloodStock
            if (bloodstock != null) {
                model.addAttribute("bloodstock", bloodstock);
                return "editbloodstock";  // Load the editbloodstock.html page
            } else {
                model.addAttribute("errorMessage", "BloodStock with ID " + id + " not found.");
                return "error";  // Display an error page if BloodStock not found
            }
        } catch (Exception e) {
            logger.error("Error fetching BloodStock with ID: " + id, e);
            model.addAttribute("errorMessage", "An error occurred while fetching the bloodstock.");
            return "error";  // Error page if something went wrong
        }
    }

    // Update BloodStock with POST instead of PUT for compatibility with HTML forms
    @PostMapping("/bloodstocks/update/{id}")
    public String updateBloodStock(@PathVariable Long id, @ModelAttribute BloodStock bloodstock) {
    	bloodstock.setId(id);  // Make sure the id is set when updating
    	BloodStock updatedBloodStock = bloodStockService.updateBloodStock(bloodstock);
        convertJavaObjtoJson.writeToJson(bloodStockService.findAll(), "BloodStock.json");
        return "redirect:/bloodstocks";
    }
    
    //Delete Blood Stock
    @GetMapping("/bloodstocks/delete/{id}")
    public String deleteBloodStock(@PathVariable Long id) {
        bloodStockService.deleteById(id);
        convertJavaObjtoJson.writeToJson(bloodStockService.findAll(), "BloodStock.json");
        return "redirect:/bloodstocks";  // Redirect to the list page after deletion
    }
    
}
