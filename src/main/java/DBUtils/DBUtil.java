package DBUtils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

    private static final String mySQlUser = "root";
    private static final String mySQLPwd = "";
    private static final String mySQLCS = "jdbc:mysql://localhost:3306/testing_db?useLegacyDatetimeCode=false&serverTimezone=UTC";

    private static final String oracleUser = "root";
    private static final String oraclePwd = "";
    private static final String oracleCS = "jdbc:mysql://localhost:3306/team_manager";

    public static Connection getConnection(DBType dbType) throws SQLException {
        {
            Connection conn = null;
            switch (dbType) {
                case MYSQLDB:
                    conn = DriverManager.getConnection(mySQLCS, mySQlUser, mySQLPwd);
                    break;
                case ORADB:
                    conn = DriverManager.getConnection(oracleCS, oracleUser, oraclePwd);
                    break;
                default:
                    return null;

            }

            return conn;


        }
    }

    public static void showErrorMessage(SQLException e) {
        System.err.println("Error : " + e.getMessage());
        System.err.println("Error Code: " + e.getErrorCode());
    }
}
