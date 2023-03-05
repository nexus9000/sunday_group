package edu.itstep.sql;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.*;

import java.sql.*;
import java.util.HashMap;
import java.util.Optional;

//import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class DBTest {
    private static final String URL = "jdbc:sqlite:test.db";
    static Logger logger = Logger.getLogger(DBTest.class);
    private Connection conn ;
    private Statement st;
    private PreparedStatement ps;
    private ResultSet rs;//cursor database
    private  HashMap<String, String> users;
    private final String USER_NAME = "user20";
    private final String PASSWORD = "password20";
    @BeforeEach
    public void setUp()throws SQLException {
        conn = DriverManager.getConnection(URL);
        st =  conn.createStatement();

        users = new HashMap<>();
    }
   @Test
   void testIsUserPasswordIsValid()throws SQLException{
        //prevent sql injection by using PreparedStatement
        String sql = "select user_name from users where user_name=? and password=?";
        ps = conn.prepareStatement(sql);
        ps.setString(1,USER_NAME);
        ps.setString(2, PASSWORD);
        rs = ps.executeQuery();
        boolean isPasswordOK = false;
        if(rs.next())isPasswordOK = true;
        Assertions.assertTrue(isPasswordOK);
   }

    @Test
    void testConnectionDB()throws Exception {
      //  assertTrue(conn instanceof Connection);
       // assertThat(conn, instanseOf);
        //assertThat(conn, instanceOf(Connection));
    }

    @Test
    void testCreateTable()throws SQLException{
        String sql = "create table if not exists users  (" +
                "id integer primary key  AUTOINCREMENT,"+
                "user_name text not null UNIQUE,"+
                "password text not null)";
        st.execute(sql);


    }
    @Test
    @Disabled
    void testSelectStatement()throws SQLException{
        String sql = "select user_name,password from users";
        rs = st.executeQuery(sql);
        Optional<String> userName = Optional.of("dummy user");
        Optional<String> password = Optional.of("dummy password");
        while(rs.next()){
            userName = Optional.of(rs.getString(1));
            password = Optional.of(rs.getString(2));
            users.put(userName.toString(),password.toString());
            System.out.println(userName.get()+ " "+password.get());
        }
       assertEquals(5,users.size());

    }
    @Test
    void testCreateTableAlbumbs()throws SQLException{

        String sql = "create table if not exists albums(" +
                "id integer primary key  AUTOINCREMENT," +
                "album_name text not null unique)";
        st.execute(sql);
     }
    @Test
    @Disabled
    void testAddAlbumsRecords()throws SQLException{
        for(int i = 1; i < 1_000; i++){
            String sql = "insert into albums (album_name) values ('"+ RandomStringUtils.randomAlphabetic(10)+"')";
            System.out.println(sql);//sql injection
            st.addBatch(sql);
        }
        st.executeBatch();
        st.clearBatch();
    }
    @Test
    void testCreateUsersCollections()throws SQLException{
        String sql = "create table if not exists users_collections(" +
                "user_id integer not null," +
                "album_id integer not null," +
                "Foreign KEY (user_id) REFERENCES users (id)," +
                "Foreign Key (album_id) References albums(id))";
        boolean result = st.execute(sql);
        assertFalse(result);
    }
    @Test
    @Disabled
    void testAddRecords()throws SQLException{
        for(int i = 20; i < 30; i++){
            String sql = "insert into users (user_name,password) values ('user"+i+"','password"+i+"')";
            System.out.println(sql);//sql injection
            st.addBatch(sql);
        }
         st.executeBatch();
         st.clearBatch();
    }


    @AfterEach
    void tearDown()throws SQLException{
        conn.close();
        st.close();
        //rs.close();
        users = null;
    }

}
