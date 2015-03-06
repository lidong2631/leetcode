public static void swap(int a, int b) {
	a = b - a;		//a becomes the difference between a, b
	b = b - a;		//b becomes to a since b = b - difference for a,b   b = b - (b - a)
	a = a + b;		//a becomes b since a = difference for a,b + a 		(b - a + a)
}

optimize:

public static void swap(int a, int b) {		//this is cool !!!!
	a = a^b;
	b = a^b;
	a = a^b;
}




但是，使用它们之前一定要想一想，你的程序中，是否有可能会让swap中的两个形参引用同一变量。如果是，那么上述两个swap函数都将出问题。

有人说，谁那么无聊去swap同一个变量。那可不好说，比如你在操作一个数组中的元素，然后用到了以下语句：

swap(a[i], a[j]); // i==j时，出问题

你并没有注意到swap会去操作同一变量，可是当i等于j时，就相当于你这么干了。然后呢，上面两个实现执行完第一条语句后，操作的那个内存中的数就变成0了。

后面的语句不会起到什么实际作用。

所以如果程序中有可能让swap函数去操作同一变量，就老老实实用最朴素的版本：

void swap(int &a, int &b){
    int t = a;
    a = b;
    b = t;
}




I understood now. u meant if a and b are the same variable. it is different from a == b. In this case, 

when b = a - b becomes zero, a becomes zero too since a is b. please remove my previous comment.
