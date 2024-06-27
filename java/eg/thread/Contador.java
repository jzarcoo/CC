package thread;

public class Contador extends Thread {

    public static final int MIL = 1000;
    private int inicio ;

    public Contador ( int inicio ) {
    	this.inicio = inicio;
    }

    @Override 
	public void run () {
    	int contador = inicio ;
    	while ( contador < MIL ) {
    		System.out.printf( " Contador : ␣ % d \n " , contador );
    		contador += 2;
    		duerme ( MIL );
    	}
    }
    
    public static void duerme ( int milisegundos ) {
    	try {
    		Thread.sleep ( milisegundos );
    	} catch ( InterruptedException ie ) {
    		System.err.println( " ADVERTENCIA : ␣ No ␣ se ␣ pudo ␣ " +
    		" dormir ␣ el ␣ hilo " );
    	}
    }
}
