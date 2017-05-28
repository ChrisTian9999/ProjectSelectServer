package com.chris.pss.dao;

import com.chris.pss.entity.TeacherEntity;
import com.chris.pss.utils.EmptyUtils;

import java.util.List;

/**
 * Created by noonecode on 2017/5/9.
 */
public class TeacherDao extends BaseDao<TeacherEntity> {
    public TeacherEntity findTchByTno(String tno) {
        List<TeacherEntity> list = findByHQL("from TeacherEntity where tno=?", tno);
        return EmptyUtils.isEmpty(list) ? null : list.get(0);
    }
    
}
