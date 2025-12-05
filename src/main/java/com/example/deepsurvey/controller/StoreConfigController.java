package com.example.deepsurvey.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.deepsurvey.model.StoreConfig;
import com.example.deepsurvey.repository.StoreConfigRepository;

import java.util.List;

@RestController
@RequestMapping("/store-config")
public class StoreConfigController {

    @Autowired
    private StoreConfigRepository repo;

    @GetMapping
    public StoreConfig getConfig() {
        List<StoreConfig> list = repo.findAll();
        if (list.isEmpty()) {
            StoreConfig cfg = new StoreConfig();
            repo.save(cfg);
            return cfg;
        }
        return list.get(0);
    }

    @PutMapping("/{id}")
    public StoreConfig update(@PathVariable Long id, @RequestBody StoreConfig updated) {
        updated.setId(id);
        return repo.save(updated);
    }
}