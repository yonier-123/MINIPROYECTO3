package modelo;

import java.util.ArrayList;

public abstract class Personaje {

    //ejemplo de como puede quedar la clase abstracta que le hereda a las hijas heroe y monstruo

    private String nombre;
    private int HP;
    private int MP;
    private int ataque;
    private int defensa;
    private int velocidad;
    private String estado;
    private TipoPersonajes person;
    
    

    public Personaje(String nombre, int HP, int MP,int ataque, int defensa, int velocidad,TipoPersonajes person, String estado) {
        this.nombre = nombre;
        this.HP = HP;
        this.MP = MP;
        this.ataque = ataque;
        this.defensa = defensa;
        this.velocidad = velocidad;
        this.person = person;
        this.estado = estado;
    }

     //Getter de un Enum
    public TipoPersonajes getTipoPersonaje(){

       return person;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getHP() {
        return HP;
    }
    public void setHP(int HP) {
        this.HP = HP;
    }
    public int getMP() {
        return MP;
    }
    public void setMP(int MP) {
        this.MP = MP;
    }
    public int getAtaque() {
        return ataque;
    }
    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }
    public int getDefensa() {
        return defensa;
    }
    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }
    public int getVelocidad() {
        return velocidad;
    }
    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
  
    public boolean recibirDanio(int danio,Personaje jugador, Personaje objetivo,boolean defendiendo){ //se le agrega esto para que se tenga en cuenta la variable de vivo y se puedan manejar las batallas mas adelante
       int auxiliarDano= danio;//Guarda daño antiguo para mostrar en la defensa de cuanto daño se defendio
        if(defendiendo){// se le agrega esto para que si cuente la accion de defenderse y no quede asi pues pailotas
            danio /=2;
            //defendiendo = false; //con esto la idea es que dure un turno la defensa y ya nada mas, no se si funcione pero segun yo si 
        }
        
          RegistroBatalla.RegistrarTextos(objetivo.getNombre() + "Tiene: "+ objetivo.getHP()+ " de HP");
         HP -= danio;//Disminuye HP
        if (HP <= 0){
           // HP = 0;
            RegistroBatalla.RegistrarTextos(objetivo.getNombre() + " Ahora Tiene: "+ objetivo.getHP()+ " de HP");
            RegistroBatalla.RegistrarTextos(objetivo.getNombre()+" Ha Quedado de Baja");
            return false;
        }else if(defendiendo){
           
            RegistroBatalla.RegistrarTextos(objetivo.getNombre() + " Ahora Tiene: "+ objetivo.getHP()+ " de HP");
            RegistroBatalla.RegistrarTextos(objetivo.getNombre()+ " Perdio: "+ danio + " de HP");
            RegistroBatalla.RegistrarTextos("Se defendio "+ danio+ " De haber sido atakado por: "+ auxiliarDano+" de Daño");
            return true;
        }else{
             RegistroBatalla.RegistrarTextos(objetivo.getNombre() + " Ahora Tiene: "+ objetivo.getHP()+ " de HP");
            return  true;
        }

    }

    public abstract void acciones(ArrayList <Heroe> listHeroes, ArrayList <Monstruo> listMonstruos,int posicionHero,int posicionMonster,String nameBoton); // ya que debe haber mas acciones a parte de atacar se cambiara a Acciones
    

}
