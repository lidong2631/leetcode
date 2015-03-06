import java.util.*;

public class CircusTest {
	public static void main(String[] args) {
		Circus c = new Circus();
		ArrayList<HtWt> list = new ArrayList<HtWt>();
		list.add(new HtWt(65,100));
		list.add(new HtWt(70,150));
		list.add(new HtWt(60,90));
		list.add(new HtWt(75,190));
		list.add(new HtWt(60,90));
		list.add(new HtWt(68,110));

		System.out.println("original sequence:");
		for(HtWt h : list)
			h.Print();
		System.out.println();

		ArrayList<HtWt> res = c.findMaxSeq(list);
		
		System.out.println("final result:");
		for(HtWt h : res)
			h.Print();
	}
}