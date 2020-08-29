package Syncmesh.subscription;

public interface Subscription {
    /**
     * a common interface that forces all subscriptions to be activated by the execute method
     **/
    public void execute();
}
