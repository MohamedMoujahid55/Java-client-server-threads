package pack0;
import java.io.*;
import java.net.*;
import java.util.*;

public class Service extends Thread{
	Socket s=null;
	
	public Service(Socket soc) {
		this.s=soc;
		
	}
	private String readStr(BufferedInputStream bis) throws Exception {
		String res="";
		int ch;
		while((ch = bis.read()) != '\n') {
			res += (char) ch;
		}
		
		return res;
	}
	
	public void run(){
		
		try {
			BufferedOutputStream bos=new BufferedOutputStream(s.getOutputStream());
			BufferedInputStream bis=new BufferedInputStream(s.getInputStream());
			File fpub = new File("public");
			File image = null;
			
			System.out.println("SERVER: I'am ready for you");
			bos.write("I'am ready for you\n".getBytes());
			bos.flush();
			
			String name = readStr(bis);
			
			for(File f : fpub.listFiles()) {
				if(f.getName().equals(name)) {
					image = f;
				}
			}
			if(image != null) {
				bos.write(1);
				bos.flush();
				
				BufferedInputStream bisImg =new BufferedInputStream(new FileInputStream(image));
				//byte[] buf = new byte[8];
				int n ;
				while((n = bisImg.read()) != -1 ) {
					bos.write(n);
				}
				System.out.println("SERVEUR;; FIN**********");
				bos.flush();
				bos.close();
			}else {
				bos.write(0);
				bos.flush();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
