package com.example.quickcart.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class User extends BaseModel {
    private String name;
    private String email;
    private String password;
    private String address;
    private Role role;
}
