package com.proyecto.ecommerce.backend.infraestructure.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.proyecto.ecommerce.backend.domain.model.UserType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String username;
	private String firstName;
	private String lastName;
	@Column(unique = true)
	private String email;
	private String address;
	private String cellPhone;
	private String password;
	@Enumerated(EnumType.STRING)
	private UserType userType;
	@CreationTimestamp
	private LocalDateTime dateCreated;
	@UpdateTimestamp
	private LocalDateTime dateUpdated;

}
