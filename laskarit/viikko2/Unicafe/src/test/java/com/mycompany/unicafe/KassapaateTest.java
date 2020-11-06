package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class KassapaateTest {
    Kassapaate paate;
    Maksukortti kortti;
    
    @Before
    public void setUp() {
        paate = new Kassapaate();
        kortti = new Maksukortti(1000);
    }
    
    @Test
    public void luodunPaatteenRahamaara1000() {
        assertEquals(100000, paate.kassassaRahaa());
    }
    
    @Test
    public void luodunPaatteenMyydytEdulliset() {
        assertEquals(0, paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void luodunPaatteenMyydytMaukkaat() {
        assertEquals(0, paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kassanRahamaaraKasvaaKunOstetaanEdullinenLounas() {
        paate.syoEdullisesti(500);
        assertEquals(100240, paate.kassassaRahaa());
    }
    
    @Test
    public void vaihtorahaOikeinKunOstetaanEdullinenLounas() {
        assertEquals(260, paate.syoEdullisesti(500));
    }
    
    @Test
    public void edullistenLounaidenMaaraKasvaa() {
        paate.syoEdullisesti(500);
        assertEquals(1, paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void kassanRahamaaraKasvaaKunOstetaanMaukasLounas() {
        paate.syoMaukkaasti(500);
        assertEquals(100400, paate.kassassaRahaa());
    }
    
    @Test
    public void vaihtorahaOikeinKunOstetaanMaukasLounas() {
        assertEquals(100, paate.syoMaukkaasti(500));
    }
    
    @Test
    public void maukkaidenLounaidenMaaraKasvaa() {
        paate.syoMaukkaasti(500);
        assertEquals(1, paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kassanRahamaaraEiKasvaKunTarjotaanLiianPieniSummaEdullinen() {
        paate.syoEdullisesti(200);
        assertEquals(100000, paate.kassassaRahaa());
    }
    
    @Test
    public void kaikkiRahatPalautetaanKunTarjotaanLiianPieniSummaEdullinen() {
        assertEquals(200, paate.syoEdullisesti(200));
    }
    
    @Test
    public void edullistenLounaidenMaaraEiKasva() {
        paate.syoEdullisesti(200);
        assertEquals(0, paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void kassanRahamaaraEiKasvaKunTarjotaanLiianPieniSummaMaukas() {
        paate.syoMaukkaasti(200);
        assertEquals(100000, paate.kassassaRahaa());
    }
    
    @Test
    public void kaikkiRahatPalautetaanKunTarjotaanLiianPieniSummaMaukas() {
        assertEquals(200, paate.syoMaukkaasti(200));
    }
    
    @Test
    public void maukkaidenLounaidenMaaraEiKasva() {
        paate.syoMaukkaasti(200);
        assertEquals(0, paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void veloitetaanSummaKortiltaJosKortillaRahaaEdulliseen() {
        paate.syoEdullisesti(kortti);
        assertEquals(760, kortti.saldo());
    }
    
    @Test
    public void palautetaanTrueJosKortillaRahaaEdulliseenOstoon() {
        assertTrue(paate.syoEdullisesti(kortti));
    } 
    
    @Test
    public void myytyjenEdullistenMaaraKasva() {
        paate.syoEdullisesti(kortti);
        assertEquals(1, paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void veloitetaanSummaKortiltaJosKortillaRahaaMaukkaaseen() {
        paate.syoMaukkaasti(kortti);
        assertEquals(600, kortti.saldo());
    }
    
    @Test
    public void palautetaanTrueJosKortillaRahaaMaukkaaseenOstoon() {
        assertTrue(paate.syoMaukkaasti(kortti));
    } 
    
    @Test
    public void myytyjenMaukkaidenMaaraKasva() {
        paate.syoMaukkaasti(kortti);
        assertEquals(1, paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void eiVeloitetaJosKortillaEiRahaaEdulliseen() {
        paate.syoMaukkaasti(kortti);
        paate.syoMaukkaasti(kortti);
        paate.syoEdullisesti(kortti);
        assertEquals(200, kortti.saldo());
    }
    
    @Test
    public void palautetaanFalseJosKortillaEiRahaaEdulliseen() {
        paate.syoMaukkaasti(kortti);
        paate.syoMaukkaasti(kortti);
        assertFalse(paate.syoEdullisesti(kortti));
    }
    
    @Test
    public void myytyjenEdullistenMaaraEiMuutuJosKortillaEiRahaa() {
        paate.syoMaukkaasti(kortti);
        paate.syoMaukkaasti(kortti);
        paate.syoEdullisesti(kortti);
        assertEquals(0, paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void eiVeloitetaJosKortillaEiRahaaMaukkaaseen() {
        paate.syoMaukkaasti(kortti);
        paate.syoMaukkaasti(kortti);
        paate.syoMaukkaasti(kortti);
        assertEquals(200, kortti.saldo());
    }
    
    @Test
    public void palautetaanFalseJosKortillaEiRahaaMaukkaaseen() {
        paate.syoMaukkaasti(kortti);
        paate.syoMaukkaasti(kortti);
        assertFalse(paate.syoMaukkaasti(kortti));
    }
    
    @Test
    public void myytyjenMaukkaidenMaaraEiMuutuJosKortillaEiRahaa() {
        paate.syoEdullisesti(kortti);
        paate.syoEdullisesti(kortti);
        paate.syoEdullisesti(kortti);
        paate.syoMaukkaasti(kortti);
        assertEquals(0, paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kassanRahamaaraEiMuutuKortillaOstettaessa() {
        paate.syoEdullisesti(kortti);
        paate.syoMaukkaasti(kortti);
        assertEquals(100000, paate.kassassaRahaa());
    }
    
    @Test
    public void kortinSaldoMuuttuuRahaaLadattaessa() {
        paate.lataaRahaaKortille(kortti, 100);
        assertEquals(1100, kortti.saldo());
    }
    
    @Test
    public void kassanRahamaaraMuuttuuRahaaLadattaessa() {
        paate.lataaRahaaKortille(kortti, 100);
        assertEquals(100100, paate.kassassaRahaa());
    }
    
    @Test
    public void saldoEiMuutuLadattaessaJosSummaPienempiKuinNolla() {
        paate.lataaRahaaKortille(kortti, -100);
        assertEquals(1000, kortti.saldo());
    }
    
    @Test
    public void kassanRahamaaraEiKasvaLadattaessaJosSummaPienempiKuinNolla() {
        paate.lataaRahaaKortille(kortti, -100);
        assertEquals(100000, paate.kassassaRahaa());
    }
    
    
}
 