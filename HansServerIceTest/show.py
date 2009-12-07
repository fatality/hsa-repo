#!/usr/bin/python

import pylab

xfile = open( 'px.txt' );
yfile = open( 'py.txt' );

xlist = []
ylist = []

while True:
	x = xfile.readline()
	if len(x) == 0:
		break
	xlist.append(x)
	

while True:
	y = yfile.readline()
	if len(y) == 0:
		break
	ylist.append(y)

xfile.close()
yfile.close()

pylab.plot( xlist, ylist )
pylab.show()
