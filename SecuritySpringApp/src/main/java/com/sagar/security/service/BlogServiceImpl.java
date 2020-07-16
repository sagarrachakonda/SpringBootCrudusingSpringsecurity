package com.sagar.security.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sagar.security.model.Blog;
import com.sagar.security.repository.BlogRepository;


@Service
@Transactional
public class BlogServiceImpl implements BlogService{

	@Autowired
	BlogRepository blogRepository;
	
	@Override
	public List<Blog> getallBlogs() {
		// TODO Auto-generated method stub
		return (List<Blog>) blogRepository.findAll();
	}

	@Override
	public Blog getBlogbyId(long id) {
		// TODO Auto-generated method stub
		return blogRepository.findById(id).get();
	}

	@Override
	public void saveorUpdate(Blog blog) {
		// TODO Auto-generated method stub
		blogRepository.save(blog);
	}

	@Override
	public void deleteBlog(long id) {
		// TODO Auto-generated method stub
		blogRepository.deleteById(id);
	}

}
