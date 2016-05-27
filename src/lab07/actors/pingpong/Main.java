package lab07.actors.pingpong;

public class Main {
    public static void main(String[] args) {
        akka.Main.main(new String[] { PingActor.class.getName() });
    }
}
