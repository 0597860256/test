



	import java.awt.BorderLayout;
	import java.awt.Color;

	import javax.swing.JFrame;

	public class ClassMain {
		public static void main(String args[]){
			Graph pan = new Graph();
			pan.setBackground(Color.white);
			
			JFrame fr = new JFrame();
			fr.setVisible(true);
			fr.setSize(500, 500);
			fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			fr.add(pan);
			
			fr.setTitle("Graph Editor");
			fr.add(Graph.PanelForButtons, BorderLayout.SOUTH);
		}
	}

