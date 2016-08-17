package fr.cab13140.herissonbuilder;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.List;

@SuppressWarnings("FieldCanBeLocal")
class MainFrame extends JFrame {

    private JButton addMeterButton = new JButton("+1m");
    private JButton addCentimeterButton = new JButton("+1cm");
    private JButton removeMeterButton = new JButton("-1m");
    private JButton removeCentimeterButton = new JButton("-1cm");
    private JButton stopButton = new JButton();
    private JButton endButton = new JButton();
    private JButton addButton = new JButton("Ajouter");
    private JLabel meterLabel = new JLabel();
    private short numero = 0;
    private JTextArea barres;
    private List<int[]> listeBarre;

    MainFrame(){
        updateLabel();

        setSize(500,500);
        this.setTitle("Pi4JTest");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setUndecorated(true);

        this.setLocationRelativeTo(null);
        Icon shutdownIcon;
        Icon endIcon;

        // Set Button Font Size to 30
        addMeterButton.setFont(new Font("Arial",Font.BOLD,50));
        addCentimeterButton.setFont(new Font("Arial",Font.ITALIC,50));
        removeCentimeterButton.setFont(new Font("Arial",Font.ITALIC,50));
        removeMeterButton.setFont(new Font("Arial",Font.BOLD,50));

        meterLabel.setText(Main.main.meters + "," + Main.main.centimeters + " m");
        meterLabel.setFont(new Font("Arial",Font.BOLD,60));

        addButton.setFont(new Font("Arial",Font.BOLD|Font.ITALIC,60));

        try {
            Image shutdownImage = ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("shutdown.png"));
            shutdownIcon = new ImageIcon(shutdownImage);
        } catch (IOException e) {
            e.printStackTrace();
            shutdownIcon = null;
        }
        stopButton.setIcon(shutdownIcon);
        stopButton.setText("Eteindre");
        stopButton.setFont(new Font("Arial",Font.BOLD,40));

        try{
            Image endImage = ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("end.png"));
            endIcon = new ImageIcon(endImage);
        }catch (IOException e){
            e.printStackTrace();
            endIcon = null;
        }
        endButton.setIcon(endIcon);
        endButton.setText("Quitter");
        endButton.setFont(new Font("Arial",Font.BOLD,40));

        Box line1 = Box.createHorizontalBox();
        line1.add(removeMeterButton);
        line1.add(addMeterButton);

        Box line2 = Box.createHorizontalBox();
        line2.add(removeCentimeterButton);
        line2.add(addCentimeterButton);

        Box line3 = Box.createHorizontalBox();
        line3.add(endButton);
        line3.add(addButton);
        line3.add(stopButton);

        Box line01 = Box.createHorizontalBox();
        line01.add(meterLabel);

        barres = new JTextArea(10,30);
        barres.setEditable(false);
        barres.setFocusable(false);
        barres.setFont(new Font("Arial",Font.PLAIN,50));
        barres.setText("Ajoutez des barres au dessus");
        JScrollPane list = new JScrollPane(barres);

        Box boxColumn = Box.createVerticalBox();
        boxColumn.add(line01);
        boxColumn.add(line1);
        boxColumn.add(line2);
        boxColumn.add(line3);
        boxColumn.add(list);

        this.getContentPane().add(boxColumn);
        this.setVisible(true);

        enableListeners();
    }

    private void enableListeners() {
        stopButton.addActionListener(e -> {
            Main.shutdownSystem();
            Main.endProgram();
        });
        addMeterButton.addActionListener(e -> {
            if (Main.addMeter(Main.ADD))
                updateLabel();
        });
        removeMeterButton.addActionListener(e -> {
            if (Main.addMeter(Main.SUB))
                updateLabel();
        });
        addCentimeterButton.addActionListener(e -> {
            if(Main.addCentimeter(Main.ADD))
                updateLabel();
        });
        removeCentimeterButton.addActionListener(e -> {
            if(Main.addCentimeter(Main.SUB))
                updateLabel();
        });
        endButton.addActionListener( e -> Main.endProgram());
        addButton.addActionListener( e -> addBarre(Main.main.meters,Main.main.centimeters));
    }

    private void updateLabel() {
        meterLabel.setText(Main.main.meters + "," + Main.main.centimeters + " m");
        this.revalidate();
        this.repaint();
    }
    private void addBarre(int m,int cm) {
        numero++;
        String oldString = barres.getText();
        String cmString;
        if (cm <= 10 && cm >= 0) {
            cmString = "0" + cm;
        } else {
            cmString = String.valueOf(cm);
        }
        barres.append("\n" + numero + "- Mesure " + );
        barres.revalidate();
        listeBarre.add(new int[]{m, cm});
    }
}
