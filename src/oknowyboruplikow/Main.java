
package oknowyboruplikow;

import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;


public class Main extends JFrame{
    
        private JPanel panel = new JPanel();
        private JButton bOpen = new JButton("Otwórz");
        private JButton bSave = new JButton("Zapisz");
        private String[] ROZSZERZENIA_TEKSTOWE = new String[] {".txt",".xml"};
        
    
    public Main()
    {
        JFileChooser wybierzPlik = new JFileChooser();
        wybierzPlik.setCurrentDirectory(new File(System.getProperty("user.dir")));
        wybierzPlik.setMultiSelectionEnabled(true);
        
        wybierzPlik.setFileFilter(new FiltrRozszerzen("Pola tekstowe", ROZSZERZENIA_TEKSTOWE));
        wybierzPlik.setFileFilter(new FileFilter() {
                @Override
                public boolean accept(File f) 
                {
                    for (int i = 0; i < ROZSZERZENIA_TEKSTOWE.length; i++)
                    if (f.getName().toLowerCase().endsWith(ROZSZERZENIA_TEKSTOWE[i]) || f.isDirectory())
                        return true;
                    return false;
                }

                @Override
                public String getDescription() 
                {
                    return "Katalogi";
                }
            });
        
        this.setTitle("Okno wyboru plików");
        this.setBounds(300,300,300,300);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        this.getContentPane().add(panel);
        panel.add(bOpen);
        panel.add(bSave);
        
                               
        bOpen.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int tmp = wybierzPlik.showOpenDialog(rootPane);
                       StworzPlikZip(wybierzPlik.getSelectedFiles());
              
            }
            
        });
        
        bSave.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int tmp = wybierzPlik.showDialog(rootPane,"ZIP in that place");
            }
            
        });
        
    }
    
    private class FiltrRozszerzen extends FileFilter
    {
        private String opis;
        private String[] rozszerzenia;        
        
        FiltrRozszerzen(String opis, String[] rozszerzenia)
        {
            this.opis = opis;
            this.rozszerzenia = rozszerzenia;
        }
        
        @Override
        public boolean accept(File f) 
        {
            return f.isDirectory();
        }

        @Override
        public String getDescription() 
        {
            return opis;
        }
        
    }

    public void StworzPlikZip(File[] filesToZIP)
    {
        for (int i = 0; i < filesToZIP.length; i++)
            System.out.println(filesToZIP[i].getPath());
            }
    
    public static void main(String[] args) 
    {
        new Main().setVisible(true);
    }
    
}
