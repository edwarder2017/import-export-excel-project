<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>学生列表</title>
	<link rel="shortcut icon" href="${path }/imgs/favicon.ico" /> 
	<link rel="stylesheet" type="text/css" href="${path }/dist/css/bootstrap.min.css"/>
	<link rel="stylesheet" type="text/css" href="${path }/css/update.css"/>
	<link rel="stylesheet" href="${path }/js/jquery-upload/css/jquery.fileupload-ui.css" />
	<link rel="stylesheet" href="${path }/js/jquery-upload/css/jquery.fileupload.css" />
</head>
<body>
	
	<div class="container">
		<ol class="breadcrumb">
			<li><a href="#">学员信息总览</a></li>
			<li><a href="#">2018</a></li>
			<li class="active">1月</li>
		</ol>
	</div>

	<div class="container">
		<div>
			<form action="${path }/student/studentList" class="bs-example bs-example-form" role="form" method="post">
				<div class="row">
					<div class="col-md-3">
						<div class="input-group">
							<input type="text" name="student.name" value="${likename }"  placeholder="学生姓名进行模糊查询" class="form-control"> 
							<span class="input-group-btn">
								<button class="btn btn-default" type="submit">查询</button>
							</span>
						</div>
					</div>
					<div class="col-md-4">
						<!-- <button class="btn btn-danger" type="button" onclick="delStu()">导入</button> -->
						<%-- <a href="${path }/student/importStudent" class="btn btn-danger">导入</a> --%>
						<div class="input-group">
							<span class="btn btn-success fileinput-button">
			                    <i class="glyphicon glyphicon-plus"></i>
			                    <span>导入</span>
			                    <input type="file" name="file_upload" id="file_upload" >
			                </span>
			                <span>
				                <a href="${path }/student/export2" class="btn btn-info">导出</a>
			                </span>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>

	<div class="container">
		<div class="table-responsive">
			<table class="table table-hover">
				<caption>全部学生信息</caption>
				<thead>
					<tr class="active">
						<th><input type="checkbox" onclick="checkAll(this)" /></th>
						<th>学员名</th>
						<th>年龄</th>
						<th>身份证</th>
						<th>性别</th>
						<th>学校</th>
						<th>专业</th>
						<th>学历</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${students }" var="student">
						<tr>
							<td><input type="checkbox" value="${student.id }" name="cks" /></td>
							<td>${student.name }</td>
							<td>${student.age }</td>
							<td>${student.idNumber }</td>
							<td>${student.gender }</td>
							<td>${student.school }</td>
							<td>${student.speciality }</td>
							<td>${student.qualification }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<script type="text/javascript" src="${path }/js/jquery.min.js"></script>
	<script type="text/javascript" src="${path }/dist/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${path }/js/jquery-upload/vendor/jquery.ui.widget.js"></script>
	<script type="text/javascript" src="${path }/js/jquery-upload/jquery.iframe-transport.js"></script>
	<script type="text/javascript" src="${path }/js/jquery-upload/jquery.fileupload.js"></script>
	<script type="text/javascript" src="${path }/js/layer/layer.js"></script>
	<script type="text/javascript">
		$(function() {
		    $('#file_upload').fileupload({
		    	url: "${pageContext.request.contextPath }/student/studentUpload",
		    	type:"POST",
		        done: function (e, data) {
		        	//layer.msg(data.result);
		        	//window.location.href = "${pageContext.request.contextPath }/student/studentList";
		        	layer.msg(data.result, function(){
		        		//关闭后的操作
		        	    window.location.href = "${pageContext.request.contextPath }/student/studentList"
		        	});
		        }
		    });
		});
	</script>
</body>
</html>