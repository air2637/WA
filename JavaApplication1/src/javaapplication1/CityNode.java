/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.util.ArrayList;

/**
 *
 * @author zhengwei
 */
public class CityNode {
    public ArrayList<CityNode> directNeighbors;
    public String cityNum;
    public boolean isCityFestive;
    
    public CityNode(String cityNum){
        this(cityNum,false);
    }
    
    public CityNode(String cityNum, boolean isCityFestive){
        this.cityNum=cityNum;
        if(this.cityNum.equals("1")){
          this.isCityFestive=true;
        }else{
          this.isCityFestive=isCityFestive;  
        }
        
        directNeighbors = new ArrayList<CityNode>();
    }
    
    public void addRelationship(CityNode otherCityNum){
        directNeighbors.add(otherCityNum);
    }
    
    public void setToFestive(){
        this.isCityFestive=true;
    }
    
}
