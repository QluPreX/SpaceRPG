package main;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;
/**
 * The display class is used for create the display JFrames and canvas to draw onto.
 * currently set to unresizable
 * @author karel
 * @version 1.0
 * @Since 2018-10-17
 */
public class Display {
	
	private JFrame frame;
	private Canvas canvas;
	private String title;
	public int width, height;
	/**
	 * The display's constructor will create a display with the given width and height
	 * @param title a name to give to the display, this will be show at the top
	 * @param width the width of the display that will be created
	 * @param height the height of the display that will be created
	 */
	public Display(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;
		createDisplay();
	}
	/**
	 * this method is called in the contructor and will create a new Jframe, Canvas and Dimension.
	 * all settings regarding the display's creation or handling will be set here
	 */
	private void createDisplay() {
		this.frame = new JFrame(title);
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		this.canvas = new Canvas();
		Dimension dim = new Dimension(width,height);
		canvas.setPreferredSize(dim);
		canvas.setMaximumSize(dim);
		canvas.setMinimumSize(dim);
		canvas.setFocusable(false);
		frame.add(canvas);
		frame.pack();
	}
	/**
	 * the public getter of the created canvas in this class
	 * @return the canvas that is used for the current display
	 */
	public Canvas getCanvas() {
		return this.canvas;
	}
	/**
	 * the public getter of the created Jframe in this class
	 * @return the frame that is used for the current display
	 */
	public JFrame getFrame() {
		return this.frame;
	}
}
