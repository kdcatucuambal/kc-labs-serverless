package com.kc.cloud.labs.aws.utils;

import com.kc.cloud.labs.aws.models.app.UserSample;

import java.util.List;

public class UserSampleDao2 extends BaseDao<UserSample> {



    public UserSampleDao2(){
        super("kec_users", "user", UserSample.class);
    }

    public List<UserSample> findAll(){
        return super.findAll();
    }

    public UserSample findById(String id){
        return super.findById(id);
    }

    public boolean save(UserSample object){
        return super.save(object);
    }

    public boolean update(UserSample object){
        return super.update(object);
    }

    public boolean delete(String id){
        return super.delete(id);
    }

}
