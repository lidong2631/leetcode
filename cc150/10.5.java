public class Point {
	double x;
	double y;

	public Point(double axis, double ayis) {
		this.x = axis;
		this.y = ayis;
	}
}

public class Line {
	Point p1;
	Point p2;

	public Line(Point a, Point b) {
		this.p1 = new Point(a);
		this.p2 = new Point(b);
	}
}

public class Square {
	public double left;
	public double right;
	public double top;
	public double bottom;

	public Square(double l, double t, double size) {
		this.left = l;
		this.top = t;
		this.right = l + size;
		this.bottom = t + size;
	}

	public Point center() {
		return new Point((this.left+this.right)/2, (this.top+this.bottom)/2);
	}

	public Line cut(Square s) {
		Point centerFirst = this.center();
		Point centerSecond = s.center();

		if(centerFirst==centerSecond)
			return new Line(new Point(left, top), new Point(right, bottom));
		else
			return new Line(centerFirst, centerSecond);
	}
}