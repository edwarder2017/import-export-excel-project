package com.zj.module.student.controller;

import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.zj.common.util.ExcelPOIUtils;
import com.zj.common.util.ExcelUtils;
import com.zj.module.student.entity.Student;
import com.zj.module.student.service.StudentService;
import com.zj.module.student.vo.StudentVO;

/**   
*    
* 项目名称：stuSys   
* 类名称：StudentController   
* 类描述：   
* 创建人：edwarder   
* 创建时间：2018年1月8日 下午4:14:20   
*       
*/
@Controller()
@RequestMapping(value="/student")
public class StudentController {
	@Autowired
	private StudentService studentService;
	
	@RequestMapping("/studentList")
	public String studentList(Model model,StudentVO studentVO) throws Exception {
		List<Student> list = studentService.findListByCondition(studentVO);
		model.addAttribute("students", list);
		return "/student/studentList";
	}
	
	/**
	 * 使用Apache POI导出
	 * @param request
	 * @param response
	 * @param studentVO
	 * @throws Exception
	 */
	@RequestMapping("/export")
	public void export(HttpServletRequest request,HttpServletResponse response,StudentVO studentVO) throws Exception {
		List<Student> list = studentService.findListByCondition(studentVO);
		String[] headers = {"ID","姓名","年龄","身份证","电话","性别","学校","专业","学历","DELFLAG"};
		
		ExcelPOIUtils<Student> export = new ExcelPOIUtils<>();
		OutputStream output = response.getOutputStream();
	    response.reset();
	    response.setHeader("Content-disposition", "attachment; filename=student_details.xls");
	    response.setContentType("application/msexcel");        
		
		export.exportExcel(headers, "学员信息一览表", list, output);
		output.close();
		
        JOptionPane.showMessageDialog(null, "导出成功!");
        System.out.println("excel导出成功！");
	}
	
	/**
	 * 使用Java Excel API 导出 Excel文件
	 * @param request
	 * @param response
	 * @param studentVO
	 * @throws Exception
	 */
	@RequestMapping("/export2")
	public void export2(HttpServletRequest request,HttpServletResponse response,StudentVO studentVO) throws Exception {
		List<Student> list = studentService.findListByCondition(studentVO);
		String[] headers = {"ID","姓名","年龄","身份证","电话","性别","学校","专业","学历","DELFLAG"};
		
		ExcelUtils<Student> export = new ExcelUtils<>();
		
		OutputStream output = response.getOutputStream();
		
		response.reset();
		response.setHeader("Content-disposition", "attachment; filename=student_details.xls");
		response.setContentType("application/msexcel");        
		
		export.exportByJXL(headers, "学员信息一览表", list, output);
		output.close();
		
		JOptionPane.showMessageDialog(null, "导出成功!");
		System.out.println("excel导出成功！");
	}
	
	//上传excel
	@RequestMapping(value="/studentUpload",method={RequestMethod.POST})
	@ResponseBody
	public String studentUpload(@RequestParam("file_upload") CommonsMultipartFile filmPic) throws Exception{
		studentService.batchInsertStudents(filmPic.getInputStream());
		return "Import Success!";
	}
	
}
