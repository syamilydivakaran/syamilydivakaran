package com.springmvc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.springmvc.beans.StudentBean;

public class StudentDAO {

	JdbcTemplate template;

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	public int save(StudentBean stu) {
		String sql = "insert into student(name,mark) " + "values('" + stu.getName() + "'," + stu.getMark() + ")";
		return template.update(sql);
	}

	public List<StudentBean> getStudentDetails() {
		return template.query("select * from student", new RowMapper<StudentBean>() {
			public StudentBean mapRow(ResultSet rs, int row) throws SQLException {
				StudentBean stu = new StudentBean();
				stu.setId(rs.getInt(1));
				stu.setName(rs.getString(2));
				stu.setMark(rs.getFloat(3));

				return stu;
			}
		});
	}

	public StudentBean getStudentDetailsById(int id) {
		String sql = "select * from student where id=?";
		return template.queryForObject(sql, new Object[] { id },
				new BeanPropertyRowMapper<StudentBean>(StudentBean.class));
	}
	
	public int update(StudentBean stu){
		String sql="update student set name='"+stu.getName()+"', mark="+stu.getMark()+""
				+ " where id="+stu.getId()+"";
		return template.update(sql);
		}
}
