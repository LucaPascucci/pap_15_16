package lab07.actors.ex;


public class Main {

    public static void main(String[] args) {
        akka.Main.main(new String[] { BootActor.class.getName() });
    }
}
