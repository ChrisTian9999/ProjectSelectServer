package com.chris.pss.entity;

import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;

/**
 * Created by noonecode on 2017/5/12.
 */
@Entity
@Table(name = "teacher", schema = "dbo", catalog = "proj_sel_sys")
public class TeacherEntity {
    @Id
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private DepartEntity depart;//所在学院

    @Column(name = "department_id")
    private Integer departmentId;

    @Column(name = "tno")
    private String tno;

    @Column(name = "name")
    private String name;

    @Column(name = "pwd")
    private String pwd;

    @Column(name = "gender")
    private Integer gender;

    @Column(name = "zhicheng")
    private String zhicheng;

    @Column(name = "tel")
    private String tel;

    @Column(name = "email")
    private String email;

    @Column(name = "is_admin")
    private Integer isAdmin;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public DepartEntity getDepart() {
        return depart;
    }

    public void setDepart(DepartEntity depart) {
        this.depart = depart;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getTno() {
        return tno;
    }

    public void setTno(String tno) {
        this.tno = tno;
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

    public String getZhicheng() {
        return zhicheng;
    }

    public void setZhicheng(String zhicheng) {
        this.zhicheng = zhicheng;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Integer isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TeacherEntity that = (TeacherEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (depart != null ? !depart.equals(that.depart) : that.depart != null) return false;
        if (departmentId != null ? !departmentId.equals(that.departmentId) : that.departmentId != null) return false;
        if (tno != null ? !tno.equals(that.tno) : that.tno != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (pwd != null ? !pwd.equals(that.pwd) : that.pwd != null) return false;
        if (gender != null ? !gender.equals(that.gender) : that.gender != null) return false;
        if (zhicheng != null ? !zhicheng.equals(that.zhicheng) : that.zhicheng != null) return false;
        if (tel != null ? !tel.equals(that.tel) : that.tel != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        return isAdmin != null ? isAdmin.equals(that.isAdmin) : that.isAdmin == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (depart != null ? depart.hashCode() : 0);
        result = 31 * result + (departmentId != null ? departmentId.hashCode() : 0);
        result = 31 * result + (tno != null ? tno.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (pwd != null ? pwd.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (zhicheng != null ? zhicheng.hashCode() : 0);
        result = 31 * result + (tel != null ? tel.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (isAdmin != null ? isAdmin.hashCode() : 0);
        return result;
    }
}
