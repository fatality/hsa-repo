// **********************************************************************
//
// Copyright (c) 2003-2007 ZeroC, Inc. All rights reserved.
//
// This copy of Ice is licensed to you under the terms described in the
// ICE_LICENSE file included in this distribution.
//
// **********************************************************************

// Ice version 3.2.1

package HansServer;

public final class StartSimPrxHelper extends Ice.ObjectPrxHelperBase implements StartSimPrx
{
    public void
    Management(int NumberOfPlanets)
    {
        Management(NumberOfPlanets, null, false);
    }

    public void
    Management(int NumberOfPlanets, java.util.Map<String, String> __ctx)
    {
        Management(NumberOfPlanets, __ctx, true);
    }

    @SuppressWarnings("unchecked")
    private void
    Management(int NumberOfPlanets, java.util.Map<String, String> __ctx, boolean __explicitCtx)
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
                __delBase = __getDelegate();
                _StartSimDel __del = (_StartSimDel)__delBase;
                __del.Management(NumberOfPlanets, __ctx);
                return;
            }
            catch(IceInternal.LocalExceptionWrapper __ex)
            {
                __handleExceptionWrapper(__delBase, __ex);
            }
            catch(Ice.LocalException __ex)
            {
                __cnt = __handleException(__delBase, __ex, __cnt);
            }
        }
    }

    public static StartSimPrx
    checkedCast(Ice.ObjectPrx __obj)
    {
        StartSimPrx __d = null;
        if(__obj != null)
        {
            try
            {
                __d = (StartSimPrx)__obj;
            }
            catch(ClassCastException ex)
            {
                if(__obj.ice_isA("::HansServer::StartSim"))
                {
                    StartSimPrxHelper __h = new StartSimPrxHelper();
                    __h.__copyFrom(__obj);
                    __d = __h;
                }
            }
        }
        return __d;
    }

    public static StartSimPrx
    checkedCast(Ice.ObjectPrx __obj, java.util.Map<String, String> __ctx)
    {
        StartSimPrx __d = null;
        if(__obj != null)
        {
            try
            {
                __d = (StartSimPrx)__obj;
            }
            catch(ClassCastException ex)
            {
                if(__obj.ice_isA("::HansServer::StartSim", __ctx))
                {
                    StartSimPrxHelper __h = new StartSimPrxHelper();
                    __h.__copyFrom(__obj);
                    __d = __h;
                }
            }
        }
        return __d;
    }

    public static StartSimPrx
    checkedCast(Ice.ObjectPrx __obj, String __facet)
    {
        StartSimPrx __d = null;
        if(__obj != null)
        {
            Ice.ObjectPrx __bb = __obj.ice_facet(__facet);
            try
            {
                if(__bb.ice_isA("::HansServer::StartSim"))
                {
                    StartSimPrxHelper __h = new StartSimPrxHelper();
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

    public static StartSimPrx
    checkedCast(Ice.ObjectPrx __obj, String __facet, java.util.Map<String, String> __ctx)
    {
        StartSimPrx __d = null;
        if(__obj != null)
        {
            Ice.ObjectPrx __bb = __obj.ice_facet(__facet);
            try
            {
                if(__bb.ice_isA("::HansServer::StartSim", __ctx))
                {
                    StartSimPrxHelper __h = new StartSimPrxHelper();
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

    public static StartSimPrx
    uncheckedCast(Ice.ObjectPrx __obj)
    {
        StartSimPrx __d = null;
        if(__obj != null)
        {
            StartSimPrxHelper __h = new StartSimPrxHelper();
            __h.__copyFrom(__obj);
            __d = __h;
        }
        return __d;
    }

    public static StartSimPrx
    uncheckedCast(Ice.ObjectPrx __obj, String __facet)
    {
        StartSimPrx __d = null;
        if(__obj != null)
        {
            Ice.ObjectPrx __bb = __obj.ice_facet(__facet);
            StartSimPrxHelper __h = new StartSimPrxHelper();
            __h.__copyFrom(__bb);
            __d = __h;
        }
        return __d;
    }

    protected Ice._ObjectDelM
    __createDelegateM()
    {
        return new _StartSimDelM();
    }

    protected Ice._ObjectDelD
    __createDelegateD()
    {
        return new _StartSimDelD();
    }

    public static void
    __write(IceInternal.BasicStream __os, StartSimPrx v)
    {
        __os.writeProxy(v);
    }

    public static StartSimPrx
    __read(IceInternal.BasicStream __is)
    {
        Ice.ObjectPrx proxy = __is.readProxy();
        if(proxy != null)
        {
            StartSimPrxHelper result = new StartSimPrxHelper();
            result.__copyFrom(proxy);
            return result;
        }
        return null;
    }
}
