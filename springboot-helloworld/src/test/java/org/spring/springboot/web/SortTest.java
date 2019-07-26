package org.spring.springboot.web;

import org.junit.Test;
import org.spring.springboot.suanfa.Sort;

import java.util.*;

import static org.junit.Assert.assertEquals;


public class SortTest {
    Sort sort = new Sort();
    @Test
    public void testBubbleSort() {
        int[] arr = {3,2,5,1,9,7};
        //sort.bubbleSort(arr);
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
    }



    //重写comparator
    @Test
    public void testSort() {
        List<Person> list = new ArrayList<Person>();
        Person p1 = new Person();
        p1.setName("张三");
        p1.setAge(12);
        list.add(p1);

        Person p2 = new Person();
        p2.setName("李四");
        p2.setAge(18);
        list.add(p2);

        Person p3 = new Person();
        p3.setName("小明");
        p3.setAge(14);
        list.add(p3);
        Collections.sort(list,new PersonComparator());
        for (Person person : list) {
            System.out.print(person.getName() + person.getAge() + " ");
        }
    }
    public class PersonComparator implements Comparator{

        @Override
        public int compare(Object o1,Object o2) {
            return ((Person) o1).getAge() - ((Person) o2).getAge();
        }
    }
    class Person{
        String name;
        int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}
