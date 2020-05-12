package toposort;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Question207_dfs {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] flags = new int[numCourses];

        List<List<Integer>> adjaTable = new ArrayList<>(numCourses);
        for (int i = 0; i < numCourses; i++) {
            adjaTable.add(new LinkedList<>());
        }
        //adja table
        for (int[] pre : prerequisites) {
            adjaTable.get(pre[1]).add(pre[0]);
        }

        for (int i = 0; i < numCourses; i++) {
            if (!dfs(flags, adjaTable, i)) {
                return false;
            }
        }
        return true;
    }

    private boolean dfs(int[] flags, List<List<Integer>> adjaTable, int index) {
        if (flags[index] == -1) {
            return true;
        }
        if (flags[index] == 1) {
            return false;
        }
        //traverse
        flags[index] = 1;
        for (int next : adjaTable.get(index)) {
            if (!dfs(flags, adjaTable, next)) {
                return false;
            }
        }
        flags[index] = -1;
        return true;
    }
}
