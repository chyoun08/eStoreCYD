package kr.ac.hansung.cse.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import kr.ac.hansung.cse.model.Cart;
import kr.ac.hansung.cse.model.CartItem;

@Repository
@Transactional
public class CartItemDao {

	@Autowired
	private SessionFactory sessionFactory;

	public void addCartItem(CartItem cartItem) {

		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(cartItem);
		session.flush(); // 자동적으로 이루어지기때문에 안넣어줘도된다.

	}

	public void removeCartItem(CartItem cartItem) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(cartItem);
		session.flush();
	}
	
//	Cart클래스에서 Cart를 읽을때 CartItem클래스를 같이 읽기위해 cartItems의 fetch타입이 EAGER줬기때문에
//	자동적으로 DB에 cartItems가 넘어가기때문에 아래와 같이사용됬을때 CartItems가 불려진다 LAZY일경우 NULL값이 읽힌다.
	public void removeAllCartItems(Cart cart) {
		List<CartItem> cartItems = cart.getCartItems();
		for(CartItem item : cartItems) {
			removeCartItem(item);
		}
	}

	@SuppressWarnings("unchecked")
	public CartItem getCartItemByProductId(int cartId, int productId) {
		Session session = sessionFactory.getCurrentSession();
		TypedQuery<CartItem> query = session.createQuery("from CartItem where cart.id=?0 and product.id =?1");
		query.setParameter(0, cartId);
		query.setParameter(1, productId);
		
		return (CartItem) query.getSingleResult();
	}
}
