Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.

For example,
[2,3,4], the median is 3

[2,3], the median is (2 + 3) / 2 = 2.5

Design a data structure that supports the following two operations:

void addNum(int num) - Add a integer number from the data stream to the data structure.
double findMedian() - Return the median of all elements so far.
 

Example:

addNum(1)
addNum(2)
findMedian() -> 1.5
addNum(3) 
findMedian() -> 2
 

Follow up:

If all integer numbers from the stream are between 0 and 100, how would you optimize it?
If 99% of all integer numbers from the stream are between 0 and 100, how would you optimize it?



Java:
public class MedianFinder {

    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

    // Adds a number into the data structure.
    public void addNum(int num) {
        if (minHeap.size() == maxHeap.size()) {
            maxHeap.offer(num);
            if (minHeap.size() != 0 && num > minHeap.peek()) {
                maxHeap.offer(minHeap.poll());
                minHeap.offer(maxHeap.poll());
            }
        }
        else {
            maxHeap.offer(num);
            minHeap.offer(maxHeap.poll());
        }
    }

    // Returns the median of current data stream
    public double findMedian() {
        return (minHeap.size() == maxHeap.size()) ? (minHeap.peek() + maxHeap.peek()) / 2.0 : (double)maxHeap.peek();
    }
};

// Your MedianFinder object will be instantiated and called as such:
// MedianFinder mf = new MedianFinder();
// mf.addNum(1);
// mf.findMedian();



insertiont O(logn) find O(1)

1 max-heap contains the smallest half of the numbers and min-heap contains the largest half
2 the number of elements in max-heap is either equal to or 1 more than the number of elements in the min-heap

once we’re asked for the median, if the total number of received elements is odd, the median is the root of the max-heap. 
If it’s even, then the median is the average of the roots of the max-heap and min-heap

http://www.ardendertat.com/2011/11/03/programming-interview-questions-13-median-of-integer-stream/