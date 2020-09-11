package dlt.load.monitor.model;

import dlt.load.monitor.services.IProcessor;

/**
 *
 * @author Uellington Damasceno
 * @version 0.0.1
 */
public class Processor implements IProcessor<BrokerStatus>{
    
    private BrokerStatus lastValueCalculated;
    
    
    
    @Override
    public BrokerStatus getLastFitness() {
        return this.lastValueCalculated;
    }    
}
