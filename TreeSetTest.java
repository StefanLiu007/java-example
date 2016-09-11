import java.util.TreeSet;

/**
 * Created by Stefan on 2016/9/7.
 */
public class TreeSetTest {
    public static void main(String[] args){
       TreeSetTest treeSetTest = new TreeSetTest();
//        treeSetTest.text1();
        treeSetTest.text2();
    }
    public void text1(){
        TreeSet nums = new TreeSet();
        nums.add(1);
        nums.add(6);
        nums.add(3);
        nums.add(3);
        nums.add(-3);
        System.out.println(nums);
        System.out.println(nums.first());
        System.out.println(nums.last());
        System.out.println(nums.headSet(5));//小于5
        System.out.println(nums.subSet(3,6));//大于等于3，小于6
        System.out.println(nums.tailSet(3));//大于等于3
    }
    public void text2(){
        TreeSet set = new TreeSet();
        Z z1 = new Z(6);
        set.add(z1);
        System.out.println(set.add(z1));
        System.out.println(set);
        ((Z) (set.first())).age = 9;
        System.out.println(((Z)(set.last())).age);

    }
}
class Z implements Comparable{
    @Override
    public String
    toString() {
        return "Z{" +
                "age=" + age +
                '}';
    }

    int age;
    public Z(int age){
        this.age = age;
    }
    public boolean equals(Object obj){
        return true;
    }
    @Override
    public int compareTo(Object o) {
        return 1;
    }
}
