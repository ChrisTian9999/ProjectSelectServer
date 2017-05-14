package com.chris.pss.dto;

import com.chris.pss.entity.DepartmentEntity;

import java.util.List;

/**
 * Created by noonecode on 2017/5/9.
 */
public class DepartmentDTO {
    private int id;
    private String name;
    private List<DepartmentEntity> majors;

    public DepartmentDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public DepartmentDTO(int id, String name, List<DepartmentEntity> majors) {
        this.id = id;
        this.name = name;
        this.majors = majors;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DepartmentEntity> getMajors() {
        return majors;
    }

    public void setMajors(List<DepartmentEntity> majors) {
        this.majors = majors;
    }

    @Override
    public String toString() {
        return "DepartmentDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", majors=" + majors +
                '}';
    }
}
