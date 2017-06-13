
package levenshtein.tokenizer;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.StringTokenizer;
import levenshtein.connection.Database_Connection;
/**
 *
 * @author Joko Banjarnahor
 */
public class Preprocessor {

    public String preprocessing(String a)
    {
            String delimiter = "\\s|\\.|\\_|\\?|\\/|\\!|\\:";
            String low = a.toLowerCase();
            String don = low.replaceAll(delimiter, "");
            return don;
    }
}
