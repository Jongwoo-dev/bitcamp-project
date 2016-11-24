package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import bitcamp.java89.ems.server.dao.TeacherDao;
import bitcamp.java89.ems.server.vo.Teacher;

public class TeacherController {
  private Scanner in;
  private PrintStream out;
  
  private TeacherDao teacherDao;
  
  public TeacherController(Scanner in, PrintStream out) throws Exception {
    this.in = in;
    this.out = out;

    teacherDao = TeacherDao.getInstance(); 
  }


  public boolean service() {
    while (true) {
      out.println("강사관리> ");
      out.println();

      String[] commands = in.nextLine().split("\\?");

      try {
        switch (commands[0]) {
        case "add": this.doAdd(commands[1]); break;
        case "list": this.doList(); break;
        case "view": this.doView(commands[1]); break;
        case "delete": this.doDelete(commands[1]); break;
        case "update": this.doUpdate(commands[1]); break;
        case "main": return true;
        case "quit": return false;
        default:
          out.println("지원하지 않는 명령어입니다.");
        }
      } catch (IndexOutOfBoundsException e) {
        out.println("인덱스가 유효하지 않습니다.");
      } catch (Exception e) {
        out.println("실행 중 오류가 발생했습니다.");
      } // try
    } // while
  }

  // 아래 doXxx() 메서드들은 오직 service()에서만 호출하기 때문에
  // private으로 접근을 제한한다.
  private void doList() {
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

  // update?userid=hong2&password=4444&name=신세계&email=newworld@test.com&tel=777-7777&age=29&subject=C#&carrer=5&salary=5500&address=인천
  private void doUpdate(String params) {
    String[] values = params.split("&");
    HashMap<String,String> paramMap = new HashMap<>();

    for (String value : values) {
      String[] kv = value.split("=");
      paramMap.put(kv[0], kv[1]);
    }
    
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

  // add?userid=hong&password=1234&name=홍길동&email=hong@test.com&tel=111-1111&age=39&subject=자바&carrer=10&salary=8500&address=서울
  private void doAdd(String params) {

    String[] values = params.split("&");
    HashMap<String,String> paramMap = new HashMap<>();

    for (String value : values) {
      String[] kv = value.split("=");
      paramMap.put(kv[0], kv[1]);
    }
    
    if (teacherDao.existUserId(paramMap.get("userid"))) {
      out.println("같은 아이디가 존재합니다. 등록을 취소합니다.");
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

    teacherDao.insert(teacher);
    out.println("등록하였습니다.");
  }

  //view?userid=hong
  private void doView(String params) {
    String[] kv = params.split("=");
    
    ArrayList<Teacher> list = teacherDao.getListByUserId(kv[1]);

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

  //delete?userid=hong
  private void doDelete(String params) {
    String[] kv = params.split("=");
    
    if (!teacherDao.existUserId(kv[1])) {
      out.println("해당 데이터가 없습니다.");
      return;
    }
    
    teacherDao.delete(kv[1]);
    out.println("해당 데이터를 삭제 완료하였습니다.");
  }
}
