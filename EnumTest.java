/**
 * Created by Stefan on 2016/9/7.
 */
public class EnumTest {
    public static void main(String[] args){
        EnumTest enumTest = new EnumTest();
        enumTest.test1();
    }
    public void test1(){
        Gender g = Gender.valueOf("FEMALE");
        g.setName("女");
        System.out.println(g.getName());
    }
}
