import java.net.*;
import java.io.*;
import java.util.Random;

public class MonteCarloPiCliente {
    private static final String ENDERECO_SERVIDOR = "127.0.0.1"; // Trocar pelo IP do servidor
    private static final int PORTA = 9999;
    private static final int NUM_PONTOS = 3000000;

    public static void main(String[] args) {
        try (Socket socket = new Socket(ENDERECO_SERVIDOR, PORTA);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            Random random = new Random();
            int pontosDentroCirculo = 0;
            
            for (int i = 0; i < NUM_PONTOS; i++) {
                double x = random.nextDouble() * 2 - 1;
                double y = random.nextDouble() * 2 - 1;
                if (x * x + y * y <= 1) {
                    pontosDentroCirculo++;
                }
            }

            out.println(pontosDentroCirculo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

