package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(1000);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void kortinSaldoAlussaOikein() {
        assertEquals("saldo: 10.0", kortti.toString());
    }
    
    @Test
    public void kortinLatausToimii() {
        kortti.lataaRahaa(120);
        assertEquals("saldo: 11.20", kortti.toString());
    }
    
    @Test
    public void saldoVaheneeOikeinJosRahaaTarpeeksi() {
        kortti.otaRahaa(100);
        assertEquals(900, kortti.saldo());
    }
    
    @Test
    public void saldoEiVaheneJosRahaaEiTarpeeksi() {
        kortti.otaRahaa(1100);
        assertEquals(1000, kortti.saldo());
    }
    
    @Test
    public void palautuuTrueJosRahatRiittavat() {
        assertTrue(kortti.otaRahaa(100));
    }
    
    @Test
    public void palautuuFalseJosRahatEivatRiita() {
        assertFalse(kortti.otaRahaa(1100));
    }
}
