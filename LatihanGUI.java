import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LatihanGUI extends JFrame {
    private JTextField txtJudul;
    private JComboBox<String> cmbPrioritas;
    private JCheckBox chkUrgent;
    private JButton btnTambah, btnBersihkan;
    private JTextArea areaCatatan;
    private JLabel lblStatus;

    public LatihanGUI() {
        setTitle("Latihan Java GUI - Catatan Tugas");
        setSize(550, 420);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout(10, 10));

        JPanel panelForm = new JPanel(new GridLayout(3, 2, 8, 8));
        panelForm.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));

        JLabel lblJudul = new JLabel("Nama Tugas:");
        txtJudul = new JTextField();

        JLabel lblPrioritas = new JLabel("Prioritas:");
        String[] opsiPrioritas = {"Rendah", "Sedang", "Tinggi"};
        cmbPrioritas = new JComboBox<>(opsiPrioritas);

        JLabel lblOpsi = new JLabel("Opsi Tambahan:");
        chkUrgent = new JCheckBox("Tandai sebagai Urgent");

        panelForm.add(lblJudul);
        panelForm.add(txtJudul);
        panelForm.add(lblPrioritas);
        panelForm.add(cmbPrioritas);
        panelForm.add(lblOpsi);
        panelForm.add(chkUrgent);

        add(panelForm, BorderLayout.NORTH);

        areaCatatan = new JTextArea();
        areaCatatan.setEditable(false);
        areaCatatan.setFont(new Font("Monospaced", Font.PLAIN, 13));

        JScrollPane scrollPane = new JScrollPane(areaCatatan);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Daftar Tugas"));
        add(scrollPane, BorderLayout.CENTER);

        JPanel panelBawah = new JPanel(new BorderLayout());
        JPanel panelTombol = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        
        btnTambah = new JButton("Tambah Tugas");
        btnBersihkan = new JButton("Bersihkan Semua");
        panelTombol.add(btnTambah);
        panelTombol.add(btnBersihkan);

        lblStatus = new JLabel("Status: Siap menerima input.");
        lblStatus.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        panelBawah.add(panelTombol, BorderLayout.CENTER);
        panelBawah.add(lblStatus, BorderLayout.SOUTH);

        add(panelBawah, BorderLayout.SOUTH);
        EventListener();
    }

    private void EventListener() {
        btnTambah.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tambahTugas();
            }
        });

        btnBersihkan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                areaCatatan.setText("");
                lblStatus.setText("Status: Semua tugas dibersihkan.");
            }
        });

        chkUrgent.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    lblStatus.setText("Status: Opsi Urgent diaktifkan.");
                } else {
                    lblStatus.setText("Status: Opsi Urgent dimatikan.");
                }
            }
        });

        btnTambah.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnTambah.setBackground(new Color(200, 230, 255));
                lblStatus.setText("Status: Klik untuk menyimpan tugas.");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnTambah.setBackground(null);
                lblStatus.setText("Status: Tambahkan tugas baru.");
            }
        });

        btnBersihkan.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnBersihkan.setBackground(new Color(255, 200, 200));
                lblStatus.setText("Status: Klik untuk menghapus semua tugas.");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnBersihkan.setBackground(null);
                lblStatus.setText("Status: Bersihkan daftar tugas.");
            }
        });

        txtJudul.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    tambahTugas();
                }
            }
        });
    }

    private void tambahTugas() {
        String judul = txtJudul.getText().trim();
        if (judul.isEmpty()) {
            JOptionPane.showMessageDialog(
                this,
                "Nama tugas tidak boleh kosong!",
                "Peringatan",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        String prioritas = (String) cmbPrioritas.getSelectedItem();
        String tagUrgent = chkUrgent.isSelected() ? " [URGENT]" : "";

        areaCatatan.append("- " + judul + " (Prioritas: " + prioritas + ")" + tagUrgent + "\n");
        txtJudul.setText("");
        chkUrgent.setSelected(false);
        txtJudul.requestFocus();
        lblStatus.setText("Status: Tugas '" + judul + "' berhasil ditambahkan.");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LatihanGUI().setVisible(true);
            }
        });
    }
}