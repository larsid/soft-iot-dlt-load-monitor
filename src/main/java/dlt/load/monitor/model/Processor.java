package dlt.load.monitor.model;

/**
 *
 * @author Antonio Crispim, Uellington Damasceno
 * @version 0.0.1
 */
public class Processor {

    private final int loadLimit;
    private LedgerConnector connector;

    public Processor(int loadLimit) {
        this.loadLimit = loadLimit;
    }

    public void setConnector(LedgerConnector connector) {
        this.connector = connector;
    }

    protected void updateBrokerStatus(Integer qtdDevices) throws InterruptedException {
        boolean lbEntry = (qtdDevices >= loadLimit);
        System.out.println("Carga: "+ qtdDevices + " Load: "+ lbEntry);
        boolean available = ((qtdDevices + 1) < loadLimit);
        	
        this.connector.put(qtdDevices, lbEntry, qtdDevices.longValue(), qtdDevices.longValue(),available);
    }
}
