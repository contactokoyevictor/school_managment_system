/*
 * SIVOTEK SCHOOL  MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LTD.
 * ALL RIGHT RESERVED 2016
 */

package com.sivotek.school_management_system.app.crud;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 *
 * @author acer
 */
public  class IdentityGen {
    private static long new_id = 1L;
    private static Integer int_id;
    public static final long getGen()
    {
       String store =  System.currentTimeMillis()+"";
       new_id = Long.parseLong(store.substring(0, 12));
      return new_id;
    }
    
    public static final Integer getIntGen() throws NoSuchAlgorithmException
    {
        SecureRandom rng = SecureRandom.getInstance("SHA1PRNG");
        rng.setSeed(System.currentTimeMillis());
        Integer numberToGenerate = 1;
        String temp ="";
        for(Integer j=0; j<numberToGenerate; j++)
        {
            Long randLong = rng.nextLong();
            if(randLong > 0)
            {
                temp = randLong+"";
                new_id = Integer.parseInt(temp.substring(0, 10));
                System.out.println(new_id);
            }
            
        }
      
      
      
      return int_id;
    }
   
}
