package com.springboot.dto;

import com.springboot.model.Staff;
import lombok.*;
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class StaffAvailable {
        private int noOfStaff;
        private String date;
        private String sections;
        private int staffId;
        private String staffName;
        private String department;
        private String shift;
        private String status;
        private String remarks;
        private String location;
        private int totalHours;
        private boolean isOnLeave;
        private String leaveReason;
        private String contactInfo;
        private String role;


}
