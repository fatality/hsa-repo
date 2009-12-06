#!/usr/bin/python
# -*- coding: utf-8 -*-
#
# @author: fatality
#
# File: TkInterTest2.py

import vtk
import math
import time

from Tkinter import *
from vtk.tk.vtkTkRenderWidget import vtkTkRenderWidget

#vtk implementation
sphere = vtk.vtkSphereSource()
sphere.SetRadius(5.0)
sphere.SetPhiResolution(20)
sphere.SetThetaResolution(20)
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
#ren.SetBackground(0.1, 0.2, 0.4)
sphereActor3.GetProperty().SetDiffuseColor(3, 1, 0)
sphereActor2.GetProperty().SetDiffuseColor(0, 3, 1)
sphereActor.GetProperty().SetDiffuseColor(0, 1, 3)
cam = ren.GetActiveCamera()
cam.SetPosition(0, -200, 0)
cam.SetFocalPoint(0, 0, 0)
cam.SetViewUp(0, 0, 1)
cam.SetViewAngle(40)
ren.SetActiveCamera(cam)

# layout
master = Tk()
master.title('Mehrkoerpersimulation')
master.geometry('800x600')

entryFrame = Frame(master)
entryFrame.pack(side=LEFT)

entryTopFrame = Frame(entryFrame)
entryTopFrame.pack(side=TOP, padx=20)

entryBottomFrame = Frame(entryFrame)
entryBottomFrame.pack(side=BOTTOM)

simulationFrame = Frame(master)
simulationFrame.pack(side=RIGHT, padx=20)

renderWidget = vtkTkRenderWidget(simulationFrame, width=400, height=400)
ren.SetBackground(0.1, 0.2, 0.4)
renderWindow = renderWidget.GetRenderWindow()
renderWindow.AddRenderer(ren)
renderWindow.SetSize(300, 300)

loopRunning = False
exitRequested = False
currentTime=0.0
timeStep=0.01

# quit function
def quit():
    global exitRequested, loopRunning, master, vis
    if loopRunning:
        exitRequested = True    
    else:
        master.quit()
#
# sphere loop
def sphereLoop():
    global loopRunning, master, exitRequested
    loopRunning = True
    while not exitRequested:
        global vis, currentTime, timeStep
        time.sleep(timeStep)
        currentTime += timeStep
        pX, pZ = 30. * math.cos(currentTime), 30. * math.sin(currentTime)
        pX2, pZ2 = 40. * math.cos(currentTime+3), 40. * math.sin(currentTime+3) 
        sphereActor.SetPosition(pX, 100, pZ)
        sphereActor2.SetPosition(pX2, 50, pZ2)
        renderWindow.Render()
        master.update()
    master.quit()

w0 = Label(entryTopFrame, text='Mehrkoerpersimulation...', font=('Arial', 16))
w0.pack(side=TOP, pady=5, fill=None)

w1 = Label(entryTopFrame, text='zu simulierende Planeten', font=('Arial', 10))
w1.pack(side=LEFT, pady=5, fill=None)

w2 = Spinbox(entryTopFrame, from_=1, to=100, font=('Arial', 10), width=10)
w2.pack(side=LEFT, fill=None, pady=5)

w3 = Button(entryBottomFrame, text='Simulation starten', command=sphereLoop)
w3.pack(side=LEFT, fill=None)

w4 = Button(entryBottomFrame, text='Beenden', command=quit)
w4.pack(fill=None)

renderWidget.pack(expand='true', fill='both', pady=5)

mainloop()