/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package LocadoraCarros.view;

// import LocadoraCarros.classe.ConexaoBanco;
import LocadoraCarros.model.Carro;
import LocadoraCarros.model.Fabricante;
import LocadoraCarros.model.Modelo;
import LocadoraCarros.services.CarroService;
import LocadoraCarros.services.FabricanteService;
import LocadoraCarros.services.ModeloService;

import java.util.ArrayList;
import java.util.List;
// import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
// import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author Fernando.Terceros
 */
public class CarroCadastroView extends javax.swing.JFrame {

    Carro carroSalvar = new Carro();

    public CarroCadastroView() {

        // Ajustando algumas propiedades da janela
        this.setTitle("Cadastro de Carros");
        this.setResizable(false);

        initComponents();
        try {

            MaskFormatter formatterNumberPlate = new MaskFormatter("*******");
            // Define os caracteres válidos
            formatterNumberPlate.setValidCharacters("ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789");

            MaskFormatter formatterYear = new MaskFormatter("####");
            MaskFormatter formatterColor = new MaskFormatter("**********");
            MaskFormatter formatterDecimal = new MaskFormatter("##########,##");

            txtPlaca.setFormatterFactory(new DefaultFormatterFactory(formatterNumberPlate));
            txtCor.setFormatterFactory(new DefaultFormatterFactory(formatterColor));
            txtAno.setFormatterFactory(new DefaultFormatterFactory(formatterYear));
            // txtValorLocacao.setFormatterFactory(new
            // DefaultFormatterFactory(formatterDecimal));
        } catch (Exception ex) {

        }
    }

    public void novoItem() {
        // Limpa oos dados no formulário
        carregarFabricantes();
        carregarModelos(1L);
        txtPlaca.setText("");
        txtCor.setText("");
        chkDisponivel.setSelected(false);
        txtAno.setText("");
        txtValorLocacao.setText("");
    }

    public Boolean validaFormulario() {
        if (cbxFabricante.getSelectedItem() == null
                || cbxModelo.getSelectedItem() == null
                || txtPlaca.getText().trim().length() < 7
                || txtCor.getText().trim().length() < 3
                || txtAno.getText().trim().length() < 4
                || txtValorLocacao.getText().trim().length() < 1) {
            System.out.println(
                    "Fabricante: " + cbxFabricante.getSelectedItem()
                            + " Modelo: " + cbxModelo.getSelectedItem()
                            + " Placa: " + txtPlaca.getText().trim().length()
                            + " Cor: " + txtCor.getText().trim().length()
                            + " Ano: " + txtAno.getText().trim().length()
                            + " Valor: " + txtValorLocacao.getText().trim().length());
            return false;
        }
        return true;
    }

    public void salvar() {

        // Verificar se e carro e novo ou se e uma edicao
        List<Carro> vCarros = new CarroService().consultar();
        if (new CarroService().existeCarro(vCarros, txtPlaca.getText().trim())) {
            System.out.println("Carro já cadastrado");
            atualizarItem();
            return;
        }

        if (!validaFormulario()) {
            System.out.println("Preencha todos os campos");
            return;
        }

        String[] vFabricante = cbxFabricante.getSelectedItem().toString().split(" - ");
        String[] vModelo = cbxModelo.getSelectedItem().toString().split(" - ");

        carroSalvar.setIdFabricante(vFabricante[0].length() > 0 ? Long.parseLong(vFabricante[0]) : 1L);
        carroSalvar.setIdModelo(vModelo[0].length() > 0 ? Long.parseLong(vModelo[0]) : 1L);

        carroSalvar.setPlaca(txtPlaca.getText().trim());
        carroSalvar.setAno(Integer.parseInt(txtAno.getText().trim()));
        carroSalvar.setCor(txtCor.getText().trim());
        carroSalvar.setValorLocacao(Double.parseDouble(txtValorLocacao.getText().trim().replace(",", ".")));
        carroSalvar.setDisponivel(chkDisponivel.isSelected());
        System.out.println(carroSalvar.toString());

        new CarroService().salvar(carroSalvar);
        carregarCarros();
    }

    // Atualizar um item da tabela de carros
    private void atualizarItem() {
        int row = tblListagem.getSelectedRow();
        if (row == -1) {
            System.out.println("Selecione um item para editar");
            return;
        }
        Long id = (Long) tblListagem.getValueAt(row, 0);
        String[] vFabricante = cbxFabricante.getSelectedItem().toString().split(" - ");
        String[] vModelo = cbxModelo.getSelectedItem().toString().split(" - ");

        carroSalvar.setId(id);
        carroSalvar.setIdFabricante(vFabricante[0].length() > 0 ? Long.parseLong(vFabricante[0]) : 1L);
        carroSalvar.setIdModelo(vModelo[0].length() > 0 ? Long.parseLong(vModelo[0]) : 1L);

        carroSalvar.setPlaca(txtPlaca.getText().trim());
        carroSalvar.setAno(Integer.parseInt(txtAno.getText().trim()));
        carroSalvar.setCor(txtCor.getText().trim());
        carroSalvar.setValorLocacao(Double.parseDouble(txtValorLocacao.getText().trim().replace(",", ".")));
        carroSalvar.setDisponivel(chkDisponivel.isSelected());
        System.out.println(carroSalvar.toString());

        new CarroService().atualizar(carroSalvar);
        carregarCarros();
    }

    private void exibirFabricantes() {
        List<Fabricante> vFabricantes = new FabricanteService().consultar();
        List<String> vLista = new ArrayList<>();

        for (Fabricante fabricante : vFabricantes) {
            vLista.add(fabricante.toString());
        }

        ListaFabricanteModelo tela = new ListaFabricanteModelo();
        tela.setLista(vLista);
        tela.setVisible(true);
    }

    // Carrega a tabela de carros
    private void carregarCarros() {
        List<Carro> vCarros = new CarroService().consultar();

        // Definindo os nomes das colunas
        String[] columnNames = {
                "ID",
                "ID Fabricante",
                "ID Modelo",
                "Placa",
                "Cor",
                "Disponível",
                "Ano",
                "Valor Locação"
        };

        // Criando o modelo da tabela
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        // Preenchendo a tabela com os dados dos carros
        for (Carro carro : vCarros) {
            Object[] rowData = { carro.getId(), carro.getIdFabricante(), carro.getIdModelo(), carro.getPlaca(),
                    carro.getCor(), carro.getDisponivel(), carro.getAno(), carro.getValorLocacao() };
            model.addRow(rowData);
        }

        // Definindo o modelo da tabela
        tblListagem.setModel(model);
    }

    // Deletar um item da tabela de carros
    private void deletarItem() {
        int row = tblListagem.getSelectedRow();
        if (row == -1) {
            System.out.println("Selecione um item para deletar");
            return;
        }

        // Pega a primeira coluna do item selecionado
        Long id = (Long) tblListagem.getValueAt(row, 0);
        new CarroService().deletar(id);
        carregarCarros();
    }

    // Editar um item da tabela de carros
    private void editarItem() {
        int row = tblListagem.getSelectedRow();
        if (row == -1) {
            System.out.println("Selecione um item para editar");
            return;
        }

        Long id = (Long) tblListagem.getValueAt(row, 0);
        Long idFabricante = (Long) tblListagem.getValueAt(row, 1);
        Long idModelo = (Long) tblListagem.getValueAt(row, 2);
        Integer indiceIdModelo = 0;
        String placa = (String) tblListagem.getValueAt(row, 3);
        String cor = (String) tblListagem.getValueAt(row, 4);
        Boolean disponivel = (Boolean) tblListagem.getValueAt(row, 5);
        Integer ano = (Integer) tblListagem.getValueAt(row, 6);
        Double valorLocacao = (Double) tblListagem.getValueAt(row, 7);

        // Carregar os dados do carro no formulário
        cbxFabricante.setSelectedIndex(idFabricante.intValue() - 1);
        // System.out.println("ID Fabricante: " + idFabricante.intValue());
        carregarModelos(idFabricante);

        List<Modelo> vModelos = new ModeloService().consultar(idFabricante);
        for (Modelo modelo : vModelos) {
            if (modelo.getId() == idModelo) {
                break;
            }
            indiceIdModelo++;
        }

        cbxModelo.setSelectedIndex(indiceIdModelo);
        // System.out.println("ID Modelo: " + idModelo.intValue());
        // System.out.println("Indice Modelo: " + indiceIdModelo);
        txtPlaca.setText(placa);
        txtCor.setText(cor);
        chkDisponivel.setSelected(disponivel);
        txtAno.setText(ano.toString());
        txtValorLocacao.setText(valorLocacao.toString());

        // Codigo abaixo para debug do item selecionado
        String[] vFabricante = cbxFabricante.getSelectedItem().toString().split(" - ");
        String[] vModelo = cbxModelo.getSelectedItem().toString().split(" - ");

        carroSalvar.setId(id);
        carroSalvar.setIdFabricante(vFabricante[0].length() > 0 ? Long.parseLong(vFabricante[0]) : 1L);
        carroSalvar.setIdModelo(vModelo[0].length() > 0 ? Long.parseLong(vModelo[0]) : 1L);

        carroSalvar.setPlaca(txtPlaca.getText().trim());
        carroSalvar.setAno(Integer.parseInt(txtAno.getText().trim()));
        carroSalvar.setCor(txtCor.getText().trim());
        carroSalvar.setValorLocacao(Double.parseDouble(txtValorLocacao.getText().trim().replace(",", ".")));
        carroSalvar.setDisponivel(chkDisponivel.isSelected());
        System.out.println(carroSalvar.toString());

    }

    // Carrega o combobox com os fabricantes
    private void carregarFabricantes() {
        List<Fabricante> vFabricantes = new FabricanteService().consultar();
        List<String> vLista = new ArrayList<>();

        for (Fabricante fabricante : vFabricantes) {
            vLista.add(fabricante.toString());
        }

        cbxFabricante.setModel(new DefaultComboBoxModel(vLista.toArray()));
    }

    // Carrega o combobox com os modelos
    private void carregarModelos(Long idFabricante) {
        List<Modelo> vModelos = new ModeloService().consultar(idFabricante);
        List<String> vLista = new ArrayList<>();

        for (Modelo modelo : vModelos) {
            vLista.add(modelo.toString());
            //System.out.println(modelo.getNome().toString());
        }

        cbxModelo.setModel(new DefaultComboBoxModel(vLista.toArray()));
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
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btnSalvar = new javax.swing.JButton();
        chkDisponivel = new javax.swing.JCheckBox();
        txtAno = new javax.swing.JFormattedTextField();
        txtValorLocacao = new javax.swing.JFormattedTextField();
        cbxFabricante = new javax.swing.JComboBox<>();
        cbxModelo = new javax.swing.JComboBox<>();
        txtPlaca = new javax.swing.JFormattedTextField();
        txtCor = new javax.swing.JFormattedTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblListagem = new javax.swing.JTable();
        btnEditaItem = new javax.swing.JButton();
        btnNovoItem = new javax.swing.JButton();
        btnDeletarItem = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel1.setText("Fabricante");

        jLabel2.setText("Modelo");

        jLabel3.setText("Placa");

        jLabel4.setText("Cor");

        jLabel6.setText("Valor Locacao");

        jLabel7.setText("Ano");

        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        chkDisponivel.setText("Disponível");

        txtAno.setColumns(4);

        txtValorLocacao.setColumns(12);

        cbxFabricante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxFabricanteActionPerformed(evt);
            }
        });

        txtCor.setToolTipText("");

        tblListagem.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {
                        { null, null, null, null, null, null, null, null },
                        { null, null, null, null, null, null, null, null },
                        { null, null, null, null, null, null, null, null },
                        { null, null, null, null, null, null, null, null }
                },
                new String[] {
                        "ID", "ID Fabricante", "ID Modelo", "Placa", "Cor", "Disponível", "Ano", "Valor Locação"
                }) {
            boolean[] canEdit = new boolean[] {
                    false, false, false, false, false, false, false, false
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(btnSalvar, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                        .addComponent(jLabel4)
                                                        .addGap(10, 10, 10)
                                                        .addComponent(txtCor, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(
                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(chkDisponivel))
                                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 80,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(layout.createSequentialGroup()
                                                        .addComponent(jLabel6)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(txtValorLocacao,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(layout.createSequentialGroup()
                                                        .addGroup(layout
                                                                .createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addGroup(layout.createSequentialGroup()
                                                                        .addComponent(jLabel7)
                                                                        .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(txtAno,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addComponent(jLabel1,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 80,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGap(18, 18, 18)
                                                        .addGroup(layout
                                                                .createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.TRAILING)
                                                                .addComponent(cbxModelo,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 135,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(cbxFabricante,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 135,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING,
                                                                        layout.createSequentialGroup()
                                                                                .addComponent(jLabel3)
                                                                                .addGap(18, 18, 18)
                                                                                .addComponent(txtPlaca,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                        89,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE))))))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 680,
                                                Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(btnNovoItem, javax.swing.GroupLayout.PREFERRED_SIZE, 111,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnEditaItem, javax.swing.GroupLayout.PREFERRED_SIZE, 111,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnDeletarItem, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap()));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel1)
                                                        .addComponent(cbxFabricante,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel2)
                                                        .addComponent(cbxModelo, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel3)
                                                        .addComponent(jLabel7)
                                                        .addComponent(txtAno, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(txtPlaca, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(txtCor, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel4)
                                                        .addComponent(chkDisponivel))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel6)
                                                        .addComponent(txtValorLocacao,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 201,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btnSalvar, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout
                                                .createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addGroup(layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(btnNovoItem,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 48,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(btnEditaItem,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 48,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(btnDeletarItem,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 48,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addContainerGap()));

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
        carregarCarros();
    }// GEN-LAST:event_btnCarregaListaActionPerformed

    private void cbxFabricanteActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_cbxFabricanteActionPerformed
        // TODO add your handling code here:
        String[] vFabricante = cbxFabricante.getSelectedItem().toString().split(" - ");
        carregarModelos(vFabricante[0].length() > 0 ? Long.parseLong(vFabricante[0]) : 1L);

    }// GEN-LAST:event_cbxFabricanteActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {// GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        carregarFabricantes();
        carregarModelos(1L);
        carregarCarros();
    }// GEN-LAST:event_formWindowOpened

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnSalvarActionPerformed
        // Verificar se é um novo carro ou se é uma edição
        salvar();
    }// GEN-LAST:event_btnSalvarActionPerformed

    private void btnFabricantesActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnFabricantesActionPerformed
        // exibirFabricantes();
        // carregarFabricantes();
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
            java.util.logging.Logger.getLogger(CarroCadastroView.class.getName()).log(java.util.logging.Level.SEVERE,
                    null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CarroCadastroView.class.getName()).log(java.util.logging.Level.SEVERE,
                    null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CarroCadastroView.class.getName()).log(java.util.logging.Level.SEVERE,
                    null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CarroCadastroView.class.getName()).log(java.util.logging.Level.SEVERE,
                    null, ex);
        }
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CarroCadastroView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDeletarItem;
    private javax.swing.JButton btnEditaItem;
    private javax.swing.JButton btnNovoItem;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox<String> cbxFabricante;
    private javax.swing.JComboBox<String> cbxModelo;
    private javax.swing.JCheckBox chkDisponivel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblListagem;
    private javax.swing.JFormattedTextField txtAno;
    private javax.swing.JFormattedTextField txtCor;
    private javax.swing.JFormattedTextField txtPlaca;
    private javax.swing.JFormattedTextField txtValorLocacao;
    // End of variables declaration//GEN-END:variables
}
