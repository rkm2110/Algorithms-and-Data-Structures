/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dfstoposort;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author userr
 */
public class DFSTopoSort 
{
    
    
    //DFS 
    ArrayList<String> DFS_Stack_List = new ArrayList<>(); 
    public void DfsTopologicalSort(Graph G)
    {
        //visited vertex
        HashMap<Integer,Boolean> visited = new HashMap<Integer,Boolean>();
        //sorted vertex
        HashMap<Integer,Boolean> done = new HashMap<Integer,Boolean>();
        
        //initiate visited and done list to falseVe
        for(int i=0; i<G.Graph.length; i++)
        {
            visited.put(i, Boolean.FALSE);
            done.put(i, Boolean.FALSE);
        }
        
        //store all the possible start vertex for dfs
        ArrayList<Integer> startVertex = getStartVertexDfs(G);
        boolean result =true;
        for(int i=0; i<startVertex.size(); i++)
        {
            if(done.get(startVertex.get(i))==Boolean.FALSE)
            {
                
                //fuction for traversal
                result = DFS_Util(startVertex.get(i), visited, done, G);
            }
        }
        if(result == true)
        {
            for(int i=DFS_Stack_List.size()-1; i>=0; i--){
                System.out.print(DFS_Stack_List.get(i)+" ");
            }
        }
        
    }
    
    //dfs utility function
    public boolean DFS_Util(int start, HashMap visited, HashMap done,Graph G)
    {
       // check if cycle exist
        if(visited.get(start)==Boolean.TRUE && done.get(start)!=Boolean.TRUE)
        {
            System.out.println("ERROR !! Cycle Detected !!");
            return false;
        }

        //if vertex is already sorted
        if(visited.get(start)==Boolean.TRUE && done.get(start)==Boolean.TRUE)
        {
            return true;
        } 
        //mark vertex as visited
        visited.put(start, Boolean.TRUE);
        //looping through the ajacenct verices of vertex v
        for(int i=0; i<G.Graph.length; i++)
        {
            if(G.Graph[start][i] == 1)
            {
                //visited.put(i, Boolean.TRUE);
                boolean notCycle = DFS_Util(i, visited, done, G);
                if(notCycle == false)
                {
                    return false;
                }
            }
        }
        //print
        DFS_Stack_List.add(G.vertexList.get(start));
        //mark vertex as sorted
        done.put(start, Boolean.TRUE);
        
        return true;
    }
    
    //possible start vertex
    public ArrayList<Integer> getStartVertexDfs(Graph G)
    {
        //store all the possible start vertex for dfs
        ArrayList<Integer> startVertex = new  ArrayList<Integer>();
        for(int i=0; i<G.Graph.length; i++)
        {
            boolean checkStart = true;
            for(int j=0; j<G.Graph.length; j++)
            {
                if(G.Graph[i][j]==-1)
                {
                    checkStart = false;
                    break;
                }
            }
            if(checkStart == true)
            {
                startVertex.add(i);
            }
        }
        return startVertex;
    }
    
   
    //Main Function
    public static void main(String[] args) 
    {
        DFSTopoSort T = new DFSTopoSort();
        
        
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
        //DFS
        System.out.println();
        System.out.print("DFS Topological Sort : ");
        T.DfsTopologicalSort(graph);
       
        
       
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
        //DFS
        System.out.println();
        System.out.print("DFS Topological Sort : ");
        T.DfsTopologicalSort(graph);
        System.out.println();
       
    }
    
}


class Graph 
{
    //vertex to matrix index conversion
    ArrayList<String> vertexList = new ArrayList<String>();
    //Matrix representation
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
    
    //vertices index from vertexList
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