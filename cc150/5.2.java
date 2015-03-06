public class toBinary {
	public static void main(String[] args) {
		toBinary tb = new toBinary();
		String res = tb.printBinary("-6.5");
		System.out.println(res);
	}

	public String printBinary(String n) {
		int intPart = Integer.parseInt(n.substring(0, n.indexOf('.')));
		double decPart = Double.parseDouble(n.substring(n.indexOf('.'), n.length()));

		String int_string = "";

		while(intPart>0)
		{
			int res = intPart%2;
			intPart/=2;
			int_string = String.valueOf(res) +int_string;
			//System.out.println(int_string);
		}

		StringBuffer dec_string = new StringBuffer();
		while(decPart>0)
		{
			if(dec_string.length()>32)
				return "ERROR";
			//if(decPart==1)
			//{
			//	dec_string.append((int)decPart);
			//	break;
			//}
			double decimal = decPart*2;
			if(decimal>=1)
			{
				dec_string.append(1);
				decPart = decimal - 1;
			}
			else
			{
				dec_string.append(0);
				decPart = decimal;
			}
		}
		return int_string + "." + dec_string.toString();
	}
}

Note: String.valueOf(), Integer.toString()

对于负数的处理在正数的基础上取补码

负数的二进制表示方法
今天在看base64编码转换时，既然对负数的二进制表示有些遗忘，在网上找了一下资料，贴出来已备在此遗忘：

假设有一个 int 类型的数，值为5，那么，我们知道它在计算机中表示为：

00000000 00000000 00000000 00000101

5转换成二制是101，不过int类型的数占用4字节（32位），所以前面填了一堆0。

现在想知道，-5在计算机中如何表示？

　

在计算机中，负数以其正值的补码形式表达。

什么叫补码呢？这得从原码，反码说起。

　

原码：一个整数，按照绝对值大小转换成的二进制数，称为原码。

比如 00000000 00000000 00000000 00000101 是 5的 原码。

　

反码：将二进制数按位取反，所得的新二进制数称为原二进制数的反码。

取反操作指：原为1，得0；原为0，得1。（1变0; 0变1）

比如：将00000000 00000000 00000000 00000101每一位取反，得11111111 11111111 11111111 11111010。

称：11111111 11111111 11111111 11111010 是 00000000 00000000 00000000 00000101 的反码。

反码是相互的，所以也可称：

11111111 11111111 11111111 11111010 和 00000000 00000000 00000000 00000101 互为反码。

　

补码：反码加1称为补码。

也就是说，要得到一个数的补码，先得到反码，然后将反码加上1，所得数称为补码。

比如：00000000 00000000 00000000 00000101 的反码是：11111111 11111111 11111111 11111010。

那么，补码为：

11111111 11111111 11111111 11111010 + 1 = 11111111 11111111 11111111 11111011


所以，-5 在计算机中表达为：11111111 11111111 11111111 11111011。转换为十六进制：0xFFFFFFFB。


再举一例，我们来看整数-1在计算机中如何表示。

假设这也是一个int类型，那么：


1、先取1的原码：00000000 00000000 00000000 00000001

2、得反码：     11111111 11111111 11111111 11111110

3、得补码：     11111111 11111111 11111111 11111111


可见，－1在计算机里用二进制表达就是全1。16进制为：0xFFFFFF