using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class MainMenu : MonoBehaviour
// * Erittäin laiskasti toteutettu
// Kommenttini eivät ole ehkä parhainta tasoa, mutta eivät kuitenkaan
// https://www.youtube.com/watch?v=k238XpMMn38 videon tasolla, mutta ehkä jokin päivä
{
  public void PlayButton(){
      SceneManager.LoadScene(2);
  }
  public void SettingsButton(){
      SceneManager.LoadScene(3);
  }
  public void QuitButton(){
      Application.Quit();
  }
}
