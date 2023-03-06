package Homework;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;


/**
 * this class represent the instance of the problem
 */
public class Problem {
    private Location[] location;
    private Road[] road;

    /**
     * getter for the array of locations
     *
     * @return the array of locations
     */
    public Location[] getLocation() {
        return location;
    }

    /**
     * setter of the array of location
     *
     * @param location the array of location
     */
    public void setLocation(Location[] location) {
        this.location = location;
    }

    /**
     * getter for the array of roads
     *
     * @return the array of roads
     */
    public Road[] getRoad() {
        return road;
    }

    /**
     * setter for the array of roads
     *
     * @param road the array of roads
     */
    public void setRoad(Road[] road) {
        this.road = road;
    }

    /**
     * all-args constructor
     *
     * @param location an array of location
     * @param road     an array of roads
     */
    public Problem(Location[] location, Road[] road) {
        this.location = location;
        this.road = road;
    }

    /**
     * the method for check if the instance is correct
     *
     * @return true, if the instance is correct, false otherwise
     */
    public boolean isValidInput() {
        for (int i = 0; i < this.location.length; i++) {
            for (int j = 0; j < this.location.length; j++) {
                if (i == j) {
                    continue;
                }
                if (location[i].equals(location[j])) {
                    return false;
                }
            }
        }
        for (int i = 0; i < this.road.length; i++) {
            for (int j = 0; j < this.road.length; j++) {
                if (i == j) {
                    continue;
                }
                if (road[i].equals(road[j])) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * overriding method toString()
     *
     * @return a string who represent a described for the problem
     */
    @Override
    public String toString() {
        return "Problem{" +
                "location=" + Arrays.toString(location) +
                ", road=" + Arrays.toString(road) +
                '}';
    }

    /**
     * the mtehod where we check if exist roads between 2 location
     *
     * @param loc1 location 1
     * @param loc2 location 2
     * @return true, if exist roads between loc1 and loc2
     */
    public boolean existRoadsBetween(Location loc1, Location loc2) {
        if(isValidInput()==false)
        {
            return false;
        }
        boolean[] marked = new boolean[this.location.length];
        for (int i = 0; i < marked.length; i++) {
            marked[i] = false;
        }
        Deque<Location> stack = new ArrayDeque<>();
        stack.push(loc1);
        while (!stack.isEmpty()) {
            Location aux = stack.pop();
            int position = -1;
            for (int i = 0; i < this.location.length; i++) {
                if (location[i].equals(aux)) {
                    position = i;
                    break;
                }
            }
            if (position == -1)
                break;
            if (marked[position] == false) {
                marked[position] = true;
                for (int i = 0; i < this.road.length; i++) {
                    if (this.road[i].getStart().equals(aux)) {
                        if (this.road[i].getFinish().equals(loc2))
                            return true;
                        else {
                            for (int j = 0; j < this.location.length; j++) {
                                if (this.road[i].getFinish().equals(this.location[j])) {
                                    if (marked[j] == false) {
                                        stack.push(this.road[i].getFinish());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}
