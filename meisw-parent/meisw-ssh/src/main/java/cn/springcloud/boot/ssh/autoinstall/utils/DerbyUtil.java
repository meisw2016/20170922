package cn.springcloud.boot.ssh.autoinstall.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class DerbyUtil {

	private final Logger log = LoggerFactory.getLogger(DerbyUtil.class);

	@Autowired
	private Environment env;

	public Connection getConnection() {
		String path = Thread.currentThread().getContextClassLoader().getResource("").toString().replaceFirst("file:/", "");
		log.info("****path******:" + path);
		Connection conn = null;
		String driver = env.getProperty("derby.db.driver-class-name");
		String dbURL = env.getProperty("derby.db.url");
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(dbURL);// 启动嵌入式数据库
		} catch (Exception e) {
			log.error("获取Derby链接失败", e);
		}
		return conn;
	}

	public void test() {
		String driver = "org.apache.derby.jdbc.EmbeddedDriver";// 在derby.jar里面
		String dbName = "D:/derby/db";
		String dbURL = "jdbc:derby:" + dbName + ";create=true";// create=true表示当数据库不存在时就创建它
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(dbURL);// 启动嵌入式数据库
			Statement st = conn.createStatement();
			st.execute("create table foo (FOOID INT NOT NULL,FOONAME VARCHAR(30) NOT NULL)");// 创建foo表
			st.executeUpdate("insert into foo(FOOID,FOONAME) values (1,'chinajash')");// 插入一条数据
			ResultSet rs = st.executeQuery("select * from foo");// 读取刚插入的数据
			while (rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				System.out.println("id=" + id + ";name=" + name);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}