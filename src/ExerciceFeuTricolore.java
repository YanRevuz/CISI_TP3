
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
public class ExerciceFeuTricolore extends javax.swing.JFrame {
    
     //Tous les etat de notre Automate
    private enum State {
        ARRET, //etat initial
        ROUGE,
        VERT,
        ORANGE,
        ORANGE_ALLUME,
        ETIENT
    }
     
    /* Etat en cours*/
    private State etatEnCours;
    
    /* Represente la fréquence d'un tic du timer rouge */
    private static final int DELAY_TIMER_RED = 2000;
    
    /* Le timer du feu rouge de notre application */
    private Timer redTimer;
    
    /* Represente la fréquence d'un tic du timer vert */
    private static final int DELAY_TIMER_VERT = 3000;
    
    /* Le timer du feu vert de notre application */
    private Timer greenTimer;
    
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
    /**
     * Creates new form Feu
     */
    public ExerciceFeuTricolore() {
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
        eteindreFeu();
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
    public void setEnabledPauseButton(boolean enabled){
        this.pauseButton.setEnabled(enabled);
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
        
        /*Eteint les ampoules */
        eteindreAmpoules();
        
        /*Set les bouttons */
        setEnabledOffButton(false);
        setEnabledOnButton(true);
        setEnabledPauseButton(false);
    }
    
    public void allumerFeuRouge(){
        /* Passer dans l'etat rouge */
        changeState(State.ROUGE);
        
        /* Demarre le timer rouge */
        stopGreenTimer();
        stopOrangeTimer();
        startRedTimer();
        stopPauseOffTimer();
        stopPauseOnTimer();
 
        /* Allume l'ampoule rouge on eteint les autre */
        allumerRougeEteindreOrangeVert();
        
        /* Set les buttons */
        setEnabledOffButton(true);
        setEnabledOnButton(false);
        setEnabledPauseButton(true);
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
 
        /* Allume l'ampoule orange on eteint les autre */
        allumerOrangeEteindreRougeVert();
        
        /* Set les buttons */
        setEnabledOffButton(true);
        setEnabledOnButton(false);
        setEnabledPauseButton(true);
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
 
        /* Allume l'ampoule vert on eteint les autre */
        allumerVertEteindreOrangeRouge();
        
        /* Set les buttons */
        setEnabledOffButton(true);
        setEnabledOnButton(false);
        setEnabledPauseButton(true);
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
 
        /* Allume l'ampoule orange on eteint les autre */
        allumerOrangeEteindreRougeVert();
        
        /* Set les buttons */
        setEnabledOffButton(true);
        setEnabledOnButton(true);
        setEnabledPauseButton(false);
    }
    
    public void eteindreFeuPanne(){
        /* Passer dans l'etat eteint */
        changeState(State.ETIENT);
        
        /* Demarre le timer orange eteint */
        stopGreenTimer();
        stopOrangeTimer();
        stopRedTimer();
        startPauseOffTimer();
        stopPauseOnTimer();
 
        /* on eteint les ampoules */
        eteindreAmpoules();
        
        /* Set les buttons */
        setEnabledOffButton(true);
        setEnabledOnButton(true);
        setEnabledPauseButton(false);
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
        pauseButton = new javax.swing.JButton();
        offButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout ampouleRougeLayout = new javax.swing.GroupLayout(ampouleRouge);
        ampouleRouge.setLayout(ampouleRougeLayout);
        ampouleRougeLayout.setHorizontalGroup(
            ampouleRougeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 71, Short.MAX_VALUE)
        );
        ampouleRougeLayout.setVerticalGroup(
            ampouleRougeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 69, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout ampouleVertLayout = new javax.swing.GroupLayout(ampouleVert);
        ampouleVert.setLayout(ampouleVertLayout);
        ampouleVertLayout.setHorizontalGroup(
            ampouleVertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 71, Short.MAX_VALUE)
        );
        ampouleVertLayout.setVerticalGroup(
            ampouleVertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 73, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout ampouleOrangeLayout = new javax.swing.GroupLayout(ampouleOrange);
        ampouleOrange.setLayout(ampouleOrangeLayout);
        ampouleOrangeLayout.setHorizontalGroup(
            ampouleOrangeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
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

        pauseButton.setText("Panne");
        pauseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pauseButtonActionPerformed(evt);
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
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(onButton)
                    .addComponent(pauseButton)
                    .addComponent(offButton))
                .addGap(53, 53, 53)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(ampouleOrange, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ampouleVert, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(ampouleRouge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(188, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(ampouleRouge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(onButton)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(ampouleOrange, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(pauseButton)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(ampouleVert, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(offButton)
                        .addGap(44, 44, 44))))
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
            case ORANGE:
                /* Interdit */
                break;
            case VERT:
                /* Interdit */
                break;
            case ORANGE_ALLUME:
                allumerFeuRouge();
                break;
            case ETIENT:
                allumerFeuRouge();
                break;    
        }
    }//GEN-LAST:event_onButtonActionPerformed

    private void pauseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pauseButtonActionPerformed
        switch(etatEnCours){
            case ARRET:
                /* Interdit */
                break;
            case ROUGE:
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
            case ETIENT:
                /* Interdit */
                break;    
        }
    }//GEN-LAST:event_pauseButtonActionPerformed

    private void offButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_offButtonActionPerformed
        setEnabledOnButton(true);
        setEnabledOffButton(false);
        setEnabledPauseButton(false);
        switch(etatEnCours){
            case ARRET:
                /* Interdit */
                break;
            case ROUGE:
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
            case ETIENT:
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
                case ETIENT:
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
                case ORANGE:
                    /* Interdit */
                    break;
                case VERT:
                    allumerFeuOrange();
                    break;
                case ORANGE_ALLUME:
                    /* Interdit */
                    break;
                case ETIENT:
                    /* Interdit */
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
                case ORANGE:
                    allumerFeuRouge();
                    break;
                case VERT:
                    /* Interdit */
                    break;
                case ORANGE_ALLUME:
                    /* Interdit */
                    break;
                case ETIENT:
                    /* Interdit */
                    break;    
            }
        }
    };
    
    /* Déclaration des action réalisées lors d'un tic du timer panneOn */
    ActionListener taskPerformerPanneOn = new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
            switch(etatEnCours){
                case ARRET:
                    /* Interdit */
                    break;
                case ROUGE:
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
                case ETIENT:
                    /* Interdit */
                    break;    
            }
        }
    };
    
    /* Déclaration des action réalisées lors d'un tic du timer panneOff */
    ActionListener taskPerformerPanneOff = new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
            switch(etatEnCours){
                case ARRET:
                    /* Interdit */
                    break;
                case ROUGE:
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
                case ETIENT:
                    allumerFeuPanne();
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
            java.util.logging.Logger.getLogger(ExerciceFeuTricolore.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ExerciceFeuTricolore.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ExerciceFeuTricolore.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ExerciceFeuTricolore.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ExerciceFeuTricolore().setVisible(true);
            }
        });
    }
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Ampoule ampouleOrange;
    private Ampoule ampouleRouge;
    private Ampoule ampouleVert;
    private javax.swing.JButton offButton;
    private javax.swing.JButton onButton;
    private javax.swing.JButton pauseButton;
    // End of variables declaration//GEN-END:variables
}
