package node;
import java.util.Scanner;

public class LinkedList 
{
    
    static Node head;
    
    // Method to define a node
    static class Node 
    { 
        int data; 
        Node next; 
        Node(int d) 
        { 
            data = d; 
            next = null; 
        } // Constructor 
    }
    
    //method to print the Linked List
    public void printList() 
    { 
        Node temp = head; 
        while (temp != null) 
        { 
            System.out.print(temp.data + " "); 
            temp = temp.next; 
        } 
    } 
    
    
    //Method to append nodes for new numbers
    public void append(int data) 
    {     
		Node new1 = new Node(data); 
	  
		// If list is empty
		if (head == null) 
		{ 
			head = new Node(data); 
			return; 
		} 
	  
		new1.next = null; 
	  
		Node last = head;  
		while (last.next != null) 
			last = last.next; 
	  
		last.next = new1; 
    } 
    
    
    static Node sort( Node RefH)  
    {  
    // if list is empty  
    if ((RefH) == null)  
        return null;  
  
    RefH = rSelectionSort(RefH);  
    return RefH; 
    } 
    
    // recursive selection sort technique  
    static Node rSelectionSort( Node head)  
    {  
    // if there is only a single node  
    if (head.next == null)  
        return head;  
  
    // min - min value node  
    Node min = head;  
  
    // 'MinPrev' - node previous to min node
    Node MinPrev = null;  
    Node ptr;  
  
    // traverse the list till the last node  
    for (ptr = head; ptr.next != null; ptr = ptr.next)  
    {  
  
        // update new min and MinPrev  
        if (ptr.next.data < min.data)  
        {  
            min = ptr.next;  
            MinPrev = ptr;  
        }  
    }  
  
    //swap min and head 
    if (min != head)  
        head = swap(head, head, min, MinPrev);  
  
    // sort remaining
    head.next = rSelectionSort(head.next);  
  
    return head;  
    }
    
    static Node swap( Node RefHead, Node A,Node B, Node BPrev)  
    {  
    // make 'B' as new head  
    RefHead = B;  
  
    // adjust links  
    BPrev.next = A;  
  
    // Swap next pointers  
    Node temp = B.next;  
    B.next = A.next;  
    A.next = temp;  
    return RefHead; 
    }      




    public static void main(String[] args) 
    {
        //test main
        //System.out.println("Hello");
              
        LinkedList llist = new LinkedList();
        
        Scanner input = new Scanner(System.in);
    	
        int i;
        System.out.print("Enter length of list (atleat 15): ");
        int n = input.nextInt();
        System.out.print("Enter the numbers (followed by spaces): ");
        for(i=1;i<=n;i++)
        {
            int val = input.nextInt();
            // Call method append to add numbers to the linked list
            llist.append(val);
        }
        

        System.out.println();
        System.out.print("Linked List : ");
        llist.printList();
        System.out.println();
        
         // sort the linked list
        head = sort(head);
        System.out.print("Sorted List : ");
        
        llist.printList();
        System.out.println();
        
        
                
    } 
        
}
    
