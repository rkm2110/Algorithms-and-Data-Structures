package heap;
import java.util.Scanner;

public class Heap 
{
    
    public static void MinHeapify(int H[],int i,int n)
    {
        //System.out.print("i = "+ (i) +" i*2="+ (i*2) +" i*2+1="+ (i*2+1)+ "\n");
        if ((i*2)==n)
        {
            if (H[i]>H[i*2])
            {
                int temp1 =H[i];
                H[i]=H[i*2];
                H[i*2]=temp1;
            }
        }
        
        
        
        else if((i*2+1)<=n)
        {
            if ((H[i] >H[i*2]) || (H[i] > H[i*2+1]))
            {
                //System.out.print("Inside MinHeapify 1 \n ");
                int temp = H[i];
                if (H[i*2]<=H[i*2+1])
                    {
                        H[i]=H[i*2];
                        H[i*2]=temp;
                    }
                else
                    {
                        H[i]=H[i*2+1];
                        H[i*2+1]=temp;
                    }
            }
        }
        
        //System.out.print("Inside MinHeapify 2 \n ");
        if ((i*2)<=n) // left child recursive call
        {
            MinHeapify(H,i*2,n);
        }
        if ((i*2+1)<=n) // right child recursive call
        {
            MinHeapify(H,i*2+1,n);
        }
    }
    
    
    public static void sort(int H[],int i,int n)
    {
        int temp2=H[1];
        H[1]=H[n-i+1];
        H[n-i+1]=temp2;
        H[0]=H[0]-1;
        //System.out.print("\nInside Sort i="+(i)+" n-i="+(n-i)+" H="+H);
        //MinHeapify(H,i,n-i);
        for(int k=H[0]/2;k>0;k--)
        {
            //System.out.print("For loop for Heapify. i=" + i+"\n");
            MinHeapify(H,k,n-i);
            //System.out.print("\n" + H[i]);
        } 
    }
 
   
    
    
    public static void main(String[] args)
    {
        
        
        Scanner input = new Scanner(System.in);
        
    	int i;
        System.out.print("Enter number of values : ");
        int n = input.nextInt();
        int H[];
        H = new int[n+1]; //allocating the size of the array
        H[0]=n; // first value in the array is the size of the heap
        System.out.print("Enter the numbers (followed by spaces): ");
        for(i=1;i<=n;i++)
        {
            H[i] = input.nextInt();
        }
        System.out.print("\n\nArray of numbers : ");
        for(i=1;i<=n;i++)
        {
            System.out.print(H[i] + " ");
        }
        
       //System.out.print("\nHeapiFy is Running \n");
       
       
       for(i=H[0]/2;i>0;i--)
        {
            //System.out.print("For loop for Heapify. i=" + i+"\n");
            MinHeapify(H,i,n);
            //System.out.print("\n" + H[i]);
        } 
        
       
       //MinHeapify(H,n/2,n);
       
       System.out.print("\nHeap (using Floyd's Algorithm) : ");
       
       for(i=1;i<=n;i++)
        {
            System.out.print(H[i] + " ");
        }
       
        
       for(i=1;i<=n;i++)
       {
           //System.out.print("\nFor loop for Sort. i=" + i+"\t\t");
           sort(H,i,n);
        }
       
       System.out.print("\nPrint after Heap Sort : ");
       
       for(i=1;i<=n;i++)
        {
            System.out.print(H[i] + " ");
        }
       
               
       System.out.print("\n\n"); 
        
    }
} 
