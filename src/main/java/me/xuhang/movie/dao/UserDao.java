package me.xuhang.movie.dao;


import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import me.xuhang.movie.entity.Comment;
import me.xuhang.movie.entity.User;

import java.util.List;


/**
 * Created by xuhang on 2019/11/20.
 */

@SuppressWarnings("unused")
@Repository
public class UserDao extends BaseDao<User> {

    public UserDao() {
        super(User.class);
    }

    public boolean checkUserName(String userName) {
        Query query = getCurrentSession().createQuery("select count(*) from User as user where user.userName = :userName");
        query.setString("userName", userName);
        return (Long) query.uniqueResult() == 0;
    }

    public User findByUserName(String userName) {
        Query query = getCurrentSession().createQuery("from User as user where user.userName = :userName");
        query.setString("userName", userName);
        return (User) query.uniqueResult();
    }

    public User findByUserId(String Id) {
        Query query = getCurrentSession().createQuery("from User as user where user.id = :id");
        query.setString("id", Id);
        return (User) query.uniqueResult();
    }

    @SuppressWarnings("unchecked")
	public List<User> listByUserId(int start, int end) {
        Query query = getCurrentSession().createQuery("from User as user where user.admin = 0 order by user.createTime desc");
        query.setFirstResult(start);
        query.setMaxResults(end);
        return query.list();
    }

    public int countByUserId() {
        Query query = getCurrentSession().createQuery("select count(*) from User as user where user.admin = 0 ");
        return ((Long) query.uniqueResult()).intValue();
    }
}
