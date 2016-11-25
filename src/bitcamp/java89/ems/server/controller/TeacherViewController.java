package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

import bitcamp.java89.ems.server.AbstractCommand;
import bitcamp.java89.ems.server.dao.TeacherDao;
import bitcamp.java89.ems.server.vo.Teacher;

public class TeacherViewController extends AbstractCommand {

  // teacher/view?userid=hong2
  @Override
  protected void doResponse(HashMap<String,String> paramMap, PrintStream out) throws Exception {
    TeacherDao teacherDao = TeacherDao.getInstance();
    ArrayList<Teacher> list = teacherDao.getListByUserId(paramMap.get("userid"));

    for (Teacher teacher : list) {
      out.println("---------------------------");
      out.printf("아이디: %s\n", teacher.getUserId());
      out.printf("암호: (***)\n");
      out.printf("이름: %s\n", teacher.getName());
      out.printf("이메일: %s\n", teacher.getEmail());
      out.printf("전화: %s\n", teacher.getTel());
      out.printf("나이: %d\n", teacher.getAge());
      out.printf("담당과목: %s\n", teacher.getSubject());
      out.printf("경력: %d\n", teacher.getCarrer());
      out.printf("연봉: %d\n", teacher.getSalary());
      out.printf("주소: %s\n", teacher.getAddress());
    }
  }
}

