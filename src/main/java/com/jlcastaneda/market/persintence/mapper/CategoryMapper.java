package com.jlcastaneda.market.persintence.mapper;

import com.jlcastaneda.market.domain.Category;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    //Traducir objetos

    @Mappings({
            @Mapping(source = "idCategoria" , target = "categoriId"),
            @Mapping(source = "descripcion" , target = "category"),
            @Mapping(source = "estado" , target = "active")
    })
    Category toCategory(com.jlcastaneda.market.persintence.entity.Categoria categoria);

    @InheritInverseConfiguration
    @Mapping(target = "productos", ignore = true)
    com.jlcastaneda.market.persintence.entity.Categoria toCategoria(Category category);
}
