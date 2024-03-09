/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package LocadoraCarros.view;

import LocadoraCarros.model.Fabricante;
import LocadoraCarros.services.FabricanteService;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author Fernando.Terceros
 */
public class FabricanteCadastroView extends javax.swing.JFrame {

    Fabricante fabricanteSalvar = new Fabricante();

    public FabricanteCadastroView() {

        // Ajustando algumas propiedades da janela
        this.setTitle("Cadastro de Fabricantes");
        this.setResizable(false);

        initComponents();
        try {

            MaskFormatter formatterNumberPlate = new MaskFormatter("*******");
            // Define os caracteres válidos
            formatterNumberPlate.setValidCharacters("ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789");

            MaskFormatter formatterYear = new MaskFormatter("####");
            MaskFormatter formatterColor = new MaskFormatter("**********");
            MaskFormatter formatterDecimal = new MaskFormatter("##########,##");

        } catch (Exception ex) {
            System.out.println("Erro ao formatar campo: " + ex.getMessage());
        }
    }

    public void novoItem() {
        // Limpa oos dados no formulário
        carregarFabricantes();
        txtFabricante.setText("");
    }

    public Boolean validaFormulario() {
        if (txtFabricante.getText().trim().length() < 1) {
            System.out.println(
                    "Fabricante: " + txtFabricante.getText().trim().length());
            return false;
        }
        return true;
    }

    public void salvar() {

        if (!validaFormulario()) {
            System.out.println("Preencha todos os campos");
            return;
        }

        // Verificar se o fabricante e novo ou se e uma edicao
        List<Fabricante> vFabricantes = new FabricanteService().consultar();
        if (new FabricanteService().existeFabricante(vFabricantes, txtFabricante.getText().trim())) {
            System.out.println("Fabricante já cadastrado");
            atualizarItem();
            return;
        }

        fabricanteSalvar.setNome(txtFabricante.getText().trim());

        System.out.println(fabricanteSalvar.toString());

        new FabricanteService().salvar(fabricanteSalvar);
        carregarFabricantes();
        novoItem();
    }

    // Atualizar um item da tabela de carros
    private void atualizarItem() {
        int row = tblListagem.getSelectedRow();
        if (row == -1) {
            System.out.println("Selecione um item para editar");
            return;
        }
        Long id = (Long) tblListagem.getValueAt(row, 0);

        String vFabricante = txtFabricante.getText();
        fabricanteSalvar.setId(id);
        fabricanteSalvar.setNome(vFabricante);
        System.out.println(fabricanteSalvar.toString());

        new FabricanteService().atualizar(fabricanteSalvar);
        carregarFabricantes();
        novoItem();
    }

    // private void exibirFabricantes() {
    // List<Fabricante> vFabricantes = new FabricanteService().consultar();
    // List<String> vLista = new ArrayList<>();

    // for (Fabricante fabricante : vFabricantes) {
    // vLista.add(fabricante.toString());
    // }

    // ListaFabricanteModelo tela = new ListaFabricanteModelo();
    // tela.setLista(vLista);
    // tela.setVisible(true);
    // }

    // Deletar um item da tabela de carros
    private void deletarItem() {
        int row = tblListagem.getSelectedRow();
        if (row == -1) {
            System.out.println("Selecione um item para deletar");
            return;
        }

        // Pega a primeira coluna do item selecionado
        Long id = (Long) tblListagem.getValueAt(row, 0);
        new FabricanteService().deletar(id);
        carregarFabricantes();
    }

    // Editar um item da tabela de carros
    private void editarItem() {
        int row = tblListagem.getSelectedRow();
        if (row == -1) {
            System.out.println("Selecione um item para editar");
            return;
        }

        Long id = (Long) tblListagem.getValueAt(row, 0);
        String nomeFabricante = (String) tblListagem.getValueAt(row, 1);

        // Carregar os dados do carro no formulário
        txtFabricante.setText(nomeFabricante.trim());
    }

    // Carrega a tabela fabricantes
    private void carregarFabricantes() {
        List<Fabricante> vFabricantes = new FabricanteService().consultar();
        // List<String> vLista = new ArrayList<>();

        String[] columnNames = {
                "ID",
                "Nome Fabricante"
        };

        // Criando o modelo da tabela
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        // Preenchendo a tabela com os dados dos Fabricantes
        for (Fabricante fabricante : vFabricantes) {
            Object[] rowData = { fabricante.getId(), fabricante.getNome() };
            model.addRow(rowData);
        }

        // Definindo o modelo da tabela
        tblListagem.setModel(model);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btnSalvar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblListagem = new javax.swing.JTable();
        btnEditaItem = new javax.swing.JButton();
        btnNovoItem = new javax.swing.JButton();
        btnDeletarItem = new javax.swing.JButton();
        txtFabricante = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel1.setText("Nome:");

        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        tblListagem.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {
                        { null, null },
                        { null, null },
                        { null, null },
                        { null, null }
                },
                new String[] {
                        "ID", "Nome Fabricante"
                }) {
            boolean[] canEdit = new boolean[] {
                    false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        tblListagem.setName(""); // NOI18N
        jScrollPane1.setViewportView(tblListagem);

        btnEditaItem.setText("Editar");
        btnEditaItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditaItemActionPerformed(evt);
            }
        });

        btnNovoItem.setText("Novo");
        btnNovoItem.setActionCommand("");
        btnNovoItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoItemActionPerformed(evt);
            }
        });

        btnDeletarItem.setText("Deletar");
        btnDeletarItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeletarItemActionPerformed(evt);
            }
        });

        txtFabricante.setToolTipText("Insira aqui o Fabricante");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 347,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 68,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtFabricante, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        126, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(btnNovoItem, javax.swing.GroupLayout.PREFERRED_SIZE, 78,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(btnEditaItem, javax.swing.GroupLayout.PREFERRED_SIZE, 78,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(btnDeletarItem, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 78,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(14, Short.MAX_VALUE)));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(txtFabricante, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnNovoItem, javax.swing.GroupLayout.PREFERRED_SIZE, 48,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnEditaItem, javax.swing.GroupLayout.PREFERRED_SIZE, 48,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnDeletarItem, javax.swing.GroupLayout.PREFERRED_SIZE, 48,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 49,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(20, 20, 20)));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDeletarItemActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnDeletarItemActionPerformed
        // TODO add your handling code here:
        deletarItem();
    }// GEN-LAST:event_btnDeletarItemActionPerformed

    private void btnNovoItemActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnNovoItemActionPerformed
        // TODO add your handling code here:
        novoItem();

    }// GEN-LAST:event_btnNovoItemActionPerformed

    private void btnEditaItemActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnEditaItemActionPerformed
        // TODO add your handling code here:
        editarItem();
    }// GEN-LAST:event_btnEditaItemActionPerformed

    private void btnCarregaListaActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnCarregaListaActionPerformed
        // TODO add your handling code here:
        carregarFabricantes();
    }// GEN-LAST:event_btnCarregaListaActionPerformed

    private void cbxFabricanteActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_cbxFabricanteActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_cbxFabricanteActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {// GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        carregarFabricantes();
    }// GEN-LAST:event_formWindowOpened

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnSalvarActionPerformed
        // Verificar se é um novo carro ou se é uma edição
        salvar();
    }// GEN-LAST:event_btnSalvarActionPerformed

    private void btnFabricantesActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnFabricantesActionPerformed
    }// GEN-LAST:event_btnFabricantesActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
        // (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default
         * look and feel.
         * For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FabricanteCadastroView.class.getName()).log(
                    java.util.logging.Level.SEVERE,
                    null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FabricanteCadastroView.class.getName()).log(
                    java.util.logging.Level.SEVERE,
                    null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FabricanteCadastroView.class.getName()).log(
                    java.util.logging.Level.SEVERE,
                    null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FabricanteCadastroView.class.getName()).log(
                    java.util.logging.Level.SEVERE,
                    null, ex);
        }
        // </editor-fold>
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FabricanteCadastroView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDeletarItem;
    private javax.swing.JButton btnEditaItem;
    private javax.swing.JButton btnNovoItem;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblListagem;
    private javax.swing.JFormattedTextField txtFabricante;
    // End of variables declaration//GEN-END:variables
}
