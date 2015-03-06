class Solution:
	def pow(self, x, n):
		if n == 0:
			return 1.0
		print(n/2)
		half = pow(x, int(1.0*n/2))
		print(half)
		if n % 2 == 0:
			return half * half
		elif n > 0:
			return half * half * x
		else:
			return 1.0* half / x * half

s = Solution()
print(s.pow(8.88023, 3))