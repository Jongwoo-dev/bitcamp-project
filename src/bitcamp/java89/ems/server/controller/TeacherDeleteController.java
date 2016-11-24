package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.HashMap;

import bitcamp.java89.ems.server.Command;
import bitcamp.java89.ems.server.dao.TeacherDao;

public class TeacherDeleteController implements Command {
  private TeacherDao teacherDao;

  public TeacherDeleteController() {
    teacherDao = TeacherDao.getInstance();
  }

  public void service(HashMap<String,String> paramMap, PrintStream out) {
    if (!teacherDao.existUserId(paramMap.get("userid"))) {
      out.println("해당 데이터가 없습니다.");
      return;
    }
    
    teacherDao.delete(paramMap.get("userid"));
    out.println("해당 데이터를 삭제 완료하였습니다.");
  }
}
