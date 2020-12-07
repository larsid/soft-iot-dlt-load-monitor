package dlt.load.monitor.model;

//import com.sun.management.OperatingSystemMXBean;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Uellington Damasceno
 */
public class LoadMonitor implements Runnable {

    private final ScheduledExecutorService executor;
//    private OperatingSystemMXBean so;
    private Processor processor;

    public LoadMonitor() {
        this.executor = Executors.newSingleThreadScheduledExecutor();
//        this.so = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
    }

    public void setProcessor(Processor processor) {
        this.processor = processor;
        double cpu = new Random().nextDouble();
        long mem = new Random().nextLong();
        this.processor.updateBrokerStatus(cpu, mem);
    }

    public void start() {
//        this.executor.scheduleAtFixedRate(this, 0, 5, TimeUnit.SECONDS);
    }

    public void stop() {
        if (this.executor != null) {
            this.executor.shutdown();
            try {
                this.executor.awaitTermination(3, TimeUnit.SECONDS);
            } catch (InterruptedException ex) {
                this.executor.shutdownNow();
            }
        }
    }

    @Override
    public void run() {
//        double cpu = this.so.getSystemCpuLoad();
//        long mem = this.so.getFreePhysicalMemorySize();

        double cpu = new Random().nextDouble();
        long mem = new Random().nextLong();
        this.processor.updateBrokerStatus(cpu, mem);
    }
}
