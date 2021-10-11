
package graphbfs;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;
       

public class GraphBFS 
{
    //vertices
    int vert;
    // list of vertex
    LinkedList[] list = null;
    
    //constructor
    GraphBFS(int verticesCount)
    {
        this.vert = verticesCount;
        this.list = new LinkedList[this.vert];
        
    }
   
    
    //method for creating vertex
    public void addVertex(int vertex)
    {
        LinkedList linkedlist = new LinkedList();
        this.list[vertex]= linkedlist; //add vertex to the graph
    }
    
    //method for adding edges
    public void addEdge(int source, int destination)
    {
        LinkedList currList = this.list[source];
        currList.add(destination); //add vertices to list
    }
    
    public static void main(String[] args) 
    {
        
        int vertices=16;
        Scanner input = new Scanner(System.in);
        
        GraphBFS graph = new GraphBFS(vertices);
        
        for(int i=0;i<vertices;i++)
        {
            graph.addVertex(i);
        }
       
        
        //add edges between vertices
        graph.addEdge(0,2);graph.addEdge(2,0);
        graph.addEdge(0,1);graph.addEdge(1,0);
        graph.addEdge(1,2);graph.addEdge(2,1);
        graph.addEdge(1,8);graph.addEdge(8,1);
        graph.addEdge(1,4);graph.addEdge(4,1);
        graph.addEdge(1,6);graph.addEdge(6,1);
        graph.addEdge(2,3);graph.addEdge(3,2);
        graph.addEdge(3,4);graph.addEdge(4,3);
        graph.addEdge(3,7);graph.addEdge(7,3);
        graph.addEdge(3,9);graph.addEdge(9,3);
        graph.addEdge(4,5);graph.addEdge(5,4);
        graph.addEdge(4,8);graph.addEdge(8,4);
        graph.addEdge(5,7);graph.addEdge(7,5);
        graph.addEdge(5,9);graph.addEdge(9,5);
        graph.addEdge(6,8);graph.addEdge(8,6);
        graph.addEdge(6,7);graph.addEdge(7,6);
        graph.addEdge(7,8);graph.addEdge(8,7);
        //disconnection between graphs
        graph.addEdge(10,11);graph.addEdge(11,10);
        graph.addEdge(10,12);graph.addEdge(12,10);
        graph.addEdge(10,14);graph.addEdge(14,10);
        graph.addEdge(11,13);graph.addEdge(13,11);
        graph.addEdge(11,15);graph.addEdge(15,11);
        graph.addEdge(12,13);graph.addEdge(13,12);
        graph.addEdge(12,14);graph.addEdge(14,12);
        graph.addEdge(13,15);graph.addEdge(15,13);
        graph.addEdge(13,14);graph.addEdge(14,13);
        graph.addEdge(14,15);graph.addEdge(15,14);
        
        
        //print the Adjacency List
        graph.printGraph();
        
        System.out.println();
        
        System.out.print("Enter the source of traversal : ");
        int n = input.nextInt();
        System.out.print("BFS Traversal : ");
        graph.bfs(n);
        
        System.out.println();
        System.out.println();
        
    }
    
   
    
    //print Graph
    public void printGraph()
    {
        System.out.println("Graph in the form of Adjacency List :");
        for(int i=0; i<list.length; i++)
        {
            System.out.print(i+" -> ");
            System.out.println(list[i]); //print each adjacency list
        }
    }
    
    
    //BFS traversal
     public void bfs(int source)
     {
        boolean[] visited = new boolean[this.vert];
        BFSTrav(source,visited);
        
        for(int i=0; i< visited.length; i++)
        {
            if(visited[i]!= true)
            {
                //fuction for traversal
                BFSTrav(i, visited);
            }
        }
     }
    public void BFSTrav(int v, boolean visited[])
    {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(v); //add to queue
        visited[v] = true; // mark visited
            
        while(queue.size() != 0)
        {
            v = queue.poll(); //dequeue
            System.out.print(v+" ");
            LinkedList adjList = list[v]; //get the adjacent list
            
            for(int num=0; num<adjList.size(); num++) //Loop the Adjacency list
            {
                int j = (int) adjList.get(num);
                if(visited[j] != true) //check if previously visited
                {
                    queue.add(j);   //add to queue
                    visited[j] = true; // mark visited
                }
            }
        }
        
    }
}
    
