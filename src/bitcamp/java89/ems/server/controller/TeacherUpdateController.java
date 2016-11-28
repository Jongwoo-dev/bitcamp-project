package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.HashMap;

import bitcamp.java89.ems.server.AbstractCommand;
import bitcamp.java89.ems.server.dao.TeacherDao;
import bitcamp.java89.ems.server.vo.Teacher;

public class TeacherUpdateController extends AbstractCommand {
  // 의존 객체 DAO를 저장할 변수 선언
  TeacherDao teacherDao;

  // 의존 객체 주입할 때 호출할 셋터 추가.
  public void setTeacherDao(TeacherDao teacherDao) {
    this.teacherDao = teacherDao;
  }
  
  @Override
  public String getCommandString() {
    return "teacher/update";
  }
  
  // teacher/update?userid=hong2&password=4444&name=신세계&email=newworld@test.com&tel=777-7777&age=29&subject=C#&carrer=5&salary=5500&address=인천
  @Override
  protected void doResponse(HashMap<String,String> paramMap, PrintStream out) throws Exception {
    // 주입 받은 teacherDao를 사용할 것이기 때문에
    // 더이상 이 메서드에서 TeacherDao 객체를 준비하지 않는다.
    // => 단 이 메서드가 호출되기 전에 반드시 TeacherDao가 주입되어 있어야 한다.
    if (!teacherDao.existUserId(paramMap.get("userid"))) {
      out.println("이메일을 찾지 못했습니다.");
      return;
    }

    Teacher teacher = new Teacher();
    teacher.setUserId(paramMap.get("userid"));
    teacher.setPassword(paramMap.get("password"));
    teacher.setName(paramMap.get("name"));
    teacher.setEmail(paramMap.get("email"));
    teacher.setTel(paramMap.get("tel"));
    teacher.setAge(Integer.parseInt(paramMap.get("age")));
    teacher.setSubject(paramMap.get("subject"));
    teacher.setCarrer(Integer.parseInt(paramMap.get("carrer")));
    teacher.setSalary(Integer.parseInt(paramMap.get("salary")));
    teacher.setAddress(paramMap.get("address"));

    teacherDao.update(teacher);
    out.println("변경하였습니다.");
  }
}
