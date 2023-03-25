package com.project.myFirstTGBOT.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
@NoArgsConstructor
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "message",nullable = false)
    private String message;

    @Column(name = "chat_id", nullable = false)
    private long chatId;

    @Column(name = "task_date_time", nullable = false)
    private LocalDateTime taskDateTime;
}
