package bitcamp.java89.ems.server.vo;

import java.io.Serializable;

public class Teacher implements Serializable {
  private static final long serialVersionUID = 1L;
  
  protected String userId;
  protected String password;
  protected String name;
  protected String email;
  protected String tel;
  protected int age;
  protected String subject;
  protected int carrer;
  protected int salary;
  protected String address;

  public Teacher() {}

  public Teacher(String userId, String password, String name, String tel) {
    this.userId = userId;
    this.password = password;
    this.name = name;
    this.tel = tel;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getTel() {
    return tel;
  }

  public void setTel(String tel) {
    this.tel = tel;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public int getCarrer() {
    return carrer;
  }

  public void setCarrer(int carrer) {
    this.carrer = carrer;
  }

  public int getSalary() {
    return salary;
  }

  public void setSalary(int salary) {
    this.salary = salary;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  @Override
  public String toString() {
    return "Teacher [userId=" + userId + ", password=" + password + ", name=" + name + ", email=" + email + ", tel="
        + tel + ", age=" + age + ", subject=" + subject + ", carrer=" + carrer + ", salary=" + salary + ", address="
        + address + "]";
  }
  
}
