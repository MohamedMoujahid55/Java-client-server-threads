package pack0;

import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
	
	public static void main(String []args) {
		ServerSocket ss=null;
		Socket s=null;
		
		try {
			ss=new ServerSocket(3030);

			while(true) {
				System.out.println("attend client in port 3030....");
				s=ss.accept();
				System.out.println("client connecter");
				Service sr = new Service(s);
				sr.start();
				//sr.join();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
