package Javalearning;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class qq {
    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入文件的目录及文件名(D:\\33\\333\\33.txt)");
        String path = scanner.next();
        System.out.println("请输入要插入的内容及行数");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));//
        String data = br.readLine();
        int insertLine = scanner.nextInt();

        insertStringInFile(path, insertLine, data);
        insertIntoDatabase(path,insertLine,data);
        System.out.println("插入成功");

        System.out.println("是否要查询输入的信息，如果需要的话请输入1,不需要的话请输入0");
        int judge = scanner.nextInt();
        if(judge==1){
            List<FileDatabase> file = OutputInfo();
            for (FileDatabase a: file
                 ) {
                System.out.print(a.getId()+"  ");
                System.out.print(a.getPath()+"  ");
                System.out.print(a.getInsertLine()+"  ");
                System.out.print(a.getData()+"  ");
            }
        }


    }


    public static void insertStringInFile(String path, int insertLine, String data) throws IOException {
        File inFile = new File(path);
        if (!inFile.exists()) {
            try {
                inFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("文件已创建");
        } else {
            System.out.println("文件已存在");
        }
        File outFile = File.createTempFile("name", ".tmp");//创建一个暂时储存的file
        FileInputStream fis = new FileInputStream(inFile);
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));
        FileOutputStream fos = new FileOutputStream(outFile, true);
        String readLine = "";
        PrintWriter out = new PrintWriter(fos);

        String Line;
        // 从第一行开始
        int line = 1;
        int length = 0;
        while ((Line = br.readLine()) != null) {
            // 循环到目标行的话，就将插入的数据输出到暂时储存的file中
            if (line == insertLine) {
                out.println(data);
            }
            // 输出读取到的数据
            out.println(Line);
            // 行号增加
            line++;
            //计算行数
            length++;
        }
        //如果file原本为空或者要插入的行数大于原本的行数则在末尾进行插入
        if (line == 1 || length < insertLine) {
            out.println(data);
        }
        //输出插入的数据
        out.flush();
        out.close();
        br.close();

        // 删除原始文件
        inFile.delete();
        // 把临时文件改名为原文件名
        outFile.renameTo(inFile);
    }

    public static Connection createConnection() throws SQLException, ClassNotFoundException {
        //连接数据库
        String url = "jdbc:mysql://localhost:3306/database1?serverTimezone=GMT%2B8";
        String username = "root";
        String password = "";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, username, password);
        return con;
    }
    public static void insertIntoDatabase(String path, int insertLine, String data) throws ClassNotFoundException, SQLException {
       //将信息输入到数据库中
        Connection con = createConnection();
        String query = "INSERT INTO file(id,path,insert_line,data) values(default,?,?,?)";
        PreparedStatement ps = con.prepareStatement(query);

        ps.setString(1,path);
        ps.setInt(2,insertLine);
        ps.setString(3,data);
        ps.executeUpdate();
    }

    public  static List<FileDatabase> OutputInfo() throws SQLException, ClassNotFoundException {
        List<FileDatabase> file = new ArrayList<>();
        Connection con = createConnection();
        String query = "SELECT * FROM file";

        PreparedStatement ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            FileDatabase fd = new FileDatabase();
            fd.setId(rs.getInt("id"));
            fd.setPath(rs.getString("path"));
            fd.setData(rs.getString("i"));
            fd.setInsertLine(rs.getInt("insert_line"));
        }
        return file;
    }
}
