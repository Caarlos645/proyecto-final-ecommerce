package com.proyecto.ecommerce.backend.infraestructure.adapter;

import org.springframework.stereotype.Repository;

import com.proyecto.ecommerce.backend.domain.model.Order;
import com.proyecto.ecommerce.backend.domain.model.OrderState;
import com.proyecto.ecommerce.backend.domain.port.IOrderRepository;
import com.proyecto.ecommerce.backend.infraestructure.entity.OrderEntity;
import com.proyecto.ecommerce.backend.infraestructure.entity.UserEntity;
import com.proyecto.ecommerce.backend.infraestructure.mapper.IOrderMapper;

@Repository
public class OrderCrudRepositoryImpl implements IOrderRepository {

	private final IOrderMapper iOrderMapper;
	private final IOrderCRUDRepository iOrderCRUDRepository;

	public OrderCrudRepositoryImpl(IOrderMapper orderMapper, IOrderCRUDRepository iOrderCRUDRepository) {
		this.iOrderMapper = orderMapper;
		this.iOrderCRUDRepository = iOrderCRUDRepository;
	}

	@Override
	public Order save(Order order) {
		OrderEntity orderEntity = iOrderMapper.toOrderEntity(order);
		orderEntity.getOrderProducts().forEach(o -> o.setOrderEntity(orderEntity));
		return iOrderMapper.toOrder(iOrderCRUDRepository.save(orderEntity));
	}

	@Override
	public Order findById(Integer id) {
		return iOrderMapper.toOrder(iOrderCRUDRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Orden con id: " + id + " no encontrada.")));
	}

	@Override
	public Iterable<Order> findAll() {
		return iOrderMapper.toOrderList(iOrderCRUDRepository.findAll());
	}

	@Override
	public Iterable<Order> findByUserId(Integer userId) {
		UserEntity userEntity = new UserEntity();
		userEntity.setId(userId);
		return iOrderMapper.toOrderList(iOrderCRUDRepository.findByUserEntity(userEntity));
	}

	@Override
	public void updateStateById(Integer id, String state) {
		if (state.equals(OrderState.CANCELLED)) {
			iOrderCRUDRepository.updateStateById(id, OrderState.CANCELLED);
		} else {
			iOrderCRUDRepository.updateStateById(id, OrderState.CONFIRMED);
		}
	}

}
