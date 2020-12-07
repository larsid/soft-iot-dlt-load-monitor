package dlt.load.monitor.model;

import dlt.load.monitor.services.IProcessor;
import java.util.Random;

/**
 *
 * @author Uellington Damasceno
 * @version 0.0.1
 */
public class Processor implements IProcessor<BrokerStatus>{
    
    private BrokerStatus lastValueCalculated;
    
    public Processor(){
        this.lastValueCalculated = new BrokerStatus();
    }
    
    protected void updateBrokerStatus(double cpu, long mem){
         this.lastValueCalculated.setFull(new Random().nextBoolean());
    }
    
    @Override
    public BrokerStatus getLastFitness() {
        return this.lastValueCalculated;
    }    
}
