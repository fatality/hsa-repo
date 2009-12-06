#!/usr/bin/python
# -*- coding: utf-8 -*-
#
# @author: fatality
#
# File: TkInterTest.py

from Tkinter import *

class App:
    def __init__(self, master):
        frame = Frame(master)
        frame.pack()
        
        self.button = Button(frame, text="Schließen", fg="red", command=frame.quit)
        self.button.pack(side=LEFT)
        
        self.hi_there = Button(frame, text="Hallo Welt", fg="black", command=self.say_hi)
        self.hi_there.pack(side=LEFT)
        
    def say_hi(self):
        print "Hallo ihr da draußen!"

root = Tk()

app = App(root)

root.mainloop()
