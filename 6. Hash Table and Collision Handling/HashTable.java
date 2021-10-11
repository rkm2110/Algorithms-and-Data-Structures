/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hashtable;

import java.io.*;
/**
 *
 * @author userr
 */
public class HashTable 
{
    //global count for number of collisions
    public static int colCount =0;
    public static int TotalColCount =0;
    public static void main(String[] args) 
    {
        HashTable h = new HashTable();
        
        //create hash Table
        int size=31;
        System.out.println("Initial Table Size = "+size);
        
        //Insert the entries in Hash Table
        String[] hTable = h.insert(size);
        
        System.out.println();
        //Print the Hash Table
        System.out.println("Hash Table is represented below: ");
        for(int i=0; i<hTable.length; i++)
        {
            System.out.println("Table"+"["+i+"] => \t"+hTable[i]);
        }
        
        System.out.println();
        System.out.println("Total Collisions (Counting 1 for repeating collisions of 1 entry) = "+colCount);
        System.out.println("Total Collisions (including repeating collisions for 1 entry) = "+TotalColCount);
        
    }
    
    //
    public int hashFunction(String str, int size)
    {
        //get the ASCII value of first character
        int value = str.charAt(0);
        //System.out.println("Inside Hash Function. String ="+ str);
        //System.out.println("Inside Hash Function. ASCII value of first character ="+ value);
        //System.out.println("Inside Hash Function. index ="+ value%size);
        return value%size;
        
        //return hashVal%size;
    }
    
    public boolean CheckColl(int index, String[] hTable)
    {
        if(hTable[index]!=null)
        {
            //System.out.println("Inside check Coll. Index ="+index);
            //System.out.println("Inside check Coll. Index not NULL");
            return true;
        }
        return false;
    }
    
    
    public int colResolution(int index, String[] hTable)
    {
        
        int initIndex = index;
        int curColCount = 0;
        //check collisions
        while(hTable[index]!=null)
        {
            //global collision count
            TotalColCount++;
            //current element collision count
            curColCount++;
           // System.out.print(hTable[index]+" ");
            //get index after quadratic probic open addressing
            index = QuadraticProb(initIndex,hTable.length,curColCount);
        }
        colCount++;
        //System.out.println("Inside ColResolution ="+ colCount);
        
        return index;
    }
    
    public int QuadraticProb(int index, int size, int curColCount)
    {
        index = index+(curColCount*curColCount); 
        return index%size;
    }
    
    public String[] insert(int size)
    {
        //global collision count
        colCount = 0;
        //intializing hash table
        String[] hTable = new String[size];
         //index for insertion in table
        int index = 0;
        //load factor
        int loadFactor = 0;

        try
        {
            //read file
            //String currentDirectory = System.getProperty("user.dir");
            //File file = new File("currentDirectory"+"input.txt");
            File file = new File("C:\\Users\\userr\\OneDrive\\Desktop\\Spring2020\\Algo DS\\Assignment 6\\data.txt");
            BufferedReader br = new BufferedReader(new FileReader(file)); 
            String st;
            
            //loop through all the words
            while ((st = br.readLine()) != null)
            {
                loadFactor++;
                //index for insertion
                index=hashFunction(st, hTable.length);
                
                int present_coll=0;
                
                
                if(CheckColl(index,hTable)==true)
                {   
                    //System.out.println("Inside Insert Check Coll ="+ present_coll++);
                    
                    //resolve collision
                    //returns index for insertion
                    index = colResolution(index,hTable);
                   
                }
                
                //insert data into hash table
                hTable[index]=st;
                    
                //check if load factor exceed 0.5
                if(loadFactor>=(hTable.length/2))
                {
                    //delete current table
                    hTable = null;
                    
                    //call the function again with double table size
                    size = findNextPrime(2*size);
                    System.out.println("New Table Size = "+size);
                    //creates new hash table
                    hTable = insert(size);
                    break;
                }
                

            }
            
            
                
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return hTable;
    }
    
    public int findNextPrime(int num)
    {
        
        boolean prime = false;
        while(prime==false)
        {
            prime = true;
            for(int i=2; i<=(int) Math.sqrt(num); i++)
            {
                if(num%i == 0)
                {
                   prime = false; 
                }
            }
            num++;
        }
        
        return num-1;
    }
}

    
