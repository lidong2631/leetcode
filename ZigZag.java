public class ZigZag {
    public static void main(String[] args) {
        ZigZag z = new ZigZag();
        String s = z.convert("abcde", 3);
        System.out.println(s);
    }

    public String convert(String s, int nRows) {
        if(s==null || s.length()==0 || nRows==0)
            return null;
        if(nRows==1)
            return s;
        StringBuilder[] sb = new StringBuilder[nRows];
        for(int i=0; i<nRows; i++)
            sb[i] = new StringBuilder("");  //the whole point is here you need to initialize each StringBuilder manually or it will be null
        int index = -1;
        int step = 1;
        for(int i=0; i<s.length(); i++)
        {
            index+=step;
            if(index==nRows)
            {
                index = nRows-2;
                step = -1;
            }
            if(index==-1)
            {
                index = 1;
                step = 1;
            }
            sb[index].append(s.charAt(i));
        }
        StringBuilder res = new StringBuilder();
        for(int i=0; i<sb.length; i++)
            res.append(sb[i].toString());
        return res.toString();
    }
}