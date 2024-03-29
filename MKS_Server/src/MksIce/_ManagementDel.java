// **********************************************************************
//
// Copyright (c) 2003-2009 ZeroC, Inc. All rights reserved.
//
// This copy of Ice is licensed to you under the terms described in the
// ICE_LICENSE file included in this distribution.
//
// **********************************************************************

// Ice version 3.3.1

package MksIce;

public interface _ManagementDel extends Ice._ObjectDel
{
    void initManagement(int NumberOfPlanets, int simDuration, double maxPlanetMass, double centralStarMass, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    String getData(java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;
}
