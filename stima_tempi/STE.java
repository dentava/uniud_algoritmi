import java.util.Random;

public class STE {

    public static double ottieniRisoluzioneClock() {

        double risoluzione = Long.MAX_VALUE;

        for (int i = 0; i < 40; i++) {
            double t0 = System.nanoTime();
            double t1 = System.nanoTime();
            while (t0 == t1) {
                t1 = System.nanoTime();
            }
            risoluzione = Math.min(risoluzione, t1 - t0);
        }
        return risoluzione;
    }

    public static void popolaVettore(int[] vet, int n) {

        Random generatoreInteriPseudocasuali = new Random(); // controllare come viene usato il seme

        for (int i = 0; i < n; i++)
            vet[i] = generatoreInteriPseudocasuali.nextInt();
    }

    public static int generatoreInteroLimitatoSuperiorementeCasuale(int n) {
        return (int) (n * Math.random());
    }

    public static void main(String[] args) {

        int nMin = 100;
        int nMax = 1000000;

        int vettorePseudoCasuale[] = new int[nMax]; // alloco qui una volta per tutte

        double A = nMin;
        double B = Math.exp((Math.log(nMax) - Math.log(A)) / 99);

        for (int j = 0; j < 1; j++) { // Numero di campioni/iterazioni (punti sul grafico)

            int n = (int) (A * Math.pow(B, j)); // Distribuzione esponenziale
            // Perché ho bisogno di fare casting?

            double x = 0;
            double y = 0; // Deviazione std
            int d; // Deviazione standard

            double risoluzioneClock = ottieniRisoluzioneClock();
            double erroreRelativoMassimoAmmesso = 0.01;
            double tempoMedio = -1; // Solo per inizializzarlo
            int interoCasuale = generatoreInteroLimitatoSuperiorementeCasuale(n);

            double start = System.nanoTime();
            double end;
            int contatoreIterazioni = 0;

            popolaVettore(vettorePseudoCasuale, n);

            for (int g = 0; g < vettorePseudoCasuale.length; g++)
                System.out.print(vettorePseudoCasuale[g] + " ");
            System.out.println();
            System.out.println(interoCasuale + " " + n);

        }

    }
}