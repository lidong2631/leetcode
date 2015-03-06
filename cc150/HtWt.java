public class HtWt implements Comparable<Object> {
	private int ht;
	private int wt;

	public HtWt(int ht, int wt) {
		this.ht = ht;
		this.wt = wt;
	}

	public boolean isBefore(HtWt htwt) {
		return (this.ht < htwt.ht && this.wt < htwt.wt);
	}

	public int compareTo(Object o) {
		HtWt h = (HtWt)o;
		if(ht>h.ht)
			return 1;
		else if(ht<h.ht)
			return -1;
		else
		{
			if(wt>h.wt)
				return 1;
			else if(wt<h.wt)
				return -1;
			return 0;
		}
	}

	public void Print()
	{
		System.out.println("("+ht+","+wt+")");
	}
}