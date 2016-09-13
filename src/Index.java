import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.List; 

public class Index {

	private Map<String, List<PositionPosting>> Index;
	
	public Index () {
		Index = new HashMap<String, List<PositionPosting>>();
	}
	
	public void addTerm (String term, int doc, int position) {
		if (!(Index.containsKey(term))) {
			List <PositionPosting> temp = new ArrayList<PositionPosting>();
			temp.add(new PositionPosting(doc, position));
			Index.put(term, temp);
		}
		
		else  { 
			if (!(Index.get(term).contains(new PositionPosting(doc, position))))//irrelevant unless code is being deliberately broken
				Index.get(term).add(new PositionPosting(doc, position));	
		}
	}
	
	public List<PositionPosting> getPositionPostings (String term) {
		if (!(Index.containsKey(term))) {
			System.out.println(term + " not in corpus");
			return null;
		}
		return Index.get(term);
	}
	/**
	 * Will return all the terms in the corpus
	 * @return an array of all terms in the corpus in sorted order
	 */
	public String [] getDictionary () {
		String [] words = new String[Index.size()];
		int i = 0;
		for (String a: Index.keySet()) {
			words[i] = a;
			i++;
		}
		Arrays.sort(words);
		return words;
	}
	
	/**
	 * 
	 * @param term1
	 * @param term2
	 * @param k
	 * @return
	 */
	public boolean near (String term1, String term2, int k) throws IndexOutOfBoundsException {
		List <PositionPosting> positions = Index.get(term1);
		List <PositionPosting> compareList = Index.get(term2);
		PositionPosting pointer1;
		PositionPosting pointer2;
		int i = 0;
		int j = 0;
		pointer1 = positions.get(i);
		pointer2 = compareList.get(j);
		try {
			while (true) {
				System.out.println(pointer1 + " " + pointer2);
				if (pointer1.near(pointer2, k))
					return true;
				else {
					int comparison = pointer1.compareTo(pointer2);
						if (comparison < 0)
							pointer1 = positions.get(++j);
						else if (comparison > 0)
							pointer2 = compareList.get(++i);
	
						else if (comparison == 0) {
							pointer1 = positions.get(++i);
							pointer2 = compareList.get(++j);
						}
					}
				}
			}
		catch (IndexOutOfBoundsException e) {
				System.out.println(term1 + " is not found within those limits of " + term2);
			}
		return false;
		}
	}

