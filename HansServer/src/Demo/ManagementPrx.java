// **********************************************************************
//
// Copyright (c) 2003-2007 ZeroC, Inc. All rights reserved.
//
// This copy of Ice is licensed to you under the terms described in the
// ICE_LICENSE file included in this distribution.
//
// **********************************************************************

// Ice version 3.2.1

package Demo;

public interface ManagementPrx extends Ice.ObjectPrx
{
    public void initManagement(int NumberOfPlanets, int simDuration, double maxPlanetMass, double centralStarMass);
    public void initManagement(int NumberOfPlanets, int simDuration, double maxPlanetMass, double centralStarMass, java.util.Map<String, String> __ctx);
}
