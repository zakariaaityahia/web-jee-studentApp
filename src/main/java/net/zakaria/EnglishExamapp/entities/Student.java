package net.zakaria.EnglishExamapp.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString @Builder
@Entity
@Table(name = "Student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private Date birth;
    private int mark;
    private boolean status;
}
