import java.util.LinkedList;

/**
 * Created by Stefan on 2016/9/8.
 */
public class LinkedListTest {
    public static void main(String[] args){
        LinkedList linkedList  = new LinkedList();
        linkedList.offer("疯狂的java讲义");
        linkedList.push("轻量级企业javaee讲义");
        linkedList.offerFirst("疯狂的android讲义");
        for (int i = 0;i < linkedList.size();i++){
            System.out.println("遍历中："+linkedList.get(i));
        }
    }
}
