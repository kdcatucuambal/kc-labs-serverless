package com.kc.cloud.labs.aws.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public abstract class  BaseDao<T> {


    private String tableName;
    private String prefix;
    private Class<T> clazz;
    public BaseDao() {}

    public BaseDao(String tableName, String prefix, Class<T> clazz) {
        this.tableName = tableName;
        this.prefix = prefix;
        this.clazz = clazz;
    }

    public List<T> findAll(){
        return SqlSampleClient.findAll(tableName, prefix, clazz);
    }

    public T findById(String id){
        return SqlSampleClient.findOne(tableName, prefix, id, clazz);
    }

    public boolean save(T object){
        return SqlSampleClient.save(object, tableName, prefix);
    }

    public boolean update(T object){
        return SqlSampleClient.update(object, tableName, prefix);
    }

    public boolean delete(String id){
        return SqlSampleClient.deleteOne(tableName, prefix, id);
    }

    public List<T> executeQuery(String sql){
        return null;
    }
}
