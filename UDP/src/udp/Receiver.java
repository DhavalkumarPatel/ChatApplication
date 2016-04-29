package udp;
import java.net.*;
public class Receiver
{
   public static void main(String[] args) throws Exception
   {
   	
	DatagramSocket ds = new DatagramSocket(5000);

	byte buffer[] = new byte[256];

	
		DatagramPacket dp = new DatagramPacket(buffer,buffer.length);

		ds.receive(dp);
		String str = new String(dp.getData());
		System.out.println(str);
		
	
}
}