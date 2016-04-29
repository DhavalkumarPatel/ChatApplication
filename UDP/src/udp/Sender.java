package udp;
import java.net.*;
public class Sender
{
   public static void main(String[] args) throws Exception
   {
 
	String server="abc & def";	

	InetAddress ia = InetAddress.getLocalHost();

	DatagramSocket ds = new DatagramSocket();

	byte buffer[] = server.getBytes();

	DatagramPacket dp = new DatagramPacket(buffer,buffer.length,ia,5000);
		
	ds.send(dp);
    
   }

}