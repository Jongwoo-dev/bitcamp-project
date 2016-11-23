package bitcamp.java89.ems.server;

import java.io.IOException;
import java.net.ServerSocket;

public class EduAppServer {
  

  public static void main(String[] args) throws Exception {
    EduAppServer eduAppServer = new EduAppServer();
    eduAppServer.service();
  }

  private void service() throws IOException {
    ServerSocket ss = new ServerSocket(8888);
    System.out.println("서버 실행 중...");

    while (true) {
      new RequestThread(ss.accept()).start();
    }
  }

  
}
