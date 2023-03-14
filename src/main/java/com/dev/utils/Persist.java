
package com.dev.utils;


import com.dev.models.AuctionModel;
import com.dev.objects.Offers;
import com.dev.objects.Auction;
import com.dev.objects.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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

    public Auction getProductByProductNameAndOwnerOf (String productName, String ownerOfProduct) {
        Auction found = null;
        Session session = sessionFactory.openSession();
        found = (Auction) session.createQuery("FROM Auction WHERE productName = :productName and ownerOfTheProduct = :ownerOfProduct")
                .setParameter("productName", productName)
                .setParameter("ownerOfProduct",ownerOfProduct)
                .uniqueResult();
        session.close();
        return found;
    }

    public Auction getAuctionById (int id) {
        Session session = sessionFactory.openSession();
        Auction auction = (Auction) session.createQuery("From Auction WHERE id = :id")
                .setParameter("id", id)
                .uniqueResult();
        session.close();
        return auction;
    }

    public List<Auction> getAuctionsByUsername (String username) {
        Session session = sessionFactory.openSession();
        List<Auction> auctionListForUser = session.createQuery("FROM Auction WHERE ownerOfTheProduct = :username")
                .setParameter("username", username)
                .list();
        session.close();
        return auctionListForUser;
    }

    public List<Offers> getOffersByUsername (String username) {
        Session session = sessionFactory.openSession();
        List<Offers> offersListForUser = session.createQuery("FROM Offers WHERE ownOfOffer = :username")
                .setParameter("username", username)
                .list();
        session.close();
        return offersListForUser;
    }

    public double getMaxOfferByUsernameAndProduct (String username, String productName){
        Session session = sessionFactory.openSession();
        double maxOffer = (double)session.createQuery("SELECT MAX (amountOfOffer) FROM Offers WHERE ownOfOffer = :username AND productName = :productName")
                .setParameter("username", username)
                .setParameter("productName", productName)
                .uniqueResult();
        session.close();
        return maxOffer;
    }

    public int getAmountOfOffersByName (String productName) {
        Session session = sessionFactory.openSession();
        List<String> countOfOffersForProduct = session.createQuery("SELECT productName FROM Offers WHERE productName = :productName")
                .setParameter("productName", productName)
                .list();
        session.close();
        return countOfOffersForProduct.size();
    }

    public void saveUser (User user) {
        Session session = sessionFactory.openSession();
        session.save(user);
        session.close();
    }

    public void saveOffer (Offers offer) {
        Session session = sessionFactory.openSession();
        session.save(offer);
        session.close();
    }

    public void updateAuction (Auction auction) {
        Session session = sessionFactory.openSession();
        session.saveOrUpdate(auction);
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

    public double getMaxOfferForProduct(String ownOfProduct, int auctionId) {
        Session session = sessionFactory.openSession();
        double maxOffer = (double)session.createQuery("SELECT MAX (amountOfOffer) FROM Offers WHERE ownOfProduct = :ownOfProduct AND auctionId = :auctionId")
                .setParameter("ownOfProduct", ownOfProduct)
                .setParameter("auctionId", auctionId)
                .uniqueResult();
        Auction auction = getAuctionById(auctionId);
        auction.setMaxOfferAmount(maxOffer);
        session.save(auction);
        session.close();
        return maxOffer;
    }

    public List<User> getAllUsers () {
        Session session = sessionFactory.openSession();
        List<User> allUsers = session.createQuery("FROM User ").list();
        session.close();
        return allUsers;
    }

    public List<Auction> getAllAuctions () {
        Session session = sessionFactory.openSession();
        List<Auction> allAuction = session.createQuery("FROM Auction ").list();
        session.close();
        return allAuction;
    }

    public List<Offers> getAllOffers () {
        Session session = sessionFactory.openSession();
        List<Offers> allOffers = session.createQuery("FROM Offers ").list();
        session.close();
        return allOffers;
    }

    public List<Auction> getAllOpenAuctions () {
        Session session = sessionFactory.openSession();
        List<Auction> allAuction = session.createQuery("FROM Auction WHERE isOpen=true ").list();
        session.close();
        return allAuction;
    }

    public User getUserByToken (String token) {
        Session session = sessionFactory.openSession();
        User user = (User) session.createQuery("From User WHERE token = :token")
                        .setParameter("token", token)
                                .uniqueResult();
        session.close();
        return user;
    }

    public void uploadProduct(Auction auction) {
       // boolean uploadSuccess = false;
        Session session = sessionFactory.openSession();
        session.save(auction);
        session.close();
    }

    public void updateCreditsForUser (String username){
        Session session=sessionFactory.openSession();
        Transaction transaction=session.beginTransaction();
        User user = getUserByUsername(username);
        if (user!=null ){
            user.setAmountOfCredits(user.getAmountOfCredits()-2);
        }
        session.saveOrUpdate(user);
        transaction.commit();
    }

  /* public void updateMaxOfferForAuction (String productName){
        Session session=sessionFactory.openSession();
        Transaction transaction=session.beginTransaction();
        Auction auction = getProductByProductName(productName);
        if (auction!=null ){
            auction.setMaxOfferAmount(getAllOffersForProduct(auction.getOwnerOfTheProduct(), productName));
        }
        session.saveOrUpdate(auction);
        transaction.commit();
    }*/

    public List<Offers> listOfMyOffers (String username,String productName){
        Session session= sessionFactory.openSession();
        List<Offers> listOfOffers = session.createQuery("SELECT amountOfOffer FROM Offers WHERE ownOfOffer = :username and productName = :productName")
                .setParameter("username",username)
                .setParameter("productName",productName)
                .list();
        session.close();
        return listOfOffers;
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
