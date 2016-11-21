package bitcamp.java89.ems.server.controller;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import bitcamp.java89.ems.server.vo.Teacher;

public class TeacherController {
  private String filename = "teacher2.data";
  private ArrayList<Teacher> list;
  private boolean changed;
  private Scanner keyScan;

  public TeacherController(Scanner keyScan) throws Exception {
    list = new ArrayList<Teacher>();
    this.keyScan = keyScan;
    
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
      System.out.println("강사 데이터 로딩 중 오류 발생!");
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

  public void service() {
    loop:
    while (true) {
      System.out.print("강사관리> ");
      String command = keyScan.nextLine().toLowerCase();

      try {
        switch (command) {
        case "add": this.doAdd(); break;
        case "list": this.doList(); break;
        case "view": this.doView(); break;
        case "delete": this.doDelete(); break;
        case "update": this.doUpdate(); break;
        case "main": break loop;
        default:
          System.out.println("지원하지 않는 명령어입니다.");
        }
      } catch (IndexOutOfBoundsException e) {
        System.out.println("인덱스가 유효하지 않습니다.");
      } catch (Exception e) {
        System.out.println("실행 중 오류가 발생했습니다.");
      } // try
    } // while
  }

  // 아래 doXxx() 메서드들은 오직 service()에서만 호출하기 때문에
  // private으로 접근을 제한한다.
  private void doList() {
    if (list.size() == 0) {
      System.out.println("리스트가 존재하지 않습니다.");
    } else {
      for (Teacher teacher : list) {
        System.out.printf("%s, %s, %s, %s, %s, %d, %s, %d, %d, %s\n",
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

  private void doUpdate() {
    System.out.print("변경할 강사의 인덱스? ");
    int index = Integer.parseInt(this.keyScan.nextLine());

    Teacher oldTeacher = list.get(index);

    //새 강사 정보를 입력받는다.
    Teacher teacher = new Teacher();
    System.out.print("암호(예:1234)? ");
    teacher.setPassword(this.keyScan.nextLine());

    System.out.printf("이름(%s)? ", oldTeacher.getName());
    teacher.setName(this.keyScan.nextLine());

    System.out.printf("이메일(%s)? ", oldTeacher.getEmail());
    teacher.setEmail(this.keyScan.nextLine());

    System.out.printf("전화(%s)? ", oldTeacher.getTel());
    teacher.setTel(this.keyScan.nextLine());

    while (true) {
      try {
        System.out.printf("나이(%d)? ", oldTeacher.getAge());
        teacher.setAge(Integer.parseInt(this.keyScan.nextLine()));
        break;
      } catch (Exception e) {
        System.out.println("정수 값을 입력하세요.");
      }
    }

    System.out.printf("담당과목(%s)? ", oldTeacher.getSubject());
    teacher.setSubject(this.keyScan.nextLine());

    while (true) {
      try {
        System.out.printf("경력(%d)? ", oldTeacher.getCarrer());
        teacher.setCarrer(Integer.parseInt(this.keyScan.nextLine()));
        break;
      } catch (Exception e) {
        System.out.println("정수 값을 입력하세요.");
      }
    }

    while (true) {
      try {
        System.out.printf("연봉(%d)? ", oldTeacher.getSalary());
        teacher.setSalary(Integer.parseInt(this.keyScan.nextLine()));
        break;
      } catch (Exception e) {
        System.out.println("정수 값을 입력하세요.");
      }
    }

    System.out.printf("주소(%s)? ", oldTeacher.getAddress());
    teacher.setAddress(this.keyScan.nextLine());

    System.out.printf("저장하시겠습니까(y/n)? ");
    if (this.keyScan.nextLine().toLowerCase().equals("y")) {
      teacher.setUserId(oldTeacher.getUserId());
      list.set(index, teacher);
      changed = true;
      System.out.println("저장하였습니다.");
    } else {
      System.out.println("변경을 취소하였습니다.");
    }
  }

  private void doAdd() {
    while (true) {
      Teacher teacher = new Teacher();
      System.out.print("아이디(예:hong)? ");
      teacher.setUserId(this.keyScan.nextLine());

      System.out.print("암호(예:1234)? ");
      teacher.setPassword(this.keyScan.nextLine());

      System.out.print("이름(예:홍길동)? ");
      teacher.setName(this.keyScan.nextLine());

      System.out.print("이메일(예:hong@test.com)? ");
      teacher.setEmail(this.keyScan.nextLine());

      System.out.print("전화(예:010-1111-2222)? ");
      teacher.setTel(this.keyScan.nextLine());

      while (true) {
        try {
          System.out.print("나이(예:39)? ");
          teacher.setAge(Integer.parseInt(this.keyScan.nextLine()));
          break;
        } catch (Exception e) {
          System.out.println("정수 값을 입력하세요.");
        }
      }

      System.out.print("담당과목(예:자바)? ");
      teacher.setSubject(this.keyScan.nextLine());

      while (true) {
        try {
          System.out.print("경력(예:10)? ");
          teacher.setCarrer(Integer.parseInt(this.keyScan.nextLine()));
          break;
        } catch (Exception e) {
          System.out.println("정수 값을 입력하세요.");
        }
      }

      while (true) {
        try {
          System.out.print("연봉(예:8500)? ");
          teacher.setSalary(Integer.parseInt(this.keyScan.nextLine()));
          break;
        } catch (Exception e) {
          System.out.println("정수 값을 입력하세요.");
        }
      }

      System.out.print("주소(예:서울시 서초구 서초동)? ");
      teacher.setAddress(this.keyScan.nextLine());

      list.add(teacher);
      changed = true;
      
      System.out.print("계속 입력하시겠습니까(y/n)? ");
      if (!this.keyScan.nextLine().equals("y"))
        break;
    } //while
  }

  private void doView() {
    System.out.print("강사의 인덱스? ");
    int index = Integer.parseInt(this.keyScan.nextLine());

    Teacher teacher = list.get(index);

    System.out.printf("아이디: %s\n", teacher.getUserId());
    System.out.printf("암호: (***)\n");
    System.out.printf("이름: %s\n", teacher.getName());
    System.out.printf("이메일: %s\n", teacher.getEmail());
    System.out.printf("전화: %s\n", teacher.getTel());
    System.out.printf("나이: %d\n", teacher.getAge());
    System.out.printf("담당과목: %s\n", teacher.getSubject());
    System.out.printf("경력: %d\n", teacher.getCarrer());
    System.out.printf("연봉: %d\n", teacher.getSalary());
    System.out.printf("주소: %s\n", teacher.getAddress());
  }

  private void doDelete() {
    System.out.print("삭제할 강사의 인덱스? ");
    int index = Integer.parseInt(keyScan.nextLine());
    Teacher deletedTeacher = list.remove(index);
    changed = true;
    System.out.printf("%s 강사 정보를 삭제하였습니다.\n", deletedTeacher.getUserId());
  }
}
