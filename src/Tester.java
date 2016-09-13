import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


//because the position is just relative to the scanner as it scans through a document, we can assume they're all 
//in sorted order anyway in the index?
public class Tester {

	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("chapter1.txt");
		Scanner sc = new Scanner (file);
		Index corpus = new Index();
		int i = 0;
		while (sc.hasNext()) {
			corpus.addTerm(sc.next(), 1, i);
			i++;
		}
		System.out.println(corpus.near("will", "with", 4));
		for (String a: corpus.getDictionary())
			System.out.println(a + corpus.getPositionPostings(a));
	}

}
