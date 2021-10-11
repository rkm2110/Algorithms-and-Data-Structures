/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bfstoposort;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author userr
 */
public class BFSTopoSort 
{

    //BFS 
    public void BFSTopoSort(Graph G)
    {
        //store the predecessor Count of each vertex
        int[] predCount = setPredCount(G.Graph);
        //create a queue
        Queue<Integer> queue = new LinkedList<>();
        //arraylist to store sorted list
        ArrayList<Integer> sortedList = new ArrayList<Integer>();
        
        BFS_Util(predCount,G,queue,sortedList);
        
    }
    
    public void BFS_Util(int[] predCount, Graph G,Queue<Integer> queue,ArrayList<Integer> sortedList)
    {
        
        //if all the vertices are sorted
        if(sortedList.size() == predCount.length && queue.isEmpty()==true){
            for(int i=0; i<sortedList.size(); i++){
                System.out.print(G.vertexList.get(sortedList.get(i))+" ");
            }
            return;
        }
       
        //get indexes with value zero from predCount
        int[] zeroValueList = getZeroValueIndex(predCount);
        
        if(zeroValueList.length==0 && queue.isEmpty()==true && (sortedList.size() != predCount.length) && sortedList.size()>0){
            System.out.print("ERROR !! Cycle Detected !!");
            return;
        }
        // insert 0 value predCounts to queue
         for(int i=0; i<zeroValueList.length; i++)
         {
             queue.add(zeroValueList[i]);
             //set zero value predCOunts to -1 so that it won't be retrieve in the next pass
             predCount[zeroValueList[i]] = -1;
         }
        
             //dequeue
            int dequeueIndex = queue.remove();
            sortedList.add(dequeueIndex);

            //rearranged predCount
            for(int i=0; i<G.Graph.length; i++)
            {
                    if(G.Graph[dequeueIndex][i]>0)
                    {
                        predCount[i]--;
                    }
            }
         
        //recursive call to BFS_Util
        BFS_Util(predCount,G,queue,sortedList);
    }
    //gets the indexes with value zero from predCount
    public int[] getZeroValueIndex(int[] predCount)
    {
        ArrayList<Integer> a = new  ArrayList<>();
        for(int i=0; i<predCount.length; i++)
        {
            if(predCount[i]==0)
            {
                a.add(i);
            }
        }
        
        int[] result = new int[a.size()];
        
        for(int i=0; i<result.length; i++)
        {
            result[i] = a.get(i);
        }
        return result;
    }
    
    //initiate the predCount
    public int[] setPredCount(int[][] a)
    {
        int[] predCount = new int[a.length];
        
        for(int i=0; i<a.length; i++)
        {
            int count = 0;
            for(int j=0; j<a.length; j++)
            {
                if(a[i][j]==-1)
                {
                    count++;
                }
                predCount[i] = count;
            }
        }
        return predCount;
    }
    
    //Main Function
    public static void main(String[] args) 
    {
        BFSTopoSort T = new BFSTopoSort();
        
        
        //Graph 1
        Graph graph = new Graph(14);
        //Add Vertices
        graph.addVertex("m");
        graph.addVertex("n");
        graph.addVertex("o");
        graph.addVertex("p");
        graph.addVertex("q");
        graph.addVertex("r");
        graph.addVertex("s");
        graph.addVertex("t");
        graph.addVertex("u");
        graph.addVertex("v");
        graph.addVertex("w");
        graph.addVertex("x");
        graph.addVertex("y");
        graph.addVertex("z");
        
        
        // Add edges
        graph.addEdge("m", "q");
        graph.addEdge("m", "r");
        graph.addEdge("m", "x");
        graph.addEdge("n", "o");
        graph.addEdge("n", "q");
        graph.addEdge("n", "u");
        graph.addEdge("o", "r");
        graph.addEdge("o", "s");
        graph.addEdge("o", "v");
        graph.addEdge("p", "o");
        graph.addEdge("p", "s");
        graph.addEdge("p", "z");
        graph.addEdge("q", "t");
        graph.addEdge("r", "u");
        graph.addEdge("r", "y");
        graph.addEdge("s", "r");
        graph.addEdge("u", "t");
        graph.addEdge("v", "w");
        graph.addEdge("v", "x");
        graph.addEdge("w", "z");
        graph.addEdge("y", "v");
        
       
       
       //print
        System.out.println("GRAPH 1 : ");
        graph.printGraph();
        //BFS
        System.out.println();
        System.out.print("BFS Topological Sort : ");
        T.BFSTopoSort(graph);
        
       
        System.out.println();
        System.out.println();
        
        //Graph 2
        graph = new Graph(7);
        //Add vertices
        graph.addVertex("1");
        graph.addVertex("2");
        graph.addVertex("3");
        graph.addVertex("4");
        graph.addVertex("5");
        graph.addVertex("6");
        graph.addVertex("7");
        //Add edges
        graph.addEdge("1", "2");
        graph.addEdge("1", "5");
        graph.addEdge("1", "6");
        graph.addEdge("2", "3");
        graph.addEdge("2", "5");
        graph.addEdge("2", "7");
        graph.addEdge("3", "4");
        graph.addEdge("4", "5");
        graph.addEdge("4", "7");
        graph.addEdge("5", "7");
        graph.addEdge("5", "8");
        graph.addEdge("6", "5");
        graph.addEdge("6", "8");
        graph.addEdge("7", "4");
        graph.addEdge("7", "8");
        
        
        //print
        System.out.println();
        System.out.println("GRAPH 2 : ");
        graph.printGraph();
        //BFS
        System.out.println();
        System.out.print("BFS Topological Sort : ");
        T.BFSTopoSort(graph);
        System.out.println();
        System.out.println();
       
    }
    
}


class Graph 
{
    //vertex to matrix index conversion
    ArrayList<String> vertexList = new ArrayList<String>();
    //Graph representation as 2D matrix
    int[][] Graph;
    //constructor
    Graph(int vertexCount)
    {
        Graph = new int[vertexCount][vertexCount];
        for(int i=0; i<vertexCount; i++){
            for(int j=0; j<vertexCount; j++)
            {
                Graph[i][j] = 0;
            }
        }
    }
    //add vertex
    public void addVertex(String vertex)
    {
        vertexList.add(vertex);
    }
    //add edge
    public void addEdge(String source, String destination)
    {
        //get source and destination vertex index
        int[] index = getVertextToIndex(source,destination);
        int sourceIndex= index[0];
        int destinationIndex= index[1];
        
        //positive edges
        Graph[sourceIndex][destinationIndex] = 1;
        //negative edges or reverse edges
        Graph[destinationIndex][sourceIndex] = -1;
    }
    
    //get index of the vertices from vertexList Arraylist.
    public int[] getVertextToIndex(String source, String destination)
    {
        int[] index = new int[2];
        for(int i=0; i<vertexList.size(); i++)
        {
            if(vertexList.get(i)==source)
            {
                index[0]=i;
            }
            if(vertexList.get(i)==destination)
            {
                index[1]=i;
            }
        }
        return index;
    }
    
   //print matrix
    public void printGraph()
    {
       System.out.println("Matrix representation of the Graph : ");
       for(int i=0; i<Graph.length; i++)
       {
           for(int j=0; j<Graph.length; j++)
           {
               System.out.print(Graph[i][j]+"\t");
           }
           System.out.println();
       } 
    }
  
}