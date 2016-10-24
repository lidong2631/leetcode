public class Solution {
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>() {
            public int compare(int[] arr1, int[] arr2) {
                if (arr1[0] == arr2[0]) return Integer.compare(arr1[1], arr2[1]);
                return arr1[0] > arr2[0] ? -1 : 1;
            }
        });
        List<int[]> list = new LinkedList<>();
        for (int[] p : people)
            list.add(p[1], p);
        int[][] res = new int[people.length][2];
        for (int i = 0; i < res.length; i++)
            res[i] = list.get(i);
        return res;
    }
}

first sort descending order then insert into list by arr[1] value

[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]

sort:
[[7,0], [7,1], [6,1], [5,0], [5,2], [4,4]]

insert:
[7,0]
[7,0], [7,1]
[7,0], [6,1], [7,1]
[5,0], [7,0], [6,1], [7,1]
[5,0], [7,0], [5,2], [6,1], [7,1]
[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]

O(n^2)

https://discuss.leetcode.com/topic/60437/java-solution-using-priorityqueue-and-linkedlist



Suppose you have a random list of people standing in a queue. Each person is described by a pair of integers (h, k), 

where h is the height of the person and k is the number of people in front of this person who have a height greater than or equal to h. 

Write an algorithm to reconstruct the queue.

Note:
The number of people is less than 1,100.

Example

Input:
[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]

Output:
[[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]





