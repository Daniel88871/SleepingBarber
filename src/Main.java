public class Main {
    public static void main(String[] args) {

        int cadires = 5;
        int clients = 10;

        Sala llocTreball = new Sala(cadires);
        Barber barber = new Barber("Wilson",llocTreball);
        Client[] clientsSala = new Client[clients];
        barber.start();

        for (int i = 0; i < clientsSala.length; i++) {
            clientsSala[i] = new Client(llocTreball,i);
            clientsSala[i].start();
        }
    }
}