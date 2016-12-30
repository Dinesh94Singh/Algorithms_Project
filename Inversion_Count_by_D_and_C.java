import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

class Count_the_Inversions
{
	public static int totalSortedArray[];
	public static long mergeSort_and_countIt(int array[])
	{
		// use merge sort algorithm and simultaneously sort
		int mid = array.length/2;
		
		if(array.length <= 1)
		{
			return 0; // meaning that there are only 1/0 elements, so no need to count it
		}
		
		// merge sort algorithm
		int leftArray[] = new int[mid];
		int rightArray[] = new int[array.length - mid];
		
		for(int i = 0;i<mid;i++)
		{
			leftArray[i] = array[i];
		}
		for(int j = 0;j<array.length-mid;j++)
		{
			rightArray[j] = array[mid+j];
		}
		
		// just like merge algorithm for left side and right side 
		long leftSide = mergeSort_and_countIt(leftArray);
		long rightSide = mergeSort_and_countIt(rightArray);
		
		// call merge function
		totalSortedArray = new int[array.length];
		long count = merge(leftArray,rightArray,totalSortedArray);
		
		for(int k=0;k<array.length;k++)
		{
			array[k] = totalSortedArray[k];  
		}
		
		
		long totalCount = leftSide + rightSide + count;
		
		return totalCount;
	}
	
	public static long merge(int leftSide[],int rightSide[],int totalResult[])
	{
		int i = 0;
		int j = 0;
		int k = 0;
		long count = 0;
		for(;k<=leftSide.length+rightSide.length && (i<leftSide.length) && (j<rightSide.length);k++)
		{
			if(leftSide[i] <= rightSide[j]) // no need to invert
			{
				totalResult[k] = leftSide[i];
				i++;
			}
			else // we need to invert here so count it 
			{
				totalResult[k] = rightSide[j];
				j++;
				count = count + leftSide.length - i;
			}
		} // end of for loop
		
		if( i == leftSide.length)
		{
			for(int v = j ; v<rightSide.length;v++)
			{
				totalResult[k] = rightSide[v];
				k++;
			}
		}
		else
		{
			for(int v = i; v<leftSide.length;v++)
			{
				totalResult[k] = leftSide[v];
				k++;
			}
		}
		
		return count;
	}
}

public class Inversion_Count_by_D_and_C 
{

	public static void main(String[] args) throws IOException 
	{
		
		long startTime = System.currentTimeMillis();
		String testFile = "/Users/Vinay/Desktop/IntegerArray.txt";
		String testOutput = "/Users/Vinay/Documents/MS/Advanceed DBMS/testOutput.txt";
		
		FileReader file1 = new FileReader(testFile);
		FileReader file2 = new FileReader(testFile);
		FileReader file3 = new FileReader(testOutput);
		BufferedReader br = new BufferedReader(file1);
		BufferedReader b = new BufferedReader(file2);
		String line = null;
		String line1 = null;
		
		int index=0;
		int i=0;
		while(true)
		{
			line=b.readLine();
			if(line == null)
			{
				break;
			}
			else
			{
				i++;
			}
		}
		b.close();
		int array[] = new int[i];		
		while(true)
		{
			line1 = br.readLine();
			if(line1 == null)
			{
				break;
			}
			else
			{ 
				array[index] = Integer.parseInt(line1);
				index++;
			}
		}

		// from here the array to be inversion counted by D and C is ready
		
		long a = Count_the_Inversions.mergeSort_and_countIt(array);
		System.out.println("The total number of Inversions is "+a);
		br.close();
		
		File f = new File("/Users/Vinay/Documents/MS/Advanceed DBMS/testOutput.txt");
		File f2 = new File("/Users/Vinay/Documents/MS/Advanceed DBMS/testOutput1.txt");
		
		if(!f.exists()){
			f.createNewFile();
		}
		FileWriter fw = new FileWriter(f);
		BufferedWriter bw = new BufferedWriter(fw);
		long endTime = System.currentTimeMillis();
		FileWriter fw2 = new FileWriter(f2);
		BufferedWriter bw2 = new BufferedWriter(fw2);
		
		bw2.write(""+a+"\n Total Time Taken:"+ (endTime - startTime));
		bw2.close();
		
		
		
		
			for(int d=0;d<= (Count_the_Inversions.totalSortedArray.length) -1;d++){
				System.out.println(Count_the_Inversions.totalSortedArray[d]);
				bw.write(""+Count_the_Inversions.totalSortedArray[d]+"\n");
			}
			bw.close();
	}

}
