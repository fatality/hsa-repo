package core;



public class StartSimI extends Ice._StartSimDisp{
    public void
    doSimulation(int numberofplanets, Ice.Current current)
    {
        Management man = new Management(numberofplanets);
        man.doSim();
    }
}

