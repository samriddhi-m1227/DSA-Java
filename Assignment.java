package labHeapSort;

import java.io.*;
import java.util.*;

public class Assignment {

    public static int[] heapify(int[] heap, int sizeA, int index) {
    	//indexed at 0 so adjustment to calculating children was made 
        int l=index*2+1; 
        int r= (index*2)+2;
        int largest;
        
        if(l<sizeA && heap[l]>heap[index]) {
        	largest=l;
        }
        	else {
        		largest=index;
        	}
        if(r<sizeA && heap[r]>heap[largest]) {
        	largest=r;
        }
        
        if(largest!=index) {
        	//exchange a[index] with largest
        	int tmp= heap[largest];
        	heap[largest]=heap[index];
        	heap[index]=tmp;
        	
        	heap=heapify(heap, sizeA, largest);
        	
        }
        return heap;
    }

    public static int[] buildHeap(int[] arr, int sizeA) {
        for (int i= (sizeA-1)/2; i>=0; i--) {
        	arr=heapify(arr, sizeA, i); 
        }
        return arr;
    }

    public static int[] heapSort(int[] arr, int sizeA) {
    	arr= buildHeap(arr, sizeA);
       for (int i = sizeA-1; i>0; i--) {
    	   
    	   //exchange a[0] (largest element) with a[i](to the end)
    	   int tmp= arr[0];
    	   arr[0]=arr[i];
    	   arr[i]=tmp;
    	   
    	   sizeA--;
    	   arr=heapify(arr, sizeA, 0); 
       }
       return arr;
    }

    public static void run(String inputPath) {
        try (BufferedReader br = new BufferedReader(new FileReader(inputPath))) {
            int size = Integer.parseInt(br.readLine());
            int[] A = new int[size];

            for (int i = 0; i < size; i++) {
                A[i] = Integer.parseInt(br.readLine());
            }

            int[] heap = heapSort(A, size);

            for (int i = 0; i < size; i++) {
                System.out.print(heap[i] + ";");
            }
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
