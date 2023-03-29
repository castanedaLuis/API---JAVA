package com.jlcastaneda.market.domain.repository;

import com.jlcastaneda.market.domain.Purchase;

import java.util.List;
import java.util.Optional;

public interface PurchaseRepository {
    List<Purchase> getAll();
    Optional <List<Purchase>> getByClient(String clientId);
    Purchase save(Purchase purchase);

}
