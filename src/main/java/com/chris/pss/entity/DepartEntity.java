package com.chris.pss.entity;

import javax.persistence.*;

/**
 * Created by zht on 2017/5/9.
 */
@Entity
@Table(name = "t_depart", schema = "dbo", catalog = "proj_sel_sys")
public class DepartEntity {
    @Id
    @Column(name = "id")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "parent_id")
    private DepartEntity parent;

    @Column(name = "name")
    private String name;

    @Column(name = "time_begin")
    private String timeBegin;

    @Column(name = "time_end")
    private String timeEnd;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public DepartEntity getParent() {
        return parent;
    }

    public void setParent(DepartEntity parent) {
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTimeBegin() {
        return timeBegin;
    }

    public void setTimeBegin(String timeBegin) {
        this.timeBegin = timeBegin;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DepartEntity that = (DepartEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (parent != null ? !parent.equals(that.parent) : that.parent != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (timeBegin != null ? !timeBegin.equals(that.timeBegin) : that.timeBegin != null) return false;
        return timeEnd != null ? timeEnd.equals(that.timeEnd) : that.timeEnd == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (parent != null ? parent.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (timeBegin != null ? timeBegin.hashCode() : 0);
        result = 31 * result + (timeEnd != null ? timeEnd.hashCode() : 0);
        return result;
    }
}
