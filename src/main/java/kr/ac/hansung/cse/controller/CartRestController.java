package kr.ac.hansung.cse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.hansung.cse.model.Cart;
import kr.ac.hansung.cse.model.CartItem;
import kr.ac.hansung.cse.model.Product;
import kr.ac.hansung.cse.model.User;
import kr.ac.hansung.cse.service.CartItemService;
import kr.ac.hansung.cse.service.CartService;
import kr.ac.hansung.cse.service.ProductService;
import kr.ac.hansung.cse.service.UserService;

//Controller와 ResponseBody를 같있는 어노테이션
@RestController
@RequestMapping("/api/cart")
public class CartRestController {

	@Autowired
	private CartService cartService;
	
	@Autowired
	private CartItemService cartItemService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProductService productService;
	
//	카드정보 조회 앞에 /api/cart가 붙는다.
	@RequestMapping(value = "/{cartId}", method = RequestMethod.GET)
	public ResponseEntity<Cart> getCartById(@PathVariable(value="cartId") int cartId){
		Cart cart = cartService.getCartById(cartId);
		HttpHeaders headers = new HttpHeaders();
		headers.setCacheControl("max-age=10");
		System.out.println("max-age=10 호출");
//		최종적으로 받는 내용을 Body부분에 붙여 전송
		return new ResponseEntity<Cart>(cart, headers, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{cartId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> clearCart(@PathVariable(value="cartId") int cartId){
		Cart cart = cartService.getCartById(cartId);
		cartItemService.removeAllCartItems(cart);
		
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value = "/add/{productId}", method = RequestMethod.PUT)
	public ResponseEntity<Void> addItem(@PathVariable(value="productId") int productId){
		
		Product product = productService.getProductById(productId);
		
//		현제 인증된 사용자를 객체값으로 가져옴 이를 쓰기위헤 sercurity-context.xml에 추가
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		
		User user = userService.getUserByName(username);
		Cart cart = user.getCart();
		List<CartItem> cartItems = cart.getCartItems();
		
		for(int i=0;i<cartItems.size();i++) {
			if(product.getId()==cartItems.get(i).getProduct().getId()) {
				CartItem cartItem = cartItems.get(i);
				if(cartItem.getQuantity()<product.getUnitInStock()) {
					cartItem.setQuantity(cartItem.getQuantity()+1);
					cartItem.setTotalPrice(product.getPrice()*cartItem.getQuantity());
					cartItemService.addCartItem(cartItem);
					return new ResponseEntity<>(HttpStatus.OK);
				}else {
					return new ResponseEntity<>(HttpStatus.ACCEPTED);
				}
			}
		}
		
		CartItem cartItem = new CartItem();
		cartItem.setQuantity(1);
		cartItem.setTotalPrice(product.getPrice()*cartItem.getQuantity());
		cartItem.setProduct(product);
		cartItem.setCart(cart);
		
		cart.getCartItems().add(cartItem);		
		cartItemService.addCartItem(cartItem);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/cartItem/{productId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> removeItem(@PathVariable(value="productId") int productId){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		
		User user = userService.getUserByName(username);
		Cart cart = user.getCart();
		
		CartItem cartItem = cartItemService.getCartItemByProductId(cart.getId(), productId);
		cartItemService.removeCartItem(cartItem);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	//한유저의 카트아이템에 해당 아이템 제거
	@RequestMapping(value = "/revise/cartItem/{productId}", method = RequestMethod.PUT)
	public ResponseEntity<Void> removeUserItem(@PathVariable(value="productId") int productId){
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		
		Product product = productService.getProductById(productId);
		
		User user = userService.getUserByName(username);
		Cart cart = user.getCart();		
		
		List<CartItem> cartItems = cart.getCartItems();
		
		for(int i=0;i<cartItems.size();i++) {
			if(product.getId()==cartItems.get(i).getProduct().getId()) {
				CartItem cartItem = cartItems.get(i);
				if(cartItem.getQuantity()>1) {
					cartItem.setQuantity(cartItem.getQuantity()-1);
					cartItem.setTotalPrice(product.getPrice()*cartItem.getQuantity());
					cartItemService.addCartItem(cartItem);
					return new ResponseEntity<>(HttpStatus.OK);
				}else {
					cartItemService.removeCartItem(cartItem);
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				}
			}
		}
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
