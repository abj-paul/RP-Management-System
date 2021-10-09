package frontend;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;

import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import backend.DefaultValues;
import backend.ManageDatabase;
import user.Daoist;

import javax.swing.JScrollPane;

public class AdminInterface {

	private JFrame frame;
	private JTextField txtEnterUserName;
	private JTextField textUserName;
	private JTextField txtUserId;
	private JTextField txtUserAge;
	private JTextField txtBuff;
	private JTextField txtDebuff;
	private JTextField txtQiDeviationProbablity;
	private JTextField txtNumOfPill;
	private JTextField txtNumberOfTalisman;
	private JTextField txtNumberOfFormation;
	private JTextField txtAttack;
	private JTextField txtDefense;
	private JTextField txtSpeed;
	private JTextField txtAddCultivationBase;
	private JTextField txtUserName_view;
	private JTable table;
	private JTextField txtAddAdditionalItem;
	private JTextField textField_userName_addUser;
	private JTextField txtUserId_addUser;
	private JTextField txtSpiritStone_addUser;
	private JTextField txt_userName_removeUser;
	private JTextField txtUserId_removeUser;
	
	private JButton btnSearch_view;
	private JButton btnSearch_Edit;
	private JButton btnSaveInfo_Edit;
	private JButton btnAddUser;
	private JButton btnRemoveUser;
	
	private JLabel lbl_commandStatus_addRemove;
	private JLabel lblUsername, lblUserId, lblUserAge, lblCultivationStage, lblDebuff_view, lblbuff, lblQiDeviationProbablity;
	private JList list_StoragePouch;
	private JLabel lblSaveDatabase;


	/**
	 * Launch the application.
	 */
	public static void run_AdminInterface(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminInterface window = new AdminInterface();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AdminInterface() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 682, 512);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(12, 0, 658, 476);
		frame.getContentPane().add(tabbedPane);
		
		JPanel panel_0 = new JPanel();
		tabbedPane.addTab("Home", null, panel_0, null);
		panel_0.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 12, 283, 425);
		panel_0.add(scrollPane);
		
		JList list_event = new JList();
		scrollPane.setViewportView(list_event);
		list_event.setModel(new AbstractListModel() {
			String[] values = new String[] {"Upcoming Events and Rules", "1. Jade Beauty Realm has opened", "2. Treasure Hall now provides Nirvana Stage Pills!", "3. Immortal Han has failed in ascension tribulation! Who will be the next leader?", "4. The lewd realm is selling bigra once again. Be sure to test their intensity before using!", "5. Fighting in arena will come soon!"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(358, 13, 283, 393);
		panel_0.add(scrollPane_4);
		
		table = new JTable();
		scrollPane_4.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"User Name", "Cultivation Base"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(40);
		
		JButton btnNewButton = new JButton("Refresh Table");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//table
				if(table.getRowCount()!=ManageDatabase.database.size()) {
					DefaultTableModel model = (DefaultTableModel)table.getModel();
					for(int i=table.getRowCount(); i<ManageDatabase.database.size(); i++)
						model.addRow(new Object [] {ManageDatabase.database.get(i).getName(), ManageDatabase.database.get(i).getCultivatio_stage()});
				}
			}
		});
		btnNewButton.setBounds(446, 418, 162, 25);
		panel_0.add(btnNewButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setForeground(Color.WHITE);
		tabbedPane.addTab("View User Info", null, panel_1, null);
		panel_1.setLayout(null);
		
		txtUserName_view = new JTextField();
		txtUserName_view.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtUserName_view.setText("");
			}
		});
		txtUserName_view.setToolTipText("Enter User Name");
		txtUserName_view.setText("Enter User Name");
		txtUserName_view.setColumns(10);
		txtUserName_view.setBounds(12, 15, 471, 19);
		panel_1.add(txtUserName_view);
		
		// defining those labels here so that addActionListener can get them		
		btnSearch_view = new JButton("Search");
		btnSearch_view.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Update all those labels
				int index = ManageDatabase.search_user(txtUserName_view.getText(), null);
				if(index==-1) txtUserName_view.setText("User Not Found!");
				else {
					lblUsername.setText("User Name: "+ManageDatabase.database.get(index).getName());
					lblUserId.setText("User Id: "+ManageDatabase.database.get(index).getUser_id());
					lblUserAge.setText("Age: "+ManageDatabase.database.get(index).getAge());
					lblCultivationStage.setText("Cultivation Stage: "+ManageDatabase.database.get(index).getCultivatio_stage());
					lblDebuff_view.setText("Debuff: "+ManageDatabase.database.get(index).get_last_Debuff_effect());
					lblbuff.setText("Buff: "+ManageDatabase.database.get(index).get_last_Buff_effect());
					lblQiDeviationProbablity.setText("Qi Deviation Probablity: "+ManageDatabase.database.get(index).getQiDeviationProbablity());
					
					// in storage pouch list, add - storage pouch , talisman, treasures
					DefaultListModel DLM = new DefaultListModel();
					ArrayList<String> temp = ManageDatabase.database.get(index).getStorage_pouch();
					for(int i=0; i<temp.size(); i++) DLM.addElement(temp.get(i));
					temp = ManageDatabase.database.get(index).getTalisman();
					for(int i=0; i<temp.size(); i++) DLM.addElement(temp.get(i));
					temp = ManageDatabase.database.get(index).getTreasure();
					for(int i=0; i<temp.size(); i++) DLM.addElement(temp.get(i));
					// pills, formation, cultivation art
					temp = ManageDatabase.database.get(index).getPills_taken();
					for(int i=0; i<temp.size(); i++) DLM.addElement(temp.get(i));
					temp = ManageDatabase.database.get(index).getFormation();
					for(int i=0; i<temp.size(); i++) DLM.addElement(temp.get(i));
					temp = ManageDatabase.database.get(index).getCultivationArt();
					for(int i=0; i<temp.size(); i++) DLM.addElement(temp.get(i));
					
					list_StoragePouch.setModel(DLM);
				}
				
			}
		});
		btnSearch_view.setBounds(506, 12, 117, 25);
		panel_1.add(btnSearch_view);
		
		lblUsername = new JLabel("UserName:");
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsername.setBounds(238, 59, 403, 15);
		panel_1.add(lblUsername);
		
		lblUserId = new JLabel("User Id:");
		lblUserId.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserId.setBounds(227, 88, 403, 15);
		panel_1.add(lblUserId);
		
		lblUserAge = new JLabel("User Age:");
		lblUserAge.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserAge.setBounds(238, 115, 385, 15);
		panel_1.add(lblUserAge);
		
		lblCultivationStage = new JLabel("Cultivation Stage:");
		lblCultivationStage.setForeground(Color.MAGENTA);
		lblCultivationStage.setHorizontalAlignment(SwingConstants.CENTER);
		lblCultivationStage.setBounds(238, 142, 385, 15);
		panel_1.add(lblCultivationStage);
		
		lblDebuff_view = new JLabel("Debuff:");
		lblDebuff_view.setHorizontalAlignment(SwingConstants.CENTER);
		lblDebuff_view.setBounds(238, 171, 403, 15);
		panel_1.add(lblDebuff_view);
		
		JLabel lblStoragePouce = new JLabel("Storage Pouch:");
		lblStoragePouce.setForeground(Color.RED);
		lblStoragePouce.setHorizontalAlignment(SwingConstants.CENTER);
		lblStoragePouce.setBounds(12, 46, 194, 15);
		panel_1.add(lblStoragePouce);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 73, 209, 364);
		panel_1.add(scrollPane_1);
		
		list_StoragePouch = new JList();
		list_StoragePouch.setModel(new AbstractListModel() {
			String[] values = new String[] {"Storage Pouch is Empty!"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list_StoragePouch.setSelectedIndex(2);
		scrollPane_1.setViewportView(list_StoragePouch);
		
		lblbuff = new JLabel("Buff:");
		lblbuff.setHorizontalAlignment(SwingConstants.CENTER);
		lblbuff.setBounds(218, 204, 194, 15);
		panel_1.add(lblbuff);
		
		lblQiDeviationProbablity = new JLabel("Qi Deviation Probablity:");
		lblQiDeviationProbablity.setHorizontalAlignment(SwingConstants.CENTER);
		lblQiDeviationProbablity.setBounds(424, 204, 229, 15);
		panel_1.add(lblQiDeviationProbablity);
		
		JLabel lblInternalInfo = new JLabel("Internal Info");
		lblInternalInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblInternalInfo.setForeground(Color.RED);
		lblInternalInfo.setBounds(218, 231, 219, 15);
		panel_1.add(lblInternalInfo);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(218, 258, 209, 179);
		panel_1.add(scrollPane_2);
		
		JList list_internal_info = new JList();
		scrollPane_2.setViewportView(list_internal_info);
		list_internal_info.setModel(new AbstractListModel() {
			String[] values = new String[] {"", "Active Status", "Cultivation Speed", "Body Physique", "Cultivation Speed Buff", "Yang Yin Ratio"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		
		JLabel lblCultivationArts = new JLabel("Current Missions");
		lblCultivationArts.setHorizontalAlignment(SwingConstants.CENTER);
		lblCultivationArts.setForeground(Color.RED);
		lblCultivationArts.setBounds(434, 231, 219, 15);
		panel_1.add(lblCultivationArts);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(434, 258, 209, 179);
		panel_1.add(scrollPane_3);
		
		JList list_current_mission = new JList();
		list_current_mission.setModel(new AbstractListModel() {
			String[] values = new String[] {"Kill the Red Bear", "Collect 540 Boar Tusks", "Collect 100 year old ginseng", "Look after the medicine garden in west mapple mountain valley", "Kill the robbers in Yin Rai State"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		scrollPane_3.setViewportView(list_current_mission);
		
		JPanel panel_2 = new JPanel();
		panel_2.setForeground(Color.LIGHT_GRAY);
		tabbedPane.addTab("Edit User Info", null, panel_2, null);
		panel_2.setLayout(null);
		
		txtEnterUserName = new JTextField();
		txtEnterUserName.setToolTipText("Enter User Name");
		txtEnterUserName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtEnterUserName.setText("");
			}
		});
		txtEnterUserName.setText("Enter User Name");
		txtEnterUserName.setBounds(12, 23, 471, 19);
		panel_2.add(txtEnterUserName);
		txtEnterUserName.setColumns(10);
		
		btnSearch_Edit = new JButton("Search");
		btnSearch_Edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = ManageDatabase.search_user(txtEnterUserName.getText(), txtUserName_view.getText());
				if(index==-1) txtUserName_view.setText(txtUserName_view.getText()+" Not Found!");
				else {
					textUserName.setText(ManageDatabase.database.get(index).getName());
					txtUserId.setText(ManageDatabase.database.get(index).getUser_id());
					txtUserAge.setText(ManageDatabase.database.get(index).getAge());
					
					txtBuff.setText(ManageDatabase.database.get(index).get_last_Buff_effect());
					txtDebuff.setText(ManageDatabase.database.get(index).get_last_Debuff_effect());
					txtQiDeviationProbablity.setText(Double.toString(ManageDatabase.database.get(index).getQiDeviationProbablity()));
					
					//txtpnARegularUser LATER
					txtNumOfPill.setText("0");
					txtNumberOfTalisman.setText("0");
					txtNumberOfFormation.setText("0");
					
					txtAttack.setText(Double.toString(ManageDatabase.database.get(index).getStat_attack()));
					txtDefense.setText(Double.toString(ManageDatabase.database.get(index).getStat_defense()));
					txtSpeed.setText(Double.toString(ManageDatabase.database.get(index).getStat_speed()));
					
					txtAddCultivationBase.setText(Double.toString(ManageDatabase.database.get(index).getCultivation_base()));
					txtAddAdditionalItem.setText("A random rock.");

				}
			}
		});
		btnSearch_Edit.setBounds(506, 20, 117, 25);
		panel_2.add(btnSearch_Edit);
		
		JLabel lblEditInformation = new JLabel("Edit Information");
		lblEditInformation.setForeground(Color.MAGENTA);
		lblEditInformation.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditInformation.setBounds(12, 67, 611, 15);
		panel_2.add(lblEditInformation);
		
		textUserName = new JTextField();
		textUserName.setToolTipText("New User Name");
		textUserName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				textUserName.setText("");
			}
		});
		textUserName.setText("User Name");
		textUserName.setColumns(10);
		textUserName.setBounds(12, 105, 142, 19);
		panel_2.add(textUserName);
		
		txtUserId = new JTextField();
		txtUserId.setToolTipText("New User Id");
		txtUserId.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtUserId.setText("");
			}
		});
		txtUserId.setText("User Id");
		txtUserId.setColumns(10);
		txtUserId.setBounds(12, 136, 142, 19);
		panel_2.add(txtUserId);
		
		txtUserAge = new JTextField();
		txtUserAge.setToolTipText("New Age");
		txtUserAge.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtUserAge.setText("");
			}
		});
		txtUserAge.setText("User Age");
		txtUserAge.setColumns(10);
		txtUserAge.setBounds(12, 170, 142, 19);
		panel_2.add(txtUserAge);
		
		txtBuff = new JTextField();
		txtBuff.setToolTipText("New Buff");
		txtBuff.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtBuff.setText("");
			}
			
		});
		txtBuff.setText("Buff");
		txtBuff.setColumns(10);
		txtBuff.setBounds(188, 105, 142, 19);
		panel_2.add(txtBuff);
		
		txtDebuff = new JTextField();
		txtDebuff.setToolTipText("New Debuff");
		txtDebuff.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtDebuff.setText("");
			}
		});
		txtDebuff.setText("Debuff");
		txtDebuff.setColumns(10);
		txtDebuff.setBounds(188, 136, 142, 19);
		panel_2.add(txtDebuff);
		
		txtQiDeviationProbablity = new JTextField();
		txtQiDeviationProbablity.setToolTipText("Edited Qi Deviation Probablity");
		txtQiDeviationProbablity.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtQiDeviationProbablity.setText("");
			}
		});
		txtQiDeviationProbablity.setText("Qi Deviation Probablity");
		txtQiDeviationProbablity.setColumns(10);
		txtQiDeviationProbablity.setBounds(188, 170, 142, 19);
		panel_2.add(txtQiDeviationProbablity);
		
		JComboBox comboBox_Tresure = new JComboBox();
		comboBox_Tresure.setModel(new DefaultComboBoxModel(new String[] {"Add a treasure", "Flying Sword", "Golden Needle", "Wooden Alms Bowl"}));
		comboBox_Tresure.setBounds(342, 102, 206, 24);
		panel_2.add(comboBox_Tresure);
		
		JComboBox comboBox_cultivationArt = new JComboBox();
		comboBox_cultivationArt.setModel(new DefaultComboBoxModel(new String[] {"Add Cultivation Art", "Chaotic Source Scripture.", "Grand Five Elements Scripture.", "Eternal Flames Scripture.", "Frozen Heaven Scripture."}));
		comboBox_cultivationArt.setBounds(342, 133, 206, 24);
		panel_2.add(comboBox_cultivationArt);
		
		JComboBox comboBox_sect = new JComboBox();
		comboBox_sect.setModel(new DefaultComboBoxModel(new String[] {"Select a Sect", "Soaring Sword Sect", "Flying Needle Sect", "Ghotly Wails Sect", "Wooden Paradise Sect"}));
		comboBox_sect.setBounds(342, 167, 206, 24);
		panel_2.add(comboBox_sect);
		
		JTextPane txtpnARegularUser = new JTextPane();
		txtpnARegularUser.setToolTipText("Edit User Status - is he regular?");
		txtpnARegularUser.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtpnARegularUser.setText("");
			}
		});
		txtpnARegularUser.setText("A Regular User");
		txtpnARegularUser.setBounds(560, 103, 81, 86);
		panel_2.add(txtpnARegularUser);
		
		JComboBox comboBox_pill = new JComboBox();
		comboBox_pill.setModel(new DefaultComboBoxModel(new String[] {"Add a Pill", "Qi Refining Pill ", "Bone Refining Pill", "Nirvana Realm Pill", "Celestial pill", "Sacred pill", "Star pill", "Heavenly pill", "Emperor pill "}));
		comboBox_pill.setBounds(12, 231, 206, 24);
		panel_2.add(comboBox_pill);
		
		txtNumOfPill = new JTextField();
		txtNumOfPill.setToolTipText("Edit number of pills");
		txtNumOfPill.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtNumOfPill.setText("");
			}
		});
		txtNumOfPill.setText("Number Of Pills");
		txtNumOfPill.setColumns(10);
		txtNumOfPill.setBounds(46, 267, 130, 19);
		panel_2.add(txtNumOfPill);
		
		JComboBox comboBox_talisman = new JComboBox();
		comboBox_talisman.setModel(new DefaultComboBoxModel(new String[] {"Add a talisman", "Golden Barrier Talisman", "Refill Mana Talisman"}));
		comboBox_talisman.setBounds(230, 231, 206, 24);
		panel_2.add(comboBox_talisman);
		
		txtNumberOfTalisman = new JTextField();
		txtNumberOfTalisman.setToolTipText("Edit number of talisman");
		txtNumberOfTalisman.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtNumberOfTalisman.setText("");
			}
		});
		txtNumberOfTalisman.setText("Number Of talisman");
		txtNumberOfTalisman.setColumns(10);
		txtNumberOfTalisman.setBounds(264, 267, 142, 19);
		panel_2.add(txtNumberOfTalisman);
		
		JComboBox comboBox_formation = new JComboBox();
		comboBox_formation.setModel(new DefaultComboBoxModel(new String[] {"Add/Remove a Formation", "True Yang Formation ", "Golden Qi Formation"}));
		comboBox_formation.setBounds(447, 231, 206, 24);
		panel_2.add(comboBox_formation);
		
		txtNumberOfFormation = new JTextField();
		txtNumberOfFormation.setToolTipText("Edit number of formation");
		txtNumberOfFormation.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtNumberOfFormation.setText("");
			}
		});
		txtNumberOfFormation.setText("Number Of formation");
		txtNumberOfFormation.setColumns(10);
		txtNumberOfFormation.setBounds(481, 267, 142, 19);
		panel_2.add(txtNumberOfFormation);
		
		JLabel lblStats = new JLabel("Stats");
		lblStats.setForeground(Color.MAGENTA);
		lblStats.setHorizontalAlignment(SwingConstants.CENTER);
		lblStats.setBounds(12, 310, 206, 15);
		panel_2.add(lblStats);
		
		txtAttack = new JTextField();
		txtAttack.setToolTipText("Edit stat - attack");
		txtAttack.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtAttack.setText("");
			}
		});
		txtAttack.setText("Attack");
		txtAttack.setColumns(10);
		txtAttack.setBounds(12, 347, 142, 19);
		panel_2.add(txtAttack);
		
		txtDefense = new JTextField();
		txtDefense.setToolTipText("Edit stat - defense");
		txtDefense.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtDefense.setText("");
			}
		});
		txtDefense.setText("Defense");
		txtDefense.setColumns(10);
		txtDefense.setBounds(12, 378, 142, 19);
		panel_2.add(txtDefense);
		
		txtSpeed = new JTextField();
		txtSpeed.setToolTipText("Edit stat - speed");
		txtSpeed.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtSpeed.setText("");
			}
		});
		txtSpeed.setText("Speed");
		txtSpeed.setColumns(10);
		txtSpeed.setBounds(12, 409, 142, 19);
		panel_2.add(txtSpeed);
		
		txtAddCultivationBase = new JTextField();
		txtAddCultivationBase.setToolTipText("Edit Cultivation Base");
		txtAddCultivationBase.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtAddCultivationBase.setText("");
			}
		});
		txtAddCultivationBase.setForeground(Color.MAGENTA);
		txtAddCultivationBase.setText("Add Cultivation Base(percentage)");
		txtAddCultivationBase.setColumns(10);
		txtAddCultivationBase.setBounds(188, 347, 248, 19);
		panel_2.add(txtAddCultivationBase);
		
		JComboBox comboBox_cultivation_stage = new JComboBox();
		comboBox_cultivation_stage.setModel(new DefaultComboBoxModel(new String[] {"Cultivation Stage", "Qi Refining Stage 1", "Qi Refining Stage 2", "Qi Refining Stage 3", "Qi Refining Stage 4", "Qi Refining Stage 5", "Qi Refining Stage 6", "Qi Refining Stage 7", "Qi Refining Stage 8", "Qi Refining Stage 9"}));
		comboBox_cultivation_stage.setBounds(188, 375, 248, 24);
		panel_2.add(comboBox_cultivation_stage);
		
		btnSaveInfo_Edit = new JButton("Save All Info");
		btnSaveInfo_Edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = ManageDatabase.search_user(txtUserName_view.getText(), null);
				if(index==-1) txtUserName_view.setText(txtUserName_view.getText()+" Not Found!");
				else {
					ManageDatabase.database.get(index).setName(textUserName.getText());
					ManageDatabase.database.get(index).setUser_id(txtUserId.getText());
					ManageDatabase.database.get(index).setAge(txtUserAge.getText());
					
					ManageDatabase.database.get(index).addBuff_effect(txtBuff.getText());
					ManageDatabase.database.get(index).addDebuff_effect(txtDebuff.getText());
					ManageDatabase.database.get(index).setQiDeviationProbablity(Double.parseDouble(txtQiDeviationProbablity.getText()));
					
					ManageDatabase.database.get(index).setStat_attack(Double.parseDouble(txtAttack.getText()));
					ManageDatabase.database.get(index).setStat_defense(Double.parseDouble(txtDefense.getText()));
					ManageDatabase.database.get(index).setStat_speed(Double.parseDouble(txtSpeed.getText()));
					
					ManageDatabase.database.get(index).setCultivation_base(Double.parseDouble(txtAddCultivationBase.getText()));
					ManageDatabase.database.get(index).add_to_storage_pouch(txtAddAdditionalItem.getText());
					
					//Combo Boxes
					//ManageDatabase.database.get(index)
					//comboBox_Tresure.getSelectedItem().toString()
					ManageDatabase.database.get(index).add_treasure(comboBox_Tresure.getSelectedItem().toString());
					ManageDatabase.database.get(index).add_cultivationArt(comboBox_cultivationArt.getSelectedItem().toString());
					ManageDatabase.database.get(index).setCurrent_sect(comboBox_sect.getSelectedItem().toString());
					
					ManageDatabase.database.get(index).add_pill(comboBox_pill.getSelectedItem().toString());
					ManageDatabase.database.get(index).add_talisman(comboBox_talisman.getSelectedItem().toString());
					ManageDatabase.database.get(index).add_formation(comboBox_formation.getSelectedItem().toString());
					
					String stage = comboBox_cultivation_stage.getSelectedItem().toString();
					if(!stage.equals(DefaultValues.DEFAULT_CULTIVATIONN_STAGE)) ManageDatabase.database.get(index).setCultivatio_stage(stage);
					
					// Set the text area with default text
					textUserName.setText("User Name");
					txtUserId.setText("User ID");
					txtUserAge.setText("Age");
					
					txtBuff.setText("Buff");
					txtDebuff.setText("Debuff");
					txtQiDeviationProbablity.setText("Qi Deviation Probablity: ");
					
					//txtpnARegularUser LATER
					txtNumOfPill.setText("0");
					txtNumberOfTalisman.setText("0");
					txtNumberOfFormation.setText("0");
					
					txtAttack.setText("Attack");
					txtDefense.setText("Defense");
					txtSpeed.setText("Speed");
					
					txtAddCultivationBase.setText("Cultivation Base(%)");
					txtAddAdditionalItem.setText("A random rock.");
				}
			}
		});
		btnSaveInfo_Edit.setToolTipText("All information above will be saved in the database for this user, The previous information will be lost.");
		btnSaveInfo_Edit.setBounds(470, 344, 153, 53);
		panel_2.add(btnSaveInfo_Edit);
		
		txtAddAdditionalItem = new JTextField();
		txtAddAdditionalItem.setToolTipText("Add Additional item to Storage Pouch");
		txtAddAdditionalItem.setText("Add Additional item to Storage Pouch");
		txtAddAdditionalItem.setForeground(Color.MAGENTA);
		txtAddAdditionalItem.setColumns(10);
		txtAddAdditionalItem.setBounds(188, 409, 248, 19);
		panel_2.add(txtAddAdditionalItem);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setForeground(Color.LIGHT_GRAY);
		tabbedPane.addTab("Add New User", null, panel_3, null);
		
		JLabel lblEditInformation_1 = new JLabel("Add User - Basic User Information");
		lblEditInformation_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditInformation_1.setForeground(Color.MAGENTA);
		lblEditInformation_1.setBounds(12, 12, 629, 15);
		panel_3.add(lblEditInformation_1);
		
		textField_userName_addUser = new JTextField();
		textField_userName_addUser.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				textField_userName_addUser.setText("");
			}
		});
		textField_userName_addUser.setToolTipText("New User Name");
		textField_userName_addUser.setText("User Name");
		textField_userName_addUser.setColumns(10);
		textField_userName_addUser.setBounds(40, 49, 173, 19);
		panel_3.add(textField_userName_addUser);
		
		txtUserId_addUser = new JTextField();
		txtUserId_addUser.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtUserId_addUser.setText("");
			}
		});
		txtUserId_addUser.setToolTipText("New User Id");
		txtUserId_addUser.setText("User Id");
		txtUserId_addUser.setColumns(10);
		txtUserId_addUser.setBounds(40, 80, 173, 19);
		panel_3.add(txtUserId_addUser);
		
		txtSpiritStone_addUser = new JTextField();
		txtSpiritStone_addUser.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtSpiritStone_addUser.setText("");
			}
		});
		txtSpiritStone_addUser.setToolTipText("Sprit Stones");
		txtSpiritStone_addUser.setText("Spirit Stone");
		txtSpiritStone_addUser.setColumns(10);
		txtSpiritStone_addUser.setBounds(239, 49, 190, 19);
		panel_3.add(txtSpiritStone_addUser);
		
		JComboBox comboBox_cultivation_art_addUser = new JComboBox();
		comboBox_cultivation_art_addUser.setModel(new DefaultComboBoxModel(new String[] {"Add Cultivation Art", "Chaotic Source Scripture.", "Grand Five Elements Scripture.", "Eternal Flames Scripture.", "Frozen Heaven Scripture."}));
		comboBox_cultivation_art_addUser.setBounds(239, 77, 190, 24);
		panel_3.add(comboBox_cultivation_art_addUser);
		
		btnAddUser = new JButton("Add User");
		btnAddUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Daoist daoist = new Daoist();
				daoist.setBuff_effect(new ArrayList<String>());
				daoist.setCultivatio_stage("Qi Gathering Stage 1");
				daoist.setCultivation_base(1);
				daoist.setCultivationArt(new ArrayList<String>());
				daoist.add_cultivationArt(comboBox_cultivation_art_addUser.getSelectedItem().toString());
				daoist.setCurrent_sect(null);
				daoist.setDebuff_effect(new ArrayList<String>());
				daoist.setFormation(new ArrayList<String>());
				daoist.setName(textField_userName_addUser.getText());
				daoist.setPills_taken(new ArrayList<String>());
				daoist.setQiDeviationProbablity(0.01);
				daoist.setSpirit_stones(Integer.parseInt(txtSpiritStone_addUser.getText())); // WE GIVE 5000SS TO ALL DAOIST AT BEGINNING
				daoist.setStat_attack(100); // EDIT IT BASED ON STANDARD
				daoist.setStat_defense(100);
				daoist.setStat_speed(100);
				daoist.setStorage_pouch(new ArrayList<String>());
				daoist.add_item_in_storagePouch("Cultivation Silk Robe");
				daoist.setTalisman(new ArrayList<String>());
				daoist.setTreasure(new ArrayList<String>());
				daoist.setUser_id(txtUserId_addUser.getText());
				daoist.setAge("20");
				
				backend.ManageDatabase.database.add(daoist);
				backend.ManageDatabase.print_database(); // FOR DEBUG PURPOSE
				lbl_commandStatus_addRemove.setText(txt_userName_removeUser.getText()+" has been added.");
			}
		});
		btnAddUser.setBounds(495, 65, 146, 25);
		panel_3.add(btnAddUser);
		
		JLabel lblEditInformation_1_1 = new JLabel("Remove User - Give User Id or User Name");
		lblEditInformation_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditInformation_1_1.setForeground(Color.MAGENTA);
		lblEditInformation_1_1.setBounds(12, 167, 629, 15);
		panel_3.add(lblEditInformation_1_1);
		
		txt_userName_removeUser = new JTextField();
		txt_userName_removeUser.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txt_userName_removeUser.setText("");
			}
		});
		txt_userName_removeUser.setToolTipText("New User Name");
		txt_userName_removeUser.setText("User Name");
		txt_userName_removeUser.setColumns(10);
		txt_userName_removeUser.setBounds(40, 207, 173, 19);
		panel_3.add(txt_userName_removeUser);
		
		JLabel lblNewLabel_or = new JLabel("or");
		lblNewLabel_or.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_or.setBounds(220, 209, 70, 15);
		panel_3.add(lblNewLabel_or);
		
		txtUserId_removeUser = new JTextField();
		txtUserId_removeUser.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtUserId_removeUser.setText("");
			}
		});
		txtUserId_removeUser.setToolTipText("New User id");
		txtUserId_removeUser.setText("User Id");
		txtUserId_removeUser.setColumns(10);
		txtUserId_removeUser.setBounds(291, 207, 173, 19);
		panel_3.add(txtUserId_removeUser);
		
		btnRemoveUser = new JButton("Remove User");
		btnRemoveUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int user_index = ManageDatabase.search_user(txt_userName_removeUser.getText(), txtUserId_removeUser.getText());
				if(user_index==-1) {
					lbl_commandStatus_addRemove.setText(txt_userName_removeUser.getText()+" or his id "+txt_userName_removeUser.getText()+" could not be found.");
				}
				else {
					lbl_commandStatus_addRemove.setText(txt_userName_removeUser.getText()+" was found.");
				}
			}
		});
		btnRemoveUser.setBounds(495, 204, 150, 25);
		panel_3.add(btnRemoveUser);
		
		lbl_commandStatus_addRemove = new JLabel("Command Status");
		lbl_commandStatus_addRemove.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_commandStatus_addRemove.setForeground(Color.MAGENTA);
		lbl_commandStatus_addRemove.setBounds(12, 422, 629, 15);
		panel_3.add(lbl_commandStatus_addRemove);
		
		lblSaveDatabase = new JLabel("Save Database in Harddrive");
		lblSaveDatabase.setHorizontalAlignment(SwingConstants.CENTER);
		lblSaveDatabase.setForeground(Color.MAGENTA);
		lblSaveDatabase.setBounds(12, 272, 629, 15);
		panel_3.add(lblSaveDatabase);
		
		JButton btnSaveDatabase = new JButton("Save Database");
		btnSaveDatabase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ManageDatabase.save_database(ManageDatabase.database);
			}
		});
		btnSaveDatabase.setForeground(Color.RED);
		btnSaveDatabase.setBounds(257, 299, 146, 25);
		panel_3.add(btnSaveDatabase);
		
		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("Help", null, panel_4, null);
		panel_4.setLayout(null);
		
		JLabel lbl_help_1 = new JLabel("1. Hover over any text field if you forget what that text field does.");
		lbl_help_1.setBounds(0, 12, 641, 15);
		panel_4.add(lbl_help_1);
		
		JLabel lbl_help_2 = new JLabel("2. Must save database before quiting, otherwise all data during its runtime will be lost.");
		lbl_help_2.setBounds(0, 39, 641, 15);
		panel_4.add(lbl_help_2);
	}
}
