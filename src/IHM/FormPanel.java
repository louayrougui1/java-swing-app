package IHM;

import Model.DataProfile;
import Model.Profile;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Set;

public class FormPanel extends JPanel {
    String ps;
    HashMap<String, Integer> languages;


    JLabel lb_nom, lb_langue, lb_cycle, lb_annee;
    JButton btn_add, btn_save, btn_close;
    JPanel north_panel, form_panel, south_panel, langue_panel, cycle_panel, annee_panel, first_panel;
    JComboBox<String> combo_cycle;
    JRadioButton rb1, rb2, rb3;
    ButtonGroup radio_group;
    Profile profile;

    public FormPanel(String ps) {
        this.ps = ps;
        profile = DataProfile.getProfile(ps);
        assert profile != null;
        languages = profile.getLanguages();
        this.setLayout(new BorderLayout());
        form_panel = new JPanel(new GridLayout(3, 1));
        north_panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        south_panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        langue_panel = new JPanel(/*new FlowLayout(FlowLayout.CENTER*/);
        langue_panel.setLayout(new BoxLayout(langue_panel, BoxLayout.Y_AXIS));
        first_panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        cycle_panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        annee_panel = new JPanel(new FlowLayout(FlowLayout.CENTER));


        assert profile != null;
        lb_nom = new JLabel("Bonjour " + profile.getNom() + " " + profile.getPrenom());
        north_panel.add(lb_nom);
        this.add(north_panel, BorderLayout.NORTH);

        JScrollPane scroll_langue = new JScrollPane(langue_panel);
        scroll_langue.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll_langue.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll_langue.setBorder(null);

        lb_langue = new JLabel("Langue");
        lb_langue.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn_add = new JButton("+");
        btn_add.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String new_language = JOptionPane.showInputDialog("Langue:");
                if (new_language != null && !languages.containsKey(new_language)) {
                    languages.put(new_language, 1);
                    JPanel row_language = new JPanel();
                    JLabel lb_language = new JLabel("-" + new_language);
                    JSpinner sp_language = new JSpinner(new SpinnerNumberModel(1, 1, 5, 1));
                    row_language.add(lb_language);
                    row_language.add(sp_language);
                    langue_panel.add(row_language);
                    langue_panel.revalidate();
                    langue_panel.repaint();

                    sp_language.addChangeListener(new ChangeListener() {
                        @Override
                        public void stateChanged(ChangeEvent e) {
                            Integer value = (Integer) sp_language.getValue();
                            languages.put(new_language, value);
                        }
                    });

                }

            }
        });


        lb_cycle = new JLabel("Cycle");
        combo_cycle = new JComboBox<>();
        combo_cycle.addItem("1er cycle");
        combo_cycle.addItem("2eme cycle");
        combo_cycle.setSelectedIndex(profile.getCycle() - 1);

        combo_cycle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String chosen_cycle = (String) combo_cycle.getSelectedItem();
                assert chosen_cycle != null;
                rb3.setVisible(!chosen_cycle.equals("1er cycle"));
            }
        });

        lb_annee = new JLabel("Annee");

        radio_group = new ButtonGroup();
        rb1 = new JRadioButton("1");
        rb2 = new JRadioButton("2");
        rb3 = new JRadioButton("3");
        rb3.setVisible(profile.getCycle() == 2);
        radio_group.add(rb1);
        radio_group.add(rb2);
        radio_group.add(rb3);
        rb1.setSelected(profile.getAnnee() == 1);
        rb2.setSelected(profile.getAnnee() == 2);
        rb3.setSelected(profile.getAnnee() == 3);


        first_panel.add(lb_langue);
        first_panel.add(btn_add);
        langue_panel.add(first_panel);

        //get the already chosen languages
        Set<String> languagesString = languages.keySet();
        for (String s : languagesString) {
            JPanel row_language = new JPanel();
            JLabel lb_language = new JLabel("-" + s);
            JSpinner sp_language = new JSpinner(new SpinnerNumberModel(languages.get(s).intValue(), 1, 5, 1));
            row_language.add(lb_language);
            row_language.add(sp_language);
            langue_panel.add(row_language);
            langue_panel.revalidate();
            langue_panel.repaint();
        }

        cycle_panel.add(lb_cycle);
        cycle_panel.add(combo_cycle);
        annee_panel.add(lb_annee);
        annee_panel.add(rb1);
        annee_panel.add(rb2);
        annee_panel.add(rb3);
        form_panel.add(scroll_langue);
        form_panel.add(cycle_panel);
        form_panel.add(annee_panel);


        btn_close = new JButton("Close");
        btn_save = new JButton("Save");

        btn_save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int year = rb1.isSelected() ? 1 : rb2.isSelected() ? 2 : rb3.isSelected() ? 3 : 0;
                int cycle = combo_cycle.getSelectedIndex() + 1;
                System.out.println(cycle);
                if (cycle >= 1 && year > 1) {
                    profile.setLanguages(languages);
                    profile.setAnnee(year);
                    profile.setCycle(cycle);

                    System.out.println(profile);
                    JOptionPane.showMessageDialog(FormPanel.this, "Saved successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(FormPanel.this, "Choose a cycle and year", "Missing fields", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btn_close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selection = JOptionPane.showConfirmDialog(FormPanel.this, "Unsaved changes may be lost, are you sure you want to close?");
                if (selection == JOptionPane.YES_OPTION) {
                    Container parent = FormPanel.this.getParent();
                    while (parent != null && !(parent instanceof JTabbedPane)) {
                        parent = parent.getParent();
                    }


                    if (parent != null) {
                        parent.remove(FormPanel.this);
                    }

                }
            }
        });

        south_panel.add(btn_save);
        south_panel.add(btn_close);

        this.add(form_panel, BorderLayout.CENTER);
        this.add(south_panel, BorderLayout.SOUTH);


    }


}
