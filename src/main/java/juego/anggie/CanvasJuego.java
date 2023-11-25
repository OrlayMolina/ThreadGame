package juego.anggie;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

public class CanvasJuego extends Canvas implements KeyListener {
    private static final long serialVersionUID = 1l;

    public static int ANCHO = 800;
    public static int ALTO = ANCHO - 300;
    //public static int ESCALA = 3;

    private ArrayList<Personaje> listaPersonajes;
    private ArrayList<HiloPersonaje> hilotes;

    private Image fondo;

    public CanvasJuego(){
        Dimension dimension = new Dimension(ANCHO , ALTO );
        hilotes = new ArrayList<>();
        setPreferredSize(dimension);
        setMaximumSize(dimension);
        setMinimumSize(dimension);

        addKeyListener(this);

        listaPersonajes = new ArrayList<>();
    }

    public synchronized void render(){
        BufferStrategy bs = getBufferStrategy();
        if(bs == null){
            createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.drawImage(fondo, 0, 0, null);

        synchronized (listaPersonajes){
            for(Personaje personaje : listaPersonajes){
                personaje.render(g);
            }
            listaPersonajes.notify();
        }

        g.dispose();
        bs.show();
    }

    public void init(){
        Personaje p1, p2, p3;

        fondo = new ImageIcon("C:/Java proyectos/anggie/src/main/resources/juego/anggie/res/fondo.jpg").getImage();
        p1 = new Personaje("C:/Java proyectos/anggie/src/main/resources/juego/anggie/res/ovni1.png", 100, 350, 1);
        p2 = new Personaje("C:\\Java proyectos\\anggie\\src\\main\\resources\\juego\\anggie\\res\\ovni2.png", 100, 350, 1);
        p3 = new Personaje("C:\\Java proyectos\\anggie\\src\\main\\resources\\juego\\anggie\\res\\ovni3.png", 100, 350, 1);

        listaPersonajes.add(p1);
        listaPersonajes.add(p2);

        HiloPersonaje hiloP1 = new HiloPersonaje(this, p1, 1);
        hilotes.add(hiloP1);
        hiloP1.asignarDireccion(1);

        HiloPersonaje hiloP2 = new HiloPersonaje(this, p2, 1);
        hilotes.add(hiloP2);
        hiloP2.asignarDireccion(2);

        HiloPersonaje hiloP3 = new HiloPersonaje(this, p3, 1);
        hilotes.add(hiloP3);
        hiloP3.asignarDireccion(3);

        requestFocus();

        new Thread(new HiloJuego(this)).start();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        switch (key){
            case KeyEvent.VK_1:
                hilotes.get(0).detener();
                break;
            case KeyEvent.VK_2:
                hilotes.get(1).detener();
                break;
            case KeyEvent.VK_3:

                Personaje p4;
                int x = (int) Math.floor(Math.random() * 500);
                int y = (int) Math.floor(Math.random() * 500);
                int mov = (int) Math.floor(Math.random() * ((3))) + 1;
                int vel = (int) Math.floor(Math.random() * ((9))) + 1;
                if(vel % 2 == 0){
                    p4 = new Personaje("C:/Java proyectos/anggie/src/main/resources/juego/anggie/res/ovni1.png", x, y, mov);
                }else {
                    p4 = new Personaje("C:/Java proyectos/anggie/src/main/resources/juego/anggie/res/ovni2.png", x, y, mov);
                    HiloPersonaje hiloP3 = new HiloPersonaje(this, p4, vel);
                    hilotes.add(hiloP3);
                    synchronized (listaPersonajes){
                        listaPersonajes.add(p4);
                        listaPersonajes.notify();
                    }
                }
                break;
            case KeyEvent.VK_SPACE:
                System.exit(0);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
