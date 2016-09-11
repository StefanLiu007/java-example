import java.util.LinkedHashSet;

/**
 * Created by Stefan on 2016/9/7.
 */
public class LinkHashSetTest {
    public static void main(String[] args){
        LinkedHashSet books = new LinkedHashSet();
        books.add("nihao");
        books.add("wohao");
        System.out.println(books);
        books.remove("nihao");
        books.add("nihao");
        System.out.print(books);
    }
}
