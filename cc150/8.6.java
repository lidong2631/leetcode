enum Color {
	Black, White, Red, Yellow, Green
}

public boolean PaintFill(Color[][] screen, int x, int y, Color oldColor, Color newColor) {
	if(x<0 || x>=screen[0].length || y<0 || y>=screen.length)
	{
		return false;
	}
	if(screen[y][x]==oldColor)
	{
		screen[y][x] = newColor;
		PaintFill(screen, x-1, y, oldColor, newColor);
		PaintFill(screen, x+1, y, oldColor, newColor);
		PaintFill(screen, x, y-1, oldColor, newColor);
		PaintFill(screen, x, y+1, oldColor, newColor);
	}
	return true;
}

public boolean Paint(Color[][] screen, int x, int y, Color oldColor, Color newColor) {
	return PaintFill(screen, x, y, screen[y][x], newColor);
}

Note: flood fill解法 可以看下leetcode surrounded regions 比这个难