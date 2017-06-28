Note: This is a companion problem to the System Design problem: Design TinyURL.
TinyURL is a URL shortening service where you enter a URL such as https://leetcode.com/problems/design-tinyurl and it returns a short URL such as http://tinyurl.com/4e9iAk.

Design the encode and decode methods for the TinyURL service. There is no restriction on how your encode/decode algorithm should work. You just need to ensure that a URL can be encoded to a tiny URL and the tiny URL can be decoded to the original URL.

Hide Company Tags Amazon Google Uber Facebook
Hide Tags Hash Table Math
Hide Similar Problems (M) Design TinyURL



import java.util.*;

public class Codec {

    private Map<String, String> long2Short;
    private Map<String, String> short2Long;
    
    public Codec() {
        long2Short = new HashMap<String, String>();
        short2Long = new HashMap<String, String>();
    }

    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        if (long2Short.containsKey(longUrl))
            return long2Short.get(longUrl);
            
        while (true) {
            String shortUrl = generateShortUrl();
            if (!short2Long.containsKey(shortUrl)) {
                short2Long.put(shortUrl, longUrl);
                long2Short.put(longUrl, shortUrl);
                return shortUrl;
            }
        }
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        if (!short2Long.containsKey(shortUrl))
            return null;
        return short2Long.get(shortUrl);
    }
    
    private String generateShortUrl() {
        String allowedChars = "0123456789" +
                                "abcdefghijklmnopqrstuvwxyz" +
                                "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rand = new Random();
        String shortUrl = "http://tinyurl.com/";
        for (int i = 0; i < 6; i++) {
            int index = rand.nextInt(62);
            shortUrl += allowedChars.charAt(index);
        }
        return shortUrl;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(url));







/**
 * 本代码由九章算法编辑提供。版权所有，转发请注明出处。
 * - 九章算法致力于帮助更多中国人找到好的工作，教师团队均来自硅谷和国内的一线大公司在职工程师。
 * - 现有的面试培训课程包括：九章算法班，系统设计班，算法强化班，Java入门与基础算法班，Android 项目实战班，Big Data 项目实战班，
 * - 更多详情请见官方网站：http://www.jiuzhang.com/?source=code
 */

// version 1: Random
public class TinyUrl {
    private Map<String, String> long2Short;
    private Map<String, String> short2Long;
    
    public TinyUrl() {
        long2Short = new HashMap<String, String>();
        short2Long = new HashMap<String, String>();
    }
    
    /**
     * @param url a long url
     * @return a short url starts with http://tiny.url/
     */
    public String longToShort(String url) {
        if (long2Short.containsKey(url)) {
            return long2Short.get(url);
        }
        
        while (true) {
            String shortURL = generateShortURL();
            if (!short2Long.containsKey(shortURL)) {
                short2Long.put(shortURL, url);
                long2Short.put(url, shortURL);
                return shortURL;
            }
        }
    }

    /**
     * @param url a short url starts with http://tiny.url/
     * @return a long url
     */
    public String shortToLong(String url) {
        if (!short2Long.containsKey(url)) {
            return null;
        }
        
        return short2Long.get(url);
    }
    
    private String generateShortURL() {
        String allowedChars = 
            "0123456789" +
            "abcdefghijklmnopqrstuvwxyz" + 
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        
        Random rand = new Random();
        String shortURL = "http://tiny.url/";
        for (int i = 0; i < 6; i++) {
            int index = rand.nextInt(62);
            shortURL += allowedChars.charAt(index);
        }
        
        return shortURL;
    }
}


// version 2: base62
public class TinyUrl {
    public static int GLOBAL_ID = 0;
    private HashMap<Integer, String> id2url = new HashMap<Integer, String>();
    private HashMap<String, Integer> url2id = new HashMap<String, Integer>();
    
    private String getShortKey(String url) {
        return url.substring("http://tiny.url/".length());
    }

    private int toBase62(char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        }
        if (c >= 'a' && c <= 'z') {
            return c - 'a' + 10;
        }
        return c - 'A' + 36;
    }
    
    private int shortKeytoID(String short_key) {
        int id = 0;
        for (int i = 0; i < short_key.length(); ++i) {
            id = id * 62 + toBase62(short_key.charAt(i));
        }
        return id;
    }

    private String idToShortKey(int id) {
        String chars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String short_url = "";
        while (id > 0) {
            short_url = chars.charAt(id % 62) + short_url;
            id = id / 62;
        }
        while (short_url.length() < 6) {
            short_url = "0" + short_url;
        }
        return short_url;
    }

    /**
     * @param url a long url
     * @return a short url starts with http://tiny.url/
     */
    public String longToShort(String url) {
        if (url2id.containsKey(url)) {
            return "http://tiny.url/" + idToShortKey(url2id.get(url));
        }
        GLOBAL_ID++;
        url2id.put(url, GLOBAL_ID);
        id2url.put(GLOBAL_ID, url);
        return "http://tiny.url/" + idToShortKey(GLOBAL_ID);
    }

    /**
     * @param url a short url starts with http://tiny.url/
     * @return a long url
     */
    public String shortToLong(String url) {
        String short_key = getShortKey(url);
        int id = shortKeytoID(short_key);
        return id2url.get(id);
    }
}




see http://www.jiuzhang.com/solutions/tiny-url/