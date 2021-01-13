import java.net.*;
import java.util.*;
import java.io.*;

public class Server {

   public static void main(String[] args) {
       
       if (args.length != 1) {
           System.err.println("Usage: java EchoServer <port number>");
           System.exit(1);
       }
       
       int portNumber = Integer.parseInt(args[0]);
       Server es = new Server();
       es.run(portNumber);
    }

    public void run(int portNumber) {
       try {
           ServerSocket serverSocket = new ServerSocket(portNumber);
		   System.out.println("server listening");
           while(true) {
              Socket client = serverSocket.accept();
              Connection cc = new Connection(client);
           }
       } catch(Exception e) {
          System.out.println("Exception: "+e);
       }
   }
       
 
}

class Connection extends Thread {
   Socket client;
   PrintWriter out;
   BufferedReader in;
   

   public Connection(Socket s) { // constructor
      client = s;
     

      try {
          out = new PrintWriter(client.getOutputStream(), true);                  
          in = new BufferedReader(
               new InputStreamReader(client.getInputStream()));

      } catch (IOException e) {
          try {
            client.close();
          } catch (IOException ex) {
            System.out.println("Error while getting socket streams.."+ex);
          }
          return;
      }
       this.start(); // Thread starts here...this start() will call run()
   }

   
   public void run() {
	   String studentNum = "";
	   String sport = "";
	   try{
		   while(true){
			   out.println("enter 9 digits for your student ID");
		studentNum = in.readLine();
		//Checks if student Number is 9 digits
		if (studentNum.length() == 9) {
	   System.out.println("client sent student ID : " + studentNum);
	   out.println("Great! You're in! Please select a sport between hockey, basketball, volleyball, baseball : ");
	   sport = in.readLine();
	   System.out.println("Client sent sport " + sport);
	   try{
		   FileWriter sportFile = new FileWriter("sports.csv", true);
		   List<List<String>> info = Arrays.asList(Arrays.asList(studentNum, sport));
			for(List<String> rowData : info) {
				sportFile.append(String.join(",", rowData));
				sportFile.append("\n");
	   }
	  sportFile.close();
	   } 
	   catch(IOException e){
		   e.printStackTrace();
		}
		}
		else {
		System.out.println("Incorrect student ID");
		}
		   }
    
      } catch (IOException e) {
          System.out.println("Exception caught...");
          System.out.println(e.getMessage());
      }
   }
}

