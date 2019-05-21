package experiment.concurrent.deadLock;

/**
 * @author : liulei
 **/
public class TransferMoney {
    //加时赛锁，当两个对象的hashCode相同的时候，先获得加时赛锁来执行，消除死锁。
    private static final Object lock = new Object();
    private static final Integer fromObject = 100;
    private static final Integer toObject = 200;

    public void transferMoney(final Integer from, final Integer to, final Integer money) throws Exception{

        class Helper {
            Integer fromCount = from;
            Integer toCount = to;

            public void transfer() throws Exception {
                if(from < money) {
                    throw new Exception("余额不足");
                } else {
                    fromCount = from - money;
                    System.out.println("from left: " + fromCount);
                    toCount = to + money;
                    System.out.println("to become: " + toCount);
                }
            }
        }


        int fromID = System.identityHashCode(from);
        System.out.println(from + " id = " + fromID);
        int toID = System.identityHashCode(to);
        System.out.println(to +" id = " + toID);

        //根据id决定对象的使用顺序，保证只是用一个顺序执行
        if(fromID < toID) {
            synchronized (from) {
                synchronized (to) {
                    new Helper().transfer();
                }
            }
        } else if(fromID > toID) {
            synchronized (to) {
                synchronized (from) {
                    new Helper().transfer();
                }
            }
        } else {
            synchronized (lock) {
                synchronized (from) {
                    synchronized (to) {
                        new Helper().transfer();
                    }
                }
            }
        }

    }


    public static void main(String[] args) throws Exception {
        TransferMoney money = new TransferMoney();

        new Thread(() -> {
            try {
                money.transferMoney(fromObject, toObject, 30);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                money.transferMoney(fromObject, toObject, 50);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();


    }
}
