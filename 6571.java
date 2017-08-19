public class Solution {
    
    public boolean judgeCircle(String moves) {
        int [][] dir = new int [26][2];
        dir['U' - 'A'] = new int[] {-1, 0};
        dir['D' - 'A'] = new int[] {1, 0};
        dir['L' - 'A'] = new int[] {0, -1};
        dir['R' - 'A'] = new int[] {0, 1};
        int [] pos =new int[2];
        for(int i = 0; i< moves.length(); ++i ) {
            int index = moves.charAt(i) - 'A';
            pos[0] += dir[index][0];
            pos[1] += dir[index][1];
        }
        return pos[0] == 0 && pos[1] == 0;
    }
}
