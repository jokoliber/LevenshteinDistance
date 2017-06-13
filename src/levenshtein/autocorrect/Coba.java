/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package levenshtein.autocorrect;

/**
 *
 * @author Joko Banjarnahor
 */



public class Coba {
    
    //    public static int levenshteindistance(String a, String b)
//    {
//        //a = a.toLowerCase();
//        //b = b.toLowerCase();
//        int[] costs = new int[b.length() + 1];
//        for (int j = 0; j < costs.length; j++)
//            costs[j] = j;
//        for (int i = 1; i <= a.length(); i++)
//        {
//            costs[0] = i;
//            int nw = i - 1;
//            for (int j = 1; j <= b.length(); j++)
//            {
//                int cj = Math.min(1 + Math.min(costs[j], costs[j - 1]),
//                        a.charAt(i - 1) == b.charAt(j - 1) ? nw : nw + 1);
//                nw = costs[j];
//                costs[j] = cj;
//            }
//        }
//        return costs[b.length()];
//    }
    
    
    public static int distance(String a, String b)
    {
        a = a.toLowerCase();
        b = b.toLowerCase();
        int[] costs = new int[b.length() + 1];
        for (int j = 0; j < costs.length; j++)
            costs[j] = j;
        for (int i = 1; i <= a.length(); i++)
        {
            costs[0] = i;
            int nw = i - 1;
            for (int j = 1; j <= b.length(); j++)
            {
                int cj = Math.min(1 + Math.min(costs[j], costs[j - 1]),
                        a.charAt(i - 1) == b.charAt(j - 1) ? nw : nw + 1);
                nw = costs[j];
                costs[j] = cj;
            }
        }
        return costs[b.length()];
    }
    
    public static void main(String[] args) {
        
        System.out.println(distance("polo6n14", "galf314"));
        
    }
}
