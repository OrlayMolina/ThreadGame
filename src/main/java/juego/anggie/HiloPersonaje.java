package juego.anggie;

public class HiloPersonaje extends Thread{

    private Personaje p;
    private boolean activo;
    private long espera;
    private CanvasJuego miCanvas;
    //private boolean not = true;

    private int DELTA_X = 1;
    private int DELTA_Y = 1;
    private int direccion = 0;
    private int unidesplazamiento = 20;

    public HiloPersonaje(CanvasJuego c, Personaje p, long espera){
        this.p = p;
        activo = true;
        this.espera = espera;
        miCanvas = c;

        start();
    }

    @Override
    public void run(){
        while(activo){
            switch(direccion){
                case 1:
                    if(p.getMov() == 1){
                        if(p.getX() > miCanvas.getWidth()){
                            p.setX(0);
                            p.setY(p.getY() + DELTA_Y + unidesplazamiento);

                            if(p.getY() > miCanvas.getHeight()){
                                p.setY(0);
                            }
                        }else{
                            p.setX(p.getX() + DELTA_X);
                        }
                        try {
                            sleep(espera);
                        } catch(InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 2:
                    if(p.getMov() == 1){
                        if(p.getY() > miCanvas.getHeight()){
                            p.setY(0);
                            p.setX(p.getX() + DELTA_X + unidesplazamiento);

                            if(p.getX() > miCanvas.getWidth()){
                                p.setX(0);
                            }
                        }else {
                            p.setY(p.getY() + DELTA_Y);
                        }
                        try {
                            sleep(espera);
                        } catch(InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 3:
                    int x = 0;
                    int y = 0;
                    if(p.getMov() == 1){
                        if(p.getY() > miCanvas.getHeight()){
                            p.setY(0);

                            p.setY(p.getY() + DELTA_Y + unidesplazamiento);

                            if(p.getX() > miCanvas.getWidth()){
                                p.setX(0);

                                p.setX(p.getX() + DELTA_X +unidesplazamiento);

                            }
                        }else if(p.getX() > miCanvas.getWidth()){
                            p.setX(0);
                            p.setX(p.getX() + DELTA_X + unidesplazamiento);

                            if(p.getY() > miCanvas.getHeight()){
                                p.setY(0);

                                p.setY(p.getY() + DELTA_Y + unidesplazamiento);

                            }
                        }else {
                            p.setY(p.getY() + DELTA_Y);
                            p.setX(p.getX() + DELTA_X);
                        }
                        try {
                            sleep(espera);
                        } catch(InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                default:
                    break;
            }
        }
    }

    public void asignarDireccion(int direccion){
        this.direccion = direccion;
    }

    public void detener(){
        activo = false;
    }
}
