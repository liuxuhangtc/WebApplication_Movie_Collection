package me.xuhang.movie.controller;

import me.xuhang.movie.entity.Subject;
import me.xuhang.movie.rest.PageInfo;
import me.xuhang.movie.service.DoubanService;
import me.xuhang.movie.service.SubjectService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by xuhang on 2019/11/20.
 */
@Controller
@RequestMapping("/category")
public class CategoryController extends BaseController {

	@Autowired
	private SubjectService subjectService;

	@Autowired
	private DoubanService doubanService;

	@RequestMapping({ "/index", "" })
	public ModelAndView index(String key) {
		ModelAndView modelAndView = new ModelAndView("/category/list");
		if (StringUtils.isNotEmpty(key)) {
			key = key.trim();
			modelAndView.addObject("key", key);
			doubanService.saveBySearch(key);
		}
		return modelAndView;
	}

	@RequestMapping("/list")
	@ResponseBody
	public PageInfo<Subject> list(int pageNo, String year, String place, String type, String sort, String key) {
		return subjectService.listBySearch(pageNo, 6, year, place, type, sort, key);
	}

}
