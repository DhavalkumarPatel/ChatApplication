package enhancedChitChatProtocol;

public class MathUtility 
{
	public static double getAverage(int arrData[], int size)
	{
		int total = 0;
		for(int i=0; i<size; i++)
		{
			total = total + arrData[i]; 
		}		
		
		return ((double) total / size);
	}
	
	public static double getMedian(int arrData[], int size)
	{
		int middle = size/2;
		
		if(size % 2 == 0)
		{
			return ((double)(arrData[middle-1] + arrData[middle])) / 2.0;
		}
		else
		{
			return arrData[middle];
		}
	}
}
