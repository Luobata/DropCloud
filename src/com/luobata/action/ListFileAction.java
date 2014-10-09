package com.luobata.action;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.luobata.dao.FileDao;
import com.luobata.model.User;
import com.luobata.util.DbUtil;
import com.luobata.util.JsonUtil;
import com.luobata.util.ResponseUtil;
import com.opensymphony.xwork2.ActionSupport;

public class ListFileAction extends ActionSupport implements ServletRequestAware{

	private String list;
	private String delIds; 
	private String txt;
	private String type;
	
	

	
	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getTxt() {
		return txt;
	}


	public void setTxt(String txt) {
		this.txt = txt;
	}


	public String getList() {
		return list;
	}


	public void setList(String list) {
		this.list = list;
	}


	public String getDelIds() {
		return delIds;
	}


	public void setDelIds(String delIds) {
		this.delIds = delIds;
	}






	DbUtil dbUtil=new DbUtil();
	FileDao fileDao=new FileDao();
	HttpServletRequest requset;
	
	@Override
	public String execute() throws Exception {
		
		Connection con =null;
		HttpSession session=requset.getSession();
		con=dbUtil.getCon();
		String author=((User)session.getAttribute("currentUser")).getUserName();
		//JSONObject result=new JSONObject();
		System.out.println("key:"+txt);
		JSONArray jsonArray=JsonUtil.formatRsToJsonArray(fileDao.ListFile(con, author,txt));
		
		list=jsonArray.toString();
		System.out.println(list);
		try {
			//获取session对象
			//获取sessio user
			//封装json数组
			//result.put("list", jsonArray);
            //list = jsonArray.toString();//给result赋值，传递给页面
			
			System.out.println(list);
			//ResponseUtil.write(ServletActionContext.getResponse(), result);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return SUCCESS;
	}

	public String delete() throws Exception{
		
		JSONObject result=new JSONObject();
		String str[]=delIds.split(",");
		if(fileDao.DeleteFile(dbUtil.getCon(),str)){
			result.put("success","true");
			ResponseUtil.write(ServletActionContext.getResponse(), result);
		}
		
		return null;
	}
	
	public String select()throws Exception{
		
		Connection con =null;
		HttpSession session=requset.getSession();
		con=dbUtil.getCon();
		String author=((User)session.getAttribute("currentUser")).getUserName();
		System.out.println("type:"+type);
		JSONArray jsonArray=JsonUtil.formatRsToJsonArray(fileDao.ListByType(con,author,type));
		
		list=jsonArray.toString();
		System.out.println(list);
		dbUtil.closeCon(con);
		return SUCCESS;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.requset=request;
		// TODO Auto-generated method stub
		
	}

	
	
	
	
}
