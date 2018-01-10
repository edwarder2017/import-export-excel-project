package com.zj.module.student.entity;
/**   
*    
* 项目名称：stuSys   
* 类名称：Student   
* 类描述：  学生实体类
* 创建人：edwarder   
* 创建时间：2018年1月8日 下午3:15:03   
*       
*/

public class Student{

	/**
	 * 
	 */
	private Integer id;
	private String name;
	private Integer age;
	private String idNumber; //身份证号
	private String tel;	//电话
	private String gender;	//性别
	private String school;	//学校
	private String speciality;	//专业
	private String qualification;	//学历
	private String delFlag;	//删除标识位
	
	public Student() {
		super();
	}
	public Student(String name, Integer age, String idNumber, String tel, String gender, String school,
			String speciality, String qualification,String delFlag) {
		super();
		this.name = name;
		this.age = age;
		this.idNumber = idNumber;
		this.tel = tel;
		this.gender = gender;
		this.school = school;
		this.speciality = speciality;
		this.qualification = qualification;
		this.delFlag = delFlag;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getSpeciality() {
		return speciality;
	}
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	
	public String getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(String delFlag) {
		this.delFlag = "0";
	}
	
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", age=" + age + ", idNumber=" + idNumber + ", tel=" + tel
				+ ", gender=" + gender + ", school=" + school + ", speciality=" + speciality + ", qualification="
				+ qualification + ", delFlag=" + delFlag + "]";
	}
	
}
