/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package my.gui;

import java.awt.Font;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

/**
 *
 * @author ADMIN
 */
public class NotePad extends JFrame {

    private JMenuBar mBar;
    private JMenu mFile, mEdit, mFormat, mView, mHelp, mZoom;
    private JMenuItem itemNew, itemOpen, itemSave, itemSaveAs, itemPageSetup, itemExit, itemPrint, itemFont;
    private JMenuItem itemCopy, itemPaste, itemFind, itemReplace, itemUndo, itemCut, itemDelete;
    private JMenuItem itemSearchwithbing, itemNextFind, itemFindprevious, itemGoto, itemSelectall, itemTimeDate;
    private JMenuItem itemZoomin, itemZoomout, itemRestoredefaultzoom;
    private JMenuItem itemViewHelp, itemSendFeedback, itemAboutNotepad;
     private JCheckBoxMenuItem itemStatusbar;
    private JCheckBoxMenuItem itemWrap;
    private JTextArea txtEditor;
    private File currentFile;

    public NotePad(String title) {
        super(title);
        createMenu();
        createGUI();
        processEvent();
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void createMenu() {
        mBar = new JMenuBar();
        mFile = new JMenu("File");
        mEdit = new JMenu("Edit");
        mFormat = new JMenu("Format");
        mView = new JMenu("View");
        mHelp = new JMenu("Help");

        mBar.add(mFile);
        mBar.add(mEdit);
        mBar.add(mFormat);
        mBar.add(mView);
        mBar.add(mHelp);
        
         mView.add(mZoom = new JMenu("Zoom"));
        mZoom.add(itemZoomin = new JMenuItem("Zoom in"));
        mZoom.add(itemZoomout = new JMenuItem("Zoom out"));
        mZoom.add(itemRestoredefaultzoom = new JMenuItem("Restore Default Zoom"));
        mView.add(itemStatusbar = new JCheckBoxMenuItem("Status Bar", true));
       
        
        itemZoomin.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_PLUS, KeyEvent.CTRL_DOWN_MASK));
        itemZoomout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, KeyEvent.CTRL_DOWN_MASK));
        itemRestoredefaultzoom.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_0, KeyEvent.CTRL_DOWN_MASK));
       
        
        mHelp.add(itemViewHelp = new JMenuItem("View help"));
        mHelp.add(itemSendFeedback = new JMenuItem("Send Feedback"));
        mHelp.addSeparator();
        mHelp.add(itemAboutNotepad = new JMenuItem("About Notepad"));   

        
        
        mFile.add(itemNew = new JMenuItem("New..."));
        mFile.add(itemOpen = new JMenuItem("Open..."));
        mFile.add(itemSave = new JMenuItem("Save"));
        mFile.add(itemSaveAs = new JMenuItem("Save As..."));
        mFile.addSeparator();
        mFile.add(itemPageSetup = new JMenuItem("PageSetup"));
        mFile.add(itemPrint = new JMenuItem("Print..."));
        mFile.addSeparator();
        mFile.add(itemExit = new JMenuItem("Exit"));

        mEdit.add(itemUndo = new JMenuItem("Undo"));
        mEdit.addSeparator();
        mEdit.add(itemCut = new JMenuItem("Cut"));
        mEdit.add(itemCopy = new JMenuItem("Copy"));
        mEdit.add(itemPaste = new JMenuItem("Paste"));
        mEdit.add(itemDelete = new JMenuItem("Delete"));

        mEdit.addSeparator();
        mEdit.add(itemSearchwithbing = new JMenuItem("Search With Bring..."));
        mEdit.add(itemFind = new JMenuItem("Find..."));
        mEdit.add(itemNextFind = new JMenuItem("Find Next..."));
        mEdit.add(itemFindprevious = new JMenuItem("Find Previous"));
        
        mEdit.add(itemReplace = new JMenuItem("Replace"));
        mEdit.add(itemGoto = new JMenuItem("Go to"));
        mEdit.addSeparator();
        mEdit.add(itemSelectall = new JMenuItem("Select All"));
        mEdit.add(itemTimeDate = new JMenuItem("Time/Date"));

        mFormat.add(itemWrap = new JCheckBoxMenuItem("Word Wrap", true));
        mFormat.add(itemFont = new JMenuItem("Font..."));
        itemCut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK));
        itemNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
        itemOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
        itemSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
        itemSaveAs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK + KeyEvent.SHIFT_DOWN_MASK));
        itemPrint.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_DOWN_MASK));
        itemUndo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_DOWN_MASK));
        itemCut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK));
        itemCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK));
        itemPaste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_DOWN_MASK));
        itemSearchwithbing.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, KeyEvent.CTRL_DOWN_MASK));
        itemFind.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, KeyEvent.CTRL_DOWN_MASK));
        itemReplace.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, KeyEvent.CTRL_DOWN_MASK));
        itemGoto.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, KeyEvent.CTRL_DOWN_MASK));
        itemSelectall.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_DOWN_MASK));
        itemNextFind.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0));
        itemTimeDate.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
        itemFindprevious.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3, KeyEvent.SHIFT_DOWN_MASK));
        setJMenuBar(mBar);
    }

    private void processEvent() {
        itemOpen.addActionListener(e -> openFile());
        itemSave.addActionListener(e -> saveFile(false));
        itemSaveAs.addActionListener(e -> saveFile(true));
        itemPrint.addActionListener(e -> printFile());
        itemExit.addActionListener(e -> System.exit(0));
        itemCopy.addActionListener(e -> txtEditor.copy());
        itemPaste.addActionListener(e -> txtEditor.paste());
        itemFind.addActionListener(e -> findText());
        itemReplace.addActionListener(e -> replaceText());
        itemWrap.addActionListener(e -> txtEditor.setLineWrap(itemWrap.isSelected()));
        itemFont.addActionListener(e -> changeFont());
    }

    private void openFile() {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showOpenDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            currentFile = fileChooser.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(currentFile))) {
                txtEditor.read(reader, null);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error opening file: " + ex.getMessage());
            }
        }
    }

    private void saveFile(boolean saveAs) {
        if (saveAs || currentFile == null) {
            JFileChooser fileChooser = new JFileChooser();
            int option = fileChooser.showSaveDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
                currentFile = fileChooser.getSelectedFile();
            } else {
                return;
            }
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(currentFile))) {
            txtEditor.write(writer);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error saving file: " + ex.getMessage());
        }
    }

    private void printFile() {
        try {
            txtEditor.print();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error printing: " + ex.getMessage());
        }
    }

    private void findText() {
        String findText = JOptionPane.showInputDialog(this, "Enter text to find:");
        if (findText != null && !findText.isEmpty()) {
            String editorText = txtEditor.getText();
            int index = editorText.indexOf(findText);
            if (index >= 0) {
                txtEditor.setCaretPosition(index + findText.length());
                txtEditor.select(index, index + findText.length());
            } else {
                JOptionPane.showMessageDialog(this, "Text not found!");
            }
        }
    }

    private void replaceText() {
        String findText = JOptionPane.showInputDialog(this, "Enter text to find:");
        if (findText != null && !findText.isEmpty()) {
            String replaceText = JOptionPane.showInputDialog(this, "Enter replacement text:");
            if (replaceText != null) {
                txtEditor.setText(txtEditor.getText().replace(findText, replaceText));
            }
        }
    }

    private void changeFont() {
        Font currentFont = txtEditor.getFont();
        Font newFont = JFontChooser.showDialog(this, "Choose Font", currentFont);
        if (newFont != null) {
            txtEditor.setFont(newFont);
        }
    }

    private void createGUI() {
        txtEditor = new JTextArea();
        JScrollPane scrollEditor = new JScrollPane(txtEditor);
        add(scrollEditor);
        txtEditor.setLineWrap(true);
        txtEditor.setFont(new Font("Arial", Font.PLAIN, 16));
    }

    private static class JFontChooser {

        private static Font showDialog(NotePad aThis, String choose_Font, Font currentFont) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public JFontChooser() {
        }
    }

}
