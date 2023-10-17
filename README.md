``` mermaid
classDiagram
    Tetris <|-- Tablero
    Tetris <|-- Juego
    Tablero <|-- Juego
    Juego <|--Jugador
    Tablero <|--GUI
    GUI <|--Jugador


    Tetris: +int posicionActual
    class Tetris{
        void moverseIoD()
        void rotar()
        void caer()
    }

    Tablero: +Array[][] tablero
    class Tablero{
        bool comprobarColision()
        void limpiarFilas()
        void actualizarPuntuacion()
    }

    Juego: +Tablero tablero
    Juego: +Tetris actual
    class Juego{
        void iniciarJuego()
        void finalizarJuego()
    }

    Jugador: +String nombre
    Jugador: +int puntuacion
    class Jugador{
        void registrarPuntacion()
        void setNombre()
        
    }

    class GUI{
        void dibujarTablero()
        int mostrarPuntuacion()
    }
```
