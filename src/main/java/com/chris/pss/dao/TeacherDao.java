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

    /**
     * 重置密码
     */
    public boolean resetPwd(String tno, String pwd, String newPwd) {
        return update(new String[]{"pwd"}, new String[]{newPwd}
                , new String[]{
                        String.format("tno='%s'", tno)
                        , String.format("pwd='%s'", pwd)
                }
        );
    }
}
