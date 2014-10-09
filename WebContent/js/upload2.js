 $(document).ready(function(){
	 
	 getList("listfile.action");
 });

$(function(){
			
			$("#file").bind("change",function(){
				var file = $(this).val();
				if(file==""){
					alert("请选择文件夹");
					return;
				}else{
					$("#fileUpload").ajaxForm(function(data) { 
						var obj = JSON.parse(data);
						var m=obj.message;
						alert(m);
		            }).submit();
					getList("listfile.action");
					//callback(result.list);
				}
			});
	});
	function getList(ur){
    	var txt=$('.search_text').attr("value"); 
		$.ajax({ 
    		 url : ur+"?txt="+txt,   
    		 //data : {key: txt},
    		 type : "post", 
    		 cache : false, 
    		 dataType : "json", 
    		 success:function (list){
    		    	//var json=list;
    			 var obj= $.parseJSON(list.list);
    		    	var tb = $("#file_list"); 
    		    	var str ;
    		    	if(obj==null){
    		    		tb.remove();
    		    		str="<table class='file_list' id='file_list'cellspacing='0'>"+
    		    	"<thead> <tr>"+ 
       					"<td class='ck_box'><input type='checkbox' onclick='sel(this)' id='id_all' name='id_all'></th>"+ 
        				"<td class='filename'>文件名</th>"+
        				"<td class='filesize'>文件大小</th>"+
        				"<td class='date'>修改日期</th></tr></thead><tbody></tbody></table>" ;
    		    	$('.tb').append(str);
    		    	//$('.qqq tr').remove();
    		    	tb=$("#file_list"); 
    		    	return;
    		    	}
    		    	//alert(obj.length);
    		    	tb.remove();
    		    		str="<table class='file_list' id='file_list'cellspacing='0'>"+
    		    	"<thead> <tr>"+ 
       					"<td class='ck_box'><input type='checkbox' onclick='sel(this)' id='id_all' name='id_all'></th>"+ 
        				"<td class='filename'>文件名</th>"+
        				"<td class='filesize'>文件大小</th>"+
        				"<td class='date'>修改日期</th></tr></thead><tbody></tbody></table>" ;
    		    	$('.tb').append(str);
    		    	//$('.qqq tr').remove();
    		    	tb=$("#file_list"); 
    		    	 for(var i=0;i<obj.length;i++){ 
    		    		// alert("sss");
    		    		 str="<tr><td class='ck_box'><input type='checkbox' onclick='sel(this)' value='"+obj[i].file_id+"'  name='id'></th>"+
    						"<td class='filename'>"+obj[i].file_name+"</th>"+ 
    						"<td class='filesize'>"+obj[i].file_size+"</th> "+
    						"<td class='date'>"+obj[i].date+"</th>"+
    	    				"<td class='root' value='"+obj[i].file_root_rel+"'></th></tr>";
    					tb=$("#file_list"); 	
    		    		 tb.append(str);
    		    	 }
    		    }	  
    		 }); 
    }
	
	
	function selectByType(types){
    	var txt=$('.search_text').attr("value"); 
		$.ajax({ 
    		 url : "listfile!select?type="+types,   
    		 //data : {key: txt},
    		 type : "post", 
    		 cache : false, 
    		 dataType : "json", 
    		 success:function (list){
    		    	//var json=list;
    			 var obj= $.parseJSON(list.list);
    		    	var tb = $("#file_list"); 
    		    	var str ;
    		    	//alert(obj.length);
    		    	tb.remove();
    		    		str="<table class='file_list' id='file_list'cellspacing='0'>"+
    		    	"<thead> <tr>"+ 
       					"<td class='ck_box'><input type='checkbox' onclick='sel(this)' id='id_all' name='id_all'></th>"+ 
        				"<td class='filename'>文件名</th>"+
        				"<td class='filesize'>文件大小</th>"+
        				"<td class='date'>修改日期</th></tr></thead><tbody></tbody></table>" ;
    		    	$('.tb').append(str);
    		    	//$('.qqq tr').remove();
    		    	tb=$("#file_list"); 
    		    	 for(var i=0;i<obj.length;i++){ 
    		    		// alert("sss");
    		    		 str="<tr><td class='ck_box'><input type='checkbox' onclick='sel(this)' value='"+obj[i].file_id+"'  name='id'></th>"+
    						"<td class='filename'>"+obj[i].file_name+"</th>"+ 
    						"<td class='filesize'>"+obj[i].file_size+"</th> "+
    						"<td class='date'>"+obj[i].date+"</th>"+
    	    				"<td class='root' value='"+obj[i].file_root_rel+"'></th></tr>";
    					tb=$("#file_list"); 	
    		    		 tb.append(str);
    		    	 }
    		    }	  
    		 }); 
    }
	function del(){
		var user = document.getElementsByName("id");
		var ischeck; 
		var a=new Array();
		for(i=0;i<user.length ;i++){
		if(user[i].checked==true){
		ischeck=true;
		break;
		}
		}
		if(ischeck){
		if(confirm("确定要删除么？")){
			var strIds=[];
			for(var i=0;i<user.length ;i++){
				if(user[i].checked==true){
					strIds.push(user[i].value);
				}
			}
			var ids=strIds.join(",");
			$.post("listfile!delete",{delIds:ids},function(result){
				if(result.success){
					getList("listfile.action");
				}
				else{
					alert("删除过程发生错误");
				}
			},"json");
			return	true;
		}else{

		return false;
		}
		}
		else{
		alert('请选择');
		return false;
		}} 
	
	function dow(){
		var user = document.getElementsByName("id");
		var ischeck; 
		var a=new Array();
		for(i=0;i<user.length ;i++){
		if(user[i].checked==true){
		ischeck=true;
		break;
		}
		}
		if(ischeck){
		if(confirm("确定要下载？")){
			var strIds=[];
			for(var i=0;i<user.length ;i++){
				if(user[i].checked==true){
					strIds.push(user[i].value);
				}
			}
			var ids=strIds.join(",");
			$.post("download.action",{delIds:ids},function(result){
				if(result.success){
					getList("listfile.action");
				}
				else{
					alert("下载过程发生错误");
				}
			},"json");
			return	true;
		}else{

		return false;
		}
		}
		else{
		alert('请选择');
		return false;
		}
	}
    function callback(list){
    	//var json=list;
    	var tb = $("#file_list"); 
    	var str ;
    	 for(var i=0;i<list.length;i++){ 
    		 str="<td class='ck_box'><input type='checkbox' onclick='sel(this)'  name='id'></th>"
				"<td class='filename'>"+list[i].file_name+"</th>" 
				"<td class='filesize'>"+list[i].file_size+"</th> "
				"<td class='date'>"+list[i].date+"</th>";
				tb.append(str);
    	 }
    }	
    