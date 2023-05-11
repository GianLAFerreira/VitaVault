/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package br.com.vitavault.swing;

import br.com.vitavault.Utils.Utils;
import br.com.vitavault.domain.EnumNivelAcesso;
import br.com.vitavault.domain.EnumTipoMovimentacao;
import br.com.vitavault.domain.MovimentacaoEstoque;
import br.com.vitavault.exceptions.EstoqueException;
import br.com.vitavault.exceptions.MovimentacaoEstoqueException;
import br.com.vitavault.model.Estoque;
import br.com.vitavault.model.Funcionario;
import br.com.vitavault.model.Papel;
import br.com.vitavault.model.Produto;
import br.com.vitavault.validation.MovimentacaoEstoqueValidation;
import br.com.vitavault.validation.impl.MovimentacaoEstoqueValidationImpl;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author lucas
 */
public class HomeMovimentacao extends javax.swing.JFrame {

    private MovimentacaoEstoqueValidation movimentacaoEstoqueValidation;
    private Funcionario funcionarioLogado;
    private Estoque estoqueService;

    /**
     * Creates new form HomeConsulta
     */
    public HomeMovimentacao(Funcionario funcionario, Estoque estoque) {
        initComponents();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - this.getSize().width) / 2;
        int y = (screenSize.height - this.getSize().height) / 2;
        this.setLocation(x, y);

        movimentacaoEstoqueValidation = new MovimentacaoEstoqueValidationImpl();
        funcionarioLogado = funcionario;

        estoqueService = estoque;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        bota_movimentar_produto = new javax.swing.JButton();


        // -> label
        label_produto = new javax.swing.JLabel();
        label_data = new javax.swing.JLabel();
        label_tipo_movimentacao = new javax.swing.JLabel();
        label_motivo = new javax.swing.JLabel();
        label_quantidade = new javax.swing.JLabel();

        //-> campos
        campo_produto = new javax.swing.JComboBox<>();
        campo_data = new javax.swing.JTextField();
        campo_tipo_movimentacao = new javax.swing.JComboBox<>();
        campo_motivo = new javax.swing.JComboBox<>();
        campo_quantidade = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(0, 70, 107));

        jLabel2.setFont(new java.awt.Font("Zapfino", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("V");

        label_produto.setFont(new java.awt.Font("AppleGothic", 0, 24)); // NOI18N
        label_produto.setForeground(new java.awt.Color(255, 255, 255));
        label_produto.setText("Movimentar Estoque");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(label_produto, javax.swing.GroupLayout.PREFERRED_SIZE, 535, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(246, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addContainerGap(39, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label_produto, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(27, 27, 27))
        );

        jLabel3.setText("Selecione o produto:");

        for (Map.Entry<UUID, Produto> entry : Produto.getProdutos().entrySet()) {
            campo_produto.addItem(entry.getValue());
        }

        campo_produto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxProdutoActionPerformed(evt);
            }
        });

        label_tipo_movimentacao.setText("Tipo de movimentação:");

        campo_tipo_movimentacao.setModel(new javax.swing.DefaultComboBoxModel<>(new EnumTipoMovimentacao[]{EnumTipoMovimentacao.ENTRADA, EnumTipoMovimentacao.SAIDA}));
        campo_tipo_movimentacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        label_motivo.setText("Motivo da movimentação:");

        campo_motivo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Compra de material", "Venda", "Cortesia", "Doação"}));
        campo_motivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });

        label_quantidade.setText("Quantidade:");

        campo_quantidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        bota_movimentar_produto.setText("Movimentar produto");
        bota_movimentar_produto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao_movimentar_produtoActionPerformed(evt);
            }
        });

        campo_data.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        try {
            MaskFormatter dateFormatter = new MaskFormatter("##/##/####");
            dateFormatter.setPlaceholderCharacter('_');
            campo_data = new JFormattedTextField(dateFormatter);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        label_data.setText("Data");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(180, 180, 180)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                .addComponent(campo_tipo_movimentacao, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(campo_produto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(label_tipo_movimentacao)
                                                        .addComponent(label_quantidade)
                                                        .addComponent(campo_quantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(174, 174, 174)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(label_motivo)
                                                        .addComponent(campo_motivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(campo_data, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(label_data)))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(339, 339, 339)
                                                .addComponent(bota_movimentar_produto)))
                                .addContainerGap(232, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(label_data)
                                        .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(campo_data, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(campo_produto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(label_motivo)
                                        .addComponent(label_tipo_movimentacao))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(campo_motivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(campo_tipo_movimentacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(label_quantidade)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(campo_quantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                                .addComponent(bota_movimentar_produto)
                                .addGap(139, 139, 139))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBoxProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed

    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void botao_movimentar_produtoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Produto produto = (Produto) campo_produto.getSelectedItem();
        String data = campo_data.getText().trim();
        String motivo = campo_motivo.getSelectedItem().toString();
        String quantidade = campo_quantidade.getText().trim();
        EnumTipoMovimentacao tipoMovimentacao = (EnumTipoMovimentacao) campo_tipo_movimentacao.getSelectedItem();


        List<String> mensagens = movimentacaoEstoqueValidation.validar(data, quantidade);

        if (!mensagens.isEmpty()) {
            alertaMensagens(mensagens);
            return;
        }


        LocalDate dataFormatada = Utils.formatarData(data);
        Long quantidadeFormatada = Utils.formatarQuantidade(quantidade);
        MovimentacaoEstoque movimentacaoEstoque
                = new MovimentacaoEstoque(produto,
                funcionarioLogado,
                criarPapel(),
                dataFormatada,
                tipoMovimentacao,
                quantidadeFormatada,
                estoqueService);

        try {
            movimentacaoEstoque.movimentarEstoque(movimentacaoEstoque.getItem(), movimentacaoEstoque.getQuantidade(), movimentacaoEstoque.getTipoMovimentacao());
            JOptionPane.showMessageDialog(this, "Estoque movimentado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
        } catch (MovimentacaoEstoqueException | EstoqueException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao movimentar o estoque: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(Tela_Cadastrar_Conta.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private static Papel criarPapel() {
        return new Papel(UUID.randomUUID(), EnumNivelAcesso.DIGITADOR, "Teste");
    }

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bota_movimentar_produto;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;

    // -> label dos campos
    private javax.swing.JLabel label_produto;
    private javax.swing.JLabel label_data;
    private javax.swing.JLabel label_tipo_movimentacao;
    private javax.swing.JLabel label_motivo;
    private javax.swing.JLabel label_quantidade;

    // -> label dos produtos
    private javax.swing.JComboBox<Produto> campo_produto;
    private javax.swing.JTextField campo_data;
    private javax.swing.JComboBox<EnumTipoMovimentacao> campo_tipo_movimentacao;
    private javax.swing.JComboBox<String> campo_motivo;
    private javax.swing.JTextField campo_quantidade;

    // End of variables declaration//GEN-END:variables

    private void alertaMensagens(List<String> mensagens) {
        for (String mensagem : mensagens) {
            JOptionPane.showMessageDialog(this, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
