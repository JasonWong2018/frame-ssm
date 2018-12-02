package com.frame.ssm.test;

import com.frame.ssm.domain.Student;
import com.frame.ssm.domain.User;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * java反射练习
 */
public class ReflectTest {

    @Test
    public void test1() {
        Student s = new Student();
        Class clazz = s.getClass();
        System.out.println(clazz.getName());

        Class clazz2 = Student.class;
        System.out.println(clazz == clazz2);

        try {
            Class clazz3 = Class.forName("com.frame.ssm.domain.Student");
            System.out.println(clazz2 == clazz3);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() {
        try {
            //1.加载Class对象
            Class clazz = Class.forName("com.frame.ssm.domain.Student");
            //2.获取所有公有构造方法
            System.out.println("**********************所有公有构造方法*********************************");
            Constructor[] constructors = clazz.getConstructors();
            for (Constructor constructor : constructors) {
                System.out.println(constructor);
            }

            System.out.println("************所有的构造方法(包括：私有、受保护、默认、公有)***************");
            constructors = clazz.getDeclaredConstructors();
            for (Constructor constructor : constructors) {
                System.out.println(constructor);
            }

            System.out.println("*****************获取公有、无参的构造方法*******************************");
            Constructor con = clazz.getConstructor(null);

            System.out.println("con = " + con);

            //调用构造方法
            Object obj = con.newInstance();
            System.out.println("obj = " + obj);

            System.out.println("******************获取私有构造方法，并调用*******************************");
            con = clazz.getDeclaredConstructor(char.class);
            System.out.println(con);
            //调用构造方法
            con.setAccessible(true);//暴力访问(忽略掉访问修饰符)
            obj = con.newInstance('男');


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test3() {
        //1.加载Class对象
        try {
            Class clazz = Class.forName("com.frame.ssm.domain.Student");

            //2.获取字段
            System.out.println("************获取所有公有的字段********************");
            Field[] fieldArray = clazz.getFields();
            for (Field field : fieldArray) {
                System.out.println(field);
            }

            System.out.println("************获取所有的字段(包括私有、受保护、默认的)********************");
            fieldArray = clazz.getDeclaredFields();
            for (Field f : fieldArray) {
                System.out.println(f);
            }

            System.out.println("*************获取公有字段**并调用***********************************");
            Field f = clazz.getField("name");
            System.out.println(f);
            //获取一个对象
            Object obj = clazz.getConstructor().newInstance();//产生Student对象--》Student stu = new Student();
            //为字段设置值
            f.set(obj, "刘德华");//为Student对象中的name属性赋值--》stu.name = "刘德华"
            //验证
            Student stu = (Student) obj;
            System.out.println("验证姓名：" + stu.name);

            System.out.println("**************获取私有字段****并调用********************************");
            f = clazz.getDeclaredField("phoneNum");
            System.out.println(f);
            f.setAccessible(true);//暴力反射，解除私有限定
            f.set(obj, "18888889999");
            System.out.println("验证电话：" + stu);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test4(){
        try {
            Class clazz = Class.forName("com.frame.ssm.domain.Student");
            //2.获取所有公有方法
            System.out.println("***************获取所有的”公有“方法*******************");
            clazz.getMethods();
            Method[] methodArray = clazz.getMethods();
            for(Method m : methodArray){
                System.out.println(m);
            }

            System.out.println("***************获取所有的方法，包括私有的*******************");
            methodArray = clazz.getDeclaredMethods();
            for(Method m : methodArray){
                System.out.println(m);
            }
            System.out.println("***************获取公有的show1()方法*******************");
            Method m = clazz.getMethod("show1", String.class);
            System.out.println(m);
            //实例化一个Student对象
            Object obj = clazz.getConstructor().newInstance();
            m.invoke(obj, "刘德华");

            System.out.println("***************获取私有的show4()方法******************");
            m = clazz.getDeclaredMethod("show4", int.class);
            System.out.println(m);
            m.setAccessible(true);//解除私有限定
            Object result = m.invoke(obj, 20);//需要两个参数，一个是要调用的对象（获取有反射），一个是实参
            System.out.println("返回值：" + result);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Test
    public void test5(){
        try {
            Class clazz = Class.forName("com.frame.ssm.domain.Student");
            Method methodMain = clazz.getMethod("main", String[].class);
            methodMain.invoke(null, (Object)new String[]{"a","b","c"});//方式一


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test6(){
        ArrayList<String> strList = new ArrayList<>();
        strList.add("aaa");
        strList.add("bbb");

        Class clazz = strList.getClass();

        try {
            Method add = clazz.getMethod("add",Object.class);
            add.invoke(strList,100);

            //遍历集合
            for(Object obj : strList){
                System.out.println(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
