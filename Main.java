import java.sql.*;
import java.util.Scanner;

class invalidCredentials extends Exception{}

public class Main {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pro1","root","Apavan");
            Statement st = con.createStatement();
            Scanner sc= new Scanner(System.in);
            String query;
            ResultSet rs;

            sop("Welcome to Pavan's Bank\n");

            sop("1.Create Account\n2.Log in\n");
            int opt1 = sc.nextInt();
            sc.nextLine();

            if(opt1 == 1){
                sop("Enter Username: ");
                String Uname = sc.nextLine();

                sop("Enter Password: ");
                String pass = sc.nextLine();

                sop("Enter Balance: ");
                int bal = sc.nextInt();

                query = "INSERT INTO account values('"+Uname+"','"+pass+"',"+bal+");";
                st.execute(query);
                sop(query);
            } else if (opt1 == 2) {
                sop("Enter Username: ");
                String u_name = sc.nextLine();

                sop("Enter Password: ");
                String u_pass = sc.nextLine();

                rs = st.executeQuery("SELECT pass from account where uname='"+u_name+"'");
                rs.next();
                if(u_pass.equals(rs.getString(1))){
                    sopl("Login Successful");

                    rs = st.executeQuery("SELECT bal from account where uname='"+u_name+"'");
                    rs.next();
                    int bal = rs.getInt(1);

                    sop("1.Deposit\n2.Withdraw\n3.Balance Enquiry\n");
                    int opt2 = sc.nextInt();

                    if (opt2 == 1){
                        sop("Enter the amount for deposit: ");
                        int amt = sc.nextInt();

                        bal += amt;
                        st.execute("UPDATE account SET bal ="+bal+" where uname='"+u_name+"'");

                        sopl("Deposit Successful");
                    } else if (opt2 == 2) {
                        sop("Enter amount to Withdraw: ");
                        int amt = sc.nextInt();

                        if(bal<amt){
                            sopl("Insufficient Balance");
                        } else {
                            bal -= amt;
                            st.execute("UPDATE account SET bal ="+bal+" where uname='"+u_name+"'");

                            sopl("Withdraw Successful");
                        }
                    } else if (opt2 == 3) {
                        sopl("Available Balance is: "+bal);
                    } else {
                        sopl("Invalid Input");
                    }
                }else{
                    throw new invalidCredentials();
                }
            } else {
                sopl("Invalid Input");
            }
            st.close();
            con.close();

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            st.close();
            con.close();
        }
    }
    public static void sop(String msg){
        System.out.print(msg);
    }
    public static void sopl(String msg){
        System.out.println(msg);
    }
}
