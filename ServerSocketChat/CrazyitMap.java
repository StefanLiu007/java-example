package ServerSocketChat;

import java.util.*;

/**
 * Created by Stefan on 2016/9/11.
 */
public class CrazyitMap<K,V> {
    public Map<K,V> map = Collections.synchronizedMap(new HashMap<K, V>());
    public synchronized void removeByValue(Object value){
        for (Object key : map.keySet()){
            if (map.get(key) == value){
                map.remove(key);
                break;
            }
        }
    }
    public synchronized Set<V> ValueSet(){
        Set<V> result = new HashSet<V>();
        map.forEach((key,value) -> result.add(value));
        return result;
    }
    public synchronized K getKeyByValue(V val){
        for (K key : map.keySet()){
            if (map.get(key) == val || map.get(key).equals(val)){
                return key;
            }
        }

        return null;
    }
    public synchronized V put (K key,V value){
        for (V val : ValueSet()){
            if (val.equals(value) && val.hashCode() == value.hashCode()){
                throw new RuntimeException("MyMap 实例中不允许有重复value！");
            }
        }
        return map.put(key,value);
    }
}
