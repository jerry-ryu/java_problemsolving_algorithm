package project;

import java.util.HashMap;
import java.util.Iterator;

public class Service implements ServiceImpl{
    private HashMap<String, StationData> stationList;
    private HashMap<String, String> startingStation = new HashMap<>();
    Service(HashMap<String,StationData> stationList){
        this.stationList = stationList;
        startingStation.put("1호선", "서울역");
        startingStation.put("2호선", "시청");
        startingStation.put("2호선 성수지선", "성수");
        startingStation.put("2호선 신정지선", "신도림");
        startingStation.put("3호선", "지축");
        startingStation.put("4호선", "당고개");
    }
    
    public void findWays(String stationA, String stationB){
        StationData startS = stationList.get(stationA);
        if(startS == null){
            System.out.println(stationA + "이(가) 없습니다");
            return;
        }
        StationData endS = stationList.get(stationB);
        if(endS == null){
            System.out.println(stationB + "이(가) 없습니다");
            return;
        }
        
        dijkstra D = new dijkstra();
        D.findway(startS, endS);
        clearAll();
    }
    
    
    public void searchStation(String station){
        StationData s = stationList.get(station);
        try{
            s.printStationInfo();
        }catch(NullPointerException e){
            System.out.println(station + "이(가) 없습니다");
        }
        
    }
    
    
    public void searchLine(String line){
        StationData s = stationList.get(startingStation.get(line));
        System.out.println("< " + line +  " >");
        System.out.println();
        int p = 0;
        while(s!=null){
            if(p==10){
                System.out.println();
                p=0;
            }
            System.out.print(s.getStationName() + "  ");
            s.visit();
            s = findLineStation(s,line);
            p++;
        }
        
        System.out.println();
        System.out.println();
        
        clearAll();
    }
    
    StationData findLineStation(StationData s, String line){
        HashMap<StationData, String[]> map = s.getConnectedStation();
        for(StationData tmps : map.keySet()){
            if(map.get(tmps)[0].equals(line) && tmps.isVisited() == false){
                return tmps;
            }
        }
        
        return null;
    }
    
    void clearAll(){
        Iterator<String> keys = stationList.keySet().iterator();
        while(keys.hasNext()){
            stationList.get(keys.next()).clear();
        }

    }
}
