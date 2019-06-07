package kr.ac.hansung.cse.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import kr.ac.hansung.cse.model.User;

@Repository
@Transactional
public class UserDao {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<User> getAllUsers(){
		Session session = sessionFactory.getCurrentSession();
		TypedQuery<User> query = session.createQuery("from User");
		List<User> userList = query.getResultList();
		
		return userList;
	}
	
	public User getUserById(int userId) {
		Session session = sessionFactory.getCurrentSession();
		User user = (User) session.get(User.class, userId);

		return user;
	}
	
	@SuppressWarnings("unchecked")
	public User getUsername(String username) {
		Session session = sessionFactory.getCurrentSession();
		TypedQuery<User> query = session.createQuery("from User where username = ?0");
		query.setParameter(0, username);
		
		return query.getSingleResult();
	}

	public void addUser(User user) {

		Session session = sessionFactory.getCurrentSession();
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		session.saveOrUpdate(user);
		session.flush(); // 자동적으로 이루어지기때문에 안넣어줘도된다.

	}

	public void deleteUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(user);
		session.flush();
	}

	public void updateUser(User user) {

		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(user);
		session.flush(); // 자동적으로 이루어지기때문에 안넣어줘도된다.

	}
}
