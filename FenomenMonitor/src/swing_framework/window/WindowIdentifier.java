package swing_framework.window;

/** ������������� ����, ������� �������������� � ���������� JFrame */
public class WindowIdentifier {
	private String caption;
	/** ������������� ����, ������� �������������� � ���������� JFrame */
	public WindowIdentifier(){
	}
	
	/** ������������� ����, ������� �������������� � ���������� JFrame */
	public WindowIdentifier(String caption){
		this.caption=caption;
	}
	
	/** ������������� ����, ������� �������������� � ���������� JFrame */
	public WindowIdentifier(String caption, boolean singleton){
		this.caption=caption;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}


	@Override
	public boolean equals(Object object){
		if(object instanceof WindowIdentifier){
			if((((WindowIdentifier)object).getCaption()!=null)&&(this.getCaption()!=null)){
				return this.getCaption().equals( ((WindowIdentifier)object).getCaption());
			}else{
				// source Caption is null or destination Caption is null
				return false;
			}
		}else{
			return false;
		}
	}
}
