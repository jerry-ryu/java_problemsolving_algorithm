package project;

import java.util.HashMap;
import java.util.HashSet;

class StationData extends SearchableForm{
    
    private String stationName; //역명
    private HashMap<StationData, String[]> connectedStation = new HashMap<>(); //주변 역과 걸리는 시간
    
    StationData(String name){
        this.stationName = name;
    }
    
    void setData(StationData s, String[] value){
        this.connectedStation.put(s,value);
    }
    
    @Override
    void set(StationData pre, int cost){
        this.pre = pre;
        this.cost = pre.howCost() +
            Integer.parseInt(this.connectedStation.get(pre)[1]);
    }
    
    String getStationName(){
        return this.stationName;
    }
    
    boolean istransferStation(){
        HashSet<String> set = new HashSet<>();
        for(HashMap.Entry<StationData, String[]> entry : connectedStation.entrySet()){
            set.add(entry.getValue()[0]);
        }
        if(set.size()>1){
            return true;
        }
        return false;
    }
    
    HashMap<StationData, String[]> getConnectedStation(){
        return this.connectedStation;
    }
    
    void clear(){
        this.visited = false;
        this.pre = null;
        this.cost = Integer.MAX_VALUE;
    }
    
    void printStationInfo(){
        System.out.println("< " + stationName +  " >");
        System.out.println();
        System.out.println( stationName +  "은");
        if(istransferStation()){
            System.out.println( "환승역이며,");
        }
        for(StationData s : connectedStation.keySet()){
            String[] tmp = connectedStation.get(s);
            System.out.println(s.getStationName() + "과 " + tmp[0] + "으로 연결되어 있고, 소요시간은" 
                               + tmp[1] + "분입니다.");
        }
        System.out.println("이상입니다.");
    }
    
}
