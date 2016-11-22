package bitcamp.java89.ems.server.controller;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import bitcamp.java89.ems.server.vo.Teacher;

public class TeacherController {
  private Scanner in;
  private PrintStream out;
  private String filename = "teacher-v1.6.data";
  private ArrayList<Teacher> list;
  private boolean changed;

  public TeacherController(Scanner in, PrintStream out) throws Exception {
    list = new ArrayList<Teacher>();
    this.in = in;
    this.out = out;

    this.load();  // 기존의 데이터 파일을 읽어서 ArrayList에 학생 정보를 로딩한다. 
  }

  public boolean isChanged() {
    return changed;
  }

  @SuppressWarnings("unchecked")
  private void load() {
    //파일에서 정보 읽어오는 메소드
    FileInputStream in0 = null;
    ObjectInputStream in = null;
    try {
      in0 = new FileInputStream(this.filename);
      in = new ObjectInputStream(in0);
      list = (ArrayList<Teacher>)in.readObject();
    } catch (EOFException e) {
      // 파일을 모두 읽었다.
    } catch (Exception e) {
      out.println("강사 데이터 로딩 중 오류 발생!");
    } finally {
      try {
        in.close();
        in0.close();
      } catch (Exception e) {
        // close하다가 예외 발생하면 무시한다.
      }
    }
  }

  public void save() throws Exception {
    // 파일에 저장한다.
    FileOutputStream out0 = new FileOutputStream(this.filename);
    ObjectOutputStream out = new ObjectOutputStream(out0);

    out.writeObject(list);

    changed = false;

    out.close();
    out0.close();
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
    if (list.size() == 0) {
      out.println("리스트가 존재하지 않습니다.");
    } else {
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
  }

  // update?userid=hong&password=4444&name=부부부&email=boo@test.com&tel=777-7777&age=29&subject=C#&carrer=5&salary=5500&address=인천
  private void doUpdate(String params) {
    String[] values = params.split("&");
    HashMap<String,String> paramMap = new HashMap<>();

    for (String value : values) {
      String[] kv = value.split("=");
      paramMap.put(kv[0], kv[1]);
    }

    for (Teacher teacher : list) {
      if (teacher.getUserId().equals(paramMap.get("userid"))) {
        teacher.setPassword(paramMap.get("password"));
        teacher.setName(paramMap.get("name"));
        teacher.setEmail(paramMap.get("email"));
        teacher.setTel(paramMap.get("tel"));
        teacher.setAge(Integer.parseInt(paramMap.get("age")));
        teacher.setSubject(paramMap.get("subject"));
        teacher.setCarrer(Integer.parseInt(paramMap.get("carrer")));
        teacher.setSalary(Integer.parseInt(paramMap.get("salary")));
        teacher.setAddress(paramMap.get("address"));
        out.println("업데이트를 완료하였습니다.");
        changed = true;
        return;
      }
    }
    out.println("해당하는 아이디가 존재하지 않습니다. 업데이트를 취소합니다.");
  }

  // add?userid=hong&password=1234&name=홍길동&email=hong@test.com&tel=111-1111&age=39&subject=자바&carrer=10&salary=8500&address=서울
  private void doAdd(String params) {

    String[] values = params.split("&");
    HashMap<String,String> paramMap = new HashMap<>();

    for (String value : values) {
      String[] kv = value.split("=");
      paramMap.put(kv[0], kv[1]);
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

    if (existUserId(teacher.getUserId())) {
      out.println("같은 아이디가 존재합니다. 등록을 취소합니다.");
      return;
    }

    list.add(teacher);
    changed = true;
    out.println("등록하였습니다.");
  }

  //view?userid=hong
  private void doView(String params) {
    String[] kv = params.split("=");
    for (Teacher teacher : list) {
      if (teacher.getUserId().equals(kv[1])) {
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

  //delete?userid=hong
  private void doDelete(String params) {
    String[] kv = params.split("=");

    for (int i = 0; i < list.size(); i++) {
      Teacher teacher = list.get(i);
      if (teacher.getUserId().equals(kv[1])) {
        Teacher deletedTeacher = list.remove(i);
        changed = true;
        out.printf("%s 강사 정보를 삭제하였습니다.\n", deletedTeacher.getUserId());     
        return;
      }
    }
    out.println("아이디가 일치하는 강사가 존재하지 않습니다.");
  }

  private boolean existUserId(String userId) {
    for (Teacher teacher : list) {
      if (teacher.getUserId().toLowerCase().equals(userId.toLowerCase())) {
        return true;
      }
    }
    return false;
  }
}
