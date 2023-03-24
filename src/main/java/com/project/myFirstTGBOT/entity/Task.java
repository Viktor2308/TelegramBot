package com.project.myFirstTGBOT.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name="Tasks")
@NoArgsConstructor
@Data
public class Task {
    @Id
    @GeneratedValue
    private long id;

    private String message;

    private long chatId;

    private LocalDateTime taskDateTime;
}
