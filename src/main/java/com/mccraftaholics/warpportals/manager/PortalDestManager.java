package com.mccraftaholics.warpportals.manager;

import com.mccraftaholics.warpportals.objects.CoordsPY;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;
import java.util.logging.Logger;

public class PortalDestManager {

    public HashMap<String, CoordsPY> mPortalDestMap = new HashMap<String, CoordsPY>();
    Logger mLogger;
    private PortalManager mPM;

    public PortalDestManager(PortalManager pm, Logger logger) {
        mPM = pm;
        mLogger = logger;
    }

    public void addDestination(String destname, CoordsPY coords) {
        mPortalDestMap.put(destname, coords);
        mPM.saveDataFile();
    }

    public void removeDestination(String destName) {
        mPortalDestMap.remove(destName);
        mPM.saveDataFile();
    }

    public CoordsPY getDestCoords(String destname) {
        return mPortalDestMap.get(destname);
    }

    public Set<String> getDestinations() {
        return mPortalDestMap.keySet();
    }

    public String getDestinationName(CoordsPY coords) {
        for (Entry<String, CoordsPY> dest : mPortalDestMap.entrySet()) {
            if (dest.getValue().equals(coords)) {
                return dest.getKey();
            }
        }
        return null;
    }

}
