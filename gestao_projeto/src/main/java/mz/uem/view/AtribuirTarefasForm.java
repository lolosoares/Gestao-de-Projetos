package mz.uem.view;

import com.formdev.flatlaf.FlatDarkLaf;
import com.toedter.calendar.JDateChooser;

import mz.uem.controller.TarefaController;
import mz.uem.model.model.Estado;
import mz.uem.model.model.Projeto;
import mz.uem.model.model.Responsavel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class AtribuirTarefasForm extends JFrame {
    // Componentes da Interface
    private JComboBox<Projeto> projetoComboBox;
    private JComboBox<Responsavel> responsavelComboBox;
    private JTextField nomeTarefaField;
    private JTextArea descricaoTarefaArea;
    private JDateChooser dataInicioChooser;
    private JDateChooser dataFimChooser;
    private JComboBox<Estado> estadoComboBox;
    private JButton btnAtribuir, btnGerarRelatorio;
    private JLabel lblProjeto, lblResponsavel, lblNome, lblDescricao, lblDataInicio, lblDataFim, lblEstado;

    private TarefaController tarefaController=new TarefaController();
    private List<Responsavel> responsaveis;
    private List<Projeto> projetos;

    public AtribuirTarefasForm() throws SQLException {
        // Aplica o tema FlatLaf para um design moderno
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        // lista de responsáveis e projetos
        responsaveis=tarefaController.getResponsavel();
        projetos=tarefaController.getProjetos();

        // Define o layout e configurações gerais da tela
        setTitle("Atribuição de Tarefas");
        setSize(900, 700); // Aumentei o tamanho para acomodar todos os campos
        setLocationRelativeTo(null); // Para centralizar a janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Configurações de GridBagConstraints para centralizar os componentes
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Define fontes e tamanhos para um layout mais moderno
        Font labelFont = new Font("Arial", Font.PLAIN, 16);
        Font fieldFont = new Font("Arial", Font.PLAIN, 18);

        // Defina a imagem de fundo
        ImageIcon backgroundImage = new ImageIcon("/Users/antoniosoares/Documents/POO2Code/gestao_projeto/src/main/resources/images/Gestao-de-Projetos.png"); //O caminho da imagem
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Image backgroundImageScaled = backgroundImage.getImage().getScaledInstance(screenSize.width, screenSize.height, Image.SCALE_SMOOTH);
        JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImageScaled));
        backgroundLabel.setLayout(new GridBagLayout()); // Usando GridBagLayout para o fundo
        backgroundLabel.setSize(getSize()); // Define o tamanho do JLabel para o tamanho da janela
        backgroundLabel.setPreferredSize(new Dimension(getWidth(), getHeight()));
        setContentPane(backgroundLabel); // imagem como fundo da janela
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                backgroundLabel.setPreferredSize(new Dimension(getWidth(), getHeight()));
                backgroundLabel.revalidate();
            }
        });
        
        // Label e combo box para selecionar Projeto
        lblProjeto = new JLabel("Selecionar Projeto:");
        lblProjeto.setForeground(Color.WHITE);
        lblProjeto.setFont(labelFont);
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST; // Alinha à esquerda
        backgroundLabel.add(lblProjeto, gbc);

        projetoComboBox = new JComboBox<>(projetos.toArray(new Projeto[0]));
        projetoComboBox.setFont(fieldFont);
        projetoComboBox.setBackground(Color.WHITE);
        projetoComboBox.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        projetoComboBox.setPreferredSize(new Dimension(400, 30)); // Definindo um tamanho mais adequado
        gbc.gridx = 1;
        backgroundLabel.add(projetoComboBox, gbc);

        // Label e combo box para Responsável
        lblResponsavel = new JLabel("Responsável:");
        lblResponsavel.setForeground(Color.WHITE);
        lblResponsavel.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 1;
        backgroundLabel.add(lblResponsavel, gbc);

        responsavelComboBox = new JComboBox<>(responsaveis.toArray(new Responsavel[0]));
        responsavelComboBox.setFont(fieldFont);
        responsavelComboBox.setBackground(Color.WHITE);
        responsavelComboBox.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        responsavelComboBox.setPreferredSize(new Dimension(400, 30)); // Tamanho ajustado
        gbc.gridx = 1;
        backgroundLabel.add(responsavelComboBox, gbc);

        // Label e campo para Nome da Tarefa
        lblNome = new JLabel("Nome da Tarefa:");
        lblNome.setForeground(Color.WHITE);
        lblNome.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 2;
        backgroundLabel.add(lblNome, gbc);

        nomeTarefaField = new JTextField();
        nomeTarefaField.setFont(fieldFont);
        nomeTarefaField.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        nomeTarefaField.setPreferredSize(new Dimension(400, 30)); // Tamanho ajustado
        gbc.gridx = 1;
        backgroundLabel.add(nomeTarefaField, gbc);

        // Label e área para Descrição da Tarefa
        lblDescricao = new JLabel("Descrição:");
        lblDescricao.setForeground(Color.WHITE);
        lblDescricao.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 3;
        backgroundLabel.add(lblDescricao, gbc);

        descricaoTarefaArea = new JTextArea();
        descricaoTarefaArea.setFont(fieldFont);
        descricaoTarefaArea.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        descricaoTarefaArea.setLineWrap(true);
        descricaoTarefaArea.setWrapStyleWord(true);
        descricaoTarefaArea.setPreferredSize(new Dimension(400, 100)); // Tamanho ajustado
        gbc.gridx = 1;
        backgroundLabel.add(new JScrollPane(descricaoTarefaArea), gbc);

        // Label e campo para Data de Início
        lblDataInicio = new JLabel("Data de Início:");
        lblDataInicio.setForeground(Color.WHITE);
        lblDataInicio.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 4;
        backgroundLabel.add(lblDataInicio, gbc);

        dataInicioChooser = new JDateChooser();
        dataInicioChooser.setDateFormatString("dd/MM/yyyy");
        dataInicioChooser.setDate(new java.util.Date());
        dataInicioChooser.setFont(fieldFont);
        dataInicioChooser.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        dataInicioChooser.setPreferredSize(new Dimension(400, 30)); // Tamanho ajustado
        gbc.gridx = 1;
        backgroundLabel.add(dataInicioChooser, gbc);

        // Label e campo para Data de Fim
        lblDataFim = new JLabel("Data de Fim:");
        lblDataFim.setForeground(Color.WHITE);
        lblDataFim.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 5;
        backgroundLabel.add(lblDataFim, gbc);

        dataFimChooser = new JDateChooser();
        dataFimChooser.setDateFormatString("dd/MM/yyyy");
        dataFimChooser.setDate(new java.util.Date());
        dataFimChooser.setFont(fieldFont);
        dataFimChooser.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        dataFimChooser.setPreferredSize(new Dimension(400, 30)); // Tamanho ajustado
        gbc.gridx = 1;
        backgroundLabel.add(dataFimChooser, gbc);

        // Label e combo box para Estado
        lblEstado = new JLabel("Estado:");
        lblEstado.setForeground(Color.WHITE);
        lblEstado.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 7;
        backgroundLabel.add(lblEstado, gbc);

        estadoComboBox = new JComboBox<>(Estado.values());
        estadoComboBox.setFont(fieldFont);
        estadoComboBox.setBackground(Color.WHITE);
        estadoComboBox.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        estadoComboBox.setPreferredSize(new Dimension(400, 30)); // Tamanho ajustado
        gbc.gridx = 1;
        backgroundLabel.add(estadoComboBox, gbc);

  // Botão Atribuir Tarefa
btnAtribuir = new JButton("Atribuir Tarefa");
btnAtribuir.setFont(new Font("Arial", Font.BOLD, 18));
btnAtribuir.setBackground(new Color(30, 144, 255));
btnAtribuir.setForeground(Color.WHITE);
btnAtribuir.setPreferredSize(new Dimension(200, 40));

//acao do botao
btnAtribuir.addActionListener(new ActionListener() {

    @Override
    public void actionPerformed(ActionEvent e) {
        Projeto selecionadoProjeto = (Projeto) projetoComboBox.getSelectedItem();
        Responsavel selecionado = (Responsavel) responsavelComboBox.getSelectedItem();

// Obter as datas
java.util.Date begin = dataInicioChooser.getDate();
java.util.Date end = dataFimChooser.getDate();

// Verificar se as datas são válidas
if (begin == null || end == null || end.before(begin)) {
    JOptionPane.showMessageDialog(null, "Por favor, selecione datas válidas. A data de fim deve ser posterior à data de início.", 
                                  "Erro", JOptionPane.ERROR_MESSAGE);
    return; // Interrompe a execução
}

// Converter datas
LocalDate dataInicio = begin.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
LocalDate dataFim = end.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();

// Validar outros campos
if (selecionadoProjeto == null || selecionado == null || 
    nomeTarefaField.getText().trim().isEmpty() || descricaoTarefaArea.getText().trim().isEmpty()) {
    JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos obrigatórios.", 
                                  "Erro", JOptionPane.ERROR_MESSAGE);
    return; // Interrompe a execução
}

// Obter valores dos campos
int projetoId = selecionadoProjeto.getId();
int responsavel = selecionado.getId();
String nome = nomeTarefaField.getText().trim();
String descricao = descricaoTarefaArea.getText().trim();
Estado estado = (Estado) estadoComboBox.getSelectedItem();

// Salvar a tarefa
tarefaController.salvarTarefa(projetoId, nome, descricao, dataInicio, dataFim, responsavel, estado);

// Mostrar mensagem de sucesso
JOptionPane.showMessageDialog(null, "Tarefa salva com sucesso!", 
                              "Sucesso", JOptionPane.INFORMATION_MESSAGE);

// Limpar os campos
nomeTarefaField.setText("");
descricaoTarefaArea.setText("");
dataInicioChooser.setDate(null);
dataFimChooser.setDate(null);

    }
    
});

// Botão Gerar Relatório
btnGerarRelatorio = new JButton("Gerar Relatório");
btnGerarRelatorio.setFont(new Font("Arial", Font.BOLD, 18));
btnGerarRelatorio.setBackground(new Color(34, 139, 34));
btnGerarRelatorio.setForeground(Color.WHITE);
btnGerarRelatorio.setPreferredSize(new Dimension(200, 40));
btnGerarRelatorio.addActionListener(e -> {
    try {
        mostrarFormularioRelatorio();
    } catch (SQLException e1) {
       
        e1.printStackTrace();
    }}); 

// Botão Voltar
JButton btnVoltar = new JButton("Voltar");
btnVoltar.setFont(new Font("Arial", Font.BOLD, 18));
btnVoltar.setBackground(new Color(255, 69, 0));  // Cor opcional
btnVoltar.setForeground(Color.WHITE);
btnVoltar.setPreferredSize(new Dimension(200, 40));

// Definindo os botões lado a lado na primeira linha
GridBagConstraints gbcButtons = new GridBagConstraints();

// Linha 1: Botões Atribuir e Gerar Relatório
gbcButtons.gridy = 8; // Linha dos botões

// Botão Atribuir
gbcButtons.gridx = 0; // Coluna 0
gbcButtons.gridwidth = 1; // Um botão por coluna
gbcButtons.insets = new Insets(10, 10, 10, 10); // Espaçamento entre os botões
backgroundLabel.add(btnAtribuir, gbcButtons);

// Botão Gerar Relatório
gbcButtons.gridx = 1; // Coluna 1
backgroundLabel.add(btnGerarRelatorio, gbcButtons);

// Linha 2: Botão Voltar (centralizado)
gbcButtons.gridx = 0; // Coluna 0
gbcButtons.gridy = 9; // Linha 9 (abaixo da linha 8)
gbcButtons.gridwidth = 2; // O botão "Voltar" ocupa 2 colunas
gbcButtons.anchor = GridBagConstraints.CENTER; // Centraliza o botão
backgroundLabel.add(btnVoltar, gbcButtons);

// Ação para o botão Voltar (opcional)
btnVoltar.addActionListener(e -> {
    // Adicione o comportamento do botão aqui, como voltar para a tela anterior ou fechar a janela
    System.out.println("Voltar clicado!");
});

// Texto © 2024 Robson Soares no rodapé
JLabel footerLabel = new JLabel("© 2024 Robson Soares");
footerLabel.setForeground(Color.WHITE); // Cor do texto
footerLabel.setFont(new Font("Arial", Font.PLAIN, 12)); // Fonte e tamanho
footerLabel.setHorizontalAlignment(SwingConstants.CENTER); // Alinha no centro

// Definindo a posição do rodapé na grade (coluna inteira e linha final)
GridBagConstraints gb = new GridBagConstraints();
gb.gridx = 0; // Posição inicial na grade (coluna 0)
gb.gridy = 10; // Posição do rodapé na grade (linha final ou abaixo dos outros componentes)
gb.gridwidth = 2; // Se necessário, ocupe duas colunas
gb.anchor = GridBagConstraints.CENTER; // Centraliza o rodapé
backgroundLabel.add(footerLabel, gb);

// Atualize o layout para garantir que os componentes sejam reposicionados
backgroundLabel.revalidate();
backgroundLabel.repaint();

setExtendedState(JFrame.MAXIMIZED_BOTH);
        // Exibe a janela
        setVisible(true);
    }

    public void mostrarFormularioRelatorio() throws SQLException {
        // Remover componentes existentes
        getContentPane().removeAll();
        
        // Configuração do painel principal com background
        ImageIcon backgroundImage = new ImageIcon("/Users/antoniosoares/Documents/POO2Code/gestao_projeto/src/main/resources/images/Gestao-de-Projetos.png"); // Ajuste o caminho da imagem
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Image backgroundImageScaled = backgroundImage.getImage().getScaledInstance(screenSize.width, screenSize.height, Image.SCALE_SMOOTH);
        JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImageScaled));
        backgroundLabel.setLayout(new GridBagLayout());
        setContentPane(backgroundLabel);
        revalidate();
        repaint();
    
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
    
        projetos = tarefaController.getProjetos();
        Font labelFont = new Font("Arial", Font.PLAIN, 16);
        Font fieldFont = new Font("Arial", Font.PLAIN, 18);
    
        // Label e ComboBox para Projeto
        JLabel projetoLabel = new JLabel("Selecione o Projeto:");
        projetoLabel.setForeground(Color.WHITE);
        projetoLabel.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 0;
        backgroundLabel.add(projetoLabel, gbc);
    
        JComboBox<Projeto> projetoComboBox = new JComboBox<>(projetos.toArray(new Projeto[0]));
        projetoComboBox.setFont(fieldFont);
        projetoComboBox.setBackground(Color.WHITE);
        projetoComboBox.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        projetoComboBox.setPreferredSize(new Dimension(400, 30));
        gbc.gridx = 1;
        backgroundLabel.add(projetoComboBox, gbc);
    
        // Label e TextField para Nome do Relatório
        JLabel nomePdfLabel = new JLabel("Nome do Relatório:");
        nomePdfLabel.setForeground(Color.WHITE);
        nomePdfLabel.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 1;
        backgroundLabel.add(nomePdfLabel, gbc);
    
        JTextField nomePdfField = new JTextField();
        nomePdfField.setFont(fieldFont);
        nomePdfField.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        nomePdfField.setPreferredSize(new Dimension(400, 30));
        gbc.gridx = 1;
        backgroundLabel.add(nomePdfField, gbc);
    
        // Botão Gerar Relatório
        JButton gerarButton = new JButton("Gerar Relatório");
        gerarButton.setFont(new Font("Arial", Font.BOLD, 18));
        gerarButton.setBackground(new Color(34, 139, 34));
        gerarButton.setForeground(Color.WHITE);
        gerarButton.setPreferredSize(new Dimension(200, 40));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        backgroundLabel.add(gerarButton, gbc);
    
        // Botão Voltar
        JButton voltarButton = new JButton("Voltar");
        voltarButton.setFont(new Font("Arial", Font.BOLD, 18));
        voltarButton.setBackground(new Color(255, 69, 0));
        voltarButton.setForeground(Color.WHITE);
        voltarButton.setPreferredSize(new Dimension(200, 40));
        gbc.gridy = 3;
        backgroundLabel.add(voltarButton, gbc);
    
        voltarButton.addActionListener(e -> {
            try {
                backgroundLabel.removeAll();
                getContentPane().removeAll();
                revalidate();
                repaint();
                new AtribuirTarefasForm();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });
    
        gerarButton.addActionListener(e -> {
            Projeto projeto = (Projeto) projetoComboBox.getSelectedItem();
            String nomePdf = nomePdfField.getText();
            if (!nomePdf.isEmpty()) {
                tarefaController.gerarRelatorio(projeto, nomePdf);
                JOptionPane.showMessageDialog(null, "Relatório gerado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                try {
                    new AtribuirTarefasForm();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(null, "O nome do relatório não pode ser vazio!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
    
        // Adicionar o backgroundLabel ao frame
        setContentPane(backgroundLabel);
        revalidate();
        repaint();
    }
    
}
