import java.sql.*;
import java.util.Scanner;
public class JDBCPractice {
    public static Connection connectDB() throws SQLException, ClassNotFoundException {
        String url="jdbc:postgresql://localhost:5432/demo";
        String username="postgres";
        String pass="admin";
        Connection con=DriverManager.getConnection(url,username,pass);
        System.out.println("Connection Established");
        return con;
    }
    public static void displayStudent() throws SQLException, ClassNotFoundException {
        Connection con=connectDB();
        String query="select * from student order by sid";
        Statement st=con.createStatement();
        ResultSet rs=st.executeQuery(query);
        System.out.println("SID  Name  Marks");
        while(rs.next()){
            System.out.print(rs.getInt(1)+" ");
            System.out.print(rs.getString(2)+" ");
            System.out.println(rs.getInt(3));
        }
        con.close();
        System.out.println("Connection Closed");

    }
    public static void insertStudent() throws SQLException, ClassNotFoundException {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter Student ID: ");
        int sid=sc.nextInt();
        sc.nextLine();
        System.out.println("Enter Student Name: ");
        String name=sc.nextLine();
        System.out.println("Enter Marks: ");
        int marks=sc.nextInt();

        Connection con=connectDB();
        String query="insert into student values(?,?,?)";
        PreparedStatement st=con.prepareStatement(query);
        st.setInt(1,sid);
        st.setString(2,name);
        st.setInt(3,marks);
        st.execute();
        System.out.println("Data Inserted Successfully");
        con.close();

    }
    public static void deleteStudent() throws SQLException, ClassNotFoundException {
        Scanner sc=new Scanner(System.in);
        System.out.println("Delete by ID?(press 1)\n Delete By Name? (Press 2) ");
        int ch=sc.nextInt();
        int sid=0; String name="";
        if(ch==1){
            System.out.println("Please Enter Student ID ");
            sid=sc.nextInt();
        }
        else{
            sc.nextLine();
            System.out.println("Please Enter Name ");
            name=sc.nextLine();
        }

        Connection con=connectDB();
        String query="delete from student where sid=? or sname= ?";
        PreparedStatement st=con.prepareStatement(query);
        st.setInt(1,sid);
        st.setString(2,name);
        st.execute();
        System.out.print("The record was deleted successfully");
        con.close();
    }

    public static void updateSID() throws SQLException, ClassNotFoundException {
        Scanner sc=new Scanner(System.in);
        System.out.println("Please Enter the Current SID");
        int currSID=sc.nextInt();
        System.out.println("Please Enter the New SID");
        int newSID=sc.nextInt();
        Connection con=connectDB();
        String query="update student set sid=? where sid=?";
        PreparedStatement st=con.prepareStatement(query);
        st.setInt(1,newSID);
        st.setInt(2,currSID);
        st.execute();
        System.out.println("SID Updated Successfully");
        con.close();
    }
    public static void updateSName() throws SQLException, ClassNotFoundException {
        Scanner sc=new Scanner(System.in);
        System.out.println("Please Enter the Current Name");
        String currName=sc.nextLine();
        System.out.println("Please Enter the New Name");
        String newName=sc.nextLine();
        Connection con=connectDB();
        String query="update student set sname=? where sname=?";
        PreparedStatement st=con.prepareStatement(query);
        st.setString(1,newName);
        st.setString(2,currName);
        st.execute();
        System.out.println("Student Name Updated Successfully");
        con.close();
    }
    public static void updateSMarks() throws SQLException, ClassNotFoundException {
        Scanner sc=new Scanner(System.in);
        System.out.println("Please Enter the Student Name whose marks to be updated");
        String Name=sc.nextLine();
        System.out.println("Please Enter the New Marks");
        int newMarks=sc.nextInt();
        Connection con=connectDB();
        String query="update student set marks=? where sname=?";
        PreparedStatement st=con.prepareStatement(query);
        st.setInt(1,newMarks);
        st.setString(2,Name);
        st.execute();
        System.out.println("Student Marks Updated Successfully");
        con.close();
    }
    public static void updateStudent() throws SQLException, ClassNotFoundException {
        Scanner sc=new Scanner(System.in);
        System.out.println("What do you want to update? ");
        System.out.println("1. Student ID");
        System.out.println("2. Student Name");
        System.out.println("3. Student Marks");
        int ch=sc.nextInt();
        switch (ch){
            case 1: updateSID();break;
            case 2: updateSName();break;
            case 3:updateSMarks();break;
        }
    }
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Scanner sc=new Scanner(System.in);
        int ch;
        do {
            System.out.println("Please Choose from the following options");
            System.out.println("1. Display Table Student");
            System.out.println("2. Insert a new record into Student table");
            System.out.println("3. Update a new record of Student table");
            System.out.println("4. Delete a record from the Student table");
            System.out.println("5. Exit");
            ch=sc.nextInt();

            switch (ch) {
                case 1:displayStudent();break;
                case 2: insertStudent();break;
                case 4: deleteStudent();break;
                case 3: updateStudent();break;
                default:
                    System.out.println("Please Choose correct option");

            }
        }while(ch!=5);


    }
}
