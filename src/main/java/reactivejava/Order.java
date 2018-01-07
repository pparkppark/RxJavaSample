package reactivejava;

public class Order {

	private String id;

	public Order(String id) {
		super();
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "OrderId = " + id;
	}
}
