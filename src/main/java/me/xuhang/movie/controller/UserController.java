package me.xuhang.movie.controller;

import me.xuhang.movie.dao.CommentDao;
import me.xuhang.movie.dao.UserDao;
import me.xuhang.movie.entity.Comment;
import me.xuhang.movie.entity.Subject;
import me.xuhang.movie.entity.User;
import me.xuhang.movie.rest.PageInfo;
import me.xuhang.movie.rest.RestData;
import me.xuhang.movie.security.AdminRequired;
import me.xuhang.movie.utils.ContextUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by xuhang on 2019/11/20.
 */
@SuppressWarnings("unused")
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

	@Autowired
	private CommentDao commentDao;

	@Autowired
	private UserDao userDao;

	@RequestMapping("/{id}")
	public ModelAndView getAccount(@PathVariable(value = "id") String id,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo) {
		ModelAndView modelAndView = new ModelAndView("/user");
		User user = userDao.find(id);
		PageInfo<Comment> pageInfo = new PageInfo<Comment>(pageNo, 5);
		modelAndView.addObject("user", user);
		int commentCount = commentDao.countByUserId(id);
		modelAndView.addObject("commentCount", commentCount);
		if (commentCount > 0) {
			pageInfo.setResultList(commentDao.listByUserId(id, pageInfo.getStartRow(), 5));
			pageInfo.setTotalRows(commentDao.countByUserId(id));
		}
		modelAndView.addObject("pageInfo", pageInfo);

		return modelAndView;
	}

	@RequestMapping("/{id}/setAdmin")
	@AdminRequired
	public String setAdmin(@PathVariable(value = "id") String id) {
		User user = userDao.find(id);
		if (null == user) {
			return "/misc/404";
		}
		if (user.isAdmin()) {
			user.setAdmin(false);
		} else {
			user.setAdmin(true);
		}
		userDao.update(user);
		return "redirect:/user/" + id;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public RestData deleteComment(String id) {
		User user = userDao.find(id);
		RestData restData = new RestData();
		if (null == user) {
			restData.setComment("user not exist！");
			return restData;
		}
		User currentUser = ContextUtils.getUser(request);
		if (null == currentUser) {
			restData.setComment("Please login first！");
			return restData;
		}
		if (currentUser != null && currentUser.isAdmin()) {
			userDao.delete(user);
			restData.setSuccess(1);
			return restData;

		} else {
			restData.setComment("Only admin can delete");
			return restData;
		}
	}

	@RequestMapping("/list")
	public ModelAndView getList(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo) {
		ModelAndView modelAndView = new ModelAndView("/userlist");
		PageInfo<User> pageInfo = new PageInfo<User>(pageNo, 5);
		pageInfo.setResultList(userDao.listByUserId(pageInfo.getStartRow(), 5));
		pageInfo.setTotalRows(userDao.countByUserId());
		modelAndView.addObject("pageInfo", pageInfo);
		return modelAndView;
	}
}
