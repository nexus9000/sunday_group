package edu.itstep.streamstests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

class StreamTest {
    List<Integer> nums;

    @BeforeEach
    void setUp() {
        nums = Arrays.asList(11, 12, 3, 8);
    }

    @Test
    @DisplayName("Test Stream mapping")
    void streamMapTest() throws Exception {
        Iterator<Integer> it = nums.iterator();
        List<Integer> sq = new ArrayList<>(nums.size());
        while (it.hasNext()) {
            Integer value = it.next();
            sq.add(value * value);
        }
        Assertions.assertEquals(nums.size(), sq.size());
        for (Integer value : sq) {
            System.out.print(" " + value);
        }
        List<Integer> sqStream = nums.parallelStream().map(d -> d * d).toList();
        Assertions.assertEquals(nums.size(), sqStream.size());
    }

    @Test
    void streamStringTest() {
        List<String> str = Arrays.asList("Java", "Spring", "Python");
        List<String> filterList = str.parallelStream().filter(s -> s.startsWith("S")).toList();
        filterList.forEach(System.out::println);
        Assertions.assertEquals("Spring", filterList.get(0));
    }

    @Test
    void testReduce() {
        int sumNums = nums.parallelStream().reduce(0, Integer::sum);
        System.out.println(sumNums);
        Assertions.assertEquals(34, sumNums);
    }

}
