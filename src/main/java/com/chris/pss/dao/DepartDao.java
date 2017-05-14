package com.chris.pss.dao;

import com.chris.pss.dto.DepartmentDTO;
import com.chris.pss.entity.DepartmentEntity;
import com.chris.pss.utils.EmptyUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by noonecode on 2017/5/8.
 */
public class DepartDao extends BaseDao<DepartmentEntity> {

    /**
     * 根据学院的id获得一个学院基本信息
     */
    public DepartmentEntity findDepartById(int id) {
        List<DepartmentEntity> list = findByHQL("from DepartmentEntity where id=? and parentId is null", id);
        return EmptyUtils.isEmpty(list) ? null : list.get(0);
    }

    /**
     * 根据id找到某个专业
     */
    public DepartmentEntity findMajorById(int id) {
        List<DepartmentEntity> list = findByHQL("from DepartmentEntity where id=? and parentId is not null", id);
        return EmptyUtils.isEmpty(list) ? null : list.get(0);
    }

    public List<DepartmentEntity> getDepartList() {
        return findByHQL("from DepartmentEntity");
    }
}
