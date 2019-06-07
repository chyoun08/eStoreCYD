package kr.ac.hansung.cse.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
//import javax.validation.constraints.NotEmpty;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "users")
public class User {
//여기서 table이름을 users라고 주는 이유! security-context.xml에 검색하는곳을 users라고 줬기때무에 이넘도 맞춰서 users라고 줘야한다.
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="userId")
	private int id;
	
	@NotEmpty(message="The username must not be null")
	private String username;
	
	@NotEmpty(message="The password must not be null")
	private String password;
	
	@NotEmpty(message="The Email must not be null")
	private String email;
	
//	opntional이 false이기때문에 반드시 입력되어야하는 값이다.
//	1대1관계이기때문에 unique의 관계가 true값이다.
	@OneToOne(optional=false, cascade=CascadeType.ALL)
	@JoinColumn(unique=true)
	private ShippingAddress shippingAddress;
	
	@OneToOne(optional=false, cascade=CascadeType.ALL)
	@JoinColumn(unique=true)
	private Cart cart;
	
	private boolean enabled = false;
	
	private String authority;
}
