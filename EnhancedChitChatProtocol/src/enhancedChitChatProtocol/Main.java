package enhancedChitChatProtocol;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Main 
{
	public static void main(String[] args) 
	{
		for(int rd=0; rd<40; rd++)
		{
		for(int dsp=0; dsp<5; dsp++)
		{
			
		
        Random random = new Random();
        
        //Input
       /* Scanner scanner = new Scanner(System.in);
        System.out.println("Enter size of Grid (i.e. 10 for 10*10 Grid) :: ");
        int sizeOfGrid = scanner.nextInt();
        System.out.println("Enter allowed No of passings (TTL) :: ");
        int noOfPassingsAllowed = scanner.nextInt();*/

        int sizeOfGrid = 10;
        int noOfPassingsAllowed = 25*(rd+1);		
        //Generate Nodes
        Node nodes[][] = CommonUtility.generateNodeForGrid(sizeOfGrid);

        //Assign Chit(Random Destination Address) to Each Node
        for(int i=0;i<sizeOfGrid;i++)
        {
            for(int j=0;j<sizeOfGrid;j++)
            {
                Chit newChit = new Chit();
                newChit.setDestinationAddress(random.nextInt(sizeOfGrid*sizeOfGrid));
                newChit.setNoOfHopsTraversed(0);
                nodes[i][j].getChitList().add(newChit);
            }
        }

        //Display Initial status of chits on each Node
        /*System.out.println("Initial status of chits on each Node :::");
        CommonUtility.displayChitsOnNodes(nodes, sizeOfGrid);*/

        //Delivery of the chits
        for(int k=0; k<noOfPassingsAllowed; k++)
        {
			for(int i=0;i<sizeOfGrid;i++)
			{
			    for(int j=0;j<sizeOfGrid;j++)
			    {
			        for(int chitNo=0; chitNo< nodes[i][j].getChitList().size(); chitNo++)
			        {
			        	Chit chitToBeSend = nodes[i][j].getChitList().get(chitNo);
			            if(chitToBeSend.getDestinationAddress() != nodes[i][j].getNodeAddress())
			            {
		            		ArrayList<Integer> availableNeighbourAddrList = new ArrayList<Integer>();
		            		for(int neighbourAddress : nodes[i][j].getNeighbourNodesAddrList())
			            	{
			            		if(neighbourAddress == chitToBeSend.getDestinationAddress())
			            		{
			            			availableNeighbourAddrList.removeAll(availableNeighbourAddrList);
			            			availableNeighbourAddrList.add(neighbourAddress);
			            			break;
			            		}
			            		else
			            		{
			            			boolean isNeighbourNodeAlreadyTraversed = false;
			            			for(int alreadyTraversedNodeAddr : chitToBeSend.getAddrOfAlreadyTraversedNodes())
			            			{
			            				if(alreadyTraversedNodeAddr == neighbourAddress)
			            				{
			            					isNeighbourNodeAlreadyTraversed = true;
			            					break;
			            				}
			            			}
			            			if(!isNeighbourNodeAlreadyTraversed)
			            			{
			            				availableNeighbourAddrList.add(neighbourAddress);
			            			}
			            		}
			            	}	
		            		int selectedNeighbourAddress;
		            		if(availableNeighbourAddrList.size() > 0)
		            		{
		            			selectedNeighbourAddress = availableNeighbourAddrList.get(random.nextInt(availableNeighbourAddrList.size()));
		            		}
		            		else
		            		{
		            			selectedNeighbourAddress = nodes[i][j].getNeighbourNodesAddrList().get(random.nextInt(nodes[i][j].getNeighbourNodesAddrList().size()));
		            		}
			            	Node selectedNeighbourNode = CommonUtility.getSelectedNeighbourPosition(nodes, selectedNeighbourAddress, sizeOfGrid);
			            	
			            	if(null != selectedNeighbourNode)
			            	{
			            		chitToBeSend.setNoOfHopsTraversed(chitToBeSend.getNoOfHopsTraversed() + 1);
			            		chitToBeSend.getAddrOfAlreadyTraversedNodes().add(nodes[i][j].getNodeAddress());
				            	selectedNeighbourNode.getChitList().add(chitToBeSend);
				            	nodes[i][j].getChitList().remove(chitNo);
			            	}
			            }
			        }
			    }
			}
        }

        //Display Final status of chits on each Node
        /*System.out.println("Final status of chits on each Node :::");
        CommonUtility.displayChitsOnNodes(nodes, sizeOfGrid);*/

        //Output
        int deliveredChitsCountArr[] = new int[sizeOfGrid*sizeOfGrid];
        int k=0;
        for(int i=0;i<sizeOfGrid;i++)
        {
              for(int j=0;j<sizeOfGrid;j++)
              {
                  for(int chitNo=0; chitNo< nodes[i][j].getChitList().size(); chitNo++)
                  {
                      Chit chit = nodes[i][j].getChitList().get(chitNo);
                      if(chit.getDestinationAddress() == nodes[i][j].getNodeAddress())
                      {
                          deliveredChitsCountArr[k] = chit.getNoOfHopsTraversed();
                          k++;
                      }
                  }
              }
          }
        
        Arrays.sort(deliveredChitsCountArr, 0, k);
        
        double chitDeliveryRatio = (double)(k*100)/(sizeOfGrid*sizeOfGrid);
        
        /*System.out.println("Output :::");
        System.out.println("Chit Delivery Ratio : " + chitDeliveryRatio + "%");
        System.out.println("Averege no. of hopes needed for delivered chits : " + MathUtility.getAverage(deliveredChitsCountArr,k));
        System.out.println("Median no. of hopes needed for delivered chits : " + MathUtility.getMedian(deliveredChitsCountArr,k));
        System.out.println("Max no. of hopes needed for delivered chits : " + deliveredChitsCountArr[k-1]);
        System.out.println("Min no. of hopes needed for delivered chits : " + deliveredChitsCountArr[0]);*/
        
        System.out.println(sizeOfGrid*sizeOfGrid + "\t" + noOfPassingsAllowed + "\t" + chitDeliveryRatio + "\t" + MathUtility.getAverage(deliveredChitsCountArr,k) + "\t" + MathUtility.getMedian(deliveredChitsCountArr,k) + "\t" + deliveredChitsCountArr[k-1] + "\t" + deliveredChitsCountArr[0]);
	
		}
		}	
	}

}
