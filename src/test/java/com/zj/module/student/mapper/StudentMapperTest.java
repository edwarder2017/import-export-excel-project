package com.zj.module.student.mapper;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zj.module.student.entity.Student;
import com.zj.module.student.vo.StudentVO;

/**   
*    
* 项目名称：stuSys   
* 类名称：StudentMapperTest   
* 类描述：   
* 创建人：edwarder   
* 创建时间：2018年1月8日 下午3:43:39   
*       
*/
public class StudentMapperTest {

	private ApplicationContext applicationContext;
	
	@Before
	public void setUp() throws Exception {
		applicationContext = new ClassPathXmlApplicationContext("spring-context.xml");
	}

	@Test
	public void testSave() {
		StudentMapper studentMapper = (StudentMapper) applicationContext.getBean("studentMapper");
		Student student = new Student("jack", 20, "342425199008068218", "1585001234", "男", 
				"苏州大学", "计算机网络", "本科","0");
		studentMapper.save(student);
		
	}

	@Test
	public void testFindList() {
		StudentMapper studentMapper = (StudentMapper) applicationContext.getBean("studentMapper");
		studentMapper.findListByCondition(new StudentVO()).forEach(System.out::println);
	}

}
