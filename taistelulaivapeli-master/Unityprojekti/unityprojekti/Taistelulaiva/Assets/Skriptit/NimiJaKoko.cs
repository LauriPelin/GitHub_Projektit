using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;
using UnityEngine.UI;

public class NimiJaKoko : MonoBehaviour
// * Ennemmin näin jatkossa, vrt. MainMenu
// Asetukset ja moni napeista ei tee mitään, johtuu yksinkertaisesti ajanpuutteesta
{
    [Header("HUD")]
    public Button AloitaPeliButton;
    public Button IsoPeliButton;
    public Button PikkuPeliButton;
    public Button QuitButton;
    public Button VahanAluksiaButton;
    public Button PaljonAluksiaButton;
    public Button CancelButton;
    public Button ConfirmButton;
    public GameObject InputField;
    void Start(){
        AloitaPeliButton.onClick.AddListener(()=> AloitaPeli());
        QuitButton.onClick.AddListener(()=> QuitPeli());

    }
    public void AloitaPeli(){
      SceneManager.LoadScene(1);
    }
    public void QuitPeli(){
      SceneManager.LoadScene(0);
  }
}
