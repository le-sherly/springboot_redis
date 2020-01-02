package com.entor.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.entor.entity.Student;
import com.entor.mapper.StudentMapper;
import com.entor.service.IStudentService;

@Service
@CacheConfig(cacheNames="students")
public class StudentServiceImpl implements IStudentService{

	@Autowired
	private StudentMapper studentMapper;
	@Override
	public void add(Student student) {
		// TODO Auto-generated method stub
		studentMapper.add(student);
	}

	@Override
	public void update(Student student) {
		// TODO Auto-generated method stub
		studentMapper.update(student);
	}

	@Override
	@CacheEvict(allEntries=true)//清除改组中的所有缓存
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		studentMapper.deleteById(id);
	}

	@Override
	//先从缓存中查询是否有数据,如果有则直接返回,否则查询数据库返回值保存在缓存中,把方法返回值保存到缓存中,如果传递id是23,则保持到redis中的key的名称是student_23
	@Cacheable(key="'student_'+#id")
	public Student queryById(int id) {
		// TODO Auto-generated method stub
		return studentMapper.queryById(id);
	}

	@Override
	@Cacheable(key="'student_'+#p0+'_'+#p1")
	public List<Student> queryByPage(int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return studentMapper.queryByPage((currentPage-1)*pageSize, pageSize);
	}

	@Override
	public List<Student> queryAll() {
		// TODO Auto-generated method stub
		return studentMapper.queryAll();
	}

}
