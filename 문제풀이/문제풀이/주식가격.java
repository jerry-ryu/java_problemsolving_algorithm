package study;

import java.util.*;

class Solution {
    
    public class keyvalue{
        
        private int key;
        private int value;
        
        keyvalue(int a, int b){
            this.key = a;
            this.value = b;
        }
        
        public int getkey(){
            return this.key;
        }
        
        public int getvalue(){
            return this.value;
        }
    }
    
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        
        Stack<keyvalue> stack = new Stack<>();
        for(int i = 0; i<prices.length; i++){
            keyvalue tmp = new keyvalue(i,prices[i]);
            if(stack.isEmpty()){
                stack.push(tmp);
            }
            else{
                while(!(stack.isEmpty())&&stack.peek().getvalue()>tmp.getvalue()){
                    keyvalue tmp2 = stack.pop();
                    answer[tmp2.getkey()] = i-tmp2.getkey();
                }
                stack.push(tmp);
            }
        }
        
        while(!(stack.isEmpty())){
            keyvalue tmp2 = stack.pop();
            answer[tmp2.getkey()] = prices.length-1-tmp2.getkey();
        }
        
        
        return answer;
    }
}
