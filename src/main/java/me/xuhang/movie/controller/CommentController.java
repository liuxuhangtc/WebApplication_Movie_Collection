package me.xuhang.movie.controller;

import me.xuhang.movie.dao.CommentDao;
import me.xuhang.movie.dao.SubjectDao;
import me.xuhang.movie.entity.Comment;
import me.xuhang.movie.entity.Subject;
import me.xuhang.movie.entity.User;
import me.xuhang.movie.rest.RestData;
import me.xuhang.movie.utils.ContextUtils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.HtmlUtils;

import java.util.Date;

/**
 * Created by xuhang on 2019/11/20.
 */
@Controller
@RequestMapping("/comment")
public class CommentController extends BaseController {

	@Autowired
	private CommentDao commentDao;

	@Autowired
	private SubjectDao subjectDao;

	@RequestMapping(value = "/post", method = RequestMethod.POST)
	public ModelAndView getComments(Comment comment) {
		ModelAndView modelAndView = new ModelAndView("redirect:/subject/" + comment.getSubjectId());
		if (StringUtils.isEmpty(ContextUtils.getUserId(request))) {
			modelAndView.addObject("error", "Login to Review and Collect");
			return modelAndView;
		}
		comment.setUserId(ContextUtils.getUserId(request));
		comment.setSubmitDate(new Date());
		comment.setContent(HtmlUtils.htmlEscape(comment.getContent()));
		commentDao.create(comment);
		Subject subject = subjectDao.find(comment.getSubjectId());
		subject.setTotalRating(subject.getTotalRating() + comment.getRating());
		subject.setRatingCount(subject.getRatingCount() + 1);
		subject.setCommentCount(subject.getCommentCount() + 1);
		subjectDao.update(subject);
		return modelAndView;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public RestData deleteComment(String id) {
		Comment comment = commentDao.find(id);
		RestData restData = new RestData();
		if (null == comment) {
			restData.setComment("This collection not exist！");
			return restData;
		}
		User currentUser = ContextUtils.getUser(request);
		if (null == currentUser) {
			restData.setComment("Please login first！");
			return restData;
		}
		if (currentUser.isAdmin() || currentUser.getId().equals(comment.getUserId())) {
			Subject subject = subjectDao.find(comment.getSubjectId());
			subject.setCommentCount(subject.getCommentCount() - 1);
			subject.setTotalRating(subject.getTotalRating() - comment.getRating());
			subject.setRatingCount(subject.getRatingCount() - 1);
			subjectDao.update(subject);
			commentDao.delete(comment);
			restData.setSuccess(1);
			return restData;

		} else {
			restData.setComment(
					"You are not the author or moderator of this comment collection, you do not have permission to delete this comment collection.");
			return restData;
		}
	}
}
