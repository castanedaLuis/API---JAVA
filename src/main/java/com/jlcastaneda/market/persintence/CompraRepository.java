package com.jlcastaneda.market.persintence;

import com.jlcastaneda.market.domain.Purchase;
import com.jlcastaneda.market.domain.repository.PurchaseRepository;
import com.jlcastaneda.market.persintence.crud.CompraCrudrepository;
import com.jlcastaneda.market.persintence.entity.Compra;
import com.jlcastaneda.market.persintence.mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository //Le decims a spring qu ees un componente que se va a comunicar directo con la db

public class CompraRepository implements PurchaseRepository {

    @Autowired
    private CompraCrudrepository compraCrudrepository;

    @Autowired
    private PurchaseMapper mapper;

    @Override
    public List<Purchase> getAll() {
        return mapper.toPurchases((List<Compra>) compraCrudrepository.findAll());
    }

    @Override
    public Optional<List<Purchase>> getByClient(String clientId) {
        return compraCrudrepository.findByIdCliente(clientId)
                .map(compras -> mapper.toPurchases(compras));
    }

    @Override
    public Purchase save(Purchase purchase) {
        Compra compra = mapper.toCompra(purchase);
        compra.getProductos().forEach(producto -> producto.setCompra(compra));
        return mapper.toPurchase(compraCrudrepository.save(compra));
    }
}
