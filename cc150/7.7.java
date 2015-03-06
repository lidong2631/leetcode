enum StatusType {
	online, offline, away;
}

public class Status {
	private StatusType status_type;
	private String status_msg;
}

public class User {
	private String username;
	private String display_name;
	private User[] contact_list;
	private addRequest[] requests;

	public boolean updateStatus(StatusType statusType, String msg) {

	}

	public boolean addUser(String username) {

	}

	public boolean approveRequest(String username) {

	}

	public boolean removeUser(String username) {

	}

	public boolean sendMsg(String username, String msg) {

	}
}

public class Request {
	User from;
	User to;
}

public class Server {
	public User getUserByUsername(String username) {

	}
}

Note: 这只是个简单的模型 注意看下concerned problem