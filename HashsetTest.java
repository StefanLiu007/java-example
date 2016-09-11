import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by Stefan on 2016/7/2.
 */
public class HashsetTest {
    public static void main(String[] args) {
        HashSet h = new HashSet();
        h.add(new HashsetTest2(5));
        h.add(new HashsetTest2(-3));
        h.add(new HashsetTest2(9));
        h.add(new HashsetTest2(-2));
        System.out.println(h);
        Iterator it = h.iterator();
        HashsetTest2 ht = (HashsetTest2) it.next();
        ht.count = -3;
        System.out.println(h);

    }
    /**
     * Created by Stefan on 2016/7/2.
     */
    public static class HashsetTest2 {
        int count;

        public HashsetTest2(int count) {
            this.count = count;
        }

        public String toString() {
            return "R[count:" + count + "]";
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && obj.getClass() == HashsetTest2.class) {
                HashsetTest2 r = (HashsetTest2) obj;
                return this.count == r.count;
            }
            return false;
        }

        public int hashCode() {
            return this.count;
        }

    }





}
