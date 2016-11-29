package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

import bitcamp.java89.ems.server.annotation.Component;
import bitcamp.java89.ems.server.annotation.RequestMapping;
import bitcamp.java89.ems.server.dao.ContactDao;
import bitcamp.java89.ems.server.vo.Contact;

@Component(value="contact/view") // ApplicationContext가 관리하는 대상 클래스임을 표시한다.
public class ContactViewController {
  // 의존 객체 DAO를 저장할 변수 선언
  ContactDao contactDao;

  // 의존 객체 주입할 때 호출할 셋터 추가.
  public void setContactDao(ContactDao contactDao) {
    this.contactDao = contactDao;
  }
  
  //view?name=홍길동
  @RequestMapping 
  public void view(HashMap<String,String> paramMap, PrintStream out) throws Exception {
    ArrayList<Contact> list = contactDao.getListByName(paramMap.get("name"));
    for (Contact contact : list) {
      out.println("---------------------------");
      out.printf("이름: %s\n", contact.getName());
      out.printf("직위: %s\n", contact.getPosition());
      out.printf("전화: %s\n", contact.getTel());
      out.printf("이메일: %s\n", contact.getEmail());
    }
  }
}
