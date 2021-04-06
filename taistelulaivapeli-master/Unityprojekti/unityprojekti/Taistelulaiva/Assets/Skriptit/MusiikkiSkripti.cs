using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class MusiikkiSkripti : MonoBehaviour
{
   private void Awake(){
       GameObject[] musicObj = GameObject.FindGameObjectsWithTag("AudioSource");
           if(musicObj.Length>1){
               Destroy(this.gameObject);
           }
       DontDestroyOnLoad(this.gameObject);
   }
}
