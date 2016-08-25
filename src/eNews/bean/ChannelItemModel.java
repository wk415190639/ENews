package eNews.bean;

public class ChannelItemModel {

	private int Id;
	private String Name;
	private String Selected;

	public ChannelItemModel(int id, String name, String selected) {
		// TODO Auto-generated constructor stub
		this.Id = id;
		this.Name = name;
		this.Selected = selected;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getSelected() {
		return Selected;
	}

	public void setSelected(String selected) {
		Selected = selected;
	}

}
