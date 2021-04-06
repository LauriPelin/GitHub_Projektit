using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using System.Text.RegularExpressions;
using System.Linq;
using System;
using UnityEngine.SceneManagement;

public class PeliManageri : MonoBehaviour
// * 
// LAURI PELIN SOOLOPROJEKTI DTEK0097 2021 
// PeliManageri on tämän pelin "aivot". Se käyttää pelintuottamiseen kuitenkin myös muita luokkia.
// Tietokonepelaajan vuorottaisissa ohjusiskuissa ongelmia, ei jäänyt aikaa korjata.
// Pelissä on myös todennäköisesti memory leakkeja, mikä voi johtaa satunnaisiin crasheihin.
// *
{
    [Header("Laivat")]
    private int laivaIndex = 0;
    public List<TileScript> allTilesScript;
    public GameObject[] laivat;
    private ShipScript shipScript;
    public Vihollinen enemyScript;
    [Header("HUD")]
    public Button GoButton;
    public Button PyöräytysButton;
    public Button replayBtn;
    public Button QuitBtn;
    public Text TextTop;
    public Text pelaajaText;
    public Text enemyText;

    [Header("Objects")]
    public GameObject OhjusPrefab;
    public GameObject VihollisenOhjusPrefab;
    public GameObject TuliPrefab;
    public GameObject WoodDock;


    private bool setupComplete = false;
    private bool pelaajanVuoro = true;
    private List<int[]> enemyShips;
    private int enemyShipCount = 15;
    private int playShipCount = 15;
    private List<GameObject> pelaajanTulet = new List <GameObject>();
    private List<GameObject> vihollisenTulet = new List <GameObject>();


    // Start is called before the first frame update
    void Start()
    {
        shipScript = laivat[laivaIndex].GetComponent<ShipScript>();
        GoButton.onClick.AddListener(()=> NextShipClicked());
        PyöräytysButton.onClick.AddListener(()=> RotateClicked());
        replayBtn.onClick.AddListener(()=> ReplayClicked());
        QuitBtn.onClick.AddListener(()=> QuitClicked());
        enemyShips = enemyScript.PlaceEnemyShips();
    }
    // Vaatimuksissa pyydetty pyöräytys R painikkeella
    void Update(){
        if(Input.GetKeyDown(KeyCode.R)){
            RotateClicked();
        }
    }
        
    private void NextShipClicked()
    {
        if (!shipScript.OnGameBoard())
        {
            shipScript.FlashColor(Color.red);
        } else
        {
            if(laivaIndex <= laivat.Length - 2)
            {
                laivaIndex++;
                shipScript = laivat[laivaIndex].GetComponent<ShipScript>();
                shipScript.FlashColor(Color.yellow);
            }
            else
            {
                PyöräytysButton.gameObject.SetActive(false);
                GoButton.gameObject.SetActive(false);
                WoodDock.SetActive(false);
                TextTop.text = "Valitse Ohjuskohde";
                setupComplete = true;
                for (int i = 0; i < laivat.Length; i++) laivat[i].SetActive(false);
            }
        }
        
    }

   // * Tile on pelin "meriruutu". 
    public void TileClicked(GameObject tile){
        if(setupComplete && pelaajanVuoro){
            Vector3 tilePos=tile.transform.position;
            tilePos.y += 15;
            pelaajanVuoro=false;
            Instantiate(OhjusPrefab, tilePos, OhjusPrefab.transform.rotation);
        } else if (!setupComplete){
            asetaLaiva(tile);
            shipScript.SetClickedTile(tile);
        }
    }
    private void asetaLaiva(GameObject tile){
        shipScript = laivat[laivaIndex].GetComponent<ShipScript>();
        shipScript.PuhdistaTileList();
        Vector3 newVec = shipScript.GetOffsetVec(tile.transform.position);
        laivat[laivaIndex].transform.localPosition = newVec; 
    }
    private void RotateClicked(){
        shipScript.RotateShip();
    }
    public void CheckHit(GameObject tile){
        int tileNum = Int32.Parse(Regex.Match(tile.name, @"\d+").Value);
        int hitCount = 0;
        foreach(int[] tileNumArray in enemyShips){
            if(tileNumArray.Contains(tileNum)){
                for(int i = 0; i<tileNumArray.Length; i++){
                    if(tileNumArray[i] == tileNum){
                    tileNumArray[i] = -5;
                    hitCount++;
                }
                else if(tileNumArray[i] == -5){
                    hitCount++;
                }
            }
            if(hitCount == tileNumArray.Length){
                enemyShipCount--;
                TextTop.text = "OSUI JA UPPOSI";
                vihollisenTulet.Add(Instantiate(TuliPrefab, tile.transform.position, Quaternion.identity));
                tile.GetComponent<TileScript>().SetTileColor(1, new Color32(68, 0, 0, 255));
                tile.GetComponent<TileScript>().SwitchColors(1);
                
            }
            else{
                TextTop.text="OSUI";
                tile.GetComponent<TileScript>().SetTileColor(1, new Color32(255, 0, 0, 255));
                tile.GetComponent<TileScript>().SwitchColors(1);
                
            }
            break;
        }
        }
        if(hitCount== 0){
            tile.GetComponent<TileScript>().SetTileColor(1, new Color32(38, 57, 76, 255));
            tile.GetComponent<TileScript>().SwitchColors(1);
            TextTop.text="HUTI";
            
        }
        // * pelkästään Invoke("EndPlayerTurn", 1.0f); vuorottaisessa pelaamisessa *
        if(hitCount ==0){
            Invoke("EndPlayerTurn", 1.0f);
        }
        else{
            Invoke("EndEnemyTurn", 1.0f);
        }
            
    }
    public void EnemyHitPlayer(Vector3 tile, int tileNum, GameObject hitObj){
        enemyScript.MissileHit(tileNum);
        tile.y+=0.2f;
        pelaajanTulet.Add(Instantiate(TuliPrefab, tile, Quaternion.identity));
        if(hitObj.GetComponent<ShipScript>().HitCheckSunk()){
            playShipCount--;
            pelaajaText.text = playShipCount.ToString();
            enemyScript.SunkPlayer();
        }
        Invoke("EndEnemyTurn", 1.0f);
        Invoke("EndPlayerTurn", 1.0f); // vuorottainen pelaaminen ilman tätä
        // * Tietokonepelaajan saadessa vuoron se saattaa täräyttää 
        // * useampaan kertaan samaan laivaan, ja se luulee saaneensa koko laivan alas
        // * Tämän korjaukseen ei valitettavasti jäänyt aikaa.
        
        if (playShipCount < 1) GameOver("Hävisit pelin"); // * Check

    }
    public void EndPlayerTurn()
    {
        for (int i = 0; i < laivat.Length; i++) laivat[i].SetActive(true);
        foreach (GameObject tuli in pelaajanTulet) tuli.SetActive(true);
        foreach (GameObject tuli in vihollisenTulet) tuli.SetActive(false);
        enemyText.text = enemyShipCount.ToString();
        TextTop.text = "Vihollisen vuoro";
        enemyScript.NPCTurn();
        ColorAllTiles(0);
        if (playShipCount < 1) GameOver("Hävisit pelin");
    }
    public void EndEnemyTurn()
    {
        for (int i = 0; i < laivat.Length; i++) laivat[i].SetActive(false);
        foreach (GameObject tuli in pelaajanTulet) tuli.SetActive(false);
        foreach (GameObject tuli in vihollisenTulet) tuli.SetActive(true);
        pelaajaText.text = playShipCount.ToString();
        TextTop.text = "Valitse ohjuskohde";
        pelaajanVuoro = true;
        ColorAllTiles(1);
        if (enemyShipCount < 1) GameOver("Voitit pelin");
    }
    private void ColorAllTiles(int colorIndex){
        foreach(TileScript tileScript in allTilesScript){
            tileScript.SwitchColors(colorIndex);
        }
    }    
    void GameOver(string voittaja){ // nappaa nimi nimijakoko menusta, tuo tähän
        TextTop.text="Peli päättyi" + voittaja;
        replayBtn.gameObject.SetActive(true);
        pelaajanVuoro = false;

    }
    void ReplayClicked(){
        SceneManager.LoadScene(SceneManager.GetActiveScene().name);
    }
    void QuitClicked(){
        Application.Quit();
    }
}
