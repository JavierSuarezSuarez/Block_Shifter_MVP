
package app;

import control.BlockPresenter;
import control.Command;
import control.DownCommand;
import control.LeftCommand;
import control.RightCommand;
import control.UpCommand;
import java.util.Map;
import model.Block;
import javax.swing.JFrame;
import view.BlockDisplay;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JMenuBar;

public class Main  extends JFrame{
    private static final int SIZE = 100;
    public static void main(String[] args) {
        new Main().execute();
    }
    private Block block;
    private BlockDisplay blockDisplay;
    private Map<String, Command> commands;
    private BlockPresenter blockPresenter;
    
    public Main() {
        this.setTitle("Block shifter");
        this.setSize(700,762);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.getContentPane().add(blockPanel());
        this.add(toolbar(), BorderLayout.SOUTH);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    private void execute() {
        this.block = new Block(4,4);
        this.blockPresenter = new BlockPresenter(SIZE,block,blockDisplay);
        this.commands= createCommands();
        this.setVisible(true);
    }
    private BlockPanel blockPanel() {
         BlockPanel blockPanel = new BlockPanel(SIZE,Block.MAX);
         this.blockDisplay = blockPanel;
         return  blockPanel;
    }
    
    private HashMap<String,Command> createCommands() {
        HashMap<String,Command> comm = new HashMap<>();
        comm.put("L",new LeftCommand(block));
        comm.put("R",new RightCommand(block));
        comm.put("U",new UpCommand(block));
        comm.put("D",new DownCommand(block));
        return comm;
    }
    
    private Component toolbar(){
        JMenuBar bar = new JMenuBar();
        bar.setLayout(new FlowLayout(FlowLayout.CENTER));
        bar.add(button("L"));
        bar.add(button("U"));
        bar.add(button("D"));
        bar.add(button("R"));
        return bar;
    }
    
    private JButton button(String name) {
        JButton button = new JButton(name);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                commands.get(name).execute();
            }
        });
        return button;
    }
    
}
