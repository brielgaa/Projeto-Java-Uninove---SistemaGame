package jogo;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SistemaGameUI extends JFrame implements Serializable {
	
	private static final long serialVersionUID = 1L;

    private final List<Cliente> clientes = new ArrayList<>();
    private final List<Jogo> jogos = new ArrayList<>();
    private final List<Venda> vendas = new ArrayList<>();

    private final CardLayout card = new CardLayout();
    private final JPanel content = new JPanel(card);

    private static final String CLIENTES_FILE = "clientes.dat";
    private static final String JOGOS_FILE = "jogos.dat";
    private static final String VENDAS_FILE = "vendas.dat";

    public SistemaGameUI() {
        super("SistemaGame – Gerenciador de Jogos e Clientes");

        configurarTemaEscuro();

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                confirmarSaida();
            }
        });

        setSize(1100, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel side = new JPanel(new GridLayout(0, 1, 10, 10));
        side.setBorder(new EmptyBorder(20, 20, 20, 20));
        side.setBackground(new Color(30, 30, 30));

        JButton btnHome = criarBotaoMenu("Início");
        JButton btnClientes = criarBotaoMenu("Clientes");
        JButton btnJogos = criarBotaoMenu("Jogos");
        JButton btnVendas = criarBotaoMenu("Vendas");
        JButton btnHistorico = criarBotaoMenu("Histórico");
        JButton btnSaldo = criarBotaoMenu("Saldo");
        JButton btnSair = criarBotaoMenu("Sair");
        btnSair.setForeground(Color.WHITE);
        btnSair.setBackground(new Color(45, 45, 45));
        btnSair.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btnSair.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnSair.setBackground(new Color(170, 0, 0));
                btnSair.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnSair.setBackground(new Color(45, 45, 45));
                btnSair.setForeground(Color.WHITE);
            }
        });
        

        side.add(btnHome);
        side.add(btnClientes);
        side.add(btnJogos);
        side.add(btnVendas);
        side.add(btnHistorico);
        side.add(btnSaldo);
        side.add(btnSair);

        add(side, BorderLayout.WEST);
        add(content, BorderLayout.CENTER);

        JPanel telaHome = buildHome();
        ClientesPanel telaClientes = new ClientesPanel();
        JogosPanel telaJogos = new JogosPanel();
        VendasPanel telaVendas = new VendasPanel();
        HistoricoPanel telaHistorico = new HistoricoPanel();
        SaldoPanel telaSaldo = new SaldoPanel();

        content.add(telaHome, "HOME");
        content.add(telaClientes, "CLIENTES");
        content.add(telaJogos, "JOGOS");
        content.add(telaVendas, "VENDAS");
        content.add(telaHistorico, "HISTORICO");
        content.add(telaSaldo, "SALDO");

        btnHome.addActionListener(e -> card.show(content, "HOME"));
        btnClientes.addActionListener(e -> { telaClientes.refresh(); card.show(content, "CLIENTES"); });
        btnJogos.addActionListener(e -> { telaJogos.refresh(); card.show(content, "JOGOS"); });
        btnVendas.addActionListener(e -> { telaVendas.refresh(); card.show(content, "VENDAS"); });
        btnHistorico.addActionListener(e -> { telaHistorico.refresh(); card.show(content, "HISTORICO"); });
        btnSaldo.addActionListener(e -> { telaSaldo.refresh(); card.show(content, "SALDO"); });
        btnSair.addActionListener(e -> confirmarSaida());

        carregarDados();
        card.show(content, "HOME");
    }

    private JButton criarBotaoMenu(String texto) {
        JButton btn = new JButton(texto);
        btn.setBackground(new Color(45, 45, 45));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        btn.setBorder(BorderFactory.createLineBorder(new Color(0, 122, 204)));
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btn.setBackground(new Color(0, 122, 204)); }
            public void mouseExited(MouseEvent e) { btn.setBackground(new Color(45, 45, 45)); }
        });
        return btn;
    }

    private JPanel buildHome() {
        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(new Color(30, 30, 30));
        JLabel title = new JLabel("Bem-vindo ao SistemaGame", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 26));
        JLabel subtitle = new JLabel("Escolha uma opção no menu à esquerda", SwingConstants.CENTER);
        subtitle.setForeground(new Color(180, 180, 180));
        p.add(title, BorderLayout.NORTH);
        p.add(subtitle, BorderLayout.CENTER);
        return p;
    }

    private void configurarTemaEscuro() {
    	UIManager.put("Panel.background", new Color(30, 30, 30));
        UIManager.put("Table.background", new Color(40, 40, 40));
        UIManager.put("Table.foreground", Color.WHITE);
        UIManager.put("Table.gridColor", new Color(80, 80, 80));
        UIManager.put("TableHeader.background", new Color(45, 45, 45));
        UIManager.put("TableHeader.foreground", Color.WHITE);
        UIManager.put("TextField.background", new Color(45, 45, 45));
        UIManager.put("TextField.foreground", Color.WHITE);
        UIManager.put("Label.foreground", Color.WHITE);
        UIManager.put("Button.background", new Color(45, 45, 45));
        UIManager.put("Button.foreground", Color.WHITE);
        UIManager.put("ComboBox.background", new Color(45, 45, 45));
        UIManager.put("ComboBox.foreground", Color.WHITE);
        UIManager.put("OptionPane.background", new Color(30, 30, 30));
        UIManager.put("Panel.background", new Color(30, 30, 30));
        UIManager.put("OptionPane.messageForeground", Color.WHITE);
    }
    
    private class ClientesPanel extends JPanel {
    	private static final long serialVersionUID = 1L;
        private final DefaultTableModel model = new DefaultTableModel(
                new Object[]{"ID", "Nome", "Email", "Saldo", "Tipo"}, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };

        private final JTable table = new JTable(model);
        private final JTextField tfNome = new JTextField();
        private final JTextField tfEmail = new JTextField();
        private final JTextField tfSaldo = new JTextField();
        private final JComboBox<String> cbTipo = new JComboBox<>(new String[]{"Regular", "Premium"});

        ClientesPanel() {
            setLayout(new BorderLayout(12, 12));
            setBorder(new EmptyBorder(20, 20, 20, 20));
            setBackground(new Color(30, 30, 30));

            JPanel form = new JPanel(new GridLayout(0, 2, 8, 8));
            form.setBackground(new Color(30, 30, 30));

            form.add(new JLabel("Nome:"));
            form.add(tfNome);
            form.add(new JLabel("Email:"));
            form.add(tfEmail);
            form.add(new JLabel("Saldo (R$):"));
            form.add(tfSaldo);
            form.add(new JLabel("Tipo:"));
            form.add(cbTipo);

            JButton btnSalvar = criarBotaoAcao("Salvar");
            JButton btnRemover = criarBotaoAcao("Remover");
            JButton btnLimpar = criarBotaoAcao("Limpar");

            JPanel actions = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            actions.setBackground(new Color(30, 30, 30));
            actions.add(btnRemover);
            actions.add(btnLimpar);
            actions.add(btnSalvar);

            JScrollPane scroll = new JScrollPane(table);
            scroll.getViewport().setBackground(new Color(40, 40, 40));
            table.setSelectionBackground(new Color(0, 122, 204));
            table.setRowHeight(25);
            ((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer())
                    .setHorizontalAlignment(SwingConstants.CENTER);

            add(form, BorderLayout.NORTH);
            add(scroll, BorderLayout.CENTER);
            add(actions, BorderLayout.SOUTH);

            btnSalvar.addActionListener(this::onSalvar);
            btnLimpar.addActionListener(e -> clearForm());
            btnRemover.addActionListener(e -> onRemover());

            table.getSelectionModel().addListSelectionListener(e -> {
                int i = table.getSelectedRow();
                if (i >= 0 && i < clientes.size()) {
                    Cliente c = clientes.get(i);
                    tfNome.setText(c.getNome());
                    tfEmail.setText(c.getEmail());
                    tfSaldo.setText(String.valueOf(c.getSaldo()));
                    cbTipo.setSelectedItem(c instanceof ClientePremium ? "Premium" : "Regular");
                }
            });
        }

        private JButton criarBotaoAcao(String texto) {
            JButton btn = new JButton(texto);
            btn.setBackground(new Color(45, 45, 45));
            btn.setForeground(Color.WHITE);
            btn.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            btn.setFocusPainted(false);
            btn.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent e) { btn.setBackground(new Color(0, 122, 204)); }
                public void mouseExited(MouseEvent e) { btn.setBackground(new Color(45, 45, 45)); }
            });
            return btn;
        }

        private void onSalvar(ActionEvent e) {
            String nome = tfNome.getText().trim();
            String email = tfEmail.getText().trim();
            String sSaldo = tfSaldo.getText().trim();
            String tipo = (String) cbTipo.getSelectedItem();

            if (nome.isEmpty() || email.isEmpty() || sSaldo.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos.");
                return;
            }

            double saldo;
            try { saldo = Double.parseDouble(sSaldo.replace(",", ".")); }
            catch (Exception ex) { JOptionPane.showMessageDialog(this, "Saldo inválido."); return; }

            int selected = table.getSelectedRow();
            if (selected >= 0 && selected < clientes.size()) {
                Cliente c = clientes.get(selected);
                c.setNome(nome);
                c.setEmail(email);
                c.setSaldo(saldo);
            } else {
                Cliente novo = "Premium".equals(tipo)
                        ? new ClientePremium(nome, email, saldo)
                        : new ClienteRegular(nome, email, saldo);
                clientes.add(novo);
            }

            salvarDados();
            refresh();
            clearForm();
        }

        private void onRemover() {
            int i = table.getSelectedRow();
            if (i >= 0 && i < clientes.size()) {
                clientes.remove(i);
                salvarDados();
                refresh();
                clearForm();
            }
        }

        void refresh() {
            model.setRowCount(0);
            for (Cliente c : clientes) {
                model.addRow(new Object[]{
                        c.getIdUsuario(),
                        c.getNome(),
                        c.getEmail(),
                        String.format("%.2f", c.getSaldo()),
                        c.getTipoCliente()
                });
            }
        }

        void clearForm() {
            tfNome.setText("");
            tfEmail.setText("");
            tfSaldo.setText("");
            cbTipo.setSelectedIndex(0);
            table.clearSelection();
        }
    }

    private class JogosPanel extends JPanel {
    	private static final long serialVersionUID = 1L;
        private final DefaultTableModel model = new DefaultTableModel(
                new Object[]{"ID", "Título", "Gênero", "Ano", "Preço", "Status"}, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };

        private final JTable table = new JTable(model);
        private final JTextField tfTitulo = new JTextField();
        private final JTextField tfGenero = new JTextField();
        private final JTextField tfAno = new JTextField();
        private final JTextField tfPreco = new JTextField();
        private final JCheckBox chkDisponivel = new JCheckBox("Disponível", true);

        JogosPanel() {
            setLayout(new BorderLayout(12, 12));
            setBorder(new EmptyBorder(20, 20, 20, 20));
            setBackground(new Color(30, 30, 30));

            JPanel form = new JPanel(new GridLayout(0, 2, 8, 8));
            form.setBackground(new Color(30, 30, 30));

            form.add(new JLabel("Título:"));
            form.add(tfTitulo);
            form.add(new JLabel("Gênero:"));
            form.add(tfGenero);
            form.add(new JLabel("Ano de Lançamento:"));
            form.add(tfAno);
            form.add(new JLabel("Preço (R$):"));
            form.add(tfPreco);
            form.add(new JLabel("Status:"));
            chkDisponivel.setBackground(new Color(30, 30, 30));
            chkDisponivel.setForeground(Color.WHITE);
            form.add(chkDisponivel);

            JButton btnSalvar = criarBotaoAcao("Salvar");
            JButton btnRemover = criarBotaoAcao("Remover");
            JButton btnLimpar = criarBotaoAcao("Limpar");

            JPanel actions = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            actions.setBackground(new Color(30, 30, 30));
            actions.add(btnRemover);
            actions.add(btnLimpar);
            actions.add(btnSalvar);

            JScrollPane scroll = new JScrollPane(table);
            scroll.getViewport().setBackground(new Color(40, 40, 40));
            table.setSelectionBackground(new Color(0, 122, 204));
            table.setRowHeight(25);

            add(form, BorderLayout.NORTH);
            add(scroll, BorderLayout.CENTER);
            add(actions, BorderLayout.SOUTH);

            btnSalvar.addActionListener(this::onSalvar);
            btnRemover.addActionListener(e -> onRemover());
            btnLimpar.addActionListener(e -> clearForm());

            table.getSelectionModel().addListSelectionListener(e -> {
                int i = table.getSelectedRow();
                if (i >= 0 && i < jogos.size()) {
                    Jogo j = jogos.get(i);
                    tfTitulo.setText(j.getTitulo());
                    tfGenero.setText(j.getGenero());
                    tfAno.setText(String.valueOf(j.getAnoLancamento()));
                    tfPreco.setText(String.valueOf(j.getPreco()));
                    chkDisponivel.setSelected(j.isDisponivel());
                }
            });
        }

        private JButton criarBotaoAcao(String texto) {
            JButton btn = new JButton(texto);
            btn.setBackground(new Color(45, 45, 45));
            btn.setForeground(Color.WHITE);
            btn.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            btn.setFocusPainted(false);
            btn.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent e) { btn.setBackground(new Color(0, 122, 204)); }
                public void mouseExited(MouseEvent e) { btn.setBackground(new Color(45, 45, 45)); }
            });
            return btn;
        }

        private void onSalvar(ActionEvent e) {
            String titulo = tfTitulo.getText().trim();
            String genero = tfGenero.getText().trim();
            String sAno = tfAno.getText().trim();
            String sPreco = tfPreco.getText().trim();
            boolean disponivel = chkDisponivel.isSelected();

            if (titulo.isEmpty() || genero.isEmpty() || sAno.isEmpty() || sPreco.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos.");
                return;
            }

            int ano; double preco;
            try { ano = Integer.parseInt(sAno); }
            catch (Exception ex) { JOptionPane.showMessageDialog(this, "Ano inválido."); return; }
            try { preco = Double.parseDouble(sPreco.replace(",", ".")); }
            catch (Exception ex) { JOptionPane.showMessageDialog(this, "Preço inválido."); return; }

            int selected = table.getSelectedRow();
            if (selected >= 0 && selected < jogos.size()) {
                Jogo j = jogos.get(selected);
                j.setTitulo(titulo);
                j.setGenero(genero);
                try { j.setAnoLancamento(ano); } catch (Exception ex) { JOptionPane.showMessageDialog(this, ex.getMessage()); return; }
                j.setPreco(preco);
                j.setDisponivel(disponivel);
            } else {
                try {
                    Jogo novo = new Jogo(titulo, genero, ano, preco);
                    novo.setDisponivel(disponivel);
                    jogos.add(novo);
                } catch (Exception ex) { JOptionPane.showMessageDialog(this, ex.getMessage()); return; }
            }

            salvarDados();
            refresh();
            clearForm();
        }

        private void onRemover() {
            int i = table.getSelectedRow();
            if (i >= 0 && i < jogos.size()) {
                jogos.remove(i);
                salvarDados();
                refresh();
                clearForm();
            }
        }

        void refresh() {
            model.setRowCount(0);
            for (Jogo j : jogos) {
                model.addRow(new Object[]{
                        j.getId(),
                        j.getTitulo(),
                        j.getGenero(),
                        j.getAnoLancamento(),
                        String.format("%.2f", j.getPreco()),
                        j.isDisponivel() ? "Disponível" : "Indisponível"
                });
            }
        }

        void clearForm() {
            tfTitulo.setText("");
            tfGenero.setText("");
            tfAno.setText("");
            tfPreco.setText("");
            chkDisponivel.setSelected(true);
            table.clearSelection();
        }
    }

    private class VendasPanel extends JPanel {
    	private static final long serialVersionUID = 1L;
        private final DefaultTableModel model = new DefaultTableModel(
                new Object[]{"ID Venda", "Cliente", "Jogo", "Valor Pago", "Data"}, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };

        private final JTable table = new JTable(model);
        private final JComboBox<Cliente> cbCliente = new JComboBox<>();
        private final JComboBox<Jogo> cbJogo = new JComboBox<>();
        private final JTextField tfValorOriginal = new JTextField();
        private final JTextField tfValorFinal = new JTextField();

        VendasPanel() {
            setLayout(new BorderLayout(12, 12));
            setBorder(new EmptyBorder(20, 20, 20, 20));
            setBackground(new Color(30, 30, 30));

            JPanel form = new JPanel(new GridLayout(0, 2, 8, 8));
            form.setBackground(new Color(30, 30, 30));

            form.add(new JLabel("Cliente:"));
            form.add(cbCliente);
            form.add(new JLabel("Jogo:"));
            form.add(cbJogo);
            form.add(new JLabel("Preço Original (R$):"));
            form.add(tfValorOriginal);
            form.add(new JLabel("Preço com Desconto (R$):"));
            tfValorFinal.setEditable(false);
            form.add(tfValorFinal);

            JButton btnCalcular = criarBotaoAcao("Calcular Desconto");
            JButton btnVender = criarBotaoAcao("Efetuar Venda");
            JButton btnRemover = criarBotaoAcao("Remover");

            JPanel actions = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            actions.setBackground(new Color(30, 30, 30));
            actions.add(btnRemover);
            actions.add(btnCalcular);
            actions.add(btnVender);

            JScrollPane scroll = new JScrollPane(table);
            scroll.getViewport().setBackground(new Color(40, 40, 40));
            table.setSelectionBackground(new Color(0, 122, 204));
            table.setRowHeight(25);

            add(form, BorderLayout.NORTH);
            add(scroll, BorderLayout.CENTER);
            add(actions, BorderLayout.SOUTH);

            btnCalcular.addActionListener(e -> onCalcular());
            btnVender.addActionListener(e -> onVender());
            btnRemover.addActionListener(e -> onRemover());
            cbJogo.addActionListener(e -> autoFillPreco());
        }
        
        
        private JButton criarBotaoAcao(String texto) {
            JButton btn = new JButton(texto);
            btn.setBackground(new Color(45, 45, 45));
            btn.setForeground(Color.WHITE);
            btn.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            btn.setFocusPainted(false);
            btn.addMouseListener(new MouseAdapter() {
                @Override public void mouseEntered(MouseEvent e) { btn.setBackground(new Color(0, 122, 204)); }
                @Override public void mouseExited(MouseEvent e)  { btn.setBackground(new Color(45, 45, 45)); }
            });
            return btn;

        }

        private void autoFillPreco() {
            Jogo j = (Jogo) cbJogo.getSelectedItem();
            if (j != null) {
                tfValorOriginal.setText(String.valueOf(j.getPreco()));
                onCalcular();
            }
        }

        private void onCalcular() {
            Cliente c = (Cliente) cbCliente.getSelectedItem();
            String sValor = tfValorOriginal.getText().trim();
            if (c == null || sValor.isEmpty()) {
                tfValorFinal.setText("");
                return;
            }
            try {
                double original = Double.parseDouble(sValor.replace(",", "."));
                double finalV = c.calcularDesconto(original);
                tfValorFinal.setText(String.format("%.2f", finalV));
            } catch (Exception ignored) {}
        }

        private void onVender() {
            Cliente c = (Cliente) cbCliente.getSelectedItem();
            Jogo j = (Jogo) cbJogo.getSelectedItem();
            if (c == null || j == null) {
                JOptionPane.showMessageDialog(this, "Selecione cliente e jogo.");
                return;
            }
            if (!j.isDisponivel()) {
                JOptionPane.showMessageDialog(this, "Jogo indisponível.");
                return;
            }

            double valor;
            try { valor = Double.parseDouble(tfValorFinal.getText().replace(",", ".")); }
            catch (Exception ex) { JOptionPane.showMessageDialog(this, "Calcule o desconto primeiro."); return; }

            try {
                c.debitarSaldo(valor);
                j.setDisponivel(false);
                Venda v = new Venda(c, j, valor);
                vendas.add(v);
                JOptionPane.showMessageDialog(this, "Venda realizada com sucesso!");
                salvarDados();
                refresh();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        }

        private void onRemover() {
            int i = table.getSelectedRow();
            if (i >= 0 && i < vendas.size()) {
                vendas.remove(i);
                salvarDados();
                refresh();
            }
        }

        void refresh() {
            cbCliente.removeAllItems();
            for (Cliente c : clientes) cbCliente.addItem(c);
            cbJogo.removeAllItems();
            for (Jogo j : jogos) if (j.isDisponivel()) cbJogo.addItem(j);

            model.setRowCount(0);
            for (Venda v : vendas) {
            	DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            	model.addRow(new Object[]{
            	    v.getId(),
            	    v.getCliente().getNome(),
            	    v.getJogo().getTitulo(),
            	    String.format("%.2f", v.getValorPago()),
            	    v.getDataCompra().format(fmt)
                });
            }
        }
    }

    private class HistoricoPanel extends JPanel {
    	private static final long serialVersionUID = 1L;
        private final DefaultTableModel model = new DefaultTableModel(
                new Object[]{"ID Venda", "Cliente", "Jogo", "Valor", "Data"}, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        private final JTable table = new JTable(model);
        private final JComboBox<Cliente> cbCliente = new JComboBox<>();

        HistoricoPanel() {
            setLayout(new BorderLayout(12, 12));
            setBorder(new EmptyBorder(20, 20, 20, 20));
            setBackground(new Color(30, 30, 30));

            JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
            top.setBackground(new Color(30, 30, 30));
            top.add(new JLabel("Filtrar por Cliente:"));
            top.add(cbCliente);

            JScrollPane scroll = new JScrollPane(table);
            scroll.getViewport().setBackground(new Color(40, 40, 40));
            table.setSelectionBackground(new Color(0, 122, 204));
            table.setRowHeight(25);

            add(top, BorderLayout.NORTH);
            add(scroll, BorderLayout.CENTER);

            cbCliente.addActionListener(e -> refresh());
        }

        void refresh() {
            cbCliente.removeAllItems();
            cbCliente.addItem(null);
            for (Cliente c : clientes) cbCliente.addItem(c);

            Cliente filtro = (Cliente) cbCliente.getSelectedItem();

            model.setRowCount(0);
            for (Venda v : vendas) {
                if (filtro == null || v.getCliente().equals(filtro)) {
                	DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                	model.addRow(new Object[]{
                	    v.getId(),
                	    v.getCliente().getNome(),
                	    v.getJogo().getTitulo(),
                	    String.format("%.2f", v.getValorPago()),
                	    v.getDataCompra().format(fmt)
                    });
                }
            }
        }
    }

    private class SaldoPanel extends JPanel {
    	private static final long serialVersionUID = 1L;
        private final JComboBox<Cliente> cbCliente = new JComboBox<>();
        private final JTextField tfValor = new JTextField();

        SaldoPanel() {
            setLayout(new GridLayout(5, 1, 10, 10));
            setBorder(new EmptyBorder(60, 200, 60, 200));
            setBackground(new Color(30, 30, 30));

            add(new JLabel("Selecione o Cliente:", SwingConstants.CENTER));
            add(cbCliente);
            add(new JLabel("Valor a adicionar (R$):", SwingConstants.CENTER));
            add(tfValor);

            JButton btnAdicionar = new JButton("💵 Adicionar Saldo");
            btnAdicionar.setBackground(new Color(0, 122, 204));
            btnAdicionar.setForeground(Color.WHITE);
            btnAdicionar.setFont(new Font("Segoe UI", Font.BOLD, 16));
            btnAdicionar.addActionListener(e -> adicionarSaldo());
            add(btnAdicionar);
        }

        private void adicionarSaldo() {
            Cliente c = (Cliente) cbCliente.getSelectedItem();
            if (c == null) { JOptionPane.showMessageDialog(this, "Selecione um cliente."); return; }
            try {
                double valor = Double.parseDouble(tfValor.getText().replace(",", "."));
                c.adicionarSaldo(valor);
                salvarDados();
                JOptionPane.showMessageDialog(this, "Saldo atualizado! Novo saldo: R$ " + String.format("%.2f", c.getSaldo()));
                tfValor.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Valor inválido.");
            }
        }

        void refresh() {
            cbCliente.removeAllItems();
            for (Cliente c : clientes) cbCliente.addItem(c);
        }
    }

    private void salvarDados() {
        try {
            salvarObjeto(clientes, CLIENTES_FILE);
            salvarObjeto(jogos, JOGOS_FILE);
            salvarObjeto(vendas, VENDAS_FILE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar dados: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void carregarDados() {
        try {
            List<Cliente> c = (List<Cliente>) carregarObjeto(CLIENTES_FILE);
            if (c != null) clientes.addAll(c);
            List<Jogo> j = (List<Jogo>) carregarObjeto(JOGOS_FILE);
            if (j != null) jogos.addAll(j);
            List<Venda> v = (List<Venda>) carregarObjeto(VENDAS_FILE);
            if (v != null) vendas.addAll(v);
        } catch (Exception e) {
            System.out.println("Nenhum dado carregado ainda: " + e.getMessage());
        }
    }

    private void salvarObjeto(Object obj, String nomeArquivo) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
            oos.writeObject(obj);
        }
    }

    private Object carregarObjeto(String nomeArquivo) throws IOException, ClassNotFoundException {
        File file = new File(nomeArquivo);
        if (!file.exists()) return null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return ois.readObject();
        }
    }

    private void confirmarSaida() {
        salvarDados();
        int resp = JOptionPane.showConfirmDialog(
                this,
                "Deseja realmente sair?\nTodos os dados foram salvos.",
                "Sair do Sistema",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );
        if (resp == JOptionPane.YES_OPTION) {
            dispose();
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SistemaGameUI ui = new SistemaGameUI();
            ui.setVisible(true);
        });
    }
}
