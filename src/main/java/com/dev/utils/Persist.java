
package com.dev.utils;


import com.dev.objects.Product;
import com.dev.objects.Tender;
import com.dev.objects.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Persist {

    private final SessionFactory sessionFactory;

    @Autowired
    public Persist (SessionFactory sf) {
        this.sessionFactory = sf;
    }

    public User getUserByUsername (String username) {
        User found = null;
        Session session = sessionFactory.openSession();
        found = (User) session.createQuery("FROM User WHERE username = :username")
                .setParameter("username", username)
                .uniqueResult();
        session.close();
        return found;
    }

    public void saveUser (User user) {
        Session session = sessionFactory.openSession();
        session.save(user);
        session.close();
    }



    public User getUserByUsernameAndToken (String username, String token) {
        User found = null;
        Session session = sessionFactory.openSession();
        found = (User) session.createQuery("FROM User WHERE username = :username " +
                        "AND token = :token")
                .setParameter("username", username)
                .setParameter("token", token)
                .uniqueResult();
        session.close();
        return found;
    }

    public List<User> getAllUsers () {
        Session session = sessionFactory.openSession();
        List<User> allUsers = session.createQuery("FROM User ").list();
        session.close();
        return allUsers;
    }
    public List<Tender> getAllTenders () {
        Session session = sessionFactory.openSession();
        List<Tender> allTender = session.createQuery("FROM Tender ").list();
        session.close();
        return allTender;
    }

    public List<Tender> getAllOpenTenders () {
        Session session = sessionFactory.openSession();
        List<Tender> allTender = session.createQuery("FROM Tender WHERE isOpen=true ").list();
        session.close();
        return allTender;
    }

    public User getUserByToken (String token) {
        Session session = sessionFactory.openSession();
        User user = (User) session.createQuery("From User WHERE token = :token")
                        .setParameter("token", token)
                                .uniqueResult();
        session.close();
        return user;
    }

    public void uploadProduct(Product product) {

        boolean uploadSuccess = false;
        Session session = sessionFactory.openSession();
        session.save(product);
        session.close();

    }



    public User getUserById (int id) {
        Session session = sessionFactory.openSession();
        User user = (User) session.createQuery("FROM User WHERE id = :id")
                .setParameter("id", id)
                .uniqueResult();
        session.close();
        return user;
    }

//    public void updateCredits(String username, int creditsTUpdate) {
//        List<Integer> idList =  sessionFactory.openSession().createQuery("select username from User where username = : username")
//                .setParameter("username", username).list();
//        Session session = sessionFactory.openSession();
//        int userId = idList.get(0);
//        System.out.println();
//        Transaction tx = null;
//        tx = session.beginTransaction();
//        User user = session.get(User.class, userId);
//        user.setAmountOfCredits(creditsTUpdate);
//        session.update(user);
//        tx.commit();
//    }



}
