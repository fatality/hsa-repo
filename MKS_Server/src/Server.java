/**
 * ########################################################
 * Copyright (c) 2009
 * @author fatality
 * @date 2009-11-05
 * http://www.gironimo.org/
 * 
 * Lizenz: GPL 3
 * ########################################################
 */

/**
 * Diese Klasse startet den Server und übergibt alle notwendigen Variablen ans Management.
 */
public class Server {

	/**
	 * <strong>Main Methode</strong> unserer Simulation. Mit <em>Ice</em> Implementierung.
	 * 
	 * @param args
	 */
	public static void main( String[] args ) {
		int status = 0;
		Ice.Communicator ic = null;
		try {
			ic = Ice.Util.initialize( args );
			Ice.ObjectAdapter adapter = ic.createObjectAdapterWithEndpoints( "SimpleManagementAdapter", "default -p 10003" );
			Ice.Object object = new ManagementI();
			adapter.add( object, ic.stringToIdentity( "SimpleManagement" ) );
			adapter.activate();
			ic.waitForShutdown();
		} catch ( Ice.LocalException e ) {
			e.printStackTrace();
			status = 1;
		} catch ( Exception e ) {
			System.err.println( e.getMessage() );
			status = 1;
		}
		if ( ic != null ) {
			try {
				ic.destroy();
			} catch ( Exception e ) {
				System.err.println( e.getMessage() );
				status = 1;
			}
		}
		System.exit( status );
	}

}
