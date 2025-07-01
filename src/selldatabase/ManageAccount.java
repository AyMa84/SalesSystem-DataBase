/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package selldatabase;


public class ManageAccount {
    
    public static String Email;
    private static String Pass;
    private static String RealPass;
    public static String name;
    public static String lastname;
    public static int counter;
    public ManageAccount(String Pass){
        this.Pass = Pass;
        
    }
    public ManageAccount(){
        
    }
    public void createAccount(){
        Connections ob = new Connections(Email, Pass, name, lastname);
    }
    public boolean checkIfAccountExists(String email){//email
        Connections ob = new Connections();
        if (ob.CheckIfExists(email, "userAccountInfo", "user_email")){
            return true;
        }
        return false;
    }
    
    public boolean checkPasswordMatch(){
        ////first find the real email here
        Connections ob = new Connections();
        RealPass = ob.getRealPass(Email);
        if(Pass.equalsIgnoreCase(RealPass)){
            return true;
        }
        return false;
    }
}
