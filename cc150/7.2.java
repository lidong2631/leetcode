public class Call {
	int rank = 0;
}


public class Employee {
	int rank;
	boolean isFree;
	public Employee(int r) {
		this.rank = r;
	}

	public void ProcessCall(Call call) {
		...
	}

	public void DeliverCall(Call call) {
		call.rank = rank + 1;
		callHandler.dispatchCall(call);
		free = true;
		callHandler.getNextCall(this);
	}
}

public class Fresher extends Employee {
	public Fresher() {
		super(0);
	}
}

public class TechLead extends Employee {
	public TechLead() {
		super(1);
	}
}

public class ProjectManager extends Employee {
	public ProjectManager() {
		super(2);
	}
}


public class callHandler {
	static final int LEVELS = 3;
	static final int NUM_FRESHERS = 5;
	static final int NUM_TECHLEAD = 1;
	static final int NUM_PROJECTMANAGER = 1;
	
	ArrayList<Employee> Levels = new ArrayList<Employee>[LEVELS];

	Queue<Call>[] callQueues = new LinkedList[LEVELS];

	public callHandler() {

	}

	public Employee getCallHander(Call call) {		//找到可以处理这个call的employee
		for(int level=call.rank; level<LEVELS-1; level++)	//从call.rank开始 找到下一个free的employee
		{
			ArrayList<Employee> employee = Levels[level];
			for(int i=0; i<employee.size(); i++)
			{
				if(employee.get(i).free)
					return employee.get(i);
			}
		}
		return null;
	}

	public void dispatchCall(Call call) {	//route the call to an available employee or add to a queue
		Emplyee e = getCallHander(call);
		if(e!=null)
			e.ProcessCall(call);
		else
			callQueues[call.rank].add(call);	//if no one available put this call to 对应这个call rank的employee队列中
	}

	public void getNextCall(Employee e) {	//look for call according to e' rank

	}
}