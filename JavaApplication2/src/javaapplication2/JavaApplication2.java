/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication2;

import java.util.ArrayList;
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
        System.out.println();
    }

    private static ArrayList<int[]> getInputMap() {
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
        
        
        return intList;
    }
    
}
