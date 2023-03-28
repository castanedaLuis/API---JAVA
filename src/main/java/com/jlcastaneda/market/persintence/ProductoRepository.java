package com.jlcastaneda.market.persintence;

import com.jlcastaneda.market.persintence.crud.ProductoCrudRepository;
import com.jlcastaneda.market.persintence.entity.Producto;

import java.util.List;

public class ProductoRepository {
    private ProductoCrudRepository productoCrudRepository;

    public List<Producto> getAll(){
        return (List<Producto>) productoCrudRepository.findAll();
    }
}
