package view;

import java.util.concurrent.Semaphore;

import controller.ThreadBanco;

public class Principal {

	public static void main(String[] args) {
		Semaphore semaforo = new Semaphore(2);
		for(int i = 0; i < 20; i++) {
			ThreadBanco t = new ThreadBanco(semaforo, (i+1), (Math.random() * 10000), (Math.random() * 1000), (int)((Math.random() * 2) + 1));
			t.start();
		}
	}

}
