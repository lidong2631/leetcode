public class WordDistance {

    Map<String, List<Integer>> map;

    public WordDistance(String[] words) {
        map = new HashMap<String, List<Integer>>();
        for(int i=0; i<words.length; i++) {
            if(map.containsKey(words[i]))
                map.get(words[i]).add(i);
            else {
                List<Integer> list = new ArrayList<Integer>();
                list.add(i);
                map.put(words[i], list);
            }
        }
    }

    public int shortest(String word1, String word2) {
        List<Integer> l1 = map.get(word1);
        List<Integer> l2 = map.get(word2);
        int shortest = Integer.MAX_VALUE;
        for(int i=0, j=0; i<l1.size() && j<l2.size(); ) {   // find minimum distance between two sorted array. related topic: closest value in sorted array (binary search)
            int index1 = l1.get(i);
            int index2 = l2.get(j);
            if(index1<index2) {
                shortest = Math.min(shortest, index2-index1);
                i++;
            }
            else {
                shortest = Math.min(shortest, index1-index2);
                j++;
            }
        }
        return shortest;
    }
}

// Your WordDistance object will be instantiated and called as such:
// WordDistance wordDistance = new WordDistance(words);
// wordDistance.shortest("word1", "word2");
// wordDistance.shortest("anotherWord1", "anotherWord2");


https://leetcode.com/discuss/50190/java-solution-using-hashmap