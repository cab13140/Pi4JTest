package fr.cab13140.herissonbuilder;

import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CDevice;
import com.pi4j.io.i2c.I2CFactory;

import java.io.IOException;
import java.util.logging.Logger;

public class Main {
    int meters;
    int centimeters;
    final static Main main = new Main();
    final static int ADD = 1;
    final static int SUB = 0;

    public static void main(String args[]) {
        // Récupérer l'instance GPIO
        /*
        final GpioController gpio = GpioFactory.getInstance();
        */

        // Préparer l'affichage et les mètres
        main.meters = 1;
        main.centimeters = 0;

        // Lancer la fenêtre
        MainFrame mainFrame = new MainFrame();
    }

    static void endProgram(){
        Logger.getLogger("[Pi4JTest]").info("Ending program...");
        System.exit(0);
    }

    static void shutdownSystem(){
        Logger.getLogger("[Pi4JTest]").info("Ending system...");
        try {
            Runtime.getRuntime().exec("shutdown now");
        } catch (IOException ignored){}
    }

    static boolean addMeter(int type) {
        if (type != ADD && type != SUB)
            return false;
        if (type == ADD && main.meters == 11 && main.centimeters > 0) {
            main.meters = 12;
            main.centimeters = 0;
            return true;
        }

        if (type == SUB && main.meters == 0 && main.centimeters > 0) {
            main.centimeters = 0;
            return true;
        }

        if (main.meters == 0 && type == SUB)
            return false;
        else if (main.meters == 12 && type == ADD)
            return false;
        else {
            if (type == SUB)
                main.meters--;
            if (type == ADD)
                main.meters++;
            return true;
        }
    }

    /**
     * Ajoute ou retire un centimètre au compteur
     *
     * @param type si c'est un ADD ou un SUB
     * @return true si modifié, sinon false
     */
    static boolean addCentimeter(int type) {
        if (type != ADD && type != SUB)
            return false;

        if (type == SUB && main.meters == 0 && main.centimeters == 0)
            return false;

        if (type == ADD && main.meters == 12 && main.centimeters == 0)
            return false;

        if (main.centimeters == 0 && type == SUB && main.meters >= 1) {
            main.centimeters = 99;
            main.meters--;
            return true;
        }

        else if (main.centimeters == 99 && type == ADD && main.meters <= 11) {
            main.centimeters = 0;
            main.meters++;
            return true;
        }

        else {
            if (type == SUB)
                main.centimeters--;
            if (type == ADD)
                main.centimeters++;
            return true;
        }
    }
}
