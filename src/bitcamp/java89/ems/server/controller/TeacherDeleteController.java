package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.HashMap;

import bitcamp.java89.ems.server.AbstractCommand;
import bitcamp.java89.ems.server.dao.TeacherDao;

public class TeacherDeleteController extends AbstractCommand {
  // 의존 객체 DAO를 저장할 변수 선언
  TeacherDao teacherDao;

  // 의존 객체 주입할 때 호출할 셋터 추가.
  public void setTeacherDao(TeacherDao teacherDao) {
    this.teacherDao = teacherDao;
  }
  
  @Override
  public String getCommandString() {
    return "teacher/delete";
  }
  
  @Override
  protected void doResponse(HashMap<String,String> paramMap, PrintStream out) throws Exception {
    // 주입 받은 teacherDao를 사용할 것이기 때문에
    // 더이상 이 메서드에서 TeacherDao 객체를 준비하지 않는다.
    // => 단 이 메서드가 호출되기 전에 반드시 TeacherDao가 주입되어 있어야 한다.
    if (!teacherDao.existUserId(paramMap.get("userid"))) {
      out.println("해당 데이터가 없습니다.");
      return;
    }

    teacherDao.delete(paramMap.get("userid"));
    out.println("해당 데이터를 삭제 완료하였습니다.");
  }
}
