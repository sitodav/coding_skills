import java.util.Arrays;

public class MinHeapWithBackingArray extends MinHeap {

	
	int[] backArray = null;
	int lastUsedIdxOnArray = -1;
			
	public MinHeapWithBackingArray(int initialSize)
	{
		backArray = new int[initialSize];
	}
	/*utility methods to work with the back array easily */
	private int getLeftChildIndex(int myIdx)
	{
		return 2*myIdx + 1;
	}
	private int getRightChildIndex(int myIdx)
	{
		return 2*myIdx + 2;
	}
	private int getMyFatherIndex(int myIdx)
	{
		return (myIdx-2) / 2;
	}
	private void swapItems(int idxA, int idxB)
	{
		int t = this.backArray[idxA];
		this.backArray[idxA] = this.backArray[idxB];
		this.backArray[idxB] = t;
	}
	 
	
	
	/*We remove the root from the array (heap)
	 * we put the last item in the root
	 * and we heapify down
	 */
	@Override
	public int popRoot() {
		
		if(lastUsedIdxOnArray >= 0) //just to check if the heap was empty
		{
			int t = this.backArray[0];
			this.backArray[0] = this.backArray[this.lastUsedIdxOnArray];
			this.backArray[this.lastUsedIdxOnArray] = 0;
			this.lastUsedIdxOnArray--;
			heapifyDown();
			return t;
		}
		
		return -1; //in this case the heap was empty
		
	}
	
	//We add the new element at the end of the heap
	//and we heapify it up
	@Override
	public void addElement(int element) {
		if(this.lastUsedIdxOnArray == this.backArray.length - 1)
		{
			this.backArray = Arrays.copyOf(this.backArray, this.backArray.length * 2);
		}
		
		this.backArray[++this.lastUsedIdxOnArray] = element;
		
		heapifyUp();
	}
	
	@Override
	public void heapifyDown() {
		//bubble down until current item is > the min between the childs
		int idxHeapifying = 0; //start from the root
		while(idxHeapifying <= this.lastUsedIdxOnArray-1)
		{
			
			
			int idxLeftChild = getLeftChildIndex(idxHeapifying);
			if(idxLeftChild <= this.lastUsedIdxOnArray) //here the left child exists
			{
				int idxMinOfChilds = idxLeftChild;
				
				int idxRightChild = getRightChildIndex(idxHeapifying) ;
				if(idxRightChild <= this.lastUsedIdxOnArray) //the right child can exist only if the left child exists
				{
					if(  this.backArray[idxRightChild] < this.backArray[idxLeftChild]  )
						idxMinOfChilds = idxRightChild;
				}
				if(this.backArray[idxHeapifying] > this.backArray[idxMinOfChilds])
				{
					swapItems(idxHeapifying, idxMinOfChilds);
					//we keep heapifying on the min of childs
					idxHeapifying = idxMinOfChilds;
				}
			}
			else break; //else nothing it has no childs and we have finished the heapify down
		}
	}
	
	@Override
	public void heapifyUp() {
		//bubble up until current item is < father
		int idxHeapifying = this.lastUsedIdxOnArray; //start from last element
		while(idxHeapifying > 0 && this.backArray[getMyFatherIndex(idxHeapifying)] > this.backArray[idxHeapifying])
		{
			swapItems(getMyFatherIndex(idxHeapifying),idxHeapifying);
			idxHeapifying = getMyFatherIndex(idxHeapifying); //we move up with the heapifying onto the father
		}
	}
	
	
	
	
	//####################################################################################TEST########################################################################################
	
	public static void main(String[] args)
	{
		MinHeap minHeap = new MinHeapWithBackingArray(1);
		
		minHeap.addElement(7);
		minHeap.addElement(2);
		minHeap.addElement(3);
		minHeap.addElement(5);
		minHeap.addElement(1);
		minHeap.popRoot();
		
		System.out.println("terminato");
		
	}
	
}
