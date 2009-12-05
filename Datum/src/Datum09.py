'''
Created on 05.12.2009

@author: stud
'''

class Datum09():
    
    def __init__(self):
        self.aktuellerMonat = 12
        self.WochenTage = {'Montag': 0, 'Dienstag': 1, 'Mittwoch': 2,
                           'Donnerstag': 3, 'Freitag': 4, 'Samstag': 5,
                           'Sonntag': 6}    
        StartTag = 1
        StartMonat = 1
        StartWochenTag = 3
        aktuellerTag = 7
        hilfsVariablen = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]
    
    def wochenTag(self):
        monate = Datum09.aktuellerMonat
        tage = 3 + Datum09.aktuellerTag
        while monate >= 0:
            tage += Datum09.hilfsVariablen(monate)
            monate -= 1
            
        print Datum09.WochenTage(tage % 7)