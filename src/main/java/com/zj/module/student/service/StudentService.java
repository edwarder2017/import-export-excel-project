package com.zj.module.student.service;
/**   
*    
* 项目名称：stuSys   
* 类名称：StudentService   
* 类描述：   
* 创建人：edwarder   
* 创建时间：2018年1月8日 下午4:11:42   
*       
*/

import java.io.InputStream;
import java.util.List;

import com.zj.module.student.entity.Student;
import com.zj.module.student.vo.StudentVO;

public interface StudentService {
	/**
	 * 查询学员列表
	 */
	public List<Student> findListByCondition(StudentVO studentVO);
	
	/**
	 * 批量插入学员信息
	 */
	public void batchInsertStudents(InputStream inputStream);
}
