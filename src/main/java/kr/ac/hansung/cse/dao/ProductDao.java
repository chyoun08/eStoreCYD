package kr.ac.hansung.cse.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import kr.ac.hansung.cse.model.Product;

@Repository
@Transactional
public class ProductDao {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public List<Product> getProducts() {
		
		Session session = sessionFactory.getCurrentSession();
		TypedQuery<Product> query = session.createQuery("from Product");
		List<Product> products = query.getResultList();
		
		return products;
	}
	
	public Product getProductById(int id) {
		Session session = sessionFactory.getCurrentSession();
		Product product = (Product) session.get(Product.class, id);

		return product;
	}

	public void addProduct(Product product) {

		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(product);
		session.flush(); // 자동적으로 이루어지기때문에 안넣어줘도된다.

	}

	public void deleteProduct(Product product) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(product);
		session.flush();
	}

	public void updateProduct(Product product) {

		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(product);
		session.flush(); // 자동적으로 이루어지기때문에 안넣어줘도된다.

	}

}