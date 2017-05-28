package com.chris.pss.dao;

import com.chris.pss.entity.ProjectEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ProjectDao extends BaseDao<ProjectEntity> {

    public boolean create(int departId, int majorId, int teacherId, String title, String detail, int ranking) {
        Session session = null;
        Transaction tx = null;
        try {
            session = this.getSession();
            tx = session.beginTransaction();
            String hql = "insert into project values('%d','%d','%d',NULL ,'%s','%s','%d', NULL , NULL )";
            hql = String.format(hql, departId, majorId, teacherId, title, detail, ranking);
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

}
