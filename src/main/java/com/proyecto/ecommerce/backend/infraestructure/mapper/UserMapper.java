package com.proyecto.ecommerce.backend.infraestructure.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.proyecto.ecommerce.backend.domain.model.User;
import com.proyecto.ecommerce.backend.infraestructure.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {
	@Mappings({
			// source es de la clase que pasamos como parametro, y target a la otra clase
			@Mapping(source = "id", target = "id"), @Mapping(source = "username", target = "username"),
			@Mapping(source = "lastName", target = "lastName"), @Mapping(source = "email", target = "email"),
			@Mapping(source = "address", target = "address"), @Mapping(source = "cellPhone", target = "cellPhone"),
			@Mapping(source = "password", target = "password"), @Mapping(source = "userType", target = "userType"),
			@Mapping(source = "dateCreated", target = "dateCreated"),
			@Mapping(source = "dateUpdated", target = "dateUpdated") })

	User toUser(UserEntity userEntity);

	Iterable<User> toUser(Iterable<UserEntity> userEntities);
	
	@InheritInverseConfiguration
	UserEntity toUserEntity(User user);

}
