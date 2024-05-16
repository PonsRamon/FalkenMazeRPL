
# Práctica Documentación

Esta práctica consiste en hacer la documentación de un proyecto llamado FalkenMaze, que trata de hacer laberintos con bloques.

imagen

##Introdución

El proyecto consiste en una cuadrícula vacía y ocho tipos diferentes de bloques con los cuales, seleccionandolos podremos insertarlos en la cuadrícula y formar nuestro laberinto.

##Ejecución

Para ejecutarlo, desde el cmd, introduciremos el comando javafx:run

##Créditos

- Desarrollado por Pedro
- Documentado por Ramón

##Enunciado

- Se tiene una matriz donde se van a colocar una serie de bloques inspirados en el juego Super Mario Bross, los cuales los encontraremos en la izquierda de la ventana.

- Seleccionaremos uno de los bloques y clicando en una de las celdas de la matriz insertaremos el bloque.

- Para eliminar un bloque de la matriz tendremos que clicar en la papelera y a continuación hacer clic en el bloque que queramos eliminar.

##Paquetes

El proyecto está formado por tres paquetes:
1. falkensmaze
2. falkensmaze.components
3. falkensmaze.model

###1. Paquete falkensmaze

Este paquete incluye las clases Principal y Size.

1. Clase principal:
Esta clase extiende de Application e implementa los metodos:
- start(Stage stage):

    public void start(Stage stage) throws Exception {
        BorderPane border = new BorderPane();
        border.setCenter(this.createMaze());
        border.setLeft(this.createBlockMenu());
        border.setTop(this.createMenu());
        this.scene = new Scene(border, this.width + 120, this.height + 50);

        stage.setTitle("Falken's Maze Editor");
        stage.setResizable(false);
        stage.setScene(scene);
        //para que cierre al pulsar el icono
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });

        stage.show();
        this.maze.draw();
    }

Creara la escena del laberinto.

- createBlockMenu():
    private Pane createBlockMenu() {
        BlocksPanel b = new BlocksPanel();
        Block tb;
        String[] nombres = Block.getNamesBlocks();
        for (int i = 0; i < nombres.length; i++) {
            tb = new Block();
            tb.setTipo(nombres[i]);
            b.addBlock(tb);
            tb.addBlocklistener(this.maze);
        }
        b.init();
        return b;
    }

Crea el menú de bloques.

- createMaze():
    private MazeCanvas createMaze() {
        this.maze = new MazeCanvas();
        this.maze.setBoard_size(new Size(this.width, this.height));
        this.maze.setRows(10);
        this.maze.setCols(10);
        // this.maze.setCell_size(new Size(this.width / 10, this.height / 10));
        this.maze.init();

        return this.maze;
    }

Obtiene la matriz para dibujar el laberinto.

- createMenu():

Se crean todos los munús del jeugo.

2. Clase size:
Esta clase implementa Cloneable, Comparable<size>, Serializable. Sus métodos:

- Clone():
    public Object clone() throws CloneNotSupportedException{
        return new Size(this.getWidth(), this.getHeight());
    }

Crea un objeto Size con el alto y el ancho.

- equals(Object o):
Para ver si dos objetos son iguales.

- compareTo():
Compara dos tamaños.

###Paquete falkensmaze.components

Este paquete incluye las clases Block, BlocksPanel, DialogSize, DialogTime, MazeCanvas y la interfaz IBlockListener.

    1. Clase Block:

    2. Clase BlockPanel:

    3. Ckase DialogSize:

    4. Clase DialogTime:

    5. Clase MazeCanvas:

    6. Interfaz IBlockListener:

public interface IBlockListener {
    public void onClicked(Block b);
    public void onDoubleClicked(Block b);
}

Escucha cuando se hace un clic o doble clic a un bloque

3. Paquete falkensmaze.model:
Este paquete esta formado por las clases Block y Maze.

1. Clase Block:

Esta clase implementa Serializable.

2. Clase Maze:

Esta clase implementa Serializable y sus métodos son:

- init():
    public void init() {
        this.setBlocks(new Block[this.getSize().getHeight()][this.getSize().getWidth()]);
        for (int i = 0; i < this.getBlocks().length; i++) {
            for (int j = 0; j < this.getBlocks()[i].length; j++) {
                this.getBlocks()[i][j] = new Block();

            }
        }
    }

Inicia los bloques.

- reset():
    public void reset() {
        for (int i = 0; i < this.getBlocks().length; i++) {
            for (int j = 0; j < this.getBlocks()[i].length; j++) {
                this.getBlocks()[i][j] = null;

            }
        }
        this.setBlocks(null);
    }

Borra los bloques.

- reset(Size newsize):
    public void reset(Size newsize) {
        this.reset();
        this.setSize(newsize);
        this.init();
    }

Cambia el tamaño.









