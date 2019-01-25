/*
 * SIVOTEK SCHOOL  MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LTD.
 * ALL RIGHT RESERVED 2016
 */

package com.sivotek.school_management_system.app.pojo;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 *
 * @author acer
 */
public class SecureRamdomGenerator {
    public static void main(String args[]) throws NoSuchAlgorithmException
    {
        SecureRandom rng = SecureRandom.getInstance("SHA1PRNG");
        rng.setSeed(System.currentTimeMillis());
        
        int numberToGenerate = new Integer(100000);
        byte randNumbers[] = new byte[numberToGenerate];
        //Long randLung = 10L;
        //rng.nextLong();
        rng.nextBytes(randNumbers);
        for(int j=0; j<numberToGenerate; j++)
        {
            Long randLong = rng.nextLong();
            if(randLong > 0)
            {
                String temp = randLong+"";
                System.out.println(temp.substring(0, 10));
            }
            
        }
        System.out.println(" ");
    }
}
