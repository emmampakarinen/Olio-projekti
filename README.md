# Olio-ohjelmointi harjoitustyöprojekti 2023

## Luokkakaavio ##

![image](https://user-images.githubusercontent.com/118518912/235314408-47a01612-5f5a-42b3-8fbf-9d8e5606ed73.png)

## Implementoidut ominaisuudet ##

* Perusvaatimukset
  * Lutemoneja voi siirtää, niitä voi laittaa taistelemaan keskenään ja niitä pystyy luomaan
* RecycleView
  * Toteutettu esim. kaikkien Lutemonien listauksessa, lutemonien listauksessa lokaatioissa sekä checkboxien listauksessa.
* Lutemoneilla kuva
  * Lutemonille voi valita kuvan värin perusteella kahdesta vaihtoehdosta
* Taistelu visualisoitu (2p arvoisesti omasta mielestä)
  * Taistelussa näytetään kumpi Lutemon hyökkää ja kumpi puolustautuu kuvien avulla. Taistelussa myös näytetään kuluva taistelukierros sekä Lutemonin elämät. 
  * Taistelun lopuksi myös näytetään kruunua tai hautakiveä sen Lutemonin päällä, joka voitti/hävisi taistelun. 
* Tilastot ja visualisointi
  * Tilastoitu voitetut ja hävityt taistelut per Lutemon ja ne on myös visualisoitu Anychartin avulla.
* Kuolema pois
  * Lutemon ei kuole hävittyään taistelun, vaan siirtyy takaisin kotiin. Jotta Lutemonia voi viedä uudelleen taisteluihin, sen pitää käydä Spa:ssa lataamassa elämät täyteen. 
* Fragmentit
  * Fragmentteja hyödynnetty lokaatioissa sekä tilastojen näyttämisessä
* Tietojen tallennus ja lataus 
* Omat lisätyt ominaisuudet
  * Spa -ominaisuus
    * Spa on merkattu yhdeksi lokaatioista. Spa:ssa on metodi, jossa Lutemon saa elämänsä täyteen.
    * Lutemon ei myöskään voi treenata tai mennä taisteluun ellei sen elämät ole täynnä. Jos Lutemonia yrittää siirtää, kun sen elämät ei ole täynnä, 
    käyttäjälle tulee Toast-ilmoitus, jossa kehotetaan käyttämään Lutemonia Spa:ssa
   
   
 ## Yleinen kuvaus työstä ##
 
 Toteutin kurssilla valmiiksi ehdotetun projektin eli Lutemon -ohjelman. Ohjelmassa voi luoda Lutemoneja, pelata niillä sekä vaihtaa 
 niiden lokaatioita. Lokaatioilla on ominaisuuksia, joilla voi esim. kasvattaa Lutemonin hyökkäystaitoja. Taisteluun valitaan kaksi Lutemonia, 
 jotka taistelevat, kunnes jomman kumman elämät vähenevät nollaan tai alle. Voittaja saa kokemus- ja hyökkäyspisteitä. Ohjelmaan on lisätty mahdollisuus tarkastella
 luotujen Lutemonien voitettuja/hävittyjä taisteluja visuaalisin keinoin. Ohjelman visuaaliseen näyttävyyteen on myös käytetty aikaa ja resursseja.  
   
 Etsin tietoa netistä, miten toteuttaa erilaisia toiminnallisuuksia työssä, kuten fragmenttien luonti (loin fragmentit työhön ennen kuin ne tulivat
 kurssin materiaaleihin saataville, minkä takia toteutus eroaa hieman siitä mitä kurssilla opittiin). Työstä löytyy lähteet kommentoituna niistä kohdista, joissa 
 ulkoisia lähteitä hyödynnettiin. Muutoin työssä on käytetty kurssilla jaettuja tietoja ja oppeja.   
   
 ## Ohjelman demovideo (linkki) ##
 https://youtu.be/MjJ3mDDnAck
    
