public class Janitor implements Runnable {

    @Override
    public void run() {
        DataBase.archiveStaleOrders();
    }
}
