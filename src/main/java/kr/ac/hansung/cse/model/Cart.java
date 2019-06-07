package kr.ac.hansung.cse.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class Cart implements Serializable {
	
	private static final long serialVersionUID = -8384315056776654269L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
//	실질적으로 생성되진않는column이다.
	@OneToMany(mappedBy="cart", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private List<CartItem> cartItems = new ArrayList<CartItem>();
	
	private double grandTotal;
}

/*SerialIzation
 *						 -> File 	->
 * Object -> Byte Stream -> DB		-> Byte Stream -> Object
 						 -> Memory	->
 */