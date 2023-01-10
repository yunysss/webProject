package com.br.notice.model.dao;

import static com.br.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.br.notice.model.vo.Notice;

public class NoticeDao {
	
	private Properties prop = new Properties();
	
	public NoticeDao() {
		try {
			prop.loadFromXML(new FileInputStream(NoticeDao.class.getResource("/db/sql/notice-mapper.xml").getPath()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Notice> selectNoticeList(Connection conn){
		// select문 => ResultSet(여러행) => ArrayList<Notice> 객체
		ArrayList<Notice> list = new ArrayList<>(); // 텅빈리스트
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectNoticeList");
		
		try {
			pstmt = conn.prepareStatement(sql); // 완성된 sql
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				list.add(new Notice(rset.getInt("notice_no"),
						            rset.getString("notice_title"),
						            rset.getString("user_id"),
						            rset.getInt("count"),
						            rset.getDate("create_date")
						            ));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}
	
	public int insertNotice(Connection conn, Notice n) {
		// insert문 => 처리된 행수
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertNotice");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, n.getNoticeTitle());
			pstmt.setString(2, n.getNoticeContent());
			//pstmt.setInt(3, Integer.parseInt(n.getNoticeWriter()));
			pstmt.setString(3, n.getNoticeWriter()); // 오라클은 문자열안에 오로지 숫자가 있으면 숫자형으로 자동 형변환
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

}
