package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.HashMap;

import bitcamp.java89.ems.server.annotation.Component;
import bitcamp.java89.ems.server.annotation.RequestMapping;
import bitcamp.java89.ems.server.dao.TeacherDao;

@Component(value="teacher/delete") // ApplicationContext가 관리하는 대상 클래스임을 표시한다.
public class TeacherDeleteController {
  // 의존 객체 DAO를 저장할 변수 선언
  TeacherDao teacherDao;

  // 의존 객체 주입할 때 호출할 셋터 추가.
  public void setTeacherDao(TeacherDao teacherDao) {
    this.teacherDao = teacherDao;
  }
  
  @RequestMapping
  public void delete(HashMap<String,String> paramMap, PrintStream out) throws Exception {
    if (!teacherDao.existUserId(paramMap.get("userid"))) {
      out.println("해당 데이터가 없습니다.");
      return;
    }

    teacherDao.delete(paramMap.get("userid"));
    out.println("해당 데이터를 삭제 완료하였습니다.");
  }
}
