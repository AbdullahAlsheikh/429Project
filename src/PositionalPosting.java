import java.util.ArrayList;
import java.util.List; 
import java.util.HashMap;
import java.util.Set;
/**
 * @author Richard
 *
 */
public class PositionalPosting {

	private HashMap <Integer, List<Integer>> positionalPosting;

	/**
	 * Default positional posting; instantiates null hash map
	 */
	public PositionalPosting () {
		positionalPosting = new HashMap <Integer, List<Integer>>();
	}
	/**
	 * 
	 * @param doc
	 * The integer representation of a document
	 * @param position 
	 * the position a term was found in
	 */
	public void addPosting (int doc, int position) {
		if (!(positionalPosting.containsKey(doc))) {
			ArrayList<Integer> temp = new ArrayList<Integer>();
			temp.add(position);
			positionalPosting.put(doc, temp);
		}
		
		else {
			if (!(positionalPosting.get(doc).contains(position)))
				positionalPosting.get(doc).add(position); 
		}
	}
	
	/**
	 * Method for retrieveing a set of all documents a term is found in
	 * @return set of documents term is found in
	 */
	public Set <Integer> getDocs () {
		return positionalPosting.keySet();
	}
	
	
	public String toString() {
		return "Found in documents " + positionalPosting.keySet().toString() + ":\n" + positionalPosting.values().toString();
	}
	
	/**
	 * Will check if a term comes within k of another term
	 * @param posting the posting to compare to
	 * @param k the distance from said term
	 * @return BOOLEAN (True or false)
	 */
	public Set<Integer> near(PositionalPosting posting, int k) {
		Set <Integer> resultSet = this.positionalPosting.keySet();
		Set <Integer> compareSet = posting.getDocs();
		for (int i: resultSet) {
			if (!(compareSet.contains(i)))
				resultSet.remove(i);
		}
		return resultSet;
		
	}
}
