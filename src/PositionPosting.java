
public class PositionPosting implements Comparable<PositionPosting> {
	
	private int document;
	private int position;
	
	public PositionPosting(int a, int b) {
		document = a;
		position = b;
	}
	
	public int getDocument () {
		return document;
	}
	
	public int getPosition () {
		return position;
	}

	public boolean equals (Object other) {
		if (!(other instanceof PositionPosting))
			return false;
		PositionPosting p = (PositionPosting) other;
		if (this.document != p.getDocument() || this.position != p.getPosition())
			return false;
		return true;
	}

	/**
	 * Will compare position postings by document; if equal, then by position (bigger number = +1)
	 */
	public int compareTo(PositionPosting other) {
		if (document == other.getDocument())
			return position - other.getPosition();
		return document - other.getDocument();//fix this shit
	}
	
	/**
	 * Will check if a position posting is within distance of another
	 * @param other 
	 * @param k
	 * @return
	 */
	public boolean near (PositionPosting other, int k) {
		if (document != other.getDocument())
			return false;
		return (Math.abs(position - other.getPosition()) <= k);

	}
		
	public String toString () {
		return document + "|" + position;
	}

}