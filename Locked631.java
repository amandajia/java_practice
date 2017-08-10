/*

Your task is to design the basic function of Excel and implement the function of sum formula. Specifically, you need to implement the following functions:

Excel(int H, char W): This is the constructor. The inputs represents the height and width of the Excel form. H is a positive integer, range from 1 to 26. It represents the height. W is a character range from 'A' to 'Z'. It represents that the width is the number of characters from 'A' to W. The Excel form content is represented by a height * width 2D integer array C, it should be initialized to zero. You should assume that the first row of C starts from 1, and the first column of C starts from 'A'.


void Set(int row, char column, int val): Change the value at C(row, column) to be val.


int Get(int row, char column): Return the value at C(row, column).


int Sum(int row, char column, List of Strings : numbers): This function calculate and set the value at C(row, column), where the value should be the sum of cells represented by numbers. This function return the sum result at C(row, column). This sum formula should exist until this cell is overlapped by another value or another sum formula.

numbers is a list of strings that each string represent a cell or a range of cells. If the string represent a single cell, then it has the following format : ColRow. For example, "F7" represents the cell at (7, F).

If the string represent a range of cells, then it has the following format : ColRow1:ColRow2. The range will always be a rectangle, and ColRow1 represent the position of the top-left cell, and ColRow2 represents the position of the bottom-right cell.


Example 1:
Excel(3,"C"); 
// construct a 3*3 2D array with all zero.
//   A B C
// 1 0 0 0
// 2 0 0 0
// 3 0 0 0

Set(1, "A", 2);
// set C(1,"A") to be 2.
//   A B C
// 1 2 0 0
// 2 0 0 0
// 3 0 0 0

Sum(3, "C", ["A1", "A1:B2"]);
// set C(3,"C") to be the sum of value at C(1,"A") and the values sum of the rectangle range whose top-left cell is C(1,"A") and bottom-right cell is C(2,"B"). Return 4. 
//   A B C
// 1 2 0 0
// 2 0 0 0
// 3 0 0 4

Set(2, "B", 2);
// set C(2,"B") to be 2. Note C(3, "C") should also be changed.
//   A B C
// 1 2 0 0
// 2 0 2 0
// 3 0 0 6
Note:
You could assume that there won't be any circular sum reference. For example, A1 = sum(B1) and B1 = sum(A1).
The test cases are using double-quotes to represent a character.
Please remember to RESET your class variables declared in class Excel, as static/class variables are persisted across multiple test cases. Please see here for more details.

*/
public class Excel {
    int [][] table;
    int count ;
    Map<Integer, List<Integer>> react;
    Map<Integer, Integer> tranToGrid;
    Map<Integer, Integer> gridToTran;
    public Excel(int H, char W) {
        table = new int[H + 1][W -'A' + 1];
        count = 0;
        react = new HashMap<>();
        tranToGrid = new HashMap<>();
        gridToTran = new HashMap<>();
    }
    
    int toOne(int x, int y){
        return x * table.length + y;
    }
    
    int toOne(int x, char y){
        return x * table.length + y - 'A';
    }
    
    int [] toGrid(int num) {
        int [] ret = new int[2];
        ret[0] = num / table.length;
        ret[1] = num % table.length;
        return ret;
    }
    
    void updateReact(Queue<Integer> q, long off) {
        while(!q.isEmpty()) {
            int grid = q.poll();
            List<Integer> chs = react.get(grid);
            if(chs != null) {
                for(int i = chs.size() -1; i >=0; --i) {
                    int tran = chs.get(i);
                    if(!tranToGrid.containsKey(tran)) {
                        chs.remove(i);
                        continue;
                    }
                    int target = tranToGrid.get(tran);
                    int[] l = toGrid(target);
                    table[l[0]][l[1]] =(int)(off + table[l[0]][l[1]]);
                    if(react.containsKey(target)) {
                        q.add(target);
                    }
                }
            
            }
        }
    }
    
    public void set(int r, char c, int v) {
        update(r, c, v);
        int grid = toOne(r, c);
        if(gridToTran.containsKey(grid)) {
            int tran = gridToTran.get(grid);
            gridToTran.remove(grid);
            tranToGrid.remove(tran);
        }
    }
    
    public int get(int r, char c) {
        return table[r][c - 'A'];
    }
    
    void update(int r, char c, int v) {
        Long offset = (long)v - table[r][c - 'A'];
        table[r][c - 'A'] = v;
        int grid = toOne(r, c);
        // update react
        if(react.containsKey(grid)) {
            Queue<Integer> toUpdate = new LinkedList<>();
            toUpdate.add(grid);
            updateReact(toUpdate, offset);
        }
    }
    
    public int sum(int r, char c, String[] strs) {
        int one = toOne(r, c);
        Integer tran = gridToTran.get(one);
        if(tran != null) {
            tranToGrid.remove(tran);
        } 
        gridToTran.put(one, count);
        tranToGrid.put(count, one);
        int sum = 0;
        for(String s : strs) {
            if(s.length() <= 3) {
                int t = toOne(Integer.parseInt(s.substring(1)) , s.charAt(0));
                sum += table[t/table.length][t%table.length];
                react.putIfAbsent(t, new ArrayList<>());
                react.get(t).add(count);
            } else {
                String [] temp = s.split(":");
                int [] start = new int[2];
                start[0] = Integer.parseInt(temp[0].substring(1));
                start[1] = temp[0].charAt(0) - 'A';
                int [] end = new int[2];
                end[0] = Integer.parseInt(temp[1].substring(1));
                end[1] = temp[1].charAt(0) - 'A';
                for(int i = start[0]; i<=end[0]; ++i) {
                    for(int j = start[1]; j<= end[1]; ++j) {
                        int t = toOne(i, j);
                        sum += table[t/table.length][t%table.length];
                        react.putIfAbsent(t, new ArrayList<>());
                        react.get(t).add(count);
                    }
                }    
            }
        }
        count++;
        update(r, c, sum);
        return sum;
    }
}

/**
 * Your Excel object will be instantiated and called as such:
 * Excel obj = new Excel(H, W);
 * obj.set(r,c,v);
 * int param_2 = obj.get(r,c);
 * int param_3 = obj.sum(r,c,strs);
 */
