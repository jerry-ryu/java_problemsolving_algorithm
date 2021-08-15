package project;

abstract class SearchableForm {
    protected boolean visited=false; //방문하였는지
    protected StationData pre=null;; //어떤 역에서 방문하였는지
    protected int cost = Integer.MAX_VALUE; //현재 역까지의 경로 비용
    
    void visit(){
        this.visited = true;
    }
    
    abstract void visit(StationData pre, int cost);
    
    boolean isVisited(){
        return this.visited;
    }
    
    StationData whoVisited(){
        return this.pre;
    }
    
    int howCost(){
        return this.cost;
    }
}
