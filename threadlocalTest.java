import static java.lang.String.valueOf;

/**
 * Created by Stefan on 2016/7/20.
 */
public class threadlocalTest {
    public static final ThreadLocal<String> mLocal = new ThreadLocal<String>();
    public static void main(String[] args){
        mLocal.set("stefan");
        System.out.println(mLocal.get()+".....");

        for (int i = 0; i<3; i++) {


         Thread t =    new Thread(new Runnable() {
                @Override
                public void run() {
//                    mLocal.set("stefan1........"+Thread.currentThread());
                    System.out.println(mLocal.get() );
                }
            });
            try {
                t.start();
                t.join();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(mLocal.get());
    }
}
