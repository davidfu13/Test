import java.util.Scanner;
import java.io.FileNotFoundException;

public class AutoCompletionTest {
	public static void main(String[] args) throws FileNotFoundException {
		Dictionary dict = new Dictionary();
		dict.loadDictionary("tinyDictionary.txt");
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter prefix");
		while (sc.hasNext()) {
			Iterable<String> res = dict.wordsWithPrefix(sc.next());
			for (String s : res)
				System.out.println(s);
			System.out.println("\nPlease enter prefix");
		}
		
	}
}
