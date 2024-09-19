package labRadixSort;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Assignment {

    private static class Record {
        public int numerals;
        public int[] digits;

        public Record(int _numerals, int numDigits) {
            numerals = _numerals;
            digits = new int[numDigits];
            for (int i = 0; i < numDigits; i++)
                digits[i] = 0;
        }
    }

    // Sort arr stably numerals on sortingDigit
    private static void CountSort(Record[] arr, int sortingDigit) {
    	 int arraySize = arr.length;
         Record[] output = new Record[arraySize]; 
         int[] count = new int[4]; //  numerals: 0, 1, 2, and 3 (aka 4 spaces)


         //first, the count array will be filled with zeros 
         for (int i = 0; i < 4; i++) {
             count[i] = 0;
         }

         // count of occurrences of each digit
         for (int i = 0; i < arraySize; i++) {
             int digit = arr[i].digits[sortingDigit];
             count[digit]++;
         }

         
         for (int i = 1; i < count.length; i++) {
             count[i] = count[i]+count[i - 1];
         }

         //stable sorting 
         for (int j = arraySize - 1; j >= 0; j--) {
             int digit = arr[j].digits[sortingDigit];
             output[count[digit]- 1] = arr[j];
             count[digit]--;
         }

         for (int i = 0; i < arraySize; i++) { //copy updated output to original array
             arr[i] = output[i];
         }
     }
    
    // Perform Radix Sort in place on the provided array
    // Each digit in arr is on the range 0, 1, ..., numerals-1
    // numDigits is the maximum number of digits needed to represent elements
    private static void radixSort(long[] arr, int numerals, int numDigits) {
    	int arraySize = arr.length;
        Record[] arrRecord = new Record[arraySize];

        
        for (int i = 0; i < arraySize; i++) {//make array of Record objects
            arrRecord[i] = new Record(numerals, numDigits);
        }
        for(int i=0; i<arraySize; i++) {
            long number = arr[i]; 
            for (int j = numDigits - 1; j >= 0; j--) {
                arrRecord[i].digits[j] = (int) (number % 10);//extract digit by digit into arrRecord
                number /= 10;
            }
        }

        // Perform counting sort 10 times (numDigits-1), since 10 digits in all numbers 
        for (int digit = numDigits - 1; digit >= 0; digit--) {
            CountSort(arrRecord, digit);
        }

       // make the sorted Record objects back into long numbers and convert back to the input array.
        for (int i = 0; i < arraySize; i++) {
            long number = 0;
            for (int j = 0; j < numDigits; j++) {
                number = number * 10 + arrRecord[i].digits[j];
            }
            arr[i] = number;
        }
    }


    public static void run(String inputPath) {
        try (BufferedReader br = new BufferedReader(new FileReader(inputPath))) {
            // Get the size of the sequence
            int arraySize = Integer.parseInt(br.readLine());
            long[] sequence = new long[arraySize];

            // Read the sequence
            for (int i = 0; i < arraySize; i++) {
                sequence[i] = Long.parseLong(br.readLine());
            }

            // Perform radix sort in-place
            // For this assignment, each number should be no more than 10 digits
            // long and contain only the symbols 0, 1, 2, 3 (e.g. base 4)
            radixSort(sequence, 4, 10);

            // Print the sequence
            for (int i = 0; i < arraySize; i++) {
                System.out.println(sequence[i]);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
