package com.example.socialnetwork.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RoleDto {

    private String id;
    private String name;
    public RoleDto(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
