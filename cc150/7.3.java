public class CD {

}

public class Playlist {
	private Song track;
	private Queue<Song> queue;

	public Playlist(Song track, Queue<Song> queue) {

	}

	public Song getNextTrack() {
		return queue.peek();
	}

	public void addTrack(Song track) {
		queue.add(track);
	}
}

public class CDPlayer {
	private Playlist p;
	private CD c;

	public CDPlayer(CD c) {
		this.c = c;
	}

	public CDPlayer(CD c, Playlist p) {
		this.c = c;
		this.p = p;
	}

	public CDPlayer(Playlist p) {
		this.p = p;
	}

	public Playlist getPlaylist() {
		return p;
	}

	public void setPlaylist(Playlist p) {
		this.p = p
	}

	public CD getCD() {
		return c;
	}

	public void setCD(CD c) {
		this.c = c;
	}

	public void playTrack(Song s) {

	}
}

public class User {
	private String name;
	private long ID;

	public User(String name, long id) {
		this.name = name;
		this.ID = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getID() {
		return ID;
	}

	public void setID(long id) {
		this.ID = id;
	}

	public User getUser() {
		return this;
	}

	public static User addUser(String name, long id) {

	}
}


public class TrackSelector {
	private Song currentSong;

	public TrackSelector(Song s) {
		currentSong = s;
	}

	public Song getTrack() {
		return currentSong;
	}

	public void setTrack(Song s) {
		currentSong = s;
	}
}


public class Song {
	private String songName;
}


public class JukeBox {
	private CDPlayer cdplayer;
	private User user;
	private Set<CD> cdCollection;
	private TrackSelector ts;

	public JukeBox(CDPlayer cdplayer, User user, Set<CD> cdCollection, TrackSelector ts) {

	}

	public Song getCurrentTrack() {
		return ts.getCurrentTrack();
	}

	public void processUser(User u) {
		this.user = u;
	}
}