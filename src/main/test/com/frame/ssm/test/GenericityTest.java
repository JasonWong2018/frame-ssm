package com.frame.ssm.test;

import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 泛型练习
 */
public class GenericityTest {


    @Test
    public void test1() {
        //泛型的类型参数只能是类类型（包括自定义类），不能是简单类型
        //传入的实参类型需与泛型的类型参数类型相同，即为Integer.
        Generic<Integer> genericInteger = new Generic<Integer>(123456);

        //传入的实参类型需与泛型的类型参数类型相同，即为String.
        Generic<String> genericString = new Generic<String>("key_vlaue");
        System.out.println("泛型测试" + "key is " + genericInteger.getKey());
        System.out.println("泛型测试" + "key is " + genericString.getKey());
    }

    @Test
    public void test2() {
        Generic generic = new Generic("111111");
        Generic generic1 = new Generic(4444);
        Generic generic2 = new Generic(55.55);
        Generic generic3 = new Generic(false);
        System.out.println("泛型测试key is " + generic.getKey());
        System.out.println("泛型测试key is " + generic1.getKey());
        System.out.println("泛型测试key is " + generic2.getKey());
        System.out.println("泛型测试key is " + generic3.getKey());
    }

    @Test
    public void test3() {
        FruitGenerator f = new FruitGenerator();
        String next = f.next();
        System.out.println(next);
    }

    @Test
    public void test4() {
        Generic<Number> gNumber = new Generic<Number>(456);
        Generic<Integer> gInteger = new Generic<Integer>(123);
        showKeyValue(gNumber);
        //showKeyValue(gInteger);
        gNumber.showKeyName(gNumber);
    }


    public void showKeyValue(Generic<Number> obj) {
        System.out.println("泛型测试key value is " + obj.getKey());
    }


    @Test
    public void test5() {
        List<String> stringArrayList = new ArrayList<String>();
        List<Integer> integerArrayList = new ArrayList<Integer>();
        Class classStringArrayList = stringArrayList.getClass();
        Class classIntegerArrayList = integerArrayList.getClass();
        if (classStringArrayList.equals(classIntegerArrayList)) {
            System.out.println("泛型测试,类型相同");
        }
    }

}
