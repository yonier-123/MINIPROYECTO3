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

    private JTextArea txtRegistro;
    private JButton botonAtk,botonDef,botonSkill,botonVolverJugar;

    public VentanaBatalla(ArrayList<Heroe> heroes, ArrayList<Monstruo> monstruos, ControladorJuego controlador) {
        this.heroes = heroes;
        this.monstruos = monstruos;
        this.controlador = controlador;

        setTitle("Dragon Quest VIII - Batalla");
        setSize(1000, 650);
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
         txtRegistro = new JTextArea(15, 25);
        txtRegistro.setEditable(false);
        txtRegistro.setLineWrap(true);
        txtRegistro.setWrapStyleWord(true);
        txtRegistro.setFont(new Font("Serif", Font.PLAIN, 16));
        txtRegistro.setBackground(new Color(40, 40, 40));
        txtRegistro.setForeground(Color.WHITE);
        JScrollPane scrollMensajes = new JScrollPane(txtRegistro);
        scrollMensajes.setBorder(BorderFactory.createTitledBorder("Mensajes de batalla"));
        panelDerecho.add(scrollMensajes, BorderLayout.CENTER);
        //Registra Correctamante todo lo que sucede en batalla
        controlador.accionRegistrarTextArea(txtRegistro);
        // Botones de acción debajo
        JPanel panelBotones = new JPanel(new GridLayout(4, 1, 10, 10));
        panelBotones.setBackground(new Color(25, 25, 25));
      
        botonAtk = new JButton("Atacar");
        botonAtk.setFont(new Font("SansSerif", Font.BOLD, 16));
        botonAtk.setBackground(new Color(70, 70, 90));
        botonAtk.setForeground(Color.WHITE);
        botonAtk.setFocusPainted(false);
        panelBotones.add(botonAtk);

        botonDef = new JButton("Defender");
        botonDef.setFont(new Font("SansSerif", Font.BOLD, 16));
        botonDef.setBackground(new Color(70, 70, 90));
        botonDef.setForeground(Color.WHITE);
        botonDef.setFocusPainted(false);
        panelBotones.add(botonDef);

        botonSkill = new JButton("Habilidad");
        botonSkill.setFont(new Font("SansSerif", Font.BOLD, 16));
        botonSkill.setBackground(new Color(70, 70, 90));
        botonSkill.setForeground(Color.WHITE);
        botonSkill.setFocusPainted(false);
        panelBotones.add(botonSkill);

         botonVolverJugar = new JButton("Volver a Jugar");
        botonVolverJugar.setFont(new Font("SansSerif", Font.BOLD, 16));
        botonVolverJugar.setBackground(new Color(70, 70, 90));
        botonVolverJugar.setForeground(Color.WHITE);
        botonVolverJugar.setFocusPainted(false);
        panelBotones.add(botonVolverJugar);

        setBotonAcciones(false);//Para Habilitar o Deshabilitar Botones

        panelDerecho.add(panelBotones, BorderLayout.SOUTH);
        add(panelDerecho, BorderLayout.EAST);
        setVisible(true);

         configurarEventos();
         controlador.deactiveMonsterButton();//Los deshabilita inicialmente a los montruos
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

        // --- PANEL CENTRAL DONDE VA EL BOTÓN ---
        JPanel panelBoton = new JPanel(null);  // Layout absoluto
        panelBoton.setPreferredSize(new Dimension(80, 80));
        panelBoton.setOpaque(false);

        JButton boton = new JButton(p.getNombre());
        boton.setBounds(5, 5, 70, 70); // Tamaño y posición
        boton.setOpaque(false);
        boton.setContentAreaFilled(false);
        boton.setBorderPainted(true);
        boton.putClientProperty("personaje", p);//Asignando personaje al boton
        asignarEventoBotonPersonaje(boton,p);
        p.setBoton(boton); //Se Asocia Botones Creados a sus personajes para mas adelante poder controlar esos botones
        panelBoton.add(boton);

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
        p.setBarraHP(barraHP);//Se guarda esa barra en especifico asociada a un personaje
        // Barra MP
        JProgressBar barraMP = new JProgressBar(0, 100);
        barraMP.setValue(Math.min(p.getMP(), 100));
        barraMP.setString("MP: " + p.getMP());
        barraMP.setStringPainted(true);
        p.setBarraMP(barraMP);
        // Barra Estado
        JProgressBar barraEstado = new JProgressBar(0, 100);
        barraEstado.setString("Estado: " + p.getEstado());
        barraEstado.setStringPainted(true);
        p.setBarraEstado(barraEstado);
        //Separacion Pequeña entre Barras
        panelBarras.add(barraHP);
        panelBarras.add(Box.createVerticalStrut(3)); 
        panelBarras.add(barraMP);
        panelBarras.add(Box.createVerticalStrut(3));
        panelBarras.add(barraEstado);
        // Se agregan en el panel principal
        panelPersonaje.add(nombreHeroe, BorderLayout.NORTH);
        panelPersonaje.add(panelBoton,BorderLayout.CENTER);
        panelPersonaje.add(panelBarras, BorderLayout.SOUTH);
        
        panel.add(panelPersonaje);
    }
        return panel;

     }
        //Getters y setters para habilitar y deshabilitar botones
     public void setBotonAcciones(boolean b){
        enableAtacar(b);
        enableDefensa(b);
        enableHabilidad(b);
        enableVolverJugar(b);

     }
     public void enableAtacar(boolean b){ botonAtk.setEnabled(b); }
    public void enableDefensa(boolean b){ botonDef.setEnabled(b); }
    public void enableHabilidad(boolean b){ botonSkill.setEnabled(b); }
    public void enableVolverJugar(boolean b){ botonVolverJugar.setEnabled(b); }

    public JButton getEnableAtacar(boolean b){ return botonAtk; }
    public JButton getEnableDefensa(boolean b){ return botonDef; }
    public JButton getEnableHabilidad(boolean b){ return botonSkill; }
    public JButton getEnableVolverJugar(boolean b){ return botonVolverJugar; }

  private void asignarEventoBotonPersonaje(JButton boton, Personaje p){

     for (ActionListener remove : boton.getActionListeners()) {
        boton.removeActionListener(remove);
    }

        //Se Guarda Personaje dentro del evento
    boton.putClientProperty("personaje", p);

    //Creacion del evento
    boton.addActionListener(e -> {
        Personaje seleccionado = (Personaje) ((JButton) e.getSource())
                .getClientProperty("personaje");

        controlador.personajeSeleccionado(seleccionado);
        });

     }
        //Metodo donde se configuran los eventos de los botones principales de las acciones
    
     private void configurarEventos() {
        botonAtk.addActionListener(e -> ejecutarAccion("atacar"));
        botonDef.addActionListener(e -> ejecutarAccion("defender"));
        botonSkill.addActionListener(e -> ejecutarAccion("habilidad"));
        botonVolverJugar.addActionListener(e -> ejecutarAccion("Volver a Jugar"));
    }
   //Metodo donde se ejecutan los eventos de los botones principales 
    public void ejecutarAccion(String accion){// Ellos se comunican con metodos de controloador

        switch (accion) {
            case "atacar":
                    controlador.atacar();
                break;
            
             case "defender":
                    controlador.defender();
                break;

             case "habilidad":
                    controlador.habilidad();
                break;

             case "Volver a Jugar":
                    controlador.volverJugar();
                    txtRegistro.setText("");//Para Limpiar por completo el JTextArea
               break;

            default:
                break;
        }

    }


    public void actualizarPantalla(ArrayList<Heroe> listHeroes, ArrayList<Monstruo> listMonstruos) {

         // Aqui se Actualiza la barra de los heroes
    for (Heroe h : listHeroes) {

        if (h.getBarraHP() != null) {
            JProgressBar barraHP = h.getBarraHP();
            barraHP.setValue(Math.min(h.getHP(), 100));
            barraHP.setString("HP: " + h.getHP());
        }

        if (h.getBarraMP() != null) {
            JProgressBar barraMP = h.getBarraMP();
            barraMP.setValue(Math.min(h.getMP(), 100));
            barraMP.setString("MP: " + h.getMP());
        }

        if (h.getBarraEstado() != null) {
            JProgressBar barraEstado = h.getBarraEstado();
            barraEstado.setString("Estado: " + h.getEstado());
        }
    }

    // aqui se Actualiza la barra de los monstruos
    for (Monstruo m : listMonstruos) {

        if (m.getBarraHP() != null) {
            JProgressBar barraHP = m.getBarraHP();
            barraHP.setValue(Math.min(m.getHP(), 100));
            barraHP.setString("HP: " + m.getHP());
        }

        if (m.getBarraMP() != null) {
            JProgressBar barraMP = m.getBarraMP();
            barraMP.setValue(Math.min(m.getMP(), 100));
            barraMP.setString("MP: " + m.getMP());
        }

        if (m.getBarraEstado() != null) {
            JProgressBar barraEstado = m.getBarraEstado();
            barraEstado.setString("Estado: " + m.getEstado());
        }   

    }

    //  refrescar la interfaz para actualizarse correctamente
    revalidate();
    repaint();
       
 }

}

