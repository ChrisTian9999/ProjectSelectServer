package com.chris.pss.dao;

import com.chris.pss.entity.ProjectEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ProjectDao extends BaseDao<ProjectEntity> {

    /**
     * 创建课题
     */
    public boolean create(int majorId, int teacherId, String title, String detail, int ranking) {
        Session session = null;
        Transaction tx = null;
        try {
            session = this.getSession();
            tx = session.beginTransaction();
            String hql = "insert into t_project values('%d','%d',NULL ,'%s','%s','%d','0','0')";
            hql = String.format(hql, majorId, teacherId, title, detail, ranking);
            session.createSQLQuery(hql).executeUpdate();
            tx.commit();
            return true;
        } catch (Exception ex) {
            System.out.println("保存对象出现错误！");
            ex.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return false;
    }

    public List<ProjectEntity> getProjectListByTno(String tno) {
        return findByHQL("from ProjectEntity p where p.teacher.tno=? order by p.id desc,p.isChecked desc", tno);
    }


    /**
     * 查询审核的状态是isChecked的课题
     *
     * @param departId  学院系的id
     * @param isChecked 状态，是否通过审核
     */
    public List<ProjectEntity> getByCheckState(int departId, boolean isChecked) {
        return isChecked ? findByHQL("from ProjectEntity p where p.major.parent.id=? and p.isChecked=1 order by p.teacher.id asc", departId)
                : findByHQL("from ProjectEntity p where p.major.parent.id=? and p.isChecked!=1 order by p.teacher.id asc", departId);
    }

    public List<ProjectEntity> getAll() {
        return findByHQL("from ProjectEntity");
    }


}
