/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package selldatabase;

/**
 *
 * @author ASUS
 */
public abstract class Ad implements Contactable {
    protected String name;
    protected double price;
    protected String color,phone,category;
    public String getCategory() {
        return category;
    }
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getColor() {
        return color;
    }
    public String getPhone() {
        return phone;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setCat(String cat){
        this.category = cat;
    }
    public void setPhone(String phone){
        this.phone = phone;
    }
    public void setColor(String color){
        this.color= color;
    }
    public void setPrice(double price){
        this.price = price;
    }
    public abstract void setBrand(String brand);
    public abstract void setKilometer(double Kilometer);
    public abstract void setAccident(String Accident);
    
    public abstract String getBrand();
    public abstract double getKilometer();
    public abstract String getAccident();
    

    @Override
    public String toString() {
        return String.format("Title: %s\nPrice: $%.2f\nDescription: %s", name, price, color);
    }
}

