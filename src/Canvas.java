
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JPanel;


public class Canvas extends JPanel {
	private ArrayList<DShape> collection;
	private DShape selected;
	
	public Canvas(){
		collection = new ArrayList<DShape>();
		setSelected(null);
	}

	public ArrayList<DShape> getCollection() 
	{
		return collection;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(DShape shape : collection) {
			shape.draw(g);
			if(shape.equals(selected) && !(shape instanceof DLine))
			{
				for(Rectangle r : shape.getKnobs())
				{
					g.setColor(Color.black);
					g.fillRect(r.x, r.y, r.width, r.height);
				}
			}
			else if(shape.equals(selected) && (shape instanceof DLine))
			{
				for(Rectangle r : shape.getLineKnobs())
				{
					g.setColor(Color.black);
					g.fillRect(r.x, r.y, r.width, r.height);
				}
			}
		}
	}
	
	public void addShape(DShape shape) {
		collection.add(shape);
		setSelected(shape);
		repaint();
	}

	public DShape getSelected() {
		return selected;
	}
	
	public void setSelected(DShape shape)
	{
		this.selected = shape;
	}

	public void setSelected(int x, int y) {
		for(DShape shape: collection)
		{
			if (x >= shape.getX() && x <= shape.getX() + shape.getWidth() && y >= shape.getY() && y <= shape.getY() + shape.getHeight())
			{
				this.selected = shape;
			}
		}	
	}
	
	public void changeColor(Color color) {
		for(DShape shape: collection)
		{
			if (getSelected().getID() == shape.getID())
			{
				shape.setColor(color);
			}
		}
		repaint();
	}

	public void moveSelectedToFront() {
		for (int i = 0; i < collection.size() - 1; i++)
		{
			if(collection.get(i).equals(selected))
			{
				DShape temp = collection.get(i);
				collection.set(i, collection.get(i + 1));
				collection.set(i + 1, temp);
				break;
			}
		}
		repaint();
	}
	
	public void moveSelectedToBack() {
		for (int i = 1; i < collection.size(); i++)
		{
			if(collection.get(i).equals(selected))
			{
				DShape temp = collection.get(i);
				collection.set(i, collection.get(i - 1));
				collection.set(i - 1, temp);
				break;
			}
		}
		repaint();
	}
	
	public void removeSelected() {
		removeShape(getSelected());
	}
	
	public void removeShape(DShape shape) {
		for(int i = 0; i < collection.size(); i++)
		{
			if (shape.getID() == collection.get(i).getID())
			{
				for(DShape s : collection)
				{
					if(s.getID() > collection.get(i).getID())
					{
						s.setID(shape.getID() - 1);
					}
				}
				collection.remove(i);
			}
		}
		repaint();
	}
	
	public void reset() {
		collection = new ArrayList<DShape>();
		setSelected(null);
	}

	public void updateShape(DShape shape) {
		for(DShape d : collection)
		{
			if(d.getID() == shape.getID())
			{
				d.setAll(shape.getX(), shape.getY(), shape.getWidth(), shape.getHeight(), shape.getColor(), shape.getID());
			}
		}
		repaint();
	}

	
}
