package thread;

public class UsoContadorLambda {

    public static final int MIL = 1000;

    public static void duerme ( int milisegundos ) {
        try {
            Thread . sleep ( milisegundos );
        } catch ( InterruptedException ie ) {
            System . err . println ( " ADVERTENCIA : ␣ No ␣ se ␣ pudo ␣ " +
            " dormir ␣ el ␣ hilo " );
        }
    }

    private static void contador ( int inicio ) {
        int contador = inicio ;
        while ( contador < MIL ) {
            System . out . printf ( " Contador : ␣ % d \n " , contador );
            contador += 2;
            duerme ( MIL );
        }
    }
    
    public static void main ( String [] args ) {
        Thread c1 = new Thread (() -> contador (0));
        Thread c2 = new Thread (() -> contador (1));
        c1 . start ();
        duerme ( MIL/2 );
        c2 . start ();
    }
}
