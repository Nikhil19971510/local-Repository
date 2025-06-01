package com.springboot.model;

import com.springboot.common.EntityIdentity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "staff")
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Staff extends EntityIdentity {

    @Column(name = "noOfStaff")
    private int noOfStaff;

    @Column(name = "date")
    private String date;

    @Column(name = "sections")
    private String sections;

    @Column(name = "staffId")
    private int staffId;

    @Column(name = "staffName")
    private String staffName;

    @Column(name = "department")
    private String department;

    @Column(name = "shift")
    private String shift;

    @Column(name = "status")
    private String status;

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "location")
    private String location;

    @Column(name = "totalHours")
    private int totalHours;

    @Column(name = "isOnLeave")
    private boolean isOnLeave;

    @Column(name = "leaveReason")
    private String leaveReason;

    @Column(name = "contactInfo")
    private String contactInfo;

    @Column(name = "role")
    private String role;


}
