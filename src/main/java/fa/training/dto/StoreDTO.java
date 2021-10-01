package fa.training.dto;

import fa.training.entities.Store;

public class StoreDTO {
	
	private int id;
	private String storeName;
	private String address;
	private boolean isAvailable;
	
	public StoreDTO() {}

	public StoreDTO(String storeName, String address, boolean isAvailable) {
		super();
		this.storeName = storeName;
		this.address = address;
		this.isAvailable = isAvailable;
	}
	
	public StoreDTO(Store entity) {
		this.address = entity.getAddress();
		this.id = entity.getId();
		this.isAvailable = entity.isAvailable();
		this.storeName = entity.getStoreName();
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
