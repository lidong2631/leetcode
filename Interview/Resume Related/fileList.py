import os, sys
def listdir(dir, file):
	file.write(dir + '\n')
	filenum = 0
	list = os.listdir(dir)
	for line in list:
		if os.path:
			myfile.write(line + '\n')
			filenum = filenum + 1
	myfile.write('all the file num is ' + str(filenum))
dir = raw_input('please input the path: ')
myfile = open('list.txt', 'w')
listdir(dir, myfile)
myfile.close()