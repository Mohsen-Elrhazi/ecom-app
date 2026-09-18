package com.app.ecom.productservice.mapper;

import com.app.ecom.productservice.dto.product.request.CreateProductRequest;
import com.app.ecom.productservice.dto.product.request.UpdateProductRequest;
import com.app.ecom.productservice.dto.product.response.ProductResponse;
import com.app.ecom.productservice.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProductMapper {

    @Mapping(target = "category", ignore = true)
     Product toEntity(CreateProductRequest request);

     ProductResponse toResponse(Product product);

     void updateEntity(UpdateProductRequest request, @MappingTarget Product product);
}
