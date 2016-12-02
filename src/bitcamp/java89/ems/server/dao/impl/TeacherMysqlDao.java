package bitcamp.java89.ems.server.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import bitcamp.java89.ems.server.annotation.Component;
import bitcamp.java89.ems.server.dao.TeacherDao;
import bitcamp.java89.ems.server.util.DataSource;
import bitcamp.java89.ems.server.vo.Teacher;

@Component  // ApplicationContext가 관리하는 클래스임을 표시하기 위해 태그를 단다.
public class TeacherMysqlDao extends AbstractFileDao<Teacher> implements TeacherDao {
  DataSource ds;

  public void setDataSource(DataSource dataSource) {
    this.ds = dataSource;
  }
  
  public ArrayList<Teacher> getList() throws Exception {
    ArrayList<Teacher> list = new ArrayList<>();
    Connection con = ds.getConnection();  // 커넥션풀에서 한 개의 Connection 객체를 임대한다.
    try (
        PreparedStatement stmt = con.prepareStatement(
            "select userid, password, name, tel, email, age, subject, carrer, salary, address from ex_teachers");
        ResultSet rs = stmt.executeQuery(); ){

      while (rs.next()) { // 서버에서 레코드 한 개를 가져왔다면,
        Teacher teacher = new Teacher();
        teacher.setUserId(rs.getString("userid"));
        teacher.setPassword(rs.getString("password"));
        teacher.setName(rs.getString("name"));
        teacher.setTel(rs.getString("tel"));
        teacher.setEmail(rs.getString("email"));
        teacher.setAge(rs.getInt("age"));
        teacher.setSubject(rs.getString("subject"));
        teacher.setCarrer(rs.getInt("carrer"));
        teacher.setSalary(rs.getInt("salary"));
        teacher.setAddress(rs.getString("address"));

        list.add(teacher);
      }
    } finally {
      ds.returnConnection(con);
    }
    return list;
  }

  public ArrayList<Teacher> getListByUserId(String userId) throws Exception {
    ArrayList<Teacher> list = new ArrayList<>();
    Connection con = ds.getConnection();  // 커넥션풀에서 한 개의 Connection 객체를 임대한다.
    try (
        PreparedStatement stmt = con.prepareStatement(
            "select userid, password, name, tel, email, age, subject, carrer, salary, address"
            + " from ex_teachers where userid=?"); ){
      stmt.setString(1, userId);
      ResultSet rs = stmt.executeQuery();

      while (rs.next()) { // 서버에서 레코드 한 개를 가져왔다면,
        Teacher teacher = new Teacher();
        teacher.setUserId(rs.getString("userid"));
        teacher.setPassword(rs.getString("password"));
        teacher.setName(rs.getString("name"));
        teacher.setTel(rs.getString("tel"));
        teacher.setEmail(rs.getString("email"));
        teacher.setAge(rs.getInt("age"));
        teacher.setSubject(rs.getString("subject"));
        teacher.setCarrer(rs.getInt("carrer"));
        teacher.setSalary(rs.getInt("salary"));
        teacher.setAddress(rs.getString("address"));

        list.add(teacher);
      }
      rs.close();
    } finally {
      ds.returnConnection(con);
    }
    return list;
  }

  public void insert(Teacher teacher) throws Exception {
    Connection con = ds.getConnection();  // 커넥션풀에서 한 개의 Connection 객체를 임대한다.
    try (
        PreparedStatement stmt = con.prepareStatement(
            "insert into ex_teachers(userid,password,name,tel,email,age,subject,carrer,"
                + "salary,address) values(?,?,?,?,?,?,?,?,?,?)"); ) {

      stmt.setString(1, teacher.getUserId());
      stmt.setString(2, teacher.getPassword());
      stmt.setString(3, teacher.getName());
      stmt.setString(4, teacher.getTel());
      stmt.setString(5, teacher.getEmail());
      stmt.setInt(6, teacher.getAge());
      stmt.setString(7, teacher.getSubject());
      stmt.setInt(8, teacher.getCarrer());
      stmt.setInt(9, teacher.getSalary());
      stmt.setString(10, teacher.getAddress());


      stmt.executeUpdate();
    } finally {
      ds.returnConnection(con);
    }
  }

  public void update(Teacher teacher) throws Exception {
    Connection con = ds.getConnection();  // 커넥션풀에서 한 개의 Connection 객체를 임대한다.
    try (
        PreparedStatement stmt = con.prepareStatement(
            "update ex_teachers set password=?,name=?,tel=?,email=?,age=?,subject=?,carrer=?,"
                + "salary=?,address=? where userid=?"); ) {

      stmt.setString(1, teacher.getPassword());
      stmt.setString(2, teacher.getName());
      stmt.setString(3, teacher.getTel());
      stmt.setString(4, teacher.getEmail());
      stmt.setInt(5, teacher.getAge());
      stmt.setString(6, teacher.getSubject());
      stmt.setInt(7, teacher.getCarrer());
      stmt.setInt(8, teacher.getSalary());
      stmt.setString(9, teacher.getAddress());
      stmt.setString(10, teacher.getUserId());

      stmt.executeUpdate();
    } finally {
      ds.returnConnection(con);
    }
  }

  public void delete(String userId) throws Exception {
    Connection con = ds.getConnection();  // 커넥션풀에서 한 개의 Connection 객체를 임대한다.
    try (
        PreparedStatement stmt = con.prepareStatement(
            "delete from ex_teachers where userid=?"); ) {

      stmt.setString(1, userId);

      stmt.executeUpdate();
    } finally {
      ds.returnConnection(con);
    }
  }

  public boolean existUserId(String userId) throws Exception {
    Connection con = ds.getConnection();  // 커넥션풀에서 한 개의 Connection 객체를 임대한다.
    try (
        PreparedStatement stmt = con.prepareStatement(
            "select * from ex_teachers where userid=?"); ){

      stmt.setString(1, userId);
      ResultSet rs = stmt.executeQuery();

      if (rs.next()) { // 서버에서 레코드 한 개를 가져왔다면(존재한다면),
        rs.close();
        return true;
      } else {
        rs.close();
        return false;
      }
    } finally {
      ds.returnConnection(con);
    }
  }
}
