import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Iterator;
import java.util.ArrayList;

public class Dictionary {
	private static final int R = 26;

	private Node root;

	private static class Node {
		private Node[] next = new Node[R];		// 'a' to 'z'
		private boolean isWord;
		private int searchHitCnt;
	}

	public Dictionary() {
	}

	public void loadDictionary(String filename) throws FileNotFoundException {
		clear();
		Scanner sc = new Scanner(new File(filename));
		while (sc.hasNext()) {
			add(sc.next());
		}
	}

	public Iterable<String> wordsWithPrefix(String prefix) {
		ArrayList<String> res = new ArrayList<String>();
		Node x = get(root, prefix, 0);
		traverse(x, new StringBuilder(prefix), res);

		return res;
	}

	public void clear() {
		root = null;	
	}

	private Node get(Node x, String word, int d) {
		if (x == null) return null;
		if (d == word.length()) return x;
		char c = word.charAt(d);
		return get(x.next[c-'a'], word, d+1);
	
	}
	private void traverse(Node x, StringBuilder prefix, ArrayList<String> res) {
		if (x == null) return;
		if (x.isWord) res.add(prefix.toString());
		for (char c = 0; c < R; c++) {
			char c2 = (char) ('a' + c);
			prefix.append(c2);
			traverse(x.next[c], prefix, res);
			prefix.deleteCharAt(prefix.length() - 1);
		}
	}

	private void add(String word) {
		root = add(root, word, 0);
	}

	private Node add(Node x, String word, int d) {
		if (x == null) x = new Node();
		if (d == word.length()) {
			x.isWord = true;
		}
		else {
			char c = word.charAt(d);
			x.next[c-'a'] = add(x.next[c-'a'], word, d+1);
		}
		return x;
	}

	

}
