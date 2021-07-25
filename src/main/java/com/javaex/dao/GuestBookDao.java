package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestBookVo;

@Repository
public class GuestBookDao {
	
	@Autowired
	private DataSource dataSource;
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	// 데이터베이스 연결
	private void getconnection() {
		try {
			// 2. Connection 얻어오기
			conn = dataSource.getConnection();
			// 3. SQL문 준비 / 바인딩 / 실행

			// 4.결과처리

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

	}

	// 자원 정리
	private void Close() {

		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

	}
	
	//리스트
	public List<GuestBookVo> guestbookList() {
		
		List<GuestBookVo> guestBookList = new ArrayList<GuestBookVo>();
		this.getconnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";

			query += "select no, ";
			query += "       name, ";
			query += "       password, ";
			query += "       content, ";
			query += "		 reg_date ";
			query += "from guestbook ";
			query += "order by no asc ";

			pstmt = conn.prepareStatement(query);

			rs = pstmt.executeQuery();

			// 4.결과처리
			while (rs.next()) {
				int no = rs.getInt("no");
				String name = rs.getString("name");
				String password = rs.getString("password");
				String content = rs.getString("content");
				String regDate = rs.getString("reg_date");

				GuestBookVo guestBookVo = new GuestBookVo(no,name,password,content,regDate);

				guestBookList.add(guestBookVo);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.Close();

		return guestBookList;

	}
	
	//삭제
	public int guestBookDelete(int no, String pw) {
		
		this.getconnection();

		int count = 0;
		
		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";

			query += "delete from guestbook ";
			query += "where password = ? ";
			query += "and no = ? ";

			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, pw);
			pstmt.setInt(2, no);

			count = pstmt.executeUpdate();

			// 4.결과처리

			System.out.println(count + "건 삭제되었습니다.");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.Close();
		
		return count;
	}
	
	//입력
	public int guestBookInsert(GuestBookVo guestBookVo) {
		
		this.getconnection();
		
		int count = 0;
		
		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";

			query += "insert into guestbook ";
			query += "values(seq_guestbook_no.nextval,?,?,?,sysdate) ";
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, guestBookVo.getName());
			pstmt.setString(2, guestBookVo.getPw());
			pstmt.setString(3, guestBookVo.getContent());
				
			count = pstmt.executeUpdate();

			// 4.결과처리

			System.out.println(count + "건 입력되었습니다.");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		this.Close();
		
		return count;
	}
	
}
