using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class TuliScripti : MonoBehaviour
{
    public GameObject punaTuli;
    public GameObject keltaTuli;
    public GameObject oranssiTuli;
    int count;

    List<Color> tuliVärit = new List<Color> { Color.red, Color.yellow, new Color(1.0f, 0.64f, 0) };

    void FixedUpdate()
    {
        if(count > 30)
        {
            tuliVärit.Add(Color.red);
            int rnd = Random.Range(0, tuliVärit.Count);
            punaTuli.GetComponent<Renderer>().material.SetColor("_Color", tuliVärit[rnd]);
            tuliVärit.RemoveAt(rnd);
            rnd = Random.Range(0, tuliVärit.Count);
            oranssiTuli.GetComponent<Renderer>().material.SetColor("_Color", tuliVärit[rnd]);
            tuliVärit.RemoveAt(rnd);
            keltaTuli.GetComponent<Renderer>().material.SetColor("_Color", tuliVärit[0]);
            tuliVärit.Clear();
            tuliVärit = new List<Color> { Color.red, Color.yellow, new Color(1.0f, 0.64f, 0) };
            count = 0;
        }
        count++;
    }
}