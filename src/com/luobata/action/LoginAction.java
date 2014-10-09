package com.luobata.action;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.luobata.dao.UserDao;
import com.luobata.model.User;
import com.luobata.util.DbUtil;
import com.luobata.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport implements ServletRequestAware{

	private User user;
	private String error;
	private String imageCode;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	
	
	public String getImageCode() {
		return imageCode;
	}
	public void setImageCode(String imageCode) {
		this.imageCode = imageCode;
	}


	DbUtil dbUtil=new DbUtil();
	UserDao userDao=new UserDao();
	HttpServletRequest requset;
	
	
	@Override
	public String execute() throws Exception {
	
		//��ȡsession
		HttpSession session=requset.getSession();
		if(StringUtil.isEmpty(user.getUserName())||StringUtil.isEmpty(user.getPassword_Md5())){
					error="�û���������Ϊ��";
					return ERROR;
				}
				/*if(StringUtil.isEmpty(imageCode)){
					error="��֤�벻��Ϊ��";
					return ERROR;
				}
				else if(!imageCode.equals(session.getAttribute("sRand"))){
					error="��֤�����";
					return ERROR;
				}*/
				Connection con=null;
				try {
					con=dbUtil.getCon();
					User currentUser=userDao.login(con, user);
					if(currentUser==null){
						error="�û����������";
						return ERROR;
						}
					else{
						session.setAttribute("currentUser", currentUser);
					   //�ͻ�����ת		
						return SUCCESS;
					}
				} catch (Exception e) {
					// TODO: handle exception
				}finally{
					try {
						dbUtil.closeCon(con);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				return SUCCESS;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.requset=request;
		// TODO Auto-generated method stub
		
	}
	
	
}
