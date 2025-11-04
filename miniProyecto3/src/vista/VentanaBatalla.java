package vista;
/*aqui lo que se hace de todo lo que viene es una ventana normal, pero el que va a actuar full es el
 controlador ya que basicamente hace que las clases del modelo se conecten con esto, por el momento
 hare un commit de todo esto, no va a funcionar, o al menos aun no ya que necesito que reconozca a controlador
 como parte del proyecto pero por alguna razon no lo hace, la función de la vista no tiene ningun misterio
 lo unico que cambia es basicamente que usa a cotrolador para facilitar la comunicacion entre el resto
 de clases.
 */
import controlador.ControladorJuego;
import modelo.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class VentanaBatalla extends JFrame {

    private ArrayList<Heroe> heroes;
    private ArrayList<Monstruo> monstruos;
    private ControladorJuego controlador;

    private JTextArea areaTexto;
    private JButton botonAtacar;

    public VentanaBatalla(ArrayList<Heroe> heroes, ArrayList<Monstruo> monstruos, ControladorJuego controlador) {
        this.heroes = heroes;
        this.monstruos = monstruos;
        this.controlador = controlador;

        setTitle("Batalla RPG");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        areaTexto = new JTextArea();
        areaTexto.setEditable(false);
        RegistroBatalla.setTextArea(areaTexto);
        add(new JScrollPane(areaTexto), BorderLayout.CENTER);

        botonAtacar = new JButton("Atacar (Héroe 1 vs Monstruo 1)");
        botonAtacar.addActionListener(e -> controlador.atacar(0, 0));
        add(botonAtacar, BorderLayout.SOUTH);

        actualizarPantalla();
    }

    public void actualizarPantalla() {
        StringBuilder info = new StringBuilder();
        info.append("=== HÉROES ===\n");
        for (Heroe h : heroes) {
            info.append(h.getNombre() + " HP: " + h.getHP() + " Estado: " + h.getEstado() + "\n");
        }

        info.append("\n=== MONSTRUOS ===\n");
        for (Monstruo m : monstruos) {
            info.append(m.getNombre() + " HP: " + m.getHP() + " Estado: " + m.getEstado() + "\n");
        }

        areaTexto.setText(info.toString());
    }
}
