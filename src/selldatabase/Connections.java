/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package selldatabase;
import java.util.List;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Connections {
    private static final String URL = "jdbc:mysql://localhost:3306/account";
    private static final String USER = "root";
    private static final String PASSWORD = "ay84lin13";
    public String URLgetter(){
        return URL;
    }
    public String USERgetter(){
        return USER;
    }
    public String PASSWORDgetter(){
        return PASSWORD;
    }    
    
    public Connections(String Email,String  Pass,String name,String lastname){//calls account create
        try{
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            String sql = "INSERT INTO userAccountInfo (user_email, user_password, user_name, user_lastname) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, Email);
            stmt.setString(2, Pass);
            stmt.setString(3, name);
            stmt.setString(4, lastname);
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("User added successfully!");
            }
            stmt.close();
            conn.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public Connections(){
        
    }
    public void insert(String tableName){
        try{
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            String sql = "INSERT INTO "+ tableName + "(email, counter) VALUES (?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, ManageAccount.Email);
            stmt.setInt(2, ++ManageAccount.counter);
            System.out.printf("counter is"+ManageAccount.counter);/////////////////////
            System.out.println("insertdone");
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("insert data added successfully!");
            }
            stmt.close();
            conn.close();
        }catch(SQLException e){
            System.out.println("insert failed");
        }
    }
    public void AddStringToDataBase(String table, String query, String toBeAdded, int a){
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            String sql = "UPDATE " + table + " SET " + query + " = ? WHERE email = ? AND counter = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            if(a == 1){
                System.out.println("add string a = 1");///////////////////////////////////
                stmt.setString(1, toBeAdded);
                stmt.setString(2, ManageAccount.Email);
                stmt.setInt(3, ManageAccount.counter);
                System.out.printf("counter is"+ManageAccount.counter);/////////////////////
                int rowsInserted = stmt.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("data added successfully!");
                }
                stmt.close();
                conn.close();                
            }else{
                System.out.println("add string a = 2");///////////////////////////////////
                stmt.setString(1,toBeAdded);
                stmt.setString(2,ManageAccount.Email);
                stmt.setInt(3,getRealCounter(Edit.name2));
                int rowsInserted = stmt.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("data added successfully!");
                }
            }
            
        }catch(SQLException e){
            System.out.println("failed");
        }
    }
    public void AddDecimalToDataBase(String table, String query, double toBeAdded,int a){
        try {
            
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            String sql = "UPDATE " + table + " SET " + query + " = ? WHERE email = ? AND counter = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            if(a==1){
               System.out.println("add decimal a = 1");///////////////////////////////////
               stmt.setDouble(1, toBeAdded);
               stmt.setString(2, ManageAccount.Email);
               stmt.setInt(3, ManageAccount.counter);
               int rowsInserted = stmt.executeUpdate();
               if (rowsInserted > 0) {
                    System.out.println("data added successfully!");
               }
               stmt.close();
               conn.close();               
            }else{
               System.out.println("add decimal a = 1");///////////////////////////////////
               stmt.setDouble(1, toBeAdded);
               stmt.setString(2, ManageAccount.Email);
               stmt.setInt(3, getRealCounter(Edit.name2));
               int rowsInserted = stmt.executeUpdate();
               if (rowsInserted > 0) {
                    System.out.println("data added successfully!");
               }
               stmt.close();
               conn.close(); 
            }

        }catch(SQLException e){
            System.out.println("failed");
        }        
    }
    public boolean CheckIfExists(String toExist, String query, String par){
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            String sql = "SELECT * FROM " + query + " WHERE "+ par + " = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, toExist);
            ResultSet rs = stmt.executeQuery();
            if (!rs.next()) {
                return false;
            }
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    public String getRealPass(String email){
       String pass = "s"; 
       while (true){
                  try { 
                      Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                      String sql = "SELECT user_password FROM userAccountInfo WHERE user_email = ?";/////////////////                      
                      PreparedStatement stmt = conn.prepareStatement(sql);
                      stmt.setString(1, email);
                      try (ResultSet rs = stmt.executeQuery()) {
                            if (rs.next()) {
                              pass = rs.getString("user_password");                             
                            } else {
                              System.out.println("No user found with this email.");
                            }
                     }catch (Exception e) {
                            e.printStackTrace();
                     }
                      stmt.close();
                      conn.close();
                      break;
                  }catch(SQLException e){
                      e.printStackTrace();
                  }
       }
       System.out.println("get realpass is over");////////////
       return pass;
    }
    public static int getCounter(){//when user entres
        int counter; 
        while(true){
            try{
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            String sql = "SELECT counter FROM useraccountinfo WHERE user_email = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, ManageAccount.Email);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                counter = rs.getInt("counter");
                rs.close();
                stmt.close();
                conn.close();
                System.out.println("success");///////////////////;
                break;
            }else{
                rs.close();
                stmt.close();
                conn.close();
                System.out.println("no record match");
                System.exit(1);
            }
               
            }catch(SQLException e){
                e.printStackTrace();
                System.out.println("exception happened");//////////////////
                System.exit(1);
            }
        }
        return counter;
    }
    public static void updateCounter(){//after creating and deleting an announcement
        try{
     
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
           
            String sql = "UPDATE useraccountinfo SET counter = ? WHERE user_email = ?";
           
            PreparedStatement stmt = conn.prepareStatement(sql);
          
            stmt.setInt(1, ManageAccount.counter);
            
            stmt.setString(2, ManageAccount.Email);
           
            int rowsInserted = stmt.executeUpdate();
           
            if (rowsInserted > 0) {
                System.out.println("data added successfully!");
            }
            
            stmt.close();
            
            conn.close();
            System.out.println("aseraccountinfo updated");/////////////////////
        }catch(SQLException e){
            
        }
    }
    public List<String>  searchInsideAds(String category, int Max, int Min){
       System.out.println("search inside ads is read");
        List<String> display = new ArrayList<>();
        
        String sql = "SELECT Ad_name FROM ads WHERE category = ? AND price BETWEEN ? AND ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, category);
            stmt.setDouble(2, Min);
            stmt.setDouble(3, Max);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                String name = rs.getString("Ad_name");
                String sql1 = "SELECT email FROM ads WHERE category = ? AND Ad_name = ?";
                try(Connection conn1 = DriverManager.getConnection(URL, USER, PASSWORD); PreparedStatement stmt1 = conn1.prepareStatement(sql1)){
                    
                    stmt1.setString(1, category);
                    
                    stmt1.setString(2, name);
                   
                    ResultSet rs1 = stmt1.executeQuery();
                    if (rs1.next()){
                   
                        String email = rs1.getString("email");
                     
                        VIEW.ownersEmails.add(email);
               
                    }
                    rs1.close();
                    stmt1.close();
                    conn1.close();
                }
                catch(SQLException e){
                    e.printStackTrace();
                    System.out.println("error in adds email finding");
                }
                display.add(name);
            }
        }catch(SQLException e){
            System.out.println("error in adds");
        }
        return display;
    }
    public void connectToView(String email,String name, String toBeChosen, int a){
        
        String sql = "SELECT "+toBeChosen+" FROM ads WHERE Ad_name = ? AND email = ?";
   
       
        try(Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)){
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, name);
            
            stmt.setString(2, email);
            
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()){
               
                if (toBeChosen.equals("category")){
                   
                    String s = rs.getString(toBeChosen);
                   
                    if(a == 1){
                        Detailed.category2 = s;
                        
                    }else{
                        Edit.category2 = s;
                    }
                }else if(toBeChosen.equals("price")){
                    
                    double d = rs.getDouble(toBeChosen);
                    
                    if(a == 1){
                        Detailed.price2 = d;
                        
                    }else{
                        Edit.price2 = d;
                    }
                    
                }else if (toBeChosen.equals("phone_number")){
                    
                    String s = rs.getString(toBeChosen);
                    
                    if(a ==1){
                        Detailed.phone2 = s;
                        
                    }else{
                        Edit.phone2 = s;
                    }
                    
                }else if(toBeChosen.equals("color")){
                   
                    String s = rs.getString(toBeChosen);
                   
                    if(a==1){
                        Detailed.color2 = s;
                       
                    }else{
                        Edit.color2 = s;
                    }
                }
                else if(toBeChosen.equals("brand_model")){
                   
                    String s = rs.getString(toBeChosen);
                    
                    if(s != null ){
                        if (a==1){
                            Detailed.brand2 = s;
                           
                        }else{
                            Edit.brand2 = s;
                        }
                    }
                }else if(toBeChosen.equals("Kilometer")){
                    
                    double d = rs.getDouble(toBeChosen);
                    
                   
                    if(d != 0 ){
                        if(a==1){
                           Detailed.kilometer2 = d;
                          
                        }else{
                            Edit.kilometer2 = d;
                        }
                    }
                }else if(toBeChosen.equals("has_accident")){
                    
                    String s = rs.getString(toBeChosen);
                   
                    if(s != null ){
                        if(a==1){
                            Detailed.accident2 = s;
                            
                        }else{
                            Edit.accident2 = s;
                        }
                    }
                }
            }
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException e){
            new shortMassages("sorry! an error has occured connecting to database").setVisible(true);
        }
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
    public List<String> findPersonalAds(String email){
        List<String> pa = new ArrayList<>();
        String sql = "SELECT Ad_name FROM ads WHERE email = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                String name = rs.getString("Ad_name"); 
                pa.add(name);
            }
            rs.close();
            stmt.close();
            conn.close(); 
        }catch(SQLException e){
            new shortMassages("No add found").setVisible(true);
        }
        return pa;
    }
    public int getRealCounter(String name){
        int counter = 0;
        String sql = "SELECT counter FROM ads WHERE email = ? AND Ad_name = ?";
        try(Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)){
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,ManageAccount.Email);
            stmt.setString(2, name);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                counter = rs.getInt("counter"); 
            }
            rs.close();
            stmt.close();
            conn.close(); 
        }catch(SQLException e){
            
        }
        return counter;
    }
    public void Delete(String name){
        String sql = "DELETE FROM ads WHERE email = ? AND Ad_name = ?";
        try(Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)){
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,ManageAccount.Email);
            stmt.setString(2, name);
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                new shortMassages("Ad deletet successfully!").setVisible(true);
            }else{
                new shortMassages("Ad not deleted").setVisible(true);
            }
         
            stmt.close();
            conn.close(); 
        }catch(SQLException e){
            new shortMassages("Ad not deleted").setVisible(true);
        }
    }
    public void getAndSet(String email,String name, String toBeChosen){
        Ad ob = new VehicleAd();
        String sql = "SELECT "+toBeChosen+" FROM ads WHERE Ad_name = ? AND email = ?";
   
       
        try(Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)){
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, name);
            
            stmt.setString(2, email);
            
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()){
               
                if (toBeChosen.equals("category")){
                   
                    String s = rs.getString(toBeChosen);
                    ob.setCat(s);
                }else if(toBeChosen.equals("price")){
                    
                    double d = rs.getDouble(toBeChosen);
                    ob.setPrice(d);
                    
                }else if (toBeChosen.equals("phone_number")){
                    
                    String s = rs.getString(toBeChosen);
                    ob.setPhone(s);
                    
                }else if(toBeChosen.equals("color")){
                   
                    String s = rs.getString(toBeChosen);
                    ob.setColor(s);
                }
                else if(toBeChosen.equals("brand_model")){
                   
                    String s = rs.getString(toBeChosen);
                    
                    if(s != null ){
                       ob.setBrand(s);
                    }
                }else if(toBeChosen.equals("Kilometer")){
                    
                    double d = rs.getDouble(toBeChosen);
                    
                   
                    if(d != 0 ){
                        ob.setKilometer(d);
                    }
                }else if(toBeChosen.equals("has_accident")){
                    
                    String s = rs.getString(toBeChosen);
                   
                    if(s != null ){
                       ob.setAccident(s);
                    }
                }
            }
            
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException e){
            new shortMassages("sorry! an error has occured connecting to database").setVisible(true);
        }        
    }
}
