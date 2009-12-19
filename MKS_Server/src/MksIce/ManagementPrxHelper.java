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

@SuppressWarnings("serial")
public final class ManagementPrxHelper extends Ice.ObjectPrxHelperBase implements ManagementPrx
{
    public void
    initManagement(int NumberOfPlanets, int simDuration, double maxPlanetMass, double centralStarMass)
    {
        initManagement(NumberOfPlanets, simDuration, maxPlanetMass, centralStarMass, null, false);
    }

    public void
    initManagement(int NumberOfPlanets, int simDuration, double maxPlanetMass, double centralStarMass, java.util.Map<String, String> __ctx)
    {
        initManagement(NumberOfPlanets, simDuration, maxPlanetMass, centralStarMass, __ctx, true);
    }

    private void
    initManagement(int NumberOfPlanets, int simDuration, double maxPlanetMass, double centralStarMass, java.util.Map<String, String> __ctx, boolean __explicitCtx)
    {
        if(__explicitCtx && __ctx == null)
        {
            __ctx = _emptyContext;
        }
        int __cnt = 0;
        while(true)
        {
            Ice._ObjectDel __delBase = null;
            try
            {
                __delBase = __getDelegate(false);
                _ManagementDel __del = (_ManagementDel)__delBase;
                __del.initManagement(NumberOfPlanets, simDuration, maxPlanetMass, centralStarMass, __ctx);
                return;
            }
            catch(IceInternal.LocalExceptionWrapper __ex)
            {
                __handleExceptionWrapper(__delBase, __ex, null);
            }
            catch(Ice.LocalException __ex)
            {
                __cnt = __handleException(__delBase, __ex, null, __cnt);
            }
        }
    }

    public static ManagementPrx
    checkedCast(Ice.ObjectPrx __obj)
    {
        ManagementPrx __d = null;
        if(__obj != null)
        {
            try
            {
                __d = (ManagementPrx)__obj;
            }
            catch(ClassCastException ex)
            {
                if(__obj.ice_isA("::MksIce::Management"))
                {
                    ManagementPrxHelper __h = new ManagementPrxHelper();
                    __h.__copyFrom(__obj);
                    __d = __h;
                }
            }
        }
        return __d;
    }

    public static ManagementPrx
    checkedCast(Ice.ObjectPrx __obj, java.util.Map<String, String> __ctx)
    {
        ManagementPrx __d = null;
        if(__obj != null)
        {
            try
            {
                __d = (ManagementPrx)__obj;
            }
            catch(ClassCastException ex)
            {
                if(__obj.ice_isA("::MksIce::Management", __ctx))
                {
                    ManagementPrxHelper __h = new ManagementPrxHelper();
                    __h.__copyFrom(__obj);
                    __d = __h;
                }
            }
        }
        return __d;
    }

    public static ManagementPrx
    checkedCast(Ice.ObjectPrx __obj, String __facet)
    {
        ManagementPrx __d = null;
        if(__obj != null)
        {
            Ice.ObjectPrx __bb = __obj.ice_facet(__facet);
            try
            {
                if(__bb.ice_isA("::MksIce::Management"))
                {
                    ManagementPrxHelper __h = new ManagementPrxHelper();
                    __h.__copyFrom(__bb);
                    __d = __h;
                }
            }
            catch(Ice.FacetNotExistException ex)
            {
            }
        }
        return __d;
    }

    public static ManagementPrx
    checkedCast(Ice.ObjectPrx __obj, String __facet, java.util.Map<String, String> __ctx)
    {
        ManagementPrx __d = null;
        if(__obj != null)
        {
            Ice.ObjectPrx __bb = __obj.ice_facet(__facet);
            try
            {
                if(__bb.ice_isA("::MksIce::Management", __ctx))
                {
                    ManagementPrxHelper __h = new ManagementPrxHelper();
                    __h.__copyFrom(__bb);
                    __d = __h;
                }
            }
            catch(Ice.FacetNotExistException ex)
            {
            }
        }
        return __d;
    }

    public static ManagementPrx
    uncheckedCast(Ice.ObjectPrx __obj)
    {
        ManagementPrx __d = null;
        if(__obj != null)
        {
            try
            {
                __d = (ManagementPrx)__obj;
            }
            catch(ClassCastException ex)
            {
                ManagementPrxHelper __h = new ManagementPrxHelper();
                __h.__copyFrom(__obj);
                __d = __h;
            }
        }
        return __d;
    }

    public static ManagementPrx
    uncheckedCast(Ice.ObjectPrx __obj, String __facet)
    {
        ManagementPrx __d = null;
        if(__obj != null)
        {
            Ice.ObjectPrx __bb = __obj.ice_facet(__facet);
            ManagementPrxHelper __h = new ManagementPrxHelper();
            __h.__copyFrom(__bb);
            __d = __h;
        }
        return __d;
    }

    protected Ice._ObjectDelM
    __createDelegateM()
    {
        return new _ManagementDelM();
    }

    protected Ice._ObjectDelD
    __createDelegateD()
    {
        return new _ManagementDelD();
    }

    public static void
    __write(IceInternal.BasicStream __os, ManagementPrx v)
    {
        __os.writeProxy(v);
    }

    public static ManagementPrx
    __read(IceInternal.BasicStream __is)
    {
        Ice.ObjectPrx proxy = __is.readProxy();
        if(proxy != null)
        {
            ManagementPrxHelper result = new ManagementPrxHelper();
            result.__copyFrom(proxy);
            return result;
        }
        return null;
    }
}
