/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author zhengwei
 */
public class JavaApplication2 {

    /**
     * @param args the command line arguments
     */
    static ArrayList<int[]> intList = new ArrayList<int[]>();
    
    public static void main(String[] args) {
        // TODO code application logic here
       
        
        getInputMap();
        processList();
        System.out.println();
    }

    private static void getInputMap() {
        Scanner sc = new Scanner(System.in);
        ArrayList<int[]> myIntList = new ArrayList<int[]>();
        boolean firstRowFlg = true;
        int n=Integer.MAX_VALUE;//row
        int m=Integer.MAX_VALUE;//column
        int rowCnt=0;
        while(sc.hasNextLine()){
            String nextLine = sc.nextLine();
            if(nextLine==null||nextLine.isEmpty()||rowCnt==n){
                break;
            }
            else{
                if(firstRowFlg){
                    String[] strArray = nextLine.split(" ");
                    n = Integer.parseInt(strArray[0]);
                    m = Integer.parseInt(strArray[1]);
                    firstRowFlg=false;
                }else{
                    rowCnt++;
                    String[] strArray = nextLine.split(" ");
                    int[] intArray = new int[strArray.length];
                    for(int i=0; i<intArray.length; i++){
                        intArray[i]=Integer.parseInt(strArray[i]);
                    }
                    myIntList.add(intArray);
                }  
            }
        }
        
        
        for(int i=0; i<m; i++){
            int[] anotherIntArray = new int[m];
            int j=0;
            for(int[] eachArray: myIntList){
                anotherIntArray[j]=eachArray[i];
                j++;
            }
            intList.add(anotherIntArray);
        }
    }

    private static void processList() {
        //add a new column into process
        
        //first column only consider the value itself, from down, from up
        
        //other other first column, consider itself, from down, up
        
        //store the optimal value after processing
        
        //if no more new column to add, return the max value in current column
        
        int[] optimalColumn = new int[intList.get(0).length]; //default all zero
        
        for(int i=0; i<intList.size(); i++){
            int[] columnToProcess = intList.get(i);
            optimalColumn = processThisColumn(optimalColumn, columnToProcess);
        }
        int maxValue = -1;
        for(int element:optimalColumn){
            if(element>maxValue){
                maxValue = element;
            }
        }
        
        System.out.println(maxValue);
    }
    
    
    //note that column2 is the current column to be processed
    private static int[] processThisColumn(int[] column1, int[] column2) {
        int[] optimalColumn = new int[column1.length];
        int[] columnFromLeft, columnFromUp, columnFromDown;
        
        columnFromLeft = processFromLeft(column1,column2);
        
        columnFromUp = processFromDownward(columnFromLeft, column2);
        columnFromDown = processFromUpward(columnFromLeft, column2);
        for(int j=0; j<column1.length; j++){
            optimalColumn[j] = Math.max(columnFromLeft[j], columnFromUp[j]);
            optimalColumn[j] = Math.max(optimalColumn[j], columnFromDown[j]);
        }
        return optimalColumn;
    }

    private static int[] processFromLeft(int[] column1, int[] column2) {
        int[] columnToReturn = new int[column1.length];
        
        for(int i=0; i<column1.length; i++){
            if(column1[i]==-1 || column2[i]==-1){
                columnToReturn[i] = -1;
            }else{
                columnToReturn[i] = column1[i] + column2[i];
            }
        }
        return columnToReturn;
    }

    private static int[] processFromDownward(int[] columnFromLeft, int[] column2) {
        int[] columnToReturn = new int[columnFromLeft.length];
        //start from last row
        //this is to find the max [orgin, or coulmnFromLeft
        columnToReturn[columnToReturn.length-1] = Math.max(column2[column2.length-1],columnFromLeft[columnFromLeft.length-1]);
        int base = columnToReturn[columnToReturn.length-1];
        
        for(int i=columnToReturn.length-2; i>=0; i--){
            if(base == -1 || column2[i] == -1){
                base = Math.max(column2[i], columnFromLeft[i]);
                columnToReturn[i] = base;
            }else{
                columnToReturn[i] = base + column2[i];
                base = columnToReturn[i];
            }
        }
        
        
        /*
        for(int i=0; i<columnFromLeft.length-1; i++){
            if(columnFromLeft[i+1]==-1 || column2[i] ==-1){
                columnToReturn[i] = -1;
            }else{
                columnToReturn[i] = columnFromLeft[i+1] + column2[i];
            }
        }
        //last column has no left lower value to add, therefore itself.
        columnToReturn[columnFromLeft.length-1] = column2[column2.length-1];
        */
        return columnToReturn;
    }

    private static int[] processFromUpward(int[] columnFromLeft, int[] column2) {
        int[] columnToReturn = new int[columnFromLeft.length];
        
        //start from first row
        //this is to find the max [orgin, or columnFromLeft
        columnToReturn[0] = Math.max(column2[0],columnFromLeft[0]);
        int base = columnToReturn[0];
        
        for(int i=1; i<columnToReturn.length; i++){
            if(base == -1 || column2[i] ==-1){
                base = Math.max(column2[i], columnFromLeft[i]) ;
                columnToReturn[i] = base;
            }else{
                columnToReturn[i] = base + column2[i];
                base = columnToReturn[i];
            }
        }
        
        
        /*
        //first column has no left upper value to add, therefore itself.
        columnToReturn[0] = column2[0];
        
        for(int i=1; i<columnFromLeft.length; i++){
            if(columnFromLeft[i-1] == -1 || column2[i] == -1){
                columnToReturn[i] = -1;
            }else{
                columnToReturn[i] = columnFromLeft[i-1] + column2[i]; 
            }
        }
        */
        return columnToReturn;
    }
}
