package dlt.load.monitor.model;

/**
 *
 * @author Uellington Damasceno
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
        this.connector.put(qtdDevices, lbEntry);
    }
}
