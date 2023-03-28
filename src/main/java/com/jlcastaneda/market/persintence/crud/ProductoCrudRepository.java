package com.jlcastaneda.market.persintence.crud;

import com.jlcastaneda.market.persintence.entity.Producto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductoCrudRepository extends CrudRepository<Producto,Integer> {

     List<Producto> findByIdCategoriaOrderByNombreAsc(int idCategoria);

     Optional<Producto> findByCantidadStockLessThanAndEstado(int cantidadStock, boolean estado);

}
