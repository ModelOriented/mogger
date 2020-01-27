package com.pw.mini.mogger.dataset.control.repository;

import com.pw.mini.mogger.dataset.entity.DatasetEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DatasetRepository extends JpaRepository<DatasetEntity, Long> {

    Page<DatasetEntity> findAll(Pageable pageable);

    List<DatasetEntity> findAllByTaskName(String taskName);
}
