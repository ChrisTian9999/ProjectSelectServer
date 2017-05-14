package com.chris.pss.dto;

import com.chris.pss.entity.DepartmentEntity;
import com.chris.pss.entity.ProjectEntity;
import com.chris.pss.entity.StudentEntity;

import java.util.List;

/**
 * Created by noonecode on 2017/5/9.
 */
public class StudentLoginDTO {
    private StudentEntity stud; //包含用户信息
    private DepartmentEntity major;//包含专业信息
    private ProjectEntity proj; //包含课题信息
    private List<DepartmentEntity> extras;//包含所有的专业与学院信息

    public StudentLoginDTO(StudentEntity stud, DepartmentEntity major, ProjectEntity proj, List<DepartmentEntity> extras) {
        this.stud = stud;
        this.major = major;
        this.proj = proj;
        this.extras = extras;
    }

    public StudentEntity getStud() {
        return stud;
    }

    public void setStud(StudentEntity stud) {
        this.stud = stud;
    }

    public DepartmentEntity getMajor() {
        return major;
    }

    public void setMajor(DepartmentEntity major) {
        this.major = major;
    }

    public ProjectEntity getProj() {
        return proj;
    }

    public void setProj(ProjectEntity proj) {
        this.proj = proj;
    }

    public List<DepartmentEntity> getExtras() {
        return extras;
    }

    public void setExtras(List<DepartmentEntity> extras) {
        this.extras = extras;
    }
}
