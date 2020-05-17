package toposort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Question210 {

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        //padding adjaTable
        List<List<Integer>> adjaTable = new ArrayList<>(numCourses);
        for (int i = 0; i < numCourses; i++) {
            adjaTable.add(new LinkedList<>());
        }
        for (int[] pre : prerequisites) {
            adjaTable.get(pre[1]).add(pre[0]);
        }
        List<Integer> reverseRet = new ArrayList<>();
        int[] flags = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (!dfs(i, flags, adjaTable, reverseRet)) {
                return new int[0];
            }
        }
        int[] resArray = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            resArray[numCourses - i - 1] = reverseRet.get(i);
        }
        return resArray;
    }


    boolean dfs(int index, int[] flags, List<List<Integer>> adjaTables, List<Integer> reverseRet) {
        if (flags[index] == -1) {
            return true;
        }
        if (flags[index] == 1) {
            return false;
        }
        flags[index] = 1;
        for (int next : adjaTables.get(index)) {
            if (!dfs(next, flags, adjaTables, reverseRet)) {
                return false;
            }
        }
        flags[index] = -1;
        reverseRet.add(index);
        return true;
    }
}
