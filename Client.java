import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Client {
   public static void main(String[] args) throws URISyntaxException {
       
       if (args.length != 2) {
           //System.err.println("Usage: java EchoClient <host name> <port number>");
           System.exit(1);
       }

       String hostName = args[0];
       int portNumber = Integer.parseInt(args[1]);

     try{
        Socket s = new Socket(hostName, portNumber);
           PrintWriter out = new PrintWriter(s.getOutputStream(), true);
           BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
		while(true){
			
			System.out.println(in.readLine());
			Scanner input = new Scanner(System.in);//reads student id from client
			String inputLine = input.nextLine();
			out.println(inputLine);
			}
       
   }catch (UnknownHostException e) {            
			System.err.println("Don't know about host " + hostName);            
			System.exit(1);        
			} catch (IOException e) {            
			System.err.println("Couldn't get I/O for the connection to " +                
			hostName);            
			System.exit(1);        
			} 
   }
}

//  final URI aaa = new URI("https://www.bing.com/videos/search?q=ontario+tech+campus+recreation+sport&&view=detail&mid=944244A9BA59C343A75E944244A9BA59C343A75E&&FORM=VRDGAR&ru=%2Fvideos%2Fsearch%3Fq%3Dontario%2Btech%2Bcampus%2Brecreation%2Bsport%26FORM%3DHDRSC3");
	    
	   // play(aaa);
//	}
//	public static void play(URI aaa) {
	//	if )Desktop.isDesktopSupported()) {
	//		try {
			//	Desktop.getDesktop().browse(uri);
			//}
			
	//	}catch (IOException e) {
		//	e.printStackTrace();		
		//	}
	
	
