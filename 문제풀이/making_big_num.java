class Solution {
    public String solution(String number, int k) {
        
        StringBuilder num = new StringBuilder(number); //StringBuilder 생성
        
        int i = 0;//인덱스 변수 i
        
       for(int j = 0; j<k; j++){
           i=0;
           int len = num.length()-1;
           while(i< len){
               if((int)num.charAt(i) < (int)num.charAt(i+1)){ 
                   //i번째 인덱스가 i+1번째 인덱스보다 작으면, i번째 인덱스 삭제
                   num.delete(i,i+1);
                   break;
               }
               else{
                   //그렇지 않으면, i+1번째 인덱스와 i+2번째 인덱스 비교
                   i++;
               }
           }
           
           if(i==len){
               num.delete(i,i+1);
           }
       }
        
        
        String answer = num.toString();
        return answer;
    }
}