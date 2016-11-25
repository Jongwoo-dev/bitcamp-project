package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.HashMap;

import bitcamp.java89.ems.server.AbstractCommand;
import bitcamp.java89.ems.server.Command;
import bitcamp.java89.ems.server.dao.TeacherDao;

public class TeacherDeleteController extends AbstractCommand {

  @Override
  protected void doResponse(HashMap<String,String> paramMap, PrintStream out) throws Exception {
    TeacherDao teacherDao = TeacherDao.getInstance();
    if (!teacherDao.existUserId(paramMap.get("userid"))) {
      out.println("해당 데이터가 없습니다.");
      return;
    }

    teacherDao.delete(paramMap.get("userid"));
    out.println("해당 데이터를 삭제 완료하였습니다.");
  }
}
