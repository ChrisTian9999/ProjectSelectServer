package com.chris.pss.dao;

import com.chris.pss.entity.DepartmentEntity;
import com.chris.pss.utils.EmptyUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by noonecode on 2017/5/8.
 */
public class DepartDao extends BaseDao<DepartmentEntity> {

    /**
     * 根据id修改这个专业的起止时间
     */
    public boolean updateById(int id, String start, String end) {
        return update(new String[]{"timeBegin", "timeEnd"}, new String[]{start, end}, new String[]{"id=" + id});
    }

    /**
     * 根据id找到某个专业
     */
    public DepartmentEntity findMajorById(int id) {
        List<DepartmentEntity> list = findByHQL("from DepartmentEntity where id=? and parentId is not null", id);
        return EmptyUtils.isEmpty(list) ? null : list.get(0);
    }

    /**
     * 根据学院的id找所属所有的专业
     */
    public List<DepartmentEntity> getMajorListByParentId(int parentId) {
        List<DepartmentEntity> list = findByHQL("from DepartmentEntity where parentId=?", parentId);
        return list != null ? list : new ArrayList<DepartmentEntity>();
    }

    /**
     * 获得所有的学院和专业
     */
    public List<DepartmentEntity> getDepartList() {
        return findByHQL("from DepartmentEntity");
    }
}
