package com.coursework.Kotik.Repositories;

import com.coursework.Kotik.Models.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

public interface PurchaseRepository extends CrudRepository<Purchase, Long> {
    Purchase findByUserIdAndProductId(long userId, long productId);
    Purchase findByIdAndUserId(long id, long userId);
    Purchase findById(long id);
    List<Purchase> findAllByUserId(long userId);
    Long deleteById(long id);
    @Transactional
    Long deleteAllByUserId(long userId);
}
