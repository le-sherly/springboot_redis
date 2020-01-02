package com.entor.controller;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entor.entity.Student;
import com.entor.service.IStudentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@RestController
public class StudentController {

	@Autowired
	private IStudentService iStudentService;
	
	@RequestMapping("/add")
	public void add(Student student) {
		iStudentService.add(student);
	}
	
	@RequestMapping("update")
	public void update(Student student) {
		iStudentService.update(student);
	}
	
	@RequestMapping("deleteById")
	public void deleteById(int id) {
		iStudentService.deleteById(id);
	}
	
	@RequestMapping("queryById")
	public Student queryById(int id) {
		return iStudentService.queryById(id);
	}
	
	@RequestMapping("queryByPage")
	public List<Student> queryByPage(@Param("currentPage") int currentPage,@Param("pageSize") int pageSize) {
		return iStudentService.queryByPage(currentPage, pageSize);
	}
	
	@RequestMapping("queryByPage2")
	public PageInfo<Student> queryByPage2(int currentPage,int pageSize) {
		PageHelper.startPage(currentPage,pageSize);
		List<Student> list = iStudentService.queryAll();
		PageInfo<Student> pageInfo = new PageInfo<Student>(list);
		return pageInfo;
	}
	
}
