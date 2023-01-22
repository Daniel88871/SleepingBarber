public class Barber extends Thread{
    private Sala llocTreball;

    public Barber(String name,Sala llocTreball){
        super(name);
        this.llocTreball = llocTreball;
    }

    public void run(){
        for(;;){
            try{
                llocTreball.barberEsperantClient(getName());
                Thread.sleep(5000);
                llocTreball.fiTall(getName());
                Thread.sleep(5000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}