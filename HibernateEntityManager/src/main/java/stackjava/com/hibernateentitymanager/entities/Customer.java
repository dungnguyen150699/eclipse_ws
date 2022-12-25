package stackjava.com.hibernateentitymanager.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "address")
	private String address;
	
	@PrePersist
	public void prePersist() {
		System.out.println("pre persist!");
	}
	@PostPersist
	public void postPersist() {
		System.out.println("post persist!");
	}
	@PreUpdate
	public void preUpdate() {
		System.out.println("pre update!");
	}
	@PostUpdate
	public void postUpdate() {
		System.out.println("post update!");
	}
	@PreRemove
	public void preRemove() {
		System.out.println("pre remove!");
	}
	@PostRemove
	public void postRemove() {
		System.out.println("post remove!");
	}
	public Customer() {
	}

	public Customer(String name, String address) {
		this.name = name;
		this.address = address;
	}
	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", address=" + address + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}