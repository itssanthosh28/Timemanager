package com.example.time_management_system.entity;

import com.example.time_management_system.entity.Project;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "timesheets")
@Data
public class Timesheet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Employee
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Project
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    private LocalDate date;

    private Integer hours;

    private String status; // PENDING, APPROVED, REJECTED

    // Manager who approved
    @ManyToOne
    @JoinColumn(name = "approved_by")
    private User approvedBy;

    private String remarks;
}