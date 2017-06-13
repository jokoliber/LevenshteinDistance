package levenshtein.autocorrect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import levenshtein.connection.Database_Connection;
import levenshtein.tokenizer.Preprocessor;

/**
 *
 * @author Joko Banjarnahor
 */
public class LevenshteinAutoCorrect {

    /**
     * @param args the command line arguments
     */
    
    private static int Minimum (int a, int b, int c) {
        int mi;
        mi = a;
        if (b < mi) {
          mi = b;
        }
    
        if (c < mi) {
            mi = c;
        }
        return mi;
  }
    
    public static int levenshteindistance(String s, String t) {
        int d[][]; // matrix
        int n; // length of s
        int m; // length of t
        int i; // iterates through s
        int j; // iterates through t
        char s_i; // ith character of s
        char t_j; // jth character of t
        int cost; // cost
        // Step 1
        n = s.length ();
        m = t.length ();
        if (n == 0) {
          return m;
        }
        if (m == 0) {
          return n;
        }
        d = new int[n+1][m+1];
        // Step 2
        for (i = 0; i <= n; i++) {
          d[i][0] = i;
        }
        for (j = 0; j <= m; j++) {
          d[0][j] = j;
        }
        // Step 3
        for (i = 1; i <= n; i++) {
            s_i = s.charAt (i - 1);
          // Step 4
          for (j = 1; j <= m; j++) {
            t_j = t.charAt (j - 1);
            // Step 5
            if (s_i == t_j) {
                cost = 0;
            }
            else {
                cost = 1;
            }
            // Step 6
            d[i][j] = Minimum(d[i-1][j]+1, d[i][j-1]+1, d[i-1][j-1] + cost);
          }
        }
        // Step 7
        return d[n][m];

  }
    
    public static void main(String[] args) throws SQLException {
        System.out.println(levenshteindistance("joko", "jako"));
        
        Scanner sc = new Scanner(System.in);
        Preprocessor preprocess = new Preprocessor();
        Connection connect = Database_Connection.getConnection();
        String sql = "Select name From autos";
        PreparedStatement statement = (PreparedStatement) connect.prepareStatement(sql);
        ResultSet result = statement.executeQuery();
        ArrayList<String> data = new ArrayList<String>();
        List<Integer> dist = new ArrayList<Integer>();
        
        System.out.println("Masukkan kata yang ingin dicari : ");
        String key = sc.nextLine();
        String keys = preprocess.preprocessing(key);
        //System.out.println(keys);
        for(int i = 0; result.next()!= false; i++)
            {
                String mob = result.getString("name");
                String hasil = preprocess.preprocessing(mob);
                data.add(hasil);
                dist.add(levenshteindistance(hasil, keys));
            }
        Collections.sort(dist);
        
        for (int a = 0; a < data.size(); a++)
        {
            if (levenshteindistance(data.get(a), keys) == dist.get(0))
            {
                System.out.println("Did you mean this word: "+data.get(a));
                break;
            }

        }
        sc.close();
    }

    }
    
