package com.example.quickcart.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Setter
@Getter
@NoArgsConstructor
@Entity
public class User extends BaseModel {
    private String name;
    private String email;
    private String password;
    private String contactNumber;
    private String address;
    @Enumerated(EnumType.STRING)
    private List<Role> roles;
}
