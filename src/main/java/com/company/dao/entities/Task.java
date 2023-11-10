package com.company.dao.entities;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;

@Entity
@Builder
@Table(name = "tasks")
@NoArgsConstructor
@AllArgsConstructor
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
