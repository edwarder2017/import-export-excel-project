package com.zj.module.student.mapper;
/**   
*    
* 项目名称：stuSys   
* 类名称：StudentMapper   
* 类描述：   
* 创建人：edwarder   
* 创建时间：2018年1月8日 下午3:27:26   
*       
*/

import java.util.List;

import com.zj.module.student.entity.Student;
import com.zj.module.student.vo.StudentVO;

public interface StudentMapper {
	//保存学生
	public void save(Student student);
	
	//查询学生列表
	public List<Student> findListByCondition(StudentVO studentVO);
	
	//批量保存学生信息
	public void insertStudentBatch(List<Student> studentList);
}
