package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * <h1>The Class BoulderDashBDDConnector.</h1>
 *
 * @author Jean-Aymeric DIET jadiet@cesi.fr
 * @version 1.0
 */
public final class LorannBDDConnector {

    /** The instance. */
    private static LorannBDDConnector instance;

    /** The login. */
    private static String                  user     = "root";

    /** The password. */
    private static String                  password = "Sophie1902";

    /** The url. */
    private static String                  url      = "jdbc:mysql://localhost/Lorann3?useSSL=false&serverTimezone=UTC";

    /** The connection. */
    private Connection                     connection;

    /** The statement. */
    private Statement                      statement;

    /**
     * Instantiates a new boulder dash BDD connector.
     */
    public LorannBDDConnector(int choose) {
        this.open(choose);
    }

    /**
     * Gets the single instance of BoulderDashBDDConnector.
     *
     * @return single instance of BoulderDashBDDConnector
     */
    public static LorannBDDConnector getInstance(int choose) {
        if (instance == null) {
            setInstance(new LorannBDDConnector(choose));
        }
        return instance;
    }

    /**
     * Sets the instance.
     *
     * @param instance
     *            the new instance
     */
    private static void setInstance(final LorannBDDConnector instance) {
        LorannBDDConnector.instance = instance;
    }

    /**
     * Open.
     * @param choose 
     *
     * @return true, if successful
     */
    private boolean open(int choose) {
        try {
            this.connection = DriverManager.getConnection(LorannBDDConnector.url, LorannBDDConnector.user,
                    LorannBDDConnector.password);
            this.statement = this.connection.createStatement();
            System.out.println("test");
            System.out.println("Creating statement...");
            statement = connection.createStatement();
            String sql;
            System.out.println("TEST DEBUT REQUETE");
            sql = "SELECT * FROM lorann3.niveau;";
            sql = "call lorann3.Niveaux("+ choose + ")";
            ResultSet rs = statement.executeQuery(sql);
            //STEP 5: Extract data from result set
            while(rs.next()){
               //Retrieve by column name
               int id  = rs.getInt("id");
               String map = rs.getString("niveau_name");

               //Display values
               System.out.print(" ID: " + id);
               System.out.println(", Map : " + map);
            System.out.println("TEST FIN REQUETE");

           
            return true;}
        } catch (final SQLException exception) {
            exception.printStackTrace();
        }
        return false;
    }

    /**
     * Execute query.
     *
     * @param query
     *            the query
     * @return the result set
     */
    public ResultSet executeQuery(final String query) {
        try {
            return this.getStatement().executeQuery(query);
        } catch (final SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Prepare call.
     *
     * @param query
     *            the query
     * @return the java.sql. callable statement
     */
    public java.sql.CallableStatement prepareCall(final String query) {
        try {
            return this.getConnection().prepareCall(query);
        } catch (final SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Execute update.
     *
     * @param query
     *            the query
     * @return the int
     */
    public int executeUpdate(final String query) {
        try {
            return this.statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
        } catch (final SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Gets the connection.
     *
     * @return the connection
     */
    public Connection getConnection() {
        return this.connection;
    }

    /**
     * Sets the connection.
     *
     * @param connection
     *            the new connection
     */
    public void setConnection(final Connection connection) {
        this.connection = connection;
    }

    /**
     * Gets the statement.
     *
     * @return the statement
     */
    public Statement getStatement() {
        return this.statement;
    }

    /**
     * Sets the statement.
     *
     * @param statement
     *            the new statement
     */
    public void setStatement(final Statement statement) {
        this.statement = statement;
    }
}