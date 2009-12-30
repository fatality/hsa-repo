#!/usr/bin/python
# -*- coding: utf-8 -*-
#
# file: mks_start.py
#

import sys, traceback, Ice, MksIce

from PyQt4 import QtGui, QtCore
from mks_qt_gui import Ui_MainWindow as Gui
from vtk.qt4 import QVTKRenderWindowInteractor


class StartGui( QtGui.QMainWindow, Gui ):

    def __init__( self, parent = None ):
        QtGui.QMainWindow.__init__( self, parent )
        self.setupUi( self )
        
        # Slots einrichten
        self.connect( self.start_button, QtCore.SIGNAL( 'clicked()' ), self.onStart ) 
        self.connect( self.default_button, QtCore.SIGNAL( 'clicked()' ), self.onDefault )
        
        
    def onStart( self ):
        print 'Server: %s' % self.server_box.currentText()
        print 'Port: %s' % self.port_box.currentText()
        print 'Anzahl der Körper: %s' % self.koerper_box.value()
        print 'Simulationsdauer in Tagen: %s' % self.simduration_box.value()
        print 'Massebereich der Körper: %s' % self.masskoerper_box.value()
        print 'Massebereich des Zentralkörpers: %s' % self.masscentral_box.value()
        
        status = 0
        ic = None
        
        try:
            ic = Ice.initialize( sys.argv )
            base = ic.stringToProxy( 'SimpleManagement: default -p 10003' )
            management = MksIce.ManagementPrx.checkedCast( base )
            if not management:
                raise RuntimeError( 'Invalid proxy' )
            #management.initManagement( self.koerper_box.value(), self.simduration_box.value(), self.masskoerper_box.value(), self.masscentral_box.value() )
            management.initManagement( 1, 365, 100.0, 6000000.0 )
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


    def onDefault( self ):
        self.koerper_box.setProperty( 'value', 1 )
        self.simduration_box.setProperty( 'value', 365 )
        self.masskoerper_box.setProperty( 'value', 26 )
        self.masscentral_box.setProperty( 'value', 30 )


app = QtGui.QApplication( sys.argv )
window = StartGui()
window.show()
sys.exit( app.exec_() )
