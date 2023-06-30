package br.com.vitavault.view;

import br.com.vitavault.validation.CadastroProdutoValidation;
import br.com.vitavault.validation.impl.CadastroProdutoValidationImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class CadastroProdutoView extends javax.swing.JFrame {

    private CadastroProdutoValidation cadastroProdutoValidation;

    public CadastroProdutoView() {
        initComponents();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - this.getSize().width) / 2;
        int y = (screenSize.height - this.getSize().height) / 2;
        this.setLocation(x, y);
        cadastroProdutoValidation = new CadastroProdutoValidationImpl();
    }

    public void adicionarAcaoAdicionarProduto(ActionListener acao) {
        botaoAdicionarProduto.addActionListener(acao);
    }

    public String getNomeProduto() {
        return campoNomeProduto.getText().trim();
    }

    public String getDescricaoProduto() {
        return campoDescricaoProduto.getText().trim();
    }

    public String getCodigoProduto() {
        return campoCodigoProduto.getText().trim();
    }

    public String getPrecoProduto() {
        return campoPrecoProduto.getText().trim();
    }

    public String getCategoriaProduto() {
        return campoCategoriaProduto.getText().trim();
    }

    public String getDataValidadeProduto() {
        return campoDataValidadeProduto.getText().trim();
    }

    public void exibe() {
        setVisible(true);
    }

    public void resetarCampos() {
        campoNomeProduto.setText("");
        campoDescricaoProduto.setText("");
        campoCodigoProduto.setText("");
        campoPrecoProduto.setText("");
        campoCategoriaProduto.setText("");
        campoDataValidadeProduto.setText("");
    }

    public void alertaMensagens(List<String> mensagens) {
        for (String mensagem : mensagens) {
            JOptionPane.showMessageDialog(this, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
        }
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
        jLabel1 = new javax.swing.JLabel();
        campoNomeProduto = new javax.swing.JTextField();
        labelNomeProduto = new javax.swing.JLabel();
        labelDataValidadeProduto = new javax.swing.JLabel();
        campoDataValidadeProduto = new javax.swing.JTextField();
        botaoAdicionarProduto = new javax.swing.JButton();
        campoCodigoProduto = new javax.swing.JTextField();
        labelCodigoProduto = new javax.swing.JLabel();
        campoDescricaoProduto = new javax.swing.JTextField();
        labelPrecoProduto = new javax.swing.JLabel();
        labelDescricaoProduto = new javax.swing.JLabel();
        labelCategoriaProduto = new javax.swing.JLabel();
        campoCategoriaProduto = new javax.swing.JTextField();
        campoPrecoProduto = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(0, 70, 107));

        jLabel2.setFont(new java.awt.Font("Zapfino", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("V");

        jLabel1.setFont(new java.awt.Font("AppleGothic", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Cadastro de Produto");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 535, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(246, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addContainerGap(39, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(27, 27, 27))
        );

        labelNomeProduto.setText("Nome do Produto");
        labelDataValidadeProduto.setText("Data de Validade (opcional)");
        campoDataValidadeProduto.setForeground(new java.awt.Color(204, 204, 204));
        campoDataValidadeProduto.setText("dd/mm/aa");

        botaoAdicionarProduto.setText("Adicionar Produto");
        labelCodigoProduto.setText("Codigo do Produto");
        labelPrecoProduto.setText("Preço do Produto");
        labelDescricaoProduto.setText("Descrição do Produto");
        labelCategoriaProduto.setText("Categoria do Produto");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(171, 171, 171)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(labelCodigoProduto)
                                        .addComponent(labelNomeProduto)
                                        .addComponent(campoNomeProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(campoCodigoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(labelCategoriaProduto)
                                        .addComponent(campoCategoriaProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(campoDescricaoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(labelPrecoProduto)
                                        .addComponent(labelDescricaoProduto)
                                        .addComponent(campoPrecoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(labelDataValidadeProduto)
                                        .addComponent(campoDataValidadeProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(207, 207, 207))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(353, 353, 353)
                                .addComponent(botaoAdicionarProduto)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(62, 62, 62)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(labelNomeProduto)
                                        .addComponent(labelDescricaoProduto))
                                .addGap(1, 1, 1)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(campoNomeProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(campoDescricaoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(labelCodigoProduto)
                                        .addComponent(labelPrecoProduto))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(campoCodigoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(campoPrecoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(labelCategoriaProduto)
                                        .addComponent(labelDataValidadeProduto))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(campoCategoriaProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(campoDataValidadeProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                                .addComponent(botaoAdicionarProduto)
                                .addGap(118, 118, 118))
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoAdicionarProduto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel labelNomeProduto;
    private javax.swing.JLabel labelDescricaoProduto;
    private javax.swing.JLabel labelCodigoProduto;
    private javax.swing.JLabel labelPrecoProduto;
    private javax.swing.JLabel labelCategoriaProduto;
    private javax.swing.JLabel labelDataValidadeProduto;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField campoNomeProduto;
    private javax.swing.JTextField campoDescricaoProduto;
    private javax.swing.JTextField campoCodigoProduto;
    private javax.swing.JTextField campoPrecoProduto;
    private javax.swing.JTextField campoCategoriaProduto;
    private javax.swing.JTextField campoDataValidadeProduto;
    // End of variables declaration//GEN-END:variables

}
