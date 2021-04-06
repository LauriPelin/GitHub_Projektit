using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class VihollisenOhjus : MonoBehaviour
{
    PeliManageri PeliManageri;
    Vihollinen enemyScript;
    public Vector3 targetTileLocation;
    private int targetTile = -1;
    // Start is called before the first frame update
    void Start()
    {
        PeliManageri = GameObject.Find("PeliManageri").GetComponent<PeliManageri>();
        enemyScript = GameObject.Find("Vihollinen").GetComponent<Vihollinen>();
    }
    private void OnCollisionEnter(Collision collision){
        if(collision.gameObject.CompareTag("Ship")){
            PeliManageri.EnemyHitPlayer(targetTileLocation, targetTile, collision.gameObject);
        }
        else{
            enemyScript.PauseAndEnd(targetTile);

        }
        Destroy(gameObject);
    }
    public void SetTarget(int target){
        targetTile = target;
    }


}
