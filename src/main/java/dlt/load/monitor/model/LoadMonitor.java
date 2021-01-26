package dlt.load.monitor.model;

//import com.sun.management.OperatingSystemMXBean;
import java.util.Random;

/**
 *
 * @author Uellington Damasceno
 */
public class LoadMonitor implements Runnable {

    private Processor processor;
    private int samplingInterval, sampling;
    
    public LoadMonitor(int samplingInterval, int sampling) {
        this.samplingInterval = samplingInterval;
        this.sampling = sampling;
    }

    public void setProcessor(Processor processor) {
        this.processor = processor;
        double cpu = new Random().nextDouble();
        long mem = new Random().nextLong();
        this.processor.updateBrokerStatus(cpu, mem);
    }

    public void start() {
    }

    public void stop() {
    }

    @Override
    public void run() {
        double cpu = new Random().nextDouble();
        long mem = new Random().nextLong();
        this.processor.updateBrokerStatus(cpu, mem);
    }
}
