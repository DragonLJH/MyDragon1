package com.gupt.dragon.impl.service;

import com.gupt.dragon.dao.StudentDao;
import com.gupt.dragon.service.StudentService;

public class StudentServiceImpl implements StudentService{
	private StudentDao studentDao;
	
	public void setStudentDao(StudentDao studentDao) {
		this.studentDao = studentDao;
	}
	
	@Override
	public String selectStudent() {
		return studentDao.selectStudent();
	}

}
