package com.ironhack.edgeservice.utils.dtos;

public class RoleDTO {
    private Integer id;
    private String name;

    public RoleDTO() {
        this.name = "USER";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
