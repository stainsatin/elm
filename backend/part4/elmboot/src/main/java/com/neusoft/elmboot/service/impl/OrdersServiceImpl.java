package com.neusoft.elmboot.service.impl;

import com.neusoft.elmboot.domain.VirtualWallet;
import com.neusoft.elmboot.domain.impl.VirtualWalletImpl;
import com.neusoft.elmboot.entity.Cart;
import com.neusoft.elmboot.entity.Orders;
import com.neusoft.elmboot.exception.order.BusinessInOrderNotFoundException;
import com.neusoft.elmboot.exception.order.DeliveryAddressInOrderNotFoundException;
import com.neusoft.elmboot.exception.order.OrderHasPayedException;
import com.neusoft.elmboot.exception.wallet.BalanceRemainNotEnoughException;
import com.neusoft.elmboot.exception.wallet.PayOrdersFailedException;
import com.neusoft.elmboot.exception.wallet.UserHasNotCreatedWalletIdException;
import com.neusoft.elmboot.mapper.*;
import com.neusoft.elmboot.po.DeliveryAddress;
import com.neusoft.elmboot.po.TransactionPo;
import com.neusoft.elmboot.po.VirtualWalletPo;
import com.neusoft.elmboot.service.OrdersService;
import com.neusoft.elmboot.util.CommonUtil;
import com.neusoft.elmboot.util.UserUtil;
import jakarta.annotation.Resource;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class OrdersServiceImpl implements OrdersService {
	@Resource
	private CartMapper cartMapper;
	@Resource
	private OrdersMapper ordersMapper;
	@Resource
	private DeliveryAddressMapper deliveryAddressMapper;
	@Resource
	private UserMapper userMapper;
	@Resource
	private TransactionMapper transactionMapper;

	@Resource
	private VirtualWalletMapper virtualWalletMapper;

	@Caching(evict = {@CacheEvict(cacheNames = "ordersList", allEntries = true)})
	@Override
	@Transactional
	public int createOrders(Integer businessId, Integer daId, double orderTotal) throws BusinessInOrderNotFoundException, DeliveryAddressInOrderNotFoundException {
		String userId = UserUtil.getUserId();
		List<Cart> cartList = cartMapper.getCartByUserId(userId);

		DeliveryAddress da = deliveryAddressMapper.getDeliveryAddressById(daId);
		if (da == null) throw new DeliveryAddressInOrderNotFoundException();

		Orders orders = new Orders(
				null,
				userId,
				businessId,
				CommonUtil.getCurrentDate(),
				orderTotal,
				daId,
				0
		);
		ordersMapper.saveOrders(orders);
		AtomicReference<Boolean> hasFound = new AtomicReference<>(false);
		cartList.forEach(
				cart -> {
					if (cart.getBusinessId().equals(businessId)) {
						hasFound.set(true);
						cartMapper.removeCart(cart);
					}
				});
		if (!hasFound.get()) {
			throw new BusinessInOrderNotFoundException();
		}
		return orders.getOrderId();
	}

	@Override
	public Orders getOrdersById(Integer orderId) {
		return ordersMapper.getOrdersById(orderId);
	}

	@Override
	public List<Orders> listOrdersByUserId(String userId) {
		return ordersMapper.listOrdersByUserId(userId);
	}


	@Override
	@Transactional
	public String payOrders(Integer orderId) throws BalanceRemainNotEnoughException, UserHasNotCreatedWalletIdException, PayOrdersFailedException, OrderHasPayedException {
		Orders orders = ordersMapper.getOrdersById(orderId);
		if (orders == null) throw new PayOrdersFailedException();
		Orders checkOrders = ordersMapper.getOrdersByIdOrderState(orderId, 0);
		if (checkOrders == null) throw new OrderHasPayedException();

		double orderTotal = orders.getOrderTotal();
		String userId = UserUtil.getUserId();
		Integer outputWalletId = userMapper.getWalletIdByUserId(userId);
		if (outputWalletId == null) throw new UserHasNotCreatedWalletIdException();
		VirtualWalletPo outputVirtualWalletPo = virtualWalletMapper.getVirtualWalletById(outputWalletId);
		VirtualWallet outputVirtualWallet = new VirtualWalletImpl(outputVirtualWalletPo.getWalletId(), outputVirtualWalletPo.getBalance());
		if (outputVirtualWallet.decreaseBalance(orderTotal) == 1) {
			TransactionPo transactionPo = new TransactionPo(CommonUtil.getCurrentDate(), orderTotal, 1, null, outputWalletId);
			int done1 = virtualWalletMapper.updateBalance(outputVirtualWalletPo);
			int done2 = transactionMapper.writeTransaction(transactionPo);
			int done3 = ordersMapper.payOrders(orderId);
			if (done1 == 1 && done2 == 1 && done3 == 1) {
				return "支付成功";
			} else {
				throw new PayOrdersFailedException();
			}
		} else throw new BalanceRemainNotEnoughException();
	}
}
