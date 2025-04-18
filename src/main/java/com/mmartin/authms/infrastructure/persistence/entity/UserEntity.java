package com.mmartin.authms.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Data
@Entity
@Table(name = "users")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UserEntity {

    @Id
    @Column(columnDefinition = "uuid")
    @GeneratedValue
    private UUID id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;
}
