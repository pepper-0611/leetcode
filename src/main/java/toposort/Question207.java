package toposort;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class Question207 {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        //in degree set
        int[] inDegrees = new int[numCourses];
        //adja table
        List<List<Integer>> adjaTable = new ArrayList<>(numCourses);
        for (int i = 0; i < numCourses; i++) {
            adjaTable.add(new LinkedList<>());
        }
        //adja table padding & in degree array padding.
        for (int[] pre : prerequisites) {
            inDegrees[pre[0]]++;
            adjaTable.get(pre[1]).add(pre[0]);
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegrees[i] == 0) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            Integer pre = queue.poll();
            numCourses--;
            for (Integer next : adjaTable.get(pre)) {
                if (--inDegrees[next] == 0) {
                    queue.add(next);
                }
            }
        }
        return numCourses == 0;
    }
}
