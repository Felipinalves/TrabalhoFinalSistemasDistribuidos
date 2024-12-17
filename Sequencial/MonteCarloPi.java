import java.util.Random;

public class MonteCarloPi{
    public static void main(String[] args) {
        int numPoints = 3000000; // Número de pontos a serem gerados
        int pointsInsideCircle = 0;

        Random random = new Random();

        // Captura o tempo de início
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < numPoints; i++) {
            double x = random.nextDouble() * 2 - 1; // Coordenada x aleatória entre -1 e 1
            double y = random.nextDouble() * 2 - 1; // Coordenada y aleatória entre -1 e 1

            // Verifica se o ponto está dentro do círculo
            if (x * x + y * y <= 1) {
                pointsInsideCircle++;
            }
        }

        // Captura o tempo de término
        long endTime = System.currentTimeMillis();

        // Calcula Pi
        double piEstimate = 4.0 * pointsInsideCircle / numPoints;
        System.out.println("Estimativa de Pi: " + piEstimate);

        // Calcula e exibe o tempo de execução
        long executionTime = endTime - startTime;
        System.out.println("Tempo de execução (ms): " + executionTime);
    }
}