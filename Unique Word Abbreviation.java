public class ValidWordAbbr {

    private Map<String, Set<String>> map;
    
    public ValidWordAbbr(String[] dictionary) {
        map = new HashMap<>();
        for(String str : dictionary) {
            String tmp = abbr(str);
            Set<String> set = map.containsKey(tmp)?map.get(tmp):new HashSet<String>();
            set.add(str);
            map.put(tmp, set);
        }
    }

    public boolean isUnique(String word) {
        String tmp = abbr(word);
        return (!map.containsKey(tmp)) || (map.get(tmp).contains(word) && map.get(tmp).size()==1);
    }
    
    private String abbr(String str) {
        return str.length()<3?str:str.substring(0,1)+(str.length()-2) 
                                  +str.substring(str.length()-1, str.length());
    }
}


// Your ValidWordAbbr object will be instantiated and called as such:
// ValidWordAbbr vwa = new ValidWordAbbr(dictionary);
// vwa.isUnique("Word");
// vwa.isUnique("anotherWord");


map中key是abbr value是一个set 包含所有abbr是key的字符串

https://leetcode.com/discuss/61658/share-my-java-solution