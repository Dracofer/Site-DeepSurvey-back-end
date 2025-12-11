package com.example.deepsurvey.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.deepsurvey.model.DeliveryRegion;

public interface DeliveryRegionRepository extends JpaRepository<DeliveryRegion, Long> {
}