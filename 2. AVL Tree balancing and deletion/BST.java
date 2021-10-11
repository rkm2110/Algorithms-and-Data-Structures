package bst;
import java.util.Scanner;


public class BST
{
    
    static class Node
    {
        int data;
        Node left;
        Node right;
        Node par;
    }
    
    public void printList(Node temp) 
    {
        if (temp==null) return;
        printList(temp.left);
        System.out.print(temp.data + " ");
        printList(temp.right);
    }
    
    public void printNode(Node temp,int val) 
    {
        if (temp==null) return;
        if (temp.data==val)
        {
        System.out.print("Node.data = " + temp.data + "\n");
        if (temp.par!=null) System.out.print("Node.par = " + temp.par.data + "\n");
        if (temp.par==null) System.out.print("Node.par = NULL\n");
        if (temp.left!=null) System.out.print("Node.left = " + temp.left.data + "\n");
        if (temp.left==null) System.out.print("Node.left = NULL\n");
        if (temp.right!=null) System.out.print("Node.right = " + temp.right.data + "\n\n");
        if (temp.right==null) System.out.print("Node.right = NULL\n\n");
        }
        else 
        {
        printNode(temp.left,val);
        printNode(temp.right,val);
        }
    }
    
    public Node createNewNode(int k)
    {
        Node a = new Node();
        a.data=k;
        a.left=null;
        a.right=null;
        a.par=null;
        return a;
    }
    
    public Node insert(Node node, int val)
    {
        if(node==null)
        {return createNewNode(val);
        }
        if(val<=node.data)
        {
            node.left=insert(node.left,val);
            node.left.par=node;
        }
        if(val>node.data)
        {
            node.right=insert(node.right,val);
            node.right.par=node;
        }
        return node;
    
    }
    
    public Node delete(Node node, int val)
    {
        if(node==null) return null;
        
        else if(node.data==val)
        {    
            if((node.left==null)&&(node.right==null)) //leaf node
            {
                //System.out.print("Leaf Node \n");
                Node temp2=node.par;
                if(temp2.left.data==node.data) temp2.left=null;
                else if(temp2.right.data==node.data) temp2.right=null;
                //node=null;
            }
            else if((node.left==null)&&(node.right.right==null)) // No predecessor but has right node
            {
                Node temp3P=node.par;
                Node temp3R=node.right;
                if(temp3P.left.data==node.data) temp3P.left=temp3R;
                else if(temp3P.right.data==node.data) temp3P.right=temp3R;
                temp3R.par=temp3P;
            }
                
                
            else    // Has a predecessor
            {
                Node pred=getPredecessor(node);
                //printNode(node,val); //check node values
                //System.out.print("Predecessor data: \n");
                //printNode(node,pred.data); //check predecessor values
                Node Par=pred.par; //Parent of predecessor
                //Predecessor node shifting
                if ((Par.data)==val) //pred.right will be null by default (pred.right>pred)
                {
                    node.data=pred.data;
                    Par.left=Par.left.left;
                    Par.left.par=Par;
                } 
                else
                {
                    node.data=pred.data;
                    Par.right=pred.left;
                    if ((Par.right)!=null) Par.right.par=Par;
                }
              
                
                
            }
        }
        else 
        {
            delete(node.left, val);
            delete(node.right, val);
            
        }
        
        return node;
    }
    
    public Node  getPredecessor(Node node) //takes node as input
    {
        if (node==null) return null;
        Node temp=node.left;
        while(temp!=null)
        {
            Node temp1=temp.right;
            if (temp1==null) break;
            temp=temp.right;
        }
        return temp;
    }
    
    public Node  getSuccessor(Node node)
    {
        if (node==null) return null;
        Node temp=node.right;
        while(temp!=null)
        {
            temp=temp.left;
        }
        return temp;
    }
    
    
    public static void main(String[] args)
    {
        BST a = new BST();
        Node root =null;
        
        Scanner input = new Scanner(System.in);
        
    	int i;
        System.out.print("Enter number of nodes : ");
        int n = input.nextInt();
        System.out.print("Enter the numbers (followed by spaces): ");
        for(i=1;i<=n;i++)
        {
            int num = input.nextInt();
            // Call method append to add numbers to the linked list
            root=a.insert(root,num);
        }
        
        /*
        //Nodes : 50, 40, 80, 20, 45, 60, 100, 70, 65, 42, 44, 30, 25, 35, 33
        root = a.insert(root,50);
        root = a.insert(root,40);
        root = a.insert(root,80);
        root = a.insert(root,20);
        root = a.insert(root,45);
        root = a.insert(root,60);
        root = a.insert(root,100);
        root = a.insert(root,70);
        root = a.insert(root,65);
        root = a.insert(root,42);
        root = a.insert(root,44);
        root = a.insert(root,30);
        root = a.insert(root,25);
        root = a.insert(root,35);
        root = a.insert(root,33);
        */
        
        System.out.print("In-Order Traversal of the Nodes : ");
        a.printList(root);
        System.out.print("\n");
        /*
        System.out.print("Enter the Node value for details : ");
        int x = input.nextInt();
        a.printNode(root, x);
        */
        System.out.print("\n");
        System.out.print("Enter the number to delete : ");
        int d = input.nextInt();
        root=a.delete(root,d);
        System.out.print("In-Order Traversal of the Nodes : ");
        a.printList(root);
        System.out.print("\n\n");
        System.out.print("Enter the number to delete : ");
        d = input.nextInt();
        root=a.delete(root,d);
        System.out.print("In-Order Traversal of the Nodes : ");
        a.printList(root);
        System.out.print("\n\n");
        System.out.print("Enter the number to delete : ");
        d = input.nextInt();
        root=a.delete(root,d);
        System.out.print("In-Order Traversal of the Nodes : ");
        a.printList(root);
        System.out.print("\n\n");
        System.out.print("Enter the number to delete : ");
        d = input.nextInt();
        root=a.delete(root,d);
        System.out.print("In-Order Traversal of the Nodes : ");
        a.printList(root);
        System.out.print("\n\n");
        
        
        
    }
}