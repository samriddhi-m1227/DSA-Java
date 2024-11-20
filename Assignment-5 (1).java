package labHashTable;

import java.io.*;
import java.util.*;

public class Assignment {

    // Insert key into the hash Table
    public static void Insert(List<Integer>[] Table, int key) {
    	int bucket= key%Table.length; /*calculate the 'bucket' in the array that
    	the key should be inserted in using chaining */
    	
    	Table[bucket].add(0,key); //use .add and insert at the start of the LinkedList
    	
    }

    // Delete key from the hash Table
    public static void Delete(List<Integer>[] Table, int key) {
    	int bucket= key%Table.length; //go to the bucket where key might be 
    	
    	if(Table[bucket].contains(key)) {//if they list contains the key 
    		Table[bucket].remove(Integer.valueOf(key));//then remove the actual object of key
    		System.out.println(key + ":DELETED;");
    	}
    	else {//if the list does not contain the key , then print this:
        System.out.println(key + ":DELETE_FAILED;");
    }
    }

    // Search for key in the hash Table
    public static void Search(List<Integer>[] Table, int key) {
        int index=key%Table.length;
        
        if(Table[index].contains(key)) { //if the key is found in the Table
        	int listIndex= Table[index].indexOf(key); //get the listIndex using .indexOf
        	
        	//print the following :
        	System.out.println(key + ":FOUND_AT" + index + "," + listIndex + ";");
        	
        }
        else {//if not , print this:
        System.out.println(key + ":NOT_FOUND;");
    }
    }

    public static void Output(List<Integer>[] Table) {
        for (int i = 0; i < Table.length; i++) {
            System.out.print(i + ":");
            for (int key : Table[i]) {
                System.out.print(key + "->");
            }
            System.out.println(";");
        }
    }

    public static void run(String inputPath) {
        try (BufferedReader br = new BufferedReader(new FileReader(inputPath))) {
            int size = Integer.parseInt(br.readLine().trim());
            List<Integer>[] Table = new LinkedList[size];
            for (int i = 0; i < size; i++) {
                Table[i] = new LinkedList<>();
            }

            String line;
            while ((line = br.readLine()) != null) {
                // Parse each line of input as an instruction what to do
                if (line.length() == 0)
                    continue;
                if (line.charAt(0) == 'e') // Exit
                    return;
                if (line.charAt(0) == 'o') {
                    Output(Table);
                    continue;
                }

                // Get the key integer only if the instruction requires
                int key;
                try {
                    key = Integer.parseInt(line.substring(1).trim());
                } catch (NumberFormatException e) {
                    key = -1;
                }

                // Perform an operation on the hash Table based on instructions
                if (line.charAt(0) == 'i')
                    Insert(Table, key);
                else if (line.charAt(0) == 'd')
                    Delete(Table, key);
                else if (line.charAt(0) == 's')
                    Search(Table, key);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
