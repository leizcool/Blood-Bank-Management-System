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

import com.anm.spring.restapi.modal.Seeker;
import com.anm.spring.restapi.service.SeekerService;

@RestController
@RequestMapping("/seekers")
public class SeekerController {
	
    @Autowired
    private SeekerService seekerService;
    
    @GetMapping
    public List<Seeker> getAllSeekers() {
        return seekerService.getAllSeekers();
    }

    @PostMapping
    public Seeker createSeeker(@RequestBody Seeker seeker) {
        return seekerService.saveSeeker(seeker);
    }

    @PutMapping("/{id}")
    public Seeker updateSeeker(@PathVariable Long id, @RequestBody Seeker seeker) {
        seeker.setId(id);
        return seekerService.updateSeeker(seeker);
    }

    @DeleteMapping("/{id}")
    public void deleteSeeker(@PathVariable Long id) {
        seekerService.deleteSeeker(id);
    }
}
