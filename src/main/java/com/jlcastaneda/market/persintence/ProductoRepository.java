package com.jlcastaneda.market.persintence;

import com.jlcastaneda.market.persintence.crud.ProductoCrudRepository;
import com.jlcastaneda.market.persintence.entity.Producto;

import java.util.List;
import java.util.Optional;

public class ProductoRepository {
    private ProductoCrudRepository productoCrudRepository;

    public List<Producto> getAll(){
        return (List<Producto>) productoCrudRepository.findAll();
    }

    public  List<Producto> getByCategoria(int idCategoria){
        return productoCrudRepository.findByIdCategoriaOrderByNombreAsc(idCategoria);
    }

    public Optional<List<Producto>> getEscasos(int cantidad){
        return  productoCrudRepository.findByCantidadStockLessThanAndEstado(cantidad, true):
    }
}
