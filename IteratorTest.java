import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by Stefan on 2016/7/
 */
public class IteratorTest {
    public static void main(String[] args){
        Collection c = new HashSet();
        c.add("hehda");
        java.util.Iterator it = c.iterator();
        String book = (String)it.next();
        book = "henimei";
        System.out.println(c);

    }
}
