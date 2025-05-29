package com.springboot.serviceI;

import com.springboot.model.Staff;

import java.util.List;
import java.util.Optional;

public interface StafServiceI {
     List<Staff> getAllStaff();
     Optional<Staff> getStaffById(Long id);
     Staff addStaff(Staff staff);
     Staff updateStaff(Long id, Staff updatedStaff);
     void deleteStaff(Long id);
}
