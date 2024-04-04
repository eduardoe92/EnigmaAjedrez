package enigmaAjedrez;

/*
Un muchacho se entretiene pintando su tablero de ajedrez. 
Planea cubrir cada espacio por completo en tonos rojos o azules. 
Para darle un toque personal, quiere contar con igual cantidad de espacios rojos y azules, 
evitando a su vez que dos filas o columnas presenten el mismo número de espacios rojos. 
¿Podría pintar el tablero siguiendo estos criterios? ¿Qué pasaría si, en lugar de un tablero 
de ajedrez tradicional de 8x8, tuviera uno enorme de 1000x1000?
 */


public class TableroAjedrez {

    public static void main(String[] args) {
        int n = 8; // Tamaño del tablero de ajedrez (n x n) 
        char[][] tablero = new char[n][n]; // Representación del tablero con 'R' para rojo y 'A' para azul

        if (pintarTablero(tablero, 0, 0, 0, 0)) {
            System.out.println("Tablero pintado correctamente:");
            for (char[] fila : tablero) {
                for (char casilla : fila) {
                    System.out.print("[ " + casilla + " ] ");
                }
                System.out.println();
            }
        } else {
            System.out.println("No se pudo pintar el tablero con los criterios dados.");
        }
    }

    public static boolean pintarTablero(char[][] tablero, int fila, int columna, int rojosPintados, int azulesPintados) {
        int n = tablero.length;

        if (fila == n) {
            return true;
        }

        char color = ((fila + columna) % 2 == 0) ? 'R' : 'A';

        if ((color == 'R' && rojosPintados < n * n / 2) || (color == 'A' && azulesPintados < n * n / 2)) {
            if (esMovimientoValido(tablero, fila, columna, color)) {
                tablero[fila][columna] = color;

                int siguienteRojos = rojosPintados + (color == 'R' ? 1 : 0);
                int siguienteAzules = azulesPintados + (color == 'A' ? 1 : 0);

                int siguienteFila = fila;
                int siguienteColumna = columna + 1;
                if (siguienteColumna == n) {
                    siguienteFila++;
                    siguienteColumna = 0;
                }

                if (pintarTablero(tablero, siguienteFila, siguienteColumna, siguienteRojos, siguienteAzules)) {
                    return true;
                }

                tablero[fila][columna] = '\0';
            }
        }

        return false;
    }

    public static boolean esMovimientoValido(char[][] tablero, int fila, int columna, char color) {
        int n = tablero.length;

        // Verificar que la fila no tenga el mismo número de espacios del mismo color
        int conteoColorFila = 0;
        for (int i = 0; i < n; i++) {
            if (tablero[fila][i] == color) {
                conteoColorFila++;
            }
        }
        if (conteoColorFila == n / 2) {
            return false;
        }

        // Verificar que la columna no tenga el mismo número de espacios del mismo color
        int conteoColorColumna = 0;
        for (int i = 0; i < n; i++) {
            if (tablero[i][columna] == color) {
                conteoColorColumna++;
            }
        }
        if (conteoColorColumna == n / 2) {
            return false;
        }

        return true;
    }
}
