package stream.order;

class Order {
	
	private String id;
	private String creationDate;
	

	public Order(String id, String creationDate) {
		this.id = id;
		this.creationDate = creationDate;
	}

	public String getId() {
		return id;
	}

	public String getCreationDate() {
		return creationDate;
	}

}
