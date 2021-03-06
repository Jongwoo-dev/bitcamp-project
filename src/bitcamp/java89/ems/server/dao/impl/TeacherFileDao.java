package bitcamp.java89.ems.server.dao.impl;

import java.util.ArrayList;

import bitcamp.java89.ems.server.dao.TeacherDao;
import bitcamp.java89.ems.server.vo.Teacher;

//@Component  // ApplicationContext가 관리하는 클래스임을 표시하기 위해 태그를 단다.
public class TeacherFileDao extends AbstractFileDao<Teacher> implements TeacherDao {
  
  public TeacherFileDao() throws Exception {
    this.setFilename("teacher-v1.9.data");
    try {this.load();} catch (Exception e) {}
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
