package study;

class Solution {
    private int answer = 0;
    private int[] number;
    private int tar;
    
    public int solution(int[] numbers, int target) {
        number = numbers;
        tar = target;
        dfs(0,0);
        
        return answer;
    }
    
    public void dfs(int now, int depth){
        if(depth == number.length){
            if(now == tar){
                answer += 1;
            }
            return;
        }
        dfs(now + number[depth], depth + 1);
        dfs(now - number[depth], depth + 1);
    }
}