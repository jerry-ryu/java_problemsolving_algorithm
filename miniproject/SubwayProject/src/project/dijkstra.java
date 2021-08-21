package project;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Stack;

public class dijkstra {
    PriorityQueue<StationData> pq = new PriorityQueue<>(new Comparator<StationData>() {
        @Override
        public int compare(StationData s1, StationData s2){
            return s1.howCost() - s2.howCost();
        }
});
    
    public void findway(StationData start, StationData end){
        int nowCost = 0;
        start.firstStation();
        pq.add(start);
        
        while(!pq.isEmpty()){
            StationData tmp = pq.poll();           
            
            HashMap<StationData, String[]> tmpmap = tmp.getConnectedStation();
            for(StationData tmps : tmpmap.keySet()){
                if(tmps.howCost() > tmp.howCost() + Integer.parseInt(tmps.getConnectedStation().get(tmp)[1])){
                    tmps.set(tmp, nowCost);
                    pq.add(tmps);
                }
            }
        }
        
        reverse(end);
        System.out.println("소요시간은 " + end.howCost() + "분 입니다.");
    }
    
    public void reverse(StationData end){
        Stack<String> track = new Stack<>();
        
        StationData tmp = end;
        String line = tmp.getConnectedStation().get(tmp.whoVisited())[0];
        
        
        String tmpString=null;
        
        while(tmp.whoVisited() != null){
            
            
            if(!line.equals(tmp.getConnectedStation().get(tmp.whoVisited())[0])){
               tmpString = "(에서 "+ line +"으로 환승)";
                track.add(tmpString);
                line = tmp.getConnectedStation().get(tmp.whoVisited())[0];
            }
            track.add(tmp.getStationName());
            tmp = tmp.whoVisited();
            
             
        }
        track.add(tmp.getStationName());
        
        int i = 0;
        System.out.println("이동경로는 " +line +"에서 시작하여");
        
        while(!track.isEmpty()){
            System.out.print(track.pop() + " ");
            if(i == 5){
                System.out.println();
                i=0;
            }
            i++;
        }
        System.out.println(" 도착 입니다");
    }
}
