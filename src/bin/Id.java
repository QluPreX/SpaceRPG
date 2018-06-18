package bin;
import bin.Handy;
public class Id {
	
	//ID: We (weapon), Ma (materializer), Mi (miningDrill), Ed (3Dcreator), Sp (spacesuit), ...
	Id id;
	
	public Id(Id id) {
		setId(id);
	}
	
	private void setId(Id _id) {
		//ID contains exact 4
		try {
			this.id = _id;
			///TODO : GET ID using ITEM!
		}catch(Exception e) {
			
		}
	}
	
	public boolean validateId(Id id) {
		///TODO: implement this
		return false;
	}
	
	public void addIdToDatabase(Id id) {
		///TODO: implement this
	}
}
