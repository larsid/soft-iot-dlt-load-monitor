package dlt.load.monitor.activator;

/**
 * 
 * @author Uellington Damasceno
 * @version 0.0.1
 */
public class Activator implements IActivator {

    @Override
    public void start() {
        System.out.println("The load monitor bundle is running!");
    }

    @Override
    public void stop() {
        System.out.println("The load monitor bundle stopped");
    }

}