package bitcamp.java89.ems.server.dao;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import bitcamp.java89.ems.server.vo.Teacher;

public class TeacherDao {
  static TeacherDao obj;
  private String filename = "teacher-v1.8.data";
  private ArrayList<Teacher> list;

  private TeacherDao() {
    this.load();  // 기존의 데이터 파일을 읽어서 ArrayList에 학생 정보를 로딩한다. 
  }
  
  public static TeacherDao getInstance() {
    if (obj == null) {
      obj = new TeacherDao();
    }
    return obj;
  }

  @SuppressWarnings("unchecked")
  private void load() {
    //파일에서 정보 읽어오는 메소드
    try (
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(this.filename));){
      
      list = (ArrayList<Teacher>)in.readObject();
      
    } catch (EOFException e) {
      // 파일을 모두 읽었다.
    } catch (Exception e) {
      System.out.println("강사 데이터 로딩 중 오류 발생!");
      list = new ArrayList<Teacher>();
    }
  }

  public void save() throws Exception {
    // 파일에 저장한다.
    try (
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(this.filename)); ) {

    out.writeObject(list);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public ArrayList<Teacher> getList() {
    return this.list;
  }
  
  public ArrayList<Teacher> getListByUserId(String userId) {
    ArrayList<Teacher> results = new ArrayList<>();
    
    for (Teacher teacher : list) {
      if (teacher.getUserId().equals(userId)) {
        results.add(teacher);
      }
    }
    return results;
  }

  synchronized public void insert(Teacher teacher) {
    list.add(teacher);
    try {this.save();} catch (Exception e) {}
  }
  
  synchronized public void update(Teacher teacher) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getUserId().equals(teacher.getUserId())) {
        list.set(i, teacher);
        try {this.save();} catch (Exception e) {}
        return;
      }
    }
  }

  synchronized public void delete(String userId) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getUserId().equals(userId)) {
        list.remove(i);
        try {this.save();} catch (Exception e) {}
        return;
      }
    }
  }
  
  public boolean existUserId(String userId) {
    for (Teacher teacher : list) {
      if (teacher.getUserId().toLowerCase().equals(userId.toLowerCase())) {
        return true;
      }
    }
    return false;
  }
}
