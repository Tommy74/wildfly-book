package org.wildfly.example;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/demo")
public class DemoServlet extends HttpServlet {
	@Resource(lookup="java:jboss/datasources/postgresqlDS")
	private DataSource ds;


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.write("<h1>Datasource example</h1>");

		try (Connection con = ds.getConnection();
			 PreparedStatement ps = createPreparedStatement(con);
			 ResultSet rs = ps.executeQuery()) {

			while(rs.next()) {
				out.write("Time from Database: " +rs.getString(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}


	}
	private PreparedStatement createPreparedStatement(Connection con) throws SQLException {
		String sql = "SELECT NOW();";
		PreparedStatement ps = con.prepareStatement(sql);
		return ps;
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
