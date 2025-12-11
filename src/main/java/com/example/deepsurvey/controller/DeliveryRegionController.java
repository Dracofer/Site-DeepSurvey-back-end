package com.example.deepsurvey.controller;

import com.example.deepsurvey.model.DeliveryRegion;
import com.example.deepsurvey.repository.DeliveryRegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/delivery-regions")
public class DeliveryRegionController {

    @Autowired
    private DeliveryRegionRepository repo;

    @GetMapping
    public List<DeliveryRegion> list() {
        return repo.findAll();
    }

    @PostMapping
    public DeliveryRegion create(@RequestBody DeliveryRegion r) {
        r.setId(null);
        if (r.getActive() == null) r.setActive(true);
        return repo.save(r);
    }

    @PutMapping("/{id}")
    public DeliveryRegion update(@PathVariable Long id, @RequestBody DeliveryRegion r) {
        r.setId(id);
        return repo.save(r);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}