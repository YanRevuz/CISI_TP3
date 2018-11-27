
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Yan Revuz
 */
public class ExerciceFeuAutrichien extends javax.swing.JFrame {
    
     //Tous les etat de notre Automate
    private enum State {
        ARRET, //etat initial
        ROUGE,
        ROUGE_ORANGE,
        VERT,
        VERT_ETEINT,
        VERT_ALLUME,
        ORANGE,
        ORANGE_ALLUME,
        ORANGE_ETEINT
    }
     
    /* Etat en cours*/
    private State etatEnCours;
    
    /* Represente la fréquence d'un tic du timer rouge */
    private static final int DELAY_TIMER_RED = 2000;
    
    /* Le timer du feu rouge de notre application */
    private Timer redTimer;
    
    /* Represente la fréquence d'un tic du timer rouge orange */
    private static final int DELAY_TIMER_RED_ORANGE = 1000;
    
    /* Le timer du feu rouge orange de notre application */
    private Timer redOrangeTimer;
    
    /* Represente la fréquence d'un tic du timer vert */
    private static final int DELAY_TIMER_VERT = 3000;
    
    /* Le timer du feu vert de notre application */
    private Timer greenTimer;
    
     /* Represente la fréquence d'un tic du timer vert allume */
    private static final int DELAY_TIMER_VERT_ALLUME = 1000;
    
    /* Le timer du feu vert allume de notre application */
    private Timer greenTimerOn;
    
     /* Represente la fréquence d'un tic du timer vert eteint */
    private static final int DELAY_TIMER_VERT_ETEINT = 1000;
    
    /* Le timer du feu vert eteint de notre application */
    private Timer greenTimerOff;
    
    /* Represente la fréquence d'un tic du timer orange */
    private static final int DELAY_TIMER_ORANGE = 1500;
    
    /* Le timer du feu orange de notre application */
    private Timer orangeTimer;
    
    /* Represente la fréquence d'un tic du timer panne Allume */
    private static final int DELAY_TIMER_PAUSE_ON = 1000;
    
    /* Le timer du feu orange en panne allume de notre application */
    private Timer panneOnTimer;
    
    /* Represente la fréquence d'un tic du timer panne Eteint */
    private static final int DELAY_TIMER_PAUSE_OFF = 500;
    
    /* Le timer du feu orange en panne eteint de notre application */
    private Timer panneOffTimer;
    
    /* le compteur du nombre de clignotement vert */
    private int compteurClignotementVert;
    /**
     * Creates new form ExerciceFeuAutrichien
     */
    public ExerciceFeuAutrichien() {
        initComponents();
        init();
    }
    
    public void init(){
        this.ampouleRouge.setColor(Color.RED);
        this.ampouleVert.setColor(Color.GREEN);
        this.ampouleOrange.setColor(Color.ORANGE);
        this.redTimer = new Timer(DELAY_TIMER_RED, taskPerformerRed);
        this.greenTimer = new Timer(DELAY_TIMER_VERT, taskPerformerGreen);
        this.orangeTimer = new Timer(DELAY_TIMER_ORANGE, taskPerformerOrange);
        this.panneOnTimer = new Timer(DELAY_TIMER_PAUSE_ON, taskPerformerPanneOn);
        this.panneOffTimer = new Timer(DELAY_TIMER_PAUSE_OFF, taskPerformerPanneOff);
        this.redOrangeTimer = new Timer(DELAY_TIMER_RED_ORANGE, taskPerformerRedOrange);
        this.greenTimerOff = new Timer(DELAY_TIMER_VERT_ETEINT, taskPerformerGreenOff);
        this.greenTimerOn = new Timer(DELAY_TIMER_VERT_ALLUME, taskPerformerGreenOn);
        initCompteurClignotementVert();
        changeState(State.ARRET);
        
        eteindreAmpoules();
        /*Set les bouttons */
        setEnabledOffButton(false);
        setEnabledOnButton(true);
        setEnabledPanneButton(false);
    }
    
    /* reset le compteur à 0 */
    public void initCompteurClignotementVert(){
        this.compteurClignotementVert =0;
    }
    
    /* incremente le compteur */
    public void incrementeCompteurClignotementVert(){
        this.compteurClignotementVert +=1;
    }
    
    /* Permet de desactiver / activer le boutton marche*/
    public void setEnabledOnButton(boolean enabled){
        this.onButton.setEnabled(enabled);
    }
    
    /* Permet de desactiver / activer le boutton arret */
    public void setEnabledOffButton(boolean enabled){
        this.offButton.setEnabled(enabled);
    }
    
    /* Permet de desactiver / activer le boutton pause */
    public void setEnabledPanneButton(boolean enabled){
        this.panneButton.setEnabled(enabled);
    }
    
     /* Permet de changer d'état */
    public void changeState(State state){
        this.etatEnCours = state;
    }
    
    /* Permet de demarrer le timer rouge */
    public void startRedTimer(){
        this.redTimer.start();
    }
    
    /* Permet d'interrompre le timer rouge */
    public void stopRedTimer(){
        this.redTimer.stop();
    }
    
    /* Permet de demarrer le timer vert */
    public void startGreenTimer(){
        this.greenTimer.start();
    }
    
    /* Permet d'interrompre le timer vert */
    public void stopGreenTimer(){
        this.greenTimer.stop();
    
    } 
    
    /* Permet de demarrer le timer orange */
    public void startOrangeTimer(){
        this.orangeTimer.start();
    }
    
    /* Permet d'interrompre le timer orange */
    public void stopOrangeTimer(){
        this.orangeTimer.stop();
    }  
    
    /* Permet de demarrer le timer pauseOn */
    public void startPauseOnTimer(){
        this.panneOnTimer.start();
    }
    
    /* Permet d'interrompre le timer pauseOn */
    public void stopPauseOnTimer(){
        this.panneOnTimer.stop();
    }
    
    /* Permet de demarrer le timer pauseOff */
    public void startPauseOffTimer(){
        this.panneOffTimer.start();
    }
    
    /* Permet d'interrompre le timer pauseOff */
    public void stopPauseOffTimer(){
        this.panneOffTimer.stop();
    }
    
    /* Permet de demarrer le timer vert eteint */
    public void startGreenOffTimer(){
        this.greenTimerOff.start();
    }
    
    /* Permet d'interrompre le timer vert eteint */
    public void stopGreenOffTimer(){
        this.greenTimerOff.stop();
    }
    
    /* Permet de demarrer le timer vert allume */
    public void startGreenOnTimer(){
        this.greenTimerOn.start();
    }
    
    /* Permet d'interrompre le timer vert allume */
    public void stopGreenOnTimer(){
        this.greenTimerOn.stop();
    }
    
    /* Permet de demarrer le timer rouge orange */
    public void startRedOrangeTimer(){
        this.redOrangeTimer.start();
    }
    
    /* Permet d'interrompre le timer rouge orange */
    public void stopRedOrangeTimer(){
        this.redOrangeTimer.stop();
    }
    
    /* Eteint toutes les ampoule */
    public void eteindreAmpoules(){
        ampouleRouge.turnOFF();
        ampouleOrange.turnOFF();
        ampouleVert.turnOFF();
    }
    
    /* Allume l'ampoule Rouge Eteint les ampoules orange et vert */
    public void allumerRougeEteindreOrangeVert(){
        ampouleRouge.turnON();
        ampouleOrange.turnOFF();
        ampouleVert.turnOFF();
    }
    
    /* Allume l'ampoule orange Eteint les ampoules rouge et vert */
    public void allumerOrangeEteindreRougeVert(){
        ampouleRouge.turnOFF();
        ampouleOrange.turnON();
        ampouleVert.turnOFF();
    }
    
    /* Allume les ampoules orange et rouge Eteint l'ampoule vert */
    public void allumerRougeOrangeEteindreVert(){
        ampouleRouge.turnON();
        ampouleOrange.turnON();
        ampouleVert.turnOFF();
    }
    
    /* Allume l'ampoule vert Eteint les ampoules orange et rouge */
    public void allumerVertEteindreOrangeRouge(){
        ampouleRouge.turnOFF();
        ampouleOrange.turnOFF();
        ampouleVert.turnON();
    }
    
    public void eteindreFeu(){
        /* Passer dans l'état arret */
        changeState(State.ARRET);
        
        /* Stop les timer */
        stopGreenTimer();
        stopOrangeTimer();
        stopRedTimer();
        stopPauseOffTimer();
        stopPauseOnTimer();
        stopGreenOffTimer();
        stopGreenOnTimer();
        stopRedOrangeTimer();
        
        /*Eteint les ampoules */
        eteindreAmpoules();
        
        /*Set les bouttons */
        setEnabledOffButton(false);
        setEnabledOnButton(true);
        setEnabledPanneButton(false);
    }
    
    public void allumerFeuRouge(){
        /* Passer dans l'etat rouge */
        changeState(State.ROUGE);
        
        /*INIT le compteur */
        initCompteurClignotementVert();
        
        /* Demarre le timer rouge */
        stopGreenTimer();
        stopOrangeTimer();
        startRedTimer();
        stopPauseOffTimer();
        stopPauseOnTimer();
        stopGreenOffTimer();
        stopGreenOnTimer();
        stopRedOrangeTimer();
 
        /* Allume l'ampoule rouge on eteint les autre */
        allumerRougeEteindreOrangeVert();
        
        /* Set les buttons */
        setEnabledOffButton(true);
        setEnabledOnButton(false);
        setEnabledPanneButton(true);
    }
    
    public void allumerFeuRougeOrange(){
        /* Passer dans l'etat rouge orange */
        changeState(State.ROUGE_ORANGE);
        
        /* Demarre le timer rouge orange */
        stopGreenTimer();
        stopOrangeTimer();
        stopRedTimer();
        stopPauseOffTimer();
        stopPauseOnTimer();
        stopGreenOffTimer();
        stopGreenOnTimer();
        startRedOrangeTimer();
 
        /* Allume les ampoules rouge et orange on eteint les autre */
        allumerRougeOrangeEteindreVert();
        
        /* Set les buttons */
        setEnabledOffButton(true);
        setEnabledOnButton(false);
        setEnabledPanneButton(true);
    }
    
    public void allumerFeuVert(){
        /* Passer dans l'etat vert */
        changeState(State.VERT);
        
        /* Demarre le timer vert */
        startGreenTimer();
        stopOrangeTimer();
        stopRedTimer();
        stopPauseOffTimer();
        stopPauseOnTimer();
        stopGreenOffTimer();
        stopGreenOnTimer();
        stopRedOrangeTimer();
 
        /* Allume l'ampoule vert on eteint les autre */
        allumerVertEteindreOrangeRouge();
        
        /* Set les buttons */
        setEnabledOffButton(true);
        setEnabledOnButton(false);
        setEnabledPanneButton(true);
    }
    
    public void eteindreFeuVert(){
        /* Passer dans l'etat vert eteint */
        changeState(State.VERT_ETEINT);
        
        /* Demarre le timer vert eteint */
        stopGreenTimer();
        stopOrangeTimer();
        stopRedTimer();
        stopPauseOffTimer();
        stopPauseOnTimer();
        startGreenOffTimer();
        stopGreenOnTimer();
        stopRedOrangeTimer();
        
        /* on eteint les ampoules */
        eteindreAmpoules();

        /* Set les buttons */
        setEnabledOffButton(true);
        setEnabledOnButton(false);
        setEnabledPanneButton(true);
    }
    
    public void allumerFeuVertClignotement(){
        /* Passer dans l'etat vert allume */
        changeState(State.VERT_ALLUME);
        
        /* Demarre le timer vert allume */
        stopGreenTimer();
        stopOrangeTimer();
        stopRedTimer();
        stopPauseOffTimer();
        stopPauseOnTimer();
        stopGreenOffTimer();
        startGreenOnTimer();
        stopRedOrangeTimer();
        
        /*incremente le nombre de clignotement */
        incrementeCompteurClignotementVert();
        
        /* Allume l'ampoule vert on eteint les autre */
        allumerVertEteindreOrangeRouge();

        /* Set les buttons */
        setEnabledOffButton(true);
        setEnabledOnButton(false);
        setEnabledPanneButton(true);
    }
    
    public void allumerFeuOrange(){
        /* Passer dans l'etat orange */
        changeState(State.ORANGE);
        
        /* Demarre le timer orange */
        stopGreenTimer();
        startOrangeTimer();
        stopRedTimer();
        stopPauseOffTimer();
        stopPauseOnTimer();
        stopGreenOffTimer();
        stopGreenOnTimer();
        stopRedOrangeTimer();
        
        /* init le compteur */
        initCompteurClignotementVert();
 
        /* Allume l'ampoule orange on eteint les autre */
        allumerOrangeEteindreRougeVert();
        
        /* Set les buttons */
        setEnabledOffButton(true);
        setEnabledOnButton(false);
        setEnabledPanneButton(true);
    }
    
    public void allumerFeuPanne(){
        /* Passer dans l'etat orange allumé */
        changeState(State.ORANGE_ALLUME);
        
        /* Demarre le timer orange allumé */
        stopGreenTimer();
        stopOrangeTimer();
        stopRedTimer();
        stopPauseOffTimer();
        startPauseOnTimer();
        stopGreenOffTimer();
        stopGreenOnTimer();
        stopRedOrangeTimer();
 
        /* Allume l'ampoule orange on eteint les autre */
        allumerOrangeEteindreRougeVert();
        
        /* Set les buttons */
        setEnabledOffButton(true);
        setEnabledOnButton(true);
        setEnabledPanneButton(false);
    }
    
    public void eteindreFeuPanne(){
        /* Passer dans l'etat orange eteint */
        changeState(State.ORANGE_ETEINT);
        
        /* on eteint les ampoulesa */
        eteindreAmpoules();
        
        /* Demarre le timer orange eteint */
        stopGreenTimer();
        stopOrangeTimer();
        stopRedTimer();
        startPauseOffTimer();
        stopPauseOnTimer();
        stopGreenOffTimer();
        stopGreenOnTimer();
        stopRedOrangeTimer();
  
        /* Set les buttons */
        setEnabledOffButton(true);
        setEnabledOnButton(true);
        setEnabledPanneButton(false);
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ampouleRouge = new Ampoule();
        ampouleVert = new Ampoule();
        ampouleOrange = new Ampoule();
        onButton = new javax.swing.JButton();
        panneButton = new javax.swing.JButton();
        offButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout ampouleRougeLayout = new javax.swing.GroupLayout(ampouleRouge);
        ampouleRouge.setLayout(ampouleRougeLayout);
        ampouleRougeLayout.setHorizontalGroup(
            ampouleRougeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );
        ampouleRougeLayout.setVerticalGroup(
            ampouleRougeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout ampouleVertLayout = new javax.swing.GroupLayout(ampouleVert);
        ampouleVert.setLayout(ampouleVertLayout);
        ampouleVertLayout.setHorizontalGroup(
            ampouleVertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );
        ampouleVertLayout.setVerticalGroup(
            ampouleVertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout ampouleOrangeLayout = new javax.swing.GroupLayout(ampouleOrange);
        ampouleOrange.setLayout(ampouleOrangeLayout);
        ampouleOrangeLayout.setHorizontalGroup(
            ampouleOrangeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 73, Short.MAX_VALUE)
        );
        ampouleOrangeLayout.setVerticalGroup(
            ampouleOrangeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );

        onButton.setText("Marche");
        onButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onButtonActionPerformed(evt);
            }
        });

        panneButton.setText("Panne");
        panneButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                panneButtonActionPerformed(evt);
            }
        });

        offButton.setText("Arret");
        offButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                offButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panneButton)
                        .addGap(173, 173, 173))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(ampouleRouge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(onButton)
                                    .addComponent(offButton))
                                .addGap(53, 53, 53)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ampouleOrange, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ampouleVert, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 302, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(ampouleRouge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(onButton)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(panneButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                        .addComponent(offButton)
                        .addGap(48, 48, 48))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(ampouleOrange, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ampouleVert, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void onButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onButtonActionPerformed
        switch(etatEnCours){
            case ARRET:
                allumerFeuRouge();
                break;
            case ROUGE:
                /* Interdit */
                break;
            case ROUGE_ORANGE:
                /* Interdit */
                break;
            case ORANGE:
                /* Interdit */
                break;
            case VERT:
                /* Interdit */
                break;
            case ORANGE_ALLUME:
                allumerFeuRouge();
                break;
            case ORANGE_ETEINT:
                allumerFeuRouge();
                break;
            case VERT_ALLUME:
                /* Interdit */
                break;
            case VERT_ETEINT:
                /* Interdit */
                break;
        }
    }//GEN-LAST:event_onButtonActionPerformed

    private void panneButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_panneButtonActionPerformed
        switch(etatEnCours){
            case ARRET:
                /* Interdit */
                break;
            case ROUGE:
                allumerFeuPanne();
                break;
            case ROUGE_ORANGE:
                allumerFeuPanne();
                break;
            case ORANGE:
                allumerFeuPanne();
                break;
            case VERT:
                allumerFeuPanne();
                break;
            case ORANGE_ALLUME:
                /* Interdit */
                break;
            case ORANGE_ETEINT:
                /* Interdit */
                break;
            case VERT_ALLUME:
                allumerFeuPanne();
                break;
            case VERT_ETEINT:
                allumerFeuPanne();
                break;
        }
    }//GEN-LAST:event_panneButtonActionPerformed

    private void offButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_offButtonActionPerformed
        switch(etatEnCours){
            case ARRET:
                /* Interdit */
                break;
            case ROUGE:
                eteindreFeu();
                break;
            case ROUGE_ORANGE:
                eteindreFeu();
                break;
            case ORANGE:
                eteindreFeu();
                break;
            case VERT:
                eteindreFeu();
                break;
            case ORANGE_ALLUME:
                eteindreFeu();
                break;
            case ORANGE_ETEINT:
                eteindreFeu();
                break;
            case VERT_ALLUME:
                eteindreFeu();
                break;
            case VERT_ETEINT:
                eteindreFeu();
                break;
        }
    }//GEN-LAST:event_offButtonActionPerformed

    /* Déclaration des action réalisées lors d'un tic du timer rouge */
    ActionListener taskPerformerRed = new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
            switch(etatEnCours){
                case ARRET:
                    /* Interdit */
                    break;
                case ROUGE:
                    allumerFeuRougeOrange();
                    break;
                case ROUGE_ORANGE:
                    /* Interdit */
                    break;
                case ORANGE:
                    /* Interdit */
                    break;
                case VERT:
                    /* Interdit */
                    break;
                case ORANGE_ALLUME:
                    /* Interdit */
                    break;
                case ORANGE_ETEINT:
                    /* Interdit */
                    break;
                case VERT_ALLUME:
                    /* Interdit */
                    break;
                case VERT_ETEINT:
                    /* Interdit */
                    break;
            }
        }
    };
    
     /* Déclaration des action réalisées lors d'un tic du timer rouge orange */
    ActionListener taskPerformerRedOrange = new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
            switch(etatEnCours){
                case ARRET:
                    /* Interdit */
                    break;
                case ROUGE:
                    /* Interdit */
                    break;
                case ROUGE_ORANGE:
                    allumerFeuVert();
                    break;
                case ORANGE:
                    /* Interdit */
                    break;
                case VERT:
                    /* Interdit */
                    break;
                case ORANGE_ALLUME:
                    /* Interdit */
                    break;
                case ORANGE_ETEINT:
                    /* Interdit */
                    break;
                case VERT_ALLUME:
                    /* Interdit */
                    break;
                case VERT_ETEINT:
                    /* Interdit */
                    break;
            }
        }
    };
    
     /* Déclaration des action réalisées lors d'un tic du timer vert */
    ActionListener taskPerformerGreen = new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
            switch(etatEnCours){
                case ARRET:
                    /* Interdit */
                    break;
                case ROUGE:
                    /* Interdit */
                    break;
                case ROUGE_ORANGE:
                    /* Interdit */
                    break;
                case ORANGE:
                    /* Interdit */
                    break;
                case VERT:
                    eteindreFeuVert();
                    break;
                case ORANGE_ALLUME:
                    /* Interdit */
                    break;
                case ORANGE_ETEINT:
                    /* Interdit */
                    break;
                case VERT_ALLUME:
                    /* Interdit */
                    break;
                case VERT_ETEINT:
                    /* Interdit */
                    break;
            }
        }
    };
    
     /* Déclaration des action réalisées lors d'un tic du timer vert allume */
    ActionListener taskPerformerGreenOn = new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
            switch(etatEnCours){
                case ARRET:
                    /* Interdit */
                    break;
                case ROUGE:
                    /* Interdit */
                    break;
                case ROUGE_ORANGE:
                    /* Interdit */
                    break;
                case ORANGE:
                    /* Interdit */
                    break;
                case VERT:
                    /* Interdit */
                    break;
                case ORANGE_ALLUME:
                    /* Interdit */
                    break;
                case ORANGE_ETEINT:
                    /* Interdit */
                    break;
                case VERT_ALLUME:
                    eteindreFeuVert();
                    break;
                case VERT_ETEINT:
                    /* Interdit */
                    break;
            }
        }
    };
    
     /* Déclaration des action réalisées lors d'un tic du timer vert eteint */
    ActionListener taskPerformerGreenOff = new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
            switch(etatEnCours){
                case ARRET:
                    /* Interdit */
                    break;
                case ROUGE:
                    /* Interdit */
                    break;
                case ROUGE_ORANGE:
                    /* Interdit */
                    break;
                case ORANGE:
                    /* Interdit */
                    break;
                case VERT:
                    /* Interdit */
                    break;
                case ORANGE_ALLUME:
                    /* Interdit */
                    break;
                case ORANGE_ETEINT:
                    /* Interdit */
                    break;
                case VERT_ALLUME:
                    /* Interdit */
                    break;
                case VERT_ETEINT:
                    if (compteurClignotementVert < 4){
                        allumerFeuVertClignotement();
                    }else{
                        allumerFeuOrange();
                    }
                    break;
            }
        }
    };
    
     /* Déclaration des action réalisées lors d'un tic du timer orange */
    ActionListener taskPerformerOrange = new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
            switch(etatEnCours){
                case ARRET:
                    /* Interdit */
                    break;
                case ROUGE:
                    /* Interdit */
                    break;
                case ROUGE_ORANGE:
                    /* Interdit */
                    break;
                case ORANGE:
                    allumerFeuRouge();
                    break;
                case VERT:
                    /* Interdit */
                    break;
                case ORANGE_ALLUME:
                    /* Interdit */
                    break;
                case ORANGE_ETEINT:
                    /* Interdit */
                    break;
                case VERT_ALLUME:
                    /* Interdit */
                    break;
                case VERT_ETEINT:
                    /* Interdit */
                    break;
            }
        }
    };
    
     /* Déclaration des action réalisées lors d'un tic du timer panne on */
    ActionListener taskPerformerPanneOn = new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
            switch(etatEnCours){
                case ARRET:
                    /* Interdit */
                    break;
                case ROUGE:
                    /* Interdit */
                    break;
                case ROUGE_ORANGE:
                    /* Interdit */
                    break;
                case ORANGE:
                    /* Interdit */
                    break;
                case VERT:
                    /* Interdit */
                    break;
                case ORANGE_ALLUME:
                    eteindreFeuPanne();
                    break;
                case ORANGE_ETEINT:
                    /* Interdit */
                    break;
                case VERT_ALLUME:
                    /* Interdit */
                    break;
                case VERT_ETEINT:
                    /* Interdit */
                    break;
            }
        }
    };
    
     /* Déclaration des action réalisées lors d'un tic du timer panne off */
    ActionListener taskPerformerPanneOff = new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
            switch(etatEnCours){
                case ARRET:
                    /* Interdit */
                    break;
                case ROUGE:
                    /* Interdit */
                    break;
                case ROUGE_ORANGE:
                    /* Interdit */
                    break;
                case ORANGE:
                    /* Interdit */
                    break;
                case VERT:
                    /* Interdit */
                    break;
                case ORANGE_ALLUME:
                    /* Interdit */
                    break;
                case ORANGE_ETEINT:
                    allumerFeuPanne();
                    break;
                case VERT_ALLUME:
                    /* Interdit */
                    break;
                case VERT_ETEINT:
                    /* Interdit */
                    break;
            }
        }
    };
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ExerciceFeuAutrichien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ExerciceFeuAutrichien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ExerciceFeuAutrichien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ExerciceFeuAutrichien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ExerciceFeuAutrichien().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Ampoule ampouleOrange;
    private Ampoule ampouleRouge;
    private Ampoule ampouleVert;
    private javax.swing.JButton offButton;
    private javax.swing.JButton onButton;
    private javax.swing.JButton panneButton;
    // End of variables declaration//GEN-END:variables
}
