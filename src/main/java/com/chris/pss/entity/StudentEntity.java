package com.chris.pss.entity;

import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;

/**
 * Created by noonecode on 2017/5/12.
 */
@Entity
@Table(name = "t_student", schema = "dbo", catalog = "proj_sel_sys")
public class StudentEntity {
    @Id
    @Column(name = "id")
    private Integer id;

    /**
     * 专业
     */
    @ManyToOne
    @JoinColumn(name="depart_id", nullable = false)
    private DepartEntity major;

    @Column(name = "sno")
    private String sno;

    @Column(name = "name")
    private String name;

    @Column(name = "pwd")
    private String pwd;

    @Column(name = "classname")
    private Integer classname;

    @Column(name = "gender")
    private Integer gender;

    @Column(name = "tel")
    private String tel;

    @Column(name = "heart_beat")
    private Long heartBeat;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public DepartEntity getMajor() {
        return major;
    }

    public void setMajor(DepartEntity major) {
        this.major = major;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Long getHeartBeat() {
        return heartBeat;
    }

    public void setHeartBeat(Long heartBeat) {
        this.heartBeat = heartBeat;
    }

    public Integer getClassname() {
        return classname;
    }

    public void setClassname(Integer classname) {
        this.classname = classname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StudentEntity that = (StudentEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (major != null ? !major.equals(that.major) : that.major != null) return false;
        if (sno != null ? !sno.equals(that.sno) : that.sno != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (pwd != null ? !pwd.equals(that.pwd) : that.pwd != null) return false;
        if (classname != null ? !classname.equals(that.classname) : that.classname != null) return false;
        if (gender != null ? !gender.equals(that.gender) : that.gender != null) return false;
        if (tel != null ? !tel.equals(that.tel) : that.tel != null) return false;
        return heartBeat != null ? heartBeat.equals(that.heartBeat) : that.heartBeat == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (major != null ? major.hashCode() : 0);
        result = 31 * result + (sno != null ? sno.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (pwd != null ? pwd.hashCode() : 0);
        result = 31 * result + (classname != null ? classname.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (tel != null ? tel.hashCode() : 0);
        result = 31 * result + (heartBeat != null ? heartBeat.hashCode() : 0);
        return result;
    }
}
