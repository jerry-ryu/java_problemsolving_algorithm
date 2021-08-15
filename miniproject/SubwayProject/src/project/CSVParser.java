package project;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;

class CSVParser {
    static HashMap<String, StationData> stationList;
    
    public static HashMap<String, StationData> ReadData(String filename){
        System.out.println("start parsing");
        stationList= new HashMap<>();
        
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try{
            fileReader = new FileReader(filename);
            bufferedReader = new BufferedReader(fileReader);
            /* 원래 /workspace/train-csv.csv가 터미널에서 file -bi train-csv.csv 명령어를 쳤을때,
            text/plain; charset = iso-8859-1로 인코딩 되어있어서
            iconv -c -f iso-8859-1 -t utf-8 train-csv.csv 명령어로
            utf-8로 인코딩해주고.
            
            parser에서 Charset.forName("utf-8");로 utf-8로 데이터를 읽도록 하였다.
            */
            Charset.forName("utf-8");
            
            String data;
            StationData previous=null;
            String[] pretmpString= null;
            
            bufferedReader.readLine();//한줄 넘기기
            
            while((data = bufferedReader.readLine()) != null){
                
                //,로 파싱
                String[] tmpString= data.split(",");                
                
                //주변 데이터 한글만 남기기(""자르기)
                for(int i = 0; i<3; i++){
                    tmpString[i] = tmpString[i].substring(1,tmpString[i].length()-1);
                }
                
                
                //호선 순으로 정렬되어있으므로, 호선이 바뀌면 새롭게 노드 저장
                if(!(previous==null)){
                    if(!pretmpString[0].equals(tmpString[0])){
                        previous = null;
                        pretmpString = null;
                    }
                }
                
                
                    
                String[] tmpValue = {tmpString[0],tmpString[2]};
                StationData tmp;
                
                if(isStationExist(tmpString[1])){
                    if(previous == null){
                        tmp = stationList.get(tmpString[1]);
                        
                    }else{
                        tmp = stationList.get(tmpString[1]);
                        tmp.setData(previous,tmpValue);
                        previous.setData(tmp, tmpValue);
                        
                    }
                }else{
                    if(previous == null){
                        tmp = new StationData(tmpString[1]);
                        stationList.put(tmpString[1], tmp);
                    }else{
                        tmp = new StationData(tmpString[1]);
                        tmp.setData(previous, tmpValue);
                        previous.setData(tmp, tmpValue);
                        stationList.put(tmpString[1], tmp);
                    }
                }
                
                previous = tmp;
                pretmpString = tmpString;
                
            }
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(bufferedReader != null){
                try{
                    bufferedReader.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
            if(fileReader != null){
                try{
                    fileReader.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
        
        System.out.println("parsing complete!");
        return stationList;
    }
    
    
    private static boolean isStationExist(String name){
        if(stationList.containsKey(name)){
            return true;
        }
        return false;
    }
}
