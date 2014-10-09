
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="s" uri="/struts-tags" %>
 <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>DropCloud</title>
<%
   	if(session.getAttribute("currentUser")==null){
   		response.sendRedirect("hello.jsp");
    	return;
   	}
%>
<link rel="stylesheet" type="text/css" href="css/common.css">
  

</head>
<body>

<div class="head">

	<div class="logo">
		
	</div>
	<div class="head_other">
		<div class="head_content"> 
			<input type="text" class="search_text" name="serarch_text" value="">
			<input type="button" class="search_button" onclick="getList('listfile.action')" value="搜索">
			
			<span class="loginout">
				<a href="">
				注销
				</a>
			</span>
			<span class="username">
				${currentUser.userName}
			</span>
		</div>
	</div>
	
	
</div>

<div class="left">
	<div class="select">
		<span>
			<a href="javascript:selectByType('all')">全部文件
			</a>
		</span>
	</div>
	<div class="select">
		<span>
			<a href="javascript:selectByType('img')">图片</a>
		</span>
	</div>
	<div class="select">
		<span>
			<a href="javascript:selectByType('doc')">文档</a>
		</span>
	</div>
	<div class="select">
		<span>
			音乐
		</span>
	</div>
	<div class="select">
		<span>
			视频
		</span>
	</div>
	<div class="select">
		<span>
			其他
		</span>
	</div>

</div>
<div class="right"> 
	<div class="content">
		<div class=content_head>
			<div class="upload">
			    <form action="upload.action" id="fileUpload" enctype="multipart/form-data" method="post">
			    <input type="button" class="upload_button" value="上传文件"/>
			    <input type="file" multiple name="file" class="file" id="file" accept="" />
			    </form>
			</div>

			<div class="download">
			   <input type="button" class="download_button"  onclick="dow()"value="下载文件"/>
		    </div>

		    <div class="delete">
		       <input type="button" class="delete_button" onclick="del()" value="删除文件"/>
		    </div>
			   
			    
			
			<div class="newfloder">
			     <input type="button" class="newfloder_button"  value="新建文件夹"/>
			</div>



			
			<p class="sum_f">
			全部7个文件
			</p>
		</div>

			<form name="frmUser" id="frmUser" method="post">
		<div class="tb">
			<table class="file_list" id="file_list"cellspacing="0">
				<thead> 
				<tr> 
   					<td class="ck_box"><input type="checkbox" onclick="sel(this)" id="id_all" name="id_all"></th> 
    				<td class="filename">文件名</th> 
    				<td class="filesize">文件大小</th> 
    				<td class="date">修改日期</th>  
    				<td class="root"></th>
				</tr> 
				</thead> 
				<tbody>
					
					
					
				</tbody>
			
			</table>
		</div>
		</form>
	</div>
</div>

<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="js/index.js"></script>
 <script type="text/javascript" src="<%=path %>/js/jquery.js"></script>
<script type="text/javascript" src="<%=path %>/js/jquery.Jcrop.js"></script>
<script type="text/javascript" src="<%=path %>/js/json2.js"></script>
<script type="text/javascript" src="<%=path %>/js/jquery.form.min.js"></script>
  <script type="text/javascript" src="js/upload2.js"></script>
  <script>  
    function sel(obj){   
        _tlsCheckboxSelAll(document.frmUser,obj);   
    }   
    
    
</script>
</body>
</html>