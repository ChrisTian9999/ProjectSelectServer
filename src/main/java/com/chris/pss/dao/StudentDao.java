package com.chris.pss.dao;

import com.chris.pss.entity.StudentEntity;
import com.chris.pss.utils.EmptyUtils;
import com.chris.pss.utils.HibernateUtil;
import sun.rmi.runtime.Log;

import java.util.Date;
import java.util.List;

/**
 * Created by noonecode on 2017/5/9.
 */
public class StudentDao extends BaseDao<StudentEntity> {

    /**
     * 根据学号拿实体
     */
    public StudentEntity findStudentBySno(String sno) {
        String hql = "from StudentEntity where sno=?";
        List<StudentEntity> studentList = findByHQL(hql, sno);
        return EmptyUtils.isEmpty(studentList) ? null : studentList.get(0);
    }

    /**
     * 刷新心跳
     */
    public boolean refreshHeartBeatTime(String sno) {
        long millis = System.currentTimeMillis();
        return update(new String[]{"heartBeat"}, new Object[]{millis}, new String[]{"sno=" + sno});
    }

    /**
     * 获得duration间隔内的时间数
     */
    public Long findLatestHeartBeatCount(Long duration) {
        long def = 10 * 1000;//10s
        duration = (duration == null || duration < 0) ? def : duration;
        long millis = System.currentTimeMillis();
        return (Long) HibernateUtil.getSession()
                .createQuery("select count(*) from StudentEntity  where heartBeat>" + (millis - duration))
                .uniqueResult();
    }

    /**
     * 重置密码
     */
    public boolean resetPwd(String sno, String pwd, String newPwd) {
        return update(new String[]{"pwd"}, new String[]{newPwd}
                , new String[]{
                        String.format("sno='%s'", sno)
                        , String.format("pwd='%s'", pwd)
                }
        );
    }

}
