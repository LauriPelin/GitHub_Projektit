package fi.utu.tech.ThreadRunner.dispatchers;

import java.util.ArrayList;

import fi.utu.tech.ThreadRunner.workers.Worker;

// Luodaan HarkkaDynaThread, eli 2) osion säie-luokka
// Runko saadaan helposti HarkkaThreadista
public class HarkkaDynaThread extends Thread {
	Worker worker;
	ArrayList<Integer> työLista;
	public HarkkaDynaThread(Worker worker, ArrayList<Integer> työLista) {
		this.worker = worker;
		this.työLista = työLista;
	}
	public void run() {
		try {
			for (int time:työLista) { // toteutus kierrätetty WithoutThreadsDispatcher luokassa esiintyvästä, muutamalla muutoksella
				worker.count(time);
			}
		}
		// Rungosta tuttu exception handlaaja
		catch (Exception ex) {
			ex.printStackTrace();
	}
}
}
