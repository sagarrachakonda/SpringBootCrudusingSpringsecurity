package com.sagar.security.service;

import java.util.List;

import com.sagar.security.model.Blog;

//import com.sagar.security.Blog;

public interface BlogService {
	
	public List<Blog> getallBlogs();
	
	public Blog getBlogbyId(long id);
	
	public void saveorUpdate(Blog blog);
	
	public void deleteBlog(long id);

}
