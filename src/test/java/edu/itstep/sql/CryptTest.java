package edu.itstep.sql;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;
import org.apache.log4j.Logger;


public class CryptTest {
    Logger log = Logger.getLogger(CryptTest.class);
  //  String salt = BCrypt.gensalt();
//      String password = "test";//get by user
//      String hashPassw = BCrypt.hashpw(password,salt);
//$2a$10$jS.3CUVxmNOfB77JqP5PHuuVwWHJRp9sAQAtdQ8FssTyFs4a4dFPm
    @Test
    void testHash(){
        String salt = BCrypt.gensalt();
        String hashPass = BCrypt.hashpw("test",salt);
        log.info(hashPass);
        Assertions.assertTrue(BCrypt.checkpw("test",hashPass));
        Assertions.assertFalse("test".equalsIgnoreCase(hashPass));
    }
}
