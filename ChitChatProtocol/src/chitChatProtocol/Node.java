package chitChatProtocol;
import java.util.ArrayList;

public class Node 
{
	private int nodeAddress;
	private ArrayList<Integer> neighbourNodesAddrList = new ArrayList<Integer>();
	private ArrayList<Chit> chitList = new ArrayList<Chit>();
	
	public int getNodeAddress() 
	{
		return nodeAddress;
	}
	public void setNodeAddress(int nodeAddress) 
	{
		this.nodeAddress = nodeAddress;
	}
	public ArrayList<Integer> getNeighbourNodesAddrList() 
	{
		return neighbourNodesAddrList;
	}
	public void setNeighbourNodesAddrList(ArrayList<Integer> neighbourNodesAddrList) 
	{
		this.neighbourNodesAddrList = neighbourNodesAddrList;
	}
	public ArrayList<Chit> getChitList() 
	{
		return chitList;
	}
	public void setChitList(ArrayList<Chit> chitList) 
	{
		this.chitList = chitList;
	}    
}
