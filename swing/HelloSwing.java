import javax.swing.*;
class HelloSwing{

    static void setFrame(){
    JFrame jfrm= new JFrame("hello swing");
    jfrm.setSize(400,400);
    JLabel jlab= new JLabel("first jlabel");
    jfrm.add(jlab);
    jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    jfrm.setVisible(true);
    }
    public static void main(String args[]){
        SwingUtilities.invokeLater(new Runnable(){
            
            public void run(){
            setFrame();
            }
        });
    }

}
