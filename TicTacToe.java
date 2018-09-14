import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    public static void main(String[] args) {
        Tablero b = new Tablero();
        Random rand = new Random();
        
        b.mostrarTablero();

        System.out.println("Selecciona quien tira primero:\n\n1. Computadora 1 2. Computadora 2 ");
        int turn = b.scan.nextInt();
        if(turn == 1){
            Tiro p = new Tiro(rand.nextInt(3), rand.nextInt(3));
            b.colocarTiro(p, 1);
            b.mostrarTablero();

            while (!b.alguienGano()) {
                
                b.minimax(0, 1);

                b.colocarTiro(b.turnoComputadora, 2); 
                b.mostrarTablero();
                if (b.alguienGano()) break;
                
                b.minimax(0, 1);  
                
                b.colocarTiro(b.turnoComputadora, 1);
                b.mostrarTablero();
            }

        }else{
           Tiro p2 = new Tiro(rand.nextInt(3), rand.nextInt(3));
            b.colocarTiro(p2, 2);
            b.mostrarTablero();

            while (!b.alguienGano()) {
            
                b.minimax(0, 1);

                b.colocarTiro(b.turnoComputadora, 1); 
                b.mostrarTablero();
                if (b.alguienGano()) break;
                
                b.minimax(0, 1);  
                
                b.colocarTiro(b.turnoComputadora, 2);
                b.mostrarTablero();
            } 
        }
        
        

        if (b.ganoX()) 
            System.out.println("Gano 1");
        else if (b.ganoO()) 
            System.out.println("Gano 2"); 
        else 
            System.out.println("Empate");
    }
}