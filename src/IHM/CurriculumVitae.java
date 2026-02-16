package IHM;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Image;
import java.awt.Desktop;

public class CurriculumVitae extends JInternalFrame {

    JButton btn_valider, btn_quitter, btn_chooseImage, btn_chooseColor;
    JLabel lb_title, lb_nom, lb_prenom, lb_sexe, lb_country,lb_diplome, lb_langue, lb_institute, lb_graduation, lb_image, lb_color, lb_skills;
    JTextField tf_nom, tf_prenom, tf_institute;
    JRadioButton rb_male, rb_female;
    ButtonGroup bg_sexe;
    JComboBox<String> cb_country,cb_diplome;
    JList<String> list_langues;
    JSpinner spinner_graduation;
    JCheckBox cb_communication, cb_teamwork, cb_adaptability, cb_problemSolving;
    File profileImage;
    Color chosenColor;

    public CurriculumVitae() {
        this.setTitle("Curriculum Vitae");
        this.setSize(1000, 700);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setClosable(true);
        this.setMaximizable(true);
        this.setIconifiable(true);
        this.setResizable(true);
        // Main content panel
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        contentPanel.setPreferredSize(new Dimension(100, 1500)); // tall to allow vertical scroll

        // Scroll pane
        JScrollPane scrollPane = new JScrollPane(contentPanel,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.add(scrollPane);

        // Title
        lb_title = new JLabel("Curriculum Vitae form");
        lb_title.setForeground(Color.white);
        lb_title.setBackground(Color.blue);
        lb_title.setOpaque(true);
        lb_title.setPreferredSize(new Dimension(1000, 50));
        lb_title.setHorizontalAlignment(JLabel.CENTER);
        lb_title.setFont(new Font(Font.MONOSPACED, Font.BOLD | Font.ITALIC, 32));
        contentPanel.add(lb_title);

        // Nom
        lb_nom = new JLabel("Nom");
        tf_nom = new JTextField(20);
        contentPanel.add(lb_nom);
        contentPanel.add(tf_nom);

        // Prenom
        lb_prenom = new JLabel("Prenom");
        tf_prenom = new JTextField(20);
        contentPanel.add(lb_prenom);
        contentPanel.add(tf_prenom);

        // Institute
        lb_institute = new JLabel("Institute");
        tf_institute = new JTextField(20);
        contentPanel.add(lb_institute);
        contentPanel.add(tf_institute);

        // Sexe
        lb_sexe = new JLabel("Sexe");
        rb_male = new JRadioButton("Male");
        rb_female = new JRadioButton("Female");
        bg_sexe = new ButtonGroup();
        bg_sexe.add(rb_male);
        bg_sexe.add(rb_female);
        contentPanel.add(lb_sexe);
        contentPanel.add(rb_male);
        contentPanel.add(rb_female);

        // Country (single selection)
        lb_country = new JLabel("Country");
        String[] countries = {"France","Tunisia", "Morocco", "Spain", "Germany", "Italy", "USA", "UK"};
        cb_country = new JComboBox<>(countries);
        contentPanel.add(lb_country);
        contentPanel.add(cb_country);

        // diplome (single selection)
        lb_diplome = new JLabel("Diplome");
        String[] diplomes = {"Cycle ingenieur","Cycle preparatoire","Liscense","Master"};
        cb_diplome = new JComboBox<>(diplomes);
        contentPanel.add(lb_diplome);
        contentPanel.add(cb_diplome);

        // Languages (multi-selection)
        lb_langue = new JLabel("Languages");
        String[] langues = {"French", "English", "Arabic", "Spanish", "German"};
        list_langues = new JList<>(langues);
        list_langues.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane sp_langues = new JScrollPane(list_langues);
        sp_langues.setPreferredSize(new Dimension(150, 80));
        contentPanel.add(lb_langue);
        contentPanel.add(sp_langues);

        // Expected graduation
        lb_graduation = new JLabel("Expected Graduation");
        spinner_graduation = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(spinner_graduation, "dd/MM/yyyy");
        spinner_graduation.setEditor(dateEditor);
        contentPanel.add(lb_graduation);
        contentPanel.add(spinner_graduation);

        // Soft Skills
        lb_skills = new JLabel("Soft Skills");
        cb_communication = new JCheckBox("Communication");
        cb_teamwork = new JCheckBox("Teamwork");
        cb_adaptability = new JCheckBox("Adaptability");
        cb_problemSolving = new JCheckBox("Problem-Solving");

        JPanel skillsPanel = new JPanel();
        skillsPanel.setLayout(new GridLayout(2, 2));
        skillsPanel.add(cb_communication);
        skillsPanel.add(cb_teamwork);
        skillsPanel.add(cb_adaptability);
        skillsPanel.add(cb_problemSolving);

        contentPanel.add(lb_skills);
        contentPanel.add(skillsPanel);

        // Profile image chooser
        lb_image = new JLabel("Profile Image");
        btn_chooseImage = new JButton("Choose Image");
        btn_chooseImage.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int option = fileChooser.showOpenDialog(CurriculumVitae.this);
            if (option == JFileChooser.APPROVE_OPTION) {
                profileImage = fileChooser.getSelectedFile();
                JOptionPane.showMessageDialog(CurriculumVitae.this,
                        "Selected: " + profileImage.getName());
            }
        });
        contentPanel.add(lb_image);
        contentPanel.add(btn_chooseImage);

        // Color chooser
        lb_color = new JLabel("Choose Background Color");
        btn_chooseColor = new JButton("Choose Color");
        btn_chooseColor.addActionListener(e -> {
            chosenColor = JColorChooser.showDialog(CurriculumVitae.this, "Pick a color", Color.WHITE);
            if (chosenColor != null) {
                contentPanel.setBackground(chosenColor); // change background
            }
        });

        contentPanel.add(lb_color);
        contentPanel.add(btn_chooseColor);

        // Buttons
        btn_valider = new JButton("Valider");
        btn_quitter = new JButton("Quitter");
        contentPanel.add(btn_valider);
        contentPanel.add(btn_quitter);

        btn_quitter.addActionListener(e -> System.exit(0));
        btn_valider.addActionListener(new EcouteurButton());

        this.setVisible(true);
    }

    class EcouteurButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String nom = tf_nom.getText();
                String prenom = tf_prenom.getText();
                String institute = tf_institute.getText();
                String sexe = rb_male.isSelected() ? "Male" : "Female";
                String country = cb_country.getSelectedItem().toString();
                String diplome = cb_diplome.getSelectedItem().toString();
                List<String> selectedLanguages = list_langues.getSelectedValuesList();
                Date graduationDate = (Date) spinner_graduation.getValue();

                // Collect selected soft skills
                StringBuilder softSkills = new StringBuilder();
                if (cb_communication.isSelected()) softSkills.append("Communication, ");
                if (cb_teamwork.isSelected()) softSkills.append("Teamwork, ");
                if (cb_adaptability.isSelected()) softSkills.append("Adaptability, ");
                if (cb_problemSolving.isSelected()) softSkills.append("Problem-Solving, ");
                if (softSkills.length() > 0) softSkills.setLength(softSkills.length() - 2); // remove trailing comma

                // PDF generation using iText 5
                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream("Cv.pdf"));
                document.open();

                Paragraph title = new Paragraph("Curriculum Vitae");
                title.setLeading(20f);
                document.add(title);

                document.add(new Paragraph("Name: " + nom + " " + prenom));
                document.add(new Paragraph("Institute: " + institute));
                document.add(new Paragraph("Sexe: " + sexe));
                document.add(new Paragraph("Country: " + country));
                document.add(new Paragraph("Diplome: " + diplome));
                document.add(new Paragraph("Languages:"));
                for (String lang : selectedLanguages) {
                    document.add(new Paragraph(" - " + lang));
                }
                document.add(new Paragraph("Soft Skills: " + softSkills.toString()));
                document.add(new Paragraph("Expected Graduation: " + graduationDate));

                if (profileImage != null) {
                    try {
                        Image img = Image.getInstance(profileImage.getAbsolutePath());
                        img.scaleToFit(150, 150);
                        document.add(new Paragraph("Profile Image:"));
                        document.add(img);
                    } catch (Exception imgEx) {
                        imgEx.printStackTrace();
                        document.add(new Paragraph("Profile Image: " + profileImage.getAbsolutePath()));
                    }
                }

                document.close();
                Desktop.getDesktop().open(new File("Cv.pdf"));

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

}
