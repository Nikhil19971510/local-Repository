package com.springboot.controller;

import com.springboot.dto.StaffAvailable;
import com.springboot.model.Staff;
import com.springboot.serviceI.StafServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class StaffController {


    @Autowired
    private StafServiceI stafServiceI;

    @GetMapping
    public List<Staff> getAllStaff() {
        return stafServiceI.getAllStaff();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Staff> getStaffById(@PathVariable Long id) {
        return stafServiceI.getStaffById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Staff addStaff(@RequestBody Staff staff) {
        return stafServiceI.addStaff(staff);
    }

    @PutMapping("/{id}")
    public StaffAvailable updateStaff(@PathVariable Long id, @RequestBody StaffAvailable staff) {
        return stafServiceI.updateStaff(id, staff);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStaff(@PathVariable Long id) {
        stafServiceI.deleteStaff(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("currentStaff/{date}")
    public ResponseEntity<Map<String, List<Map<String, Object>>>> staffAvailability(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") String date) {

        try {
            return stafServiceI.staffAvailability(date);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.noContent().build();
        }

    }

}
