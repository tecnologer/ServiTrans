package Productos;

import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.util.StringTokenizer;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import servitrans.Formatea;
import servitrans.Session;
import tools.Util;
import tools.msj;
import tools.posiciones;
import tools.tiposContenido;

public class nuevoProducto extends javax.swing.JDialog {
    
    public JTextField detalleKit[][]=new JTextField[0][4];
    public JButton    deleteDetalle[]=new JButton[0];
    public eliminado  eli[]=new eliminado[0];
    private static boolean    isEdit[]=new boolean[0];
    private static int x=25;
    private static int y=45;
    private static posiciones posicionesXY[][]=new posiciones [26][5];
    public  int flag=0;    
    static    JLabel enc_codigo=new JLabel();
    static    JLabel enc_descripcion=new JLabel();
    static    JLabel enc_precio=new JLabel();
    static    JLabel enc_cantidad=new JLabel();
    static    JLabel enc_codigo1=new JLabel();
    static    JLabel enc_descripcion1=new JLabel();
    static    JLabel enc_precio1=new JLabel();
    static    JLabel enc_cantidad1=new JLabel();
    tiposContenido cont=new tiposContenido();
    public boolean edicion=false;
    public String  ubicacion="";
    static String kitDetail[][]=new String[0][4];
    public boolean isNew=true;
    public String isComplete;
    
    public boolean llenadoDetalle=false;
    
    public nuevoProducto(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setPosiciones();
        java.awt.event.ActionEvent evt=new java.awt.event.ActionEvent(this,1,null);
        isKitActionPerformed(evt);
        enc_codigo.setText("Codigo");
        enc_codigo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        enc_descripcion.setText("Descripción");
        enc_descripcion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        enc_precio.setText("Precio");
        enc_precio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        enc_cantidad.setText("Cantidad");
        enc_cantidad.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        enc_codigo1.setText("Codigo");
        enc_codigo1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        enc_descripcion1.setText("Descripción");
        enc_descripcion1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        enc_precio1.setText("Precio");
        enc_precio1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        enc_cantidad1.setText("Cantidad");
        enc_cantidad1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    }
    
    public void llenarDetalle(Object detalle[][])
    {
        llenadoDetalle=true;
        if(detalle.length>0)
            kitDetail=new String [detalle.length][4];
        for(int i=0;i<detalle.length;i++)
            for(int j=0;j<4;j++)
                kitDetail[i][j]=""+detalle[i][j];
    }
    
    private static void setPosiciones()
    {
        
        for(int i=0;i<26;i++)
        {
            posicionesXY[i][0]=new posiciones(x,y);
            posicionesXY[i][1]=new posiciones(x+70,y);
            posicionesXY[i][2]=new posiciones(x+325,y);
            posicionesXY[i][3]=new posiciones(x+390,y);
            posicionesXY[i][4]=new posiciones(x+455, y);
            
            
            y+=35;
            if(i==12)
            {
                y=45;
                x=525;
            }
        }
        x=25;
        y=45;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        isKit = new javax.swing.JCheckBox();
        id_producto = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        de_producto = new javax.swing.JTextField();
        lbl_nuPiezas = new javax.swing.JLabel();
        lbl_detalle = new javax.swing.JLabel();
        btnAgregar = new javax.swing.JButton();
        nu_piezas = new javax.swing.JLabel();
        panel = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        im_producto = new javax.swing.JTextField();
        nu_cantidad = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        lbl_complete = new javax.swing.JLabel();

        jLabel2.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Agregar producto");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMinimumSize(new java.awt.Dimension(900, 750));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                clearJText(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                addCampo(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                addCampo(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                addCampo(evt);
            }
        });

        isKit.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        isKit.setText("Kit");
        isKit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                isKitActionPerformed(evt);
            }
        });
        isKit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                windowsClose(evt);
            }
        });

        id_producto.setName("id_producto"); // NOI18N
        id_producto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                validaID(evt);
            }
        });
        id_producto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                windowsClose(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                isNumeric(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Codigo:");
        jLabel1.setAlignmentX(60.0F);
        jLabel1.setMaximumSize(new java.awt.Dimension(74, 14));
        jLabel1.setMinimumSize(new java.awt.Dimension(74, 14));
        jLabel1.setPreferredSize(new java.awt.Dimension(74, 14));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Descripción:");
        jLabel3.setAlignmentX(60.0F);
        jLabel3.setMaximumSize(new java.awt.Dimension(74, 14));
        jLabel3.setMinimumSize(new java.awt.Dimension(74, 14));
        jLabel3.setPreferredSize(new java.awt.Dimension(74, 14));

        de_producto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                windowsClose(evt);
            }
        });

        lbl_nuPiezas.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbl_nuPiezas.setText("Cantidad de piezas:");

        lbl_detalle.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbl_detalle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_detalle.setText("Detalles del Kit:");

        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/agregar.png"))); // NOI18N
        btnAgregar.setToolTipText("Agregar elemento");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        btnAgregar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                windowsClose(evt);
            }
        });

        nu_piezas.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        nu_piezas.setText("0");

        panel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                addCampo(evt);
                windowsClose(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                addCampo(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                addCampo(evt);
            }
        });
        panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Precio:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Cantidad:");

        im_producto.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        im_producto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                selectImporte(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                importeBlur(evt);
            }
        });
        im_producto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                isNumeric(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                windowsClose(evt);
            }
        });

        nu_cantidad.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        nu_cantidad.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                selectCantidad(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                cantidadBlur(evt);
            }
        });
        nu_cantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                windowsClose(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                isNumeric(evt);
            }
        });

        btnGuardar.setIcon(new javax.swing.ImageIcon("D:\\Dropbox\\INSOROB\\ServiTrans\\src\\img\\Database_1.png")); // NOI18N
        btnGuardar.setToolTipText("Guardar Producto");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        lbl_complete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/complete.png"))); // NOI18N
        lbl_complete.setText("Completo");
        lbl_complete.setFocusable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(10, 10, 10)
                                .addComponent(de_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(id_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(150, 150, 150)
                                .addComponent(isKit)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lbl_nuPiezas))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(lbl_detalle, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(nu_piezas, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(im_producto, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                    .addComponent(nu_cantidad)
                    .addComponent(lbl_complete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, 1147, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(im_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(id_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(de_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(nu_cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnGuardar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(isKit)
                            .addComponent(lbl_nuPiezas, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nu_piezas, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(lbl_detalle))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_complete)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    void percervarDatos(int a)
    {
        kitDetail=new String[detalleKit.length-1][4];
        int j=0;
        for(int i=0;i<detalleKit.length;i++)
        {
            if(i!=a)
            {
                kitDetail[j][0]=detalleKit[i][0].getText();
                kitDetail[j][1]=detalleKit[i][1].getText();
                kitDetail[j][2]=detalleKit[i][2].getText();
                kitDetail[j][3]=detalleKit[i][3].getText();
                j++;
            }
        }
    }
    
    void getDetalle()
    {
        kitDetail=new String[detalleKit.length][4];
        for(int i=0;i<detalleKit.length;i++)
        {
            kitDetail[i][0]=detalleKit[i][0].getText();
            kitDetail[i][1]=detalleKit[i][1].getText();
            kitDetail[i][2]=detalleKit[i][2].getText();
            kitDetail[i][3]=detalleKit[i][3].getText();
        }
    }
    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        if(flag==0)
            {
                panel.add(enc_codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 60, 25));
                panel.add(enc_descripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, 250, 25));
                panel.add(enc_precio, new org.netbeans.lib.awtextra.AbsoluteConstraints(350,20, 60, 25));
                panel.add(enc_cantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(420,20, 60, 25));
            }
            else if(flag==13)
            {
                panel.add(enc_codigo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 20, 60, 25));
                panel.add(enc_descripcion1, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 20, 250, 25));
                panel.add(enc_precio1, new org.netbeans.lib.awtextra.AbsoluteConstraints(855,20, 60, 25));
                panel.add(enc_cantidad1, new org.netbeans.lib.awtextra.AbsoluteConstraints(920,20, 60, 25));
            }
        isNew=true;
        agregarUnElemento();
    }//GEN-LAST:event_btnAgregarActionPerformed
    
    public void setEliminados(String id,boolean isDel, boolean isDb,int pos)
    {
        eliminado eliTemp[]=eli;
        eli=new eliminado[eli.length+1];
        int i;
        for(i=0;i<eliTemp.length;i++)
            eli[i]=eliTemp[i];
        
        eli[i]=new eliminado(id, isDel, isDb,pos);
                
    }
    public void mostrarDetalle()
    {
        java.awt.event.ActionEvent evt = new ActionEvent(this, 1, "");
        isKitActionPerformed(evt);
        if(edicion && isKit.isSelected())
        {
            if(isComplete.equals("1"))
            {
                lbl_complete.setText("Completo");
                lbl_complete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/complete.png")));
            }
            else
            {
                lbl_complete.setText("Incompleto");
                lbl_complete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/no_complete.png")));
            }
        }
    }
    private void isKitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_isKitActionPerformed
      if(isKit.isSelected())
      {
          lbl_detalle.setVisible(true);
          lbl_nuPiezas.setVisible(true);
          nu_piezas.setVisible(true);
          btnAgregar.setVisible(true);
          panel.setVisible(true);
          im_producto.setEditable(false);
          btnGuardar.setToolTipText("Guardar Kit");
            if(flag==0)
            {
                panel.add(enc_codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 60, 25));
                panel.add(enc_descripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, 250, 25));
                panel.add(enc_precio, new org.netbeans.lib.awtextra.AbsoluteConstraints(350,20, 60, 25));
                panel.add(enc_cantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(420,20, 60, 25));
                agregarUnElemento();
            }
            else if(flag==13)
            {
                panel.add(enc_codigo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 20, 60, 25));
                panel.add(enc_descripcion1, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 20, 250, 25));
                panel.add(enc_precio1, new org.netbeans.lib.awtextra.AbsoluteConstraints(855,20, 60, 25));
                panel.add(enc_cantidad1, new org.netbeans.lib.awtextra.AbsoluteConstraints(920,20, 60, 25));
            }
      }
      else
      {
          lbl_detalle.setVisible(false);
          lbl_nuPiezas.setVisible(false);
          nu_piezas.setVisible(false);
          btnAgregar.setVisible(false);
          panel.setVisible(false);
          btnGuardar.setToolTipText("Guardar Producto");
      }
    }//GEN-LAST:event_isKitActionPerformed

    private void isNumeric(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_isNumeric
      int k = (int) evt.getKeyChar(); 
        if (!cont.numerico(k)) 
            evt.consume();
    }//GEN-LAST:event_isNumeric

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
       String id=id_producto.getText();
       String descripcion=de_producto.getText();
       String importe=im_producto.getText();
       String cantidad=nu_cantidad.getText();
       if(!id.equals(""))
       {
           if(!descripcion.equals(""))
           {
               if(!importe.equals(""))
               {
                   if(!cantidad.equals(""))
                   {
                        if(isKit.isSelected())
                        {
                            String piezas=nu_piezas.getText();
                            getDetalle();
                            if(!edicion)
                            {
                                if(!MNG.kits.agregarKit(id, descripcion, importe, cantidad, piezas, kitDetail))
                                    this.dispose();
                             }
                            else
                            {
                               if(!MNG.kits.editarKit(id, descripcion, importe, cantidad, piezas, kitDetail,eli,ubicacion))
                                    this.dispose();
                            }
                        }
                        else
                        {
                            if(!edicion){
                                if(!MNG.refacciones.agregarRefaccion(id, descripcion, importe, cantidad))
                                    this.dispose();
                            }
                            else
                            {
                                if(!MNG.refacciones.editarRefaccion(id, descripcion, importe, cantidad,ubicacion))
                                    this.dispose();   
                            }
                        }
                   }
                   else
                   {
                       msj.ms("Requerido", "Es necesario la cantidad de refacciones.", msj.error);
                       nu_cantidad.requestFocus();
                   }
               }
               else
               {
                   msj.ms("Requerido", "Es necesario el importe de la refacción.", msj.error);
                   im_producto.requestFocus();
               }
           }
           else
           {
               msj.ms("Requerido", "Es necesario la descripción de la refacción.", msj.error);
               de_producto.requestFocus();
           }
       }
       else
       {
           msj.ms("Requerido", "Es necesario el id de la refacción.", msj.error);
           id_producto.requestFocus();
       }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void selectImporte(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_selectImporte
        String im_produc=im_producto.getText();
        im_produc=Util.quitarFormatoMoneda(im_produc);
        im_producto.setText(im_produc);
        im_producto.selectAll();
    }//GEN-LAST:event_selectImporte

    private void importeBlur(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_importeBlur
        String im_produc=Util.quitarFormatoMoneda(im_producto.getText());
        if(!im_produc.equals(""))
        {
            if(Util.isFloat(im_produc))
            {
                //im_produc=Util.formatoMoneda(im_produc);
                im_produc=""+Formatea.alinder("$ ###,###.00", Double.parseDouble(im_produc));
                im_producto.setText(im_produc.trim());
            }
            else
                msj.ms("", "Importe invalido", msj.error);
        }
    }//GEN-LAST:event_importeBlur
    
    private void setFormatoMonedaDetalle(java.awt.event.FocusEvent evt) {                             
        int  elem=Integer.parseInt(""+evt.getComponent().getName().split("_")[1]);
        String im_produc=detalleKit[elem][2].getText();
        if(!im_produc.equals(""))
        {
            if(Util.isFloat(im_produc))
            {
                //im_produc=Util.formatoMoneda(im_produc);
                im_produc=""+Formatea.alinder("$ ###,###.00", Double.parseDouble(im_produc));
                detalleKit[elem][2].setText(im_produc.trim());
            }
            else
                msj.ms("", "Importe invalido", msj.error);
        }
    }
    
    private void selectImporteDetalle(java.awt.event.FocusEvent evt) {                               
        int  elem=Integer.parseInt(""+evt.getComponent().getName().split("_")[1]);
        String im_produc=detalleKit[elem][2].getText();
        im_produc=Util.quitarFormatoMoneda(im_produc);
        detalleKit[elem][2].setText(im_produc);
        detalleKit[elem][2].selectAll();
    }
    
    private void selectCantidad(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_selectCantidad
        nu_cantidad.selectAll();
    }//GEN-LAST:event_selectCantidad

    private void cantidadBlur(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cantidadBlur
        String cant=nu_cantidad.getText();
        if(!cant.equals(""))
        {
            int i=0;
            if(Util.isFloat(cant))
            {
                double a=Double.parseDouble(cant);
                i=(int)a;
                nu_cantidad.setText(""+i);
            }
            else
                msj.ms("", "La cantidad debe de ser un numero entero.", msj.error);
        }
    }//GEN-LAST:event_cantidadBlur

    private void getDescripcion(java.awt.event.FocusEvent evt) {                              
        int elem=Integer.parseInt(evt.getComponent().getName().split("_")[1]);
        String id=detalleKit[elem][0].getText();
        String kit=id_producto.getText();
        String ubica=Session.ubicacion;
        if(edicion)
            ubica=ubicacion;
        if(!DAO.kits.isKit(Session.cnx, id))
        {
            if(DAO.refacciones.isRefaccion(Session.cnx, id, Session.ubicacion))
            {
                String desc=""+DAO.refacciones.getRefaccionPorID(Session.cnx, id, ubica)[0][1];
                String precio=""+DAO.refacciones.getRefaccionPorID(Session.cnx, id, ubica)[0][2];
                detalleKit[elem][1].setText(desc);
                detalleKit[elem][1].setEditable(false);
                detalleKit[elem][2].setText(""+Formatea.alinder("$ ###,###.00", Double.parseDouble(precio)));
                detalleKit[elem][2].setEditable(false);
            }
            else if(DAO.kitDetalle.existRefaccion(Session.cnx, kit, id, ubicacion))
            {
                if(edicion)
                {
                    DAO.kitDetalle.getDescripcion(Session.cnx, kit,id, ubicacion);
                }
                else
                {
                    msj.ms("Error","La refacción ya se encuentra agregada",msj.error);
                    detalleKit[elem][0].requestFocus();
                }
                detalleKit[elem][1].setEditable(true);
                detalleKit[elem][2].setEditable(true);
            }
            else if(id.equals(kit))
            {
                msj.ms("Error", "No puede ser el mismo codigo del kit", msj.error);
                detalleKit[elem][1].requestFocus();
            }
            else
            {
                detalleKit[elem][1].setEditable(true);
                detalleKit[elem][2].setEditable(true);
            }
            calcularImporte();
        }
        else
        {
            msj.ms("Error", "Acción no permitida.\nEl codigo pertenece a un kit.", msj.error);
            detalleKit[elem][0].selectAll();
            detalleKit[elem][0].requestFocus();
        }
                
    }    
    private void addCampo(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_addCampo
        if(isKit.isSelected())
        {
            ActionEvent evt1=new ActionEvent(this, 1, null);
            if(KeyEvent.KEY_PRESSED==KeyEvent.VK_PLUS)
                btnAgregarActionPerformed(evt1);
        }
        else if(evt.getKeyChar()==KeyEvent.VK_ESCAPE)
            this.dispose();
    }//GEN-LAST:event_addCampo

    private void clearJText(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_clearJText
        if(!edicion)
        {
//            id_producto.setText("");
//            de_producto.setText("");
//            im_producto.setText("");
//            nu_cantidad.setText("");
//            isKit.setSelected(false);
//            lbl_detalle.setVisible(false);
//            lbl_nuPiezas.setVisible(false);
//            nu_piezas.setVisible(false);
//            btnAgregar.setVisible(false);
//            panel.setVisible(false);
//            btnGuardar.setToolTipText("Guardar Producto");
//            detalleKit=new JTextField[0][4];
//            deleteDetalle=new JButton[0];
            flag=0;
            lbl_complete.setVisible(false);
//            llenadoDetalle=false;
        }
        recorrerFocus();
        mostrarDetalle();
    }//GEN-LAST:event_clearJText

    public void calcularImporte()
    {
        float newImpor=0;
        for(int i=0;i<detalleKit.length;i++)
        {
            String cant=detalleKit[i][3].getText();
            String im=detalleKit[i][2].getText();
            if(im.equals(""))
                im="0";
            else if(Util.isFloat(Util.quitarFormatoMoneda(im)))
                im=Util.quitarFormatoMoneda(im);
            else
            {
                msj.ms("", "Error en el importe", i);
                detalleKit[i][2].requestFocus();
                break;
            }
            
            if(cant.equals(""))
                cant="1";
            else if(!Util.isNumeric(cant))
            {
                msj.ms("", "Error en cantidad", i);
                detalleKit[i][3].requestFocus();
                break;
            }
                
            
            newImpor+=Float.parseFloat(cant)*Float.parseFloat(im);
        }
        im_producto.setText(""+Formatea.alinder("$ ###,###.00", (newImpor*0.9)));
    }
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        detalleKit=new JTextField[0][4];
        deleteDetalle=new JButton[0];
        flag=0;
        edicion=false;
        this.dispose();
    }//GEN-LAST:event_formWindowClosing

    private void validaID(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_validaID
        String id=id_producto.getText();
        String ubi=Session.ubicacion;
        if(!edicion)
        {
            if(!id.equals(""))
            {
               if(!DAO.kits.isKit(Session.cnx, id))
               {
                   if(DAO.refacciones.isRefaccion(Session.cnx, id, ubi))
                       msj.ms("Error","La refacción se encuentra registrada",msj.error);
               }
               else
                   msj.ms("Error", "El kit ya se encuentra registrado.", msj.error);

            }
        }
    }//GEN-LAST:event_validaID

    private void windowsClose(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_windowsClose
       if(evt.getKeyChar()==KeyEvent.VK_ESCAPE)
           this.dispose();
    }//GEN-LAST:event_windowsClose
    
    public  void agregaTextField()
    {       
        
            if(flag==0)
            {
                panel.add(enc_codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 60, 25));
                panel.add(enc_descripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, 250, 25));
                panel.add(enc_precio, new org.netbeans.lib.awtextra.AbsoluteConstraints(350,20, 60, 25));
                panel.add(enc_cantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(420,20, 60, 25));
            }
            else if(flag==13)
            {
                panel.add(enc_codigo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 20, 60, 25));
                panel.add(enc_descripcion1, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 20, 250, 25));
                panel.add(enc_precio1, new org.netbeans.lib.awtextra.AbsoluteConstraints(855,20, 60, 25));
                panel.add(enc_cantidad1, new org.netbeans.lib.awtextra.AbsoluteConstraints(920,20, 60, 25));
            }
            /***** CODIGO DETALLE *****/
            detalleKit[detalleKit.length-1][0] = new javax.swing.JTextField();
            detalleKit[detalleKit.length-1][0].setName("ID_"+flag); 
            detalleKit[detalleKit.length-1][0].setText(kitDetail[flag][0]);
            detalleKit[detalleKit.length-1][0].addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    isNumeric(evt);
                }
            });
            detalleKit[detalleKit.length-1][0].addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusLost(java.awt.event.FocusEvent evt) {
                    getDescripcion(evt);
                    
                }
            });
            //SI EL DETALLE PROVIENE DE LA BASE DE DATOS, EVITAMOS LA MODIFICACION DEL CODIGO
            if(eli[flag].isDb() && !eli[flag].isDelete())
                detalleKit[detalleKit.length-1][0].setEditable(false);
            else
                detalleKit[detalleKit.length-1][0].setEditable(true);
            panel.add(detalleKit[detalleKit.length-1][0], new org.netbeans.lib.awtextra.AbsoluteConstraints(posicionesXY[flag][0].getX(), posicionesXY[flag][0].getY(), 60, 25));
            /******* DESCRIPCION DETALLE *******/
            detalleKit[detalleKit.length-1][1] = new javax.swing.JTextField();
            detalleKit[detalleKit.length-1][1].setText(kitDetail[flag][1]);
            detalleKit[detalleKit.length-1][1].setName("DE_"+flag); 
            detalleKit[detalleKit.length-1][1].addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {               
               //System.out.println(evt.toString());
                selectTextDescripcion(evt);
            }
            });
            panel.add(detalleKit[detalleKit.length-1][1], new org.netbeans.lib.awtextra.AbsoluteConstraints(posicionesXY[flag][1].getX(), posicionesXY[flag][1].getY(), 250, 25));
            /******* PRECIO DETALLE *****/
            detalleKit[detalleKit.length-1][2] = new javax.swing.JTextField();
            detalleKit[detalleKit.length-1][2].setName("IM_"+flag);
            detalleKit[detalleKit.length-1][2].setText(kitDetail[flag][2]);
            detalleKit[detalleKit.length-1][2].addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    isNumeric(evt);
                }
            });
            detalleKit[detalleKit.length-1][2].addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusLost(java.awt.event.FocusEvent evt) {
                    setFormatoMonedaDetalle(evt);
                    calcularImporte();
                }
                public void focusGained(java.awt.event.FocusEvent evt) {               
                    selectImporteDetalle(evt);
                }
            });
            detalleKit[detalleKit.length-1][2].setHorizontalAlignment(javax.swing.JTextField.RIGHT);
            panel.add(detalleKit[detalleKit.length-1][2], new org.netbeans.lib.awtextra.AbsoluteConstraints(posicionesXY[flag][2].getX(), posicionesXY[flag][2].getY(), 60, 25));
            
            /********** CANTIDAD *********/
            detalleKit[detalleKit.length-1][3] = new javax.swing.JTextField();
            detalleKit[detalleKit.length-1][3].setName("CT_"+flag);
            detalleKit[detalleKit.length-1][3].setText(kitDetail[flag][3]);
            detalleKit[detalleKit.length-1][3].setHorizontalAlignment(javax.swing.JTextField.RIGHT);
            detalleKit[detalleKit.length-1][3].addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    isNumeric(evt);
                }
            });
            detalleKit[detalleKit.length-1][3].addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusLost(java.awt.event.FocusEvent evt) {
                    calcularImporte();
                }
            });
            panel.add(detalleKit[detalleKit.length-1][3], new org.netbeans.lib.awtextra.AbsoluteConstraints(posicionesXY[flag][3].getX(), posicionesXY[flag][3].getY(), 60, 25));
            
            /*********************************************/
            /****       BOTONES DE BORRADO      **********/
            deleteDetalle[deleteDetalle.length-1]= new JButton();
            deleteDetalle[deleteDetalle.length-1].setName("btn_"+(flag));
            deleteDetalle[deleteDetalle.length-1].setBorder(null);
            deleteDetalle[deleteDetalle.length-1].setBackground(null);
            deleteDetalle[deleteDetalle.length-1].setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/delete.png"))); // NOI18N
            deleteDetalle[deleteDetalle.length-1].addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                
                String elemento[]=evt.toString().split("on");
                String elemento1[]=elemento[3].split("_");
                int i=Integer.parseInt(elemento1[1].trim());
                flag=0;
                borrarElemento(i);
            }
                });
            panel.add(deleteDetalle[deleteDetalle.length-1], new org.netbeans.lib.awtextra.AbsoluteConstraints(posicionesXY[flag][4].getX(), posicionesXY[flag][4].getY(), 25, 25));
            
            pack();
            flag++;
            
            
            if(flag==26)
                btnAgregar.setEnabled(false);
            else
                btnAgregar.setEnabled(true);
            
            nu_piezas.setText(""+flag); 
   }
    
    public void agregarUnElemento()
    {
        JTextField detalleTemp[][]=detalleKit;
        
        if(!llenadoDetalle)
        {
            kitDetail=new String [deleteDetalle.length+1][4];
            boolean editTemp[]=isEdit;
            eliminado eliTemp[]=eli;
            eli=new eliminado[eli.length+1];
            int j;
            for(j=0;j<eliTemp.length;j++)
                eli[j]=eliTemp[j];
            
            eli[j]=new eliminado("", false, false,flag);
        }
        deleteDetalle=new JButton[deleteDetalle.length+1];
        detalleKit=new JTextField[detalleTemp.length+1][4];
        isEdit=new boolean[isEdit.length+1];
                
        for(int i=0;i<detalleTemp.length;i++)
        {
            detalleKit[i][0]=detalleTemp[i][0];
            detalleKit[i][1]=detalleTemp[i][1];
            detalleKit[i][2]=detalleTemp[i][2];
            detalleKit[i][3]=detalleTemp[i][3];
            
            kitDetail[i][0]="";
            kitDetail[i][1]="";
            kitDetail[i][2]="";
            kitDetail[i][3]="";
        }
       
        agregaTextField();
    }
    
    public void recorrerFocus()
    {
        for(int i=0;i<detalleKit.length;i++)
            for(int j=0;j<detalleKit[0].length;j++)
                detalleKit[i][j].requestFocus();
        
       btnAgregar.requestFocus();
    }
   void selectTextDescripcion(FocusEvent evt)
   {
       int  elem=Integer.parseInt(evt.getComponent().getName().split("_")[1]);       
       detalleKit[elem][1].selectAll();
   }
    private void borrarElemento(int elem)
    {
        JTextField detalleTemp[][]=detalleKit;
        percervarDatos(elem);
        JButton    btnTmp[]=deleteDetalle;
        deleteDetalle=new JButton[deleteDetalle.length-1];
        detalleKit=new JTextField[detalleTemp.length-1][4];
        eli[elem].setDelete(true);
        int j=0;        
        flag=0;
        nu_piezas.setText(""+flag);
        for(int i=0;i<detalleTemp.length;i++)
        {
            if(j!=i)
            {
                detalleKit[j][0]=detalleTemp[i][0];
                detalleKit[j][1]=detalleTemp[i][1];
                detalleKit[j][2]=detalleTemp[i][2];
                detalleKit[j][3]=detalleTemp[i][3];
                deleteDetalle[j]=btnTmp[i];
                j++;
            }
        }
        panel.removeAll();
        panel.repaint();
        for(int a=0;a<deleteDetalle.length;a++)
        {
            agregaTextField();
        }
        
        //colocarDatos();
    }
    
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                nuevoProducto dialog = new nuevoProducto(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnGuardar;
    public javax.swing.JTextField de_producto;
    public javax.swing.JTextField id_producto;
    public javax.swing.JTextField im_producto;
    public javax.swing.JCheckBox isKit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    public javax.swing.JLabel lbl_complete;
    private javax.swing.JLabel lbl_detalle;
    private javax.swing.JLabel lbl_nuPiezas;
    public javax.swing.JTextField nu_cantidad;
    private javax.swing.JLabel nu_piezas;
    private javax.swing.JPanel panel;
    // End of variables declaration//GEN-END:variables
}
