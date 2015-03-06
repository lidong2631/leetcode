public class HtWt implements Comparable<Object> {
	int ht;
	int wt;

	public HtWt(int ht, int wt) {
		this.ht = ht;
		this.wt = wt;
	}

	public boolean isBefore(HtWt htwt) {
		return (this.ht < htwt.ht && this.wt < htwt.wt);
	}

	public int compareTo(Object o) {
		HtWt h = (HtWt)o;
		return this.ht!=h.ht ? this.ht.compareTo(h.ht) : this.wt.compareTo(h.wt);
	}
}

public class Solution {
	
	private ArrayList<HtWt> max;

	public ArrayList<HtWt> maxSeq(ArrayList<HtWt> seq1, ArrayList<HtWt> seq2) {
		return seq1.size() > seq2.size() ? seq1:seq2;
	}

	public int findNextUnfit(int start, ArrayList<HtWt> originalSeq, ArrayList<HtWt> subSeq) {
		
		int firstUnfit = start;

		if(start<originalSeq.size())
		{
			for(int i=start; i<originalSeq.size(); i++)		//
			{
				if(i==start || originalSeq.get(i-1).isBefore(originalSeq.get(i)))	//
					subSeq.add(item);
				else
					firstUnfit = i;		//如果出现不符合顺序的话记录下第一次出现的位置 然后还要继续循环因为后面的值可能还符合顺序 所以也要加到seq中
			}
		}
		return firstUnfit;
	}

	public void findMaxSeq(ArrayList<HtWt> originalSeq) {
		
		//Collections.sort(originalSeq);
		int currUnfit = 0;
		
		while(currUnfit<originalSeq.size())
		{
			ArrayList<HtWt> subSeq = new ArrayList<HtWt>();
			int nextUnfit = findNextUnfit(currUnfit, originalSeq， subSeq);	//得到第一次unfit的位置
			
			max = maxSeq(max, subSeq);
			
			currUnfit = nextUnfit;	//
		}
		return max;
	}
}

Note: 这题我跟答案有些不同 不理解答案为什么那么写 看我自己写的程序 Circus.java, CircusTest.java, HtWt.java

顺便看下LIS问题的求解 

http://stackoverflow.com/questions/2631726/how-to-determine-the-longest-increasing-subsequence-using-dynamic-programming