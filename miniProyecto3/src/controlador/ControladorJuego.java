package controlador;
/*Funciona casi igual que el app en el anterior mini proyecto, y lo hace asi ya que asi conecta las clases normales
 del modelo y la vista
 */
import java.util.ArrayList;
import modelo.*;
import vista.VentanaBatalla;

public class ControladorJuego {

    private ArrayList<Heroe> heroes;
    private ArrayList<Monstruo> monstruos;
    private MusicaFondo musica;
    private VentanaBatalla ventana;
    private Batalla batalla;

    public ControladorJuego() {
        heroes = new ArrayList<>();
        monstruos = new ArrayList<>();
        musica = new MusicaFondo();
        batalla = new Batalla();
    }

    public void iniciarJuego() {
        musica.reproducirMusica("src/Megalovania.wav");
        crearPersonajes();
        crearVista();
    }

    private void crearPersonajes() {
        Heroe heroe1 = new Heroe("El Héroe", 40, 9, 22, 18, 12, TipoPersonajes.GUERRERO_BALANCEADO, "Vivo");
        Heroe heroe2 = new Heroe("Yangus", 50, 5, 28, 12, 8, TipoPersonajes.TANQUE, "Vivo");
        Heroe heroe3 = new Heroe("Jessica", 30, 20, 15, 9, 14, TipoPersonajes.MAGO_OFENSIVO, "Vivo");
        Heroe heroe4 = new Heroe("Angelo", 32, 15, 19, 14, 11, TipoPersonajes.SANADOR_APOYO, "Vivo");

        Monstruo monstruo1 = new Monstruo("SlimeBoy", 25, 8, 7, 3, 10, TipoPersonajes.ENEMIGO_DEBIL, "Vivo");
        Monstruo monstruo2 = new Monstruo("Dragon", 120, 24, 22, 12, 9, TipoPersonajes.JEFE_PODEROSO, "Vivo");
        Monstruo monstruo3 = new Monstruo("Esqueleto", 35, 9, 14, 8, 13, TipoPersonajes.LUCHADOR_EVIL, "Vivo");
        Monstruo monstruo4 = new Monstruo("Hechicero", 32, 15, 8, 6, 13, TipoPersonajes.MAGO_EVIL, "Vivo");

        heroe1.agregarHabilidad(new Habilidad("Golpe de espada", 3, 0, TipoHabilidad.DANIO_MAGICO));
        heroe2.agregarHabilidad(new Habilidad("Golpe de Hacha", 5, 0, TipoHabilidad.DANIO_MAGICO));
        heroe3.agregarHabilidad(new Habilidad("Bola de fuego", 4, 0, TipoHabilidad.DANIO_MAGICO));
        heroe4.agregarHabilidad(new Habilidad("Curación", 3, 12, TipoHabilidad.CURACION));

        monstruo1.agregarHabilidad(new Habilidad("Saltar Encima", 2, 0, TipoHabilidad.DANIO_MAGICO));
        monstruo2.agregarHabilidad(new Habilidad("Aliento de Fuego", 6, 0, TipoHabilidad.DANIO_MAGICO));
        monstruo3.agregarHabilidad(new Habilidad("Golpe de Espada", 3, 0, TipoHabilidad.DANIO_MAGICO));
        monstruo4.agregarHabilidad(new Habilidad("Rayo de Fuego", 5, 0, TipoHabilidad.DANIO_MAGICO));

        heroes.add(heroe1);
        heroes.add(heroe2);
        heroes.add(heroe3);
        heroes.add(heroe4);

        monstruos.add(monstruo1);
        monstruos.add(monstruo2);
        monstruos.add(monstruo3);
        monstruos.add(monstruo4);
    }

    private void crearVista() {
        ventana = new VentanaBatalla(heroes, monstruos, this);
        ventana.setVisible(true);
    }

    // aqui van todos los metodos que puede usar la vista y pues al rato se va acomodando bien a como lo teniamos antes
    //esto fue solo una prueba para ver si funcionaba o no
    public void atacar(int indiceHeroe, int indiceMonstruo) {
        batalla.EmpezarBatalla(heroes, monstruos, indiceHeroe, indiceMonstruo, "Atacar");
        ventana.actualizarPantalla();
    }

    public void detenerMusica() {
        musica.detenerMusica();
    }
}
