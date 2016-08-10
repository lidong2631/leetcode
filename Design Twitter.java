public class Twitter {

    private int count;
    private Map<Integer, Set<Integer>> relationships;       // map user and his followers
    private Map<Integer, List<Tweet>> tweets;               // map user and his own tweets

    /** Initialize your data structure here. */
    public Twitter() {
        count = 0;                                              // we use count to simulate time each time when a tweet create count+1
        relationships = new HashMap<Integer, Set<Integer>>();
        tweets = new HashMap<Integer, List<Tweet>>();
    }
    
    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        synchronized (this) {                                   // When add count only one instance can do that in a certain time
            count++;
        }
        Tweet t = new Tweet(count, tweetId);
        
        if (!tweets.containsKey(userId))
            tweets.put(userId, new ArrayList<Tweet>());
        
        tweets.get(userId).add(t);    
    }
    
    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> res = new ArrayList<Integer>(10);
        
        PriorityQueue<Tweet> heap = new PriorityQueue<Tweet>(10, new Comparator<Tweet>() {
            public int compare(Tweet t1, Tweet t2) {            // heap used to sort tweets by their posting date
                return t1.count - t2.count;
            }
        });
        
        // get self tweets and add them to heap
        List<Tweet> selfTweets = tweets.get(userId);
        if (selfTweets != null) {                               // Careful ! check whether the user himself has any tweet
            for (Tweet t : selfTweets) {
                heap.offer(t);
                if (heap.size() > 10)
                    heap.poll();
            }    
        }
        
        // get follower's tweets and add to heap
        Set<Integer> followers = relationships.get(userId);
        if (followers != null) {                                // Careful ! check whether the user has any followers
            Iterator i = followers.iterator();
            while (i.hasNext()) {
                int followerId = (int)i.next();
                List<Tweet> followerTweets = tweets.get(followerId);
                if (followerTweets != null) {                   // Careful ! check whether the user's followers have any tweets
                    for (Tweet t : followerTweets) {
                        heap.offer(t);
                        if (heap.size() > 10)
                            heap.poll();
                    }    
                }
                
            }    
        }
        
        Stack<Integer> stack = new Stack<Integer>();
        while (heap.size() > 0)                                    // use a stack to place most recent tweets in front of early tweets
            stack.push(heap.poll().tweetId);
            
        
        while (!stack.empty()) {
            res.add(stack.pop());
        }
            
        return res;
    }
    
    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        if (followerId != followeeId) {                                     // Careful ! user cannot follow himself
            Set<Integer> followers = relationships.get(followerId);
            if (followers == null) {
                followers = new HashSet<Integer>();
                relationships.put(followerId, followers);
            }
            followers.add(followeeId);
        }
    }
    
    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if (followerId != followeeId) {                                     // Careful ! user cannot unfollow himself
            Set<Integer> followers = relationships.get(followerId);
            if (followers != null) {
                if (followers.contains(followeeId)) {
                    followers.remove(followeeId);
                }
            }
        }
    }
    
    class Tweet {
        int count;
        int tweetId;
        
        public Tweet(int c, int t) {
            this.count = c;
            tweetId = t;
        }
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */


Design a simplified version of Twitter where users can post tweets, follow/unfollow another user and is able to see the 10 most recent tweets in the user news feed. Your design should support the following methods:

postTweet(userId, tweetId): Compose a new tweet.
getNewsFeed(userId): Retrieve the 10 most recent tweet ids in the user news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent.
follow(followerId, followeeId): Follower follows a followee.
unfollow(followerId, followeeId): Follower unfollows a followee.
Example:

Twitter twitter = new Twitter();

// User 1 posts a new tweet (id = 5).
twitter.postTweet(1, 5);

// User 1's news feed should return a list with 1 tweet id -> [5].
twitter.getNewsFeed(1);

// User 1 follows user 2.
twitter.follow(1, 2);

// User 2 posts a new tweet (id = 6).
twitter.postTweet(2, 6);

// User 1's news feed should return a list with 2 tweet ids -> [6, 5].
// Tweet id 6 should precede tweet id 5 because it is posted after tweet id 5.
twitter.getNewsFeed(1);

// User 1 unfollows user 2.
twitter.unfollow(1, 2);

// User 1's news feed should return a list with 1 tweet id -> [5],
// since user 1 is no longer following user 2.
twitter.getNewsFeed(1);




getNewsFeed: O(nlog(10) + mlog(10)) n is user his own tweet and m is all followers of the user tweets

see jiuzhang note about performance issue and further optimization




