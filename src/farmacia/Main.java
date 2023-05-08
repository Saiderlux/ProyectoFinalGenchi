/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package farmacia;

import farmacia.Inicio_De_Sesion.AccionesSesion;
import java.io.*;
import java.text.ParseException;

public class Main {

    public static void main(String[] args) throws IOException, ParseException {
        MenuOpciones opciones = new MenuOpciones();
        AccionesSesion InicioSesion = new AccionesSesion();
        InicioSesion.Acciones();
        opciones.MenuGlobal();
    }
}
