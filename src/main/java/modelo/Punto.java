/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author juan_d.velez_p
 */
public class Punto {

    public double x;
    public double y;
    
    public Punto() {
    }
    @JsonCreator
    public Punto(@JsonProperty("x") double x,@JsonProperty("y") double y) {
        this.x = x;
        this.y = y;
    }
    

    // Constructor con anotaciones para la deserialización
    
    

    public double getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

}
