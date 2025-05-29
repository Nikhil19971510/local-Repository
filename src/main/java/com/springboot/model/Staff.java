package com.springboot.model;

import com.springboot.common.EntityIdentity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "staff")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Staff extends EntityIdentity {


    @Column(name = "staffname")
    private String name;
    @Column(name = "role")
    private String role;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private String phone;
    @Column(name = "active")
    private boolean active;
}
