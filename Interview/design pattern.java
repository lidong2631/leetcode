Factory pattern

In Factory pattern, we create object without exposing the creation logic to the client and refer to newly created object using a common interface

1. interface

Shape.java

public interface Shape {
	void draw();
}

2. classes that implements interface

Rectangle.java

public class Rectangle implements Shape {

	public void draw() {
		System.out.println("Rectangle draw() method.");
	}
}

Square.java

public class Square implements Shape {

	public void draw() {
		System.out.println("Square draw() method.");
	}
}

Circle.java

public class Circle implements Shape {

	public void draw() {
		System.out.println("Circle draw() method.");
	}
}


ShapeFactory.java

public class ShapeFactory {

	public Shape getShape(String shapeType) {
		if(shapeType==null)
			return null;
		if(shapeType.equalsIgnoreCase("CIRCLE"))
			return new Circle();
		else if(shapeType.equalsIgnoreCase("RECTANGLE"))
			return new Rectangle();
		else if(shapeType.equalsIgnoreCase("SQUARE"))
			return new Square();
		return null;
	}
}


FactoryPatternDemo.java

public class FactoryPatternDemo {

	public static void main(String[] args) {
		ShapeFactory ShapeFactory = new ShapeFactory();

		Shape shape1 = ShapeFactory.getShape("CIRCLE");
		shape1.draw();

		Shape shape2 = ShapeFactory.getShape("RECTANGLE");
		shape2.draw();

		Shape shape3 = ShapeFactory.getShape("SQUARE");
		shape3.draw();
	}
}






Abstract Factory pattern

In Abstract Factory pattern an super factory(interface) create a factory of related objects, without explicitly specifying their classes.

Each generated factory can give the objects as per the Factory Pattern.

An interface

Shape.java

public interface Shape {
	void draw();
}

Classes that implements the interface

Rectangle.java

public class Rectangle implements Shape {

	public void draw() {
		System.out.println("Rectangle draw() method");
	}
}

Square.java

public class Square implements Shape {

	public void draw() {
		System.out.println("Square draw() method");
	}
}

Circle.java

public class Circle implements Shape {

	public void draw() {
		System.out.println("Circle draw() method");
	}
}


Another interface

Color.java

public interface Color {
	void fill();
}

Classes that implements the interface

Red.java

public class Red implements Color {

	public void fill() {
		System.out.println("Red fill() method");
	}
}

Green.java

public class Green implements Color {

	public void fill() {
		System.out.println("Green fill() method");
	}
}

Blue.java

public class Blue implements Color {

	public void fill() {
		System.out.println("Blue fill() method");
	}
}



An abstract factory that can create one type of object

AbstractFactory.java

public abstract class AbstractFactory {
	abstract Color getColor(String c);
	abstract Shape getShape(String s);
}


An concrete factory class that extends from abstract factory to generate object of concrete class based on given information.
In this example, we have shape and Color

ShapeFactory.java

public class ShapeFactory extends AbstractFactory {

	public Shape getShape(String shapeType) {
		if(shapeType==null)
			return null;
		if(shapeType.equalsIgnoreCase("CIRCLE"))
			return new Circle();
		else if(shapeType.equalsIgnoreCase("RECTANGLE"))
			return new Rectangle();
		else if(shapeType.equalsIgnoreCase("SQUARE"))
			return new Square();
		return null;
	}

	Color getColor(String color) {		//have to override it otherwise ShpaeFactory need to be declared as abstract
		return null;
	}
}

ColorFactory.java

public class ColorFactory extends AbstractFactory {

	public Shape getShape(String shapeType) {    //have to override it otherwise ShpaeFactory need to be declared as abstract
		return null;
	}

	public Color getColor(String color) {		
		if(color==null)
			return null;
		if(color.equalsIgnoreCase("RED"))
			return new Red();
		else if(color.equalsIgnoreCase("GREEN"))
			return new Green();
		else if(color.equalsIgnoreCase("BLUE"))
			return new Blue();
		return null;
	}
}


Generate factory

FactoryProducer.java

public class FactoryProducer {
	public static AbstractFactory getFactory(String choice) {
		if(choice.equalsIgnoreCase("SHAPE"))
			return new ShapeFactory();
		else if(choice.equalsIgnoreCase("COLOR"))
			return new ColorFactory();
		return null;
	}
}


AbstractFactoryPatternDemo.java

public class AbstractFactoryPatternDemo {
	public static void main(String[] args) {

		AbstractFactory ShapeFactory = FactoryProducer.getFactory("SHAPE");

		Shape shape1 = ShapeFactory.getShape("CIRCLE");

    	shape1.draw();

    	Shape shape2 = shapeFactory.getShape("RECTANGLE");

     	shape2.draw();
  
    	Shape shape3 = shapeFactory.getShape("SQUARE");

     	shape3.draw();

    	AbstractFactory colorFactory = FactoryProducer.getFactory("COLOR");

    	Color color1 = colorFactory.getColor("RED");

    	color1.fill();

    	Color color2 = colorFactory.getColor("GREEN");

    	color2.fill();

    	Color color3 = colorFactory.getColor("BLUE");

    	color3.fill();
	}
}





Singleton Pattern

A single class which is responsible to create its own object while making sure that only single object get created.
This class provides a way to access its only object which can be accessed directly without need to instantiate the object of the class

SingleObject.java

public class SingleObject {

	private static SingleObject instance = new SingleObject();
	
	private SingleObject() {
	}

	public static SingleObject getInstance() {
		return instance;
	}

	public void showMessage() {
		System.out.println("Hello World!");
	}
}


SingletonPatternDemo.java

public class SingletonPatternDemo {
	public static void main(String[] args) {
		
		SingleObject object = SingleObject.getInstance();

		object.showMessage();
	}
}






MVC Pattern

Model represents an object or JAVA POJO carrying data. It can also have logic to update controller if its data changes.

View represents the visualization of the data that model contains.

Controller acts on both Model and View. It controls the data flow into model object and updates the view whenever data changes.
It keeps View and Model separate.


Create Model Student.java

public class Student {
	private String rollNo;
	private String name;

	public String getRollNo() {
		return rollNo;
	}

	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}

	public String getName() {
		return name;
	}

	public void setname(String name) {
		this.name = name;
	}
}

Create View StudentView.java

public class StudentView {
	public void printStudentDetails(String studentName, String studentRollNo) {
		System.out.println("Student: ");
		System.out.println("Name: " + studentName);
		System.out.println("Roll No: " + studentRollNo);
	}
}

Create Controller StudentController.java

public class StudentController {
	private Student model;
	private StudentView view;

	public StudentController(Student model, StudentView view) {
		this.model = model;
		this.view = view;
	}

	public void setStudentName(String name) {
		model.setName(name);
	}

	public String getStudentName() {
		return model.getName();
	}

	public void setStudentRollNo(String rollNo) {
		model.setRollNo(rollNo);
	}

	public String getStudentRollNo() {
		return model.getRollNo();
	}

	public void updateView() {
		view.printStudentDetails(model.getName(), model.getRollNo());
	}
}

MVCPatternDemo.java

public class MVCPatternDemo {
	public static void main(String[] args) {

		Student model = retriveStudentFromDB();

		Student view = new StudentView();

		StudentController controller = new StudentController(model, view);

		controller.updateView();

		controller.setStudentName("John");

		controller.updateView();
	}

	private static Student retriveStudentFromDB() {
		Student student = new Student();
		student.setName("Robert");
		student.setRollNo("10");
		return student;
	}
}