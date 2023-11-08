package com.company.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.sql.Time;
import java.util.List;

@Entity
@Table(name = "tasks")
@RequiredArgsConstructor
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String taskName;
    private Time dedline;
    private String text;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
