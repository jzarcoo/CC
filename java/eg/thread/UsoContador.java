package thread;

public class UsoContador {
    public static void main ( String [] args ) {
        Contador c1 = new Contador (0);
        Contador c2 = new Contador (1);
        c1 . start ();
        Contador . duerme ( Contador . MIL /2);
        c2 . start ();
    }
}