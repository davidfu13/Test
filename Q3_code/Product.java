public class Product {
	public static final int numOfDiffDeliverMethod = 4;
	public static final int IN_PERSON = 0;
	public static final int EXPRESS = 1;
	public static final int D7_11 = 2;
	public static final int POST = 3;

	private int _id;
	private String _name;
	private int _price;
	// 0 -> in person, 1 -> express, 2 -> 7-11, 3 -> post
	// Arranged according to deliver methods' priority
	private boolean[] _isDeliverMethod = new boolean[numOfDiffDeliverMethod]; 
	private int avaiDeliverMethodCnt;
	
	public Product() {
	}

	public Product(int id, String name, int price, Iterable<Integer> deliverMethods) {
		_id = id;
		_name = name;
		_price = price;
		for (int i : deliverMethods)
			setDeliverMethod(i);
	}

	public boolean isLegalProduct() {
		return (avaiDeliverMethodCnt >= 1 && avaiDeliverMethodCnt <=3) ? true : false;
	}

	public int getId() {
		return _id;
	}

	public String getName() {
		return _name;
	}
	
	public int getPrice() {
		return _price;
	}
	
	public boolean isDeliverMethod(int i) {
		return _isDeliverMethod[i];
	}

	private void setDeliverMethod(int i) {
		if (!_isDeliverMethod[i]) {
			_isDeliverMethod[i] = true;
			avaiDeliverMethodCnt++;
		}
	}

}
