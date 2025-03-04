package com.sagar.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import com.sagar.security.model.Blog;
import com.sagar.security.service.BlogService;
import com.sagar.security.repository.BlogRepository;
/*import com.sagar.security.Blog;
import com.sagar.security.BlogRepository;
import com.sagar.security.BlogService;*/
import java.util.List;



@Controller
public class HomeController {
    @Autowired
	BlogService blogService;
        
	@RequestMapping("/")
	public String Home() {
		RequestMappingHandlerAdapter rmha = new RequestMappingHandlerAdapter();
		rmha.setCacheSeconds(0);
		return "home";
		
	}
	
	@RequestMapping("/logout")
	public String Logout() {
		return "logout";
		
	}
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	 public ModelAndView list() {
	  ModelAndView model = new ModelAndView("blog_list");
	 
	  //jpa
	  /*List<Blog> blogList = blogService.getallBlogs();
	  model.addObject("blogList", blogList);*/
	  
	  //using hql
	  Query q = (Query) em.createQuery("from Blog");
	  List<Blog> blog = q.getResultList();
	  model.addObject("blogList", blog);
	  
	  //hql practise:
	  q = (Query) em.createQuery("from Blog where title = :title"); 
	  //blog = q.getResultList(); 
	  q.setParameter("title", "blog");
	  List<Blog> blogs = q.getResultList();
	  System.out.println(blogs.get(0).getCategory());
	  
	  q = (Query) em.createQuery("select id,title,category from Blog where title = :title"); 
	  q.setParameter("title", "blog");
	  Object[] data = (Object[]) q.uniqueResult();
	  System.out.println("select id,title,category from Blog where title = :title:" +data[0]+","+data[1]+","+data[2]);
		/*
		  for(Blog b:blog) { System.out.println("HQL:"+b.getTitle()+b.getCategory()); }
		 */
		 
	  return model;
	 
	 }
	 
	 @RequestMapping(value="/addBlog/", method=RequestMethod.GET)
	 public ModelAndView addBlog() {
	  ModelAndView model = new ModelAndView();
	  
	  Blog blog = new Blog();
	  model.addObject("blogForm", blog);
	  model.setViewName("blog_form");
	  
	  return model;
	 }
	 
	 @RequestMapping(value="/updateBlog/{id}", method=RequestMethod.GET)
	 public ModelAndView editArticle(@PathVariable long id) {
	  ModelAndView model = new ModelAndView();
	  
	  Blog blog = blogService.getBlogbyId(id);
	  model.addObject("blogForm", blog);
	  model.setViewName("blog_form");
	  
	  return model;
	 }
	 
	 @RequestMapping(value="/saveBlog", method=RequestMethod.POST)
	 public ModelAndView save(@ModelAttribute("blogForm") Blog blog) {
	  blogService.saveorUpdate(blog);
	  
	  return new ModelAndView("redirect:/list");
	 }
	 
	 @RequestMapping(value="/deleteBlog/{id}", method=RequestMethod.GET)
	 public ModelAndView delete(@PathVariable("id") long id) {
	  blogService.deleteBlog(id);
	  
	  return new ModelAndView("redirect:/list");
	 }
}
