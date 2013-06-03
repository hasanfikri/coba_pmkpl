import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;


class SingleList extends JPanel implements ListSelectionListener 
{
		private JList list;
		private DefaultListModel listModel;
		private static final String saveString = "Save";
		private static final String delString = "Delete";
		private JButton deleteButton;
		private JTextField ename;
		
		public SingleList() 
		{
		super(new BorderLayout());
		listModel = new DefaultListModel();
		listModel.addElement("Amardeep");
		listModel.addElement("Vinod");
		listModel.addElement("Suman");
		
		//Create the list and put it in a scroll pane.
		list = new JList(listModel);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setSelectedIndex(0);
		list.addListSelectionListener(this);
		list.setVisibleRowCount(5);
		JScrollPane listScrollPane = new JScrollPane(list);
		
		JButton hireButton = new JButton(saveString);
		SaveListener save = new SaveListener(hireButton);
		hireButton.setActionCommand(saveString);
		hireButton.addActionListener(save);
		hireButton.setEnabled(false);
		
		deleteButton = new JButton(delString);
		deleteButton.setActionCommand(delString);
		deleteButton.addActionListener(new DeleteListener());
		
		ename = new JTextField(10);
		ename.addActionListener(save);
		ename.getDocument().addDocumentListener(save);
		String name = listModel.getElementAt(list.getSelectedIndex()).toString();
		
		//Create a panel that uses BoxLayout.
		JPanel pane = new JPanel();
		pane.setLayout(new BoxLayout(pane, BoxLayout.LINE_AXIS));
		pane.add(deleteButton);
		pane.add(Box.createHorizontalStrut(5));
		pane.add(new JSeparator(SwingConstants.VERTICAL));
		pane.add(Box.createHorizontalStrut(5));
		pane.add(ename);
		pane.add(hireButton);
		pane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		
		add(listScrollPane, BorderLayout.CENTER);
		add(pane, BorderLayout.PAGE_END);
}
}
class DeleteListener implements ActionListener 
{
	public void actionPerformed(ActionEvent e) 
	{
	
	int index = list.getSelectedIndex();
	listModel.remove(index);
	
	int size = listModel.getSize();
	
	if (size == 0) 
		{ //Nobody's left, disable firing.
		deleteButton.setEnabled(false);

		} 
	else 
		{ //Select an index.
		if (index == listModel.getSize()) 
			{
			//removed item in last position
			index--;
			}
		
		list.setSelectedIndex(index);
		list.ensureIndexIsVisible(index);
		}
	}
}
 