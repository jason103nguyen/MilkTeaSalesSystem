package fa.training.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import fa.training.utils.HibernateUtil;

public class GenericDao<T> {

    private Class<T> entityClass;

    public GenericDao(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public Serializable create(T entity) {
        Session session = null;
        Transaction transaction = null;
        Serializable id = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            id = session.save(entity);
            
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return id;
    }

    public T readOne(Serializable id) {
        Session session = null;
        Transaction transaction = null;
        T readEntity = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            readEntity = session.get(entityClass, id);

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return readEntity;
    }

    public List<T> readAll() {
        Session session = null;
        Transaction transaction = null;
        List<T> entityList = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

            CriteriaQuery<T> query = criteriaBuilder.createQuery(entityClass);

            Root<T> root = query.from(entityClass);

            query.select(root);

            Query<T> q = session.createQuery(query);

            entityList = q.getResultList();

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return entityList;
    }

    public void update (T entity) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            session.saveOrUpdate(entity);

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void delete (Serializable id) {
        Session session = null;
        Transaction transaction = null;
        T deletedEntity = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            deletedEntity = session.get(entityClass, id);
            session.delete(deletedEntity);

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
    
    public List<T> likeOperator(String fieldName, String value) {
    	Session session = null;
        List<T> entityList = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<T> query = criteriaBuilder.createQuery(entityClass);
            Root<T> root = query.from(entityClass);

            query.select(root);
            query.where(criteriaBuilder.like(root.get(fieldName), "%" + value + "%"));

            Query<T> q = session.createQuery(query);

            entityList = q.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return entityList;
    }

    public <V> List<T> equalOperator(String field, V v) {
		Session session = null;
        List<T> orderList = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<T> query = criteriaBuilder.createQuery(this.entityClass);
            Root<T> root = query.from(this.entityClass);

            query.select(root);
            query.where(criteriaBuilder.equal(root.get(field), v));

            Query<T> q = session.createQuery(query);

            orderList = q.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return orderList;
	}
    
    public <Y extends Comparable<? super Y>> List<T> greaterThanOperator(String field, Y value) {
		Session session = null;
        List<T> orderList = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<T> query = criteriaBuilder.createQuery(this.entityClass);
            Root<T> root = query.from(this.entityClass);

            query.select(root);
            query.where(criteriaBuilder.greaterThanOrEqualTo(root.get(field), value));

            Query<T> q = session.createQuery(query);

            orderList = q.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return orderList;
	}
    
    public <Y extends Comparable<? super Y>> List<T> lessThanOperator(String field, Y value) {
		Session session = null;
        List<T> orderList = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<T> query = criteriaBuilder.createQuery(this.entityClass);
            Root<T> root = query.from(this.entityClass);

            query.select(root);
            query.where(criteriaBuilder.lessThanOrEqualTo(root.get(field), value));

            Query<T> q = session.createQuery(query);

            orderList = q.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return orderList;
	}
}
