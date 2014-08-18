/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;

/**
 *
 * @author Alejandro
 */
public class Prueba extends javax.swing.JFrame {

    /**
     * Creates new form Prueba
     */
    public Prueba() {
        initComponents();
        modelo = (DefaultListModel)listSenias.getModel();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnHacer = new javax.swing.JButton();
        lblSenia = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listSenias = new javax.swing.JList();
        lblPalabras = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnHacer.setText("Hacer seña");
        btnHacer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHacerActionPerformed(evt);
            }
        });

        lblSenia.setText("Seña");

        listSenias.setModel(new javax.swing.DefaultListModel());
        jScrollPane1.setViewportView(listSenias);

        lblPalabras.setText("Orden de las palabras desplegadas en el GIF");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(lblSenia, javax.swing.GroupLayout.DEFAULT_SIZE, 598, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnHacer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1))
                    .addComponent(lblPalabras))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblPalabras)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnHacer))
                    .addComponent(lblSenia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
long startTime;
    private void btnHacerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHacerActionPerformed
        try{
            startTime = System.currentTimeMillis();

            modelo.clear();
            senias = (ArrayList<Senia>)Conexion.leer();
            int i=1;
            int anchoFrame = 230, altoFrame = 173, anchoImagen = 2300;
            int w = 500, h = 200;
            for(Senia senia: senias){
                modelo.addElement(i + " - " + senia.getPalabra());
                //System.out.println(senia.getId_palabra());
                //System.out.println(senia.getPalabra());
                //System.out.println(senia.getMorfema());
                Graphics g = null;
                Image img = ImageIO.read(new File(senia.getImagenes()));
               // Image img2 = img.getScaledInstance(11520, 864,Image.SCA.LE_FAST);
                //BufferedImage imagen = new BufferedImage(img);
                for(int y=1; y<=10;y++){
                    jScrollPane1.repaint();
                    
                    int sx = (y%(anchoImagen/anchoFrame))*anchoFrame;
                    int sy= (y/(anchoImagen/anchoFrame))*altoFrame;
                    g = this.getGraphics();
                    Thread.sleep(100);
                    g.drawImage(img, w, h, w + anchoFrame, h + altoFrame ,
                            sx, sy, sx + anchoFrame, sy + altoFrame, null);
                    jScrollPane1.paint(g);
                    jScrollPane1.update(g);
                   //lblSenia.setIcon(new ImageIcon();
                }
                i++;
            }
            long endTime   = System.currentTimeMillis();
            long totalTime = endTime - startTime;
            System.out.println(totalTime);
        } catch(SQLException e){
            System.out.println("No se ha podido obtener la lista de senias");
            System.out.println(e.getMessage());
            long endTime   = System.currentTimeMillis();
            long totalTime = endTime - startTime;
            System.out.println(totalTime);
        } catch (IOException e) {
            System.out.println("No se ha podido obtener la imagen");
            System.out.println(e.getMessage());
            long endTime   = System.currentTimeMillis();
            long totalTime = endTime - startTime;
            System.out.println(totalTime);
        } catch (InterruptedException ex) {
            Logger.getLogger(Prueba.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }//GEN-LAST:event_btnHacerActionPerformed

    /**
 * Converts a given Image into a BufferedImage
 *
 * @param img The Image to be converted
 * @return The converted BufferedImage
 */
        public BufferedImage toBufferedImage(Image img)
        {
            if (img instanceof BufferedImage)
            {
                return (BufferedImage) img;
            }

            // Create a buffered image with transparency
            BufferedImage bimage = new BufferedImage(lblSenia.getWidth(), lblSenia.getHeight(), BufferedImage.TYPE_INT_ARGB);

            // Draw the image on to the buffered image
            Graphics2D bGr = bimage.createGraphics();
            bGr.drawImage(img, 0, 0, null);
            bGr.dispose();

            // Return the buffered image
            return bimage;
        }
    
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
            java.util.logging.Logger.getLogger(Prueba.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Prueba.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Prueba.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Prueba.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
       
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Prueba().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHacer;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblPalabras;
    private javax.swing.JLabel lblSenia;
    private javax.swing.JList listSenias;
    // End of variables declaration//GEN-END:variables
    private ArrayList<Senia> senias;
    private DefaultListModel modelo;
}
