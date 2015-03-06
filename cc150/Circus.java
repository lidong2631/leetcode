import java.util.*;

public class Circus {
	
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
				if(i==start || originalSeq.get(i-1).isBefore(originalSeq.get(i)))
					subSeq.add(originalSeq.get(i));
				else
					firstUnfit = i;		//如果出现不符合顺序的话记录下第一次出现的位置 然后还要继续循环因为后面的值可能还符合顺序 所以也要加到seq中
			}
		}
		return firstUnfit;
	}

	public ArrayList<HtWt> findMaxSeq(ArrayList<HtWt> originalSeq) {
		
		Collections.sort(originalSeq);
		
		System.out.println("After sort:");
		for(HtWt h : originalSeq)
			h.Print();
		System.out.println();

		int currUnfit = 0;
		max = new ArrayList<HtWt>();

		while(currUnfit<originalSeq.size())
		{
			ArrayList<HtWt> subSeq = new ArrayList<HtWt>();
			
			int nextUnfit = findNextUnfit(currUnfit, originalSeq, subSeq);	//得到第一次unfit的位置
			
			max = maxSeq(max, subSeq);
			
			if(nextUnfit==currUnfit)		//如果没变说明没找到unfit的位置 如所有htwt都是高度重量排好序的 这时应该直接break 否则会infinite loop
				break;
			else 
				currUnfit = nextUnfit;
//System.out.println("currUnfit = " + currUnfit);
		}
		return max;
	}
}