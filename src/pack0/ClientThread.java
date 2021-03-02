package pack0;

import java.io.*;
import java.net.*;
import java.util.*;

public class ClientThread {
	
	private static String readStr(BufferedInputStream bis) throws Exception {
		String res="";
		int ch;
		while((ch = bis.read()) != '\n') {
			res += (char) ch;
		}
		
		return res;
	}

	public static void main(String[] args) throws IOException {
		Socket s=null;
		InetAddress ad;
		try {
			Byte res ;
			s=new Socket(InetAddress.getLocalHost(),3030);
			System.out.println("connecter");
			BufferedInputStream bis=new BufferedInputStream(s.getInputStream());
			BufferedOutputStream bos=new BufferedOutputStream(s.getOutputStream());
			Scanner sc=new Scanner(System.in);
			
			String str = readStr(bis);
			System.out.println("Client: "+str);
			
			System.out.print("Client: Giv me the name : ");
			String name = sc.nextLine();
			
			bos.write((name+"\n").getBytes());
			bos.flush();
			
			res = (byte) bis.read();
			System.out.println("res ::: "+res.toString());
			
			if(res == (byte)1) {
				// download image
				File fimg = new File("Downloads\\"+name);
				BufferedOutputStream bosImg =new BufferedOutputStream(new FileOutputStream(fimg));
				int n;
				while((n  = bis.read()) != -1 ) {
					bosImg.write(n);
				}
				  System.out.println("terminée");
				bosImg.close();
				
				
			}else{
				//else bay bay
				System.out.println("File not exist---");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if(s!=null) s.close();
		}
	}
}
