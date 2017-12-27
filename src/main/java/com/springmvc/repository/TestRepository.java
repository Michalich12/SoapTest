package com.springmvc.repository;

import com.springmvc.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Igor on 27.12.2017.
 */
public interface TestRepository extends JpaRepository<Integer, Test> {
}
