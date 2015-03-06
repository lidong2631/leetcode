public class Book {
	private long ID;
	private String content;
	private static Set<Book> books;		//good!!

	public Book(long id, String c) {
		this.ID = id;
		this.content = c;
	}

	public long getID() {
		return this.ID;
	}

	public String getContent() {
		return this.content;
	}

	public static void addBOok(long id, String c) {
		books.add(new Book(id, c));
	}

	public void update() {

	}

	public static void delete(Book b) {
		books.remove(b);
	}

	public static Book find(long id) {
		for(Book b : books)
		{
			if(b.getID()==id)
				return b;
		}
		return null;
	}
}

public class User {
	private long ID;
	private String details;
	private int acctType;
	private static Set<User> users;

	public User(long id, String details, int acctType) {
		this.ID = id;
		this.details = details;
		this.acctType = acctType;
	}

	public long getID() {
		return this.ID;
	}

	public Book searchLibrary(long id) {
		return Book.find(id);
	}

	public void renewMembership() {

	}

	public static User find(long id) {
		for(User u : users) {
			if(u.getID()==ID)
				return u;
		}
		return null;
	}

	public static void addUser(long id, String details, int acctType) {
		users.add(new User(id, details, acctType));
	}
}


public class onlineReaderSys {
	private Book b;
	private User u;

	public onlineReaderSys(Book b, User u) {

	}

	public void listenRequest() {

	}

	public void display() {

	}

	public Book searchBook(long id) {
		return Book.find(id);
	}

	public User searchUser(long id) {
		return User.find(id);
	}
}


Note: 这里对book和user的操作都定义在其自身 着重理解 




how to travese a set in java

1 iterator

Iterator iter = set.iterator();
while (iter.hasNext()) {
  System.out.println(iter.next());
}

2 enhanced for loop

Set<String> set = new HashSet<String> ();

//populate set

for (String s : set) {
    System.out.println(s);
}Or with Java 8:

set.forEach(System.out::println);