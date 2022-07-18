package edu.curso.java.demo.Service;

public class PersonajeException extends Exception {
	  

		public PersonajeException() {
			super();
		}

		public PersonajeException(String mensaje) {
			super(mensaje);
		}

		public PersonajeException(String mensaje, Throwable throwable) {
			super(mensaje, throwable);
		}
	}

