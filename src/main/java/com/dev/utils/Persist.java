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

    public Integer getAmountOfOffersForProductByUsername (String username, String productName){
        Session session = sessionFactory.openSession();
        List<Offers> amountOfOffers = session.createQuery("FROM Offers WHERE ownOfOffer = :username and productName = :productName")
                .setParameter("username",username)
                .setParameter("productName" ,productName)
                .list();
        session.close();
        return amountOfOffers.size();
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

    public Double getCreditsByUsername (String username) {
        Session session = sessionFactory.openSession();
        Double CreditsForUser = (Double) session.createQuery("SELECT amountOfCredits FROM User WHERE username = :username")
                .setParameter("username", username)
                .uniqueResult();
        session.close();
        return CreditsForUser;
    }


    public List<Offers> getOffersByUsername (String username) {
        Session session = sessionFactory.openSession();
        List<Offers> offersListForUser = session.createQuery("FROM Offers WHERE ownOfOffer = :username")
                .setParameter("username", username)
                .list();
        session.close();
        return offersListForUser;
    }

    public Double getMaxOfferByUsernameAndProduct (String username, String productName){
        Session session = sessionFactory.openSession();
        Double maxOffer = (Double) session.createQuery("SELECT MAX (amountOfOffer) FROM Offers WHERE ownOfOffer = :username AND productName = :productName")
                .setParameter("username", username)
                .setParameter("productName", productName)
                .uniqueResult();
        session.close();
        return maxOffer;
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

    public void updateAmountOfOffersForAuction(Integer auctionId, int amountOfOffering) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Auction auction = session.get(Auction.class, auctionId);
        auction.setAmountOfOffering(amountOfOffering);
        session.update(auction);
        transaction.commit();
        session.close();
    }

    public void updateMaxOfferForAuction (Integer auctionId, Double maxOffer){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Auction auction = getAuctionById(auctionId);
        auction.setMaxOfferAmount(maxOffer);
        session.update(auction);
        transaction.commit();
        session.close();
    }

    public void updateUserCredits (String username, double amountOfOffer , String productName){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        User user = getUserByUsername(username);
        Double maxOfferForProduct = getMaxOfferByUsernameAndProduct(username,productName);
        double currentCredits = user.getAmountOfCredits();
        if(maxOfferForProduct != null) {
            double amountToSubtract = amountOfOffer - maxOfferForProduct;
            user.setAmountOfCredits(currentCredits - (amountToSubtract + Constants.OFFER_UPLOAD_COST));
        }else {
            user.setAmountOfCredits(currentCredits - Constants.OFFER_UPLOAD_COST - amountOfOffer);
        }
        session.update(user);
        transaction.commit();
        session.close();
    }

    public void closeAuction (int auctionId){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Auction auction = session.get(Auction.class, auctionId);
        auction.setOpen(false);
        session.update(auction);
        transaction.commit();
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

    public Double getMaxOfferForProduct(String ownOfProduct, int auctionId) {
        Session session = sessionFactory.openSession();
        Double maxOffer = (Double) session.createQuery("SELECT MAX (amountOfOffer) FROM Offers WHERE ownOfProduct = :ownOfProduct AND auctionId = :auctionId")
                .setParameter("ownOfProduct", ownOfProduct)
                .setParameter("auctionId", auctionId)
                .uniqueResult();
        session.close();
        return maxOffer;
    }

    public void updateOfferWon(int auctionId) {
        Auction auction = getAuctionById(auctionId);


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
        Session session1 = sessionFactory.openSession();
        session1.save(auction);
        session1.close();
        Session session2 = sessionFactory.openSession();
        Transaction transaction = session2.beginTransaction();
        User user = getUserByUsername(auction.getOwnerOfTheProduct());
        double currentCredits = user.getAmountOfCredits();
        user.setAmountOfCredits(currentCredits - Constants.PRODUCT_UPLOAD_COST);
        session2.update(user);
        transaction.commit();
        session2.close();
    }

    public List<Offers> listOfMyOffers (String username,String productName){
        Session session= sessionFactory.openSession();
        List<Offers> listOfOffers = session.createQuery("SELECT amountOfOffer FROM Offers WHERE ownOfOffer = :username and productName = :productName")
                .setParameter("username",username)
                .setParameter("productName",productName)
                .list();
        session.close();
        return listOfOffers;
    }
    public List<Offers> getOffersByAuctionId (int auctionId) {
        Session session = sessionFactory.openSession();
        List<Offers> offersListForAuction = session.createQuery("FROM Offers WHERE auctionId = :auctionId")
                .setParameter("auctionId", auctionId)
                .list();
        session.close();
        return offersListForAuction;
    }

    public List<String> getTokensOwnOfProduct (String ownOfOffer) {
        Session session = sessionFactory.openSession();
        List<String> tokensOffersListForAuction = session.createQuery("SELECT token FROM User WHERE  username = :ownOfOffer")
                .setParameter("ownOfOffer", ownOfOffer)
                .list();
        session.close();
        return tokensOffersListForAuction;
    }


}