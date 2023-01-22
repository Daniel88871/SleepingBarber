public class Sala {
    private int cadires;
    private int cadiresOcupades = 0;
    private boolean barberCortant = false;
    private boolean fiTallPel = false;
    private boolean barberDormint = false;

    public Sala(int cadires) {
        this.cadires = cadires;
    }

    public synchronized boolean entra(int clientNum)
            throws InterruptedException {
        if (cadiresOcupades == cadires) {
            System.out.println("El client " + clientNum + " se'n va sense tallar-se el pel");
            return false;
        } else {
            cadiresOcupades++;
            System.out.println("El client " + clientNum + " es seu en la cadira");
            while (barberCortant) {
                wait();
            }

            cadiresOcupades--;

            barberCortant = true;
            fiTallPel = false;

            if (barberDormint) {
                System.out.println("El client " + clientNum + " desperta al barber");
                notifyAll();
            }

            System.out.println("El client " + clientNum + " es posa a la cadira del barber");
            while (!fiTallPel) {
                wait();
            }

            barberCortant = false;

            notifyAll();

            System.out.println("El client " + clientNum + " se'n va amb el pel tallat");
            return true;
        }
    }

    public synchronized void barberEsperantClient(String nomBarber) throws InterruptedException {
        barberDormint = true;
        while (!barberCortant) {
            System.out.println(nomBarber + " esperant client");
            wait();
        }
        barberDormint = false;
        System.out.println(nomBarber + " cortant el pel");
    }

    public synchronized void fiTall(String nomBarber) {
        fiTallPel = true;
        System.out.println(nomBarber + " acaba de tallar el pel");
        notifyAll();
    }
}