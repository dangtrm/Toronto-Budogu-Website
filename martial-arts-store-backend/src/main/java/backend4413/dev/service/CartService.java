package backend4413.dev.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import backend4413.dev.model.Order;
import backend4413.dev.model.OrderItem;
import backend4413.dev.model.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import backend4413.dev.model.Cart;
import backend4413.dev.model.CartItem;
import backend4413.dev.repository.CartRepository;

@Service
public class CartService {

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private ProductService productService;

	@Autowired
	private OrderService orderService;

	public Optional<Cart> findByUserId(String userId) {
		return cartRepository.findByUserId(userId);
	}

	public Cart insert(String userId, CartItem cartItem) throws NotFoundException {
		userService.findUser(userId).orElseThrow(() -> new NotFoundException());
		Product prod = productService.findProduct(cartItem.getProductId()).orElseThrow(() -> new NotFoundException());
		cartItem.setTotalPrice(cartItem.getPrice() * cartItem.getQuantity());
		cartItem.setProductName(prod.getName());
		Cart cart = this.findByUserId(userId).orElse(new Cart(userId, new ArrayList<CartItem>()));
		List<CartItem> list = cart.getCartItems();
		if (list == null) {
			list = new ArrayList<CartItem>();
		} else {
			for (CartItem item : list) {
				if (cartItem.getProductId().equals(item.getProductId())) {
					prod.setStockQuantity(prod.getStockQuantity() - cartItem.getQuantity());
					productService.saveProduct(prod);
					item.setQuantity(item.getQuantity() + cartItem.getQuantity());
					cart.setCartItems(list);
					cart.totalAmountAndQuantity();
					return cartRepository.save(cart);
//					}
				}
			}
		}
		list.add(cartItem);
		prod.setStockQuantity(prod.getStockQuantity() - cartItem.getQuantity());
		productService.saveProduct(prod);
		cart.totalAmountAndQuantity();
		return cartRepository.save(cart);
	}

	public Cart save(String userId, CartItem cartItem) throws NotFoundException {

		userService.findUser(userId).orElseThrow(() -> new NotFoundException());
		Product prod = productService.findProduct(cartItem.getProductId()).orElseThrow(() -> new NotFoundException());
		Cart cart = this.findByUserId(userId).orElse(new Cart(userId, new ArrayList<CartItem>()));
		cartItem.setTotalPrice(cartItem.getPrice() * cartItem.getQuantity());
		cartItem.setProductName(prod.getName());
		List<CartItem> list = cart.getCartItems();
		if (list == null) {
			list = new ArrayList<CartItem>();
			prod.setStockQuantity(prod.getStockQuantity() - cartItem.getQuantity());
			productService.saveProduct(prod);
			list.add(cartItem);
		} else {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getProductId().equals(cartItem.getProductId())) {
					int curr = list.get(i).getQuantity();
					prod.setStockQuantity(prod.getStockQuantity() + curr - cartItem.getQuantity());
					productService.saveProduct(prod);
					list.set(i, cartItem);
					break;
				}
			}
		}
		cart.setCartItems(list);
		cart.totalAmountAndQuantity();
		return cartRepository.save(cart);
	}

	public Cart deleteItem(String userId, String productId) throws NotFoundException {
		userService.findUser(userId).orElseThrow(() -> new NotFoundException());
		Product prod = productService.findProduct(productId).orElseThrow(() -> new NotFoundException());
		Cart cart = this.findByUserId(userId).orElseThrow();
		List<CartItem> list = cart.getCartItems();
		for (CartItem cartItem : cart.getCartItems()) {
			if (cartItem.getProductId().equals(productId)) {
				list.remove(cartItem);
				prod.setStockQuantity(prod.getStockQuantity() + cartItem.getQuantity());
				productService.saveProduct(prod);
				cart.setCartItems(list);
				return cartRepository.save(cart);
			}
		}
		return null;
	}

	public void delete(String userId) throws NotFoundException {
		userService.findUser(userId).orElseThrow(() -> new NotFoundException());
		Cart cart = this.findByUserId(userId).orElseThrow();
		for (CartItem cartItem : cart.getCartItems()) {
			Product prod = productService.findProduct(cartItem.getProductId())
					.orElseThrow(() -> new NotFoundException());
			prod.setStockQuantity(prod.getStockQuantity() + cartItem.getQuantity());
			productService.saveProduct(prod);
		}
		cartRepository.deleteByUserId(userId);
	}

	public String checkoutCart(String userId) throws NotFoundException {

		Cart cart = cartRepository.findByUserId(userId).orElseThrow(() -> new NotFoundException());

		if (cart.getCartItems() == null || cart.getCartItems().isEmpty()) {
			return "Cart is empty";
		}

		Order order = new Order();
		order.setUserId(userId);

		List<OrderItem> orderItems = new ArrayList<>();
		for (CartItem cartItem : cart.getCartItems()) {
			OrderItem orderItem = new OrderItem();
			orderItem.setProductId(cartItem.getProductId());
			orderItem.setQuantity(cartItem.getQuantity());
			orderItem.setPriceAtPurchase(cartItem.getPrice());
			orderItems.add(orderItem);
		}
		order.setOrderItems(orderItems);
		order.totalAmountAndQuantity();

		orderService.createOrder(order);

		// clear cart
		cart.getCartItems().clear();
		cart.totalAmountAndQuantity();
		cartRepository.save(cart);

		return "Order created";
	}
}
