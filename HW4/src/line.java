



	import java.awt.Point;


	public class line {

		int x1,y1;
		int x2,y2;
		Point point11,point22;
		public line() {
			// TODO Auto-generated constructor stub
			x1=x2=y1=y2=0;
		}
		public line(Point p1, Point p2){
			x1= p1.x+20;
			y1= p1.y+20;
			x2 = p2.x+20;
			y2=p2.y+20;
		}
		public void setPoint ()
		{
			//set point11 at (x1,y1)
			
			point11.x=x1;
			point11.y=y1;
			
			point22.x=x2;
			point22.y=y2;
			
		
		}

	}


