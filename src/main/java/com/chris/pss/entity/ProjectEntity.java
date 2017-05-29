package com.chris.pss.entity;

import javax.persistence.*;

/**
 * Created by zht on 2017/5/23.
 */
@Entity
@Table(name = "t_project", schema = "dbo", catalog = "proj_sel_sys")
public class ProjectEntity {
    @Id
    @Column(name = "id")
    private Integer id;

    /**
     * 专业
     */
    @ManyToOne
    @JoinColumn(name="depart_id", nullable = false)
    private DepartEntity major;

    @ManyToOne
    @JoinColumn(name="teacher_id", nullable = false)
    private TeacherEntity teacher;

    @Column(name = "title")
    private String title;

    @Column(name = "detail")
    private String detail;
    @Column(name = "ranking")
    private Integer ranking;
    @Column(name = "is_checked")
    private Integer isChecked;
    @Column(name = "is_finish")
    private Integer isFinish;


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

    public TeacherEntity getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherEntity teacher) {
        this.teacher = teacher;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    public Integer getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(Integer isChecked) {
        this.isChecked = isChecked;
    }

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

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (major != null ? !major.equals(that.major) : that.major != null) return false;
        if (teacher != null ? !teacher.equals(that.teacher) : that.teacher != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (detail != null ? !detail.equals(that.detail) : that.detail != null) return false;
        if (ranking != null ? !ranking.equals(that.ranking) : that.ranking != null) return false;
        if (isChecked != null ? !isChecked.equals(that.isChecked) : that.isChecked != null) return false;
        return isFinish != null ? isFinish.equals(that.isFinish) : that.isFinish == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (major != null ? major.hashCode() : 0);
        result = 31 * result + (teacher != null ? teacher.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (detail != null ? detail.hashCode() : 0);
        result = 31 * result + (ranking != null ? ranking.hashCode() : 0);
        result = 31 * result + (isChecked != null ? isChecked.hashCode() : 0);
        result = 31 * result + (isFinish != null ? isFinish.hashCode() : 0);
        return result;
    }
}
