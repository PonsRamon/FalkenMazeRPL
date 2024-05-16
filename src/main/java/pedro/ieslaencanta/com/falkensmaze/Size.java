/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedro.ieslaencanta.com.falkensmaze;

import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 *
 * @author ramponlop
 * @see Cloneable, Comparable, Serializable
 * Clase para gestionar los tamaños
 */
@XmlRootElement
public class Size implements Cloneable, Comparable<Size>, Serializable {
    private int width;
    private int height;
   
    /**
     *Constructor por defecto
     */
    public Size(){
    }

    /**
     *
     * @param width
     * @param height
     * 
     * Constructor sobrecargado
     */
    public Size(int width,int height){
        this.width=width;
        this.height=height;
    }

    /**
     *
     * @return Copia del objeto Size
     * @throws CloneNotSupportedException
     * 
     * Método para copiar un Objeto Size.
     */
    public Object clone() throws CloneNotSupportedException{
        return new Size(this.getWidth(), this.getHeight());
    }

    /**
     *
     * @param o Objeto con el que queremos comparar
     * @return true si son iguales y false si no lo son
     * 
     * Método para comparar dos objetos.
     */
    @Override
    public boolean equals(Object o){
        if(! (o instanceof Size)){
            return false;
        }
        if (this.getWidth()==((Size)(o)).getWidth() && this.getHeight()==((Size)(o)).getHeight()){
            return true;
        }else{
            return false;
        }
        
    }

    /**
     *
     * @param o Objeto Size con el que vamos a comparar
     * @return -1 si es menor, 0 si es igual, o 1 si es mayor.
     * 
     * Método que compara dos objeto Size
     */
    @Override
    public int compareTo(Size o) {
        if(this.getWidth()==o.getWidth() && this.getHeight()==o.getHeight())
            return 0;
        if(this.getWidth()<o.getWidth())
            return -1;
        else
            return 1;
    }

    /**
     *
     * @return Muestra el tamaño y la altura
     * 
     * Método para mostrar los atributos del objeto Size.
     */
    @Override
    public String toString(){
        return "W:"+this.width+" H:"+this.height;
    }
    /**
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * @param width the width to set
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(int height) {
        this.height = height;
    }
 

}
