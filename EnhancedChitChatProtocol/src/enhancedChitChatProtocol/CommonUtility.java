package enhancedChitChatProtocol;


public class CommonUtility 
{
	public static Node[][] generateNodeForGrid(int sizeOfGrid)
	{
		Node[][] nodes = new Node[sizeOfGrid][sizeOfGrid];
		
		//assign address to each node
		int addr=0;
		for(int i=0;i<sizeOfGrid;i++)
        {
            for(int j=0;j<sizeOfGrid;j++)
            {
                nodes[i][j] = new Node();
                nodes[i][j].setNodeAddress(addr);
                addr++;
            }
        }
		
		//assign Neighbour addresses to each Node 
		for(int i=0;i<sizeOfGrid;i++)
        {
            for(int j=0;j<sizeOfGrid;j++)
            {
            	if(i < (sizeOfGrid-1))
            	{
            		nodes[i][j].getNeighbourNodesAddrList().add(nodes[i+1][j].getNodeAddress());
            	}
            	if(i > 0)
            	{
            		nodes[i][j].getNeighbourNodesAddrList().add(nodes[i-1][j].getNodeAddress());
            	}
            	if(j < (sizeOfGrid-1))
            	{
            		nodes[i][j].getNeighbourNodesAddrList().add(nodes[i][j+1].getNodeAddress());
            	}
            	if(j > 0)
            	{
            		nodes[i][j].getNeighbourNodesAddrList().add(nodes[i][j-1].getNodeAddress());
            	}
            }
        }
	        
		return nodes;
	}
	
	public static Node getSelectedNeighbourPosition(Node[][] nodes, int selNeighbourAddr, int sizeOfGrid)
	{
		for(int i=0; i<sizeOfGrid; i++)
        {
             for(int j=0; j<sizeOfGrid; j++)
             {
                 if(nodes[i][j].getNodeAddress() == selNeighbourAddr)
                 {
                	 return nodes[i][j];
                 }
             }
        }
		return null;
	}
	
	public static void displayChitsOnNodes(Node nodes[][], int sizeOfGrid)
    {
         for(int i=0; i<sizeOfGrid; i++)
         {
              for(int j=0; j<sizeOfGrid; j++)
              {
                  System.out.print(nodes[i][j].getNodeAddress() + " [");
                  for(int chitNo=0; chitNo< nodes[i][j].getChitList().size(); chitNo++)
                  {
                	  Chit chit = nodes[i][j].getChitList().get(chitNo);
                	  System.out.print(" ("+chit.getDestinationAddress()+" , "+chit.getNoOfHopsTraversed()+") ");
                  }
                  System.out.print("]\n");
              }
         }
    }
}
