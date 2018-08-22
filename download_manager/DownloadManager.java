import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import javax.swing.*;
import javax.swing.event.*;
class DownloadManager extends JFrame implements Observer {

	private JTextField addTextField;
	private DownLoadsTable tableModel;
	private JButton pause,resume,cancel,clear;
	private Download selectedDownload;
	private JTable table;
	//private Download selectedDownload;
	private boolean clearing;
	public DownloadManager(){
		setTitle("DownLoad manager");
		setSize(640,480);
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				actionExit();
			}
		});
	
	JMenuBar menubar= new JMenuBar();
	JMenu fileMenu= new JMenu("File");
	fileMenu.setMnemonic(KeyEvent.VK_F);
	JMenuItem fileExitMenuItem =new JMenuItem("exit",KeyEvent.VK_X);
	fileExitMenuItem.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			actionExit();

		}
	});
	fileMenu.add(fileExitMenuItem);
	menubar.add(fileMenu);
	setJMenuBar(menubar);
	JPanel addPanel =new JPanel();
	addTextField= new JTextField(30);
	addPanel.add(addTextField);
	JButton addButton=new JButton("Add Download");
	addButton.addActionListener(new ActionListener(){
		
		public void actionPerformed(ActionEvent e){
			actionAdd();

		}
	});
	addPanel.add(addButton);
	tableModel=new DownLoadsTable();
	table=new JTable(tableModel);
	table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
		public void valueChanged(ListSelectionEvent e){
			tableSelectionChanged();
		}
	});
	table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	ProgressRenderer renderer =new ProgressRenderer(0,100);
	renderer.setStringPainted(true);
	table.setDefaultRenderer(JProgressBar.class,renderer);
	table.setRowHeight((int)renderer.getPreferredSize().getHeight());
	JPanel downLoadsPanel= new JPanel();
	downLoadsPanel.setBorder(
		BorderFactory.createTitledBorder("DownLoads"));
	downLoadsPanel.setLayout(new BorderLayout());
	downLoadsPanel.add(new JScrollPane(table),BorderLayout.CENTER);
	JPanel buttonsPanel= new JPanel();
	pause= new JButton("pause");
	pause.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			actionPause();
		}
	});
	pause.setEnabled(false);;
	buttonsPanel.add(pause);

	resume= new JButton("resume");
	resume.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			actionresume();
		}
	});
	resume.setEnabled(false);;
	buttonsPanel.add(resume);

	cancel= new JButton("cancel");
	cancel.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			actioncancel();
		}
	});
	cancel.setEnabled(false);;
	buttonsPanel.add(cancel);
	

clear= new JButton("clear");
	clear.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			actionclear();
		}
	});
	clear.setEnabled(false);;
	buttonsPanel.add(clear);
	
	getContentPane().setLayout(new BorderLayout());
	getContentPane().add(addPanel,BorderLayout.NORTH);

	getContentPane().add(downLoadsPanel,BorderLayout.CENTER);

	getContentPane().add(buttonsPanel,BorderLayout.SOUTH);
	}
	private void actionExit(){
		System.exit(0);
	}
	private void actionAdd(){
		URL varifiedUrl =varifyUrl(addTextField.getText());
		if(varifiedUrl!=null)
		{
			tableModel.addDownload(new Download(varifiedUrl));
			addTextField.setText("");
		}
		else{
				JOptionPane.showMessageDialog(this,"invalid download url"+varifiedUrl,"Error",JOptionPane.ERROR_MESSAGE);
		}

	}
	private URL varifyUrl(String url){
		url=url.trim();
		if(!(url.toLowerCase().startsWith("http://")||url.toLowerCase().startsWith("https://")))
		{
				JOptionPane.showMessageDialog(this,"error in starting\n","Error",JOptionPane.ERROR_MESSAGE);
			
			return null;

		}
		URL verifiedUrl=null;
		try{
			verifiedUrl=new URL(url);
		}catch(Exception e){

		JOptionPane.showMessageDialog(this,"there is an exception"+e,"Error",JOptionPane.ERROR_MESSAGE);
			return null;
		}
		if(verifiedUrl.getFile().length()<2)
		{
					JOptionPane.showMessageDialog(this,"url length less then 2"+verifiedUrl,"Error",JOptionPane.ERROR_MESSAGE);
		
			return null;
		}
		return verifiedUrl;
	}
	private void tableSelectionChanged(){
		if(selectedDownload!=null){
			selectedDownload.deleteObserver(DownloadManager.this);

		}
		if(!clearing&&table.getSelectedRow()>-1)
		{
			selectedDownload=tableModel.getDownload(table.getSelectedRow());
			selectedDownload.addObserver(DownloadManager.this);
			updateButtons();
		}
	}
	private void actionPause(){
		selectedDownload.pause();
		updateButtons();

	}
	private void actionresume(){
		selectedDownload.resume();
		updateButtons();
	}
	private void actioncancel()
	{
		selectedDownload.cancel();
		updateButtons();
	}
	private void actionclear(){
		clearing=true;
		tableModel.clearDownLoad(table.getSelectedRow());
		clearing=false;
		selectedDownload=null;
		updateButtons();
	}
	private void updateButtons(){
		if(selectedDownload!=null){
			int status=selectedDownload.getStatus();
			switch(status){
				case Download.downloading:
				pause.setEnabled(true);
				resume.setEnabled(false);
				cancel.setEnabled(true);
				clear.setEnabled(false);
				break;
				case Download.paused:
				pause.setEnabled(false);
				resume.setEnabled(true);
				cancel.setEnabled(true);
				clear.setEnabled(false);
				break;
				case Download.error:
				pause.setEnabled(false);
				resume.setEnabled(true);
				cancel.setEnabled(false);
				clear.setEnabled(true);
				break; 
				default:
				pause.setEnabled(false);
				resume.setEnabled(false);
				cancel.setEnabled(false);
				clear.setEnabled(true);
				break;
			}
		}
		else
		{

				pause.setEnabled(false);
				resume.setEnabled(false);
				cancel.setEnabled(false);
				clear.setEnabled(false);
		}
	}
	public void update(Observable o,Object arg){
		if(selectedDownload!=null && selectedDownload.equals(o)){
			updateButtons();
		}
	}
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				DownloadManager manager = new DownloadManager();
				manager.setVisible(true);
			}
		});
	}

	
}
