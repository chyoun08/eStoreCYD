package kr.ac.hansung.cse.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name="product")
public class Product implements Serializable {
	
	private static final long serialVersionUID = -6047829228948525963L;

//	IDENTITY값이 들어가면 hibernate_sequence란 테이블이 따로만들어지진않는다.
//	GenerationType을 AUTO를 줄경우 Hibernate가 알아서 다른 값들인 IDENTITY,SEQUENCE,TABLE을 할당해준다.
//	MySQL에서의 Default값은 TABLE이다. 오토의경우 테이블생성 테이블이 시퀀스넘버저장 그것을 바탕으로 primery키 할당해줌
//	@Column(name="product_id")
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="product_id")
	private int id;
	
//	@Column(name="product_name")
	@NotEmpty(message = "The product name must not be null")
	private String name;
	
//	@Column
	private String category;
	
	@Min(value=0, message = "The product price must not be less than zero")
	private int price;
	
	@NotEmpty(message = "The product manufacture must not be null")
	private String manufacturer;
	
	@Min(value=0, message = "The product price must not be less than zero")
	private int unitInStock;
	
	private String description;
	
//	실질적인 파일들의 이름,사이즈 등등의 정보들을 가지고있다.
	@Transient
	private MultipartFile productImage;
	
//	DB엔 이미지파일은 저장되지않는다 이미지파일이있는 경로만을 저장한다.
	private String imageFileName;
}
