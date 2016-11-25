package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

import bitcamp.java89.ems.server.AbstractCommand;
import bitcamp.java89.ems.server.dao.TeacherDao;
import bitcamp.java89.ems.server.vo.Teacher;

public class TeacherListController extends AbstractCommand {

  @Override
  protected void doResponse(HashMap<String,String> paramMap, PrintStream out) throws Exception {
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
  }
}
