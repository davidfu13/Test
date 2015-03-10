import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class DeliverRecommender {

	private ArrayList<Product> products = new ArrayList<Product>();
	private int[] avaiDeliverMethodCnt = new int[Product.numOfDiffDeliverMethod];

	public DeliverRecommender() {
	}
	
	public void loadProducts(String filename) throws FileNotFoundException {
		// Clear all the old products and deliver method count
		clear();		
		
		Scanner sc = new Scanner(new File(filename));
		int numOfProducts = sc.nextInt();
		for (int i = 0; i < numOfProducts; ++i) {
			int id = sc.nextInt();
			String name = sc.next();
			int price = sc.nextInt();
			int numOfMethods = sc.nextInt();
			ArrayList<Integer> methods = new ArrayList<Integer>();
			for (int j = 0; j < numOfMethods; ++j) {
				String method = sc.next();
				if 		(method.compareTo("IN_PERSON") == 0)	methods.add(Product.IN_PERSON);
				else if (method.compareTo("EXPRESS") == 0)		methods.add(Product.EXPRESS);
				else if (method.compareTo("7_11") == 0)			methods.add(Product.D7_11);
				else if (method.compareTo("POST") == 0)			methods.add(Product.POST);
			}
			Product p = new Product(id, name, price, methods);
			if (p.isLegalProduct())	products.add(p);
			else {
				System.out.println("The number of deliver methods is < 1 or > 3, "
									+ "this product is illegal and will not be considered");
			}
		}		
	}
	
	public String getRecommendedMethod() {
		getAvaiDeliverMethodCnt();
		StringBuilder ret = new StringBuilder("");
		int method = Product.IN_PERSON;
		int max = avaiDeliverMethodCnt[Product.IN_PERSON];
		for (int i = 1; i < Product.numOfDiffDeliverMethod; i++) {
			if (avaiDeliverMethodCnt[i] > max) {
				method = i;
				max = avaiDeliverMethodCnt[i]; 
			}
		}
		String recommendedMethod;
		switch (method) {
			case Product.IN_PERSON:
				recommendedMethod = "In person";
				break;
			case Product.EXPRESS:
				recommendedMethod = "Express";
				break;
			case Product.D7_11:
				recommendedMethod = "7-11";
				break;
			case Product.POST:
				recommendedMethod = "Post";
				break;
			default:
				recommendedMethod = "Undefined error";
		}
		ret.append("Deliver method recommended: " + recommendedMethod + "\n");
		if (max < products.size()) {
			ret.append("However, the following products can't be delivered by this method:\n");
			for (Product p : products)
				if (!p.isDeliverMethod(method))
					ret.append("Product ID: " + p.getId() + "\n");	
			}
		
		return ret.toString();
	}
	
	public void clear() {
		products.clear();	
		for (int i = 0; i < Product.numOfDiffDeliverMethod; ++i)
			avaiDeliverMethodCnt[i] = 0;
	}

	private void getAvaiDeliverMethodCnt() {
		for (Product p : products) {
			for (int i = 0; i < Product.numOfDiffDeliverMethod; ++i)
				if (p.isDeliverMethod(i))
					avaiDeliverMethodCnt[i]++;
		}
	}
	
}
