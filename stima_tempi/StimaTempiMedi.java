import java.util.Random;

public class StimaTempiMedi {

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

        Random generatoreInteriPseudocasuali = new Random(); // controllare come viene usato il seme (per evitare di

        for (int i = 0; i < n; i++)
            vet[i] = generatoreInteriPseudocasuali.nextInt();
    }

    public static int generatoreInteroLimitatoSuperiorementeCasuale(int n) {
        Random random = new Random();
        return (int) (n * Math.random());
    }

    public static void main(String[] args) {

        int nMin = 100;
        int nMax = 1000000;

        int vettorePseudoCasuale[] = new int[nMax]; // alloco qui una volta per tutte

        for (int j = 0; j < 100; j++) { // Numero di campioni/iterazioni (punti sul grafico)

            double A = nMin;
            double B = Math.exp((Math.log(nMax) - Math.log(nMin)) / 99);

            int n = (int) (nMin * Math.pow(B, j)); // Distribuzione esponenziale
            // Perché ho bisogno di fare casting?

            double x = 0;
            double y = 0; // Deviazione std
            int d; // Deviazione standard

            double risoluzioneClock = ottieniRisoluzioneClock();
            double erroreRelativoMassimoAmmesso = 0.01;
            double tempoMedio = -1; // Solo per inizializzarlo
            int interoCasuale = generatoreInteroLimitatoSuperiorementeCasuale(n);

            for (d = 0; d < 10; d++) {

                double start = System.nanoTime();
                double end;
                int contatoreIterazioni = 0;

                do {
                    popolaVettore(vettorePseudoCasuale, n);
                    QuickSelect.quickSelect(vettorePseudoCasuale, interoCasuale, 0, n - 1);
                    end = System.nanoTime();
                    contatoreIterazioni++;
                } while ((end - start) < (risoluzioneClock / erroreRelativoMassimoAmmesso) + risoluzioneClock);

                tempoMedio = (end - start) / contatoreIterazioni;
                x = x + Math.pow(tempoMedio, 2.0);
                y = y + tempoMedio;

            }

            double deviazioneStandard = x / d - Math.pow(y / d, 2.0);
            // System.out.println(vettorePseudoCasuale.length + " " + tempoMedio + " " +
            // deviazioneStandard);
            System.out.println(n + " " + tempoMedio);
        }

    }

}