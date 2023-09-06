package backend4413.dev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend4413.dev.model.Cart;
import backend4413.dev.model.CartItem;
import backend4413.dev.service.CartService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/cart/{userId}")
public class CartController {

	@Autowired
	private CartService cartService;

	@GetMapping
	public Cart getCart(@PathVariable String userId) throws NotFoundException {
		return cartService.findByUserId(userId).orElseThrow(() -> new NotFoundException());
	}

	@PostMapping
	public void insertCart(@PathVariable(value = "userId", required = true) String userId,
			@RequestBody(required = true) CartItem cartItem) throws NotFoundException {
		cartService.insert(userId, cartItem);
	}

	@PutMapping
	public void updateCart(@PathVariable(value = "userId", required = true) String userId,
			@RequestBody(required = true) CartItem cartItem) throws NotFoundException {
		cartService.save(userId, cartItem);
	}

	@DeleteMapping
	public void deleteCart(@PathVariable(value = "userId", required = true) String userId) throws NotFoundException {
		cartService.delete(userId);
	}
	
	@DeleteMapping(value = "/{productId}")
	public Cart deleteItem(@PathVariable(value = "userId", required = true) String userId, @PathVariable(value = "productId", required = true) String productId) throws NotFoundException {
		return cartService.deleteItem(userId, productId);
	}

	@PostMapping(value = "/checkout")
	public String checkout(@PathVariable(value = "userId", required = true) String userId) throws NotFoundException {
		return cartService.checkoutCart(userId);
	}

}
