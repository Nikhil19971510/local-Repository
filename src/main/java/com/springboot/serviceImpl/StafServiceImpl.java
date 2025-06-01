package com.springboot.serviceImpl;

import com.springboot.dto.StaffAvailable;
import com.springboot.exceptions.StaffNotFoundException;
import com.springboot.model.Staff;
import com.springboot.repo.StaffRepository;
import com.springboot.serviceI.StafServiceI;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class StaffServiceImpl implements StafServiceI {

    @Autowired
    private StaffRepository staffRepository;

    @Override
    public List<Staff> getAllStaff() {
        return staffRepository.findAll();
    }

    @Override
    public Optional<Staff> getStaffById(Long id) {
        return staffRepository.findById(id);
    }

    @Override
    public Staff addStaff(Staff staff) {
        return staffRepository.save(staff);
    }

    @Override
    public StaffAvailable updateStaff(Long id, StaffAvailable updatedStaff) {
        return staffRepository.findById(id)
                .map(staff -> {
                    if (updatedStaff.getNoOfStaff() != 0) staff.setNoOfStaff(updatedStaff.getNoOfStaff());
                    if (updatedStaff.getDate() != null) staff.setDate(updatedStaff.getDate());
                    if (updatedStaff.getSections() != null) staff.setSections(updatedStaff.getSections());
                    if (updatedStaff.getStaffName() != null) staff.setStaffName(updatedStaff.getStaffName());
                    if (updatedStaff.getDepartment() != null) staff.setDepartment(updatedStaff.getDepartment());
                    if (updatedStaff.getShift() != null) staff.setShift(updatedStaff.getShift());
                    if (updatedStaff.getStatus() != null) staff.setStatus(updatedStaff.getStatus());
                    if (updatedStaff.getRemarks() != null) staff.setRemarks(updatedStaff.getRemarks());
                    if (updatedStaff.getLocation() != null) staff.setLocation(updatedStaff.getLocation());
                    if (updatedStaff.getTotalHours() != 0) staff.setTotalHours(updatedStaff.getTotalHours());
                    staff.setOnLeave(updatedStaff.isOnLeave());
                    if (updatedStaff.getLeaveReason() != null) staff.setLeaveReason(updatedStaff.getLeaveReason());
                    if (updatedStaff.getContactInfo() != null) staff.setContactInfo(updatedStaff.getContactInfo());
                    if (updatedStaff.getRole() != null) staff.setRole(updatedStaff.getRole());

                    Staff staffDetailsUpdate = staffRepository.save(staff);
                    return convertToStaffAvailable(staffDetailsUpdate);
                }).orElseThrow(() -> new StaffNotFoundException("Staff with ID " + id + " not found"));
    }

    @Override
    public void deleteStaff(Long id) {
        staffRepository.deleteById(id);
    }

    private StaffAvailable convertToStaffAvailable(Staff staff) {
        StaffAvailable staffAvailable = new StaffAvailable();
        staffAvailable.setStaffId(staff.getStaffId());
        staffAvailable.setStaffName(staff.getStaffName());
        staffAvailable.setRole(staff.getRole());
        staffAvailable.setContactInfo(staff.getContactInfo());
        staffAvailable.setOnLeave(staff.isOnLeave());
        staffAvailable.setLeaveReason(staff.getLeaveReason());
        return staffAvailable;
    }

    @Override
    public ResponseEntity<Map<String, List<Map<String, Object>>>> staffAvailability(String date) {

        List<Staff> currentDateStaffAvailable = staffRepository.findByDate(date);

        List<StaffAvailable> staffAvailableList = currentDateStaffAvailable.stream()
                .map(staff -> {
                    StaffAvailable staffAvailable = new StaffAvailable();
                    try {
                        BeanUtils.copyProperties(staffAvailable, staff);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return staffAvailable;
                }).collect(Collectors.toList());

        if (currentDateStaffAvailable.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        // Using Streams to group staff by department
        Map<String, List<Map<String, Object>>> departmentWiseAvailability = staffAvailableList.stream()
                .collect(Collectors.groupingBy(
                        StaffAvailable::getDepartment,
                        Collectors.mapping(staff -> {
                            Map<String, Object> staffDetails = new HashMap<>();
                            staffDetails.put("name", staff.getStaffName());
                            staffDetails.put("role", staff.getRole());
                            staffDetails.put("status", staff.isOnLeave() ? "On Leave" : "Available");

                            if (staff.isOnLeave()) {
                                staffDetails.put("leaveReason", staff.getLeaveReason());
                            }

                            return staffDetails;
                        }, Collectors.toList()))
                );

        return ResponseEntity.ok(departmentWiseAvailability);
    }
}
}
