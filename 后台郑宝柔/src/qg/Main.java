package Javalearning;
import java.io.*;
import java.sql.*;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
        public static void main(String[] args) throws IOException {
//        File file = new File("C:\\Users\\zbr\\Desktop\\abc.txt");
//        File file2 = new File("C:\\Users\\zbr\\Desktop\\captmidn.txt");
        FileInputStream fileInputStream = new FileInputStream(args[0]);
            FileOutputStream fileOutputStream = new FileOutputStream(args[1]);
            int data;
            while((data=fileInputStream.read())!=-1) {
                fileOutputStream.write(data);
                fileOutputStream.flush();
            }
            System.out.println("copy success");
            fileOutputStream.close();
            fileInputStream.close();
        }
    }





