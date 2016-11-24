package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.HashMap;

import bitcamp.java89.ems.server.Command;
import bitcamp.java89.ems.server.dao.TeacherDao;
import bitcamp.java89.ems.server.vo.Teacher;

public class TeacherUpdateController implements Command {
  private TeacherDao teacherDao;

  public TeacherUpdateController() {
    teacherDao = TeacherDao.getInstance();
  }

  // teacher/update?userid=hong2&password=4444&name=신세계&email=newworld@test.com&tel=777-7777&age=29&subject=C#&carrer=5&salary=5500&address=인천
  public void service(HashMap<String,String> paramMap, PrintStream out) {
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
