/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplotanque;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Estudiante
 * Jpanel dado que voy a pintar en el Canvas
 * ActionListener: Para poder ejecutar el escenario cada ciertos milisegundos
 */
public class Tablero extends JPanel implements ActionListener{
    private Timer timer ;
    private Tanque tanque;
    
    
    public Tablero(){
        //Lanza un evento de tipo ActionListener cada 25 Milisegundo
        //Se hace referencia a this porque la misma clase (Tablero) procesa el evento
        this.tanque = new Tanque();
        tanque.setX(500);
        tanque.setY(300);
        
    
        this.timer = new Timer(25, this);
        //Registrar evento del Teclado
        setFocusable(true); //Debe estar en modo Focus para que puede detectar el evento
        addKeyListener(new EventosTeclado()); //Inner class que procesa los eventos del teclado
        this.timer.start(); //Inicio con el escenario
    }
        
    //Inner class Que captura los eventos del teclado
     private class EventosTeclado extends KeyAdapter {
        //Cuando se suelta una tecla
         @Override
        public void keyReleased(KeyEvent e) {
         
        }
        //Cuando se presiona una tecla
        @Override
        public void keyPressed(KeyEvent e) {
            
            //Toca validar los limites
           int key = e.getKeyCode();
           
           if (key == KeyEvent.VK_DOWN) {
             tanque.setAngulo(tanque.getAngulo()-1);
           }
           
           if (key == KeyEvent.VK_UP) {
             tanque.setAngulo(tanque.getAngulo()+1);
               
           }
           
           
        }
        
    }
    
    //Metodo donde se pintan los objetos 
     @Override
    public void paintComponent(Graphics g){
      super.paintComponent(g);
     //Validar que este en el rango permitido
     Image imgTanque = loadImage(tanque.getImagenes()[tanque.getAngulo()]);
     g.drawImage(imgTanque, tanque.getX(),tanque.getY(), this);
    }

    //Metodo que se ejecuta cada vez que se lanza un ActionListener
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Repaint");
        repaint();
    }
    
    public Image loadImage(String imageName) {
        ImageIcon ii = new ImageIcon(imageName);
        Image image = ii.getImage();
        return image;
    }
}
