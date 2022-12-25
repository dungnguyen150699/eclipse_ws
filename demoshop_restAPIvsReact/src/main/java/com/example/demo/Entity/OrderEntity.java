package com.example.demo.Entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "dateOrder")
	private Date dateOrder;
	
	@Column(name = "shipe_method")
	private String ship_method;
	
	@Column(name = "apporved")
	private int approved = 0 ;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL , fetch = FetchType.EAGER)
	private List<OrderDetailEntity> orderDetails = new ArrayList();
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private UserEntity user;

}
