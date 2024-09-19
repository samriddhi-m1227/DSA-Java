package labInsertionSort;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class Assignment {
    private static void insertionSort(int[] arr) {
    	
    	//run insertion sort 
    	for (int i=1; i< arr.length; i++) { //starts from index 1 as first element is considered 'sorted'.
    		int key=arr[i]; //containing arr[i] in variable 'key'
    		int j=i-1;
    		
    		while(j>=0 && arr[j]>=key) { 	
    			//shifting:
    			arr[j+1]=arr[j];
    			j--; //decrement j
    		}
    		
    		//insert key at correct position
    		arr[j+1]=key;
    		
    		// Print the subarray at the end of each iteration to show invariant
            printSubArray(arr, i);
    	}    
    }
    

    // Print the subarray arr[0, 1, ..., j] with ";" after elements
    private static void printSubArray(int[] arr, int j) {
        for (int index = 0; index < arr.length && index <= j; index++) {
            System.out.print(arr[index]);
            System.out.print(";");
        }
        System.out.println();
    }

    public static void run(String input_path) {
        int[] sequence;
        int arraySize = 1;

        try (BufferedReader br = new BufferedReader(new FileReader(input_path))) {
            // Get the size of the sequence
            arraySize = Integer.parseInt(br.readLine());
            sequence = new int[arraySize];

            // Read the sequence
            for (int i = 0; i < arraySize; i++) {
                sequence[i] = Integer.parseInt(br.readLine());
            }

            // Perform insertion sort in-place
            insertionSort(sequence);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
