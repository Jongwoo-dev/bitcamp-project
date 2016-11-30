package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

import bitcamp.java89.ems.server.annotation.Component;
import bitcamp.java89.ems.server.annotation.RequestMapping;
import bitcamp.java89.ems.server.annotation.RequestParam;
import bitcamp.java89.ems.server.dao.TeacherDao;
import bitcamp.java89.ems.server.vo.Teacher;

@Component // ApplicationContext가 관리하는 대상 클래스임을 표시한다.
public class TeacherController {
  // 의존 객체 DAO를 저장할 변수 선언
  TeacherDao teacherDao;

  // 의존 객체 주입할 때 호출할 셋터 추가.
  public void setTeacherDao(TeacherDao teacherDao) {
    this.teacherDao = teacherDao;
  }

  // teacher/add?userid=hong2&password=1234&name=홍길동&email=hong@test.com&tel=111-1111&age=39&subject=자바&carrer=10&salary=8500&address=서울
  @RequestMapping(value="teacher/add")
  public void add(
      @RequestParam("userid") String userId,
      @RequestParam("password") String password,
      @RequestParam("name") String name,
      @RequestParam("email") String email,
      @RequestParam("tel") String tel,
      @RequestParam("age") int age,
      @RequestParam("subject") String subject,
      @RequestParam("carrer") int carrer,
      @RequestParam("salary") int salary,
      @RequestParam("address") String address,
      PrintStream out) throws Exception {
    if (teacherDao.existUserId(userId)) {
      out.println("같은 아이디가 존재합니다. 등록을 취소합니다.");
      return;
    }

    Teacher teacher = new Teacher();
    teacher.setUserId(userId);
    teacher.setPassword(password);
    teacher.setName(name);
    teacher.setEmail(email);
    teacher.setTel(tel);
    teacher.setAge(age);
    teacher.setSubject(subject);
    teacher.setCarrer(carrer);
    teacher.setSalary(salary);
    teacher.setAddress(address);

    teacherDao.insert(teacher);
    out.println("등록하였습니다.");
  }

  @RequestMapping(value="teacher/delete")
  public void delete(@RequestParam("userid") String userId, PrintStream out) throws Exception {
    if (!teacherDao.existUserId(userId)) {
      out.println("해당 데이터가 없습니다.");
      return;
    }

    teacherDao.delete(userId);
    out.println("해당 데이터를 삭제 완료하였습니다.");
  }

  @RequestMapping(value="teacher/list")
  public void list(PrintStream out) throws Exception {
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

  // teacher/update?userid=hong2&password=4444&name=신세계&email=newworld@test.com&tel=777-7777&age=29&subject=C#&carrer=5&salary=5500&address=인천
  @RequestMapping(value="teacher/update")
  public void update(
      @RequestParam("userid") String userId,
      @RequestParam("password") String password,
      @RequestParam("name") String name,
      @RequestParam("email") String email,
      @RequestParam("tel") String tel,
      @RequestParam("age") int age,
      @RequestParam("subject") String subject,
      @RequestParam("carrer") int carrer,
      @RequestParam("salary") int salary,
      @RequestParam("address") String address,
      PrintStream out) throws Exception {
    if (!teacherDao.existUserId(userId)) {
      out.println("유저아이디를 찾지 못했습니다.");
      return;
    }

    Teacher teacher = new Teacher();
    teacher.setUserId(userId);
    teacher.setPassword(password);
    teacher.setName(name);
    teacher.setEmail(email);
    teacher.setTel(tel);
    teacher.setAge(age);
    teacher.setSubject(subject);
    teacher.setCarrer(carrer);
    teacher.setSalary(salary);
    teacher.setAddress(address);

    teacherDao.update(teacher);
    out.println("변경하였습니다.");
  }

  //teacher/view?userid=hong2
  @RequestMapping(value="teacher/view")
  public void view(@RequestParam("userid") String userId, PrintStream out) throws Exception {
    // 주입 받은 teacherDao를 사용할 것이기 때문에
    // 더이상 이 메서드에서 TeacherDao 객체를 준비하지 않는다.
    // => 단 이 메서드가 호출되기 전에 반드시 TeacherDao가 주입되어 있어야 한다.
    ArrayList<Teacher> list = teacherDao.getListByUserId(userId);

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
