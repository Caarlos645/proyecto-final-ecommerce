package com.proyecto.ecommerce.backend.infraestructure.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.proyecto.ecommerce.backend.domain.model.OrderProduct;
import com.proyecto.ecommerce.backend.infraestructure.entity.OrderProductEntity;

@Mapper(componentModel = "spring")
public interface IOrderProductMapper {

	@Mappings (
			{
				@Mapping(source = "id", target = "id"),
				@Mapping(source = "quantity", target = "quantity"),
				@Mapping(source = "precio", target = "precio"),
				@Mapping(source = "productId", target = "productId")
			}
			)
	OrderProduct toOrderProduct(OrderProductEntity orderProductEntity);
	
	Iterable<OrderProduct> toOrderProductList(Iterable<OrderProductEntity> orderProductEntity);
	
	@InheritInverseConfiguration
	OrderProductEntity toOrderProductEntity(OrderProduct orderProduct);
	
}
