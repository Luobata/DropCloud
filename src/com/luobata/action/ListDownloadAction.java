package com.luobata.action;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.luobata.dao.FileDao;
import com.luobata.util.DbUtil;
import com.opensymphony.xwork2.ActionSupport;

public class ListDownloadAction extends ActionSupport{

	private String fileName;
    private String delIds;
	
    public String getFileName() {
        return fileName;
    }

	public String getDelIds() {
		return delIds;
	}

	public void setDelIds(String delIds) {
		this.delIds = delIds;
	}
	DbUtil dbUtil=new DbUtil();
	FileDao fileDao=new FileDao();
	 public void setFileName(String fileName) throws UnsupportedEncodingException {
	        //��UTF-8���±����ļ���,�����������
	        this.fileName = new String(fileName.getBytes("ISO-8859-1"),"UTF-8");
	    }


		/**
		 * ���ض������
		 * ʵ�֣����������ѹ����zip��,Ȼ��������zip��
		 */
	 public InputStream getInputStream() throws UnsupportedEncodingException, FileNotFoundException{
	        HttpServletResponse response = ServletActionContext.getResponse();
	        //attachment,�Ը����ķ�ʽ�����ļ�,��򿪱����ļ��Ի���;inline,�������ķ�ʽ����,�������ֱ�Ӵ��ļ�
	        response.setHeader("Content-Disposition", "attachment;fileName="
	                  + java.net.URLEncoder.encode(fileName,"UTF-8"));//java.net.URLEncoder.encode(fileName,"UTF-8")  ����ת�����������
	          
	        //���fileName�����·��
	        //return ServletActionContext.getServletContext().getResourceAsStream(fileName);
	        //���fileName�Ǿ���·��
	        return new BufferedInputStream(new FileInputStream(fileName));
	    }
	 
	 
	 
	 
	 
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return SUCCESS;
	}
	

	 
    
}
