using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class TileScript : MonoBehaviour
{
    PeliManageri PeliManageri;
    Ray ray;
    RaycastHit hit;
    private bool missileHit = false;
    Color32[] hitColor = new Color32[2];

    void Start()
    {
        PeliManageri = GameObject.Find("PeliManageri").GetComponent<PeliManageri>();
        hitColor[0] = gameObject.GetComponent<MeshRenderer>().material.color;
        hitColor[1] = gameObject.GetComponent<MeshRenderer>().material.color;
    }

    // Update is called once per frame
    void Update()
    {
        ray = Camera.main.ScreenPointToRay(Input.mousePosition);
        if(Physics.Raycast(ray, out hit)){
            if(Input.GetMouseButtonDown(0) && hit.collider.gameObject.name == gameObject.name){
                if(missileHit == false){
                    PeliManageri.TileClicked(hit.collider.gameObject);
                }
            }
        }
    }
    private void OnCollisionEnter(Collision collision){
        if(collision.gameObject.CompareTag("Ohjus")){
            missileHit=true;
        }
        else if(collision.gameObject.CompareTag("VihollisenOhjus")){
            hitColor[0] = new Color32(38, 67, 77, 245);
            GetComponent<Renderer>().material.color = hitColor[0];
        }
    }
    public void SetTileColor(int index, Color32 color){
        hitColor[index] = color;
    }
    public void SwitchColors(int colorIndex){
        GetComponent<Renderer>().material.color = hitColor[colorIndex];
    }
}