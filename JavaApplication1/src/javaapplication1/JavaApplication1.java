/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author zhengwei
 */
public class JavaApplication1 {

    /**
     * @param args the command line arguments
     */
    
    static List<String[]> connectedCities = new ArrayList<String[]>(); 
    static List<String[]> queryCities = new ArrayList<String[]>(); 
    static List<String[]> festiveCities = new ArrayList<String[]>();
    
    static HashMap<String,CityNode> connectedCityNodes = new HashMap<String,CityNode>();
        
    public static void main(String[] args) {
        // TODO code application logic here
        
        /*
        List<int[]> intList= new ArrayList<int[]>(); 
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()){
            String line = scanner.nextLine();
            if(line == null||line.isEmpty()){
                processData(intList);
                break;
            }else{
                String[] strArray=line.split(" ");
                int[] intArray=new int[strArray.length];
                for(int i=0; i<intArray.length; i++){
                    intArray[i]=Integer.parseInt(strArray[i]);
                }
                intList.add(intArray);
            }
        }
        scanner.close();
        */
        List<String[]> strList= new ArrayList<String[]>(); 
        strList.add(new String[] {"5","5"});
        strList.add(new String[] {"1","2"});
        strList.add(new String[] {"1","3"});
        strList.add(new String[] {"3","4"});
        strList.add(new String[] {"3","5"});
        strList.add(new String[] {"2","5"});
        strList.add(new String[] {"2","3"});
        strList.add(new String[] {"1","3"});
        strList.add(new String[] {"2","3"});
        strList.add(new String[] {"2","4"});
        
        printData(strList);
      //  processData(strList);
    }

    private static void printData(List<String[]> strList) {
        String[] rowOne= strList.get(0);
        String n = rowOne[0];
        String m = rowOne[1];
        
        //below block to construct relations 
        for(int q=1; q<=Integer.parseInt(n)-1; q++){
            connectedCities.add(strList.get(q));
            
            CityNode cn1,cn2;
            if(connectedCityNodes.containsKey(strList.get(q)[0])){
                cn1 = connectedCityNodes.get(strList.get(q)[0]);
                connectedCityNodes.remove(cn1.cityNum);
            }else{
                cn1= new CityNode (strList.get(q)[0]);
            }
            
            if(connectedCityNodes.containsKey(strList.get(q)[1])){
                cn2 = connectedCityNodes.get(strList.get(q)[1]);
                connectedCityNodes.remove(cn2.cityNum);
            }else{
                cn2= new CityNode (strList.get(q)[1]);
            }
            
            cn1.addRelationship(cn2);
            cn2.addRelationship(cn1);
            
            connectedCityNodes.put((cn1.cityNum), cn1);
            connectedCityNodes.put((cn2.cityNum), cn2);
            
        }
        
        
        int orderCnt = 1;
        for(int p=Integer.parseInt(n); p<=Integer.parseInt(n)+Integer.parseInt(m)-1; p++){
            String[] temp=new String[2];
            temp[0]=String.valueOf(orderCnt);
            temp[1]=strList.get(p)[1];
            
            if(strList.get(p)[0].equals("1")){
                festiveCities.add(temp);
                CityNode cnToUpdate = connectedCityNodes.get(temp[1]);
                connectedCityNodes.remove(temp[1]);
                cnToUpdate.setToFestive();
                connectedCityNodes.put(cnToUpdate.cityNum, cnToUpdate);
            }else{
                queryCities.add(temp);
            }
            orderCnt ++;
        }
        
        
        
        System.out.println("connected cities are: ");
        for (String[] value:connectedCities){
            for (String subValue: value){
                System.out.print(subValue+" ");
            }
            System.out.println();
        }
        
        System.out.println("query cities are: ");
        for (String[] value:queryCities){
            for (String subValue: value){
                System.out.print(subValue+" ");
            }
            System.out.println();
        }
        
        System.out.println("festiveCities cities are: ");
        for (String[] value:festiveCities){
            for (String subValue: value){
                System.out.print(subValue+" ");
            }
            System.out.println();
        }
        
    }
}
