import java.sql.*;

public class DemoJDBC {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        /*
        import package
        load & register
        create connection
        create Statement
        execute statement
        process the results
        close
         */
        //Class.forName("org.postgresql.Driver");  //load & register - optional

        String url="jdbc:postgresql://localhost:5432/demo";
        String uname="postgres";  //default user name for psotgres
        String pass="admin";
        String query="select * from student";   //select
        String query1="insert into student values(4,'Nilayan',98)"; //insert
        String query2="update student set sname='Telusko' where sid=2"; //update
        String query3="delete from student where sid=6";

        int sid=8; String sname="Sorin"; int marks=100;
        String query4="insert into student values(?,?,?)";

        Connection con=DriverManager.getConnection(url,uname,pass);
        System.out.println("Connection Established");
        PreparedStatement st=con.prepareStatement(query4);
        st.setInt(1,sid);
        st.setString(2,sname);
        st.setInt(3,marks);
        boolean res=st.execute();
        if(!res)
            System.out.println("Data Inserted Sucessfully");
        else
            System.out.println("Error!");

//        Statement st=con.createStatement();  //Create Statement
//        boolean res=st.execute(query1);
//        boolean res=st.execute(query2);




//        ResultSet rs = st.executeQuery(query); //Execute Statement
//        rs.next();                              //goes to the 1st row or next availbe row
//        String name=rs.getString("sname");
//        System.out.println(name);
//        System.out.println("SID Name   Marks");
//        while(rs.next()){
//            System.out.print(rs.getInt(1)+" ");
//            System.out.print(rs.getString(2)+ " ");
//            System.out.println(rs.getInt(3));
//        }






        con.close();                            //Close Connection
        System.out.println("Connection Closed!");


    }
}
