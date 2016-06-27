package ass08.trackBeatActors.msgs;


import ass08.trackBeatActors.model.ComplexData;

/**
 * Created by Luca on 27/06/16.
 */

//Contiene i dati utilizzati per l'update della View
public class NewDataMsg {

    private ComplexData complexData;

    public NewDataMsg(ComplexData data){
        this.complexData = data;
    }

    public ComplexData getComplexData(){
        return this.complexData;
    }
}
