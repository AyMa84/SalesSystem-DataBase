/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package selldatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class chatConnection extends Connections {
    private static String URL ;
    private static String USER;
    private static String PASSWORD ;
    public void chatConnection(){
       this.URL = URLgetter();
       this.USER = USERgetter();
       this.PASSWORD = PASSWORDgetter();
    }
    
        public void createChat(String buyer, String seller){
        String sql = "INSERT INTO chat (buyer, seller, chat) VALUES (?,?, ?)";
        try(Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)){
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, buyer);
            stmt.setString(2, seller);
            stmt.setString(3, " ");
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                String sql1="UPDATE chat SET chat_name = ? WHERE buyer = ? AND seller = ?";
                PreparedStatement stmt1 = conn.prepareStatement(sql1);
                stmt1.setString(1, buyer + seller);
                stmt1.setString(2, buyer);
                stmt1.setString(3, seller);
                int rowsInserted1 = stmt1.executeUpdate();
                if (rowsInserted1 > 0) {
                    new shortMassages("Chat created successfully.you can now access it by the Massager option in the main menue").setVisible(true);
                }
                else{
                    new shortMassages("Chat creation failed.please try again.").setVisible(true);
                }
                stmt1.close();
                
            }
            else{
                new shortMassages("Chat creation failed.please try again.").setVisible(true);
            }
            stmt.close();
            conn.close();            
        }catch(SQLException e){
            
        }
    }
    public boolean searchInsideChat(String buyer, String seller, int a){
        String sql = "SELECT chat FROM chat WHERE buyer = ? AND seller = ?";
        System.out.println("searchInsideChat starts");/////////////////////////////////////////////////
        if (a==1){
            try {
                Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, buyer);
                stmt.setString(2, seller);
                ResultSet rs = stmt.executeQuery();
                if(rs.next()){
                    return true;
                }
                
            }catch(SQLException e)  {
                
            }          
        }
        return false;
    }
    public List<String> chatConnectToView(){
        List<String> display = new ArrayList<>();
        String sql = "SELECT chat_name FROM chat WHERE seller = ? OR buyer = ?";
        try(Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)){
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, ManageAccount.Email);
            stmt.setString(2, ManageAccount.Email);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                String s = rs.getString("chat_name");
                display.add(s);  
            }
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException e){
            
        }
        return display;
    }
    public String returnChatContents(String chatName){
        String c = " ";
        String sql = "SELECT chat FROM chat WHERE chat_name = ?";
        try(Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)){
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, chatName);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                c = rs.getString("chat");
            }
            rs.close();
            stmt.close();
            conn.close();            
        }catch(SQLException e){
           
        }
        return c;
    }
    public void addToChat(String newData, String chatName){
        String sql = "UPDATE chat SET chat = ? WHERE chat_name = ?";
        try(Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)){
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, newData);
            stmt.setString(2, chatName);
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                new shortMassages("Massage sent successfully!").setVisible(true);
            }else{
                new shortMassages("Massage not sent").setVisible(true);
            }
         
            stmt.close();
            conn.close(); 
        }catch(SQLException e){
            
        }
    }
}
