package com.chris.pss.dto;

import com.chris.pss.entity.DepartmentEntity;
import com.chris.pss.entity.TeacherEntity;

import java.util.List;

/**
 * Created by noonecode on 2017/5/12.
 */
public class TeacherLoginDTO {
    private TeacherEntity tch;//基本信息
    private DepartmentEntity depart;//所在的学院
    private List<DepartmentEntity> extras;//所有的专业学院信息

    public TeacherLoginDTO(TeacherEntity tch, DepartmentEntity depart, List<DepartmentEntity> extras) {
        this.tch = tch;
        this.depart = depart;
        this.extras = extras;
    }

    public TeacherEntity getTch() {
        return tch;
    }

    public void setTch(TeacherEntity tch) {
        this.tch = tch;
    }

    public DepartmentEntity getDepart() {
        return depart;
    }

    public void setDepart(DepartmentEntity depart) {
        this.depart = depart;
    }

    public List<DepartmentEntity> getExtras() {
        return extras;
    }

    public void setExtras(List<DepartmentEntity> extras) {
        this.extras = extras;
    }
}
