/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedro.ieslaencanta.com.falkensmaze.model;

import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 *
 * @author ramponlop
 * @see Serializable
 * 
 * Clase para los Bloques.
 */
@XmlRootElement
public class Block implements  Serializable {
    private String value;
    
    /**
     *Constructor por defecto
     */
    public Block(){
        this.value=null;
    }

    /**
     *
     * @return Devuelve el atributo value.
     */
    public String getValue(){
        return this.value;
    }

    /**
     *
     * @param value String que se guardará en this.value
     */
    public void setValue(String value){
        this.value=value;
    }

    /**
     *
     * @return Devuelve true si está null o false si no es null
     */
    public boolean isEmpty(){
        return this.value==null;
    }
}
