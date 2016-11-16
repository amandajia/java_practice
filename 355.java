// https://leetcode.com/problems/design-twitter/
public class Twitter {
    int most = 10;
    int ts = 0;
    public class Twit {
        public int time;
        public int id;
        public Twit(int t, int i) {
            time = t;
            id = i;
        }
    }
    
    public class TwitQueue {
        Twit[] array = new Twit[most];
        int p = 0;
        public Twit get(int l) {
            return array[(p - l - 1 + most)%most];
        }
        public void pub(Twit t) {
            array[p] = t;
            p = (p + 1)% most;
        }
    }
    Map<Integer, Set<Integer>> follow;
    Map<Integer, TwitQueue> publish;
    /** Initialize your data structure here. */
    public Twitter() {
        follow = new HashMap<>();
        publish = new HashMap<>();
    }
    
    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        publish.putIfAbsent(userId, new TwitQueue());
        TwitQueue q = publish.get(userId);
        q.pub(new Twit(ts, tweetId));
        ts++;
    }
    
    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> mostRecent = new ArrayList<>();
        Set<Integer> fs = follow.get(userId);
        PriorityQueue<Twit> pq = new PriorityQueue<Twit> (new Comparator<Twit>() {
            @Override
            public int compare(Twit t1, Twit t2) {
                return t1.time - t2.time;
            }
        });
        List<Integer> fl = new ArrayList<>();
        fl.add(userId);
        if(fs != null)
            fl.addAll(fs);
        for(int i = 0; i< fl.size(); ++i) {
            int uid = fl.get(i);
            TwitQueue tq = publish.get(uid);
            if(tq != null) {
                for(int j= 0; j<most; ++j) {
                    Twit t = tq.get(j);
                    if(t == null)
                        break;
                    pq.add(t);
                    if(pq.size() > most) {
                        Twit l = pq.poll();
                        if(l == t)
                            break;
                    }
                }
            }
        }
        while(!pq.isEmpty()) {
            mostRecent.add(pq.poll().id);
        }
        Collections.reverse(mostRecent);
        return mostRecent;
    }
    
    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        if(followerId == followeeId)
            return;
        follow.putIfAbsent(followerId, new HashSet<Integer> ());
        follow.get(followerId).add(followeeId);
    }
    
    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        Set<Integer> list = follow.get(followerId);
        if(list!= null) {
            list.remove(followeeId);
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
