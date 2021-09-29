package fa.training.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Store")
public class Store {
	
	@Id
	@Column(name = "store_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "store_name")
	private String storeName;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "is_available")
	private boolean isAvailable;
	
	@OneToMany(mappedBy = "store",cascade = CascadeType.ALL)
	private Set<Order> orders = new HashSet<Order>();
	
	public Store() {
	}

	public Store(String storeName, String address, boolean isAvailable) {
		super();
		this.storeName = storeName;
		this.address = address;
		this.isAvailable = isAvailable;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	@Override
	public String toString() {
		return "Store [id=" + id + ", storeName=" + storeName + ", address=" + address + ", isAvailable=" + isAvailable
				+ "]";
	}

}
