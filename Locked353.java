/*
Design a Snake game that is played on a device with screen size = width x height. Play the game online if you are not familiar with the game.

The snake is initially positioned at the top left corner (0,0) with length = 1 unit.

You are given a list of food's positions in row-column order. When a snake eats the food, its length and the game's score both increase by 1.

Each food appears one by one on the screen. For example, the second food will not appear until the first food was eaten by the snake.

When a food does appear on the screen, it is guaranteed that it will not appear on a block occupied by the snake.

Example:
Given width = 3, height = 2, and food = [[1,2],[0,1]].

Snake snake = new Snake(width, height, food);

Initially the snake appears at position (0,0) and the food at (1,2).

|S| | |
| | |F|

snake.move("R"); -> Returns 0

| |S| |
| | |F|

snake.move("D"); -> Returns 0

| | | |
| |S|F|

snake.move("R"); -> Returns 1 (Snake eats the first food and right after that, the second food appears at (0,1) )

| |F| |
| |S|S|

snake.move("U"); -> Returns 1

| |F|S|
| | |S|

snake.move("L"); -> Returns 2 (Snake eats the second food)

| |S|S|
| | |S|

snake.move("U"); -> Returns -1 (Game over because snake collides with border)

Credits:
Special thanks to @elmirap for adding this problem and creating all test cases.


*/
public class SnakeGame {

    /** Initialize your data structure here.
        @param width - screen width
        @param height - screen height 
        @param food - A list of food positions
        E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0]. */
    int [][] food;
    int f_index = 0;
    int m;
    int n;
    Queue<Long> position;
    Set<Long> table;
    int[] curPos;
    long convert(int [] p) {
        return ((long)p[0] <<32) | p[1];
    }
    boolean dead = false;
    
    public SnakeGame(int width, int height, int[][] f) {
        curPos = new int[2];
        food = f;
        position = new LinkedList<>();
        table = new HashSet<>();
        position.add(0L);
        table.add(0L);
        m = height;
        n = width;
    }
    
    /** Moves the snake.
        @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down 
        @return The game's score after the move. Return -1 if game over. 
        Game over when snake crosses the screen boundary or bites its body. */
    
    int [][]dirs = new int[][] {{-1,0}, {0, -1}, {0,1}, {1, 0}};
    
    int [] getDir(char c) {
        if(c == 'U' || c == 'u')    return dirs[0];
        if(c == 'L' || c == 'l')    return dirs[1];
        if(c == 'R' || c == 'r')    return dirs[2];
        if(c == 'D' || c == 'd')    return dirs[3];
        return null;
    }
    
    boolean out(int [] p) {
        return p[0] <0 || p[1] < 0 || p[0] >=m || p[1] >=n;
    }
    public int move(String direction) {
        if(dead) return -1;
        int[] dir = getDir(direction.charAt(0));
        Long curFood = null;
        if(f_index < food.length)    
            curFood = convert(food[f_index]);
        curPos[0] += dir[0];
        curPos[1] += dir[1];
        // get  current position
        long cp = convert(curPos);
        // if out of boundry or self cross (loop excluded)
        if(out(curPos) || table.contains(cp) && cp != position.peek())  {
                dead = true;
                return -1;
        }
     
        // if current food eaten 
        if(curFood!=null && curFood == cp) {
            f_index++;
            // added to table and position queu
            table.add(cp);
            position.add(cp);
           // System.out.println("Cur direction " + direction + " current mapping " + table);
            return f_index;
        } else {
            table.remove(position.poll());
            // added to table and position queu
            table.add(cp);
            position.add(cp);
          //  System.out.println("Cur direction " + direction + " current mapping " + table);
            return f_index;
        }
        //System.out.println("Cur direction " + direction + " current mapping " + table);
    }
}

/**
 * Your SnakeGame object will be instantiated and called as such:
 * SnakeGame obj = new SnakeGame(width, height, food);
 * int param_1 = obj.move(direction);
 */
