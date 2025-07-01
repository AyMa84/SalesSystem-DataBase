/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package selldatabase;

/**
 *
 * @author ASUS
 */
public class VehicleAd extends Ad {
    private static double kilometer2;
    private static String brand2,accident2;
    @Override
    public String getBrand(){
        return brand2;
    }
    @Override
    public double getKilometer(){
        return kilometer2;
    }
    @Override
    public String getAccident(){
        return accident2;
    }
    public void setKilometer(double kilo){
        this.kilometer2 = kilo;
    }
    @Override
    public void setBrand(String brand){
        this.brand2 = brand;
    }
    @Override
    public void setAccident(String accident){
        this.accident2 =accident;
    }


    @Override
    public void contactSeller() {
        System.out.println("Contacting seller of vehicle: " + name);
    }
}

