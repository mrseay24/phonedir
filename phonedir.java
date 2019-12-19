
package phonedir;

/*
  CSC3410 - Fall 2015
  Sidney Seay -  sseay5@student.gsu.edu

  Assignment: #4

  phonedir class

  File(s): phonedir.java

  Purpose: Maintain a list of records containing names and phone number
           utilizing linked list. 
           Prompt the user for a command and execute the command
*/

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class phonedir {

   /*
    * This private nested class is used internally to implement linked lists.  
    * The ListNode holds a (key, value) pair.
    *   
    */
   private class ListNode {
      Object key;
      Object value;
      // Pointer to next node in the list;
      // A null marks the end of the list.      
      ListNode next;

      private ListNode(Object thisKey, Object thisValue) 
      { 
          key = thisKey; 
          value = thisValue; 
      }      
   }

   private ListNode[] table;  // The hash table represent a Link Node
   private final static int listSize = 53; // HashTable use prime number 
   private ListNode[] list = new ListNode[listSize]; //holds the values of the hashTable
   protected Hashtable phoneEntry = new Hashtable();
   // define global variables
   private int count = 0;
   private static String firstName = "";
   private static String lastName = "";
   private static String phoneNumber = "";
   private static String fName = "";
   private static String lName = "";
   private static String pNumber = "";               
   private static String selectRecordKey = "";
   private static String sValue = "";   
   
   /*
    * Create a hash table for the first name, last name,
    * and phone number
    * 
    * This is the constructor
    */
   public phonedir() {
      table = new ListNode[53];  // hash table use prime number 

   }
   
   /*
    * Create a hash table with an initial size.
    * Check for initalSize > 0.
    */
   public phonedir(int initialSize) {
      if (initialSize <= 0)
         throw new IllegalArgumentException("Illegal table size");
      table = new ListNode[initialSize];
   }

   /*
    * M A I N
   */
   public static void main(String[] args){
 
	  // string variable
	  Object phoneKey; 
	  String sInput = "";
	  String key = "";
	  String value = "";
	   
      phonedir table = new phonedir(53);  // hash table use prime number

      System.out.println("\nA Program to keep a Phone Directory:");

      Scanner scan = new Scanner( System.in );
    
      // perform While statement until break statement
      // encountered
      while (true) {
         System.out.println(" ");
         System.out.println("   a  Show all records");
         System.out.println("   d  Delete the current record");
         System.out.println("   f  Change the first name in the current record");
         System.out.println("   l  Change the last name in the current record");
         System.out.println("   n  Add a new record");
 		 System.out.println("   p  Change the phone number in the current record");
 		 System.out.println("   q  Quit");    		
 		 System.out.println("   s  Select a record from the record list to become the current record");
         //
         System.out.println(" ");
         System.out.println(" ");                  
 		 System.out.print("Enter a command from the list above (q to Quit):  ");
 		 
         scan = new Scanner(System.in);
 		 sInput = scan.nextLine();

         //case "a":  Show all records 		 
 		 if (sInput.toUpperCase().trim().equals("A"))
 		 {
 			 table.showAllRecord();
            continue; // continue While statement
 		 }
         //case "d":  Delete the current record
 		 else if (sInput.toUpperCase().trim().equals("D"))
 		 {
 			 if (firstName.length() > 0 && lastName.length() > 0 && phoneNumber.length() > 0) {
 	             // delete current record
 				 phoneKey = selectRecordKey;
 				 table.deletePhoneEntry(phoneKey);
 	 			 // reset current record key to space
 	 			 selectRecordKey = "";
 	 			 System.out.println("\n   Deleted: " + firstName + " " + lastName + " " + phoneNumber);
 			 }
 			 else {
 	 			 System.out.println("\n   No current record "); 				 
 			 }
            continue;  // continue While statement
 		 }
         //case "f":  Change the first name in the current record
 		 else if (sInput.toUpperCase().trim().equals("F"))
 		 {
            System.out.println("\n   Enter new first name: ");
            fName = scan.nextLine();

            if (firstName.length() > 0 && lastName.length() > 0 && phoneNumber.length() > 0) {
	            if (fName.length() > 0) {
					// delete current record
	 				 phoneKey = selectRecordKey;
	 				 table.deletePhoneEntry(phoneKey);	            	
	 	 			// update current record first name
		            firstName = fName;
	                // save current record key
		            key = lastName + " " + fName;
	                selectRecordKey = key;
	                value = fName + "," + lastName + "," + phoneNumber;
	                //*************************************
	                // add record to linked list
	                // linked list key is lastName + " " + fName
	                // value delimiter is comma
	                //*************************************
	                table.put(key, value);
	 	 			System.out.println("\n   Current record is: " + fName + " " + lastName + " " + phoneNumber);            
	            }
	            else {
	                System.out.println("\n   No first name was entered - enter a valid first name");            	
	            }
 			}
 			else {
 	 			 System.out.println("\n   No current record "); 				 
 			}
            continue;  // continue While statement
 		 }
         //case "l":  Change the last name in the current record
 		 else if (sInput.toUpperCase().trim().equals("L"))
 		 {
            System.out.println("\n   Enter new last name: ");
            lName = scan.nextLine();

			if (firstName.length() > 0 && lastName.length() > 0 && phoneNumber.length() > 0) {
	            if (lName.length() > 0) {
					// delete current record
	            	phoneKey = selectRecordKey;
	            	table.deletePhoneEntry(phoneKey);
	 	 			// update current record last name
	                key = lName + " " + firstName;
	                // save current record key
	                selectRecordKey = key;
	                lastName = lName;
	                value = firstName + "," + lName + "," + phoneNumber;
 	                //*************************************
	                // add record to linked list
	                // linked list key is lastName + " " + fName
	                // value delimiter is comma
	                //*************************************
	                table.put(key, value);
	 	 			System.out.println("\n   Current record is: " + firstName + " " + lName + " " + phoneNumber);            
	            }
	            else {
	                System.out.println("\n   No last name was entered - enter a valid last name");            	
	            }
 			}
 			else {
	 			 System.out.println("\n   No current record "); 				 
			}			
            continue;  // continue While statement
 		 } 		 
         //case "n":  Add a new record
 		 else if (sInput.toUpperCase().trim().equals("N"))
 		 {
            System.out.println("\n   Enter first name: ");
            firstName = scan.nextLine();
            System.out.println("\n   Enter last name: ");
            lastName = scan.nextLine();
            System.out.println("\n   Enter phone number: ");
            phoneNumber = scan.nextLine();            

            if (firstName.length() > 0 && lastName.length() > 0 && phoneNumber.length() > 0) {
                key = lastName + " " + firstName;
                // save current record key 
                selectRecordKey = key;
                value = firstName + "," + lastName + "," + phoneNumber;
                //*************************************
                // add record to linked list
                // linked list key is lastName + " " + fName
                // value delimiter is comma
                //*************************************
                table.put(key, value);
            	// display new record
                System.out.println("\n   Current record is: " + firstName + " " + lastName + " " + phoneNumber);            	
            }
            else {
                System.out.println("\n   Enter a valid first name, last name, and  phone number");            	
            }
            continue;  // continue While statement
 		 }
         //case "p":  Change the phone number in the current record
 		 else if (sInput.toUpperCase().trim().equals("P"))
 		 {
            System.out.println("\n   Enter new phone number for current record: ");
            pNumber = scan.nextLine();

            if (firstName.length() > 0 && lastName.length() > 0 && phoneNumber.length() > 0) {
	            if (pNumber.length() > 0) {
					// delete current record
	            	phoneKey = selectRecordKey;
	            	table.deletePhoneEntry(phoneKey);
	                phoneNumber = pNumber;
	            	// update current record last name
	                key = lastName + " " + firstName;
	                // save current record key 
	                selectRecordKey = key;
	                phoneNumber = pNumber;
	                value = firstName + "," + lastName + "," + pNumber;
	                //*************************************
	                // add record to linked list
	                // linked list key is lastName + " " + fName
	                // value delimiter is comma
	                //*************************************
	                table.put(key, value);
	 	 			System.out.println("\n   Current record is: " + firstName + " " + lastName + " " + pNumber);            
	            }
	            else {
	                System.out.println("\n   No first name was entered - enter a valid first name");            	
	            }
 			}
 			else {
	 			 System.out.println("\n   No current record "); 				 
			}            
             continue; // continue While statement
 		 }
         //case "q":  Quit
 		 else if (sInput.toUpperCase().trim().equals("Q"))
 		 {
 			 break;  // end While statement
 		 } 		 
         //case "s": Select current record
 		 else if (sInput.toUpperCase().trim().equals("S"))
 		 {
 			System.out.println("\n   Enter first name: ");
            fName = scan.nextLine();
            System.out.println("   Enter last name: ");
            lName = scan.nextLine();
            
            if (fName.length() > 0 && lName.length() > 0) {
            	// find record in linked list and set as current record
            	key = lName + " " + fName;
            	phoneKey = key;

            	sValue = table.searchForRecord(key);
                // linked list value is delimited by comma
                // separate first name, last name, and phone number
                if (sValue.length() > 0) {
                	String[] sFLPValue = sValue.split(",");
                    fName = sFLPValue[0];
                    lName = sFLPValue[1];
                    pNumber = sFLPValue[2];
                    firstName = fName;
                    lastName = lName;
                    phoneNumber = pNumber;                    
                    System.out.println("\n   Current record is: " + fName + " " + lName + " " + pNumber);
                    // save current record key
                    selectRecordKey = (String)phoneKey;
                }
                else {
                    System.out.println("\n No matching record found");                	
                }
            }
            else {
                System.out.println("\n   Enter a valid first name and last name");            	 
            }
            continue;  // continue While statement
 		 }
         //default:
 		 else
 		 {
            System.out.println(" "); 			 
            System.out.println("Illegal command");
            continue;  // continue While statement
         }
  	  }  
   }
   
   /*
    *  Set First Name
    */
   private void setFirstName(String fName) {
	   firstName = fName;
   }
   
   /*
    *  Set Last Name
    */
   private void setLastName(String lName) {
	   lastName = lName;
   }
   
   /*
    *  Set Phone Number
    */
   private void setPhoneNumber(String pNumber) {
	   phoneNumber = pNumber;
   }
   
   /*
    *  Get First Name
    */
   private String getFirstName() {
	   return firstName;
   }
   
   /*
    *  Get Last Name
    */
   private String getLastName() {
	   return lastName;
   }
   
   /*
    *  Get Phone Number
    */
   private String getPhoneNumber() {
	   return phoneNumber;
   }

    /*
     *    
     */
    private void deletePhoneEntry(Object phoneKey) {
    	phoneEntry.remove(phoneKey);
    	
    }
    /*
     *  Search for a record in the linked list 
     */
    private String searchForRecord(Object phoneKey) {
  	    String sValue = "";
    	
    	sValue = (String)phoneEntry.get(phoneKey);
    	if (sValue == null) {
            sValue = "";
    	}
        return sValue;
    }

   /*
    * Format output - right justify data with space  
    */
   private String padString(String sData, int len) {
	  for (int i = sData.length(); i <= len; i++) {
		  sData += " ";
	  }
	  return sData;
   }
   
   /*
    * Show All Records
    */
   protected void showAllRecord() {
	  String sValue = "";
	  int printCount = 0; 
      // sort
	  java.util.List<String> v = new ArrayList<String>(phoneEntry.keySet());
      Collections.sort(v);

	  if (phoneEntry.size() > 0) {
	      for (int i = 0; i < phoneEntry.size(); i++) {
	    	 if (v.get(i) != null) {
		       sValue = v.get(i);
		       sValue = searchForRecord(sValue);
	    	   if (printCount == 0) {
          	 	  System.out.println("First Name                 Last Name                  Phone Numer");
        	 	  System.out.println("----------                 ---------                  -----------"); 	                  	
                }
                printCount++;
                
                // linked list value is delimited by comma
                // separate first name, last name, and phone number
                String[] sFLPValue = sValue.split(",");
                fName = sFLPValue[0];
                lName = sFLPValue[1];
                pNumber = sFLPValue[2];
                // call method padString - format output
                fName = padString(fName, 25);
                lName = padString(lName, 25);                
	            System.out.print("\n" + fName + " " + lName + " " + pNumber);
	         }
	         System.out.println();
	      }		  
	  }
	  if (printCount < 1) {
	         System.out.println("No record have been added to the phone directory");		  
	  }
   }
   
   /*
    * Associate the specified value with the specified key.
    * Key cannot be null.
    */
   public void put(String key, String value) {
      int location = (Math.abs(key.hashCode()) % table.length);       

      phoneEntry.put(key, value);
      
      ListNode newNode = new ListNode(key, value); 
      if(list[location]==null) 
      { 
          list[location] = newNode; 
      } 
      else
      { 
         ListNode occupiedNode = list[location]; 
         while(occupiedNode.next != null) 
         { 
           occupiedNode.next = occupiedNode;
         } 
         newNode = occupiedNode.next; 
      } 
      count++;
      //add 1 to count of items
   }
   
   /*
    * Return the number of key/value pairs in the table.
    */
   public int size() {
      return count;
   }
   
   /*
    * Compute a hash code for the key; key cannot be null.
    * The hash code depends on the size of the table as
    * well as on the value returned by key.hashCode().
    */
   private int hash(Object key) {
      return (Math.abs(key.hashCode())) % table.length;
   }

   /*
    * Double the size of the table, and redistribute the
    * key/value pairs to their proper locations in the
    * new table.
    */
   private void resize() {
      ListNode[] newtable = new ListNode[table.length*2];
      for (int i = 0; i < table.length; i++) {
             // Move all the nodes in linked list number i into the new table.  
             // No new ListNodes are created.  The existing ListNode for each
             // key/value pair is moved to the newtable.  This is done by 
             // changing the "next" pointer in the node and by making a pointer 
             // in the new table point to the node.
         ListNode list = table[i]; // For traversing linked list number i.
         while (list != null) {
               // Move the node pointed to by list to the new table.
            ListNode next = list.next;  // The is the next node in the list.
               // Remember it, before changing the value of list!
            int hash = (Math.abs(list.key.hashCode())) % newtable.length;
               // hash is the hash code of list.key that is 
               // appropriate for the new table size.  The
               // next two lines add the node pointed to by list
               // onto the head of the linked list in the new table
               // at position number hash.
            list.next = newtable[hash];
            newtable[hash] = list;
            list = next;  // Move on to the next node in the OLD table.
         }
      }
      table = newtable;  // Replace the table with the new table.
   }
}