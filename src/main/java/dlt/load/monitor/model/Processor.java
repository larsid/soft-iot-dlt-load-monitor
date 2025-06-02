package dlt.load.monitor.model;

import java.util.logging.Logger;

/**
 *
 * @author Allan Capistrano, Antonio Crispim, Uellington Damasceno
 * @version 0.0.2
 */
public class Processor {

    private final int loadLimit;
    private LedgerConnector connector;
    private long lastSentLbEntry;
    private long lbEntryTimeout;

    private static final Logger logger = Logger.getLogger(
            Processor.class.getName()
    );

    public Processor(int loadLimit) {
        this.loadLimit = loadLimit;
    }

    protected void updateBrokerStatus(Integer qtdDevices) throws InterruptedException {
        boolean available = qtdDevices < loadLimit;
        this.connector.put(
                qtdDevices,
                false,
                qtdDevices.longValue(),
                qtdDevices.longValue(),
                available
        );
    }

    public void setConnector(LedgerConnector connector) {
        this.connector = connector;
    }

    public void setLastSentLbEntry(long lastSentLbEntry) {
        this.lastSentLbEntry = lastSentLbEntry;
    }

    public void setLbEntryTimeout(long lbEntryTimeout) {
        this.lbEntryTimeout = lbEntryTimeout;
    }
}
