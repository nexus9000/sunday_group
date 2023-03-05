package edu.itstep.collectionstests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

class LinearCollectionTest {
    @Test
    void linkedListTest(){
        LinkedList<Integer> ll = new LinkedList<>();
        ll.add(11);
        ll.add(12);
        ll.add(13);
        Assertions.assertEquals(3, ll.size());
        ll.remove(1);
        Assertions.assertEquals(2, ll.size());
        List<Integer> syncList = Collections.synchronizedList(ll);
        List<Integer> unList = Collections.unmodifiableList(ll);
       // unList.remove(1);
        Queue<Integer> queue = new LinkedList<>();
        queue.add(111);
        System.out.println(queue.peek());//print not delete
        Assertions.assertEquals(1, queue.size());
        System.out.println(queue.poll());//print delete
        Assertions.assertEquals(0, queue.size());

    }
    @Test
    @DisplayName("test hash map ")
    void hashMapTest(){
        HashMap<Integer, String> hashMap = new HashMap<>();
        hashMap.put(101, "New York");
        hashMap.put(102, "Los Angeles");
        Hashtable<Integer, String> ht = new Hashtable<>();
        Assertions.assertEquals(2, hashMap.size());
        for (Map.Entry<Integer, String> entry : hashMap.entrySet()){
            System.out.println(entry.getKey() + " "+ entry.getValue());
        }
        System.out.println("for-each iteration");
        hashMap.forEach((key, value)-> System.out.println(key + " "+value));
    }
    @Test
    void hashSetTest(){
        HashSet<String> hashSet = new HashSet<>();
        hashSet.add("Sofia");
        hashSet.add("London");
        Assertions.assertEquals(2, hashSet.size());
        hashSet.forEach(p->System.out.println(p));
        LinkedHashSet<String >lhs = new LinkedHashSet<>();

    }
}
