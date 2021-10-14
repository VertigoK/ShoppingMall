package db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/*
JNDI (Java Naming and Directory Interface)

디렉토리 서비스에서 제공하는 데이터 및 객체를 검색하기 위한 Java API이다.
즉, 외부에 있는 객체를 가져오기 위한 기술이다. Tomcat 서버와 같은 WAS는 특정 폴더에 필요한
데이터 소스(라이브러리) 등을 사용하기 위해 JNDI 기술을 이용해서 정보를 가져온다.

META-INF/context.xml에 관련 변수 및 값들이 정의되어 있음
*/

public class JDBCUtility {

	public static Connection getConnection() {
		Connection conn = null;
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			DataSource ds = (DataSource) envCtx.lookup("jdbc/Shop");
			conn = ds.getConnection();
			conn.setAutoCommit(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void close(Connection conn) {
		try {
			if(conn!=null) conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Statement stmt) {
		try {
			if(stmt!=null) stmt.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Connection conn, Statement stmt) {
		try {
			if(stmt!=null) stmt.close();
			if(conn!=null) conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Statement stmt, ResultSet rs) {
		try {
			if(rs!=null) rs.close();
			if(stmt!=null) stmt.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Connection conn, Statement stmt, ResultSet rs) {
		try {
			if(rs!=null) rs.close();
			if(stmt!=null) stmt.close();
			if(conn!=null) conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void commit(Connection conn) {
		try {
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void rollback(Connection conn) {
		try {
			conn.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
