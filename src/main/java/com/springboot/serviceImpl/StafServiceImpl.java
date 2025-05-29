package com.springboot.serviceImpl;

import com.springboot.model.Staff;
import com.springboot.repo.StaffRepository;
import com.springboot.serviceI.StafServiceI;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StafServiceImpl implements StafServiceI {

    @Autowired
    private StaffRepository repository;
    @Override
    public List<Staff> getAllStaff() {
        return repository.findAll();
    }
    @Override
    public Optional<Staff> getStaffById(Long id) {
        return repository.findById(id);
    }
    @Override
    public Staff addStaff(Staff staff) {
        return repository.save(staff);
    }
@Override
    public Staff updateStaff(Long id, Staff updatedStaff) {
        return repository.findById(id)
                .map(staff -> {
                    staff.setName(updatedStaff.getName());
                    staff.setRole(updatedStaff.getRole());
                    staff.setEmail(updatedStaff.getEmail());
                    staff.setPhone(updatedStaff.getPhone());
                    staff.setActive(updatedStaff.isActive());
                    return repository.save(staff);
                }).orElseThrow(() -> new RuntimeException("Staff not found"));
    }
    @Override
    public void deleteStaff(Long id) {
        repository.deleteById(id);
    }
}
