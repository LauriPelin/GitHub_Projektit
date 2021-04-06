using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Ohjus : MonoBehaviour
{
    private PeliManageri PeliManageri;
    // Start is called before the first frame update
    void Start()
    {
        PeliManageri = GameObject.Find("PeliManageri").GetComponent<PeliManageri>();
        
    }
    private void OnCollisionEnter(Collision collision){
        PeliManageri.CheckHit(collision.gameObject);
        Destroy(gameObject);
    }
}
