package me.xuhang.movie.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Created by xuhang on 2019/11/20.
 */

public abstract class BaseDao<T> {

    protected Logger logger = LoggerFactory.getLogger(getClass().getName());

    @Autowired
    private SessionFactory sessionFactory;


    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }


    @SuppressWarnings("rawtypes")
	private Class entityClass;

    public BaseDao(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @SuppressWarnings("unchecked")
	@Transactional
    public List<T> findAll() throws DataAccessException {
        return getCurrentSession().createCriteria(entityClass).list();
    }

    @SuppressWarnings("unchecked")
	public T find(String id) {
        return (T) getCurrentSession().get(entityClass, id);
    }

    public void create(T t) {
        getCurrentSession().save(t);
    }

    public void update(T t) {
        getCurrentSession().update(t);
    }

    public void delete(T t) {
        getCurrentSession().delete(t);
    }

    public void flush() {
        getCurrentSession().flush();
    }

    public void deleteAll() {
        Query query = getCurrentSession().createQuery("delete from " + entityClass.getSimpleName());
        query.executeUpdate();
    }

}
