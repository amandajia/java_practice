// url : https://leetcode.com/problems/pacific-atlantic-water-flow/
class Solution {

    public class Grid {

        public int x;

        public int y;

        public int value;

        public Grid(int mx, int my, int v) {

            x = mx;

            y = my;

            value = v;

        }

    }

    Map<List<Integer>, int[]> connect = new HashMap<>();

    public class UnionFind {

        

        int [] trace;

        int [] h;

        int m;

        int n;

        

        public UnionFind(int [][] matrix) {

            m = matrix.length;

            n = matrix[0].length;

            trace = new int[m * n];

            h = new int[m * n];

            for(int i =0 ;i < m ; ++i) 

                for(int j = 0; j< n; ++ j)

                    trace[i * n + j] = i * n + j;        

        }

        

        public boolean find(int [] a, int [] b) {

            return root(a[0] * n + a[1]) == root(b[0] * n + b[1]);

        }

        

        private int root(int a) {

            while(trace[a]!= a)

                a = trace[a];

            return a;

        } 

        

        public List<Integer> findRoot(int [] o) {

            int r = root(o[0] * n + o[1]);

            List<Integer> ret = new ArrayList<>();

            ret.add(r / n);

            ret.add(r % n);

            return ret;

        }

        

        public void union(int []a, int []b) {

            int o1 = a[0] * n + a[1];

            int o2 = b[0] * n + b[1];

            int r1 = root(o1);

            int r2 = root(o2);

            if(r1 == r2)    return ;

            if(h[r1] > h[r2]) {

                trace[r2] = r1;

            } else if (h[r1] < h[r2]) {

                trace[r1] = r2;

            } else {

                trace[r2] = r1;

                h[r1] ++;

            }

        }

    }

    

    public List<int[]> pacificAtlantic(int[][] matrix) {

        List<int[]> ret = new ArrayList<>();

        if(matrix == null || matrix.length == 0)    return ret;

        int m = matrix.length;

        int n = matrix[0].length;

        

        PriorityQueue<Grid> pq = new PriorityQueue<Grid> (new Comparator<Grid>() {

                @Override

                public int compare(Grid o1, Grid o2) {

                    int diff = o1.value - o2.value;

                    if(diff != 0)   return diff;

                    diff = o1.x - o2.x;

                    if(diff != 0)   return diff;

                    return o1.y - o2.y;

                }

                

            });

        for(int i =0 ;i < m ; ++i) {

            for(int j = 0; j< n; ++ j)

                pq.add(new Grid(i, j, matrix[i][j]));

        }

  

        UnionFind uf = new UnionFind(matrix);

        int [][] dimen = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};  

        while(!pq.isEmpty()) {

            Grid o = pq.poll();

            

            int [] pa = new int[2];

            if(o.x == 0 || o.y == 0 )       pa[0] = 1;

            if(o.x == m -1 || o.y == n - 1) pa[1] = 1;

            int [] pair = new int[2];

            pair[0] = o.x;

            pair[1] = o.y;

            for(int[] d : dimen) {

                int [] cur = new int[2];

                cur[0] = o.x + d[0];

                cur[1] = o.y + d[1];

                if(cur[0] < 0 || cur[0] >= m || cur[1] < 0 || cur[1] >= n)    continue;



                int[] c = connect.get(uf.findRoot(cur));

                if(c == null)    continue;

                pa[0] = pa[0] | c[0];

                pa[1] = pa[1] | c[1];

                if(matrix[cur[0]][cur[1]] == matrix[o.x][o.y]) {

                    uf.union(cur, pair);

                }

                //System.out.println(o.x + " : " + o.y + " neighbor " + cur[0] + " : " + cur[1] + " n value " + c[0] + " : " + c[1] );

            }

            connect.put(uf.findRoot(pair), pa);

            //System.out.println(pair[0] + " : " + pair[1] + " connect to p " + pa[0] + " to a " + pa[1]);

        }

        

        for(int i =0 ;i < m ; ++i) 

            for(int j = 0; j< n; ++ j) {

                int [] pa = new int [] {i, j};

                pa = connect.get(uf.findRoot(pa));

                if(pa[0] > 0 && pa[1] > 0)

                    ret.add(new int[] {i, j});

            }

                

        return ret;

    }

}
