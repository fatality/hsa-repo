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

public final class _ManagementDelM extends Ice._ObjectDelM implements _ManagementDel {
	
    public void initManagement( int NumberOfPlanets, int simDuration, double maxPlanetMass, double centralStarMass, java.util.Map<String, String> __ctx ) throws IceInternal.LocalExceptionWrapper {
        IceInternal.Outgoing __og = __handler.getOutgoing("initManagement", Ice.OperationMode.Normal, __ctx);
        try {
            try {
                IceInternal.BasicStream __os = __og.os();
                __os.writeInt( NumberOfPlanets );
                __os.writeInt( simDuration );
                __os.writeDouble( maxPlanetMass );
                __os.writeDouble( centralStarMass );
            } catch( Ice.LocalException __ex ) {
                __og.abort( __ex );
            }
            boolean __ok = __og.invoke();
            if ( !__og.is().isEmpty() ) {
                try {
                    if ( !__ok ) {
                        try {
                            __og.throwUserException();
                        } catch( Ice.UserException __ex ) {
                            throw new Ice.UnknownUserException( __ex.ice_name() );
                        }
                    }
                    __og.is().skipEmptyEncaps();
                } catch( Ice.LocalException __ex ) {
                    throw new IceInternal.LocalExceptionWrapper( __ex, false );
                }
            }
        } finally {
            __handler.reclaimOutgoing( __og );
        }
    }
    
}
