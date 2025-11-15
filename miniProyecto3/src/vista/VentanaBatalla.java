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
    private JPanel panelHeroes, panelMonstruos;

    private JTextArea areaTexto;
    private JButton botonAtacar;

    public VentanaBatalla(ArrayList<Heroe> heroes, ArrayList<Monstruo> monstruos, ControladorJuego controlador) {
        this.heroes = heroes;
        this.monstruos = monstruos;
        this.controlador = controlador;

        setTitle("Dragon Quest VIII - Batalla");
        setSize(1000, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        //  Panel Superior: Título 
        JLabel titulo = new JLabel("...Ha Comenzado La Legendaria Batalla en Dragon Quest...", SwingConstants.CENTER);
        titulo.setFont(new Font("Serif", Font.BOLD, 28));
        titulo.setForeground(Color.WHITE);
        titulo.setOpaque(true);
        titulo.setBackground(new Color(20, 20, 70));
        add(titulo, BorderLayout.NORTH);

         //  Panel Central: Héroes vs Monstruos
        JPanel panelCentral = new JPanel(new GridLayout(2, 4, 15, 15));
        panelCentral.setBackground(new Color(30, 30, 50));

         panelHeroes = crearPanelPersonajes(heroes, "Héroes");
         panelMonstruos = crearPanelPersonajes(monstruos, "Monstruos");

          panelCentral.add(panelHeroes);//Se añaden tanto los heroes como los monstruos al panel central
          panelCentral.add(panelMonstruos);
        add(panelCentral, BorderLayout.CENTER);


         //  Panel Derecho para Mensajes y Botones 
        JPanel panelDerecho = new JPanel(new BorderLayout());
        panelDerecho.setBackground(new Color(25, 25, 25));
        panelDerecho.setPreferredSize(new Dimension(300, 0));

        // Cuadro de texto de mensajes
        JTextArea txtMensajes = new JTextArea(15, 25);
        txtMensajes.setEditable(false);
        txtMensajes.setLineWrap(true);
        txtMensajes.setWrapStyleWord(true);
        txtMensajes.setFont(new Font("Serif", Font.PLAIN, 16));
        txtMensajes.setBackground(new Color(40, 40, 40));
        txtMensajes.setForeground(Color.WHITE);
        JScrollPane scrollMensajes = new JScrollPane(txtMensajes);
        scrollMensajes.setBorder(BorderFactory.createTitledBorder("Mensajes de batalla"));
        panelDerecho.add(scrollMensajes, BorderLayout.CENTER);

        // Botones de acción debajo
        JPanel panelBotones = new JPanel(new GridLayout(4, 1, 10, 10));
        panelBotones.setBackground(new Color(25, 25, 25));
        String[] botones = {"Atacar", "Defender", "Hechizo", "Cerrar"};//Array de los nombre de los botones para crear los botones
        for (String texto : botones) {  //Creacion de los botones mediante un ciclo y usando el array con los nombre
            JButton boton = new JButton(texto);
            boton.setFont(new Font("SansSerif", Font.BOLD, 16));
            boton.setBackground(new Color(70, 70, 90));
            boton.setForeground(Color.WHITE);
            boton.setFocusPainted(false);
            panelBotones.add(boton);
        }
        panelDerecho.add(panelBotones, BorderLayout.SOUTH);
        add(panelDerecho, BorderLayout.EAST);
        setVisible(true);


       // actualizarPantalla();
    }


     private JPanel crearPanelPersonajes(ArrayList<? extends Personaje> lista, String titulo) {
         JPanel panel = new JPanel(new GridLayout(1, 4, 15, 15));
         panel.setBorder(BorderFactory.createTitledBorder(titulo));
         panel.setBackground(new Color(30, 30, 50));

         for (Personaje p : lista) {
            
            JPanel panelPersonaje = new JPanel(new BorderLayout());
        // Fondo según personaje o monstruo
        if (p.getNombre().equals("SlimeBoy") || p.getNombre().equals("Dragon") ||
            p.getNombre().equals("Esqueleto") || p.getNombre().equals("Hechicero")) {
            panelPersonaje.setBackground(new Color(100, 40, 40));
        } else {
            panelPersonaje.setBackground(new Color(40, 60, 100));
        }

        // Nombre de cada Personaje
        JLabel nombreHeroe = new JLabel(p.getNombre(), SwingConstants.CENTER);
        nombreHeroe.setForeground(Color.WHITE);
         //Para pner un JProgress encima de otro
        JPanel panelBarras = new JPanel();
        panelBarras.setLayout(new BoxLayout(panelBarras, BoxLayout.Y_AXIS));
        panelBarras.setOpaque(false); // fondo transparente para ver color del panel
        // Barra HP
        JProgressBar barraHP = new JProgressBar(0, 100);
        barraHP.setValue(Math.min(p.getHP(), 100));
        barraHP.setString("HP: " + p.getHP());
        barraHP.setStringPainted(true);
        // Barra MP
        JProgressBar barraMP = new JProgressBar(0, 100);
        barraMP.setValue(Math.min(p.getMP(), 100));
        barraMP.setString("MP: " + p.getMP());
        barraMP.setStringPainted(true);
        // Barra Estado
        JProgressBar barraEstado = new JProgressBar(0, 100);
        barraEstado.setString("Estado: " + p.getEstado());
        barraEstado.setStringPainted(true);
        //Separacion Pequeña entre Barras
        panelBarras.add(barraHP);
        panelBarras.add(Box.createVerticalStrut(3)); 
        panelBarras.add(barraMP);
        panelBarras.add(Box.createVerticalStrut(3));
        panelBarras.add(barraEstado);
        // Se agregan en el panel principal
        panelPersonaje.add(nombreHeroe, BorderLayout.NORTH);
        panelPersonaje.add(panelBarras, BorderLayout.SOUTH);

        panel.add(panelPersonaje);
    }
        return panel;

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

/*

JPanel item = new JPanel(new GridLayout(3, 1));
            item.setBorder(BorderFactory.createEtchedBorder());
            item.add(new JLabel("Nombre: " + p.getNombre()));

            JProgressBar barraHP = new JProgressBar(0, 100);
            barraHP.setValue(Math.min(p.getHP(), 100));
            barraHP.setString("HP: " + p.getHP());
            barraHP.setStringPainted(true);
            item.add(barraHP);


JProgressBar barraMP = new JProgressBar(0, 100);
            barraMP.setValue(Math.min(p.getMP(), 100));
            barraMP.setString("MP: " + p.getMP());
            barraMP.setStringPainted(true);
            item.add(barraMP);

            JProgressBar barraEstado = new JProgressBar(0, 100);
            barraEstado.setString("Estado: " + p.getEstado());
            barraEstado.setStringPainted(true);
            item.add(barraEstado);*/




/*
        areaTexto = new JTextArea();
        areaTexto.setEditable(false);
        RegistroBatalla.setTextArea(areaTexto);
        add(new JScrollPane(areaTexto), BorderLayout.CENTER);

        botonAtacar = new JButton("Atacar (Héroe 1 vs Monstruo 1)");
        botonAtacar.addActionListener(e -> controlador.atacar(0, 0));
        add(botonAtacar, BorderLayout.SOUTH);
 */