
package app;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;
import view.BlockDisplay;

public class BlockPanel extends JPanel implements BlockDisplay {
    private final int size;
    private final int max;
    private Moved moved;
    private int x;
    private int y;
    
    public BlockPanel(int size, int max) {
        this.size = size;
        this.max = max;
        MouseHandler mouseHandler = new MouseHandler();
        this.addMouseListener(mouseHandler);
        this.addMouseMotionListener(mouseHandler);
    }
    @Override
    public void paint(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, getWidth(), getHeight());
        int d = max * size;
        g.setColor(Color.black);
        for (int i=0; i<= max; i++){
            int c = i*size;
            g.drawLine(0,c,d,c);
            g.drawLine(c, 0, c, d);
        }
        g.setColor(Color.red);
        g.fillRect(x,y, size, size);
    }

    @Override
    public void paintBlock(int x, int y) {
        this.x = x;
        this.y = y;
        repaint();
    }

    @Override
    public void on(Moved moved) {
        this.moved = moved;
    }
    
    private class MouseHandler implements MouseListener, MouseMotionListener {
        private boolean grabbed;
        @Override
        public void mouseClicked(MouseEvent event) {
        }

        @Override
        public void mousePressed(MouseEvent event) {
            if((event.getX() < x) || event.getX() > x + size) return;
            if((event.getY() < y) || event.getY() > y + size) return;
            grabbed = true;
        }    

        @Override
        public void mouseReleased(MouseEvent event) {
            grabbed = false;
        }

        @Override
        public void mouseEntered(MouseEvent event) {
        }

        @Override
        public void mouseExited(MouseEvent event) {
        }

        @Override
        public void mouseDragged(MouseEvent event) {
            if(grabbed) moved.to(event.getX(), event.getY());
        }

        @Override
        public void mouseMoved(MouseEvent event) {
        }
        
    }
}