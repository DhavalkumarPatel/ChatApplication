package recursen;

public class first {
	public static void main(String[] args){


		/* Define array */
		int array[]={2,3,4,7,8,8,2,3,4,7,5};
		int ans=0;

	// Most Optimal Solution of Running time O(n)		
		// Loop every element of the array and XOR it. 
		for(int i=0;i<11;i++){
			ans=ans^array[i];
		}
		// Print the resultant answer.
		System.out.println(ans);







}
}