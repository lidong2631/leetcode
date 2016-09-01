public class Twitter {

    private int count;
    private Map<Integer, Set<Integer>> relations;
    private Map<Integer, List<Tweet>> tweets;

    class Tweet {
        int count;
        int tweetId;
        
        public Tweet(int count, int tweetId) {
            this.count = count;
            this.tweetId = tweetId;
        }
    }

    /** Initialize your data structure here. */
    public Twitter() {
        count = 0;
        relations = new HashMap<>();
        tweets = new HashMap<>();
    }
    
    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        if (!tweets.containsKey(userId)) tweets.put(userId, new ArrayList<Tweet>());
        count++;
        tweets.get(userId).add(new Tweet(count, tweetId));
    }
    
    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> list = new ArrayList<>();
        
        PriorityQueue<Tweet> heap = new PriorityQueue<Tweet>(10, new Comparator<Tweet>() {
            public int compare(Tweet t1, Tweet t2) {
                return t1.count - t2.count;
            }
        });
        
        List<Tweet> selfTweets = tweets.get(userId);
        if (selfTweets != null) {
            for (Tweet t : selfTweets) {
                heap.offer(t);
                if (heap.size() > 10) heap.poll();
            }
        }
        
        Set<Integer> followers = relations.get(userId);
        if (followers != null) {
            Iterator i = followers.iterator();
            while (i.hasNext()) {
                int followerId = (int)i.next();
                if (tweets.containsKey(followerId)) {
                    List<Tweet> followerTweets = tweets.get(followerId);
                    for (Tweet t : followerTweets) {
                        heap.offer(t);
                        if (heap.size() > 10) heap.poll();
                    }
                }
            }
        }
        
        Stack<Tweet> stack = new Stack<>();
        while (!heap.isEmpty()) {
            stack.push(heap.poll());
        }
        while (!stack.isEmpty()) {
            list.add(stack.pop().tweetId);
        }
        return list;
    }
    
    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        if (followerId != followeeId) {
            if (!relations.containsKey(followerId)) relations.put(followerId, new HashSet<Integer>());
            relations.get(followerId).add(followeeId);
        }
    }
    
    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if (followerId != followeeId) {
            Set<Integer> set = relations.get(followerId);
            if (set != null) {
                if (set.contains(followeeId)) {
                    set.remove(followeeId);
                }
            }
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

The code uses pull mode


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




