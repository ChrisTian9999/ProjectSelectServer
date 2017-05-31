package com.chris.pss.dao;

import com.chris.pss.entity.ProjectEntity;
import com.chris.pss.utils.EmptyUtils;
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
            String hql = "INSERT INTO t_project VALUES ('%d','%d',NULL ,'%s','%s','%d','0','0')";
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
        return findByHQL("from ProjectEntity p where p.teacher.tno=? order by p.id desc", tno);
    }

    public List<ProjectEntity> getMajorCheckedProjectList(int majorId) {
        return findByHQL("from ProjectEntity p where p.major.id=? and p.isChecked=1 order by p.id desc", majorId);
    }


    /**
     * 查询审核的状态是isChecked的课题
     *
     * @param departId  学院系的id
     * @param isChecked 状态，是否通过审核
     */
    public List<ProjectEntity> getByCheckState(int departId, boolean isChecked) {
        return isChecked ? findByHQL("from ProjectEntity p where p.major.parent.id=? and p.isChecked=1 order by p.id desc", departId)
                : findByHQL("from ProjectEntity p where p.major.parent.id=? and p.isChecked!=1 order by p.id desc", departId);
    }

    public List<ProjectEntity> getAll() {
        return findByHQL("from ProjectEntity order by p.id desc");
    }

    /**
     * 设置项目的审核状态
     */
    public boolean resetCheckState(int projectId, boolean isChecked) {
        return partUpdate(projectId, new String[]{"isChecked"}, (isChecked ? 1 : 0));
    }

    public ProjectEntity getProjectByStudentSno(String sno) {
        List<ProjectEntity> list = findByHQL("from ProjectEntity p where p.student!=null and p.student.sno=?", sno);
        return EmptyUtils.isEmpty(list) ? null : list.get(0);
    }

    public ProjectEntity getProjectByStudentId(int studentId) {
        List<ProjectEntity> list = findByHQL("from ProjectEntity p where p.student!=null and p.student.id=?", studentId);
        return EmptyUtils.isEmpty(list) ? null : list.get(0);
    }

    public boolean postSelectProject(int projectId, int studentId) {
        Session session = null;
        Transaction tx = null;
        try {
            session = this.getSession();
            tx = session.beginTransaction();
            String hql = "UPDATE t_project SET student_id='%d' WHERE id='%d' and student_id IS NULL ";
            hql = String.format(hql, studentId, projectId);
            int count = session.createSQLQuery(hql).executeUpdate();
            tx.commit();
            return count > 0;
        } catch (Exception ex) {
            System.out.println("选题出现错误！");
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


    public boolean removeSelection(int projectId) {
        Session session = null;
        Transaction tx = null;
        try {
            session = this.getSession();
            tx = session.beginTransaction();
            String hql = "UPDATE t_project SET student_id=NULL WHERE id='%d'";
            hql = String.format(hql, projectId);
            int count = session.createSQLQuery(hql).executeUpdate();
            tx.commit();
            return count > 0;
        } catch (Exception ex) {
            System.out.println("移除选题出现错误！");
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
