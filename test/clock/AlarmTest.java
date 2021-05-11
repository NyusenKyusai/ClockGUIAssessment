/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clock;

import java.util.Date;
import javax.swing.JSpinner;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jonah
 */
public class AlarmTest {
    
    public AlarmTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getDate method, of class Alarm.
     */
    @Test
    public void testGetDate() {
        System.out.println("getDate");
        
        Date expResult = new Date();
        expResult.setTime(0);
        
        Alarm instance = new Alarm(expResult);
        
        Date result = instance.getDate();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getDate method, of class Alarm.
     * 
     * 11/05/2021 15:30
     */
    @Test
    public void testGetDate2() {
        System.out.println("getDate2");
        
        Date expResult = new Date();
        expResult.setTime(1620747004);
        
        Alarm instance = new Alarm(expResult);
        
        Date result = instance.getDate();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getDate method, of class Alarm.
     * 
     * 2030/5/11 15:30
     */
    @Test
    public void testGetDate3() {
        System.out.println("getDate3");
        
        Date expResult = new Date();
        expResult.setTime(1904743804);
        
        Alarm instance = new Alarm(expResult);
        
        Date result = instance.getDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of getYear method, of class Alarm.
     */
    @Test
    public void testGetYear() {
        System.out.println("getYear");
        
        Date date = new Date();
        date.setYear(0);
        
        Alarm instance = new Alarm(date);
        
        String expResult = "1900";
        
        String result = instance.getYear();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getYear method, of class Alarm.
     */
    @Test
    public void testGetYear2() {
        System.out.println("getYear2");
        
        Date date = new Date();
        date.setYear(121);
        
        Alarm instance = new Alarm(date);
        
        String expResult = "2021";
        
        String result = instance.getYear();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getYear method, of class Alarm.
     */
    @Test
    public void testGetYear3() {
        System.out.println("getYear3");
        
        Date date = new Date();
        date.setYear(130);
        
        Alarm instance = new Alarm(date);
        
        String expResult = "2030";
        
        String result = instance.getYear();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMonth method, of class Alarm.
     */
    @Test
    public void testGetMonth() {
        System.out.println("getMonth");
        
        Date date = new Date();
        date.setMonth(0);
        
        Alarm instance = new Alarm(date);
        
        String expResult = "01";
        String result = instance.getMonth();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getMonth method, of class Alarm.
     */
    @Test
    public void testGetMonth1() {
        System.out.println("getMonth1");
        
        Date date = new Date();
        date.setMonth(0);
        
        Alarm instance = new Alarm(date);
        
        String expResult = "01";
        String result = instance.getMonth();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getMonth method, of class Alarm.
     */
    @Test
    public void testGetMonth2() {
        System.out.println("getMonth2");
        
        Date date = new Date();
        date.setMonth(1);
        
        Alarm instance = new Alarm(date);
        
        String expResult = "02";
        String result = instance.getMonth();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getMonth method, of class Alarm.
     */
    @Test
    public void testGetMonth3() {
        System.out.println("getMonth3");
        
        Date date = new Date();
        date.setMonth(2);
        
        Alarm instance = new Alarm(date);
        
        String expResult = "03";
        String result = instance.getMonth();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getMonth method, of class Alarm.
     */
    @Test
    public void testGetMonth4() {
        System.out.println("getMonth4");
        
        Date date = new Date();
        date.setMonth(3);
        
        Alarm instance = new Alarm(date);
        
        String expResult = "04";
        String result = instance.getMonth();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getMonth method, of class Alarm.
     */
    @Test
    public void testGetMonth5() {
        System.out.println("getMonth5");
        
        Date date = new Date();
        date.setMonth(4);
        
        Alarm instance = new Alarm(date);
        
        String expResult = "05";
        String result = instance.getMonth();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getMonth method, of class Alarm.
     */
    @Test
    public void testGetMonth6() {
        System.out.println("getMonth6");
        
        Date date = new Date();
        date.setMonth(5);
        
        Alarm instance = new Alarm(date);
        
        String expResult = "06";
        String result = instance.getMonth();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getMonth method, of class Alarm.
     */
    @Test
    public void testGetMonth7() {
        System.out.println("getMonth7");
        
        Date date = new Date();
        date.setMonth(6);
        
        Alarm instance = new Alarm(date);
        
        String expResult = "07";
        String result = instance.getMonth();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getMonth method, of class Alarm.
     */
    @Test
    public void testGetMonth8() {
        System.out.println("getMonth8");
        
        Date date = new Date();
        date.setMonth(7);
        
        Alarm instance = new Alarm(date);
        
        String expResult = "08";
        String result = instance.getMonth();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getMonth method, of class Alarm.
     */
    @Test
    public void testGetMonth9() {
        System.out.println("getMonth9");
        
        Date date = new Date();
        date.setMonth(8);
        
        Alarm instance = new Alarm(date);
        
        String expResult = "09";
        String result = instance.getMonth();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getMonth method, of class Alarm.
     */
    @Test
    public void testGetMonth12() {
        System.out.println("getMonth12");
        
        Date date = new Date();
        date.setMonth(11);
        
        Alarm instance = new Alarm(date);
        
        String expResult = "12";
        String result = instance.getMonth();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDay method, of class Alarm.
     */
    @Test
    public void testGetDay() {
        System.out.println("getDay");
        
        Date date = new Date();
        date.setDate(1);
        
        Alarm instance = new Alarm(date);
        
        String expResult = "01";
        String result = instance.getDay();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getDay method, of class Alarm.
     */
    @Test
    public void testGetDay2() {
        System.out.println("getDay2");
        
        Date date = new Date();
        date.setDate(2);
        
        Alarm instance = new Alarm(date);
        
        String expResult = "02";
        String result = instance.getDay();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getDay method, of class Alarm.
     */
    @Test
    public void testGetDay3() {
        System.out.println("getDay3");
        
        Date date = new Date();
        date.setDate(3);
        
        Alarm instance = new Alarm(date);
        
        String expResult = "03";
        String result = instance.getDay();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getDay method, of class Alarm.
     */
    @Test
    public void testGetDay4() {
        System.out.println("getDay4");
        
        Date date = new Date();
        date.setDate(4);
        
        Alarm instance = new Alarm(date);
        
        String expResult = "04";
        String result = instance.getDay();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getDay method, of class Alarm.
     */
    @Test
    public void testGetDay5() {
        System.out.println("getDay5");
        
        Date date = new Date();
        date.setDate(5);
        
        Alarm instance = new Alarm(date);
        
        String expResult = "05";
        String result = instance.getDay();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getDay method, of class Alarm.
     */
    @Test
    public void testGetDay6() {
        System.out.println("getDay6");
        
        Date date = new Date();
        date.setDate(6);
        
        Alarm instance = new Alarm(date);
        
        String expResult = "06";
        String result = instance.getDay();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getDay method, of class Alarm.
     */
    @Test
    public void testGetDay7() {
        System.out.println("getDay7");
        
        Date date = new Date();
        date.setDate(7);
        
        Alarm instance = new Alarm(date);
        
        String expResult = "07";
        String result = instance.getDay();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getDay method, of class Alarm.
     */
    @Test
    public void testGetDay8() {
        System.out.println("getDay8");
        
        Date date = new Date();
        date.setDate(8);
        
        Alarm instance = new Alarm(date);
        
        String expResult = "08";
        String result = instance.getDay();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getDay method, of class Alarm.
     */
    @Test
    public void testGetDay9() {
        System.out.println("getDay9");
        
        Date date = new Date();
        date.setDate(9);
        
        Alarm instance = new Alarm(date);
        
        String expResult = "09";
        String result = instance.getDay();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getDay method, of class Alarm.
     */
    @Test
    public void testGetDay10() {
        System.out.println("getDay30");
        
        Date date = new Date();
        date.setDate(30);
        
        Alarm instance = new Alarm(date);
        
        String expResult = "30";
        String result = instance.getDay();
        assertEquals(expResult, result);
    }

    /**
     * Test of getHours method, of class Alarm.
     */
    @Test
    public void testGetHours() {
        System.out.println("getHours");
        
        Date date = new Date();
        date.setHours(0);
        
        Alarm instance = new Alarm(date);
        
        String expResult = "00";
        String result = instance.getHours();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getHours method, of class Alarm.
     */
    @Test
    public void testGetHours2() {
        System.out.println("getHours2");
        
        Date date = new Date();
        date.setHours(1);
        
        Alarm instance = new Alarm(date);
        
        String expResult = "01";
        String result = instance.getHours();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getHours method, of class Alarm.
     */
    @Test
    public void testGetHours3() {
        System.out.println("getHours3");
        
        Date date = new Date();
        date.setHours(2);
        
        Alarm instance = new Alarm(date);
        
        String expResult = "02";
        String result = instance.getHours();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getHours method, of class Alarm.
     */
    @Test
    public void testGetHours4() {
        System.out.println("getHours4");
        
        Date date = new Date();
        date.setHours(3);
        
        Alarm instance = new Alarm(date);
        
        String expResult = "03";
        String result = instance.getHours();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getHours method, of class Alarm.
     */
    @Test
    public void testGetHours5() {
        System.out.println("getHours5");
        
        Date date = new Date();
        date.setHours(4);
        
        Alarm instance = new Alarm(date);
        
        String expResult = "04";
        String result = instance.getHours();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getHours method, of class Alarm.
     */
    @Test
    public void testGetHours6() {
        System.out.println("getHours6");
        
        Date date = new Date();
        date.setHours(5);
        
        Alarm instance = new Alarm(date);
        
        String expResult = "05";
        String result = instance.getHours();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getHours method, of class Alarm.
     */
    @Test
    public void testGetHours7() {
        System.out.println("getHours7");
        
        Date date = new Date();
        date.setHours(6);
        
        Alarm instance = new Alarm(date);
        
        String expResult = "06";
        String result = instance.getHours();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getHours method, of class Alarm.
     */
    @Test
    public void testGetHours8() {
        System.out.println("getHours8");
        
        Date date = new Date();
        date.setHours(7);
        
        Alarm instance = new Alarm(date);
        
        String expResult = "07";
        String result = instance.getHours();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getHours method, of class Alarm.
     */
    @Test
    public void testGetHours9() {
        System.out.println("getHours9");
        
        Date date = new Date();
        date.setHours(8);
        
        Alarm instance = new Alarm(date);
        
        String expResult = "08";
        String result = instance.getHours();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getHours method, of class Alarm.
     */
    @Test
    public void testGetHours10() {
        System.out.println("getHours10");
        
        Date date = new Date();
        date.setHours(9);
        
        Alarm instance = new Alarm(date);
        
        String expResult = "09";
        String result = instance.getHours();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getHours method, of class Alarm.
     */
    @Test
    public void testGetHours11() {
        System.out.println("getHours11");
        
        Date date = new Date();
        date.setHours(11);
        
        Alarm instance = new Alarm(date);
        
        String expResult = "11";
        String result = instance.getHours();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMinutes method, of class Alarm.
     */
    @Test
    public void testGetMinutes() {
        System.out.println("getMinutes");
        
        Date date = new Date();
        date.setMinutes(0);
        
        Alarm instance = new Alarm(date);
        
        String expResult = "00";
        String result = instance.getMinutes();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getMinutes method, of class Alarm.
     */
    @Test
    public void testGetMinutes2() {
        System.out.println("getMinutes2");
        
        Date date = new Date();
        date.setMinutes(1);
        
        Alarm instance = new Alarm(date);
        
        String expResult = "01";
        String result = instance.getMinutes();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getMinutes method, of class Alarm.
     */
    @Test
    public void testGetMinutes3() {
        System.out.println("getMinutes3");
        
        Date date = new Date();
        date.setMinutes(2);
        
        Alarm instance = new Alarm(date);
        
        String expResult = "02";
        String result = instance.getMinutes();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getMinutes method, of class Alarm.
     */
    @Test
    public void testGetMinutes4() {
        System.out.println("getMinutes4");
        
        Date date = new Date();
        date.setMinutes(3);
        
        Alarm instance = new Alarm(date);
        
        String expResult = "03";
        String result = instance.getMinutes();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getMinutes method, of class Alarm.
     */
    @Test
    public void testGetMinutes5() {
        System.out.println("getMinutes5");
        
        Date date = new Date();
        date.setMinutes(4);
        
        Alarm instance = new Alarm(date);
        
        String expResult = "04";
        String result = instance.getMinutes();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getMinutes method, of class Alarm.
     */
    @Test
    public void testGetMinutes6() {
        System.out.println("getMinutes6");
        
        Date date = new Date();
        date.setMinutes(5);
        
        Alarm instance = new Alarm(date);
        
        String expResult = "05";
        String result = instance.getMinutes();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getMinutes method, of class Alarm.
     */
    @Test
    public void testGetMinutes7() {
        System.out.println("getMinutes7");
        
        Date date = new Date();
        date.setMinutes(6);
        
        Alarm instance = new Alarm(date);
        
        String expResult = "06";
        String result = instance.getMinutes();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getMinutes method, of class Alarm.
     */
    @Test
    public void testGetMinutes8() {
        System.out.println("getMinutes8");
        
        Date date = new Date();
        date.setMinutes(7);
        
        Alarm instance = new Alarm(date);
        
        String expResult = "07";
        String result = instance.getMinutes();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getMinutes method, of class Alarm.
     */
    @Test
    public void testGetMinutes9() {
        System.out.println("getMinutes9");
        
        Date date = new Date();
        date.setMinutes(8);
        
        Alarm instance = new Alarm(date);
        
        String expResult = "08";
        String result = instance.getMinutes();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getMinutes method, of class Alarm.
     */
    @Test
    public void testGetMinutes10() {
        System.out.println("getMinutes10");
        
        Date date = new Date();
        date.setMinutes(9);
        
        Alarm instance = new Alarm(date);
        
        String expResult = "09";
        String result = instance.getMinutes();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getMinutes method, of class Alarm.
     */
    @Test
    public void testGetMinutes11() {
        System.out.println("getMinutes11");
        
        Date date = new Date();
        date.setMinutes(59);
        
        Alarm instance = new Alarm(date);
        
        String expResult = "59";
        String result = instance.getMinutes();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getMinutesPlus1 method, of class Alarm.
     */
    @Test
    public void testGetMinutesPlus1() {
        System.out.println("getMinutesPlus1");
        
        Date date = new Date();
        date.setMinutes(0);
        
        Alarm instance = new Alarm(date);
        
        String expResult = "01";
        String result = instance.getMinutesPlus1();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMinutesPlus1 method, of class Alarm.
     */
    @Test
    public void testGetMinutesPlus1_1() {
        System.out.println("getMinutesPlus1_1");
        
        Date date = new Date();
        date.setMinutes(1);
        
        Alarm instance = new Alarm(date);
        
        String expResult = "02";
        String result = instance.getMinutesPlus1();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getMinutesPlus1 method, of class Alarm.
     */
    @Test
    public void testGetMinutesPlus_2() {
        System.out.println("getMinutesPlus1_2");
        
        Date date = new Date();
        date.setMinutes(2);
        
        Alarm instance = new Alarm(date);
        
        String expResult = "03";
        String result = instance.getMinutesPlus1();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMinutesPlus1 method, of class Alarm.
     */
    @Test
    public void testGetMinutesPlus1_3() {
        System.out.println("getMinutesPlus1_3");
        
        Date date = new Date();
        date.setMinutes(3);
        
        Alarm instance = new Alarm(date);
        
        String expResult = "04";
        String result = instance.getMinutesPlus1();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getMinutesPlus1 method, of class Alarm.
     */
    @Test
    public void testGetMinutesPlus_4() {
        System.out.println("getMinutesPlus1_4");
        
        Date date = new Date(4);
        date.setMinutes(4);
        
        Alarm instance = new Alarm(date);
        
        String expResult = "05";
        String result = instance.getMinutesPlus1();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMinutesPlus1 method, of class Alarm.
     */
    @Test
    public void testGetMinutesPlus1_5() {
        System.out.println("getMinutesPlus1_5");
        
        Date date = new Date(5);
        date.setMinutes(5);
        
        Alarm instance = new Alarm(date);
        
        String expResult = "06";
        String result = instance.getMinutesPlus1();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getMinutesPlus1 method, of class Alarm.
     */
    @Test
    public void testGetMinutesPlus_6() {
        System.out.println("getMinutesPlus1_6");
        
        Date date = new Date();
        date.setMinutes(6);
        
        Alarm instance = new Alarm(date);
        
        String expResult = "07";
        String result = instance.getMinutesPlus1();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMinutesPlus1 method, of class Alarm.
     */
    @Test
    public void testGetMinutesPlus1_7() {
        System.out.println("getMinutesPlus1_7");
        
        Date date = new Date();
        date.setMinutes(7);
        
        Alarm instance = new Alarm(date);
        
        String expResult = "08";
        String result = instance.getMinutesPlus1();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getMinutesPlus1 method, of class Alarm.
     */
    @Test
    public void testGetMinutesPlus_8() {
        System.out.println("getMinutesPlus1_8");
        
        Date date = new Date();
        date.setMinutes(8);
        
        Alarm instance = new Alarm(date);
        
        String expResult = "09";
        String result = instance.getMinutesPlus1();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMinutesPlus1 method, of class Alarm.
     */
    @Test
    public void testGetMinutesPlus1_9() {
        System.out.println("getMinutesPlus1_9");
        
        Date date = new Date();
        date.setMinutes(59);
        
        Alarm instance = new Alarm(date);
        
        String expResult = "00";
        String result = instance.getMinutesPlus1();
        assertEquals(expResult, result);
    } 
}
