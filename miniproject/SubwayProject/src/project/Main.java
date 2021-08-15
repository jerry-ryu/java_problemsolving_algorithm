package project;

import static java.io.File.separator;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello goorm!");

        String path = separator + "workspace" + separator
            + "src" + separator + "train-csv.csv";
        
        Service service = new Service(CSVParser.ReadData(path));   
        service.searchStation("시청");
        System.out.println();
        service.searchLine("2호선");
        service.searchLine("2호선");

        
        
    }

}
