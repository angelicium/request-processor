package com.request_processor.repository;

import com.request_processor.entity.RequestProcessor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface RequestProcessorRepository extends JpaRepository<RequestProcessor, UUID> {

    List<RequestProcessor> findTop50BySentFalseOrderByCreatedAtAsc();
}
