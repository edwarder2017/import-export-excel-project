package com.zj.module.student.service.impl;

import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zj.common.util.ExcelUtils;
import com.zj.module.student.entity.Student;
import com.zj.module.student.mapper.StudentMapper;
import com.zj.module.student.service.StudentService;
import com.zj.module.student.vo.StudentVO;

/**   
*    
* 项目名称：stuSys   
* 类名称：StudentServiceImpl   
* 类描述：   
* 创建人：edwarder   
* 创建时间：2018年1月8日 下午4:12:42   
*       
*/
@Service
public class StudentServiceImpl implements StudentService{
	@Autowired
	private StudentMapper studentMapper;
	
	@Override
	public List<Student> findListByCondition(StudentVO studentVO) {
		return studentMapper.findListByCondition(studentVO);
	}

	@Override
	public void batchInsertStudents(InputStream inputStream) {
		//从excel中获取list数据
		ExcelUtils<Student> excelUtils = new ExcelUtils<Student>(Student.class);
		List<Student> list = excelUtils.importDatas(inputStream);
		list.forEach(System.out::println);
		
		//执行mapper，数据持久化
		studentMapper.insertStudentBatch(list);
	}

}
