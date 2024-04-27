package mygame.cosmos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import mygame.cosmos.Grafica;

public class Klava implements ActionListener, KeyListener{
 
	public Grafica grafica;
	private boolean gameOver;
	private int playerX;
	private int playerSpeed;
	
	
     public	Klava() {
	     
      }
   
   @Override
   public void keyTyped(KeyEvent e) {}
   @Override
   //вызывается, когда нажата клавиша клавиатуры
   public void keyPressed(KeyEvent e) {
       int key = e.getKeyCode();
       if (!gameOver) {
           if (key == KeyEvent.VK_LEFT && playerX > 0) {
               playerX -= playerSpeed;  // Перемещаем игрока влево
           }
           if (key == KeyEvent.VK_RIGHT && playerX < 350) {
               playerX += playerSpeed;  // Перемещаем игрока вправо
           }
       }
   }

@Override
public void keyReleased(KeyEvent arg0) {
	// TODO Auto-generated method stub
	
}


@Override
public void actionPerformed(ActionEvent arg0) {
	// TODO Auto-generated method stub
	
}


   
}


    

