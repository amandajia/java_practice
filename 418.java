public class Solution {
    public int wordsTyping(String[] sentence, int rows, int cols) {
        if(sentence == null)    return 0;
        int [] sizes = new int[sentence.length];
        int total = 0;
        for(int i = 0; i < sizes.length; ++i) {
            sizes[i] = sentence[i].length();
            if(sizes[i] > cols) return 0;
            total += sizes[i];
        }
        if(rows * cols < total ) return 0;
        total += sizes.length;
        List<Integer> count = new ArrayList<>();
        int index = 0;
        int time = 0;
        int o_row = rows;
        do {
            time += (cols / total);
            int space = cols % total;
            
            while(space >= sizes[index]) {
                space -= sizes[index] + 1;
                index = (index + 1) % sizes.length;
                if(index == 0) {
                    time ++;
                }
            }
            count.add(time);
            rows --;
            if(rows <= 0)   return count.get(count.size() - 1);
        } while(index != 0);
        int round_count = count.get(count.size() - 1);
        int num = o_row / count.size();
        int leftOver = o_row % count.size();
        return round_count * num + ((leftOver ==0)? 0:count.get(leftOver - 1));
    }
}
