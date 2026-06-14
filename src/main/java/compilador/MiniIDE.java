package compilador;

import javax.swing.*;
import java.awt.*;
import java.io.StringReader;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class MiniIDE extends JFrame {
    private JTextArea txtEditor;
    private JTextArea txtConsole;
    private JButton btnEjecutar;

    public MiniIDE() {
        setTitle("Mini IDE - EduObject");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        JPanel panelControles = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnEjecutar = new JButton("Ejecutar Análisis");
        panelControles.add(btnEjecutar);

        txtEditor = new JTextArea();
        txtEditor.setFont(new Font("Consolas", Font.PLAIN, 14));
        txtEditor.setText("clase Producto {\n    texto nombre;\n    decimal precio;\n\n    metodo actualizarPrecio(decimal nuevoPrecio) {\n        precio = nuevoPrecio;\n        imprimir(\"El precio fue actualizado exitosamente\");\n    }\n}\n\nProducto alfajor = nuevo Producto();\nalfajor.actualizarPrecio(1500.50);\n");
        JScrollPane scrollEditor = new JScrollPane(txtEditor);
        scrollEditor.setBorder(BorderFactory.createTitledBorder("Editor de Código (EduObject)"));

        txtConsole = new JTextArea();
        txtConsole.setEditable(false);
        txtConsole.setBackground(Color.BLACK);
        txtConsole.setForeground(Color.GREEN);
        txtConsole.setFont(new Font("Consolas", Font.PLAIN, 13));
        JScrollPane scrollConsole = new JScrollPane(txtConsole);
        scrollConsole.setPreferredSize(new Dimension(800, 200));
        scrollConsole.setBorder(BorderFactory.createTitledBorder("Salida del Compilador"));

        add(panelControles, BorderLayout.NORTH);
        add(scrollEditor, BorderLayout.CENTER);
        add(scrollConsole, BorderLayout.SOUTH);

        btnEjecutar.addActionListener(e -> ejecutarAnalisis());
    }

    private void ejecutarAnalisis() {
        String codigoFuente = txtEditor.getText();
        txtConsole.setText("Iniciando análisis léxico y sintáctico...\n\n");
        
        // Redirigir System.err para capturar errores del Lexer/Parser en la interfaz
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream oldErr = System.err;
        System.setErr(ps);

        try {
            Lexer lexer = new Lexer(new StringReader(codigoFuente));
            parser as = new parser(lexer);
            as.parse();
            
            System.err.flush();
            String errores = baos.toString();
            
            if (errores.isEmpty()) {
                txtConsole.append("[ÉXITO] El programa se ha procesado correctamente.\n");
                txtConsole.append("Estructura sintáctica válida según la gramática de EduObject.\n");
            } else {
                txtConsole.append(errores);
                txtConsole.append("\n[FALLO] Se encontraron errores durante el análisis.\n");
            }
        } catch (Exception ex) {
            txtConsole.append("\n[ERROR CRÍTICO] La compilación se detuvo.\n");
        } finally {
            System.setErr(oldErr); // Restaurar salida de error estándar
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MiniIDE().setVisible(true));
    }
}