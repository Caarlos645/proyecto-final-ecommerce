package com.proyecto.ecommerce.backend.infraestructure.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.proyecto.ecommerce.backend.domain.model.Category;
import com.proyecto.ecommerce.backend.infraestructure.entity.CategoryEntity;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

	@Mappings({
		@Mapping(target = "id", source = "id"),
		@Mapping(target = "name", source = "name"),
		@Mapping(target = "dateCreated", source = "dateCreated"),
		@Mapping(target = "dateUpdated", source = "dateUpdated")
	})
	
	Category toCategory(CategoryEntity category);
	
	Iterable<Category> toCategoryList(Iterable<CategoryEntity> categoryEntities);
	
	@InheritInverseConfiguration
	CategoryEntity toCategoryEntity (Category category);
}

