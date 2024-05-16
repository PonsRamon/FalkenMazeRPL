/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pedro.ieslaencanta.com.falkensmaze.components;

/**
 *
 * @author RamPonLop
 * 
 * Interfaz que escucha cuando se ha hecho un clic en un bloque o doble clic en un bloque
 */
public interface IBlockListener {
    public void onClicked(Block b);
    public void onDoubleClicked(Block b);
}
