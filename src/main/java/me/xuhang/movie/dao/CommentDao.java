package me.xuhang.movie.dao;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import me.xuhang.movie.entity.Comment;

import java.util.List;

/**
 * Created by xuhang on 2019/11/20.
 */
@Repository
public class CommentDao extends BaseDao<Comment> {

    public CommentDao() {
        super(Comment.class);
    }

    public int countByUserId(String userId) {
        Query query = getCurrentSession().createQuery("select count(*) from Comment as comment where comment.userId = :userId");
        query.setString("userId", userId);
        return ((Long) query.uniqueResult()).intValue();
    }

    @SuppressWarnings("unchecked")
	public List<Comment> listBySubjectId(String subjectId, int start, int end) {
        Query query = getCurrentSession().createQuery("from Comment as comment where comment.subjectId = :subjectId order by comment.submitDate desc");
        query.setString("subjectId", subjectId);
        query.setFirstResult(start);
        query.setMaxResults(end);
        return query.list();
    }

    @SuppressWarnings("unchecked")
	public List<Comment> listByUserId(String userId, int start, int end) {
        Query query = getCurrentSession().createQuery("from Comment as comment where comment.userId = :userId order by comment.submitDate desc");
        query.setString("userId", userId);
        query.setFirstResult(start);
        query.setMaxResults(end);
        return query.list();
    }

}
