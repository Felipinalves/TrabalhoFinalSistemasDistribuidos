import java.net.*;
import java.io.*;
import java.util.concurrent.atomic.AtomicInteger;

public class MonteCarloPiServidor {
    private static final int PORTA = 9999;
    private static AtomicInteger totalPontosDC = new AtomicInteger(0);
    private static AtomicInteger totalPontos = new AtomicInteger(0);

    public static void main(String[] args) {
        try (ServerSocket servidorSocket = new ServerSocket(PORTA)) {
            System.out.println("Servidor escutando na porta " + PORTA);
            ///Quantidade acionada para acionar o código
            int numTrabalhadores = 1;
            int numPontosPT = 3000000;
            //Este fora o melhor jeito para acionar o código de maneira eficiente, outras formas o código não funcionava
            for (int i = 0; i < numTrabalhadores; i++) {
                Socket clienteSocket = servidorSocket.accept();
                System.out.println("Conexão aceita de " + clienteSocket.getInetAddress());
                new Thread(new ManipuladorTrabalhador(clienteSocket, numPontosPT)).start();
                totalPontos.addAndGet(numPontosPT);
            }
            
            // Aguardando todas as threads terminarem
            Thread.sleep(5000); // Esta é uma maneira simples de esperar pelos clientes

            double piEstimado = 4.0 * totalPontosDC.get() / totalPontos.get();
            System.out.println("Valor estimado de Pi: " + piEstimado);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static class ManipuladorTrabalhador implements Runnable {
        private Socket clienteSocket;
        private int numPontos;

        public ManipuladorTrabalhador(Socket clienteSocket, int numPontos) {
            this.clienteSocket = clienteSocket;
            this.numPontos = numPontos;
        }

        @Override
        public void run() {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()))) {
                int pontosDC = Integer.parseInt(in.readLine());
                totalPontosDC.addAndGet(pontosDC);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

