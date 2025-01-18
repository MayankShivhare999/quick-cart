package com.example.quickcart.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode(callSuper = true)
@Setter
@Getter
@NoArgsConstructor
@Entity
public class User extends BaseModel {
    private String name;
    private String email;
    private String password;
    private String address;
    @Enumerated(EnumType.STRING)
    private Role role;
}
