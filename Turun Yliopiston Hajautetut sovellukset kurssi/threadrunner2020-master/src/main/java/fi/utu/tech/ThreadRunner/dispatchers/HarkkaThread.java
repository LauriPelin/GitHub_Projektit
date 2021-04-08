package fi.utu.tech.ThreadRunner.dispatchers;
import fi.utu.tech.ThreadRunner.workers.Worker;
import java.util.ArrayList;
// Luodaan HarkkaThread, asetetaan worker ja työLista, tehdään run ja kerrotaan mitä siellä tehdään

public class HarkkaThread extends Thread {
		Worker worker;
		ArrayList<Integer> työLista;
		public HarkkaThread(Worker worker, ArrayList<Integer> työLista) {
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
		
		
		
	//public class harkkaRun implements Runnable {
	//	private final ArrayList<Integer> ilist;
		
	//	public harkkaThreadi() { asetusThreadi(); }
	//	protected void asetusThreadi() {
			
	//	}
	//	protected final int tehtavaMaara;
	//	TehtavaRun(int tehtavaMaara){
	//		this.tehtavaMaara = tehtavaMaara;
	//	}
	//	protected final Worker tyoTyyppi;
	//	tyoTyyppiRun(Worker tyoTyyppi){
	//		this.tyoTyyppi = tyoTyyppi;
	//	}
	//	public void run(){
			

