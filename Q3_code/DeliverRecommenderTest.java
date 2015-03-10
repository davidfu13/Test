import java.io.FileNotFoundException;

public class DeliverRecommenderTest {

	public static void main(String[] args) throws FileNotFoundException {
		DeliverRecommender dr = new DeliverRecommender();
		// test data 1: majority is IN_PERSON
		// one of the product's delivering methods are illegal.
		String fn = "testData/test1.txt";
		dr.loadProducts(fn);
		System.out.println("Result for " + fn + " is:");
		System.out.println(dr.getRecommendedMethod());
		
		// test data 2: majority is IN_PERSON and EXPRESS
		// should output IN_PERSON because of higher priority
		fn = "testData/test2.txt";
		dr.loadProducts(fn);
		System.out.println("Result for " + fn + " is:");
		System.out.println(dr.getRecommendedMethod());
		
		// test data 3: majority is EXPRESS
		// one of the product can't be delivered by EXPRESS
		fn = "testData/test3.txt";
		dr.loadProducts(fn);
		System.out.println("Result for " + fn + " is:");
		System.out.println(dr.getRecommendedMethod());
		
		// test data 4: majority is EXPRESS, 7_11 and POST
		// should output EXPRESS because of higher priority
		// two of the product can't be delivered by EXPRESS
		fn = "testData/test4.txt";
		dr.loadProducts(fn);
		System.out.println("Result for " + fn + " is:");
		System.out.println(dr.getRecommendedMethod());

		// test data 5: majority is IN_PERSON, EXPRESS, 7_11 and POST
		// should output IN_PERSON because of higher priority
		// each product only has one delivering method
		// 3 out of 4 products can't be delivered by IN_PERSON
		fn = "testData/test5.txt";
		dr.loadProducts(fn);
		System.out.println("Result for " + fn + " is:");
		System.out.println(dr.getRecommendedMethod());
		
	}

}
