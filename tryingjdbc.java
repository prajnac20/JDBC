import com.mysql.cj.xdevapi.PreparableStatement;

import java.sql.*;
import java.util.Scanner;

public class JdbcConnection {


   static Connection con;

    public static void main(String[] args) {
        try {
            String addr = "jdbc:mysql://localhost:3306/jdbc_demo";
            String username = "root";
            String password = "root123";
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(addr, username, password);
            System.out.println("connection is established");
        } catch (Exception e) {

        }
        while (true) {
            System.out.println("Enter option for operation of database");
            System.out.println("1. Insert\n2.Delete\n3.Find\n4.Exit");
            Scanner sc = new Scanner(System.in);
            int choice = sc.nextInt();
            switch (choice) {

            case 1:
                try {
                //Scanner sc = new Scanner(System.in);
                    System.out.println("Enter USN");
                    String usn = sc.next();
                    System.out.println("Enter name");
                    String name = sc.nextLine();
                    System.out.println("Enter branch");
                    String br = sc.next();
                    System.out.println("Enter place");
                    String place = sc.next();
                    System.out.println("Enter age");
                    int age = sc.nextInt();
                    String sql = "insert into student values(?,?,?,?,?)";
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setString(1,usn);
                    ps.setString(2,name);
                    ps.setString(3,br);
                    ps.setInt(4,age);
                    ps.setString(5,place);
                    boolean status = ps.execute();
                    System.out.println("Succesful");

                } catch (SQLException e) {
                    System.out.println(e);

                }
                break;

                case 2:
                    try{
                        System.out.println("Enter the usn you want to delete");
                        String usn = sc.next();
                        String st = "delete from student where usn = ?";
                        PreparedStatement ps = con.prepareStatement(st);
                        ps.setString(1,usn);
                        boolean status = ps.execute();

                        System.out.println("Deletion successful");
                    }
                    catch (SQLException e){
                        System.out.println(e);
                    }
                    break;
                case 3:
                    try{
                        System.out.println("Enter the usn you want to find");
                        String usn = sc.next();
                        String st = "select * from student where usn = ?";
                        PreparedStatement ps = con.prepareStatement(st);
                        ps.setString(1,usn);
                        ResultSet rs = ps.executeQuery();
                        String name=" ",branch=" ",place= " ";
                        int age;
                        if (rs.next())
                            //System.out.println("Deletion successful");
                            name =  rs.getString("NAME");
                            branch = rs.getString("BRANCH");
                            age = rs.getInt("AGE");
                            place = rs.getString("PLACE");
                            System.out.println(name+" "+branch+" "+age+" "+place);



                    }
                    catch (SQLException e){
                        System.out.println(e);

                    }
                    break;
                case 4: System.exit(0);
                    break;
                default:System.out.println("Wrong input");
                break;



        }
    }

    }
}

