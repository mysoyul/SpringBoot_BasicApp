package com.basic.myspringboot.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="users")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Name은 필수 입력항목입니다.")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Email은 필수 입력항목입니다.")
    @Column(unique = true, nullable = false)
    private String email;
}
