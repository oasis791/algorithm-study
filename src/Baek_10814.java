//10814번 나이순 정렬

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Baek_10814 {
    private static int N;
    private static String[][] member;
    public static void main(String[] args) throws IOException {
        BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(bf.readLine()," ");
        N=Integer.parseInt(st.nextToken());
        member=new String[N][2];
        for(int i=0;i<N;i++){
            st=new StringTokenizer(bf.readLine()," ");
            member[i][0]=st.nextToken();
            member[i][1]=st.nextToken();
        }
        Arrays.sort(member, new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                return Integer.parseInt(o1[0])-Integer.parseInt(o2[0]);
            }
        });

        for(int i=0;i<N;i++){
            System.out.print(member[i][0]+" "+member[i][1]+"\n");
        }
    }
}
