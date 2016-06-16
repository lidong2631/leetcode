public class SnakeGame {

    Deque<Integer> snake = new LinkedList<>();          // Deque -> double-ended queue
    int width;
    int height;
    int len;
    int[][] food;
    boolean gameover;

    /** Initialize your data structure here.
        @param width - screen width
        @param height - screen height 
        @param food - A list of food positions
        E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0]. */
    public SnakeGame(int width, int height, int[][] food) {
        this.width = width;
        this.height = height;
        snake.addFirst(0);
        len = 0;
        this.food = food;
        gameover = false;
    }
    
    /** Moves the snake.
        @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down 
        @return The game's score after the move. Return -1 if game over. 
        Game over when snake crosses the screen boundary or bites its body. */
    public int move(String direction) {
        if (gameover) return -1;
        
        int head = snake.getFirst();            // get haed
        int tail = snake.removeLast();          // remove tail first
        int row = head / width;                 // get corresponding row col for the snake head
        int col = head % width;
        switch (direction) {
            case "U":
                row--;
                break;
            case "L":
                col--;
                break;
            case "R":
                col++;
                break;
            case "D":
                row++;
                break;
        }
        int newhead = row * width + col;        // calculate new value of snake head
        if (row < 0 || row >= height || col < 0 || col >= width || snake.contains(newhead)) {   // if out of border and eat itself gameover
            gameover = true;
            return -1;
        }
        snake.addFirst(newhead);                // move snake head
        if (len < food.length && row == food[len][0] && col == food[len][1]) {  // if head position is same as food increase snake
            len++;
            snake.addLast(tail);
        }
        return len;
    }
}

/**
 * Your SnakeGame object will be instantiated and called as such:
 * SnakeGame obj = new SnakeGame(width, height, food);
 * int param_1 = obj.move(direction);
 */


move snake = move head + remove tail and leave the rest body still

https://leetcode.com/discuss/106217/java-solution-of-snakegame-276ms