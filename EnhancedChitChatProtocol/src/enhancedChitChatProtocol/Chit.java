package enhancedChitChatProtocol;

import java.util.ArrayList;


public class Chit 
{
	private int destinationAddress;
	private int noOfHopsTraversed;
	private ArrayList<Integer> addrOfAlreadyTraversedNodes = new ArrayList<Integer>(); 
	
	public int getDestinationAddress() 
	{
		return destinationAddress;
	}
	public void setDestinationAddress(int destinationAddress) 
	{
		this.destinationAddress = destinationAddress;
	}
	public int getNoOfHopsTraversed() 
	{
		return noOfHopsTraversed;
	}
	public void setNoOfHopsTraversed(int noOfHopsTraversed) 
	{
		this.noOfHopsTraversed = noOfHopsTraversed;
	}
	public ArrayList<Integer> getAddrOfAlreadyTraversedNodes() 
	{
		return addrOfAlreadyTraversedNodes;
	}
	public void setAddrOfAlreadyTraversedNodes(ArrayList<Integer> addrOfAlreadyTraversedNodes) 
	{
		this.addrOfAlreadyTraversedNodes = addrOfAlreadyTraversedNodes;
	}
}
