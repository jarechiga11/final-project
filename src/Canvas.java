
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JPanel;


public class Canvas extends JPanel {
	private ArrayList<DShape> collection;
	private DShape selected;
	private final int KNOB_SIZE = 9;

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
				int knobWH = 18;
				for(Point p : shape.getKnobs())
				{
					g.setColor(Color.black);
					g.fillRect(((int)p.getX())-KNOB_SIZE, ((int)p.getY())-KNOB_SIZE, knobWH, knobWH);
				}
			}
			else if(shape.equals(selected) && (shape instanceof DLine))
			{
				int knobWH = 18;
				for(Point p : shape.getLineKnobs())
				{
					g.setColor(Color.black);
					g.fillRect(((int)p.getX())-KNOB_SIZE, ((int)p.getY())-KNOB_SIZE, knobWH, knobWH);
				}
			}
		}
	}
	
	public void addShape(DShape shape) {
		collection.add(shape);
		repaint();
	}
	
	public void addShape(DShapeModel shape) {
		if (shape instanceof DRectModel) 
		{
			DRect rect = new DRect();
			rect.setColor(shape.getColor());
			rect.setX(shape.getX());
			rect.setY(shape.getY());
			rect.setWidth(shape.getWidth());
			rect.setHeight(shape.getHeight());
			collection.add(rect);
			setSelected(rect);
		}
		else if(shape instanceof DOvalModel)
		{
			DOval oval = new DOval();
			oval.setColor(shape.getColor());
			oval.setX(shape.getX());
			oval.setY(shape.getY());
			oval.setWidth(shape.getWidth());
			oval.setHeight(shape.getHeight());
			collection.add(oval);
			setSelected(oval);
		}
		else if(shape instanceof DLineModel)
        {
            DLine line = new DLine();
            line.setColor(shape.getColor());
            line.setX(shape.getX());
            line.setY(shape.getY());
            line.setWidth(shape.getWidth());
            line.setHeight(shape.getHeight());
            collection.add(line);
            setSelected(line);
        }
		else if(shape instanceof DTextModel)
        {
            DText text = new DText();
            text.setColor(shape.getColor());
            text.setX(shape.getX());
            text.setY(shape.getY());
            text.setWidth(shape.getWidth());
            text.setHeight(shape.getHeight());
            collection.add(text);
            setSelected(text);
        }
		
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
			if (getSelected().equals(shape))
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
		for(int i = 0; i < collection.size(); i++)
		{
			if (collection.get(i).equals(getSelected()))
			{
				collection.remove(i);
			}
		}
	}
	
	public void reset() {
		collection = new ArrayList<DShape>();
		setSelected(null);
	}
}
