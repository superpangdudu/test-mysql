package com.test.leetcode;

import java.util.*;

/**
 * Created by Administrator on 2020/8/17.
 */
public class TestFindCity {

    private class City {
        int id;
        Map<Integer, Integer> cityDistanceMap = new HashMap<>();

        City(int id) {
            this.id = id;
        }

        //=====================================================================
        void addCity(int cityId, int distance) {
            cityDistanceMap.put(cityId, distance);
        }


    }

    //===================================================================================
    private Map<Integer, City> cityMap = new HashMap<>();

    //===================================================================================
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        for (int index = 0; index < edges.length; index++) {
            int fromCityId = edges[index][0];
            int toCityId = edges[index][1];
            int distance = edges[index][2];

            //
            City fromCity = getCity(fromCityId);
            fromCity.addCity(toCityId, distance);

            City toCity = getCity(toCityId);
            toCity.addCity(fromCityId, distance);
        }

        return 0;
    }

    private City getCity(int id) {
        City city = cityMap.get(id);
        if (city == null) {
            city = new City(id);
            cityMap.put(id, city);
        }

        return city;
    }

    //===================================================================================
    public static void main(String[] args) {
        TestFindCity obj = new TestFindCity();

        //
        int n = 4;
        int distanceThreshold = 4;

        int[][] edges = new int[4][3];
        edges[0] = new int[] {0, 1, 3};
        edges[1] = new int[] {1, 2, 1};
        edges[2] = new int[] {1, 3, 4};
        edges[3] = new int[] {2, 3, 1};

        //
        obj.findTheCity(n, edges, distanceThreshold);
    }
}
