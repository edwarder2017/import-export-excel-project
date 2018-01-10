package com.zj.module.student.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zj.module.student.vo.StudentVO;

/**   
*    
* 项目名称：stuSys   
* 类名称：StudentServiceTest   
* 类描述：   
* 创建人：edwarder   
* 创建时间：2018年1月8日 下午4:35:54   
*       
*/
@RunWith(SpringJUnit4ClassRunner.class) // 表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = { "classpath:spring-context.xml" })
public class StudentServiceTest {
	@Autowired
	private StudentService studentService;

	@Test
	public void testFindList() {
		studentService.findListByCondition(new StudentVO()).forEach(System.out::println);
		
	}

}
