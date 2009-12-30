# **********************************************************************
#
# Copyright (c) 2003-2009 ZeroC, Inc. All rights reserved.
#
# This copy of Ice is licensed to you under the terms described in the
# ICE_LICENSE file included in this distribution.
#
# **********************************************************************

# Ice version 3.3.1
# Generated from file `MKS_Server.ice'

import Ice, IcePy, __builtin__

if not Ice.__dict__.has_key( '_struct_marker' ):
    Ice._struct_marker = object()

# Start of module MksIce
_M_MksIce = Ice.openModule( 'MksIce' )
__name__ = 'MksIce'

if not _M_MksIce.__dict__.has_key( 'Management' ):
    _M_MksIce.Management = Ice.createTempClass()
    class Management( Ice.Object ):
        def __init__( self ):
            if __builtin__.type( self ) == _M_MksIce.Management:
                raise RuntimeError( 'MksIce.Management is an abstract class' )

        def ice_ids( self, current = None ):
            return ( '::Ice::Object', '::MksIce::Management' )

        def ice_id( self, current = None ):
            return '::MksIce::Management'

        def ice_staticId():
            return '::MksIce::Management'
        ice_staticId = staticmethod( ice_staticId )

        #
        # Operation signatures.
        #
        # def initManagement( self, NumberOfPlanets, simDuration, maxPlanetMass, centralStarMass, current = None ):
        # def getData( self, current = None ):

        def __str__( self ):
            return IcePy.stringify( self, _M_MksIce._t_Management )

        __repr__ = __str__

    _M_MksIce.ManagementPrx = Ice.createTempClass()
    class ManagementPrx( Ice.ObjectPrx ):

        def initManagement( self, NumberOfPlanets, simDuration, maxPlanetMass, centralStarMass, _ctx = None ):
            return _M_MksIce.Management._op_initManagement.invoke( self, ( ( NumberOfPlanets, simDuration, maxPlanetMass, centralStarMass ), _ctx ) )

        def getData( self, _ctx = None ):
            return _M_MksIce.Management._op_getData.invoke( self, ( (), _ctx ) )

        def checkedCast( proxy, facetOrCtx = None, _ctx = None ):
            return _M_MksIce.ManagementPrx.ice_checkedCast( proxy, '::MksIce::Management', facetOrCtx, _ctx )
        checkedCast = staticmethod( checkedCast )

        def uncheckedCast( proxy, facet = None ):
            return _M_MksIce.ManagementPrx.ice_uncheckedCast( proxy, facet )
        uncheckedCast = staticmethod( uncheckedCast )

    _M_MksIce._t_ManagementPrx = IcePy.defineProxy( '::MksIce::Management', ManagementPrx )

    _M_MksIce._t_Management = IcePy.defineClass( '::MksIce::Management', Management, (), True, None, (), () )
    Management.ice_type = _M_MksIce._t_Management

    Management._op_initManagement = IcePy.Operation( 'initManagement', Ice.OperationMode.Normal, Ice.OperationMode.Normal, False, (), ( ( (), IcePy._t_int ), ( (), IcePy._t_int ), ( (), IcePy._t_double ), ( (), IcePy._t_double ) ), (), None, () )
    Management._op_getData = IcePy.Operation( 'getData', Ice.OperationMode.Normal, Ice.OperationMode.Normal, False, (), (), (), IcePy._t_string, () )

    _M_MksIce.Management = Management
    del Management

    _M_MksIce.ManagementPrx = ManagementPrx
    del ManagementPrx

# End of module MksIce
