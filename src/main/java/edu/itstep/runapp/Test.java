package edu.itstep.runapp;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;


public class Test {
    public static int reCall;
    static Logger log = Logger.getLogger(Test.class);
    public static int ARRAY_SIZE = 3 ;
    public static void main(String...arg){
    String fileName = "log"+System.getProperty("file.separator")+"log.out";
//    StringBuilder sb = new StringBuilder();
//    sb.append("log");
     try {
         readLogFile(fileName);
     }catch(IOException | SQLException e) {
         throw new RuntimeException(e);
     }


    }
    public static void readLogFile(String file)throws IOException,SQLException{
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
               System.out.println(line);
            }//end while loop

        }
    }
    public static void arrayOut(){
        int [] array = new int[ARRAY_SIZE];
        array[7] = 12;
    }

    public static  void heapOverflowError(){
        int[] array = new int[Integer.MAX_VALUE];
    }
    public static void recursiveError(){
        reCall++;
        System.out.println("recursive call "+reCall);
        recursiveError();
    }
}
