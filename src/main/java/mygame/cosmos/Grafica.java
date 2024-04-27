package mygame.cosmos;
//используем чужиеклассы 
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import  mygame.cosmos. Klava;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Grafica  extends JPanel implements ActionListener , KeyListener{
	
	
	    private int playerX = 175;  // Начальное положение игрока по горизонтали
	    private int playerY = 480;  // Начальное положение игрока по вертикали
	    private int playerSpeed = 15;  // Скорость движения игрока
	    private ArrayList<Integer> enemyX = new ArrayList<>(); // X-координаты врагов
	    private ArrayList<Integer> enemyY = new ArrayList<>();  // Y-координаты врагов
	    private int enemySpeed = 20;  // Скорость движения врагов
	    private Timer timer;  // Таймер для обновления экрана
	    private boolean gameOver = false;  // Флаг окончания игры
	    private int score = 0;  // Счет игрока
		
	    
	    
	    public Grafica() {
	    	//это метод компонента JPanel в который мы вставляем класс который будет отрабатывать при нажатии 
	    	//добавили эту графику на события 
	    	//для обработки событий клавиатуры необходимо реализовать специальный интерфейс, 
	    	//методы интерфейса который надо обезательно реалезовать 
	    	//метод уведомление нажатии на кнопку
	    	//Чтобы добавить слушателя KeyListener к интересуемому компоненту для прослушивания событий клавиатуры, используется метод addKeyListener.
	    	//Метод addKeyListener принимает экземпляр класса, который реализует интерфейс KeyListener. В данном случае это класс KeyAdapter.
	    	
	     //   addKeyListener(this);//слушателя KeyListener к интересуемому компоненту для прослушивания событий клавиатуры, используется метод addKeyListener.В качестве параметра методу передается ссылка на слушателя.
	        //метод супер классе JPanel.
	        // этот метод находится в классе компонентов, супер классе JPanel. 
	        setFocusable(true);//фокус на кнопках Фокус — это некий указатель, который говорит о том, какой сейчас компонент активен и может реагировать на клавиатуру. Фокус возможно переключать, чтобы добраться до требуемого компонента.
	        setFocusTraversalKeysEnabled(false);//если кнопка фокусе то нельзя использовать для перенмещения фокуса другие кнопки
	        timer = new Timer(100, this);  // Тут создаем таймер таймер для начала процесса анимации
	        timer.start();  // В этой строчке его запускаем
	        
	    }
	    //рисование в рамке обьектов 
	  //метод супер класса  все что связано с рисованием 
	    public void paintComponent(Graphics g) {//вызов конструктора класса  
	       super.paintComponent(g);//и передача его супер классу 
	        //вызвать метод по этой ссылке // вызываем метод родительского класса
	        g.setColor(Color.BLACK); // Заливаем фон черным цветом
	        g.fillRect(0, 0, 400, 600);
	        g.setColor(Color.WHITE); // Белый цвет для фигуры игрока квадрата 
	        g.fillRect(playerX, playerY, 50, 50);  // Рисуем объект игрока и его положение
	        
	        //игрок это овал красного цвета размер иколичество врагов обьектов
	             for (int i = 0; i < enemyX.size(); i++) {
	             //счетчик движение в низ
	           g.setColor(Color.RED); // Используем красный цвет врарага
	            g.fillOval(enemyX.get(i), enemyY.get(i), 20, 20);//рисуем овал врага
	        }
	        
	       // Статический метод в Java принадлежит классу, а не объектам (и экземплярам) класса. Поэтому его вызывают без создания экземпляра.
	        //шрифты 
	        g.setColor(Color.WHITE);//метод.цвет любого обьекта потом метод.его размер 
	        g.setFont(new Font("Arial", Font.PLAIN, 20));
	        g.drawString("Счет: " + score, 10, 30);  // Выводим счет игрока на экран
	        
	        
	        if (gameOver) {//проиграл 
	        	//текст
	            g.setFont(new Font("Arial", Font.PLAIN, 40));
	            g.drawString("Конец игры", 120, 300);  // Выводим надпись "Конец игры" при окончании игры
	            timer.stop();  // Останавливаем таймер
	        }
	    }
	    //метод интерфейса Обработчики события
	    @Override
	    public void actionPerformed(ActionEvent e) {
	    //если   не
	    	if (!gameOver) {
	    		//то сделать стетчит проверяя  размер листа 
	            for (int i = 0; i < enemyX.size(); i++) {
	            	// Двигаем врагов вниз по экрану
	            	//координаты врагов
	                enemyY.set(i, enemyY.get(i) + enemySpeed);  // Двигаем врагов вниз по экрану
	                //если 
	               // объекты должны спускаться вниз и удаляться при пересечении нижней линии окна.
	                if (enemyY.get(i) >= 600) {
	                	//обнулить значение если i больше или равно 
	                    enemyX.remove(i);
	                    enemyY.remove(i);
	                    score++;  // Увеличиваем счет при уничтожении врага
	                }
	            }
	            
	            repaint(); // Перерисовываем экран
	            
	            
	            if (enemyX.isEmpty()) {
	            	
	            //вызов метода 'этого класса 
	                spawnEnemy();  // Создаем нового врага, если текущих нет на экране
	            }
                //метод  этого класса 
	            checkCollision();  // Проверяем коллизию игрока с врагами
	        }
	    }
	    
	    //определения метода 
	    public void spawnEnemy() {
	        Random rand = new Random();
	        int numEnemies = rand.nextInt(5) + 1; // Генерируем от 1 до 5 врагов за раз
	        for (int i = 0; i < numEnemies; i++) {
	            int x = rand.nextInt(350); // Генерируем случайную X-координату для врага
	            int y = 0;
	            //методы нашего врага 
	            enemyX.add(x);
	            enemyY.add(y); // Добавляем врага в списки координат
	        }
	    }
	    public void checkCollision() {
	        Rectangle playerBounds = new Rectangle(playerX, playerY, 50, 50);  // Границы игрока
	        for (int i = 0; i < enemyX.size(); i++) {
	            Rectangle enemyBounds = new Rectangle(enemyX.get(i), enemyY.get(i), 20, 20);  // Границы врага
	            if (playerBounds.intersects(enemyBounds)) {
	                gameOver = true;  // Если произошло столкновение, игра заканчивается
	                break;
	            }
	        }
	    }
	 //интерфейс 
		
		@Override
		public void keyReleased(KeyEvent e) {}
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
	
}


