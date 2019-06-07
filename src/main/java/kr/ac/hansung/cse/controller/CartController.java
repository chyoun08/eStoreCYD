package kr.ac.hansung.cse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.ac.hansung.cse.model.User;
import kr.ac.hansung.cse.service.UserService;

@Controller
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private UserService userService;
	
	@RequestMapping
	public String getCart(Model model) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		
		User user = userService.getUserByName(username);
		int cartId = user.getCart().getId();
		
		model.addAttribute("cartId",cartId);
		
		System.out.println("이것은 "+ username + "의 " +"카트의 아이디는 " + cartId + "입니다.");
		
		return "cart";
	}
}
