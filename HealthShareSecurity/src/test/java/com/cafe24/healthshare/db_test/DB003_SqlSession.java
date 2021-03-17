package com.cafe24.healthshare.db_test;

import java.sql.Connection;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Slf4j
public class DB003_SqlSession {

	@Setter(onMethod_=@Autowired)
	private SqlSessionFactory fac;
	
	@Test
	public void test() {
		SqlSession session = fac.openSession();
		Connection conn = session.getConnection();
		if(conn!=null) {log.info("......................SqlSessionFactory............."+conn);}
	}
}
