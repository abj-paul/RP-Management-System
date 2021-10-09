package user;

import java.util.ArrayList;

import backend.DefaultValues;
import backend.ManageDatabase;

public class Daoist { // Total 19 characteristics
	private String name;
	private String user_id;
	private String age;
	private ArrayList<String> buff_effect;
	private ArrayList<String> debuff_effect;
	private double qiDeviationProbablity;
	private ArrayList<String> treasure;
	private ArrayList<String> cultivationArt;
	private String current_sect;
	private ArrayList<String> pills_taken; // Consider case of pills that were not consumed yet
	private ArrayList<String> talisman;
	private ArrayList<String> formation;
	private double stat_attack;
	private double stat_defense;
	private double stat_speed;
	
	private double cultivation_base; // percentage
	private String cultivatio_stage;
	
	private int spirit_stone;
	private ArrayList<String> storage_pouch;
	
	public void print_DaoistInfo() {
		System.out.println("-----------------------------------------------------------");
		System.out.println(name+","+user_id+","+qiDeviationProbablity+","+current_sect+","+stat_attack+","+stat_defense+","+stat_speed);
		System.out.println(cultivatio_stage+","+cultivation_base+","+spirit_stone);
		this.print_arrayList(buff_effect);
		this.print_arrayList(cultivationArt);
		this.print_arrayList(debuff_effect);
		this.print_arrayList(formation);
		this.print_arrayList(pills_taken);
		this.print_arrayList(storage_pouch);
		this.print_arrayList(talisman);
		this.print_arrayList(treasure);
	}
	
	public void print_arrayList(ArrayList<String>arr) {
		for(int i=0;  i<arr.size(); i++) {
			System.out.println(arr.get(i));
		}
	}
	
	public String arrayList_toString(ArrayList<String>arr) {
		String s = "null";
		if(arr.size()==0) return s;
		for(int i=0; i<arr.size(); i++) s= s +","+arr.get(i);
		return s;
	}
	
	private ArrayList<String> convert_string_to_arrayList(String line){
		ArrayList<String>arr = new ArrayList<String>();
		if(line.equals("null")) return arr;
		
		String[] split_line = line.split(",");
		for(int i=0; i<split_line.length; i++) {
			arr.add(split_line[i]);
		}
		return arr;
	}
	@Override
	public String toString() {
		String s = name+"|"+user_id+"|"+qiDeviationProbablity+"|"+current_sect+"|"+stat_attack+"|"+stat_defense+"|"+stat_speed+"|"+cultivatio_stage+"|"+cultivation_base+"|"+spirit_stone;
		s = s+"|"+this.arrayList_toString(cultivationArt);
		s = s+"|"+this.arrayList_toString(buff_effect);
		s = s+"|"+this.arrayList_toString(debuff_effect);
		s = s+"|"+this.arrayList_toString(formation);
		s = s+"|"+this.arrayList_toString(talisman);
		s = s+"|"+this.arrayList_toString(pills_taken);
		s = s+"|"+this.arrayList_toString(treasure);
		s = s+"|"+this.arrayList_toString(storage_pouch);
		s = s+"|"+age;
		return s;
	}

	private String fill_data(String data) {
		if(data.equals("null")) return null;
		else return data;
	}
	
	public void load_user(String line) {
		
		String[] a = line.split("\\|");
		//this.name = a[0];
		this.name = fill_data(a[0]);
		this.user_id = fill_data(a[1]);
		this.qiDeviationProbablity = Double.parseDouble(a[2]);
		this.current_sect = fill_data(a[3]);
		this.stat_attack = Double.parseDouble(a[4]);
		this.stat_defense = Double.parseDouble(a[5]);
		this.stat_speed = Double.parseDouble(a[6]);
		this.cultivatio_stage = fill_data(a[7]);
		this.cultivation_base = Double.parseDouble(a[8]);
		this.spirit_stone = Integer.parseInt(a[9]);

		this.cultivationArt = convert_string_to_arrayList(a[10]);
		this.buff_effect = convert_string_to_arrayList(a[11]);

		this.debuff_effect = convert_string_to_arrayList(a[12]);
		this.formation = convert_string_to_arrayList(a[13]);

		this.talisman = convert_string_to_arrayList(a[14]);
		this.pills_taken = convert_string_to_arrayList(a[15]);

		this.treasure = convert_string_to_arrayList(a[16]);
		this.storage_pouch = convert_string_to_arrayList(a[17]);
		
		this.age = fill_data(a[18]);


	}
	
	public Daoist() {} // Empty 
	
	public Daoist(String name, String user_id, int spirit_stone) {
		this.name = name;
		this.user_id = user_id;
		this.spirit_stone = spirit_stone;
	}
	
	public void add_item_in_storagePouch(String item) {
		this.storage_pouch.add(item);
	}
	
	public String get_last_Buff_effect() {
		if(this.buff_effect.isEmpty()) return "None.";
		return this.buff_effect.get(this.buff_effect.size()-1);
	}
	
	public String get_last_Debuff_effect() {
		if(this.debuff_effect.isEmpty()) return "None.";
		return this.debuff_effect.get(this.debuff_effect.size()-1);
	}
	
	public void addBuff_effect(String x) {
		if(this.buff_effect.size()==0) buff_effect.add(x);
		
		int top_index = this.buff_effect.size()-1;
		if(x.equals(this.buff_effect.get(top_index)) || x.equals(DefaultValues.BUFF_EFFECT)) return;
		
		this.buff_effect.add(x);
	}
	
	public void addDebuff_effect(String x) {
		if(this.debuff_effect.size()==0) debuff_effect.add(x);
		
		int top_index = this.debuff_effect.size()-1;
		if(x.equals(this.debuff_effect.get(top_index)) || x.equals(DefaultValues.DEBUFF_EFFECT)) return;
		
		this.debuff_effect.add(x);
	}
	
	public void add_to_storage_pouch(String x) {
		if(this.storage_pouch.size()==0) storage_pouch.add(x);
		
		int top_index = this.storage_pouch.size()-1;
		if(x.equals(storage_pouch.get(top_index)) || x.equals(DefaultValues.ADDITIONAL_ITEM)) return;
		
		this.storage_pouch.add(x);
	}
	
	// The following are for combo boxes so we did not populate them with anything.
	public void add_treasure(String t) {		
		if(t.equals(DefaultValues.DEFAULT_TREASURE)) return;
		
		if(this.treasure.size()!=0 && this.treasure.remove(t)) return;
		
		this.treasure.add(t);
	}
	
	public void add_cultivationArt(String c) {
		if(c.equals(DefaultValues.DEFAULT_CULTIVATION_ART)) return;
		if(this.cultivationArt.size()!=0 && this.cultivationArt.remove(c)) return;
		this.cultivationArt.add(c);
	}
	
	public void add_pill(String p) {
		if(p.equals(DefaultValues.DEFAULT_PILL)) return;
		this.pills_taken.add(p);
	}
	
	public void add_talisman(String t) {
		if(t.equals(DefaultValues.DEFAULT_TALISMAN)) return;
		this.talisman.add(t);
	}
	
	public void add_formation(String f) {
		if(f.equals(DefaultValues.DEFAULT_FORMATION)) return;
		else this.formation.add(f);
	}
	
	
	// Following codes are just for getter-setters, nothing unusual
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public ArrayList<String> getBuff_effect() {
		return buff_effect;
	}

	public void setBuff_effect(ArrayList<String> buff_effect) {
		this.buff_effect = buff_effect;
	}

	public ArrayList<String> getDebuff_effect() {
		return debuff_effect;
	}

	public void setDebuff_effect(ArrayList<String> debuff_effect) {
		this.debuff_effect = debuff_effect;
	}

	public double getQiDeviationProbablity() {
		return qiDeviationProbablity;
	}

	public void setQiDeviationProbablity(double qiDeviationProbablity) {
		this.qiDeviationProbablity = qiDeviationProbablity;
	}

	public ArrayList<String> getTreasure() {
		return treasure;
	}

	public void setTreasure(ArrayList<String> treasure) {
		this.treasure = treasure;
	}

	public ArrayList<String> getCultivationArt() {
		return cultivationArt;
	}

	public void setCultivationArt(ArrayList<String> cultivationArt) {
		this.cultivationArt = cultivationArt;
	}

	public String getCurrent_sect() {
		return current_sect;
	}

	public void setCurrent_sect(String current_sect) {
		this.current_sect = current_sect;
	}

	public ArrayList<String> getPills_taken() {
		return pills_taken;
	}

	public void setPills_taken(ArrayList<String> pills_taken) {
		this.pills_taken = pills_taken;
	}

	public ArrayList<String> getTalisman() {
		return talisman;
	}

	public void setTalisman(ArrayList<String> talisman) {
		this.talisman = talisman;
	}

	public ArrayList<String> getFormation() {
		return formation;
	}

	public void setFormation(ArrayList<String> formation) {
		this.formation = formation;
	}

	public double getStat_attack() {
		return stat_attack;
	}

	public void setStat_attack(double stat_attack) {
		this.stat_attack = stat_attack;
	}

	public double getStat_defense() {
		return stat_defense;
	}

	public void setStat_defense(double stat_defense) {
		this.stat_defense = stat_defense;
	}

	public double getStat_speed() {
		return stat_speed;
	}

	public void setStat_speed(double stat_speed) {
		this.stat_speed = stat_speed;
	}

	public double getCultivation_base() {
		return cultivation_base;
	}

	public void setCultivation_base(double cultivation_base) {
		this.cultivation_base = cultivation_base;
	}

	public String getCultivatio_stage() {
		return cultivatio_stage;
	}

	public void setCultivatio_stage(String cultivatio_stage) {
		this.cultivatio_stage = cultivatio_stage;
	}

	public ArrayList<String> getStorage_pouch() {
		return storage_pouch;
	}

	public void setStorage_pouch(ArrayList<String> storage_pouch) {
		this.storage_pouch = storage_pouch;
	}
	public int getSpirit_stones() {
		return spirit_stone;
	}

	public void setSpirit_stones(int spirit_stones) {
		this.spirit_stone = spirit_stones;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

}
