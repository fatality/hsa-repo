#!/usr/bin/python
# -*- coding:utf-8 -*-
#
# file: Client.py
#

import sys, traceback, Ice, MksIce, vtk, time, math

from PyQt4 import QtGui, QtCore
from vtk.qt4 import QVTKRenderWindowInteractor


class InfoDialog( QtGui.QDialog ):
    
    def __init__( self, parent = None ):
        
        QtGui.QDialog.__init__( self, parent )
        
        icon = QtGui.QIcon()
        icon.addPixmap( QtGui.QPixmap( 'icons/application_icon.png' ), QtGui.QIcon.Normal, QtGui.QIcon.Off )
        
        # ===
        # InfoDialog Eigenschaften
        # ===
        self.setObjectName( 'info_dialog' )
        self.resize( 404, 251 )
        self.setWindowIcon( icon )
        
        # ===
        # InfoDialog Widgets
        # ===
        self.widget = QtGui.QWidget( self )
        self.widget.setGeometry( QtCore.QRect( 0, 10, 402, 231 ) )
        self.widget.setObjectName( 'widget' )
        
        self.logo_label = QtGui.QLabel( self.widget )
        self.logo_label.setPixmap( QtGui.QPixmap( 'icons/application_logo.png' ) )
        self.logo_label.setObjectName( 'logo_label' )
        
        self.info_edit = QtGui.QTextEdit( self.widget )
        self.info_edit.setReadOnly( True )
        self.info_edit.setObjectName( 'info_edit' )
        
        self.button_box = QtGui.QDialogButtonBox( self.widget )
        self.button_box.setOrientation( QtCore.Qt.Horizontal )
        self.button_box.setStandardButtons( QtGui.QDialogButtonBox.Close )
        self.button_box.setObjectName( 'button_box' )
        
        self.vLayout = QtGui.QVBoxLayout( self.widget )
        self.vLayout.setObjectName( 'vLayout' )
        self.vLayout.addWidget( self.logo_label )
        self.vLayout.addWidget( self.info_edit )
        self.vLayout.addWidget( self.button_box )
        
        # ===
        # Setzen des Layouts, Übersetzung des Dialogs und Darstellung
        # ===
        self.retranslateUi( self )
        self.setLayout( self.vLayout )
        self.show()
        
        # ===
        # Signal/Slot Mechanismus
        # ===
        self.connect( self.button_box, QtCore.SIGNAL( 'rejected()' ), self.close )
        
        
    def retranslateUi( self, info_dialog ):
        info_dialog.setWindowTitle( QtGui.QApplication.translate( 'info_dialog', 'Informationen', None, QtGui.QApplication.UnicodeUTF8 ) )
        info_dialog.setStatusTip( QtGui.QApplication.translate( 'info_dialog', 'http://mks.gironimo.org/', None, QtGui.QApplication.UnicodeUTF8 ) )
        self.info_edit.setHtml( QtGui.QApplication.translate( 'info_dialog', '<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0//EN\" \"http://www.w3.org/TR/REC-html40/strict.dtd\">\n'
'<html><head><meta name=\"qrichtext\" content=\"1\" /><style type=\"text/css\">\n'
"p, li { white-space: pre-wrap; }\n"
'</style></head><body style=\' font-family:\'DejaVu Sans\'; font-size:8pt; font-weight:400; font-style:normal;\'>\n'
'<p style=\' margin-top:0px; margin-bottom:0px; margin-left:0px; margin-right:0px; -qt-block-indent:0; text-indent:0px;\'>Diese Anwendung entstandt während des Praktikums an der HS Augsburg im 3. Semester im Fach Programmieren 3. Das Programm soll eine schrittweise Simulation eines Mehrkörpersystems simulieren.</p>\n'
'<p style=\'-qt-paragraph-type:empty; margin-top:0px; margin-bottom:0px; margin-left:0px; margin-right:0px; -qt-block-indent:0; text-indent:0px;\'></p>\n'
'<p style=\' margin-top:0px; margin-bottom:0px; margin-left:0px; margin-right:0px; -qt-block-indent:0; text-indent:0px;\'>Das Online Handbuch ist unter folgenden Link erreichbar: <a href=\'http://mks.gironimo.org/\'><span style=\' text-decoration: underline; color:#0000ff;\'>http://mks.gironimo.org/</span></a></p>\n'
'<p style=\'-qt-paragraph-type:empty; margin-top:0px; margin-bottom:0px; margin-left:0px; margin-right:0px; -qt-block-indent:0; text-indent:0px;\'></p>\n'
'<p style=\' margin-top:0px; margin-bottom:0px; margin-left:0px; margin-right:0px; -qt-block-indent:0; text-indent:0px;\'>Die Simulation wurde in Java (<a href=\'http://www.java.com/\'><span style=\' text-decoration: underline; color:#0000ff;\'>http://www.java.com/</span></a>) geschrieben. Der Client dagegen in Python (<a href=\'http://www.python.org/\'><span style=\' text-decoration: underline; color:#0000ff;\'>http://www.python.org/</span></a>). Als Framework für die grafische Oberfläche wurde Qt (<a href=\'http://qt.nokia.com/\'><span style=\' text-decoration: underline; color:#0000ff;\'>http://qt.nokia.com/</span></a>) gewählt. Als visuelle Ausgabe der Körper kommt VTK (<a href=\'http://www.vtk.org/\'><span style=\' text-decoration: underline; color:#0000ff;\'>http://www.vtk.org/</span></a>) zum Einsatz. Als Middleware für die Kommunikation zwischen Client und Server wurde die Internet Communication Engine (Ice) aus dem Hause ZeroC (<a href=\'http://www.zeroc.com/\'><span style=\' text-decoration: underline; color:#0000ff;\'>http://www.zeroc.com/</span></a>) implementiert.</p>\n'
'<p style=\'-qt-paragraph-type:empty; margin-top:0px; margin-bottom:0px; margin-left:0px; margin-right:0px; -qt-block-indent:0; text-indent:0px;\'></p>\n'
'<p style=\' margin-top:0px; margin-bottom:0px; margin-left:0px; margin-right:0px; -qt-block-indent:0; text-indent:0px;\'>Das Entwicklungsteam besteht aus:</p>\n'
'<p style=\' margin-top:0px; margin-bottom:0px; margin-left:0px; margin-right:0px; -qt-block-indent:0; text-indent:0px;\'>Marc Rochow (GUI, Client, Ice)</p>\n'
'<p style=\' margin-top:0px; margin-bottom:0px; margin-left:0px; margin-right:0px; -qt-block-indent:0; text-indent:0px;\'>Sebastian Schwarz (Server, Ice)</p>\n'
'<p style=\' margin-top:0px; margin-bottom:0px; margin-left:0px; margin-right:0px; -qt-block-indent:0; text-indent:0px;\'>Anja Radtke (Ice, Client)</p>\n'
'<p style=\' margin-top:0px; margin-bottom:0px; margin-left:0px; margin-right:0px; -qt-block-indent:0; text-indent:0px;\'>Dominicus Bosch (Testen, Server)</p>\n'
'<p style=\' margin-top:0px; margin-bottom:0px; margin-left:0px; margin-right:0px; -qt-block-indent:0; text-indent:0px;\'>Hannes Slosharek (Testen, GUI)</p>\n'
'<p style=\' margin-top:0px; margin-bottom:0px; margin-left:0px; margin-right:0px; -qt-block-indent:0; text-indent:0px;\'>Stefan Wiesner (Testen, Client)</p>\n'
'<p style=\'-qt-paragraph-type:empty; margin-top:0px; margin-bottom:0px; margin-left:0px; margin-right:0px; -qt-block-indent:0; text-indent:0px;\'></p>\n'
'<p style=\' margin-top:0px; margin-bottom:0px; margin-left:0px; margin-right:0px; -qt-block-indent:0; text-indent:0px;\'>Die Anwendung steht unter <a href=\'http://www.fsf.org/licensing/licenses/gpl.html\'><span style=\' text-decoration: underline; color:#0000ff;\'>GNU General Public License 3</span></a>.</p>\n'
'<p style=\' margin-top:0px; margin-bottom:0px; margin-left:0px; margin-right:0px; -qt-block-indent:0; text-indent:0px;\'>Programm Version 1.0</p></body></html>', None, QtGui.QApplication.UnicodeUTF8))

    
class MainWindow( QtGui.QMainWindow ):
    
    def __init__( self, parent = None ):
        
        QtGui.QMainWindow.__init__( self, parent)
        
        icon = QtGui.QIcon()
        icon.addPixmap( QtGui.QPixmap( 'icons/application_icon.png' ), QtGui.QIcon.Normal, QtGui.QIcon.Off )
        
        icon1 = QtGui.QIcon()
        icon1.addPixmap(QtGui.QPixmap( 'icons/application_start.png' ), QtGui.QIcon.Normal, QtGui.QIcon.Off )
        
        icon2 = QtGui.QIcon()
        icon2.addPixmap( QtGui.QPixmap( 'icons/application_help.png' ), QtGui.QIcon.Normal, QtGui.QIcon.Off )
        
        icon3 = QtGui.QIcon()
        icon3.addPixmap( QtGui.QPixmap( 'icons/application_exit.png' ), QtGui.QIcon.Normal, QtGui.QIcon.Off )
        
        icon4 = QtGui.QIcon()
        icon4.addPixmap( QtGui.QPixmap( 'icons/application_info.png' ), QtGui.QIcon.Normal, QtGui.QIcon.Off )
        
        # ===
        # MainWindow Eigenschaften
        # ===
        self.setObjectName( 'MainWindow' )
        self.resize( 800, 577 )
        self.setWindowIcon( icon )
        self.setUnifiedTitleAndToolBarOnMac( True )
        
        # ===
        # MainWindow Widgets
        # ===
        self.centralwidget = QtGui.QWidget( self )
        self.centralwidget.setObjectName( 'centralwidget' )
    
        self.tab_widget_pref = QtGui.QTabWidget( self.centralwidget )
        self.tab_widget_pref.setGeometry( QtCore.QRect( 10, 10, 291, 311 ) )
        self.tab_widget_pref.setObjectName( 'tab_widget_pref' )
    
        self.common = QtGui.QWidget()
        self.common.setObjectName( 'common' )
    
        self.layoutWidget = QtGui.QWidget( self.common )
        self.layoutWidget.setGeometry( QtCore.QRect( 10, 10, 261, 92 ) )
        self.layoutWidget.setObjectName( 'layoutWidget' )
    
        self.common_layout = QtGui.QVBoxLayout( self.layoutWidget )
        self.common_layout.setObjectName( 'common_layout' )
    
        self.server_label = QtGui.QLabel( self.layoutWidget )
        self.server_label.setObjectName( 'server_label')
    
        self.common_layout.addWidget( self.server_label )
    
        self.server_box = QtGui.QComboBox( self.layoutWidget )
        self.server_box.setObjectName( 'server_box' )
        self.server_box.addItem( '' )
    
        self.common_layout.addWidget( self.server_box )
    
        self.port_label = QtGui.QLabel( self.layoutWidget )
        self.port_label.setObjectName( 'port_label' )
    
        self.common_layout.addWidget( self.port_label )
    
        self.port_box = QtGui.QComboBox( self.layoutWidget )
        self.port_box.setObjectName( 'port_box' )
        self.port_box.addItem( '' )
        self.port_box.addItem( '' )
    
        self.common_layout.addWidget( self.port_box )
    
        self.tab_widget_pref.addTab( self.common, '' )
    
        self.simdata = QtGui.QWidget()
        self.simdata.setObjectName( 'simdata' )
    
        self.layoutWidget1 = QtGui.QWidget( self.simdata )
        self.layoutWidget1.setGeometry( QtCore.QRect( 10, 10, 261, 261 ) )
        self.layoutWidget1.setObjectName( 'layoutWidget1' )
    
        self.simdata_layout = QtGui.QVBoxLayout( self.layoutWidget1 )
        self.simdata_layout.setObjectName( 'simdata_layout' )
    
        self.simdata_layout_pref = QtGui.QVBoxLayout()
        self.simdata_layout_pref.setObjectName( 'simdata_layout_pref' )
    
        self.koerper_label = QtGui.QLabel( self.layoutWidget1 )
        self.koerper_label.setObjectName( 'koerper_label' )
    
        self.simdata_layout_pref.addWidget( self.koerper_label )
    
        self.koerper_box = QtGui.QSpinBox( self.layoutWidget1 )
        self.koerper_box.setMinimum( 1 )
        self.koerper_box.setMaximum( 999 )
        self.koerper_box.setProperty( 'value', 1 )
        self.koerper_box.setObjectName( 'koerper_box' )
    
        self.simdata_layout_pref.addWidget( self.koerper_box )
        
        self.simduration_label = QtGui.QLabel( self.layoutWidget1 )
        self.simduration_label.setObjectName( 'simduration_label' )
    
        self.simdata_layout_pref.addWidget( self.simduration_label )
    
        self.simduration_box = QtGui.QSpinBox( self.layoutWidget1 )
        self.simduration_box.setMinimum( 1 )
        self.simduration_box.setMaximum( 36500 )
        self.simduration_box.setProperty( 'value', 365 )
        self.simduration_box.setObjectName( 'simduration_box' )
    
        self.simdata_layout_pref.addWidget( self.simduration_box )
    
        self.masskoerper_label = QtGui.QLabel( self.layoutWidget1 )
        self.masskoerper_label.setObjectName( 'masskoerper_label' )
    
        self.simdata_layout_pref.addWidget( self.masskoerper_label )
    
        self.horizontalLayout = QtGui.QHBoxLayout()
        self.horizontalLayout.setObjectName( 'horizontalLayout' )
    
        self.masskoerper_box_label = QtGui.QLabel( self.layoutWidget1 )
        self.masskoerper_box_label.setAlignment( QtCore.Qt.AlignRight|QtCore.Qt.AlignTrailing|QtCore.Qt.AlignVCenter )
        self.masskoerper_box_label.setObjectName( 'masskoerper_box_label' )
    
        self.horizontalLayout.addWidget( self.masskoerper_box_label )
    
        self.masskoerper_box = QtGui.QSpinBox( self.layoutWidget1 )
        self.masskoerper_box.setMinimum( 1 )
        self.masskoerper_box.setProperty( 'value', 26 )
        self.masskoerper_box.setObjectName( 'masskoerper_box' )
    
        self.horizontalLayout.addWidget( self.masskoerper_box )
    
        self.simdata_layout_pref.addLayout( self.horizontalLayout )
    
        self.masscentral_label = QtGui.QLabel( self.layoutWidget1 )
        self.masscentral_label.setObjectName( 'masscentral_label' )
    
        self.simdata_layout_pref.addWidget( self.masscentral_label )
    
        self.horizontalLayout_2 = QtGui.QHBoxLayout()
        self.horizontalLayout_2.setObjectName( 'horizontalLayout_2' )
    
        self.masscentral_box_label = QtGui.QLabel( self.layoutWidget1 )
        self.masscentral_box_label.setAlignment( QtCore.Qt.AlignRight|QtCore.Qt.AlignTrailing|QtCore.Qt.AlignVCenter )
        self.masscentral_box_label.setObjectName( 'masscentral_box_label' )
    
        self.horizontalLayout_2.addWidget( self.masscentral_box_label )
    
        self.masscentral_box = QtGui.QSpinBox( self.layoutWidget1 )
        self.masscentral_box.setMinimum( 1 )
        self.masscentral_box.setProperty( 'value', 30 )
        self.masscentral_box.setObjectName( 'masscentral_box' )
    
        self.horizontalLayout_2.addWidget( self.masscentral_box )
    
        self.simdata_layout_pref.addLayout( self.horizontalLayout_2 )
    
        spacerItem = QtGui.QSpacerItem( 20, 40, QtGui.QSizePolicy.Minimum, QtGui.QSizePolicy.Expanding )
    
        self.simdata_layout_pref.addItem( spacerItem )
    
        self.simdata_layout.addLayout( self.simdata_layout_pref )
    
        self.simdata_layout_buttons = QtGui.QHBoxLayout()
        self.simdata_layout_buttons.setObjectName( 'simdata_layout_buttons' )
    
        spacerItem1 = QtGui.QSpacerItem( 40, 20, QtGui.QSizePolicy.Expanding, QtGui.QSizePolicy.Minimum )
    
        self.simdata_layout_buttons.addItem( spacerItem1 )
    
        self.simdata_layout_buttons_pref = QtGui.QVBoxLayout()
        self.simdata_layout_buttons_pref.setObjectName( 'simdata_layout_buttons_pref' )

        self.start_button = QtGui.QPushButton( self.layoutWidget1 )
        self.start_button.setIcon( icon1 )
        self.start_button.setObjectName( 'start_button' )
    
        self.simdata_layout_buttons_pref.addWidget( self.start_button )
    
        self.default_button = QtGui.QPushButton( self.layoutWidget1 )
        self.default_button.setObjectName( 'default_button' )
    
        self.simdata_layout_buttons_pref.addWidget( self.default_button )
    
        self.simdata_layout_buttons.addLayout( self.simdata_layout_buttons_pref )
    
        self.simdata_layout.addLayout( self.simdata_layout_buttons )
    
        self.tab_widget_pref.addTab( self.simdata, "" )
    
        self.tab_widget_help = QtGui.QTabWidget( self.centralwidget )
        self.tab_widget_help.setGeometry( QtCore.QRect( 10, 330, 291, 171 ) )
        self.tab_widget_help.setObjectName( 'tab_widget_help' )
    
        self.information = QtGui.QWidget()
        self.information.setObjectName( 'information' )
    
        self.layoutWidget2 = QtGui.QWidget( self.information )
        self.layoutWidget2.setGeometry( QtCore.QRect( 10, 10, 270, 121 ) )
        self.layoutWidget2.setObjectName( 'layoutWidget2' )
    
        self.information_layout = QtGui.QVBoxLayout( self.layoutWidget2 )
        self.information_layout.setObjectName( 'information_layout' )
    
        self.information_edit = QtGui.QTextEdit( self.layoutWidget2 )
        self.information_edit.setReadOnly( True )
        self.information_edit.setTabStopWidth( 55 )
        self.information_edit.setObjectName( 'information_edit' )
    
        self.information_layout.addWidget( self.information_edit )
    
        self.impuls_layout = QtGui.QHBoxLayout()
        self.impuls_layout.setObjectName( 'impuls_layout' )
    
        self.impuls_label = QtGui.QLabel( self.layoutWidget2 )
        self.impuls_label.setObjectName( 'impuls_label' )
    
        self.impuls_layout.addWidget( self.impuls_label )
    
        self.impuls_edit = QtGui.QLineEdit( self.layoutWidget2 )
        self.impuls_edit.setObjectName( 'impuls_edit' )
    
        self.impuls_layout.addWidget( self.impuls_edit )
    
        self.information_layout.addLayout( self.impuls_layout )
    
        self.tab_widget_help.addTab( self.information, '' )
    
        self.help = QtGui.QWidget()
        self.help.setObjectName( 'help' )
    
        self.layoutWidget3 = QtGui.QWidget( self.help )
        self.layoutWidget3.setGeometry( QtCore.QRect( 10, 10, 261, 118 ) )
        self.layoutWidget3.setObjectName( 'layoutWidget3' )
    
        self.help_layout = QtGui.QVBoxLayout( self.layoutWidget3 )
        self.help_layout.setObjectName( 'help_layout' )
    
        self.help_label = QtGui.QLabel( self.layoutWidget3 )
        self.help_label.setAlignment( QtCore.Qt.AlignJustify|QtCore.Qt.AlignVCenter )
        self.help_label.setWordWrap( True )
        self.help_label.setObjectName( 'help_label' )
    
        self.help_layout.addWidget( self.help_label )
    
        self.help_button_layout = QtGui.QHBoxLayout()
        self.help_button_layout.setObjectName( 'help_button_layout' )
    
        spacerItem2 = QtGui.QSpacerItem( 40, 20, QtGui.QSizePolicy.Expanding, QtGui.QSizePolicy.Minimum )
    
        self.help_button_layout.addItem( spacerItem2 )

        self.help_button = QtGui.QPushButton( self.layoutWidget3 )
        self.help_button.setIcon( icon2 )
        self.help_button.setObjectName( 'help_button' )
    
        self.help_button_layout.addWidget( self.help_button )
    
        self.help_layout.addLayout( self.help_button_layout )
    
        self.tab_widget_help.addTab( self.help, '' )
    
        self.widget = QtGui.QWidget( self.centralwidget )
        self.widget.setGeometry( QtCore.QRect( 310, 0, 481, 501 ) )
        self.widget.setObjectName( 'widget' )
    
        self.sim_layout = QtGui.QVBoxLayout( self.widget )
        self.sim_layout.setObjectName( 'sim_layout' )
    
        self.logo_label = QtGui.QLabel( self.widget )
        self.logo_label.setPixmap( QtGui.QPixmap( 'icons/application_logo.png' ) )
        self.logo_label.setObjectName( 'logo_label' )
    
        self.sim_layout.addWidget( self.logo_label )
    
        self.qvtk_widget = QVTKRenderWindowInteractor.QVTKRenderWindowInteractor( self.widget )
        self.qvtk_widget.setObjectName( 'qvtk_widget' )
        
        self.sphere = vtk.vtkSphereSource()
        self.sphere.SetRadius( 1.0 )
        self.sphere.SetPhiResolution( 100 )
        self.sphere.SetThetaResolution( 100 )
        
        self.sphereMapper = vtk.vtkPolyDataMapper()
        self.sphereMapper.SetInputConnection( self.sphere.GetOutputPort() )
        
        self.sphereActor = vtk.vtkActor()
        self.sphereActor.SetMapper( self.sphereMapper )
        
        self.sphereActorCentral = vtk.vtkActor()
        self.sphereActorCentral.SetMapper( self.sphereMapper )
        
        self.sphereActorCentral.GetProperty().SetDiffuseColor(1, 1, 0)
        
        self.ren1 = vtk.vtkRenderer()
        self.ren1.AddActor( self.sphereActor )
        self.ren1.AddActor( self.sphereActorCentral )
        self.ren1.SetBackground( 0.1, 0.2, 0.4 )
        
        self.light = vtk.vtkLight()
        self.light.SetPosition( - 200, - 200, 200 )
        
        self.ren1.AddLight( self.light )
        
        self.cam = self.ren1.GetActiveCamera()
        self.cam.SetPosition( 0, - 50, 50 )
        self.cam.SetFocalPoint( 0, 0, 0 )
        self.cam.SetViewUp( 0, 1, 0 )
        self.cam.SetViewAngle( 40 )
        
        self.ren1.SetActiveCamera( self.cam )

        self.sphere.SetRadius( 2 )
        
        self.renderWindow = self.qvtk_widget.GetRenderWindow()
        self.renderWindow.AddRenderer( self.ren1 )
    
        self.sim_layout.addWidget( self.qvtk_widget )
    
        self.setCentralWidget( self.centralwidget )
        
        # ===
        # MainWindow MenuBar
        # ===
        self.menubar = QtGui.QMenuBar( self )
        self.menubar.setGeometry( QtCore.QRect( 0, 0, 800, 21 ) )
        self.menubar.setObjectName( 'menubar' )
    
        self.menu_simulation = QtGui.QMenu( self.menubar )
        self.menu_simulation.setObjectName( 'menu_simulation' )
    
        self.menu_help = QtGui.QMenu( self.menubar )
        self.menu_help.setObjectName( 'menu_help' )
    
        self.start = QtGui.QAction( self )
        self.start.setIcon( icon1 )
        self.start.setObjectName( 'start' )

        self.exit = QtGui.QAction( self )
        self.exit.setIcon( icon3 )
        self.exit.setObjectName( 'exit' )
    
        self.handbook = QtGui.QAction( self )
        self.handbook.setIcon( icon2 )
        self.handbook.setObjectName( 'handbook' )

        self.actionInfo = QtGui.QAction( self )
        self.actionInfo.setIcon( icon4 )
        self.actionInfo.setObjectName( 'actionInfo' )
    
        self.menu_simulation.addAction( self.start )
        self.menu_simulation.addSeparator()
        self.menu_simulation.addAction( self.exit )
    
        self.menu_help.addAction( self.handbook )
        self.menu_help.addAction( self.actionInfo )
    
        self.menubar.addAction( self.menu_simulation.menuAction() )
        self.menubar.addAction( self.menu_help.menuAction() )
    
        self.setMenuBar( self.menubar )
        
        # ===
        # MainWindow StatusBar
        # ===
        self.statusbar = QtGui.QStatusBar( self )
        self.statusbar.setObjectName( 'statusbar' )
    
        self.setStatusBar( self.statusbar )
        
        # ===
        # MainWindow ToolBar
        # ===
        self.toolBar = QtGui.QToolBar( self )
        self.toolBar.setObjectName( 'toolBar' )
    
        self.addToolBar( QtCore.Qt.TopToolBarArea, self.toolBar )
    
        self.toolBar.addAction( self.start )
        self.toolBar.addSeparator()
        self.toolBar.addAction( self.handbook )
        self.toolBar.addAction( self.actionInfo )
        self.toolBar.addSeparator()
        self.toolBar.addAction( self.exit )
        
        # ===
        # Übersetzung des MainWindows, Tabreihenfolge festlegen und darstellen
        # ===
        self.retranslateUi( self )
        self.tab_widget_pref.setCurrentIndex( 1 )
        self.tab_widget_help.setCurrentIndex( 0 )
        self.show()
        
        # ===
        # Signal/Slot Mechanismus
        # ===
        self.connect( self.exit, QtCore.SIGNAL( 'triggered()' ), self.onExit )
        self.connect( self.default_button, QtCore.SIGNAL( 'clicked()' ), self.onDefault )
        #self.connect( self.start_button, QtCore.SIGNAL( 'clicked()' ), self.onStart )
        #self.connect( self.start, QtCore.SIGNAL( 'triggered()' ), self.onStart )
        self.connect( self.actionInfo, QtCore.SIGNAL( 'triggered()' ), self.onDialog )
        self.connect( self.start_button, QtCore.SIGNAL( 'clicked()' ), self.displayVtk )


    def onDefault( self ):
        
        self.koerper_box.setProperty( 'value', 1 )
        self.simduration_box.setProperty( 'value', 365 )
        self.masskoerper_box.setProperty( 'value', 26 )
        self.masscentral_box.setProperty( 'value', 30 )
        
        
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
            management.initManagement( self.koerper_box.value(), self.simduration_box.value(), self.masskoerper_box.value() * 1.0, self.masscentral_box.value() * 1.0 )
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
    
    
    def displayVtk( self ):
        
        timeStep = 0.01
        currentTime = 0
        simulationTime = 30
        
        while currentTime < simulationTime:
            time.sleep( timeStep )
            currentTime += timeStep
            pX, pZ = 20. * math.cos( currentTime ), 20. * math.sin( currentTime ) 
            self.sphereActor.SetPosition( pX, 0, pZ )
            self.renderWindow.Render()
            self.qvtk_widget.update()
        
        
    def onDialog( self ):
        
        d = InfoDialog()
        d.exec_()
        
        
    def onExit( self ):
        
        ret = QtGui.QMessageBox.question( None, "Ende?", "Wollen Sie wirklich schon gehen?", QtGui.QMessageBox.Yes, QtGui.QMessageBox.No )
        
        if ret == QtGui.QMessageBox.Yes:
            self.close()
        
        
    def retranslateUi( self, MainWindow ):

        MainWindow.setWindowTitle( QtGui.QApplication.translate( 'MainWindow', 'Mehrkörpersimulation | HS Augsburg', None, QtGui.QApplication.UnicodeUTF8 ) )
        MainWindow.setStatusTip( QtGui.QApplication.translate( 'MainWindow', 'Bereit...', None, QtGui.QApplication.UnicodeUTF8 ) )
    
        self.server_label.setText( QtGui.QApplication.translate( 'MainWindow', 'Auswahl des Servers:', None, QtGui.QApplication.UnicodeUTF8 ) )
        self.server_box.setItemText( 0, QtGui.QApplication.translate( 'MainWindow', 'kronosGRBV.informatik.fh-augsburg.de', None, QtGui.QApplication.UnicodeUTF8 ) )
    
        self.port_label.setText( QtGui.QApplication.translate( 'MainWindow', 'Port des zu verwendenen Servers:', None, QtGui.QApplication.UnicodeUTF8 ) )
        self.port_box.setItemText( 0, QtGui.QApplication.translate( 'MainWindow', '10003', None, QtGui.QApplication.UnicodeUTF8 ) )
        self.port_box.setItemText( 1, QtGui.QApplication.translate( 'MainWindow', '10005', None, QtGui.QApplication.UnicodeUTF8 ) )
    
        self.tab_widget_pref.setTabText( self.tab_widget_pref.indexOf( self.common ), QtGui.QApplication.translate( 'MainWindow', 'Einstellungen', None, QtGui.QApplication.UnicodeUTF8 ) )
    
        self.koerper_label.setText( QtGui.QApplication.translate( 'MainWindow', 'Körper die simuliert werden sollen:', None, QtGui.QApplication.UnicodeUTF8 ) )
    
        self.simduration_label.setText( QtGui.QApplication.translate( 'MainWindow', 'Simulationsdauer (in Tagen):', None, QtGui.QApplication.UnicodeUTF8 ) )
    
        self.masskoerper_label.setText( QtGui.QApplication.translate( 'MainWindow', 'Massebereich der Körper:', None, QtGui.QApplication.UnicodeUTF8 ) )
        self.masskoerper_box_label.setText( QtGui.QApplication.translate( 'MainWindow', '10^', None, QtGui.QApplication.UnicodeUTF8 ) )
    
        self.masscentral_label.setText( QtGui.QApplication.translate( 'MainWindow', 'Massebereich des Zentralkörpers:', None, QtGui.QApplication.UnicodeUTF8 ) )
        self.masscentral_box_label.setText( QtGui.QApplication.translate( 'MainWindow', '10^', None, QtGui.QApplication.UnicodeUTF8 ) )
    
        self.start_button.setText( QtGui.QApplication.translate( 'MainWindow', 'Simulation starten', None, QtGui.QApplication.UnicodeUTF8 ) )
        self.default_button.setText( QtGui.QApplication.translate( 'MainWindow', 'Standardwerte laden', None, QtGui.QApplication.UnicodeUTF8 ) )
    
        self.tab_widget_pref.setTabText( self.tab_widget_pref.indexOf( self.simdata ), QtGui.QApplication.translate( 'MainWindow', 'Simulationsangaben', None, QtGui.QApplication.UnicodeUTF8 ) )
        self.information_edit.setHtml( QtGui.QApplication.translate( 'MainWindow', 'Diese Anwendung entstandt während des Praktikums an der HS Augsburg im 3. Semester im Fach Programmieren 3. Das Programm soll eine schrittweise Simulation eines Mehrkörpersystems simulieren.', None, QtGui.QApplication.UnicodeUTF8 ) )
    
        self.impuls_label.setText( QtGui.QApplication.translate( 'MainWindow', 'Gesamtimpuls (kg*m / s):', None, QtGui.QApplication.UnicodeUTF8 ) )
    
        self.tab_widget_help.setTabText( self.tab_widget_help.indexOf( self.information ), QtGui.QApplication.translate( 'MainWindow', 'Information', None, QtGui.QApplication.UnicodeUTF8 ) )
    
        self.help_label.setText( QtGui.QApplication.translate( 'MainWindow', 'In der Online Dokumentation finden Sie Tipps und Hilfe zu den Einstellungen und zur Bedienung der Anwendung. Besuchen Sie die Dokumentation in dem Sie auf den Button klicken.', None, QtGui.QApplication.UnicodeUTF8 ) )
        self.help_button.setText( QtGui.QApplication.translate( 'MainWindow', 'Hilfe', None, QtGui.QApplication.UnicodeUTF8 ) )
    
        self.tab_widget_help.setTabText( self.tab_widget_help.indexOf( self.help ), QtGui.QApplication.translate( 'MainWindow', 'Hilfe', None, QtGui.QApplication.UnicodeUTF8 ) )
    
        self.menu_simulation.setTitle( QtGui.QApplication.translate( 'MainWindow', '&Simulation', None, QtGui.QApplication.UnicodeUTF8 ) )
        self.menu_help.setTitle( QtGui.QApplication.translate( 'MainWindow', '&Hilfe', None, QtGui.QApplication.UnicodeUTF8 ) )
    
        self.toolBar.setWindowTitle( QtGui.QApplication.translate( 'MainWindow', 'toolBar', None, QtGui.QApplication.UnicodeUTF8 ) )
    
        self.start.setText( QtGui.QApplication.translate( 'MainWindow', 'Simulation starten', None, QtGui.QApplication.UnicodeUTF8 ) )
        self.start.setStatusTip( QtGui.QApplication.translate( 'MainWindow', 'Mit der Simulation beginnen', None, QtGui.QApplication.UnicodeUTF8 ) )
        self.start.setShortcut( QtGui.QApplication.translate( 'MainWindow', 'Ctrl+S', None, QtGui.QApplication.UnicodeUTF8 ) )
    
        self.exit.setText( QtGui.QApplication.translate( 'MainWindow', 'Beenden', None, QtGui.QApplication.UnicodeUTF8 ) )
        self.exit.setStatusTip( QtGui.QApplication.translate( 'MainWindow', 'Programm beenden', None, QtGui.QApplication.UnicodeUTF8 ) )
        self.exit.setShortcut( QtGui.QApplication.translate( 'MainWindow', 'Ctrl+X', None, QtGui.QApplication.UnicodeUTF8 ) )
    
        self.handbook.setText( QtGui.QApplication.translate( 'MainWindow', 'Online Handbuch', None, QtGui.QApplication.UnicodeUTF8 ) )
        self.handbook.setStatusTip( QtGui.QApplication.translate( 'MainWindow', 'Im Online Handbuch lesen', None, QtGui.QApplication.UnicodeUTF8 ) )
        self.handbook.setShortcut( QtGui.QApplication.translate( 'MainWindow', 'Ctrl+H', None, QtGui.QApplication.UnicodeUTF8 ) )
    
        self.actionInfo.setText( QtGui.QApplication.translate( 'MainWindow', 'Info', None, QtGui.QApplication.UnicodeUTF8 ) )
        self.actionInfo.setStatusTip( QtGui.QApplication.translate( 'MainWindow', 'Information anzeigen', None, QtGui.QApplication.UnicodeUTF8 ) )
        self.actionInfo.setShortcut( QtGui.QApplication.translate( 'MainWindow', 'Ctrl+I', None, QtGui.QApplication.UnicodeUTF8 ) )
            

if __name__ == '__main__':
    
    app = QtGui.QApplication( sys.argv )
    start = MainWindow()
    app.exec_()
