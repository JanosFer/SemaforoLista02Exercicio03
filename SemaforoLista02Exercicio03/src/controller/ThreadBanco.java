package controller;

import java.util.concurrent.Semaphore;

public class ThreadBanco extends Thread{
	private Semaphore saque;
	private Semaphore deposito;
	private int codConta;
	private double saldo;
	private double valor;
	private int tipo;
	
	public ThreadBanco(Semaphore semaforo, int codConta, double saldo, double valor, int tipo) {
		saque = semaforo;
		deposito = semaforo;
		this.codConta = codConta;
		this.saldo = saldo;
		this.valor = valor;
		this.tipo = tipo;
	}
	
	public void run() {
		switch(tipo) {
			case 1:
				try {
					saque.acquire();
					saque();
				} catch (InterruptedException e) {
					System.err.println(e.getMessage());
				} finally {
					saque.release();
				}
			break;
			case 2:
				try {
					deposito.acquire();
					deposito();
				} catch (InterruptedException e) {
					System.err.println(e.getMessage());
				} finally {
					deposito.release();
				}
			break;	
		}
	}
	
	private void saque() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.err.println(e.getMessage());
		}
		
		if(saldo >= valor) {
			saldo -= valor;
			System.out.println("O saldo atual da conta de código " + codConta + " após o saque é de R$" + saldo + ".");
		}else {
			System.err.println("O saldo atual da conta de código " + codConta + " não é suficiente para realiar o saque no valor de R$" + saldo + ".");
		}
	}
	
	private void deposito() {
		saldo += valor; 
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.err.println(e.getMessage());
		}
		
		System.out.println("O saldo atual da conta de código " + codConta + " após o depósito é de R$" + saldo + ".");
	}
}
