import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
public class boj17219 {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        HashMap<String, String> map = new HashMap<>();
        String[] input = bf.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);
        for(int i = 0; i < n; i++){
            input = bf.readLine().split(" ");
            map.put(input[0], input[1]);
        }
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < m; i++) res.append(map.get(bf.readLine())).append('\n');
        //readLine해서 split하는거랑 StringBuilder 써서 출력하는거 처음 해봤다
        System.out.println(res.toString());
    }
}
