package com.springboot.repo;


import com.springboot.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StaffRepository extends JpaRepository<Staff, Long> {
    List<Staff> findByDate(String currentDate);
}
