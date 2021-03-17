package com.cafe24.healthshare;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
  "file:src/main/webapp/WEB-INF/spring/root-context.xml",
  "file:src/main/webapp/WEB-INF/spring/security-context.xml"
  })
@Slf4j
public class Member001_Tests {

  @Setter(onMethod_ = @Autowired)
  private PasswordEncoder pwencoder;
  
  @Setter(onMethod_ = @Autowired)
  private DataSource ds;
 
  @Test
  public void testInsertMember() {
	  Connection conn = null;   PreparedStatement pstmt = null;
	  try {
	      conn  = ds.getConnection();
	      pstmt =  conn.prepareStatement("insert into users (userid, userpw ) values (?,? )");
//	      pstmt.setString(1,"member");
//	      pstmt.setString(2, pwencoder.encode("member"));
	      pstmt.setString(1,"admin");
	      pstmt.setString(2, pwencoder.encode("admin"));
	      
	      pstmt.executeUpdate();
      }catch(Exception e) {
        e.printStackTrace();
      }finally {
        if(pstmt != null) { try { pstmt.close();  } catch(Exception e) {} }
        if(conn != null ) { try { conn.close();  } catch(Exception e) {} }
        
      }
    } 
 
  
  @Test
  public void testInsertAuth() {
    Connection conn = null;   PreparedStatement pstmt = null;
	  try {
	      conn  = ds.getConnection();
	      pstmt =  conn.prepareStatement("insert into uauthorities (userid, auth) values (?,?)");
//	      pstmt.setString(1,"member");
//	      pstmt.setString(2,"ROLE_MEMBER");
	      pstmt.setString(1,"admin");
	      pstmt.setString(2,"ROLE_ADMIN");
	      pstmt.executeUpdate();
    }catch(Exception e) {
      e.printStackTrace();
    }finally {
      if(pstmt != null) { try { pstmt.close();  } catch(Exception e) {} }
      if(conn != null ) { try { conn.close();  } catch(Exception e) {} }
      
    }
  }

  
}


