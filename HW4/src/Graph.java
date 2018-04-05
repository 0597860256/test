
	import javax.swing.*;
	import javax.swing.event.*;
	import java.awt.*;
	import java.awt.event.*;
	import java.util.*;
	
	public class Graph extends JPanel{
		
			private String InsideTheRectangle;
			private String FirstNodeString, SecondNodeString;
			private line LAMA, ClassLineToGet;
			boolean flagInYellow = false;
			private Vector V, VectorOfNumbers, VectorOfLinks, VectorOfCopying;
			private String NamesOfButtons[]={"Add New Node", "Add New Link", "Delete Node"};
			private JButton ArrayOfButtons[];
			public static JPanel PanelForButtons;
			private int x, KeepX1, KeepY1;
			private String DeletedNode;
			private int FirstInteger, SecondInteger, indexOfNode1, indexOfNode2;
			boolean Clicked=false, dontAdd=true;
	
			private int counter=0;
			
			private int XLama1, YLama1;
			int XReq, YReq;
			boolean Drag=false;
			private Rectangle RectangleInPaint, RectCompare, Require, DraggedRectangle;
			private Rectangle CurrentRectangle;
			private Rectangle NewRectangle;		boolean AddNode =false;
			boolean RightClick=false;
			boolean AddLink = false;
		
		
			public void paintComponent(Graphics g){
				
				super.paintComponent(g);
				
				for(int m=0; m<VectorOfLinks.size();m++){
					ClassLineToGet=(line)VectorOfLinks.get(m);
					g.setColor(Color.BLACK);
					if(!Drag)
						g.drawLine(ClassLineToGet.x1+22, ClassLineToGet.y1+22, ClassLineToGet.x2+22, ClassLineToGet.y2+22);
					else
					{
						if(m==indexOfNode1){
							g.drawLine(XLama1+22, YLama1+22, ClassLineToGet.x2+22, ClassLineToGet.y2+22);
						}else if(m==indexOfNode2){
							g.drawLine(ClassLineToGet.x1+22, ClassLineToGet.y1+22, XLama1+22, YLama1+22);
						}
					}
				}//for
				
				if(Clicked && AddNode){
					g.setColor(Color.WHITE);
					g.fillRect(CurrentRectangle.x, CurrentRectangle.y, CurrentRectangle.width, CurrentRectangle.height);
					
					g.setColor(Color.RED);
					g.drawRect(CurrentRectangle.x, CurrentRectangle.y, CurrentRectangle.width, CurrentRectangle.height);
					
				}
				for(int i=0; i<V.size(); i++){
				RectangleInPaint = (Rectangle)V.get(i);
				InsideTheRectangle=(String)VectorOfNumbers.get(i);
				
				g.setColor(Color.YELLOW);
				g.fillRect(RectangleInPaint.x, RectangleInPaint.y, RectangleInPaint.width, RectangleInPaint.height);
				
	
				g.setColor(Color.RED);
				g.drawRect(RectangleInPaint.x, RectangleInPaint.y, RectangleInPaint.width, RectangleInPaint.height);
				g.drawString(InsideTheRectangle, RectangleInPaint.x+20, RectangleInPaint.y+30);
				
				}
				
				}	
			
		public Graph (){
			
			    V=new Vector();
				VectorOfCopying = new Vector();
				VectorOfLinks = new Vector();
				PanelForButtons = new JPanel();
				VectorOfNumbers = new Vector();
				ArrayOfButtons = new JButton[3];
				for (int i=0; i<3; i++){
					ArrayOfButtons[i]=new JButton(NamesOfButtons[i]);
					
				PanelForButtons.add(ArrayOfButtons[i]);
					
				}
			addMouseMotionListener(new inner());
			addMouseListener(new inner());
			ArrayOfButtons[0].addActionListener(new ActionListener(){
	
				@Override
				public void actionPerformed(ActionEvent arg0) {
					AddNode=true;
				}
				
			});
			ArrayOfButtons[1].addActionListener(new ActionListener(){
	
				@Override
				public void actionPerformed(ActionEvent e) {
					
					FirstNodeString=JOptionPane.showInputDialog(Graph.this, "Enter the data of the first node...");
					SecondNodeString=JOptionPane.showInputDialog(Graph.this, "Enter the data of the second node...");
					try{
					FirstInteger= Integer.parseInt(FirstNodeString);
					SecondInteger=Integer.parseInt(SecondNodeString);
					}catch(Exception ex){
						JOptionPane.showMessageDialog(Graph.this, "sorry, but you Entered string");
					}
					
					for(int An =0; An<VectorOfNumbers.size(); An++){
						if(((String)VectorOfNumbers.get(An)).equals(FirstNodeString)&&counter==0){
							counter++;
							KeepX1=((Rectangle)V.get(An)).x;
							KeepY1=((Rectangle)V.get(An)).y;
							indexOfNode1=An;
						}
					}
					for(int An=0; An <VectorOfNumbers.size(); An++){
						if(((String)VectorOfNumbers.get(An)).equals(SecondNodeString)&&counter==1){
							counter++;
							if(counter==2){
								//*LAMA=new line (KeepX1, KeepY1, ((Rectangle)V.get(An)).x, ((Rectangle)V.get(An)).y);
								VectorOfLinks.add(LAMA);
								indexOfNode2=An;
								repaint();
								counter=0;
							}
						}
					}
				}
				
			});
		
			ArrayOfButtons[2].addActionListener(new ActionListener(){
	
				@Override
				public void actionPerformed(ActionEvent arg0) {
					int xO;Vector VecDiff= new Vector();
					
					DeletedNode=JOptionPane.showInputDialog(Graph.this, "Enter the wanted node to delete");
						for(int i=0; i<V.size(); i++){
							if(DeletedNode.equals((String)VectorOfNumbers.get(i))){
								V.remove(i);
								VectorOfNumbers.remove(i);
								VectorOfLinks.removeAllElements();
								repaint();
							}
						}
					
				}
				
			});
			
			Rectangle SoWhat;
			for(int i=0; i<V.size();i++){
				SoWhat=(Rectangle)V.get(i);
				if(SoWhat.x==((line)VectorOfLinks.get(i)).x1 && SoWhat.y==((line)VectorOfLinks.get(i)).y1){
					SoWhat.x=((line)VectorOfLinks.get(i)).x1=XReq;
					SoWhat.y=((line)VectorOfLinks.get(i)).y1=YReq;
					repaint();
				}
			}	
		}
		public class inner extends MouseAdapter implements  MouseMotionListener{
			public void mouseReleased(MouseEvent e){
				Drag=false;
			}
			
			public void mousePressed(MouseEvent e){
				int x1 = e.getX();
				int y1 = e.getY();
				
				int width=x1+50;
				int height =y1+50;
				Require = new Rectangle(x1, y1, width, height);
		
				for(int i=0; i<V.size();i++){
					RectCompare=(Rectangle)V.get(i);
				
					
						if(Require.x>RectCompare.x&&Require.x<RectCompare.x+RectCompare.width
							&& Require.y>RectCompare.y && Require.y <RectCompare.y+RectCompare.width)
								{
									flagInYellow=true;
									if(e.isMetaDown())
									{
									String s=JOptionPane.showInputDialog(Graph.this,"Change the value to:" );
									int xoya=Integer.parseInt((String)VectorOfNumbers.get(i));
					
									try{
									 xoya= Integer.parseInt(s);
									}catch(Exception exeption){
									
										if(s!=null)
										JOptionPane.showMessageDialog(Graph.this, "Sorry, but you entered a string"
												,"Error Occured",JOptionPane.ERROR_MESSAGE);
									}
									VectorOfNumbers.set(i,xoya+"");
									
									repaint();
									}
								}		
				}
			}
	
			@Override
			public void mouseMoved(MouseEvent e) {
			if(AddNode){
			Clicked=true;	
			CurrentRectangle=new Rectangle(e.getX(), e.getY(), 50, 50);	
			repaint();
				}
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				String SoWhat;
			if(AddNode){
				String S = JOptionPane.showInputDialog(Graph.this,"Enter the data of the node");
				
				if(S!=null){
				try{
					
					for(int im=0 ; im<VectorOfNumbers.size(); im++){
						if(S.equals((String)VectorOfNumbers.get(im))){
							S=JOptionPane.showInputDialog(Graph.this, "change the repeated number, please");
							
						}
					}
					x=Integer.parseInt(S);
				}catch(Exception ex){
					
					JOptionPane.showMessageDialog(Graph.this, "sorry but you intered String",
							"Error Occured", JOptionPane.ERROR_MESSAGE);
					repaint();//
					S=null;
				}
				if(S!=null){
				NewRectangle =new Rectangle(e.getX(), e.getY(), 50, 50);
					
					VectorOfNumbers.add(new String(x+""));
					VectorOfCopying.add(new String(x+""));
					V.add(NewRectangle);
					repaint();
				
				
				}
				
				AddNode=false;
				}
				
				}
			}
			
			@Override 
			public void mouseDragged(MouseEvent e){
				int xm1 = e.getX();
				int ym1 = e.getY();
				
				int widthm=xm1+50;
				int heightm =ym1+50;
				for(int i=0; i<V.size();i++){
					RectCompare=(Rectangle)V.get(i);
			
					DraggedRectangle=new Rectangle(xm1, ym1, widthm, heightm);
					if(DraggedRectangle.x>RectCompare.x&&DraggedRectangle.x<RectCompare.x+RectCompare.width
							&& DraggedRectangle.y>RectCompare.y && DraggedRectangle.y <RectCompare.y+RectCompare.width)
								{
									XReq=e.getX()-25;
									YReq=e.getY()-25;
										Drag=true;
										RectCompare.x=e.getX()-25;
										RectCompare.y=e.getY()-25;
										XLama1=e.getX()-20;
										YLama1=e.getY()-20;
										
										V.set(i, RectCompare);
										repaint();
								}}}}}

