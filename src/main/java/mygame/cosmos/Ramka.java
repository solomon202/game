package mygame.cosmos;
/*получили пакет класса рамка
 * 
 */
import javax.swing.JButton;
import javax.swing.JFrame;

public class Ramka {
//сылачная не создает обьект это тип который хранит адрес сылки .
	  private JFrame jframe;
	     //получить  сылку на обьект Объекты как параметры методов
		public Ramka(Grafica grafica) {
		//создать обьект окно с рамкой  и у него есть методы вызвали методы класса 
			jframe = new JFrame("Cosmos");
			//метод размер рамки 
			jframe.setSize(400,600);		
			//закрыть открыть 
			jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			//вставляем в рамку наш обьект игровая панель с его гафикой с его всеми данными 
		 	jframe.add(grafica);
			//расположение по центру 
			jframe.setLocationRelativeTo(null);
			//сделать видимым рамку 
			jframe.setVisible(true);
			
		}
}
