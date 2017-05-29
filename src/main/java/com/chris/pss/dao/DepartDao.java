package com.chris.pss.dao;

import com.chris.pss.entity.DepartEntity;
import com.chris.pss.utils.EmptyUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by noonecode on 2017/5/8.
 */
public class DepartDao extends BaseDao<DepartEntity> {

    /**
     * 根据id修改这个专业的起止时间
     */
    public boolean updateById(int id, String start, String end) {
        return update(new String[]{"timeBegin", "timeEnd"}, new String[]{start, end}, new String[]{"id=" + id});
    }

    /**
     * 根据id找到某个专业
     */
    public DepartEntity findMajorById(int id) {
        List<DepartEntity> list = findByHQL("from DepartEntity where id=? and parentId is not null", id);
        return EmptyUtils.isEmpty(list) ? null : list.get(0);
    }

    /**
     * 根据学院的id找所属所有的专业
     */
    public List<DepartEntity> getMajorListByParentId(int parentId) {
        List<DepartEntity> list = findByHQL("from DepartEntity where parentId=?", parentId);
        return list != null ? list : new ArrayList<DepartEntity>();
    }

    /**
     * 获得所有的学院和专业
     */
    public List<DepartEntity> getDepartList() {
        return findByHQL("from DepartEntity");
    }
}
