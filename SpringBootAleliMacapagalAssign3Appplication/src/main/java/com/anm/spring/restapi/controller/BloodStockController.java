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

import com.anm.spring.restapi.modal.BloodStock;
import com.anm.spring.restapi.service.BloodStockService;

@RestController
@RequestMapping("/bloodstocks")
public class BloodStockController {
	
    @Autowired
    private BloodStockService bloodstockService;

    @GetMapping
    public List<BloodStock> getAllBloodStocks() {
        return bloodstockService.getAllBloodStocks();
    }

    @PostMapping
    public BloodStock createBloodStock(@RequestBody BloodStock bloodstock) {
        return bloodstockService.saveBloodStock(bloodstock);
    }

    @PutMapping("/{id}")
    public BloodStock updateBloodStock(@PathVariable Long id, @RequestBody BloodStock bloodstock) {
    	bloodstock.setId(id);
        return bloodstockService.updateBloodStock(bloodstock);
    }

    @DeleteMapping("/{id}")
    public void deleteBloodStock(@PathVariable Long id) {
    	bloodstockService.deleteBloodStock(id);
    }
}
