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

public final class ManagementHolder
{
    public
    ManagementHolder()
    {
    }

    public
    ManagementHolder(Management value)
    {
        this.value = value;
    }

    public class Patcher implements IceInternal.Patcher
    {
        public void
        patch(Ice.Object v)
        {
            try
            {
                value = (Management)v;
            }
            catch(ClassCastException ex)
            {
                Ice.UnexpectedObjectException _e = new Ice.UnexpectedObjectException();
                _e.type = v.ice_id();
                _e.expectedType = type();
                throw _e;
            }
        }

        public String
        type()
        {
            return "::Demo::Management";
        }
    }

    public Patcher
    getPatcher()
    {
        return new Patcher();
    }

    public Management value;
}
