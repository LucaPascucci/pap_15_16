/**
 * PAP 2015-2016 - Assignment 06 - Es. 1 - SynchMonitor
 */
package ass06.syncsum;

public class SyncSum {
	public static void main(String[] args) {
		SyncAdder adder = null; // TO BE IMPLEMENTED

		new DataProducerA(adder).start();
		new DataProducerB(adder).start();
		new DataConsumer(adder).start();
	}

}
