package dlt.load.monitor.model;


import com.sun.management.OperatingSystemMXBean;
import java.lang.management.ManagementFactory;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Uellington Damasceno
 */
public class LoadMonitor implements Runnable{

    private final ScheduledExecutorService executor;
    private OperatingSystemMXBean so;
    
    public LoadMonitor() {
        this.executor = Executors.newSingleThreadScheduledExecutor();
        this.so = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
    }

    public void start() {
        this.executor.scheduleAtFixedRate(this, 0, 5, TimeUnit.SECONDS);
        System.out.println("Load monitor started!");
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
        System.out.println("Executor finalized!");
    }

    @Override
    public void run() {
        double cpu = this.so.getSystemCpuLoad();
        long mem = this.so.getFreePhysicalMemorySize();
        System.out.println("CPU: "+ cpu);
        System.out.println("Mem: "+ mem);
    }
}
