package com.chris.pss.entity;

import javax.persistence.*;

/**
 * Created by zht on 2017/5/23.
 */
@Entity
@Table(name = "project", schema = "dbo", catalog = "proj_sel_sys")
public class ProjectEntity {
    private int id;
    private int departmentId;
    private int teacherId;
    private Integer studentId;
    private String title;
    private String detail;
    private Integer ranking;
    private Integer isChecked;
    private Integer isFinish;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "department_id")
    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    @Basic
    @Column(name = "teacher_id")
    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    @Basic
    @Column(name = "student_id")
    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "detail")
    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Basic
    @Column(name = "ranking")
    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    @Basic
    @Column(name = "is_checked")
    public Integer getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(Integer isChecked) {
        this.isChecked = isChecked;
    }

    @Basic
    @Column(name = "is_finish")
    public Integer getIsFinish() {
        return isFinish;
    }

    public void setIsFinish(Integer isFinish) {
        this.isFinish = isFinish;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProjectEntity that = (ProjectEntity) o;

        if (id != that.id) return false;
        if (departmentId != that.departmentId) return false;
        if (teacherId != that.teacherId) return false;
        if (studentId != null ? !studentId.equals(that.studentId) : that.studentId != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (detail != null ? !detail.equals(that.detail) : that.detail != null) return false;
        if (ranking != null ? !ranking.equals(that.ranking) : that.ranking != null) return false;
        if (isChecked != null ? !isChecked.equals(that.isChecked) : that.isChecked != null) return false;
        if (isFinish != null ? !isFinish.equals(that.isFinish) : that.isFinish != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + departmentId;
        result = 31 * result + teacherId;
        result = 31 * result + (studentId != null ? studentId.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (detail != null ? detail.hashCode() : 0);
        result = 31 * result + (ranking != null ? ranking.hashCode() : 0);
        result = 31 * result + (isChecked != null ? isChecked.hashCode() : 0);
        result = 31 * result + (isFinish != null ? isFinish.hashCode() : 0);
        return result;
    }
}
