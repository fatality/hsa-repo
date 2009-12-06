'''
Created on 05.12.2009

@author: stud
'''

class Datum09():
    
    """
    Klasse wird aufgerufen in der konsole:
    from Datum09 import Datum09
    x=Datum09()
    x.methodenname
    """

    
    def __init__(self):
            """__ bewirkt das die Variablen privatisiert werden """
            self.__Tag = 7
            self.__Monat = 12
            self.__WochenTage = {0 : 'Donnerstag', 1: 'Freitag' , 2 :'Samstag',
                               3 :'Sonntag', 4 : 'Montag', 5 : 'Dienstag',
                               6 : 'Mittwoch'}   
            self.hilfsVariablen = (31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
    
    def wochenTag(self):
        
        tage = 0
       
        for i in range(self.__Monat-1):
            tage += self.hilfsVariablen[i]
           
        tage += self.__Tag-1
        print self.__WochenTage[tage % 7]
        
        
    def addiereTage(self, tage):
        if(tage >= 0):
            while(tage + self.__Tag > self.hilfsVariablen[self.__Monat-1]):
                self.__Monat +=1
                if (self.__Monat > 12):
                    print "Nur fuers Jahr 2009! Nicht fuer 2010!"
                    self.__Monat = 12
                    self.__Tag = 31
                    break
                tage -= self.hilfsVariablen[self.__Monat-1]
            self.__Tag += tage
        if(tage < 0):
            while(tage + self.__Tag < 1):
                self.__Monat-=1
                if(self.__Monat < 1):
                    print "Nur fuers Jahr 2009! Nicht fuer 2008!"
                    self.__Monat = 1
                    self.__Tag = 1
                    break
                tage += self.hilfsVariablen[self.__Monat-1]
            self.__Tag +=tage  
        
            
       
    def ausgabe(self):
        self.wochenTag(); print str(self.__Tag) + '.' +  str(self.__Monat)  +'.'   