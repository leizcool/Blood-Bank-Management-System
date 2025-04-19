package com.anm.spring.restapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anm.spring.restapi.modal.BloodBank;
import com.anm.spring.restapi.service.BloodBankService;

@RestController
@RequestMapping("/bloodbanks")
public class BloodBankController {
	
    @Autowired
    private BloodBankService bloodbankService;

    @GetMapping
    public List<BloodBank> getAllBloodBanks() {
        return bloodbankService.getAllBloodBanks();
    }

    @PostMapping
    public BloodBank createBloodBank(@RequestBody BloodBank bloodbank) {
        return bloodbankService.saveBloodBank(bloodbank);
    }

    @PutMapping("/{id}")
    public BloodBank updateBloodBank(@PathVariable Long id, @RequestBody BloodBank bloodbank) {
        bloodbank.setId(id);
        return bloodbankService.updateBloodBank(bloodbank);
    }

    @DeleteMapping("/{id}")
    public void deleteBloodBank(@PathVariable Long id) {
        bloodbankService.deleteBloodBank(id);
    }	

}
