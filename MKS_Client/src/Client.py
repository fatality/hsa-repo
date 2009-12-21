#!/usr/bin/python
# -*- coding: utf-8 -*-
#
# Client.py
#

import sys, traceback, Ice
import MksIce

status = 0
ic = None

try:
	ic = Ice.initialize( sys.argv )
	base = ic.stringToProxy( "SimpleManagement: default -p 10003" )
	management = MksIce.ManagementPrx.checkedCast( base )
	if not management:
		raise RuntimeError( "Invalid proxy" )
	management.initManagement( 1, 2, 2.0, 5.0 )
except:
	traceback.print_exc()
	status = 1

if ic:
	# Clean up
	try:
		ic.destroy()
	except:
		traceback.print_exc()
		status = 1

sys.exit( status )
