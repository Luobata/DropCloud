package com.luobata.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;

import com.luobata.model.CloudFile;
import com.luobata.model.User;

public class FileDao {

	public HashSet<CloudFile> selectByType(Connection con,User user,String type,String txt) throws Exception{

		HashSet<CloudFile> hs=new HashSet<>();
		PreparedStatement pstmt = null;
		if(!type.equals("special")){
		StringBuffer sb= new StringBuffer("select * from t_file where author_name=? ");
		
		switch(type){
		
		case "all":{
			pstmt=con.prepareStatement(sb.toString());
			pstmt.setString(1, user.getUserName());
			break;
		}
		case "doc":{
			sb.append(" and file_type='txt' or file_type='doc'");
			pstmt=con.prepareStatement(sb.toString());
			pstmt.setString(1, user.getUserName());
			break;
		}
		case "img":{
			sb.append(" and file_type='jpg' or file_type='png'");
			pstmt=con.prepareStatement(sb.toString());
			pstmt.setString(1, user.getUserName());
			break;
		}
		case "music":{
			pstmt=con.prepareStatement(sb.toString());
			pstmt.setString(1, user.getUserName());
			break;
		}
		case "video":{
			pstmt=con.prepareStatement(sb.toString());
			pstmt.setString(1, user.getUserName());
			break;
		}
		case "other":{
			pstmt=con.prepareStatement(sb.toString());
			pstmt.setString(1, user.getUserName());
			break;
		}
		
		}
		}else{
			String sql="select * from t_file where author_name=? and file_name Like '%"+txt+"%'";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, user.getUserName());
		}
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()){
			CloudFile cf=new CloudFile(rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(1));
			cf.setAuthor(rs.getString(5));
			cf.setRoot_rel(rs.getString(7));
			cf.setRoot_abs(rs.getString(6));
			cf.setFile_type(rs.getString(8));
			hs.add(cf);
		}
		
		return hs;
	}
	
	public boolean add(Connection con,String author_name,CloudFile cf)throws Exception{
		
		HashSet<CloudFile> hs=new HashSet<>();
		//TODO 问好数目待定，考虑是否加入type字段，相对文件名字段
		String sql="insert into t_file values(null,?,?,?,?,?,?,?,null)";
		String type[]=cf.getFileName().split("\\.");
		String file_type=type[type.length-1];
		String date=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1,cf.getFileName());
		pstmt.setString(2,date);
		pstmt.setString(3,cf.getSize() );
		pstmt.setString(4,author_name);
		pstmt.setString(5, "/attachments/"+author_name);
		pstmt.setString(6, "/attachments/"+date);
		pstmt.setString(7, file_type);
		
		return pstmt.execute();
	}
	
	public boolean delete(Connection con,HashSet<CloudFile> cf)throws Exception{
		for (Iterator<CloudFile> it=cf.iterator();it.hasNext();) {
			//取其中一个对象
 			CloudFile s=(CloudFile)it.next();
		String sql="delete from t_file where file_id=?";
		System.out.println(s.getFile_id());
		PreparedStatement pstmt=con.prepareStatement(sql);
		//TODO model字段增加ID,或者相对名
		pstmt.setInt(1, s.getFile_id());
		//pstmt.e
		if(pstmt.execute()){
			return false;
			}
		}
		return true;
	}
	
	public ResultSet ListFile(Connection con,String author,String key)throws Exception{
		String sql;
		System.out.println(key);
		if(key.equals("")||key.equals(null)){			
		sql="select * from t_file where author_name=?";
		}else{
			sql="select * from t_file where author_name=? and file_name Like '%"+key+"%'";
		}
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, author);
		return pstmt.executeQuery();
		
	}
	public boolean DeleteFile(Connection con,String[] array)throws Exception{
		for (int i = 0; i < array.length; i++) {
			String sql="delete from t_file where file_id=?";
			PreparedStatement pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(array[i]));
			int b=pstmt.executeUpdate();
			if(b<=0){
				//删除失败
				return false;
			}
		}
		
		return true;
	}

	public String[] dow(Connection con, String[] array) throws Exception{

		String[] str = null;
		for (int i = 0; i < array.length; i++) {
			String sql="select * from t_file where file_id=?";
			PreparedStatement pstmt =con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(array[i]));
			ResultSet rs=pstmt.executeQuery();
			str[i]=rs.getString(7)+"/"+rs.getString(2);
		}
		
		return null;
	}

	public ResultSet ListByType(Connection con, String author, String type) throws Exception{
		
		
		PreparedStatement pstmt = null;
		StringBuffer sb= new StringBuffer("select * from t_file where author_name=? ");
switch(type){
		
		case "all":{
			pstmt=con.prepareStatement(sb.toString());
			pstmt.setString(1, author);
			break;
		}
		case "doc":{
			System.out.println("e");
			sb.append(" and file_type='txt' or file_type='docx'");
			pstmt=con.prepareStatement(sb.toString());
			pstmt.setString(1, author);
			break;
		}
		case "img":{
			sb.append(" and file_type='jpg' or file_type='png'");
			pstmt=con.prepareStatement(sb.toString());
			pstmt.setString(1, author);
			break;
		}
		case "music":{
			pstmt=con.prepareStatement(sb.toString());
			pstmt.setString(1, author);
			break;
		}
		case "video":{
			pstmt=con.prepareStatement(sb.toString());
			pstmt.setString(1, author);
			break;
		}
		case "other":{
			pstmt=con.prepareStatement(sb.toString());
			pstmt.setString(1, author);
			break;
		}
		
		}
		
		ResultSet rs=pstmt.executeQuery();
		return rs;
	}
}
