import java.util.ArrayList;

public class Corp
{

    public ArrayList<Piece> units = new ArrayList<Piece>(); 
    private boolean hasActed = false;
    
    /** Corp constructor **/
    public Corp()
    {
        
    }
    
    // setter method for hasActed
    public void setHasActed(boolean bool) {
        hasActed=bool;
    }
    
    //getter method for hasActed
    public boolean getHasActed() {
        return hasActed;
    }
    
    //getter method for units
    //public ArrayList<Piece> getUnits(){
     //   return this.units;
    //}
    
    // method to add a unit to the corp
    public void addUnit(Piece unit) {
        this.units.add(unit);
    }
    
    /*
    // gives control of all units to kingCorp
    public void reassignUnits(Corp kingCorp) {
        kingCorp.units.addAll(this.units);
    }*/
    
    // method to revert delegate king's pieces to a bishop's Corp
    public void delegate(Corp bishopCorp) {
        
    }

}
