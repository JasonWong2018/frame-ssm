package com.frame.ssm.test;

public class Generic<T> {
    private T key;

    public Generic(T key){
        this.key = key;
    }

    public T getKey(){
        return key;
    }

    public <T> T showKeyName(Generic<T> container){
        System.out.println("container key :" + container.getKey());
        //当然这个例子举的不太合适，只是为了说明泛型方法的特性。
        T test = container.getKey();
        return test;
    }
}
