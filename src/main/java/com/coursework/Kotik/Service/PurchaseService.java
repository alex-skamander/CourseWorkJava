package com.coursework.Kotik.Service;

import com.coursework.Kotik.Models.Purchase;
import com.coursework.Kotik.Repositories.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseService {
    @Autowired
    private PurchaseRepository purchaseRepository;


    public void addPurchase(Purchase purchase) {
        purchaseRepository.save(purchase);
    }

    public Purchase getPurchaseByUserIdAndProductId(long userId, long productId){
        return purchaseRepository.findByUserIdAndProductId(userId, productId);
    }

    public Purchase getPurchaseByIdAndUserId(long id, long userid){
        return purchaseRepository.findByIdAndUserId(id, userid);
    }

    public Purchase getPurchaseById(long id){
        return purchaseRepository.findById(id);
    }

    public List<Purchase> getAllPurchaseUser(long userId) {
        return purchaseRepository.findAllByUserId(userId);
    }

    public void deletePurchase(long id){
        purchaseRepository.deleteById(id);
    }

    public void deleteAllPurchaseByUserId(long userId){
        purchaseRepository.deleteAllByUserId(userId);
    }




}
