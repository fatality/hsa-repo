'''
Created on 05.12.2009

@author: stud
'''

class Datum09():
    

    
    def __init__(self):
            """__ bewirkt das die Variablen privatisiert werden """
            self.Tag = 31
            self.Monat = 12
            self.WochenTage = {0 : 'Donnerstag', 1: 'Freitag' , 2 :'Samstag',
                               3 :'Sonntag', 4 : 'Montag', 5 : 'Dienstag',
                               6 : 'Mittwoch'}   
            self.hilfsVariablen = (31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
    
    def wochenTag(self):
        
        tage = 0
       
        for i in range(self.Monat-1):
            tage += self.hilfsVariablen[i]
           
        tage += self.Tag-1
        print self.WochenTage[tage % 7]
        
        
    def addiereTage(self, tage):
        while(tage + self.Tag > self.hilfsVariablen[self.Monat-1]):
            self.Monat +=1
            if (self.Monat > 12):
                print "So gehts aber nicht! Nur fuers Jahr 2009!"
                self.Monat = 12
                self.Tag = 31
                break
            tage -= self.hilfsVariablen[self.Monat-1]
            self.Tag += tage
            
       
    def ausgabe(self):
        self.wochenTag(); print str(self.Tag) + '. ' +  str(self.Monat)     