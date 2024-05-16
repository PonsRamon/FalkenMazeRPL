/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedro.ieslaencanta.com.falkensmaze.model;

import com.google.gson.Gson;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Serializable;


import java.io.UnsupportedEncodingException;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import pedro.ieslaencanta.com.falkensmaze.Size;

/**
 *
 * @author ramponlop
 * @see Serializable
 * 
 * Clase que crea el tablero.
 */
@XmlRootElement
public class Maze implements  Serializable{

    private Size size;
    private Block[][] blocks;
    private double time;
    private String sound;

    /**
     *Constructor por defecto
     */
    public Maze() {
    }

    /**
     *Método que genera la matriz del laberinto
     */
    public void init() {
        this.setBlocks(new Block[this.getSize().getHeight()][this.getSize().getWidth()]);
        for (int i = 0; i < this.getBlocks().length; i++) {
            for (int j = 0; j < this.getBlocks()[i].length; j++) {
                this.getBlocks()[i][j] = new Block();

            }
        }
    }

    /**
     *Método que borra la matriz del laberinto
     */
    public void reset() {
        for (int i = 0; i < this.getBlocks().length; i++) {
            for (int j = 0; j < this.getBlocks()[i].length; j++) {
                this.getBlocks()[i][j] = null;

            }
        }
        this.setBlocks(null);
    }

    /**
     *
     * @param newsize El nuevo tamaño
     * 
     * Método para cambiar el tamaño del laberinto.
     */
    public void reset(Size newsize) {
        this.reset();
        this.setSize(newsize);
        this.init();
    }

    /**
     *
     * @param value
     * @param row
     * @param col
     * 
     * setter
     */
    public void setBlockValue(String value, int row, int col) {
        this.getBlocks()[col][row].setValue(value);
    }

    /**
     *
     * @param row
     * @param col
     * @return Devuelve el bloque de la fila y columna indicados.
     * 
     * getter
     */
    public String getBlockValue(int row, int col) {
        return this.getBlocks()[row][col].getValue();
    }

    /**
     *
     * @return Devuelve el tamaño
     */
    public Size getSize() {
        return size;
    }

    /**
     *
     * @param size Tamaño que queremos setear
     * 
     * Setea el tamaño.
     */
    public void setSize(Size size) {
        this.size = size;
    }

    /**
     *
     * @return Devuelve el tiempo
     * 
     * Getter que te devuelve el tiempo
     */
    public double getTime() {
        return time;
    }

    /**
     *
     * @param time
     * 
     * Método para setear el tiempo.
     */
    public void setTime(double time) {
        this.time = time;
    }

    /**
     *
     * @return Devuelve el atributo sonido
     * 
     * Método para recuperar el sonido.
     */
    public String getSound() {
        return sound;
    }

    /**
     *
     * @param sound Sonido que queremos setear
     * 
     * Método para setear el sonido
     */
    public void setSound(String sound) {
        this.sound = sound;
    }

    /**
     *
     * @return Devuelve la matriz.
     * @see Block
     * 
     * Método para recuperar la matriz
     */
    public Block[][] getBlocks() {
        return blocks;
    }

    /**
     *
     * @param blocks La matriz que se quiere guardar
     * 
     * Método para añadir una matriz
     */
    public void setBlocks(Block[][] blocks) {
        this.blocks = blocks;
    }

    /**
     *
     * @param file El archivo de lectura
     * @return Maze Devuelve un objeto de tipo Maze
     * @throws JAXBException
     * @throws IOException
     * @throws FileNotFoundException
     * @throws ClassNotFoundException
     * @throws Exception
     * 
     * Método para cargar los datos de un archivo
     */
    public static Maze load(File file) throws JAXBException, IOException, FileNotFoundException, ClassNotFoundException, Exception {
        String extension = file.getName().substring(file.getName().lastIndexOf(".") + 1);
        Maze m = null;
        if (extension.equals("xml")) {
            m = Maze.loadXML(file);
        } else {
            if (extension.equals("json")) {
                m = Maze.loadJSON(file);

            } else {
                if (extension.equals("bin")) {
                    m = Maze.loadBin(file);
                } else {
                    throw new Exception("Exencsión " + extension + " no permitida");

                }
            }

        }
        return m;

    }

    /**
     *
     * @param maze El laberinto que queremos guardar
     * @param file El archivo donde lo queremos guardar
     * @throws JAXBException
     * @throws Exception
     * 
     * Método para guardar el laberinto en un fichero.
     */
    public static void save(Maze maze, File file) throws JAXBException, Exception {
        if (maze.sound == null || maze.sound.equals("")) {
            throw new Exception("Es necesario indicar el sonido del laberinto");
        }
        String extension = file.getName().substring(file.getName().lastIndexOf(".") + 1);
        if (extension.equals("xml")) {
            Maze.saveXML(maze, file);
        } else {
            if (extension.equals("json")) {
                Maze.saveJSON(maze, file);

            } else {
                if (extension.equals("bin")) {
                    Maze.saveBin(maze, file);
                } else {
                    throw new Exception("Exencsión " + extension + " no permitida");

                }
            }

        }
    }

    private static Maze loadJSON(File file) throws FileNotFoundException, IOException {
        Gson gson = new Gson();
        Reader reader;
        reader = Files.newBufferedReader(file.toPath());
        Maze m = gson.fromJson(reader, Maze.class);
        reader.close();
        return m;
    }

    private static Maze loadXML(File file) throws JAXBException, IOException {

           JAXBContext context = JAXBContext.newInstance(Maze.class);
                Unmarshaller unmarshaller = context.createUnmarshaller();
                Maze maze = (Maze) unmarshaller.unmarshal(
                        file);
                return maze;
          
    }

    /**
     *
     * @param file El archivo de lectura
     * @return Devuelve un objeto de tipo Maze
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ClassNotFoundException
     * 
     * Método para cargar un fichero en binario
     */
    public static Maze loadBin(File file) throws FileNotFoundException, IOException, ClassNotFoundException {
        FileInputStream os = new FileInputStream(file);
        
        ObjectInputStream oos = new ObjectInputStream(os);
        Maze m = (Maze) oos.readObject();
        oos.close();;
        os.close();
        return m;
    }

    private static void saveJSON(Maze maze, File file) throws FileNotFoundException, UnsupportedEncodingException {
        Gson gson = new Gson();
        String json = gson.toJson(maze);
        java.io.PrintWriter pw = new PrintWriter(file, "UTF-8");
        pw.print(json);
        pw.close();
    }

    private static void saveXML(Maze maze, File file) throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(maze.getClass());
        // create an instance of `Marshaller`
        Marshaller marshaller = context.createMarshaller();
        // enable pretty-print XML output
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        // write XML to `StringWriter`
        FileWriter fw = new FileWriter(file, StandardCharsets.UTF_8);//(file, "UTF-8");
        marshaller.marshal(maze, fw);
        fw.close();

    }

    /**
     *
     * @param maze El laberinto que queremos guardar
     * @param file El fichero donde lo queremos guardar
     * @throws FileNotFoundException
     * @throws IOException
     * 
     * Método para guardar en un fichero el binario de un laberinto
     */
    public static void saveBin(Maze maze, File file) throws FileNotFoundException, IOException {
        OutputStream os = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(os);
        oos.writeObject(maze);
        oos.close();;
        os.close();
    }

}
