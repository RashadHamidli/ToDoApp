package com.company.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.sql.Time;

@Entity
@Table(name = "tasks")
@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    @NonNull
    private String taskName;
    //    @NonNull
    private Time dedline;
    @NonNull
    private String text;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
