package edu.curso.java.demo.Service;

public class PeliculaSerieException extends Exception {
	public PeliculaSerieException() {
		super();
	}

	public PeliculaSerieException(String mensaje) {
		super(mensaje);
	}

	public PeliculaSerieException(String mensaje, Throwable throwable) {
		super(mensaje, throwable);
	}
}
