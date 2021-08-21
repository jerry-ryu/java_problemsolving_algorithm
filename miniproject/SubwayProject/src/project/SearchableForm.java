package project;

abstract class SearchableForm {
    protected boolean visited;
    protected StationData pre=null;; //어떤 역에서 방문하였는지
    protected int cost = Integer.MAX_VALUE; //현재 역까지의 경로 비용
    
    abstract void set(StationData pre, int cost);
    
    void visit(){
        visited = true;
    }
    
    boolean isVisited(){
        return visited;
    }
    
    StationData whoVisited(){
        return this.pre;
    }
    
    int howCost(){
        return this.cost;
    }
    
    void firstStation(){
        this.cost = 0;
    }
}
