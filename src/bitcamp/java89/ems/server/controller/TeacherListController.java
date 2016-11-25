package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

import bitcamp.java89.ems.server.Command;
import bitcamp.java89.ems.server.dao.TeacherDao;
import bitcamp.java89.ems.server.vo.Teacher;

public class TeacherListController implements Command {
  
  public void service(HashMap<String,String> paramMap, PrintStream out) {
    try {
      TeacherDao teacherDao = TeacherDao.getInstance();
      ArrayList<Teacher> list = teacherDao.getList();

      for (Teacher teacher : list) {
        out.printf("%s, %s, %s, %s, %s, %d, %s, %d, %d, %s\n",
            teacher.getUserId(),
            teacher.getPassword(),
            teacher.getName(),
            teacher.getEmail(),
            teacher.getTel(),
            teacher.getAge(),
            teacher.getSubject(),
            teacher.getCarrer(),
            teacher.getSalary(),
            teacher.getAddress());
      }
    } catch (Exception e) {
      out.println("작업중 예외가 발생하였습니다.");
      e.printStackTrace();
    }
  }
}
