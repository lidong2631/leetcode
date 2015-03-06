import java.lang.reflect.*;
import java.lang.Class;

public class reflectSample {
	public static void main(String[] args) {
		try {
			Class c = Class.forName("java.sql.Connection");
			Method m[] = c.getDeclaredMethods();

			for(int i=0; i<3; i++)
				System.out.println(m[i].toString());

		} catch(Throwable e) {
			System.err.println(e);
		}
	}
}




Method findInheritedMethod(Class classType, String MethodName, Class[] methodParamTypes) {

	Method inheritedMethod = null;

	while(classType!=null)
	{
		try {
			inheritedMethod = classType.getDeclaredMethod(MethodName, methodParamTypes);
			break;
		} catch(NoSuchMethodException ex) {
			classType = classType.getSuperclass();
		}
	}
	return inheritedMethod;
}