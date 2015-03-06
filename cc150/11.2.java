public class Server {
	ArrayList<Machine> m = new ArrayList<Machine>();
}

public class Machine {
	int machineID;
	ArrayList<Person> p = new ArrayList<Person>();
}

public class Person {
	Server s;
	int machineID;
	int ID;
	String Info;
	ArrayList<Person> friends = new ArrayList<Person>();

	public Person(int machine_ID, int id) {
		this.machineID = machine_ID;
		this.ID = id;
	}

	public Machine findMachine(int machine_ID) {
		for(Machine m : Server.m)
		{
			if(m.machineID==machine_ID)
				return m;
		}
		return null;
	}

	public Person findPerson(int machine_ID, int id) {
		for(Machine m : Server.m)
		{
			if(m.machineID==machine_ID)
			{
				for(Person p : m.p)
				{
					if(p.ID==id)
						return p;
				}
			}
		}
		return null;
	}

	public String getInfo() {
		return Info;
	}

	public void setInfo(String s) {
		this.Info = s;
	}

	public int getID() {
		return ID;
	}

	public int getMachineID() {
		return machineID;
	}

	public void addFriend(Person p) {
		friends.add(p);
	}
}

