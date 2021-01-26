package dlt.load.monitor.model;

import dlt.load.monitor.services.IProcessor;
import java.util.Random;

/**
 *
 * @author Uellington Damasceno
 * @version 0.0.1
 */
public class Processor implements IProcessor<BrokerStatus> {

    private BrokerStatus lastValueCalculated;
    private LedgerConnector connector;
    private int loadLimit;

    public Processor(int loadLimit) {
        this.lastValueCalculated = new BrokerStatus();
        this.loadLimit = loadLimit;
    }

    public void setConnector(LedgerConnector connector){
        this.connector = connector;
    }
    
    protected void updateBrokerStatus(double cpu, long mem) {
        this.lastValueCalculated.setFull(new Random().nextBoolean());
    }

    @Override
    public BrokerStatus getLastFitness() {
        this.updateBrokerStatus(0, 0);
        return this.lastValueCalculated;
    }
}
