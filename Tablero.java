import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

class Tablero {
 
    List<Tiro> tirosDisponibles;
    Scanner scan = new Scanner(System.in);
    int[][] tablero = new int[3][3];

    public Tablero() {
    }

    public List<Tiro> lugaresDisponibles() {
        tirosDisponibles = new ArrayList<>();
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (tablero[i][j] == 0) {
                    tirosDisponibles.add(new Tiro(i, j));
                }
            }
        }
        return tirosDisponibles;
    }

    public void colocarTiro(Tiro Tiro, int player) {
        tablero[Tiro.x][Tiro.y] = player;
    } 

    Tiro turnoComputadora; 
    
    public int minimax(int depth, int turn) {  
        if (ganoX()) return +1; 
        if (ganoO()) return -1;

        List<Tiro> TirosAvailable = lugaresDisponibles();
        if (TirosAvailable.isEmpty()) return 0; 
 
        int min = Integer.MAX_VALUE; 
        int max = Integer.MIN_VALUE;
         
        for (int i = 0; i < TirosAvailable.size(); ++i) { 
            Tiro Tiro = TirosAvailable.get(i);   
            if (turn == 1) { 
                colocarTiro(Tiro, 1); 
                int currentpuntos = minimax(depth + 1, 2);
                max = Math.max(currentpuntos, max);
                
                if(depth == 0)System.out.println("Marcador "+(i+1)+" = "+currentpuntos);
                if(currentpuntos >= 0){ if(depth == 0) turnoComputadora = Tiro;} 
                if(currentpuntos == 1){tablero[Tiro.x][Tiro.y] = 0; break;} 
                if(i == TirosAvailable.size()-1 && max < 0){if(depth == 0)turnoComputadora = Tiro;}
            } else if (turn == 2) {
                colocarTiro(Tiro, 2); 
                int currentpuntos = minimax(depth + 1, 1);
                min = Math.min(currentpuntos, min); 
                if(min == -1){tablero[Tiro.x][Tiro.y] = 0; break;}
            }
            tablero[Tiro.x][Tiro.y] = 0; 
        } 
        return turn == 1?max:min;
    }

    public boolean alguienGano() {
        return (ganoX() || ganoO() || lugaresDisponibles().isEmpty());
    }

    public boolean ganoX() {
        if ((tablero[0][0] == tablero[1][1] && tablero[0][0] == tablero[2][2] && tablero[0][0] == 1) || (tablero[0][2] == tablero[1][1] && tablero[0][2] == tablero[2][0] && tablero[0][2] == 1)) {
            return true;
        }
        for (int i = 0; i < 3; ++i) {
            if (((tablero[i][0] == tablero[i][1] && tablero[i][0] == tablero[i][2] && tablero[i][0] == 1)
                    || (tablero[0][i] == tablero[1][i] && tablero[0][i] == tablero[2][i] && tablero[0][i] == 1))) {
                return true;
            }
        }
        return false;
    }

    public boolean ganoO() {
        if ((tablero[0][0] == tablero[1][1] && tablero[0][0] == tablero[2][2] && tablero[0][0] == 2) || (tablero[0][2] == tablero[1][1] && tablero[0][2] == tablero[2][0] && tablero[0][2] == 2)) {
            return true;
        }
        for (int i = 0; i < 3; ++i) {
            if ((tablero[i][0] == tablero[i][1] && tablero[i][0] == tablero[i][2] && tablero[i][0] == 2)
                    || (tablero[0][i] == tablero[1][i] && tablero[0][i] == tablero[2][i] && tablero[0][i] == 2)) {
                return true;
            }
        }

        return false;
    }

    public void mostrarTablero() {
        System.out.println();

        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                System.out.print(tablero[i][j] + " ");
            }
            System.out.println();

        }
    }   
}