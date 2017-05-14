package com.chris.pss.dao;

import com.chris.pss.utils.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.lang.reflect.ParameterizedType;
import java.util.List;

@SuppressWarnings("unchecked")
public class BaseDao<T> {

    private Class<T> clazz; //T的具体类

    /**
     * 通过构造方法指定DAO的具体实现类
     */
    public BaseDao() {
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        clazz = (Class<T>) type.getActualTypeArguments()[0];
        //System.out.println("DAO的真实实现类是："+this.clazz.getName());
    }

    /**
     * 获取当前工作的Session
     */
    protected Session getSession() {
        return HibernateUtil.getSession();
    }


    public void save(T entity) {
        Session session = null;
        Transaction tx = null;
        try {
            session = this.getSession();
            tx = session.beginTransaction();
            session.save(entity);
            tx.commit();
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
    }

    public void update(T entity) {
        Session session = null;
        Transaction tx = null;
        try {
            session = this.getSession();
            tx = session.beginTransaction();
            session.update(entity);
            tx.commit();
        } catch (Exception ex) {
            System.out.println("更新对象出现错误！");
            ex.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public boolean update(String[] names, Object[] values, String[] wheres) {
        Session session = null;
        Transaction tx = null;
        try {
            session = this.getSession();
            tx = session.beginTransaction();

            String tab = clazz.getSimpleName();
            String hql = "update " + tab + " t";
            for (int i = 0; i < names.length; i++) {
                hql += " set t." + names[i] + "=?";
            }
            if (wheres != null && wheres.length > 0) {
                hql += " where ";
                for (int i = 0; i < wheres.length; i++) {
                    hql += wheres[i];
                    if (i < wheres.length - 1) {
                        hql += " and ";
                    }
                }
            }
            Query query = session.createQuery(hql);
            for (int i = 0; i < values.length; i++) {
                query.setParameter(i, values[i]);
            }
            System.out.println("update Sql：" + hql);
            int count = query.executeUpdate();
            tx.commit();
            return count > 0;
        } catch (Exception ex) {
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

    public void partUpdate(int id, String[] names, Object... values) {
        Session session = null;
        Transaction tx = null;
        try {
            session = this.getSession();
            tx = session.beginTransaction();

            String tab = clazz.getSimpleName();
            String hql = "update " + tab + " t";
            for (int i = 0; i < names.length; i++) {
                hql += " set t." + names[i] + "=?";
            }
            hql += " where t.id=" + id;
            Query query = session.createQuery(hql);
            for (int i = 0; i < values.length; i++) {
                query.setParameter(i, values[i]);
            }
            System.out.println("部分更新：" + hql);
            query.executeUpdate();
            tx.commit();
        } catch (Exception ex) {
            System.out.println("更新对象部分属性出现错误！");
            ex.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void delete(int id) {
        T obj = findById(id);
        Session session = null;
        Transaction tx = null;
        try {
            session = this.getSession();
            tx = session.beginTransaction();
            session.delete(obj);
            tx.commit();
        } catch (Exception ex) {
            System.out.println("删除对象出现错误！");
            ex.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public T findById(int id) {
        Session session = null;
        Transaction tx = null;
        T obj = null;
        try {
            session = this.getSession();
            tx = session.beginTransaction();

            obj = (T) session.get(clazz, id);

            tx.commit();
        } catch (Exception ex) {
            System.out.println("查找对象出现错误！");
            ex.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return obj;
    }

    public List<T> findByHQL(String hql, Object... params) {
        List<T> list = null;
        Session session = null;
        Transaction tx = null;
        try {
            session = this.getSession();
            tx = session.beginTransaction();

            Query query = session.createQuery(hql);
            for (int i = 0; params != null && i < params.length; i++) {
                query.setParameter(i, params[i]);
            }
            System.out.println("HQL查询：" + hql);
            list = query.list();

            tx.commit();
        } catch (Exception ex) {
            System.out.println("执行HQL查找对象出现错误！");
            ex.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return list;
    }

    public List<T> queryPage(String hql, int pageNo, int pageSize) {
        List<T> list = null;
        Session session = null;
        Transaction tx = null;
        try {
            session = this.getSession();
            tx = session.beginTransaction();

            Query query = session.createQuery(hql);
            list = query.setFirstResult((pageNo - 1) * pageSize)
                    .setMaxResults(pageSize)
                    .list();

            tx.commit();
        } catch (Exception ex) {
            System.out.println("分页查询出现错误出现错误！");
            ex.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return list;
    }

}