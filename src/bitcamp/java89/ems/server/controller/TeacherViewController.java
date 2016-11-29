package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

import bitcamp.java89.ems.server.annotation.Component;
import bitcamp.java89.ems.server.annotation.RequestMapping;
import bitcamp.java89.ems.server.dao.TeacherDao;
import bitcamp.java89.ems.server.vo.Teacher;

@Component(value="teacher/view") // ApplicationContext가 관리하는 대상 클래스임을 표시한다.
public class TeacherViewController {
  // 의존 객체 DAO를 저장할 변수 선언
  TeacherDao teacherDao;

  // 의존 객체 주입할 때 호출할 셋터 추가.
  public void setTeacherDao(TeacherDao teacherDao) {
    this.teacherDao = teacherDao;
  }
  
  // teacher/view?userid=hong2
  @RequestMapping
  public void view(HashMap<String,String> paramMap, PrintStream out) throws Exception {
    // 주입 받은 teacherDao를 사용할 것이기 때문에
    // 더이상 이 메서드에서 TeacherDao 객체를 준비하지 않는다.
    // => 단 이 메서드가 호출되기 전에 반드시 TeacherDao가 주입되어 있어야 한다.
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

