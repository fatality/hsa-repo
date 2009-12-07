#!/usr/bin/python
# -*- coding: utf-8 -*-
#
# file: MksClient.py
#

import vtk
import math
import time

from Tkinter import *
from vtk.tk.vtkTkRenderWidget import vtkTkRenderWidget


#===============================================================================
# Öffnen der Textdateien
# Wird später durch Internet Communication Engine (ICE, www.zeroc.com)
#===============================================================================

xfile = open( '../px.txt' )
yfile = open( '../py.txt' )

xList = []
yList = []

while True:
    x = xfile.readline()
    if len( x ) == 0:
        break
    xList.append( x )

while True:
    y = yfile.readline()
    if len( y ) == 0:
        break
    yList.append(y)

xList.reverse()
yList.reverse()

xfile.close()
yfile.close()


#===============================================================================
# VTK (Visualization Toolkit, www.vtk.org) Implementation
# Wird zur Visualisierung der Simulation benutzt
#===============================================================================

sphere = vtk.vtkSphereSource()
sphere.SetRadius( 5.0 )
sphere.SetPhiResolution( 20 )
sphere.SetThetaResolution( 20 )

sphereMapper = vtk.vtkPolyDataMapper()
sphereMapper.SetInputConnection(sphere.GetOutputPort())

sphereActor = vtk.vtkActor()
sphereActor2 = vtk.vtkActor()
sphereActor3 = vtk.vtkActor()

sphereActor.SetMapper(sphereMapper)
sphereActor2.SetMapper(sphereMapper)
sphereActor3.SetMapper(sphereMapper)

ren = vtk.vtkRenderer()
ren.AddActor(sphereActor)
ren.AddActor(sphereActor2)
ren.AddActor(sphereActor3)

sphereActor3.GetProperty().SetDiffuseColor( 3, 1, 0 )
sphereActor2.GetProperty().SetDiffuseColor( 0, 3, 1 )
sphereActor.GetProperty().SetDiffuseColor( 0, 1, 3 )

cam = ren.GetActiveCamera()
cam.SetPosition( 0, -200, 0 )
cam.SetFocalPoint( 0, 0, 0 )
cam.SetViewUp( 0, 0, 1 )
cam.SetViewAngle( 40 )
ren.SetActiveCamera( cam )


#===============================================================================
# Setzen der Variablen zur Simulation
#===============================================================================

loopRunning = False
exitRequested = False
currentTime = 0.0
timeStep = 0.01


#===============================================================================
# Implementierung der Funktion 'Programm beenden'
#===============================================================================

def quit():
  
    global exitRequested, loopRunning, master, vis
  
    if loopRunning:
        exitRequested = True
    else:
        master.quit()


#===============================================================================
# Implementierung der Funktion zur Ausgabe der Simulation
#===============================================================================

def sphereLoop():

    global loopRunning, master, exitRequested

    loopRunning = True

    while not exitRequested:
        global vis, currentTime, timeStep

        time.sleep( timeStep )
        currentTime += timeStep

        x = float( xList.pop() ) / 9000000000
        y = float( yList.pop() ) / 9000000000

        sphereActor.SetPosition( x, 100, y )
        renderWindow.Render()
        master.update()
    master.quit()


#===============================================================================
# Implementation von TkInter
# Wird später hoffentlich von QT ersetzt mit PyQt4
#===============================================================================

master = Tk()
master.title( 'Mehrkörpersimulation' )
master.geometry( '900x500' )

welcomeWidget = Label( master, text = 'Willkommen zur Mehrkörpersimulations-Software!', font = ( 'Arial', 20, 'bold' ) )
welcomeWidget.grid(row = 0, columnspan = 3, sticky = W+E+N+S, pady = 20 )

planetsWidget = Label( master, text = 'zu simulierende Planeten:', font = ( 'Arial', 10 ) )
planetsWidget.grid(row = 1, column = 0, sticky = W+S)

planetsSpinWidget = Spinbox( master, from_ = 1, to = 100, font = ( 'Arial', 10 ), width = 10 )
planetsSpinWidget.grid( row = 1, column = 1, sticky = E+S )

renderWidget = vtkTkRenderWidget( master, width = 500, height = 400 )
renderWidget.grid( row = 1, rowspan = 6, column = 2, sticky = W+E+N+S, padx = 30, pady = 5 )
renderWindow = renderWidget.GetRenderWindow()
renderWindow.AddRenderer( ren )
renderWindow.SetSize( 500, 400 )

simDurationWidget = Label( master, text = 'Simulationsdauer (in Tagen):', font = ( 'Arial', 10 ) )
simDurationWidget.grid( row = 2, column = 0, sticky = W+N )

simDurationSpinWidget = Spinbox( master, from_ = 365, to = 9999, font = ( 'Arial', 10 ), width = 10 )
simDurationSpinWidget.grid( row = 2, column = 1, sticky = E+N )

massWidget = Label( master, text = 'Massebereich der Planeten:', font = ( 'Arial', 10 ) )
massWidget.grid( row = 3, column = 0, sticky = W+S )

massEntryWidget = Entry( master, font = ( 'Arial', 10 ), width = 12 )
massEntryWidget.grid( row = 3, column = 1, sticky = E+S )

sunWidget = Label( master, text = 'Masse der Sonne:', font = ( 'Arial', 10 ) )
sunWidget.grid( row = 4, column = 0, sticky = W+N )

sunEntryWidget = Entry( master, font = ( 'Arial', 10 ), width = 12 )
sunEntryWidget.grid( row = 4, column = 1, sticky = E+N )

doSimButton = Button( master, text = 'Simulation starten!', command = sphereLoop, font = ( 'Arial', 10 ), width = 14 )
doSimButton.grid( row = 5, column = 1, sticky = E+N )

exitButton = Button( master, text = 'Programm beenden', command = quit, font = ( 'Arial', 10 ), width = 14 )
exitButton.grid( row = 6, column = 1, sticky = E )

mainloop()
