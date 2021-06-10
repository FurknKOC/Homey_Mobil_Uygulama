package com.furkan.homey.repository;

import com.furkan.homey.model.entity.Tool;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ToolRepository extends JpaRepository<Tool, Long> {

    List<Tool> getAllByApartId(Long apartId);

}
