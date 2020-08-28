package com.test.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2020/8/17.
 */
public class TestPathCrossing {

    private Map<String, Boolean> visitedPath = new HashMap<>();

    //===================================================================================
    public boolean isPathCrossing(String path) {
        if (path == null
                || path.length() == 0)
            return false;

        // init visited path map
        int x = 0;
        int y = 0;

        String key = getPosKey(x, y);
        visitedPath.put(key, true);

        //
        for (int i = 0; i < path.length(); i++) {
            char c = path.charAt(i);

            if (c == 'N')
                y -= 1;
            else if (c == 'S')
                y += 1;
            else if (c == 'E')
                x -= 1;
            else if (c == 'W')
                x += 1;

            if (visitPos(x, y))
                return true;
        }

        return false;
    }

    private String getPosKey(int x, int y) {
        return x + "_" + y;
    }

    private boolean visitPos(int x, int y) {
        String key = getPosKey(x, y);
        if (visitedPath.containsKey(key))
            return true;

        visitedPath.put(key, true);
        return false;
    }

    //===================================================================================
    public static void main(String[] args) {

    }
}
