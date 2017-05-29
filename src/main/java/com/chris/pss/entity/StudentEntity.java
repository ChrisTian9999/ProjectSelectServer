package com.chris.pss.entity;

import javax.persistence.*;

/**
 * Created by noonecode on 2017/5/12.
 */
@Entity
@Table(name = "student", schema = "dbo", catalog = "proj_sel_sys")
public class StudentEntity {
    private Integer id;
    private Integer departmentId;
    private String sno;
    private String name;
    private String pwd;
    private Integer gender;
    private String tel;
    private Long heartBeat;

    @Id
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "department_id")
    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    @Basic
    @Column(name = "sno")
    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "pwd")
    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Basic
    @Column(name = "gender")
    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    @Basic
    @Column(name = "tel")
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Basic
    @Column(name = "heart_beat")
    public Long getHeartBeat() {
        return heartBeat;
    }

    public void setHeartBeat(Long heartBeat) {
        this.heartBeat = heartBeat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StudentEntity that = (StudentEntity) o;

        if (id != that.id) return false;
        if (departmentId != null ? !departmentId.equals(that.departmentId) : that.departmentId != null) return false;
        if (sno != null ? !sno.equals(that.sno) : that.sno != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (pwd != null ? !pwd.equals(that.pwd) : that.pwd != null) return false;
        if (gender != null ? !gender.equals(that.gender) : that.gender != null) return false;
        if (tel != null ? !tel.equals(that.tel) : that.tel != null) return false;
        if (heartBeat != null ? !heartBeat.equals(that.heartBeat) : that.heartBeat != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        Integer result = id;
        result = 31 * result + (departmentId != null ? departmentId.hashCode() : 0);
        result = 31 * result + (sno != null ? sno.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (pwd != null ? pwd.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (tel != null ? tel.hashCode() : 0);
        result = 31 * result + (heartBeat != null ? heartBeat.hashCode() : 0);
        return result;
    }
}
