package project;

interface ServiceImpl {
    
    void findWays(String stationA, String stationB); //a역에서부터 b역까지의 경로와 걸리는 시간을 출력
    void searchStation(String station); //a역의 정보와 연결된 역 출력
    void searchLine(String line); //호선의 모든 역 출력(환승역 표현)
    /*
        1호선의 시작역: 서울역
        2호선의 시작역: 시청
        2호선 성수지선의 시작역: 성수
        2호선 신정지선의 시작역: 신도림
        3호선의 시작역: 지축
        4호선의 시작역: 당고개
    */

    
}
