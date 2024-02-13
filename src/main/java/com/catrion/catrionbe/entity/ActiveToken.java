package com.catrion.catrionbe.entity;


import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "active_token", indexes = @Index(columnList = "token"))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ActiveToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "token", columnDefinition = "TEXT", nullable = false)
    String token;
    @Column(name = "username", nullable = false)
    String username;
    @Column(name = "created_date", nullable = false)
    LocalDateTime createdDate;

    public ActiveToken(String token, String username, LocalDateTime createdDate) {
        this.token = token;
        this.username = username;
        this.createdDate = createdDate;
    }

}
