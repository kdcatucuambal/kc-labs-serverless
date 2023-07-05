package com.kc.cloud.labs.aws.utils;

import com.kc.cloud.labs.aws.models.app.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDao {

    private String username;
    private String password;
    private String url;

    public UserDao() {
    }

    public UserDao(String username, String password, String url) {
        this.username = username;
        this.password = password;
        this.url = url;
    }

    public List<User> getAll(){
        try (Session session = HibernateUtil.getSessionFactory(url, username, password).openSession()) {
            return session.createQuery("from User", User.class).list();
        }
    }

    public void save(User user){
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory(url, username, password).openSession()) {
            transaction = session.beginTransaction();
            session.persist(user);
            session.getTransaction().commit();
        }catch (Exception e){
            if (transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }


}