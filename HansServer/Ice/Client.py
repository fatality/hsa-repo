import sys, traceback, Ice
import Demo

status = 0
ic = None
try:
    ic = Ice.initialize(sys.argv)
    base = ic.stringToProxy("SimpleManagement:default ‑p 10000")
    management = Demo.ManagementPrx.checkedCast(base)
    if not management:
        raise RuntimeError("Invalid proxy")

    management.initManagement(NumberOfPlanets, simDuration, maxPlanetMass, centralStarMass)
except:
    traceback.print_exc()
    status = 1

if ic:
    # Clean up
    try:
        ic.destroy()
    except:
        traceback.print_exc()
        status = 1

sys.exit(status)
