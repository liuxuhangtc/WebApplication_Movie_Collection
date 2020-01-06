package me.xuhang.movie.controller;

import me.xuhang.movie.dao.UserDao;
import me.xuhang.movie.entity.Subject;
import me.xuhang.movie.entity.User;
import me.xuhang.movie.rest.RestData;
import me.xuhang.movie.security.LoginRequired;
import me.xuhang.movie.service.DoubanService;
import me.xuhang.movie.service.UserService;
import me.xuhang.movie.utils.ContextUtils;
import me.xuhang.movie.utils.WebUtils;
import me.xuhang.movie.validator.InvalidException;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by xuhang on 2019/11/20.
 */
@SuppressWarnings("unused")
@Controller
@RequestMapping("/")
public class HomeController extends BaseController {

	@Autowired
	private UserService userService;

	@Autowired
	private DoubanService doubanService;

	@Autowired
	private UserDao userDao;

	@RequestMapping({ "/index", "/" })
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView("/index");
		List<Subject> list = doubanService.findPlaying();
		List<Subject> resule = new ArrayList<Subject>();
		User user = ContextUtils.getUser(request);
		if (user != null) {
			String aihao = user.getAihao();
			for (Subject subject : list) {
				if (subject.getGenres().contains(aihao)) {
					resule.add(subject);
				}
			}
		} else {
			resule.addAll(list);
		}
		modelAndView.addObject("subjects", resule);
		return modelAndView;
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String getRegister() {
		return "/register";
	}

	public static void main(String[] ages) {
		System.out.println(DigestUtils.md5Hex("123"));
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView register(User user, String captcha) {
		ModelAndView modelAndView = new ModelAndView("/register");

		logger.debug("user:{}", user);
		user.setPassword(DigestUtils.md5Hex(user.getPassword()));
		user.setCreateTime(new Date());
		try {
			getValidatorWrapper().tryValidate(user);
			userService.create(user);
		} catch (InvalidException ex) {
			logger.error("Invalid User Object: {}", user.toString(), ex);
			modelAndView.addObject("error", ex.getMessage());
			return modelAndView;
		}

		if (ContextUtils.getSessionUtils(request).getUser() == null) {
			return new ModelAndView("redirect:/index");
		} else if (ContextUtils.getSessionUtils(request).getUser().isAdmin()) {
			return new ModelAndView("redirect:/user/list");
		} else {
			return new ModelAndView("redirect:/index");
		}
	}

	@RequestMapping(value = "/checkUserName", method = RequestMethod.GET)
	@ResponseBody
	public RestData checkUserName(String userName) {
		RestData restData = new RestData();
		if (userDao.checkUserName(userName)) {
			restData.setSuccess(1);
		} else {
			restData.setComment("Username already in use");
		}
		return restData;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView getLogin(String redirect) {
		ModelAndView modelAndView = new ModelAndView("/login");
		if (StringUtils.isNotEmpty(redirect)) {
			modelAndView.addObject("error", "Please login first!");
		}
		return modelAndView;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(String userName, String password, String captcha) {
		ModelAndView modelAndView = new ModelAndView("/login");

		User user = userDao.findByUserName(userName);

		if (null != user && user.getPassword().equals(DigestUtils.md5Hex(password))) {
			ContextUtils.getSessionUtils(request).setUser(user);
			return new ModelAndView("redirect:/");
		} else {
			modelAndView.addObject("error", "Username or password incorrect");
			return modelAndView;
		}
	}

	@RequestMapping(value = "/password", method = RequestMethod.GET)
	public String getPassword() {
		return "/password";
	}

	@RequestMapping(value = "/password", method = RequestMethod.POST)
	@LoginRequired
	public ModelAndView postPassword(String oldPassword, String newPassword, String captcha) {
		ModelAndView modelAndView = new ModelAndView("/password");

		User user = ContextUtils.getUser(request);
		if (user.getPassword().equals(DigestUtils.md5Hex(oldPassword))) {
			user.setPassword(DigestUtils.md5Hex(newPassword));
			userDao.update(user);
			return new ModelAndView("redirect:/");
		} else {
			modelAndView.addObject("error", "Old password incorrect");
		}
		return modelAndView;

	}

	@RequestMapping(value = "/editUser", method = RequestMethod.GET)
	public ModelAndView editUser(String id) {
		ModelAndView modelAndView = new ModelAndView("/editUser");
		User user = userDao.findByUserId(id);
		modelAndView.addObject("user", user);
		return modelAndView;
	}

	@RequestMapping(value = "/editUser", method = RequestMethod.POST)
	@LoginRequired
	public ModelAndView postPassword(String id, String aihao, String email, String name, String phone) {
		ModelAndView modelAndView = new ModelAndView("/editUser");

		User user = userDao.findByUserId(id);
		if (user != null) {
			user.setAihao(aihao);
			user.setEmail(email);
			user.setName(name);
			user.setPhone(phone);
			userDao.update(user);
			if (ContextUtils.getSessionUtils(request).getUser().isAdmin()) {
				return new ModelAndView("redirect:/user/list");
			} else {
				return new ModelAndView("redirect:/user/" + ContextUtils.getSessionUtils(request).getUser().getId());
			}
		} else {
			modelAndView.addObject("error", "User data error");
		}
		return modelAndView;

	}

	@RequestMapping("logout")
	public String logout() {
		request.getSession().invalidate();
		return "redirect:/";
	}

	@RequestMapping("403")
	public String get403() {
		return "/misc/403";
	}

}
