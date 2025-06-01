package com.springboot.serviceI;

import com.springboot.dto.StaffAvailable;
import com.springboot.model.Staff;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface StafServiceI {
     List<Staff> getAllStaff();
     Optional<Staff> getStaffById(Long id);
     Staff addStaff(Staff staff);
     public StaffAvailable updateStaff(Long id, StaffAvailable updatedStaff);
     void deleteStaff(Long id);

     ResponseEntity<Map<String, List<Map<String, Object>>>>  staffAvailability(String date);
}
