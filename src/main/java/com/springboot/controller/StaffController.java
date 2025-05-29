package com.springboot.controller;

import com.springboot.model.Staff;
import com.springboot.serviceI.StafServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public Staff updateStaff(@PathVariable Long id, @RequestBody Staff staff) {
        return stafServiceI.updateStaff(id, staff);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStaff(@PathVariable Long id) {
        stafServiceI.deleteStaff(id);
        return ResponseEntity.noContent().build();
    }

}
